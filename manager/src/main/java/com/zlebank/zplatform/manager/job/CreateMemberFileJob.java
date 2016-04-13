package com.zlebank.zplatform.manager.job;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;
import com.zlebank.zplatform.trade.service.ITxnsLogService;

public class CreateMemberFileJob {
    @Autowired
    private ITxnsLogService txnsLogService;

    private static final Log log = LogFactory.getLog(SaveMemberQueueJob.class);
    public void execute() throws Exception {
        //取出网络时间
        long time = TimeUtil.syncCurrentTime();
        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(date);
        dateTime = "20160308";

        // 根据日期取出t_txns_log取出当天清算的所有商户号
        List<?> memberList = txnsLogService.getAllMemberByDate(dateTime);
        List<?> memberListA = txnsLogService
                .getAllMemberByDateByCharge(dateTime);
        JSONArray jsonArray = JSONArray.fromObject(memberList);
        JSONArray jsonArrayA = JSONArray.fromObject(memberListA);
        JSONObject job = null;
        Map<String, String> memIDmap = new HashMap<String, String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            job = jsonArray.getJSONObject(i);
            memIDmap.put(job.get("ACCSECMERNO").toString(),
                    job.get("ACCSECMERNO").toString());
        }
        for (int i = 0; i < jsonArrayA.size(); i++) {
            job = jsonArrayA.getJSONObject(i);
            memIDmap.put(job.get("MEMBER_ID").toString(), job.get("MEMBER_ID")
                    .toString());
        }
        StringBuffer fileBuffer = new StringBuffer();
        // 遍历当天所有的商户
        for (Map.Entry<String, String> entry : memIDmap.entrySet()) {
            String memberId = entry.getKey();
            memberId = "200000000000587";
            fileBuffer.setLength(0);
            fileBuffer.append("MemberId:" + memberId + "|Date:" + dateTime);
            // 消费和充值的汇总信息;账户资金增加
            List<?> countList = txnsLogService.getCountExpenseAndRecharge(
                    memberId, dateTime);
            // 提现,退款,代付账户资金减少
            List<?> countReList = txnsLogService.getCountRefundAndPay(memberId,
                    dateTime);
            // 消费-账户 账户资金增加
            List<?> spendingList = txnsLogService.getCountSpendingAccount(
                    memberId, dateTime);
            memberId = "200000000000593";
            // 手工充值 账户资金增加
            List<?> handPayList = txnsLogService.getCountHandPay(memberId,
                    dateTime);
            // 总交易笔数
            int count = 0;
            // 总清算金额
            Long countClearMoney = 0L;
            // 总手续费
            Long countfree = 0L;
            if (countList != null && countList.size() > 0) {
                JSONArray json = JSONArray.fromObject(countList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                countClearMoney += Long.parseLong(json.getJSONObject(0)
                        .get("CLEARING").equals("null") ? "0" : json
                        .getJSONObject(0).get("CLEARING").toString());
                countfree += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALFEE").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALFEE").toString());
            }
            if (countReList != null && countReList.size() > 0) {
                JSONArray json = JSONArray.fromObject(countReList);
                System.out.println(json.getJSONObject(0));
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                countClearMoney -= Long.parseLong(json.getJSONObject(0)
                        .get("CLEARING").equals("null") ? "0" : json
                        .getJSONObject(0).get("CLEARING").toString());
                countfree += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALFEE").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALFEE").toString());

            }
            if (spendingList != null && spendingList.size() > 0) {
                JSONArray json = JSONArray.fromObject(spendingList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                countClearMoney += Long.parseLong(json.getJSONObject(0)
                        .get("CLEARING").equals("null") ? "0" : json
                        .getJSONObject(0).get("CLEARING").toString());
                countfree += Long.parseLong(json.getJSONObject(0)
                        .get("TOTALFEE").equals("null") ? "0" : json
                        .getJSONObject(0).get("TOTALFEE").toString());

            }
            if (handPayList != null && handPayList.size() > 0) {
                JSONArray json = JSONArray.fromObject(handPayList);
                count += Integer.parseInt(json.getJSONObject(0).get("TOTAL")
                        .equals("null") ? "0" : json.getJSONObject(0)
                        .get("TOTAL").toString());
                countClearMoney += Long.parseLong(json.getJSONObject(0)
                        .get("CLEARING").equals("null") ? "0" : json
                        .getJSONObject(0).get("CLEARING").toString());
                // countfree+=Long.parseLong(json.getJSONObject(0).get("TOTALFEE").equals("null")?"0":json.getJSONObject(0).get("TOTALFEE").toString());

            }
            fileBuffer.append("\n");
            fileBuffer.append("total:" + count + "|countClearMoney:"
                    + countClearMoney + "|countfree:" + countfree);
            memberId = "200000000000532";
            dateTime = "20160105";
            List<?> memberDetailedList = txnsLogService
                    .getAllMemberDetailedByDate(memberId, dateTime);
            JSONArray detailJsonArray = JSONArray
                    .fromObject(memberDetailedList);
            // [{TXNFEE=50, PAYORDNO=2016011200000088, AMOUNT=261,
            // BUSICODE=10000001}, {TXNFEE=50, PAYORDNO=2016011200000090,
            // AMOUNT=245, BUSICODE=10000001}, {TXNFEE=50,
            // PAYORDNO=2016011200000087, AMOUNT=250, BUSICODE=10000001},
            // {TXNFEE=50, PAYORDNO=2016011200000089, AMOUNT=247,
            // BUSICODE=10000001}, {TXNFEE=50, PAYORDNO=2016011200000075,
            // AMOUNT=251, BUSICODE=10000001}, {TXNFEE=50,
            // PAYORDNO=2016011200000086, AMOUNT=251, BUSICODE=10000001},
            // {TXNFEE=50, PAYORDNO=2016011200000083, AMOUNT=251,
            // BUSICODE=10000001}, {TXNFEE=50, PAYORDNO=2016011200000079,
            // AMOUNT=2501, BUSICODE=10000001}, {TXNFEE=50,
            // PAYORDNO=2016011200000084, AMOUNT=251, BUSICODE=10000001},
            // {TXNFEE=50, PAYORDNO=2016011200000085, AMOUNT=251,
            // BUSICODE=10000001}, {TXNFEE=50, PAYORDNO=2016011200000082,
            // AMOUNT=251, BUSICODE=10000001}]
            for (int i = 0; i < detailJsonArray.size(); i++) {
                job = detailJsonArray.getJSONObject(i);
                fileBuffer.append("\n");
                fileBuffer.append(job.get("ACCORDNO").toString() + "|"
                        + job.get("BUSICODE").toString() + "|"
                        + job.get("ACCORDCOMMITIME").toString() + "|"
                        + job.get("TXNSEQNO").toString() + "|"
                        + job.get("AMOUNT").toString() + "|"
                        + job.get("TXNFEE").toString() + "|"
                        + job.get("ACCSETTLEDATE").toString() + "|");
            }
            fileBuffer.append("\n");
            fileBuffer.append("######");
            doPrint(fileBuffer.toString(), "D:\\AAA\\" + memberId + "\\",
                    memberId + "_" + dateTime + ".txt", memberId);
        }
    }

    public void doPrint(String fileString,
            String path,
            String fileName,
            String memberId) {
        string2File(fileString, path + fileName);
        File file = new File(path + fileName);
        // String uploadPath, String fileName, File file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/spring/*");
        FTPClientFactory ftpClientFactory = context
                .getBean(FTPClientFactory.class);
        AbstractFTPClient ftpClient = ftpClientFactory.getFtpClient();
        try {
            ftpClient.upload("/memberform/" + memberId, fileName, file);
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
