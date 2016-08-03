package com.zlebank.zplatform.manager.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.dao.iface.IMemberQueueDAO;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;
import com.zlebank.zplatform.manager.util.ConsUtil;
import com.zlebank.zplatform.manager.util.MD5Util;



/**
 * 
 * 定时保存商户信息到邮件系统JOB
 *
 * @author eason,yangying
 * @version
 * @date 2016年5月13日 下午12:01:20
 * @since
 */
public class SaveMemberQueueJob {
    @Autowired
    private IMemberQueueDAO iMemberQueueDAO;
    
    private static final Log log = LogFactory.getLog(SaveMemberQueueJob.class);
    public void execute() throws Exception {
    	
      List<?> li = iMemberQueueDAO.getAllMemberQueueMode();
        JSONArray jsonArray = JSONArray.fromObject(li);          
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject job = jsonArray.getJSONObject(i);
            String memberId = job.get("MEMBER_ID").toString();
            ConsUtil consUtil = ConsUtil.getInstance();
            String urlA =consUtil.constantModel.getMerPortalUrl()+consUtil.constantModel.getQuerySyncMerRequest() ; 
            String parameterDataA = "memberId=" + memberId+"&expirationTime="+job.get("EXPIRATION_TIME").toString();
            String res = this.doPost(urlA, parameterDataA);
            JSONObject json = JSONObject.fromObject(res);
            MemberQueueMode member = iMemberQueueDAO.get(Long.parseLong(job
                    .get("ID").toString()));
            member.setSendTimes((member.getSendTimes() + 1));
            // 返回正确
            member.setStatus(json.get("respCode").toString());
            if (json.get("respCode").equals("00")) {
                String randNum = json.get("randNum").toString();
                String idCard = job.get("IDCARD").toString();
                //String url = "http://localhost:8080/mail-proxy/email/addEmail.action";
                // 生成激活链接
                // String Md5Url = EncoderByMd5(idCard + memberId + randNum);
                // String Md5Url = Md5.getInstance().md5s(memberId+idCard + randNum);
                String Md5Url = MD5Util.MD5(memberId+idCard + randNum);                    
                String contentUrl = consUtil.constantModel.getMerPortalWlanUrl()+consUtil.constantModel.getQuerySyncMerNotify()+"?memberId="
                        + memberId + "&signature=" + Md5Url;
                contentUrl = java.net.URLEncoder.encode(contentUrl,"utf-8");
                StringBuilder contentSb =new StringBuilder();
                contentSb.append("尊敬的商户您好，欢迎加入证联金融，距注册成功还差一小步，您需要点击下方的链接进行账户激活。账户激活完成后，请使用商户号及邮箱登录网站。详细信息如下：");
                contentSb.append("\n");
                contentSb.append("激活链接");
                contentSb.append("\n");
                contentSb.append(contentUrl);
                contentSb.append("\n如无法点击，请复制网页地址到浏览器地址栏中打开");
                contentSb.append("\n商户号:").append(member.getMemberId());
                contentSb.append("\n邮箱:").append(member.getEmail());
                contentSb.append("\n密码:您在激活时设置的登录密码，请妥善保管");
                contentSb.append("\n如上述操作遇到问题，请联系我们，客服热线：4001189522 工作时间：周一至周五早9：00-晚6:00 联系邮箱:kefu@zlebank.com ");
                String parameterData = "subject=商户开通激活&consignee_address="
                        + job.get("EMAIL").toString() + "&&content=" + contentSb.toString() ;
                String url3 = consUtil.constantModel.getMailProxyUrl();
                String flag = this.doPost(url3, parameterData);
                flag = URLDecoder.decode(flag,"utf-8");
                if (flag.contains("00")) {
                    member.setFlag("00");
                }
            } else {
                log.info(" querySyncMerchanet is status " + json);
            }
            iMemberQueueDAO.update(member);
            // {"SEND_TIMES":0,"ID":1,"MAX_SEND_TIMES":3,"EMAIL":"252261695@qq.com","STATUS":null,"FLAG":"01","MEMBER_ID":"111"}
        }

    }

    public String doPost(String url, String parameterData) throws Exception {
//        URL localURL = new URL(url);
//        URLConnection connection = new URL(url).openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length",
                String.valueOf(parameterData.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(parameterData.toString());
            outputStreamWriter.flush();
            
            if(log.isDebugEnabled()){
                log.info(" httpURLConnection " + httpURLConnection.getURL()+parameterData.toString());
            }
            
            if (httpURLConnection.getResponseCode() >= 300) {
                
                throw new Exception(
                        "HTTP Request is not success, Response code is "
                                + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer.toString();
    }

}
