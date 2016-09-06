package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_raisemoney_apply")
public class RaiseTrModel implements java.io.Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1460094894659641055L;
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
	
	public RaiseTrModel() {
	}

	public RaiseTrModel(long tid) {
		this.tid = tid;
	}

	public RaiseTrModel(long tid, String memberid, String financingid,
			String productcode, String orderid, String tn, String status,
			Long inUser, Date inTime, Long stexaUser, Date stexaTime,
			String stexaOpt, Long cvlexaUser, Date cvlexaTime,
			String cvlexaOpt, String notes, String remarks) {
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
	}
    
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 12, scale = 0)
	public long getTid() {
		return this.tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	@Column(name = "MEMBERID", length = 15)
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(name = "FINANCINGID", length = 15)
	public String getFinancingid() {
		return this.financingid;
	}

	public void setFinancingid(String financingid) {
		this.financingid = financingid;
	}

	@Column(name = "PRODUCTCODE", length = 16)
	public String getProductcode() {
		return this.productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	@Column(name = "ORDERID", length = 64)
	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Column(name = "TN", length = 18)
	public String getTn() {
		return this.tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IN_USER", precision = 10, scale = 0)
	public Long getInUser() {
		return this.inUser;
	}

	public void setInUser(Long inUser) {
		this.inUser = inUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "IN_TIME", length = 7)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "STEXA_USER", precision = 10, scale = 0)
	public Long getStexaUser() {
		return this.stexaUser;
	}

	public void setStexaUser(Long stexaUser) {
		this.stexaUser = stexaUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STEXA_TIME", length = 7)
	public Date getStexaTime() {
		return this.stexaTime;
	}

	public void setStexaTime(Date stexaTime) {
		this.stexaTime = stexaTime;
	}

	@Column(name = "STEXA_OPT", length = 256)
	public String getStexaOpt() {
		return this.stexaOpt;
	}

	public void setStexaOpt(String stexaOpt) {
		this.stexaOpt = stexaOpt;
	}

	@Column(name = "CVLEXA_USER", precision = 10, scale = 0)
	public Long getCvlexaUser() {
		return this.cvlexaUser;
	}

	public void setCvlexaUser(Long cvlexaUser) {
		this.cvlexaUser = cvlexaUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CVLEXA_TIME", length = 7)
	public Date getCvlexaTime() {
		return this.cvlexaTime;
	}

	public void setCvlexaTime(Date cvlexaTime) {
		this.cvlexaTime = cvlexaTime;
	}

	@Column(name = "CVLEXA_OPT", length = 256)
	public String getCvlexaOpt() {
		return this.cvlexaOpt;
	}

	public void setCvlexaOpt(String cvlexaOpt) {
		this.cvlexaOpt = cvlexaOpt;
	}

	@Column(name = "NOTES", length = 256)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the txnseqno
	 */
	@Column(name = "TXNSEQNO")
	public String getTxnseqno() {
		return txnseqno;
	}

	/**
	 * @param txnseqno the txnseqno to set
	 */
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}

	/**
	 * @return the retinfo
	 */
	@Column(name = "RETINFO")
	public String getRetinfo() {
		return retinfo;
	}

	/**
	 * @param retinfo the retinfo to set
	 */
	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}

	/**
	 * @return the retCode
	 */
	@Column(name = "RETCODE")
	public String getRetCode() {
		return retCode;
	}

	/**
	 * @param retCode the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	
	
}
