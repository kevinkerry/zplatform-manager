package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundMerchant;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.iface.IfundMerchantDao;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IfundMerchantService;
import com.zlebank.zplatform.trade.bean.InsteadPayBatchQuery;
import com.zlebank.zplatform.trade.model.PojoTranData;

/**
 * 基金商户service
 * 
 * @author lyp
 *
 */

public class FundMerchantImpl  extends BaseServiceImpl<PojoTranData, Long>
										implements IfundMerchantService {

	
	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public FundMerchant getMerchant(String id) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		FundMerchantModel merchant = dao.getMerchantById(id);
		FundMerchant fundMerchant = BeanCopyUtil.copyBean(FundMerchant.class, merchant);
		return fundMerchant;
	}
	/**
	 * 查询所有商户信息
	 */
	@Override
	public PagResultBean getAllMerchantBean(FundQueryCondition fundBean) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		PagResultBean merchants = dao.getMerchantsBean(fundBean);
		
		return merchants;
	}
	@Override
	public PagResultBean getAllMerchant(FundQueryCondition fundBean) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		 PagResultBean merchants = dao.getMerchants(fundBean);
		return merchants;
	}
	
	/**
	 * 按条件查询，按照批次号进行按条件查询
	 */
	@Override
	public List<FundMerchantBean> getmerBybatchno(String batchno) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		List<FundMerchantBeanModel> merchants = dao.getMerchantsBybatchno(batchno);
		List<FundMerchantBean> list = new ArrayList<FundMerchantBean>();

		for (FundMerchantBeanModel mr : merchants) {
			FundMerchantBean fundMerchant = BeanCopyUtil.copyBean(FundMerchantBean.class, mr);
			list.add(fundMerchant);
		}
		return list;
	}
	/**
	 * 审核明细
	 */
	@Override
	public void auditByOrder(List<String> arr,Date date) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		dao.updateStartByOrder(arr,date);
	}
	/**
	 * 根据批次号进行商户审核
	 */
	@Override
	public void auditByPc(List<String> arr,Date date) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		dao.updateStartByPc(arr,date);
	}
	@Override
	public void auditNoByOrder(List<String> arr) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		dao.updateStartNoByOrder(arr);
		
	}
	@Override
	public void auditNoByPc(List<String> arr) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		dao.updateStartNoByPc(arr);
		
	}
	
	@Override
	public IBaseDAO<PojoTranData, Long> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<FundMerchantBean> getmerByStatus(String status) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		List<FundMerchantBeanModel> merchants = dao.getMerchantsByStatus(status);
		List<FundMerchantBean> list = new ArrayList<FundMerchantBean>();

		for (FundMerchantBeanModel mr : merchants) {
			FundMerchantBean fundMerchant = BeanCopyUtil.copyBean(FundMerchantBean.class, mr);
			list.add(fundMerchant);
		}
		return list;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public PagedResult<FundMerchantBean> queryPaged(int page, int pageSize, FundQueryCondition fundBean) {
		
		return null;
	}

}
