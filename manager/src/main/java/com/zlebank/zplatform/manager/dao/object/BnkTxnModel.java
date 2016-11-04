package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BnkTxnModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BNK_TXN")
public class BnkTxnModel implements java.io.Serializable {

	// Fields

	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**
	 * 
	 */
	private Long tid;
	private String instiid;
	private String payordno;
	private String txndatetime;
	private String busicode;
	private Long amount;
	private String pan;
	private String merchno;
	private String systrcno;
	private String paytrcno;
	private Long cfee;
	private Long dfee;
	private String retcode;
	private String acqsettledate;
	private Long proid;
	private String status;
	private String result;
	private String notes;
	private String remarks;


	// Constructors

	/** default constructor */
	public BnkTxnModel() {
	}

	/** minimal constructor */
	public BnkTxnModel(Long tid) {
		this.tid = tid;
	}

	/** full constructor */
	public BnkTxnModel(Long tid, String instiid, String payordno,
			String txndatetime, String busicode, Long amount, String pan,
			String merchno, String systrcno, String paytrcno, Long cfee,
			Long dfee, String retcode, String acqsettledate, Long proid,
			String status, String result, String notes, String remarks) {
		this.tid = tid;
		this.instiid = instiid;
		this.payordno = payordno;
		this.txndatetime = txndatetime;
		this.busicode = busicode;
		this.amount = amount;
		this.pan = pan;
		this.merchno = merchno;
		this.systrcno = systrcno;
		this.paytrcno = paytrcno;
		this.cfee = cfee;
		this.dfee = dfee;
		this.retcode = retcode;
		this.acqsettledate = acqsettledate;
		this.proid = proid;
		this.status = status;
		this.result = result;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "INSTIID", length = 11)
	public String getInstiid() {
		return this.instiid;
	}

	public void setInstiid(String instiid) {
		this.instiid = instiid;
	}

	@Column(name = "PAYORDNO", length = 32)
	public String getPayordno() {
		return this.payordno;
	}

	public void setPayordno(String payordno) {
		this.payordno = payordno;
	}

	@Column(name = "TXNDATETIME", length = 14)
	public String getTxndatetime() {
		return this.txndatetime;
	}

	public void setTxndatetime(String txndatetime) {
		this.txndatetime = txndatetime;
	}

	@Column(name = "BUSICODE", length = 6)
	public String getBusicode() {
		return this.busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	@Column(name = "AMOUNT", precision = 12, scale = 0)
	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Column(name = "PAN", length = 30)
	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@Column(name = "MERCHNO", length = 15)
	public String getMerchno() {
		return this.merchno;
	}

	public void setMerchno(String merchno) {
		this.merchno = merchno;
	}

	@Column(name = "SYSTRCNO", length = 32)
	public String getSystrcno() {
		return this.systrcno;
	}

	public void setSystrcno(String systrcno) {
		this.systrcno = systrcno;
	}

	@Column(name = "PAYTRCNO", length = 6)
	public String getPaytrcno() {
		return this.paytrcno;
	}

	public void setPaytrcno(String paytrcno) {
		this.paytrcno = paytrcno;
	}

	@Column(name = "CFEE", precision = 12, scale = 0)
	public Long getCfee() {
		return this.cfee;
	}

	public void setCfee(Long cfee) {
		this.cfee = cfee;
	}

	@Column(name = "DFEE", precision = 12, scale = 0)
	public Long getDfee() {
		return this.dfee;
	}

	public void setDfee(Long dfee) {
		this.dfee = dfee;
	}

	@Column(name = "RETCODE", length = 4)
	public String getRetcode() {
		return this.retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	@Column(name = "ACQSETTLEDATE", length = 8)
	public String getAcqsettledate() {
		return this.acqsettledate;
	}

	public void setAcqsettledate(String acqsettledate) {
		this.acqsettledate = acqsettledate;
	}

	@Column(name = "PROID", precision = 10, scale = 0)
	public Long getProid() {
		return this.proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RESULT", length = 2)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "NOTES", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 128)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



}