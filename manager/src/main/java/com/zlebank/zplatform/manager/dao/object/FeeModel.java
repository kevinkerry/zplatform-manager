package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TFee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FEE")
public class FeeModel implements java.io.Serializable {

	// Fields

	private Long feeid;
	private String prdtver;
	private String feever;
	private String feename;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String initflag;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public FeeModel() {
	}

	/** minimal constructor */
	public FeeModel(Long feeid, String feever, String feename, String status) {
		this.feeid = feeid;
		this.feever = feever;
		this.feename = feename;
		this.status = status;
	}

	/** full constructor */
	public FeeModel(Long feeid, String prdtver, String feever, String feename,
			String status, Date intime, Long inuser, Date uptime, Long upuser,
			String initflag, String notes, String remarks) {
		this.feeid = feeid;
		this.prdtver = prdtver;
		this.feever = feever;
		this.feename = feename;
		this.status = status;
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
	@Column(name = "FEEID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getFeeid() {
		return this.feeid;
	}

	public void setFeeid(Long feeid) {
		this.feeid = feeid;
	}

	@Column(name = "PRDTVER", length = 4)
	public String getPrdtver() {
		return this.prdtver;
	}

	public void setPrdtver(String prdtver) {
		this.prdtver = prdtver;
	}

	@Column(name = "FEEVER", nullable = false, length = 8)
	public String getFeever() {
		return this.feever;
	}

	public void setFeever(String feever) {
		this.feever = feever;
	}

	@Column(name = "FEENAME", nullable = false, length = 64)
	public String getFeename() {
		return this.feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INTIME", length = 7)
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

	@Column(name = "INITFLAG", length = 128)
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