package com.zlebank.zplatform.manager.dao.object.scan;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_MERCH_CONFING")
public class MerchConfigModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String memberId;
	private String status;
	private String typeId;
	private String ip ;
	private String port;
	private String path;
	private String userName;
	private String password;
	private String description;
	private Date createTime;
	private String createId;
	private Date updateTime;
	private String updateId;
	private String remark;
	private String notes;
	 @Id
	 @Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "MEMBER_ID")
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Column(name = "STATUS", length=2)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "TYPE_ID", length=5)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Column(name = "IP", length=20)
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "PORT", length=128)
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@Column(name = "PATH", length=128)
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Column(name = "USER_NAME", length=128)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "PASSWORD",length=128)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "DESCRIPTION", length=128)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	 @Temporal(TemporalType.TIMESTAMP)
     @Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "CREATE_ID")
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	 @Temporal(TemporalType.TIMESTAMP)
     @Column(name="UPDATE_TIME")
	 public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "UDATE_ID", length = 128)
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	 @Column(name = "REMARK", length = 128)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 @Column(name = "NOTES", length = 128)
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
