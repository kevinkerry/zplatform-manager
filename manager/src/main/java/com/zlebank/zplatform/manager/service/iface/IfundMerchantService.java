package com.zlebank.zplatform.manager.service.iface;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundMerchant;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.trade.service.EnterpriseTradeService;

public interface IfundMerchantService {

	/**
	 * 查询商户详细还款信息
	 */
	public PagResultBean getAllMerchant(FundQueryCondition funBean);

	/**
	 * 按照条件查询
	 */
	public FundMerchant getMerchant(String id);

	/**
	 * 查询所有商户信息
	 */
	public PagResultBean getAllMerchantBean(FundQueryCondition fundBean);

	/**
	 * 查询商户信息，通过批次号
	 * 
	 * @param batchno
	 * @return
	 */
	public List<FundMerchantBean> getmerBybatchno(String batchno);

	public void auditNoByOrder(List<String> arr);

	public void auditNoByPc(List<String> arr);

	public List<FundMerchantBean> getmerByStatus(String status);

	// =====================================================================
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param pageSize
	 * @param fundBean
	 * @return
	 */
	public PagedResult<FundMerchantBean> queryPaged(int page, int pageSize, FundQueryCondition fundBean);

	/**
	 * 商户审核
	 * 
	 * @param tids
	 * @param date
	 */
	public void getAllDataByTid(List<String> tids, Date date);

	/**
	 * 明细审核
	 * 
	 * @param tids
	 * @param date
	 * @throws Exception  
	 */
	public void auditBytid(List<String> tids, Date date, EnterpriseTradeService enterpriseTradeService) throws Exception ;
    /**
     * 订单号查询
     * @param fundBean
     */
	public PagResultBean selectByOrder(FundQueryCondition fundBean);

	public PagResultBean selectByDate(FundQueryCondition fundBean);

}
