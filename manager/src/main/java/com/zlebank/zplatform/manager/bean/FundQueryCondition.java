package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;

public class FundQueryCondition implements Bean{
	
	private Integer page;
	private Integer pageSize;
	private String batchNo;
	private String merId;
	private String orderId;
	private String beginDate;
	private String endDate; 
	
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public FundQueryCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundQueryCondition(String batchNo, String merId) {
		super();
		this.batchNo = batchNo;
		this.merId = merId;
	}
	@Override
	public String toString() {
		return "FundQueryCondition [page=" + page + ", pageSize=" + pageSize + ", batchNo=" + batchNo + ", merId="
				+ merId + "]";
	}
	public FundQueryCondition(Integer page, Integer pageSize, String batchNo, String merId) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.batchNo = batchNo;
		this.merId = merId;
	}
	public FundQueryCondition(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	
}
