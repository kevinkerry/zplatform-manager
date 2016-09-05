package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.RaiseTr;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IRaiseTrDao;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;
import com.zlebank.zplatform.manager.service.iface.RaiseTrService;

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
	public List<RaiseTr> selectAllRaise() {
		IRaiseTrDao dao = daoContainer.getRaiseTrDao();
		List<RaiseTrModel> ra = dao.getAllRaise();
		List<RaiseTr> list = new ArrayList<RaiseTr>();
		for (RaiseTrModel raiseTr : ra) {
			RaiseTr fundMerchant = BeanCopyUtil.copyBean(RaiseTr.class, raiseTr);
			list.add(fundMerchant);
		}
		return list;
	}
	/**
	 * 审核
	 */
	@Override
	public void aduitMes(String orderId,Date date) {
		IRaiseTrDao dao = daoContainer.getRaiseTrDao();
		dao.aduitMoney(orderId,date);
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
