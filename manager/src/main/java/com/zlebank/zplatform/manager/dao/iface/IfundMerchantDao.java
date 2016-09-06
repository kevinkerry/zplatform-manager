package com.zlebank.zplatform.manager.dao.iface;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;

public interface IfundMerchantDao extends BaseDAO<FundMerchantModel>{
	/**
	 * 获取商户还款详细信息
	 * @return
	 */
	public PagResultBean getMerchants(FundQueryCondition fundBean);
	/**
	 * 通过mer_id获取到所有的商户还款信息.
	 * @return
	 */
	public FundMerchantModel getMerchantById(String id);
	
	public IfundMerchantDao getFundMerchantDao();
	/**
	 * 获取所有的商户信息
	 * @return
	 */
	public PagResultBean getMerchantsBean(FundQueryCondition bean);
	/**
	 * 按条件查询，按照批次号进行按条件查询
	 * @param batchno
	 * @return
	 */
	public List<FundMerchantBeanModel> getMerchantsBybatchno(String batchno);
	/**
	 * 根据订单号进行审核
	 * @param arr
	 */
	public void updateStartByOrder(List<String> arr,Date date);
	/**
	 * 根据批次号进行商户审核
	 * @param arr
	 */
	public void updateStartByPc(List<String> arr,Date date);
	/**
	 * 拒绝
	 * @param arr
	 */
	public void updateStartNoByOrder(List<String> arr); 
	public void updateStartNoByPc(List<String> arr);
	public List<FundMerchantBeanModel> getMerchantsByStatus(String status);
	
}
