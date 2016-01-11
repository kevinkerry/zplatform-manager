package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LimitMemDayModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LIMIT_MEM_DAY", schema = "PAYSERVICE")
public class LimitMemDayModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private String memberid;
	private String limitAmount;
	private String limitCount;
	private String risklevel;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public LimitMemDayModel() {
	}

	/** minimal constructor */
	public LimitMemDayModel(Long TId, String limitAmount) {
		this.TId = TId;
		this.limitAmount = limitAmount;
	}

	/** full constructor */
	public LimitMemDayModel(Long TId, String memberid, String limitAmount,
			String limitCount, String risklevel, String status, String notes,
			String remarks) {
		this.TId = TId;
		this.memberid = memberid;
		this.limitAmount = limitAmount;
		this.limitCount = limitCount;
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

	@Column(name = "MEMBERID", length = 15)
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	@Column(name = "LIMIT_AMOUNT", nullable = false, precision = 12, scale = 0)
	public String getLimitAmount() {
		return this.limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	@Column(name = "LIMIT_COUNT", precision = 10, scale = 0)
	public String getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
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