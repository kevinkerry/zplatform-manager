package com.zlebank.zplatform.manager.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.dao.iface.IMemberQueueDAO;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;

/**
 * 
 * @author eason 定时保存商户信息到邮件系统JOB
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
            String urlA = "http://192.168.13.126:8080/merportal/merchant/querySyncMerchanet";
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
               String url = "http://192.168.101.231:8081/mailproxy/email/addEmail.action";
                // 生成激活链接
                // String Md5Url = EncoderByMd5(idCard + memberId + randNum);
                String Md5Url = Md5.getInstance().md5s(
                        idCard + memberId + randNum);
                String content = "http://192.168.13.126:8080/merportal/merchant/activation.html?memberId="
                        + memberId + "&signature=" + Md5Url;
                content = java.net.URLEncoder.encode(content);
                String parameterData = "subject=商户开通激活&consignee_address="
                        + job.get("EMAIL").toString() + "&&content='" + content
                        + "'";
                String flag = this.doPost(url, parameterData);
                JSONObject jsonA = JSONObject.fromObject(flag);
                if (jsonA.get("flag").equals(true)) {
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
        URL localURL = new URL(url);
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

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
