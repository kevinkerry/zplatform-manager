package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.service.EnterpriseTradeService;

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

	// 对商户数据进行审核
	@Override
	public void getAllDataByTid(List<String> tids, Date date) {
		int starts = 0;
		// 遍历数组，拿到商户表中的tid
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		for (String tid : tids) {
			// 拿到明细表中的数据
			List<FundMerchantModel> allFundMerchantModel = dao.getAllFundMerchantModel(tid);
			int size = allFundMerchantModel.size();
			// 对明细表中的starts做判断，这个tid对应的所有明细都为已经审核则赋值“00”
			for (FundMerchantModel BeanModel : allFundMerchantModel) {
				if ("00".equals(BeanModel.getStatus())) {
					starts += 1;
				}
			}
			if (size == starts) {
				dao.updateStartByid(tid, date, "00");
			} else if (size > starts) {
				dao.updateStartByid(tid, date, "00");
				dao.updateStartBybeans(allFundMerchantModel, date);
			}
		}

	}
	//对明细数据进行审核
	@Override
	public void auditBytid(List<String> tids, Date date,EnterpriseTradeService enterpriseTradeService) throws Exception {
		//由明细数据的tids获取到对应的商户信息
		int starts = 0;
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		//根据详细信息的tid获取到所对应的商户model
		for (String tid : tids) {
			enterpriseTradeService.merchReimbusementFinish(Long.valueOf(tid));
		}
		FundMerchantBeanModel shangHuModel = dao.getfundMerModel(tids.get(0));
		dao.updateStarts(tids, date); 
		//将所有的明细审核完成之后调用商户审核，对商户进行重新审核
		List<String> shangHutid = new ArrayList<String>();
		shangHutid.add(shangHuModel.getTid().toString());
		getAllDataByTid2(shangHutid,date);
		
	}
	
	private void getAllDataByTid2(List<String> tids, Date date) {
		int starts = 0;
		// 遍历数组，拿到商户表中的tid
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		for (String tid : tids) {
			// 拿到明细表中的数据
			List<FundMerchantModel> allFundMerchantModel = dao.getAllFundMerchantModel(tid);
			int size = allFundMerchantModel.size();
			// 对明细表中的starts做判断，这个tid对应的所有明细都为已经审核则赋值“00”
			for (FundMerchantModel BeanModel : allFundMerchantModel) {
				if ("00".equals(BeanModel.getStatus())) {
					starts += 1;
				}
			}
			if (size == starts) {
				dao.updateStartByid(tid, date, "00");
			} else if (size > starts) {
				dao.updateStartByid(tid, date, "02");
				
			}
		}

	}
	/**
	 * 按照订单号查询
	 */
	@Override
	public PagResultBean selectByOrder(FundQueryCondition fundBean) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		PagResultBean list = dao.selectByOrder(fundBean);
		return list;
	}
	
	/**
	 * 根据时间查询
	 */
	@Override
	public PagResultBean selectByDate(FundQueryCondition fundBean) {
		IfundMerchantDao dao = daoContainer.getFundMerchantDao();
		Long rows;
		PagResultBean returnBean = null;
		String beginDate = fundBean.getBeginDate();
		String endDate = fundBean.getEndDate();
		List<FundMerchantBeanModel> list = new ArrayList<FundMerchantBeanModel>();
		if(beginDate.trim().length()>0){
			if(endDate.trim().length()>0){
				//区间查询
				returnBean = dao.SelectAllByDate(beginDate,endDate,fundBean);
			}else{
				//执行确定开始时间查询
				returnBean = dao.selectAllBybeginDate(beginDate,fundBean);
			}
		}else if(endDate.trim().length()>0){
				//执行结束时间查询
			returnBean = dao.selectByEndDate(endDate,fundBean);
		}
		return returnBean;
	}
}
