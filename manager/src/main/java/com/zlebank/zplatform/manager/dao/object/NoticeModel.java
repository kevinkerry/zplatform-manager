package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_NOTICE")
public class NoticeModel implements java.io.Serializable {

	// Fields

	private Long nid;
	private String title;
	private String content;
	private Long noticeType;
	private Long noticeSort;
	private Long noticeUserId;
	private Date pubTime;
	private Long pubUser;
	private Long status;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public NoticeModel() {
	}

	/** minimal constructor */
	public NoticeModel(Long nid) {
		this.nid = nid;
	}

	/** full constructor */
	public NoticeModel(Long nid, String title, String content, Long noticeType,
			Long noticeSort, Long noticeUserId, Date pubTime, Long pubUser,
			Long status, String notes, String remarks) {
		this.nid = nid;
		this.title = title;
		this.content = content;
		this.noticeType = noticeType;
		this.noticeSort = noticeSort;
		this.noticeUserId = noticeUserId;
		this.pubTime = pubTime;
		this.pubUser = pubUser;
		this.status = status;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "NID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getNid() {
		return this.nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	@Column(name = "TITLE", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT", length = 1024)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "NOTICE_TYPE", precision = 2, scale = 0)
	public Long getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(Long noticeType) {
		this.noticeType = noticeType;
	}

	@Column(name = "NOTICE_SORT", precision = 2, scale = 0)
	public Long getNoticeSort() {
		return this.noticeSort;
	}

	public void setNoticeSort(Long noticeSort) {
		this.noticeSort = noticeSort;
	}

	@Column(name = "NOTICE_USER_ID", precision = 10, scale = 0)
	public Long getNoticeUserId() {
		return this.noticeUserId;
	}

	public void setNoticeUserId(Long noticeUserId) {
		this.noticeUserId = noticeUserId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PUB_TIME", length = 7)
	public Date getPubTime() {
		return this.pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	@Column(name = "PUB_USER", precision = 10, scale = 0)
	public Long getPubUser() {
		return this.pubUser;
	}

	public void setPubUser(Long pubUser) {
		this.pubUser = pubUser;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
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