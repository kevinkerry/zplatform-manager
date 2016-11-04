/* 
 * AccEntryBean.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 上午10:55:55
 * @since 
 */
public class AccEntryBean {
    /**凭证号**/
    private Long voucherCode;
    /**账号/科目号**/
    private String acctCode;
    /**借贷标志1-借2-贷**/
    private String  crdr;
    /**金额(元)**/
    private String amount;
    /**1:本金,2手续费,3其他**/
    private String amtOrFee;
    /**支付订单号**/
    private String payordno;
    /**交易流水号**/
    private String txnseqno;
    /**状态:00-已记账，01-未记账**/
    private String status;
    /**分录时间**/
    private String inTime;
    /**交易类型代码**/
    private String busiCode;
    /**会员号**/
    private String memberid;
    /**账户名**/
    private String acctCodeName;
    private String usage;
    
    
    
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getAcctCodeName() {
        return acctCodeName;
    }
    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    public Long getVoucherCode() {
        return voucherCode;
    }
    public void setVoucherCode(Long voucherCode) {
        this.voucherCode = voucherCode;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getCrdr() {
        return crdr;
    }
    public void setCrdr(String crdr) {
        this.crdr = crdr;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAmtOrFee() {
        return amtOrFee;
    }
    public void setAmtOrFee(String amtOrFee) {
        this.amtOrFee = amtOrFee;
    }
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getInTime() {
        return inTime;
    }
    public void setInTime(String inTime) {
        this.inTime = inTime;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    
  

}
