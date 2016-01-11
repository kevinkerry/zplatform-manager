package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FTPModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FTP")
public class FTPModel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5810167824352575369L;
	private Long tid;
	private String servername;
	private String ip;
	private String port;
	private String users;
	private String pwd;
	private String localcharset;
	private String remotecharset;
	private String remarks;
	private String notes;
	private String module;
	// Constructors

	/** default constructor */
	public FTPModel() {
	}

	/** minimal constructor */
	public FTPModel(Long tid) {
		this.tid = tid;
	}

	/** full constructor */
	public FTPModel(Long tid, String servername, String ip, String port,
			String users, String pwd, String localcharset,
			String remotecharset, String remarks, String notes) {
		this.tid = tid;
		this.servername = servername;
		this.ip = ip;
		this.port = port;
		this.users = users;
		this.pwd = pwd;
		this.localcharset = localcharset;
		this.remotecharset = remotecharset;
		this.remarks = remarks;
		this.notes = notes;
	}

	// Property accessors
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 11, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "SERVERNAME", length = 64)
	public String getServername() {
		return this.servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	@Column(name = "IP", length = 32)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "PORT", length = 10)
	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Column(name = "USERS", length = 32)
	public String getUsers() {
		return this.users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	@Column(name = "PWD", length = 64)
	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "LOCALCHARSET", length = 64)
	public String getLocalcharset() {
		return this.localcharset;
	}

	public void setLocalcharset(String localcharset) {
		this.localcharset = localcharset;
	}

	@Column(name = "REMOTECHARSET", length = 64)
	public String getRemotecharset() {
		return this.remotecharset;
	}

	public void setRemotecharset(String remotecharset) {
		this.remotecharset = remotecharset;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "NOTES", length = 256)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(name="MODULE",length=32)
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
}