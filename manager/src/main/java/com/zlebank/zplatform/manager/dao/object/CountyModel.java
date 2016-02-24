package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCounty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_COUNTY")
public class CountyModel implements java.io.Serializable{
	
	// Fields

	private Long TId;
	private String TName;
	private String TZcode;
	private String TPcode;
	private Long CId;
	private String TCode;
	private String xzCode;
	private String cxzCode;
	private String pxzCode;
	
	// Constructors

	/** default constructor */
	public CountyModel() {
	}

	/** minimal constructor */
	public CountyModel(Long TId) {
		this.TId = TId;
	}

	/** full constructor */
	public CountyModel(Long TId, String TName, String TZcode, String TPcode,
			Long CId, String TCode, String xzCode, String cxzCode,
			String pxzCode) {
		this.TId = TId;
		this.TName = TName;
		this.TZcode = TZcode;
		this.TPcode = TPcode;
		this.CId = CId;
		this.TCode = TCode;
		this.xzCode = xzCode;
		this.cxzCode = cxzCode;
		this.pxzCode = pxzCode;
	}

	// Property accessors
	@Id
	@Column(name = "T_ID", nullable = false, precision = 10, scale = 0)
	public Long getTId() {
		return this.TId;
	}

	public void setTId(Long TId) {
		this.TId = TId;
	}

	@Column(name = "T_NAME", length = 32)
	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	@Column(name = "T_ZCODE", length = 6)
	public String getTZcode() {
		return this.TZcode;
	}

	public void setTZcode(String TZcode) {
		this.TZcode = TZcode;
	}

	@Column(name = "T_PCODE", length = 6)
	public String getTPcode() {
		return this.TPcode;
	}

	public void setTPcode(String TPcode) {
		this.TPcode = TPcode;
	}

	@Column(name = "C_ID", precision = 10, scale = 0)
	public Long getCId() {
		return this.CId;
	}

	public void setCId(Long CId) {
		this.CId = CId;
	}

	@Column(name = "T_CODE", length = 4)
	public String getTCode() {
		return this.TCode;
	}

	public void setTCode(String TCode) {
		this.TCode = TCode;
	}

	@Column(name = "XZ_CODE", length = 6)
	public String getXzCode() {
		return this.xzCode;
	}

	public void setXzCode(String xzCode) {
		this.xzCode = xzCode;
	}

	@Column(name = "CXZ_CODE", length = 6)
	public String getCxzCode() {
		return this.cxzCode;
	}

	public void setCxzCode(String cxzCode) {
		this.cxzCode = cxzCode;
	}

	@Column(name = "PXZ_CODE", length = 6)
	public String getPxzCode() {
		return this.pxzCode;
	}

	public void setPxzCode(String pxzCode) {
		this.pxzCode = pxzCode;
	}
}
