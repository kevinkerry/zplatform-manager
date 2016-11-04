package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.bean.RaiseTr;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IRaiseTrDao;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;
import com.zlebank.zplatform.manager.service.iface.RaiseTrService;
import com.zlebank.zplatform.member.exception.DataCheckFailedException;
import com.zlebank.zplatform.member.exception.GetAccountFailedException;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.service.EnterpriseTradeService;


public class RaiseTrServiceImpl implements RaiseTrService{
	
	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}


	
	/**
	 * 查询所有的募集款
	 */
	@Override
	public PagResultBean selectAllRaise(FundQueryCondition fundBean) {
		IRaiseTrDao dao = daoContainer.getRaiseTrDao();
		PagResultBean ra = dao.getAllRaise(fundBean);
		return ra;
	}
	/**
	 * 审核
	 * @throws TradeException 
	 * @throws GetAccountFailedException 
	 * @throws DataCheckFailedException 
	 */
	@Override
	public void aduitMes(long tid,Date date,EnterpriseTradeService enterpriseTradeService) throws Exception {
		IRaiseTrDao dao = daoContainer.getRaiseTrDao();
		enterpriseTradeService.raiseMoneyTransferFinish(tid);
		dao.aduitMoney(tid,date);
	}
	
	
	@Override
	public List<RaiseTr> selectByOrder(String orderId) {
		IRaiseTrDao dao = daoContainer.getRaiseTrDao();
		List<RaiseTrModel> ra = dao.getRaiseByOrder(orderId);
		List<RaiseTr> list = new ArrayList<RaiseTr>();
		for (RaiseTrModel raiseTr : ra) {
			RaiseTr fundMerchant = BeanCopyUtil.copyBean(RaiseTr.class, raiseTr);
			list.add(fundMerchant);
		}
		return list;
	}
 
}
