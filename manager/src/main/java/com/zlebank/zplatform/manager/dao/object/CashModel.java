package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TCash entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CASH")
public class CashModel implements java.io.Serializable {

	// Fields

	private Long cashid;
	private String cashver;
	private String cashname;
	private String status;
	private Date intime;
	private Long inuser;
	private Date uptime;
	private Long upuser;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public CashModel() {
	}

	/** minimal constructor */
	public CashModel(Long cashid, String cashver, String cashname, Date intime) {
		this.cashid = cashid;
		this.cashver = cashver;
		this.cashname = cashname;
		this.intime = intime;
	}

	/** full constructor */
	public CashModel(Long cashid, String cashver, String cashname, String status,
			Date intime, Long inuser, Date uptime, Long upuser, String notes,
			String remarks) {
		this.cashid = cashid;
		this.cashver = cashver;
		this.cashname = cashname;
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
	@Column(name = "CASHID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCashid() {
		return this.cashid;
	}

	public void setCashid(Long cashid) {
		this.cashid = cashid;
	}

	@Column(name = "CASHVER", nullable = false, length = 8)
	public String getCashver() {
		return this.cashver;
	}

	public void setCashver(String cashver) {
		this.cashver = cashver;
	}

	@Column(name = "CASHNAME", nullable = false, length = 64)
	public String getCashname() {
		return this.cashname;
	}

	public void setCashname(String cashname) {
		this.cashname = cashname;
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