/* 
 * TxnsWinhdraw.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zlebank.zplatform.acc.pojo.Money;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午9:24:57
 * @since 
 */
@Entity
@Table(name = "T_TXNS_WITHDRAW")
public class TxnsWithdrawModel {
    /**标示**/
    private Long id;

    /**提现订单号 (受理订单号)**/
    private String withdraworderno;
    /**交易订单号**/
    private String txnseqNo;
    /**批次号**/
    private String batchno;
    /**会员号**/
    private String memberid;
    /**提现类型0:个人1:商户**/
    private String withdrawtype;
    /**提现金额**/
    private Money amount;
    /**银行账号**/
    private String acctno;
    /**银行账户名称**/
    private String acctname;
    /**银行代码**/
    private String bankcode;
    /**支行名称**/
    private String bankname;
    /**提现手续费**/
    private Money fee;
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
    private Date intime;
    /**初审人**/
    private Long stexauser;
    /**初审时间**/
    private Date stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复审人**/
    private Long cvlexauser;
    /**复审时间**/
    private Date cvlexatime;
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
    
    
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "WITHDRAWORDERNO")
    public String getWithdraworderno() {
        return withdraworderno;
    }
    public void setWithdraworderno(String withdraworderno) {
        this.withdraworderno = withdraworderno;
    }
    @Column(name = "BATCHNO")
    public String getBatchno() {
        return batchno;
    }
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
    @Column(name = "MEMBERID")
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    @Column(name = "WITHDRAWTYPE")
    public String getWithdrawtype() {
        return withdrawtype;
    }
    public void setWithdrawtype(String withdrawtype) {
        this.withdrawtype = withdrawtype;
    }
    @Column(name = "AMOUNT")
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="AMOUNT"))})
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    @Column(name = "ACCTNO")
    public String getAcctno() {
        return acctno;
    }
    public void setAcctno(String acctno) {
        this.acctno = acctno;
    }
    @Column(name = "ACCTNAME")
    public String getAcctname() {
        return acctname;
    }
    public void setAcctname(String acctname) {
        this.acctname = acctname;
    }
    @Column(name = "BANKCODE")
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    @Column(name = "BANKNAME")
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    
    @Column(name = "FEE")
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="FEE"))})
    public Money getFee() {
        return fee;
    }
    public void setFee(Money fee) {
        this.fee = fee;
    }
    @Column(name = "TXNTIME")
    public String getTxntime() {
        return txntime;
    }
    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }
    @Column(name = "FINISHTIME")
    public String getFinishtime() {
        return finishtime;
    }
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "WITHDRAWINSTID")
    public String getWithdrawinstid() {
        return withdrawinstid;
    }
    public void setWithdrawinstid(String withdrawinstid) {
        this.withdrawinstid = withdrawinstid;
    }
    @Column(name = "RETCODE")
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    @Column(name = "RETINFO")
    public String getRetinfo() {
        return retinfo;
    }
    public void setRetinfo(String retinfo) {
        this.retinfo = retinfo;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "STEXAUSER")
    public Long getStexauser() {
        return stexauser;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
    @Column(name = "STEXATIME")
    public Date getStexatime() {
        return stexatime;
    }
    public void setStexatime(Date stexatime) {
        this.stexatime = stexatime;
    }
    @Column(name = "STEXAOPT")
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    @Column(name = "CVLEXAUSER")
    public Long getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    @Column(name = "CVLEXATIME")
    public Date getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(Date cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    @Column(name = "CVLEXAOPT")
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "GATEWAYORDERNO")
    public String getGatewayorderno() {
        return gatewayorderno;
    }
    public void setGatewayorderno(String gatewayorderno) {
        this.gatewayorderno = gatewayorderno;
    }
    
    
    @Column(name = "TOTALBANKCODE")
    public String getTotalBankCode() {
        return totalBankCode;
    }
    public void setTotalBankCode(String totalBankCode) {
        this.totalBankCode = totalBankCode;
    }
    @Column(name = "TXNSEQNO")
    public String getTxnseqNo() {
        return txnseqNo;
    }
    public void setTxnseqNo(String txnseqNo) {
        this.txnseqNo = txnseqNo;
    }

    
    

}
