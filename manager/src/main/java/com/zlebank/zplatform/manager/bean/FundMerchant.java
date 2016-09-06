package com.zlebank.zplatform.manager.bean;
import java.math.BigDecimal;
import java.util.Date;

import com.zlebank.zplatform.commons.bean.Bean;

public class FundMerchant implements Bean{

	private BigDecimal tid;
	private String batchno;
	private String merId;
	private String investors;
	private String orderId;
	private BigDecimal txnamt;
	private BigDecimal interset;
	private String porductCode;
	private String status;
	private Long inuser;
	private Date intime;
	private Long stexauser;
	private Date stexatime;
	private String stexaopt;
	private Long cvlexauser;
	private Date cvlexatime;
	private String cvlexaopt;
	private String remarks;
	private String txnseqno;
	private String retinfo;
	private String retCode;
	public BigDecimal getTid() {
		return tid;
	}
	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getInvestors() {
		return investors;
	}
	public void setInvestors(String investors) {
		this.investors = investors;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getTxnamt() {
		return txnamt;
	}
	public void setTxnamt(BigDecimal txnamt) {
		this.txnamt = txnamt;
	}
	public BigDecimal getInterset() {
		return interset;
	}
	public void setInterset(BigDecimal interset) {
		this.interset = interset;
	}
	public String getPorductCode() {
		return porductCode;
	}
	public void setPorductCode(String porductCode) {
		this.porductCode = porductCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInuser() {
		return inuser;
	}
	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public Long getStexauser() {
		return stexauser;
	}
	public void setStexauser(Long stexauser) {
		this.stexauser = stexauser;
	}
	public Date getStexatime() {
		return stexatime;
	}
	public void setStexatime(Date stexatime) {
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
	public Date getCvlexatime() {
		return cvlexatime;
	}
	public void setCvlexatime(Date cvlexatime) {
		this.cvlexatime = cvlexatime;
	}
	public String getCvlexaopt() {
		return cvlexaopt;
	}
	public void setCvlexaopt(String cvlexaopt) {
		this.cvlexaopt = cvlexaopt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTxnseqno() {
		return txnseqno;
	}
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}
	public String getRetinfo() {
		return retinfo;
	}
	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public FundMerchant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundMerchant(BigDecimal tid, String batchno, String merId, String investors, String orderId,
			BigDecimal txnamt, BigDecimal interset, String porductCode, String status, Long inuser, Date intime,
			Long stexauser, Date stexatime, String stexaopt, Long cvlexauser, Date cvlexatime, String cvlexaopt,
			String remarks, String txnseqno, String retinfo, String retCode) {
		super();
		this.tid = tid;
		this.batchno = batchno;
		this.merId = merId;
		this.investors = investors;
		this.orderId = orderId;
		this.txnamt = txnamt;
		this.interset = interset;
		this.porductCode = porductCode;
		this.status = status;
		this.inuser = inuser;
		this.intime = intime;
		this.stexauser = stexauser;
		this.stexatime = stexatime;
		this.stexaopt = stexaopt;
		this.cvlexauser = cvlexauser;
		this.cvlexatime = cvlexatime;
		this.cvlexaopt = cvlexaopt;
		this.remarks = remarks;
		this.txnseqno = txnseqno;
		this.retinfo = retinfo;
		this.retCode = retCode;
	}
	@Override
	public String toString() {
		return "FundMerchant [tid=" + tid + ", batchno=" + batchno + ", merId=" + merId + ", investors=" + investors
				+ ", orderId=" + orderId + ", txnamt=" + txnamt + ", interset=" + interset + ", porductCode="
				+ porductCode + ", status=" + status + ", inuser=" + inuser + ", intime=" + intime + ", stexauser="
				+ stexauser + ", stexatime=" + stexatime + ", stexaopt=" + stexaopt + ", cvlexauser=" + cvlexauser
				+ ", cvlexatime=" + cvlexatime + ", cvlexaopt=" + cvlexaopt + ", remarks=" + remarks + ", txnseqno="
				+ txnseqno + ", retinfo=" + retinfo + ", retCode=" + retCode + "]";
	}
	
	

	
	
	
	
}
