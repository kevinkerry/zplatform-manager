package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOrgan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ORGAN")
public class OrganModel implements java.io.Serializable {

	// Fields

	private Long organId;
	private String organCode;
	private String organName;
	private Long superid;
	private Byte levelid;
	private String province;
	private String city;
	private String creator;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public OrganModel() {
	}

	/** minimal constructor */
	public OrganModel(Long organId, String organName) {
		this.organId = organId;
		this.organName = organName;
	}

	/** full constructor */
	public OrganModel(Long organId, String organCode, String organName,
			Long superid, Byte levelid, String province, String city,
			String creator, String status, String notes, String remarks) {
		this.organId = organId;
		this.organCode = organCode;
		this.organName = organName;
		this.superid = superid;
		this.levelid = levelid;
		this.province = province;
		this.city = city;
		this.creator = creator;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "ORGAN_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getOrganId() {
		return this.organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	@Column(name = "ORGAN_CODE", length = 4)
	public String getOrganCode() {
		return this.organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	@Column(name = "ORGAN_NAME", nullable = false, length = 64)
	public String getOrganName() {
		return this.organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	@Column(name = "SUPERID", precision = 10, scale = 0)
	public Long getSuperid() {
		return this.superid;
	}

	public void setSuperid(Long superid) {
		this.superid = superid;
	}

	@Column(name = "LEVELID", precision = 2, scale = 0)
	public Byte getLevelid() {
		return this.levelid;
	}

	public void setLevelid(Byte levelid) {
		this.levelid = levelid;
	}

	@Column(name = "PROVINCE", precision = 2, scale = 0)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", precision = 4, scale = 0)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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