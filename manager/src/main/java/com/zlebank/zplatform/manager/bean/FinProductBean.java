package com.zlebank.zplatform.manager.bean;

import java.util.Date;

public class FinProductBean {
	//产品代码
	private String productCode;
	//产品名称
	private String productName;
	//资管人
	private String fundManager;
	//融资人
	private String financier;
	//资管人
	private String fundManagerName;
	//融资人
	private String financierName;
	//主键
	private String pid;
	private String inTime;
	private String notes;
	private String remarks;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFundManager() {
		return fundManager;
	}
	public void setFundManager(String fundManager) {
		this.fundManager = fundManager;
	}
	public String getFinancier() {
		return financier;
	}
	public void setFinancier(String financier) {
		this.financier = financier;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getFundManagerName() {
		return fundManagerName;
	}
	public void setFundManagerName(String fundManagerName) {
		this.fundManagerName = fundManagerName;
	}
	public String getFinancierName() {
		return financierName;
	}
	public void setFinancierName(String financierName) {
		this.financierName = financierName;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
