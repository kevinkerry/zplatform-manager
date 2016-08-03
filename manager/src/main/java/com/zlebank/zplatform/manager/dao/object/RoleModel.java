package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ROLE")
public class RoleModel implements java.io.Serializable {

	// Fields

	private Long roleId;
	private String roleName;
	private Long organId;
	private Long deptId;
	private String creator;
	private Date creatDate;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public RoleModel() {
	}

	/** minimal constructor */
	public RoleModel(Long roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public RoleModel(Long roleId, String roleName, Long organId, Long deptId,
			String creator, Date creatDate, String status, String notes,
			String remarks) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.organId = organId;
		this.deptId = deptId;
		this.creator = creator;
		this.creatDate = creatDate;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_NAME", length = 64)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ORGAN_ID", precision = 10, scale = 0)
	public Long getOrganId() {
		return this.organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	@Column(name = "DEPT_ID", precision = 10, scale = 0)
	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREAT_DATE", length = 7)
	public Date getCreatDate() {
		return this.creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
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