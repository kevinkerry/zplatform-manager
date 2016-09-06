package com.zlebank.zplatform.manager.dao.object.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "T_FINANCE_PRODUCT")
public class FinProductMode implements java.io.Serializable  {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2369531070364090965L;
	private long pid;
	private String productName;
	private String productCode;
	private String fundManager;
	private String financier;
	private Date inTime;
	private String notes;
	private String remarks;
	
	
	public FinProductMode() {
	}

	public FinProductMode(long pid) {
		this.pid = pid;
	}

	public FinProductMode(long pid, String productName, String productCode,
			String fundManager, String financier, Date inTime, String notes,
			String remarks) {
		super();
		this.pid = pid;
		this.productName = productName;
		this.productCode = productCode;
		this.fundManager = fundManager;
		this.financier = financier;
		this.inTime = inTime;
		this.notes = notes;
		this.remarks = remarks;
	}

	@Id
	@Column(name = "PID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getPid() {
		return this.pid;
	}

	public void setTid(long pid) {
		this.pid = pid;
	}
	@Column(name = "NOTES", length = 256)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "PRODUCT_NAME", length = 64)
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "PRODUCT_CODE", length = 10)
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@Column(name = "FUND_MANAGER", length = 15)
	public String getFundManager() {
		return fundManager;
	}
	
	
	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}
	@Column(name = "FINANCIER", length = 15)
	public String getFinancier() {
		return financier;
	}
	
	public void setFinancier(String financier) {
		this.financier = financier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTIME", length = 7)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}
	
	
	
}
