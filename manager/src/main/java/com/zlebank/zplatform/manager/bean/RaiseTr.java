package com.zlebank.zplatform.manager.bean;

import java.util.Date;

import com.zlebank.zplatform.commons.bean.Bean;

public class RaiseTr implements Bean{
	
	private long tid;
	private String memberid;
	private String financingid;
	private String productcode;
	private String orderid;
	private String tn;
	private String status;
	private Long inUser;
	private Date inTime;
	private Long stexaUser;
	private Date stexaTime;
	private String stexaOpt;
	private Long cvlexaUser;
	private Date cvlexaTime;
	private String cvlexaOpt;
	private String notes;
	private String remarks;
	private String txnseqno;
	private String retinfo;
	private String retCode;
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getFinancingid() {
		return financingid;
	}
	public void setFinancingid(String financingid) {
		this.financingid = financingid;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInUser() {
		return inUser;
	}
	public void setInUser(Long inUser) {
		this.inUser = inUser;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Long getStexaUser() {
		return stexaUser;
	}
	public void setStexaUser(Long stexaUser) {
		this.stexaUser = stexaUser;
	}
	public Date getStexaTime() {
		return stexaTime;
	}
	public void setStexaTime(Date stexaTime) {
		this.stexaTime = stexaTime;
	}
	public String getStexaOpt() {
		return stexaOpt;
	}
	public void setStexaOpt(String stexaOpt) {
		this.stexaOpt = stexaOpt;
	}
	public Long getCvlexaUser() {
		return cvlexaUser;
	}
	public void setCvlexaUser(Long cvlexaUser) {
		this.cvlexaUser = cvlexaUser;
	}
	public Date getCvlexaTime() {
		return cvlexaTime;
	}
	public void setCvlexaTime(Date cvlexaTime) {
		this.cvlexaTime = cvlexaTime;
	}
	public String getCvlexaOpt() {
		return cvlexaOpt;
	}
	public void setCvlexaOpt(String cvlexaOpt) {
		this.cvlexaOpt = cvlexaOpt;
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
	public RaiseTr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RaiseTr(long tid, String memberid, String financingid, String productcode, String orderid, String tn,
			String status, Long inUser, Date inTime, Long stexaUser, Date stexaTime, String stexaOpt, Long cvlexaUser,
			Date cvlexaTime, String cvlexaOpt, String notes, String remarks, String txnseqno, String retinfo,
			String retCode) {
		super();
		this.tid = tid;
		this.memberid = memberid;
		this.financingid = financingid;
		this.productcode = productcode;
		this.orderid = orderid;
		this.tn = tn;
		this.status = status;
		this.inUser = inUser;
		this.inTime = inTime;
		this.stexaUser = stexaUser;
		this.stexaTime = stexaTime;
		this.stexaOpt = stexaOpt;
		this.cvlexaUser = cvlexaUser;
		this.cvlexaTime = cvlexaTime;
		this.cvlexaOpt = cvlexaOpt;
		this.notes = notes;
		this.remarks = remarks;
		this.txnseqno = txnseqno;
		this.retinfo = retinfo;
		this.retCode = retCode;
	}
	@Override
	public String toString() {
		return "RaiseTr [tid=" + tid + ", memberid=" + memberid + ", financingid=" + financingid + ", productcode="
				+ productcode + ", orderid=" + orderid + ", tn=" + tn + ", status=" + status + ", inUser=" + inUser
				+ ", inTime=" + inTime + ", stexaUser=" + stexaUser + ", stexaTime=" + stexaTime + ", stexaOpt="
				+ stexaOpt + ", cvlexaUser=" + cvlexaUser + ", cvlexaTime=" + cvlexaTime + ", cvlexaOpt=" + cvlexaOpt
				+ ", notes=" + notes + ", remarks=" + remarks + ", txnseqno=" + txnseqno + ", retinfo=" + retinfo
				+ ", retCode=" + retCode + "]";
	}
	
	
}
