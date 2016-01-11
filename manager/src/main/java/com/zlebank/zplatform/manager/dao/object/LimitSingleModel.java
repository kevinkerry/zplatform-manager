package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * LimitSingleModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LIMIT_SINGLE", schema = "PAYSERVICE", uniqueConstraints = @UniqueConstraint(columnNames = "CASEID"))
public class LimitSingleModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private String caseid;
	private String maxAmount;
	private String minAmount;
	private String risklevel;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public LimitSingleModel() {
	}

	/** minimal constructor */
	public LimitSingleModel(Long TId, String caseid, String maxAmount) {
		this.TId = TId;
		this.caseid = caseid;
		this.maxAmount = maxAmount;
	}

	/** full constructor */
	public LimitSingleModel(Long TId, String caseid, String maxAmount, String minAmount,
			String risklevel, String status, String notes, String remarks) {
		this.TId = TId; 
		this.caseid = caseid;
		this.maxAmount = maxAmount;
		this.minAmount = minAmount;
		this.risklevel = risklevel;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "T_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTId() {
		return this.TId;
	}

	public void setTId(Long TId) {
		this.TId = TId;
	}

	@Column(name = "CASEID", unique = true, nullable = false, precision = 10, scale = 0)
	public String getCaseid() {
		return this.caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	@Column(name = "MAX_AMOUNT", nullable = false, precision = 12, scale = 0)
	public String getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	@Column(name = "MIN_AMOUNT", precision = 12, scale = 0)
	public String getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	@Column(name = "RISKLEVEL", precision = 1, scale = 0)
	public String getRisklevel() {
		return this.risklevel;
	}

	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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