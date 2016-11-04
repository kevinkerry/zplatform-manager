package com.zlebank.zplatform.manager.action.fund;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;

public class PagResultBean {
	
	private List<FundMerchantBeanModel> list;
	
	private List<FundMerchantModel> fundmerchants;
	
	private List<RaiseTrModel> raiseTrModels;
	/**
	 * 行数 
	 * */
	private Long rows;
	public List<FundMerchantBeanModel> getList() {
		return list;
	}
	public void setList(List<FundMerchantBeanModel> list) {
		this.list = list;
	}
	public List<FundMerchantModel> getFundmerchants() {
		return fundmerchants;
	}
	public void setFundmerchants(List<FundMerchantModel> fundmerchants) {
		this.fundmerchants = fundmerchants;
	}
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public List<RaiseTrModel> getRaiseTrModels() {
		return raiseTrModels;
	}
	public void setRaiseTrModels(List<RaiseTrModel> raiseTrModels) {
		this.raiseTrModels = raiseTrModels;
	}
	
	public PagResultBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PagResultBean(List<FundMerchantBeanModel> list, List<FundMerchantModel> fundmerchants,
			List<RaiseTrModel> raiseTrModels, Long rows) {
		super();
		this.list = list;
		this.fundmerchants = fundmerchants;
		this.raiseTrModels = raiseTrModels;
		this.rows = rows;
	}
	
	
}
