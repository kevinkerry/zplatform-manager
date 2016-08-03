package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BlacklistMemberModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BLACKLIST_MEMBER")
public class BlacklistMemberModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private String memberid;
	private String status;
	private String notes;
	private String remarks;
	private String risklevel;

	// Constructors

	/** default constructor */
	public BlacklistMemberModel() {
	}

	/** minimal constructor */
	public BlacklistMemberModel(Long TId, String memberid) {
		this.TId = TId;
		this.memberid = memberid;
	}

	/** full constructor */
	public BlacklistMemberModel(Long TId, String memberid, String status,
			String notes, String remarks, String risklevel) {
		this.TId = TId;
		this.memberid = memberid;
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

	@Column(name = "MEMBERID", unique = true, nullable = false, length = 15)
	public String getMemberid() {
		return this.memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
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