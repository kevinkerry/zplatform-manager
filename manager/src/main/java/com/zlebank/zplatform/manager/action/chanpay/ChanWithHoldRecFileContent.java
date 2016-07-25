package com.zlebank.zplatform.manager.action.chanpay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zlebank.zplatform.manager.action.upload.AbstractFileContentHandler;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.exception.ResolveReconFileContentException;
import com.zlebank.zplatform.trade.bean.enums.ChannelEnmu;
import com.zlebank.zplatform.trade.chanpay.bean.cj.G40003Bean;
/**
 * 
 *畅捷代扣对账文件解析
 *
 * @author liu_sm
 * @version
 * @date 2016年7月1日 
 * @since
 */
public class ChanWithHoldRecFileContent extends AbstractFileContentHandler {

    private List<BnkTxnModel> bnkTxnList;
    private Log log = LogFactory.getLog(ChanWithHoldRecFileContent.class);
    
    /***
     * 商户编号｜对账文件时间｜明细条数｜总手续费｜总金额
            5ee26ddb404a590|201506|185|51700|163239600|
        序号(0)|批次编号(1)｜明细编号(2)｜发起账号(3)|发起账户名(4)｜对方账户(5)
        1|5ee26ddb404a59020150623114744171|990000804|201407301524|畅捷通信息技术股份有限公司|201407301524
        ｜对方账户名(6)｜交易类型(7)｜交易代码(8)｜交易结算时间(9)｜币种(10)
        |北京成果文化发展有限公司|0|100002|20150623114800|CNY
        |金额(11)｜手续费(12)｜交易状态(13)｜状态描述(14)｜交易提交时间(15)
        |30000000|600|0000|处理成功|20150623114756
        ｜备注(16)｜用途(17)｜外部流水号(18)
        |备注|用途|外部企业流水号|
     * @throws ResolveReconFileContentException 
     */
    
    public List<BnkTxnModel> readFile(File[] upload,
            String instiid,
            String[] uploadFileName) throws ResolveReconFileContentException  {
        FileInputStream fis= null;
        InputStreamReader isr= null;
         BufferedReader reader=null;
        try {
             log.info("解析对账文件开始");
            bnkTxnList=new ArrayList<BnkTxnModel>();
            fis = new FileInputStream(upload[0]);
            isr = new InputStreamReader(fis,"UTF8"); //指定以编码读入
            reader = new BufferedReader(isr);//2 成功则表明能读取到文件
            String tempString = null;
            int line = 0;
              String acqsettledate = "";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null&&!tempString.trim().equals("")) {
                String[] temps = tempString.split("\\|",-1);
                log.info("对账内容"+tempString);
                if(line==0){
                //第一行为汇总信息，不处理  
                }else{
                   //校验是否符合要求；
                    if(temps.length!=21){
                         ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
                         rrfce.setParams("解析对账文件错误:文件每行长度有错"+temps.length);
                         throw rrfce;
                    }else{
                          //只读对账成功
                         if(temps[13].equals(G40003Bean.STATUS_SUCESS)){
                             BnkTxnModel bnk = new BnkTxnModel();
                             //本方支付订单号
                             bnk.setPayordno(temps[1]);
                             //系统参考号（或为：对方订单号）
                             bnk.setSystrcno("");
                             //交易流水号
                              bnk.setPaytrcno("");
                              bnk.setBusicode("");
                             //交易时间
                             bnk.setTxndatetime(temps[15]);
                             //银行卡号
                             bnk.setPan("");
                            //金额
                             bnk.setAmount(null==temps[11]?0L:Long.valueOf(temps[11].toString()));
                             //交易类型 0-代款 1-收款
                             String txtType = temps[8];
                             if(txtType.equals("0")){
                               //银行贷款手续费
                               bnk.setDfee(Long.parseLong(temps[12].toString()) );
                             }else{
                               //银行借款手续费
                                 bnk.setCfee(Long.parseLong(temps[12].toString()));
                             }
                             //中心应答码
                             bnk.setRetcode("00");
                             //受理清算日期
                             bnk.setAcqsettledate(acqsettledate.replace("-", ""));
                             //状态
                             //bnk.setStatus(temps[13]);
                             //对账结果
                            // bnk.setResult(temps[14]);
                             //机构标识
                             bnk.setInstiid(ChannelEnmu.CHANPAYCOLLECTMONEY.getChnlcode());
                             //备注
                             bnk.setNotes(temps[16]);
                             bnkTxnList.add(bnk);
                         }
                        
                    }
                }
                line++;
            }
            log.info("解析对账文件结束");
        } catch (Exception e) {
            log.error("Resolve file is error:" +e.getMessage());
            ResolveReconFileContentException rrfce = new ResolveReconFileContentException();
            rrfce.setParams("解析文件发生错误:"+e.getMessage());
            rrfce.initCause(e);
            throw rrfce;
        }finally{
                try {
                    if(fis!=null){
                    fis.close();
                    }
                    if(isr!=null){
                        isr.close();
                    }
                    if(reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            
        }
        return bnkTxnList;
        
    }
  
}