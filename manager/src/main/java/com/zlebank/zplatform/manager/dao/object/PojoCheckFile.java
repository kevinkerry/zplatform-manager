/* 
 * CheckFilePojo.java  
 * 
 * version TODO
 *
 * 2015年12月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月25日 下午4:41:37
 * @since 
 */
@Entity
@Table(name="T_CHECKFILE_MISTAKE")
public class PojoCheckFile implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /****/
    private Long iid;
    /**差错记录标志位**/
    private Long tid;
    /**交易序列号**/
    private PojoTxnsLog txnseqno;
    /**机构标识**/
    private String instiid;
    /**支付订单号（本方请求的订单号）**/
    private String payordno;
    /**交易时间**/
    private String txndatetime;
    /**业务标识**/
    private PojoBusiness busicode;
    /**金额**/
    private Money amount;
    /**银行卡号**/
    private String pan;
    /**商户编号**/
    //private PojoMerchDeta merchno;
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
    private Date dealEndDate;
    /**处理操作员id**/
    private Long dealOperatorId;
    /**处理日期**/
    private Date dealDate;
    /**处理结果**/
    private Long dealResult;
    /**处理意见**/
    private String dealOpinion;
    /**反馈操作员id**/
    private Long visitOperatorId;
    /**反馈日期**/
    private Date visitDate;
    /**反馈结果**/
    private Long visitResult;
    /**反馈意见**/
    private String visitOpinion;
    /**备注1**/
    private String notes;
    /**备注2**/
    private String remarks;
    @Column(name = "IID")
    @Id
    public Long getIid() {
        return iid;
    }
    public void setIid(Long iid) {
        this.iid = iid;
    }
    @Column(name = "TID")
    @Id
    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    @OneToOne(optional=false,fetch = FetchType.LAZY)
  //  @Column(name = "TXNSEQNO")
    @JoinColumn(name = "TXNSEQNO")
    public PojoTxnsLog getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(PojoTxnsLog txnseqno) {
        this.txnseqno = txnseqno;
    }
    @Column(name = "INSTIID")
    public String getInstiid() {
        return instiid;
    }
    public void setInstiid(String instiid) {
        this.instiid = instiid;
    }
    @Column(name = "PAYORDNO")
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    @Column(name = "TXNDATETIME")
    public String getTxndatetime() {
        return txndatetime;
    }
    public void setTxndatetime(String txndatetime) {
        this.txndatetime = txndatetime;
    }
    @JoinColumn(name = "BUSICODE")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    public PojoBusiness getBusicode() {
        return busicode;
    }
    public void setBusicode(PojoBusiness busicode) {
        this.busicode = busicode;
    }
    @Column(name = "AMOUNT")
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="AMOUNT"))})
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    @Column(name = "PAN")
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    /*@JoinColumn(name = "MERCHNO",insertable = false, updatable = false,referencedColumnName="MEMBER_ID")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    public PojoMerchDeta getMerchno() {
        return merchno;
    }
    public void setMerchno(PojoMerchDeta merchno) {
        this.merchno = merchno;
    }*/
    @Column(name = "SYSTRCNO")
    public String getSystrcno() {
        return systrcno;
    }
    public void setSystrcno(String systrcno) {
        this.systrcno = systrcno;
    }
    @Column(name = "PAYTRCNO")
    public String getPaytrcno() {
        return paytrcno;
    }
    public void setPaytrcno(String paytrcno) {
        this.paytrcno = paytrcno;
    }
    @Column(name = "CFEE")
    public Long getCfee() {
        return cfee;
    }
    public void setCfee(Long cfee) {
        this.cfee = cfee;
    }
    @Column(name = "DFEE")
    public Long getDfee() {
        return dfee;
    }
    public void setDfee(Long dfee) {
        this.dfee = dfee;
    }
    @Column(name = "RETCODE")
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    @Column(name = "ACQSETTLEDATE")
    public String getAcqsettledate() {
        return acqsettledate;
    }
    public void setAcqsettledate(String acqsettledate) {
        this.acqsettledate = acqsettledate;
    }
    @Column(name = "PROID")
    public Long getProid() {
        return proid;
    }
    public void setProid(Long proid) {
        this.proid = proid;
    }
    @Column(name = "RESULT")
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    @Column(name = "MISTATUS")
    public String getMistatus() {
        return mistatus;
    }
    public void setMistatus(String mistatus) {
        this.mistatus = mistatus;
    }
    @Column(name = "MISTAKE_TRADE_SOURCE")
    public Long getMistakeTradeSource() {
        return mistakeTradeSource;
    }
    public void setMistakeTradeSource(Long mistakeTradeSource) {
        this.mistakeTradeSource = mistakeTradeSource;
    }
    @Column(name = "DATASOURCE")
    public String getDatasource() {
        return datasource;
    }
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }
    @Column(name = "MISTAKEDESC")
    public String getMistakedesc() {
        return mistakedesc;
    }
    public void setMistakedesc(String mistakedesc) {
        this.mistakedesc = mistakedesc;
    }
    @Column(name = "ACCEPTER_ID")
    public Long getAccepterId() {
        return accepterId;
    }
    public void setAccepterId(Long accepterId) {
        this.accepterId = accepterId;
    }
    @Column(name = "ACCEPT_DATE")
    public Date getAcceptDate() {
        return acceptDate;
    }
    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }
    @Column(name = "ACCEPT_RESULT")
    public Long getAcceptResult() {
        return acceptResult;
    }
    public void setAcceptResult(Long acceptResult) {
        this.acceptResult = acceptResult;
    }
    @Column(name = "ACCEPT_OPINION")
    public String getAcceptOpinion() {
        return acceptOpinion;
    }
    public void setAcceptOpinion(String acceptOpinion) {
        this.acceptOpinion = acceptOpinion;
    }
    @Column(name = "DEAL_END_DATE")
    public Date getDealEndDate() {
        return dealEndDate;
    }
    public void setDealEndDate(Date dealEndDate) {
        this.dealEndDate = dealEndDate;
    }
    @Column(name = "DEAL_OPERATOR_ID")
    public Long getDealOperatorId() {
        return dealOperatorId;
    }
    public void setDealOperatorId(Long dealOperatorId) {
        this.dealOperatorId = dealOperatorId;
    }
    @Column(name = "DEAL_DATE")
    public Date getDealDate() {
        return dealDate;
    }
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
    @Column(name = "DEAL_RESULT")
    public Long getDealResult() {
        return dealResult;
    }
    public void setDealResult(Long dealResult) {
        this.dealResult = dealResult;
    }
    @Column(name = "DEAL_OPINION")
    public String getDealOpinion() {
        return dealOpinion;
    }
    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }
    @Column(name = "VISIT_OPERATOR_ID")
    public Long getVisitOperatorId() {
        return visitOperatorId;
    }
    public void setVisitOperatorId(Long visitOperatorId) {
        this.visitOperatorId = visitOperatorId;
    }
    @Column(name = "VISIT_DATE")
    public Date getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    @Column(name = "VISIT_RESULT")
    public Long getVisitResult() {
        return visitResult;
    }
    public void setVisitResult(Long visitResult) {
        this.visitResult = visitResult;
    }
    @Column(name = "VISIT_OPINION")
    public String getVisitOpinion() {
        return visitOpinion;
    }
    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
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


}
