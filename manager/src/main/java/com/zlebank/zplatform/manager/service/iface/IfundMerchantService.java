package com.zlebank.zplatform.manager.service.iface;


import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.bean.FundMerchant;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.trade.bean.InsteadPayBatchQuery;

public interface IfundMerchantService {
	
	/**
	 * 查询商户详细还款信息
	 */
	public List<FundMerchant> getAllMerchant(String bATCH_NO);
	
	/**
	 * 查询分页信息
	 */
	public void queryPaging();
		
	/**
	 * 按照条件查询
	 */
	public FundMerchant getMerchant(String id);
	
	/**
	 * 查询所有商户信息
	 */
	public List<FundMerchantBean> getAllMerchantBean();
	/**
	 * 查询商户信息，通过批次号
	 * @param batchno
	 * @return
	 */
	public List<FundMerchantBean> getmerBybatchno(String batchno);
	/**
	 * 审核明细
	 * @param arr
	 */
	public void auditByOrder(List<String> arr,Date date);
	/**
	 * 根据商户审核
	 * @param arr
	 */
	public void auditByPc(List<String> arr,Date data);
     
	public void auditNoByOrder(List<String> arr);

	public void auditNoByPc(List<String> arr);

	public PagedResult<FundMerchantBean> queryPaged(int page, int pageSize, InsteadPayBatchQuery insteadPayBatchQuery);
	public PagedResult<FundMerchant> queryPaged1(int page, int pageSize, InsteadPayBatchQuery insteadPayBatchQuery);

	public List<FundMerchantBean> getmerByStatus(String status);
}
 