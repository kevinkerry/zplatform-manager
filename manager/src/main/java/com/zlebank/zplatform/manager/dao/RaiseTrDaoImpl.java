package com.zlebank.zplatform.manager.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
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
	public PagResultBean getAllRaise(FundQueryCondition fundBean) {
		Session session = this.getSession();
		PagResultBean page = page(fundBean,session);
		return page;
	}
	
	/**
	 * 根据订单号查询
	 */
	
	
	/**
	 * 审核
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void aduitMoney(long tid,Date date) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(RaiseTrModel.class).add(Restrictions.eq("tid", tid));
		List<RaiseTrModel> list = criteria.list();
		for (RaiseTrModel raiseTrModel : list) {
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
	
	
	/**
	 * 查询所有，分页查询
	 */
	private PagResultBean page(FundQueryCondition fundBean,Session session){
		PagResultBean pages = new PagResultBean();
		Criteria criteria = session.createCriteria(RaiseTrModel.class);
		List<RaiseTrModel> li = criteria.list();
		pages.setRaiseTrModels(li);
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		pages.setRows(rowCount);
		
		return pages;
	}
	
}
