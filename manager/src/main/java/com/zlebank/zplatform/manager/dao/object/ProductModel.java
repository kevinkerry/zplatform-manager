package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PRODUCT")
public class ProductModel implements java.io.Serializable {

	// Fields

	private String prdtver;
	private Long prdtid;
	private String prdtname;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public ProductModel() {
	}

	/** minimal constructor */
	public ProductModel(String prdtver, Long prdtid, String prdtname, Date intime) {
		this.prdtver = prdtver;
		this.prdtid = prdtid;
		this.prdtname = prdtname;
		this.intime = intime;
	}

	/** full constructor */
	public ProductModel(String prdtver, Long prdtid, String prdtname,
			String status, Date intime, Long inuser, Date uptime, Long upuser,
			String notes, String remarks) {
		this.prdtver = prdtver;
		this.prdtid = prdtid;
		this.prdtname = prdtname;
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
	@Column(name = "PRDTVER", unique = true, nullable = false, length = 8)
	public String getPrdtver() {
		return this.prdtver;
	}

	public void setPrdtver(String prdtver) {
		this.prdtver = prdtver;
	}

	@Column(name = "PRDTID", nullable = false, precision = 10, scale = 0)
	public Long getPrdtid() {
		return this.prdtid;
	}

	public void setPrdtid(Long prdtid) {
		this.prdtid = prdtid;
	}

	@Column(name = "PRDTNAME", nullable = false, length = 64)
	public String getPrdtname() {
		return this.prdtname;
	}

	public void setPrdtname(String prdtname) {
		this.prdtname = prdtname;
	}

	@Column(name = "STATUS", length = 2)
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

}