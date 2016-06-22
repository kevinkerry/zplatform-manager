package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUploadLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_UPLOAD_LOG")
public class UploadLogModel implements java.io.Serializable {

	// Fields

    /** 日志id */
	private Long logid;
	/** 文件名  */
	private String filename;
	/** 重新上传次数 */	
	private Byte reuploadcount;
	/** 文件所属机构id */  
	private Long caId;
	/** 上传时间 */  
	private String uploadtime;
	/** 文件所属机构名称 */  
	private String caName;
	/** 上传操作员id */  
	private Long uploaderid;
	/** 最后一次重新上传时间 */  
	private String reuploadtime;
	/** 上传操作员名称 */  
	private String uploadername;
	/** 00-上传成功，其他表示失败 */  
	private String recode;	 
	private String notes;  
	private String remarks;
	/** 文件服务器保存路径 */  
	private String fileurl;

	// Constructors

	/** default constructor */
	public UploadLogModel() {
	}

	/** minimal constructor */
	public UploadLogModel(Long logid, String filename, Long uploaderid,
			String uploadername) {
		this.logid = logid;
		this.filename = filename;
		this.uploaderid = uploaderid;
		this.uploadername = uploadername;
	}

	/** full constructor */
	public UploadLogModel(Long logid, String filename, Byte reuploadcount,
			Long caId, String uploadtime, String caName, Long uploaderid,
			String reuploadtime, String uploadername, String recode,
			String notes, String remarks, String fileurl) {
		this.logid = logid;
		this.filename = filename;
		this.reuploadcount = reuploadcount;
		this.caId = caId;
		this.uploadtime = uploadtime;
		this.caName = caName;
		this.uploaderid = uploaderid;
		this.reuploadtime = reuploadtime;
		this.uploadername = uploadername;
		this.recode = recode;
		this.notes = notes;
		this.remarks = remarks;
		this.fileurl = fileurl;
	}

	// Property accessors
	@Id
	@Column(name = "LOGID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	@Column(name = "FILENAME", nullable = false, length = 64)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "REUPLOADCOUNT", precision = 2, scale = 0)
	public Byte getReuploadcount() {
		return this.reuploadcount;
	}

	public void setReuploadcount(Byte reuploadcount) {
		this.reuploadcount = reuploadcount;
	}

	@Column(name = "CA_ID", precision = 10, scale = 0)
	public Long getCaId() {
		return this.caId;
	}

	public void setCaId(Long caId) {
		this.caId = caId;
	}

	@Column(name = "UPLOADTIME", length = 32)
	public String getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	@Column(name = "CA_NAME", length = 32)
	public String getCaName() {
		return this.caName;
	}

	public void setCaName(String caName) {
		this.caName = caName;
	}

	@Column(name = "UPLOADERID", nullable = false, precision = 10, scale = 0)
	public Long getUploaderid() {
		return this.uploaderid;
	}

	public void setUploaderid(Long uploaderid) {
		this.uploaderid = uploaderid;
	}

	@Column(name = "REUPLOADTIME", length = 32)
	public String getReuploadtime() {
		return this.reuploadtime;
	}

	public void setReuploadtime(String reuploadtime) {
		this.reuploadtime = reuploadtime;
	}

	@Column(name = "UPLOADERNAME", nullable = false, length = 64)
	public String getUploadername() {
		return this.uploadername;
	}

	public void setUploadername(String uploadername) {
		this.uploadername = uploadername;
	}

	@Column(name = "RECODE", length = 2)
	public String getRecode() {
		return this.recode;
	}

	public void setRecode(String recode) {
		this.recode = recode;
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

	@Column(name = "FILEURL", length = 128)
	public String getFileurl() {
		return this.fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

}