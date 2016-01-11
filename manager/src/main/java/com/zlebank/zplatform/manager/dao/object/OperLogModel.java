package com.zlebank.zplatform.manager.dao.object;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOperLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_OPER_LOG")
public class OperLogModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6954744403855842627L;
	private Long logId;
	private Long functId;
	private Long userId;
	private String userName;
	private Timestamp opDate;
	private String ip;
	private String hostName;
	private String opType;
	private String opContent;
	private Long deptId;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public OperLogModel() {
	}

	/** minimal constructor */
	public OperLogModel(Long logId) {
		this.logId = logId;
	}

	/** full constructor */
	public OperLogModel(Long logId, Long functId, Long userId, String userName,
			Timestamp opDate, String ip, String hostName, String opType,
			String opContent, Long deptId, String notes, String remarks) {
		this.logId = logId;
		this.functId = functId;
		this.userId = userId;
		this.userName = userName;
		this.opDate = opDate;
		this.ip = ip;
		this.hostName = hostName;
		this.opType = opType;
		this.opContent = opContent;
		this.deptId = deptId;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "LOG_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "FUNCT_ID",  precision = 10, scale = 0)
	public Long getFunctId() {
		return this.functId;
	}

	public void setFunctId(Long functId) {
		this.functId = functId;
	}

	@Column(name = "USER_ID", precision = 10, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "OP_DATE", length = 7)
	public Timestamp getOpDate() {
		return this.opDate;
	}

	public void setOpDate(Timestamp opDate) {
		this.opDate = opDate;
	}

	@Column(name = "IP", length = 32)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "HOST_NAME", length = 32)
	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	@Column(name = "OP_TYPE", length = 32)
	public String getOpType() {
		return this.opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	@Column(name = "OP_CONTENT", length = 32)
	public String getOpContent() {
		return this.opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	@Column(name = "DEPT_ID", precision = 10, scale = 0)
	public Long getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
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