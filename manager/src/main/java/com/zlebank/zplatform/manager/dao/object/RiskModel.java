package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TRisk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RISK")
public class RiskModel implements java.io.Serializable {

	// Fields

	private Long riskid;
	private String riskver;
	private String riskname;
	private String prdtver;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public RiskModel() {
	}

	/** minimal constructor */
	public RiskModel(Long riskid, String riskver, String riskname, String prdtver,
			String status, Date intime) {
		this.riskid = riskid;
		this.riskver = riskver;
		this.riskname = riskname;
		this.prdtver = prdtver;
		this.status = status;
		this.intime = intime;
	}

	/** full constructor */
	public RiskModel(Long riskid, String riskver, String riskname, String prdtver,
			String status, Date intime, Long inuser, Date uptime, Long upuser,
			String notes, String remarks) {
		this.riskid = riskid;
		this.riskver = riskver;
		this.riskname = riskname;
		this.prdtver = prdtver;
		this.status = status;
		this.intime = intime;
		this.inuser = inuser;
		this.uptime = uptime;
		this.upuser = upuser;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "RISKID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRiskid() {
		return this.riskid;
	}

	public void setRiskid(Long riskid) {
		this.riskid = riskid;
	}

	@Column(name = "RISKVER", nullable = false, length = 8)
	public String getRiskver() {
		return this.riskver;
	}

	public void setRiskver(String riskver) {
		this.riskver = riskver;
	}

	@Column(name = "RISKNAME", nullable = false, length = 64)
	public String getRiskname() {
		return this.riskname;
	}

	public void setRiskname(String riskname) {
		this.riskname = riskname;
	}

	@Column(name = "PRDTVER", nullable = false, length = 8)
	public String getPrdtver() {
		return this.prdtver;
	}

	public void setPrdtver(String prdtver) {
		this.prdtver = prdtver;
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