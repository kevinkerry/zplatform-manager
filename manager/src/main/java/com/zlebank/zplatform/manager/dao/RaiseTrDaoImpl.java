package com.zlebank.zplatform.manager.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IRaiseTrDao;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;

@SuppressWarnings("unchecked")
public class RaiseTrDaoImpl extends HibernateBaseDAOImpl<RaiseTrModel>
											implements IRaiseTrDao{
	
	/**
	 * 查询所有的募集款划拨信息
	 */
	@Override
	public List<RaiseTrModel> getAllRaise() {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RaiseTrModel.class);
		List<RaiseTrModel> li = criteria.list();
		return li;
	}
	
	/**
	 * 根据订单号查询
	 */
	
	
	/**
	 * 审核
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void aduitMoney(String orderid,Date date) {
		List<RaiseTrModel> raise = getRaiseByOrder(orderid);
		Session session = this.getSession();
		for (RaiseTrModel raiseTrModel : raise) {
			if(raiseTrModel.getStexaTime()!=null){
				raiseTrModel.setCvlexaTime(date);
			}else{
				raiseTrModel.setStexaTime(date);
			}
			raiseTrModel.setStatus("00");
			session.update(raiseTrModel);
		}
		 
		
	}
	/**
	 * 根据订单号进行查询
	 */
	@Override
	public List<RaiseTrModel> getRaiseByOrder(String orderid) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RaiseTrModel.class).add(Restrictions.eq("orderid", orderid));
		 List<RaiseTrModel> list = criteria.list();
		return list;
	}
	
	
	
}
