package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

public class FundAuditBean implements Bean{

	private String mer_id;
	private String batchno; // 批次号
	private String chackBoxData; // 请求的数据
	private String BiaoJi;
 
	public FundAuditBean(String mer_id, String batchno, String chackBoxData, String biaoJi) {
		super();
		this.mer_id = mer_id;
		this.batchno = batchno;
		this.chackBoxData = chackBoxData;
		BiaoJi = biaoJi;
	}

	public FundAuditBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMer_id() {
		return mer_id;
	}

	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getChackBoxData() {
		return chackBoxData;
	}

	public void setChackBoxData(String chackBoxData) {
		this.chackBoxData = chackBoxData;
	}

	public String getBiaoJi() {
		return BiaoJi;
	}

	public void setBiaoJi(String biaoJi) {
		BiaoJi = biaoJi;
	}

	@Override
	public String toString() {
		return "FundAuditBean [mer_id=" + mer_id + ", batchno=" + batchno + ", chackBoxData=" + chackBoxData
				+ ", BiaoJi=" + BiaoJi + "]";
	}
    
}