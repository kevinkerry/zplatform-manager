package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TLimitPerday entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LIMIT_PERDAY")
public class LimitPerdayModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private Long caseid;
	private Long nums;
	private String risklevel;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public LimitPerdayModel() {
	}

	/** minimal constructor */
	public LimitPerdayModel(Long TId, Long caseid, Long nums) {
		this.TId = TId;
		this.caseid = caseid;
		this.nums = nums;
	}

	/** full constructor */
	public LimitPerdayModel(Long TId, Long caseid, Long nums, String risklevel,
			String status, String notes, String remarks) {
		this.TId = TId;
		this.caseid = caseid;
		this.nums = nums;
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
	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	@Column(name = "NUMS", nullable = false, precision = 12, scale = 0)
	public Long getNums() {
		return this.nums;
	}

	public void setNums(Long nums) {
		this.nums = nums;
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