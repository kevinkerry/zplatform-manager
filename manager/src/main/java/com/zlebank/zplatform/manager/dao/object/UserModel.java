package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_USER")
public class UserModel implements java.io.Serializable {

	// Fields

	private Long userId;
	private String userCode;
	private String userName;
	private String loginName;
	private String pwd;
	private Date pwdValid;
	private Short pwdErrorNumber;
	private Date lastLoginTime;
	private String creator;
	private Date createDate;
	private Long organId;
	private Long deptId;
	private String isadmin;
	private String status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public UserModel() {
	}

	/** minimal constructor */
	public UserModel(Long userId) {
		this.userId = userId;
	}

	/** full constructor */
	public UserModel(Long userId, String userCode, String userName,
			String loginName, String pwd, Date pwdValid, Short pwdErrorNumber,
			Date lastLoginTime, String creator, Date createDate, Long organId,
			Long deptId, String isadmin, String status, String notes,
			String remarks) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.loginName = loginName;
		this.pwd = pwd;
		this.pwdValid = pwdValid;
		this.pwdErrorNumber = pwdErrorNumber;
		this.lastLoginTime = lastLoginTime;
		this.creator = creator;
		this.createDate = createDate;
		this.organId = organId;
		this.deptId = deptId;
		this.isadmin = isadmin;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_CODE", length = 16)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "LOGIN_NAME", length = 32)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "PWD", length = 32)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PWD_VALID", length = 7)
	public Date getPwdValid() {
		return this.pwdValid;
	}

	public void setPwdValid(Date pwdValid) {
		this.pwdValid = pwdValid;
	}

	@Column(name = "PWD_ERROR_NUMBER", precision = 4, scale = 0)
	public Short getPwdErrorNumber() {
		return this.pwdErrorNumber;
	}

	public void setPwdErrorNumber(Short pwdErrorNumber) {
		this.pwdErrorNumber = pwdErrorNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_LOGIN_TIME", length = 7)
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	@Column(name = "ISADMIN", length = 1)
	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
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