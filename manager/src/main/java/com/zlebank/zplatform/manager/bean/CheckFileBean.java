/* 
 * CheckFileBean.java  
 * 
 * version TODO
 *
 * 2015年12月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import java.util.Date;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月25日 下午4:51:15
 * @since 
 */
public class CheckFileBean implements Bean{
    
    /****/
    private Long iid;
    /**差错记录标志位**/
    private Long tid;
    /**交易序列号**/
    private String txnseqno;
    /**机构标识**/
    private String instiid;
    /**支付订单号（本方请求的订单号）**/
    private String payordno;
    /**交易时间**/
    private String txndatetime;
    /**业务标识**/
    private String busicode;
    /**业务名称**/
    private String busName;
    /**金额**/
    private String amount;
    /**银行卡号**/
    private String pan;
    /**商户编号**/
    private String merchno;
    /**商户名**/
    private String merchName;
    /**系统参考号（或为：对方订单号）**/
    private String systrcno;
    /**支付交易流水号**/
    private String paytrcno;
    /**银行贷记手续费**/
    private Long cfee;
    /**银行借记手续费**/
    private Long dfee;
    /**中心应答码**/
    private String retcode;
    /**受理清算日期**/
    private String acqsettledate;
    /**清算日志标识**/
    private Long proid;
    /**对账结果**/
    private String result;
    /**差错状态00-待受理09-拒绝受理**/
    private String mistatus;
    /**差错来源0对账**/
    private Long mistakeTradeSource;
    /**差错数据来源0-本系统1-非本系统对账文件**/
    private String datasource;
    /**差错原因描述**/
    private String mistakedesc;
    /**受理操作员id**/
    private Long accepterId;
    /**受理日期**/
    private Date acceptDate;
    /**受理结果**/
    private Long acceptResult;
    /**受理意见**/
    private String acceptOpinion;
    /**处理截止日期**/
    private String dealEndDate;
    /**处理操作员id**/
    private Long dealOperatorId;
    /**处理日期**/
    private String dealDate;
    /**处理结果**/
    private Long dealResult;
    /**处理意见**/
    private String dealOpinion;
    /**反馈操作员id**/
    private Long visitOperatorId;
    /**反馈日期**/
    private String visitDate;
    /**反馈结果**/
    private Long visitResult;
    /**反馈意见**/
    private String visitOpinion;
    /**备注1**/
    private String notes;
    /**备注2**/
    private String remarks;
    public Long getIid() {
        return iid;
    }
    public void setIid(Long iid) {
        this.iid = iid;
    }
    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public String getInstiid() {
        return instiid;
    }
    public void setInstiid(String instiid) {
        this.instiid = instiid;
    }
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    public String getTxndatetime() {
        return txndatetime;
    }
    public void setTxndatetime(String txndatetime) {
        this.txndatetime = txndatetime;
    }
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
   
 
    public String getSystrcno() {
        return systrcno;
    }
    public void setSystrcno(String systrcno) {
        this.systrcno = systrcno;
    }
    public String getPaytrcno() {
        return paytrcno;
    }
    public void setPaytrcno(String paytrcno) {
        this.paytrcno = paytrcno;
    }
    public Long getCfee() {
        return cfee;
    }
    public void setCfee(Long cfee) {
        this.cfee = cfee;
    }
    public Long getDfee() {
        return dfee;
    }
    public void setDfee(Long dfee) {
        this.dfee = dfee;
    }
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    public String getAcqsettledate() {
        return acqsettledate;
    }
    public void setAcqsettledate(String acqsettledate) {
        this.acqsettledate = acqsettledate;
    }
    public Long getProid() {
        return proid;
    }
    public void setProid(Long proid) {
        this.proid = proid;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMistatus() {
        return mistatus;
    }
    public void setMistatus(String mistatus) {
        this.mistatus = mistatus;
    }
    public Long getMistakeTradeSource() {
        return mistakeTradeSource;
    }
    public void setMistakeTradeSource(Long mistakeTradeSource) {
        this.mistakeTradeSource = mistakeTradeSource;
    }
    public String getDatasource() {
        return datasource;
    }
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
    public String getMistakedesc() {
        return mistakedesc;
    }
    public void setMistakedesc(String mistakedesc) {
        this.mistakedesc = mistakedesc;
    }
    public Long getAccepterId() {
        return accepterId;
    }
    public void setAccepterId(Long accepterId) {
        this.accepterId = accepterId;
    }
    public Date getAcceptDate() {
        return acceptDate;
    }
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }
    public Long getAcceptResult() {
        return acceptResult;
    }
    public void setAcceptResult(Long acceptResult) {
        this.acceptResult = acceptResult;
    }
    public String getAcceptOpinion() {
        return acceptOpinion;
    }
    public void setAcceptOpinion(String acceptOpinion) {
        this.acceptOpinion = acceptOpinion;
    }

    public Long getDealOperatorId() {
        return dealOperatorId;
    }
    public void setDealOperatorId(Long dealOperatorId) {
        this.dealOperatorId = dealOperatorId;
    }
    public String getDealDate() {
        return dealDate;
    }
    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }
    public Long getDealResult() {
        return dealResult;
    }
    public void setDealResult(Long dealResult) {
        this.dealResult = dealResult;
    }
    public String getDealOpinion() {
        return dealOpinion;
    }
    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }
    public Long getVisitOperatorId() {
        return visitOperatorId;
    }
    public void setVisitOperatorId(Long visitOperatorId) {
        this.visitOperatorId = visitOperatorId;
    }
    public String getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
    public Long getVisitResult() {
        return visitResult;
    }
    public void setVisitResult(Long visitResult) {
        this.visitResult = visitResult;
    }
    public String getVisitOpinion() {
        return visitOpinion;
    }
    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getMerchno() {
        return merchno;
    }
    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }
    public String getMerchName() {
        return merchName;
    }
    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }
    public String getDealEndDate() {
        return dealEndDate;
    }
    public void setDealEndDate(String dealEndDate) {
        this.dealEndDate = dealEndDate;
    }
    public String getBusName() {
        return busName;
    }
    public void setBusName(String busName) {
        this.busName = busName;
    }
    
    
    
    
}
