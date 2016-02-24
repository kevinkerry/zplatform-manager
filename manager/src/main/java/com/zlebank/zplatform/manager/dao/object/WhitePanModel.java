package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TWhitelistPan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_WHITELIST_PAN")
public class WhitePanModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private String pan;
	private String status;
	private String notes;
	private String remarks;
	private String risklevel;

	// Constructors

	/** default constructor */
	public WhitePanModel() {
	}

	/** minimal constructor */
	public WhitePanModel(Long TId, String pan) {
		this.TId = TId;
		this.pan = pan;
	}

	/** full constructor */
	public WhitePanModel(Long TId, String pan, String status, String notes,
			String remarks, String risklevel) {
		this.TId = TId;
		this.pan = pan;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
		this.risklevel = risklevel;
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

	@Column(name = "PAN", unique = true, nullable = false, length = 19)
	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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

	@Column(name = "RISKLEVEL", precision = 1, scale = 0)
	public String getRisklevel() {
		return this.risklevel;
	}

	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
	}

}