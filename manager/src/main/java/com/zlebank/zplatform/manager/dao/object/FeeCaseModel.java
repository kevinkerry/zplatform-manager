package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TFeeCase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FEE_CASE")
public class FeeCaseModel implements java.io.Serializable {

	// Fields

	private Long caseid;
	private String busicode;
	private String businame;
	private String feever;
	private String status;
	private String setlflg;
	private String feetype;
	private String feeretflag;
	private String merchamtflag;
	private String merchfeeflag;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String initflag;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public FeeCaseModel() {
	}

	/** minimal constructor */
	public FeeCaseModel(Long caseid, String busicode, String businame,
			String feever, String status, Date intime) {
		this.caseid = caseid;
		this.busicode = busicode;
		this.businame = businame;
		this.feever = feever;
		this.status = status;
		this.intime = intime;
	}

	/** full constructor */
	public FeeCaseModel(Long caseid, String busicode, String businame,
			String feever, String status, String setlflg, String feetype,
			String feeretflag, String merchamtflag, String merchfeeflag,
			Date intime, Long inuser, Date uptime, Long upuser,
			String initflag, String notes, String remarks) {
		this.caseid = caseid;
		this.busicode = busicode;
		this.businame = businame;
		this.feever = feever;
		this.status = status;
		this.setlflg = setlflg;
		this.feetype = feetype;
		this.feeretflag = feeretflag;
		this.merchamtflag = merchamtflag;
		this.merchfeeflag = merchfeeflag;
		this.intime = intime;
		this.inuser = inuser;
		this.uptime = uptime;
		this.upuser = upuser;
		this.initflag = initflag;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "CASEID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	@Column(name = "BUSICODE", nullable = false, length = 8)
	public String getBusicode() {
		return this.busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	@Column(name = "BUSINAME", nullable = false, length = 64)
	public String getBusiname() {
		return this.businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	@Column(name = "FEEVER", nullable = false, length = 8)
	public String getFeever() {
		return this.feever;
	}

	public void setFeever(String feever) {
		this.feever = feever;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SETLFLG", precision = 1, scale = 0)
	public String getSetlflg() {
		return this.setlflg;
	}

	public void setSetlflg(String setlflg) {
		this.setlflg = setlflg;
	}

	@Column(name = "FEETYPE", precision = 1, scale = 0)
	public String getFeetype() {
		return this.feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	@Column(name = "FEERETFLAG", precision = 1, scale = 0)
	public String getFeeretflag() {
		return this.feeretflag;
	}

	public void setFeeretflag(String feeretflag) {
		this.feeretflag = feeretflag;
	}

	@Column(name = "MERCHAMTFLAG", length = 1)
	public String getMerchamtflag() {
		return this.merchamtflag;
	}

	public void setMerchamtflag(String merchamtflag) {
		this.merchamtflag = merchamtflag;
	}

	@Column(name = "MERCHFEEFLAG", length = 1)
	public String getMerchfeeflag() {
		return this.merchfeeflag;
	}

	public void setMerchfeeflag(String merchfeeflag) {
		this.merchfeeflag = merchfeeflag;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INTIME", nullable = false, length = 7)
	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Column(name = "INUSER", precision = 10, scale = 0)
	public Long getInuser() {
		return this.inuser;
	}

	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPTIME", length = 7)
	public Date getUptime() {
		return this.uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	@Column(name = "UPUSER", precision = 10, scale = 0)
	public Long getUpuser() {
		return this.upuser;
	}

	public void setUpuser(Long upuser) {
		this.upuser = upuser;
	}

	@Column(name = "INITFLAG", length = 12)
	public String getInitflag() {
		return this.initflag;
	}

	public void setInitflag(String initflag) {
		this.initflag = initflag;
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