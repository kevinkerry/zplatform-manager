/* 
 * TxnsWinhdrawBean.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;


import com.zlebank.zplatform.commons.bean.Bean;

/**
 * 提现订单查询bean
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午9:59:39
 * @since 
 */
public class TxnsWithdrawBean implements Bean{
    /**标示**/
    private Long id;
    /**提现订单号**/
    private String withdraworderno;
    /**批次号**/
    private String batchno;
    /**会员号**/
    private String memberid;
    /**提现类型0:个人1:商户**/
    private String withdrawtype;
    /**提现金额**/
    private String amount;
    /**银行账号**/
    private String acctno;
    /**银行账户名称**/
    private String acctname;
    /**银行代码**/
    private String bankcode;
    /**支行名称**/
    private String bankname;
    /**提现手续费**/
    private String fee;
    /**交易时间**/
    private String txntime;
    /**完成时间**/
    private String finishtime;
    /**状态**/
    private String status;
    /**提现渠道**/
    private String withdrawinstid;
    /**应答码**/
    private String retcode;
    /**应答信息**/
    private String retinfo;
    /**写入人**/
    private Long inuser;
    /**写入时间**/
    private String intime;
    /**初审人**/
    private Long stexauser;
    /**初审时间**/
    private String stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复审人**/
    private Long cvlexauser;
    /**复审时间**/
    private String cvlexatime;
    /**复审意见**/
    private String cvlexaopt;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**网关订单号**/
    private String gatewayorderno;
    
    /**银行主行号**/
    private String  totalBankCode;
    
    /*
     * @param merchId  一级商户号
     * @param subMerchId    二级商户号
     * @param memberId 会员id
     * @param busiCode 业务code
     * @param txnAmt 金额
     * @param cardType 卡类型
     * @param cardNo 卡号
     * @throws TradeException
     */
    
    private String merchId;
        
    private String subMerchId;
 
    private String passWord;
    
    private String cardType;
    
    /**交易流水号**/
    private String txnseqNo;
    
  
    public String getTxnseqNo() {
        return txnseqNo;
    }
    public void setTxnseqNo(String txnseqNo) {
        this.txnseqNo = txnseqNo;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getPassWord() {
        return passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getMerchId() {
        return merchId;
    }
    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }
    public String getSubMerchId() {
        return subMerchId;
    }
    public void setSubMerchId(String subMerchId) {
        this.subMerchId = subMerchId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWithdraworderno() {
        return withdraworderno;
    }
    public void setWithdraworderno(String withdraworderno) {
        this.withdraworderno = withdraworderno;
    }
    public String getBatchno() {
        return batchno;
    }
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    public String getWithdrawtype() {
        return withdrawtype;
    }
    public void setWithdrawtype(String withdrawtype) {
        this.withdrawtype = withdrawtype;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAcctno() {
        return acctno;
    }
    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }
    public String getAcctname() {
        return acctname;
    }
    public void setAcctname(String acctname) {
        this.acctname = acctname;
    }
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getFee() {
        return fee;
    }
    public void setFee(String fee) {
        this.fee = fee;
    }
    public String getTxntime() {
        return txntime;
    }
    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }
    public String getFinishtime() {
        return finishtime;
    }
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getWithdrawinstid() {
        return withdrawinstid;
    }
    public void setWithdrawinstid(String withdrawinstid) {
        this.withdrawinstid = withdrawinstid;
    }
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    public String getRetinfo() {
        return retinfo;
    }
    public void setRetinfo(String retinfo) {
        this.retinfo = retinfo;
    }
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    public String getIntime() {
        return intime;
    }
    public void setIntime(String intime) {
        this.intime = intime;
    }
    public Long getStexauser() {
        return stexauser;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
    public String getStexatime() {
        return stexatime;
    }
    public void setStexatime(String stexatime) {
        this.stexatime = stexatime;
    }
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    public Long getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    public String getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(String cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
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
    public String getGatewayorderno() {
        return gatewayorderno;
    }
    public void setGatewayorderno(String gatewayorderno) {
        this.gatewayorderno = gatewayorderno;
    }
    public String getTotalBankCode() {
        return totalBankCode;
    }
    public void setTotalBankCode(String totalBankCode) {
        this.totalBankCode = totalBankCode;
    }
    
    
    
    
}
