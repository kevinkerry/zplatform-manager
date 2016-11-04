package com.zlebank.zplatform.manager.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.util.SpringApplicationObjectSupport;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.manager.util.TimeUtil;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;
import com.zlebank.zplatform.trade.service.ITxnsLogService;

public class CreateCreditAccountFile extends SpringApplicationObjectSupport{
    private static final Log log = LogFactory.getLog(SaveMemberQueueJob.class);
    @Autowired
    private ITxnsLogService txnsLogService;
    
    private static final String CREDIT_RECHARGE= "CRRECHARGE";
    private static final String CREDIT_CONSUME= "CRCONSUME";
    private static final String CREDIT_REFUND= "CRREFUND";
    private static final String MERCH_ID = "merchId";
    private static final String DATE = "date";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String TOTAL_AMOUNT = "totalAmount";
    private static final String TOTAL_FEE = "totalFee";
    private static final String DELETIMER = "|";
    private static final String RECON_FILE_ROOT_DIR="/memberrecon/";
    private static final String RECON_FILE_LOCAL_ROOT_DIR="/home/web/recon_temp";
    //授信充值
    public void queryCreditRecharge() throws Exception{
        // 取出网络时间
        long time = TimeUtil.syncCurrentTime();
       
        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dateTime = sdf.format(date);
        String dateTime = "20161009";
        // 根据日期取出t_txns_log取出当天进行充值的所有行业专户
        List<?> memberList = txnsLogService.getCreditRechargeByDate(dateTime);

        JSONArray jsonArray = JSONArray.fromObject(memberList);
        JSONObject job = null;
        Map<String, String> memIDMap = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            job = jsonArray.getJSONObject(i);
            memIDMap.put(job.get("ACCMEMBERID").toString(),job.get("ACCMEMBERID").toString());
        }

        StringBuilder fileBuffer = new StringBuilder();
        // 遍历当天所有的行业专户
        for (Map.Entry<String, String> entry : memIDMap.entrySet()) {
            String memberId = entry.getKey();
            fileBuffer.setLength(0);
            fileBuffer.append(MERCH_ID);
            fileBuffer.append(":");
            fileBuffer.append(memberId);
            fileBuffer.append(DELETIMER).append(DATE).append(":" + dateTime);
            // 专户充值汇总信息
            List<?> sumInsteadList = txnsLogService.getSumCreditRecharge(memberId,
                    dateTime);

            // 总交易笔数
            int count = 0;
            // 总充值金额
            Long totalAmount = 0L;
            // 总手续费
            Long sumFee = 0L;
            if (sumInsteadList != null && sumInsteadList.size() > 0) {
                JSONArray json = JSONArray.fromObject(sumInsteadList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                totalAmount += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALAMOUNT").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALAMOUNT").toString());
                sumFee += Long.parseLong(json.getJSONObject(0).get("TOTALFEE")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTALFEE").toString());
            }

            fileBuffer.append("\n");
            fileBuffer.append(TOTAL_COUNT).append(":").append(count)
                    .append(DELETIMER).append(TOTAL_AMOUNT).append(":")
                    .append(totalAmount).append(DELETIMER).append(TOTAL_FEE)
                    .append(":").append(sumFee);
//            List<?> memberDetailedList = txnsLogService
//                    .getInsteadMerchantDetailedByDate(memberId, dateTime);
            List<?> memberDetailedList = txnsLogService.getCreditRechargeDetailByDate(memberId,dateTime);
            JSONArray detailJsonArray = JSONArray
                    .fromObject(memberDetailedList);
            for (int i = 0; i < detailJsonArray.size(); i++) {
                long amount = 0;
                long fee = 0;
                long settAmount = 0;
                job = detailJsonArray.getJSONObject(i);
                amount = Long.valueOf(job.get("AMOUNT").toString());
                fee = StringUtil.isEmpty(job.get("TXNFEE").toString())?0:Long.valueOf(job.get("TXNFEE").toString());
                settAmount = amount - fee;
                fileBuffer.append("\n");
                fileBuffer.append(job.get("ACCORDNO").toString())
                        .append(DELETIMER)
                        .append(job.get("TXNSEQNO").toString())
                        .append(DELETIMER).append(job.get("ACCORDCOMMITIME"))
                        .append(DELETIMER)
                        .append(job.get("BUSICODE").toString())
                        .append(DELETIMER).append("D").append(DELETIMER)
                        .append(amount).append(DELETIMER).append(fee)
                        .append(DELETIMER).append(settAmount);

            }
            fileBuffer.append("\n");
            fileBuffer.append("######");
            String filePath = RECON_FILE_LOCAL_ROOT_DIR+"/" + memberId + "/";
            String fileName = CREDIT_RECHARGE + "_" + memberId + "_" + dateTime + ".txt"; 
            doPrint(fileBuffer.toString(), filePath, fileName, memberId);
       }
    }
    //授信消费
    public void queryCreditConsume() throws Exception{
        // 取出网络时间
        long time = TimeUtil.syncCurrentTime();
       
        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(date);
//        String dateTime = "20161009";
        // 根据日期取出t_txns_log取出当天进行充值的所有行业专户
        List<?> memberList = txnsLogService.getCreditConsumeByDate(dateTime);

        JSONArray jsonArray = JSONArray.fromObject(memberList);
        JSONObject job = null;
        Map<String, String> memIDMap = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            job = jsonArray.getJSONObject(i);
            memIDMap.put(job.get("ACCMEMBERID").toString(),job.get("ACCMEMBERID").toString());
        }

        StringBuilder fileBuffer = new StringBuilder();
        // 遍历当天所有的行业专户
        for (Map.Entry<String, String> entry : memIDMap.entrySet()) {
            String memberId = entry.getKey();
            fileBuffer.setLength(0);
            fileBuffer.append(MERCH_ID);
            fileBuffer.append(":");
            fileBuffer.append(memberId);
            fileBuffer.append(DELETIMER).append(DATE).append(":" + dateTime);
            // 专户充值汇总信息
            List<?> sumInsteadList = txnsLogService.getSumCreditConsume(memberId,
                    dateTime);

            // 总交易笔数
            int count = 0;
            // 总充值金额
            Long totalAmount = 0L;
            // 总手续费
            Long sumFee = 0L;
            if (sumInsteadList != null && sumInsteadList.size() > 0) {
                JSONArray json = JSONArray.fromObject(sumInsteadList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                totalAmount += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALAMOUNT").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALAMOUNT").toString());
                sumFee += Long.parseLong(json.getJSONObject(0).get("TOTALFEE")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTALFEE").toString());
            }

            fileBuffer.append("\n");
            fileBuffer.append(TOTAL_COUNT).append(":").append(count)
                    .append(DELETIMER).append(TOTAL_AMOUNT).append(":")
                    .append(totalAmount).append(DELETIMER).append(TOTAL_FEE)
                    .append(":").append(sumFee);
            List<?> memberDetailedList = txnsLogService.getCreditConsumeDetailByDate(memberId,dateTime);
            JSONArray detailJsonArray = JSONArray
                    .fromObject(memberDetailedList);
            for (int i = 0; i < detailJsonArray.size(); i++) {
                long amount = 0;
                long fee = 0;
                long settAmount = 0;
                job = detailJsonArray.getJSONObject(i);
                amount = Long.valueOf(job.get("AMOUNT").toString());
                fee = StringUtil.isEmpty(job.get("TXNFEE").toString())?0:Long.valueOf(job.get("TXNFEE").toString());
                settAmount = amount - fee;
                fileBuffer.append("\n");
                fileBuffer.append(job.get("ACCORDNO").toString())
                        .append(DELETIMER)
                        .append(job.get("TXNSEQNO").toString())
                        .append(DELETIMER).append(job.get("ACCORDCOMMITIME"))
                        .append(DELETIMER)
                        .append(job.get("BUSICODE").toString())
                        .append(DELETIMER).append("D").append(DELETIMER)
                        .append(amount).append(DELETIMER).append(fee)
                        .append(DELETIMER).append(settAmount);

            }
            fileBuffer.append("\n");
            fileBuffer.append("######");
            String filePath = RECON_FILE_LOCAL_ROOT_DIR+"/" + memberId + "/";
            String fileName = CREDIT_CONSUME + "_" + memberId + "_" + dateTime + ".txt"; 
            doPrint(fileBuffer.toString(), filePath, fileName, memberId);
       }
    }
    //授信退款
    public void queryCreditRefund() throws Exception{
        // 取出网络时间
        long time = TimeUtil.syncCurrentTime();
       
        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(date);
//        String dateTime = "20161009";
        // 根据日期取出t_txns_log取出当天进行充值的所有行业专户
        List<?> memberList = txnsLogService.getCreditRefundByDate(dateTime);

        JSONArray jsonArray = JSONArray.fromObject(memberList);
        JSONObject job = null;
        Map<String, String> memIDMap = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            job = jsonArray.getJSONObject(i);
            memIDMap.put(job.get("ACCMEMBERID").toString(),job.get("ACCMEMBERID").toString());
        }

        StringBuilder fileBuffer = new StringBuilder();
        // 遍历当天所有的行业专户
        for (Map.Entry<String, String> entry : memIDMap.entrySet()) {
            String memberId = entry.getKey();
            fileBuffer.setLength(0);
            fileBuffer.append(MERCH_ID);
            fileBuffer.append(":");
            fileBuffer.append(memberId);
            fileBuffer.append(DELETIMER).append(DATE).append(":" + dateTime);
            // 专户充值汇总信息
            List<?> sumInsteadList = txnsLogService.getSumCreditRefund(memberId,dateTime);

            // 总交易笔数
            int count = 0;
            // 总充值金额
            Long totalAmount = 0L;
            // 总手续费
            Long sumFee = 0L;
            if (sumInsteadList != null && sumInsteadList.size() > 0) {
                JSONArray json = JSONArray.fromObject(sumInsteadList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                totalAmount += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALAMOUNT").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALAMOUNT").toString());
                sumFee += Long.parseLong(json.getJSONObject(0).get("TOTALFEE")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTALFEE").toString());
            }

            fileBuffer.append("\n");
            fileBuffer.append(TOTAL_COUNT).append(":").append(count)
                    .append(DELETIMER).append(TOTAL_AMOUNT).append(":")
                    .append(totalAmount).append(DELETIMER).append(TOTAL_FEE)
                    .append(":").append(sumFee);
            List<?> memberDetailedList = txnsLogService.getCreditRefundDetailByDate(memberId,dateTime);
            JSONArray detailJsonArray = JSONArray
                    .fromObject(memberDetailedList);
            for (int i = 0; i < detailJsonArray.size(); i++) {
                long amount = 0;
                long fee = 0;
                long settAmount = 0;
                job = detailJsonArray.getJSONObject(i);
                amount = Long.valueOf(job.get("AMOUNT").toString());
                fee = StringUtil.isEmpty(job.get("TXNFEE").toString())?0:Long.valueOf(job.get("TXNFEE").toString());
                settAmount = amount - fee;
                fileBuffer.append("\n");
                fileBuffer.append(job.get("ACCORDNO").toString())
                        .append(DELETIMER)
                        .append(job.get("TXNSEQNO").toString())
                        .append(DELETIMER).append(job.get("ACCORDCOMMITIME"))
                        .append(DELETIMER)
                        .append(job.get("BUSICODE").toString())
                        .append(DELETIMER).append("C").append(DELETIMER)
                        .append(amount).append(DELETIMER).append(fee)
                        .append(DELETIMER).append(settAmount)
                        .append(DELETIMER).append(job.get("TXNSEQNO_OG"));//退款有原交易订单号
                        
                       

            }
            fileBuffer.append("\n");
            fileBuffer.append("######");
            String filePath = RECON_FILE_LOCAL_ROOT_DIR+"/" + memberId + "/";
            String fileName = CREDIT_REFUND + "_" + memberId + "_" + dateTime + ".txt"; 
            doPrint(fileBuffer.toString(), filePath, fileName, memberId);
       }
    }
    
    public void doPrint(String fileString,String path,String fileName,String memberId) {
        string2File(fileString, path + fileName);
        File file = new File(path + fileName);
        fetchApplicationContext();
        FTPClientFactory ftpClientFactory = applicationContext.getBean(FTPClientFactory.class);
        AbstractFTPClient ftpClient = ftpClientFactory.getFtpClient();
        try {
            ftpClient.upload(RECON_FILE_ROOT_DIR + memberId, fileName, file);
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
        }
            log.warn("upload to ftp get a exception.caused by:"
                    + e.getMessage());
        }
    }
    
    public boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists())
                distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            char buf[] = new char[1024]; // 字符缓冲区
            int len;
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
