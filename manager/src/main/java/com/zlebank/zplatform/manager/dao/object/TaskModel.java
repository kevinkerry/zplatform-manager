package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TASK")
public class TaskModel implements java.io.Serializable {

	// Fields

	private Long tid;
	private String content;
	private String url;
	private Long taskFunct;
	private Long taskType;
	private Long taskOrgan;
	private String taskNo;
	private Long status;
	private Date taskDate;
	private String notes;
	private String remarks;
	private Long taskUser;
	// Constructors

	/** default constructor */
	public TaskModel() {
	}

	/** minimal constructor */
	public TaskModel(Long tid) {
		this.tid = tid;
	}

	/** full constructor */
	public TaskModel(Long tid, String content, String url, Long taskFunct,
			Long taskType, Long taskOrgan, String taskNo, Long status,
			Date taskDate, String notes, String remarks) {
		this.tid = tid;
		this.content = content;
		this.url = url;
		this.taskFunct = taskFunct;
		this.taskType = taskType;
		this.taskOrgan = taskOrgan;
		this.taskNo = taskNo;
		this.status = status;
		this.taskDate = taskDate;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "CONTENT", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "URL", length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "TASK_FUNCT", precision = 10, scale = 0)
	public Long getTaskFunct() {
		return this.taskFunct;
	}

	public void setTaskFunct(Long taskFunct) {
		this.taskFunct = taskFunct;
	}

	@Column(name = "TASK_TYPE", precision = 10, scale = 0)
	public Long getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Long taskType) {
		this.taskType = taskType;
	}

	@Column(name = "TASK_ORGAN", precision = 10, scale = 0)
	public Long getTaskOrgan() {
		return this.taskOrgan;
	}

	public void setTaskOrgan(Long taskOrgan) {
		this.taskOrgan = taskOrgan;
	}

	@Column(name = "TASK_NO", length = 32)
	public String getTaskNo() {
		return this.taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TASK_DATE", length = 7)
	public Date getTaskDate() {
		return this.taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
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
	
	@Column(name = "TASK_USER", precision = 10, scale = 0)
	public Long getTaskUser() {
		return this.taskUser;
	}

	public void setTaskUser(Long taskUser) {
		this.taskUser = taskUser;
	}
}