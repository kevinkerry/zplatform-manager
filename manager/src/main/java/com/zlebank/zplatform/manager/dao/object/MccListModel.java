package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_mcc_list")
public class MccListModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491125140848941704L;
	private String mccList;
	private String mccCont;
	private String mccType;
	private String mccTrade;
	private String industry;
	private String mccClass;
	private String mcc;
	private String status;
	
	@Id
	public String getMccList() {
		return mccList;
	}

	public void setMccList(String mccList) {
		this.mccList = mccList;
	}
	
	@Column(name="MCCCONT",length=128)
	public String getMccCont() {
		return mccCont;
	}

	public void setMccCont(String mccCont) {
		this.mccCont = mccCont;
	}
	
	@Column(name="MCCTYPE",length=64)
	public String getMccType() {
		return mccType;
	}

	public void setMccType(String mccType) {
		this.mccType = mccType;
	}
	
	@Column(name="MCCTRADE",length=32)
	public String getMccTrade() {
		return mccTrade;
	}

	public void setMccTrade(String mccTrade) {
		this.mccTrade = mccTrade;
	}
	
	@Column(name="INDUSTRY",length=16)
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Column(name="MCCCLASS",length=16)
	public String getMccClass() {
		return mccClass;
	}

	public void setMccClass(String mccClass) {
		this.mccClass = mccClass;
	}
	
	@Column(name="MCC",length=4)
	public String getMcc() {
		return mcc;
	}
	
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Column(name="STATUS",length=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
