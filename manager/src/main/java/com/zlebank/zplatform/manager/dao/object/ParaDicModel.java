package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TParaDic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PARA_DIC")
public class ParaDicModel implements java.io.Serializable {

	// Fields

	private Long tid;
	private String paraCode;
	private Long parentId;
	private String paraName;
	private String paraType;
	private Long hasSub;
	private Long status;
	private String remarks;

	// Constructors

	/** default constructor */
	public ParaDicModel() {
	}

	/** full constructor */
	public ParaDicModel(String paraCode, Long parentId, String paraName,
			String paraType, Long hasSub, Long status, String remarks) {
		this.paraCode = paraCode;
		this.parentId = parentId;
		this.paraName = paraName;
		this.paraType = paraType;
		this.hasSub = hasSub;
		this.status = status;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "PARA_CODE", length = 32)
	public String getParaCode() {
		return this.paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}

	@Column(name = "PARENT_ID", precision = 10, scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "PARA_NAME", length = 128)
	public String getParaName() {
		return this.paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	@Column(name = "PARA_TYPE", length = 64)
	public String getParaType() {
		return this.paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	@Column(name = "HAS_SUB", precision = 1, scale = 0)
	public Long getHasSub() {
		return this.hasSub;
	}

	public void setHasSub(Long hasSub) {
		this.hasSub = hasSub;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "REMARKS", length = 128)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}