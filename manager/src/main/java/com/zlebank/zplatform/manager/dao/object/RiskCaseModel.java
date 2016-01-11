package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TRiskCase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RISK_CASE")
public class RiskCaseModel implements java.io.Serializable {

	// Fields

	private Long caseid;
	private String busicode;
	private String businame;
	private String riskver;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;
	private String activeflag;
	private String initflag;

	// Constructors

	/** default constructor */
	public RiskCaseModel() {
	}

	/** minimal constructor */
	public RiskCaseModel(Long caseid, String busicode, String businame,
			String riskver, String status, Date intime, Long inuser) {
		this.caseid = caseid;
		this.busicode = busicode;
		this.businame = businame;
		this.riskver = riskver;
		this.status = status;
		this.intime = intime;
		this.inuser = inuser;
	}

	/** full constructor */
	public RiskCaseModel(Long caseid, String busicode, String businame,
			String riskver, String status, Date intime, Long inuser,
			Date uptime, Long upuser, String notes, String remarks,
			String activeflag, String initflag) {
		this.caseid = caseid;
		this.busicode = busicode;
		this.businame = businame;
		this.riskver = riskver;
		this.status = status;
		this.intime = intime;
		this.inuser = inuser;
		this.uptime = uptime;
		this.upuser = upuser;
		this.notes = notes;
		this.remarks = remarks;
		this.activeflag = activeflag;
		this.initflag = initflag;
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

	@Column(name = "BUSICODE", nullable = false, length = 4)
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

	@Column(name = "RISKVER", nullable = false, length = 8)
	public String getRiskver() {
		return this.riskver;
	}

	public void setRiskver(String riskver) {
		this.riskver = riskver;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INTIME", nullable = false, length = 7)
	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Column(name = "INUSER", nullable = false, precision = 10, scale = 0)
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

	@Column(name = "ACTIVEFLAG", length = 64)
	public String getActiveflag() {
		return this.activeflag;
	}

	public void setActiveflag(String activeflag) {
		this.activeflag = activeflag;
	}

	@Column(name = "INITFLAG", length = 2)
	public String getInitflag() {
		return this.initflag;
	}

	public void setInitflag(String initflag) {
		this.initflag = initflag;
	}

}