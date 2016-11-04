package com.zlebank.zplatform.manager.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.dao.iface.IfundMerchantDao;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;

@SuppressWarnings("unchecked")
public class FundMerchantDaoImpl extends HibernateBaseDAOImpl<FundMerchantModel> implements IfundMerchantDao {

	/**
	 * 查询商户还款详情信息
	 */
	@Override
	public PagResultBean getMerchants(FundQueryCondition fundBean) {
		Session session = this.getSession();
		// 调分页
		PagResultBean pagesMingXi = pagesMingXi(fundBean, session);
		return pagesMingXi;
	}

	@Override
	public IfundMerchantDao getFundMerchantDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FundMerchantModel getMerchantById(String id) {
		Criteria criteria = this.getSession().createCriteria(FundMerchantModel.class);
		FundMerchantModel model = (FundMerchantModel) criteria.add(Restrictions.eqOrIsNull("mer_id", id));
		return model;
	}

	/**
	 * 查询所有商户信息
	 */
	@Override
	public PagResultBean getMerchantsBean(FundQueryCondition fundBean) {
		// 分页查询
		Session session = this.getSession();
		PagResultBean pages = pages(fundBean, session);
		// List<FundMerchantBeanModel> list = pages.getList();
		return pages;
	}

	/**
	 * 按条件查询，按照批次号查询
	 */
	@Override
	public List<FundMerchantBeanModel> getMerchantsBybatchno(String batchno) {
		Session session = this.getSession();
		List<FundMerchantBeanModel> list = null;
		Criteria criteria = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.eq("batchNo", batchno));
		list = criteria.list();
		return list;
	}

	@Override
	public void updateStartNoByPc(List<String> arr) {
		Session session = this.getSession();
		session.beginTransaction();
		try {
			for (String str : arr) {
				// 查询出来所有信息
				Criteria cr = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("batchNo", str));
				List li = cr.list();
				FundMerchantBeanModel model = (FundMerchantBeanModel) li.get(0);
				session.clear();

			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// 事务回滚
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}

	@Override
	public List<FundMerchantBeanModel> getMerchantsByStatus(String status) {
		Session session = this.getSession();
		List<FundMerchantBeanModel> list = null;
		Criteria criteria = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("status", status));
		list = criteria.list();
		return list;
	}

	// 商户信息分页查询
	private PagResultBean pages(FundQueryCondition fundBean, Session session) {
		PagResultBean bean = new PagResultBean();
		Criteria criteria = session.createCriteria(FundMerchantBeanModel.class);
		List<FundMerchantBeanModel> list = criteria.list();
		bean.setList(list);
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		bean.setRows(rowCount);
		return bean;
	}

	// 商户信息分页查询
	private PagResultBean pagesMingXi(FundQueryCondition fundBean, Session session) {
		PagResultBean bean = new PagResultBean();
		Criteria criteria = session.createCriteria(FundMerchantModel.class)
				.add(Restrictions.eq("batchno", fundBean.getBatchNo()));
		List<FundMerchantModel> list = criteria.list();
		bean.setFundmerchants(list);
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		bean.setRows(rowCount);
		return bean;
	}

	@Override
	public void updateStartNoByOrder(List<String> arr) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FundMerchantModel> getAllFundMerchantModel(String tid) {
		// 传入的是商户表的tid获取通过tid明细表中的数据
		Session session = this.getSession();
		Criteria cr = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.eq("tid", new BigDecimal(tid)));
		FundMerchantBeanModel model = (FundMerchantBeanModel) cr.uniqueResult();
		Criteria cr1 = session.createCriteria(FundMerchantModel.class)
				.add(Restrictions.eq("batchno", model.getBatchNo()));
		return cr1.list();
	}

	// 传入明细的tid 获取到商户的model
	@Override
	public FundMerchantBeanModel getfundMerModel(String tid) {
		Session session = this.getSession();
		Criteria cr1 = session.createCriteria(FundMerchantModel.class).add(Restrictions.eq("tid", new BigDecimal(tid)));
		FundMerchantModel model = (FundMerchantModel) cr1.uniqueResult();
		Criteria cr = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.eq("batchNo", model.getBatchno()));
		FundMerchantBeanModel model1 = (FundMerchantBeanModel) cr.uniqueResult();
		return model1;
	}

	/**
	 * 审核商户
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateStartByid(String tid, Date date, String starts) {
		Session session = this.getSession();
		// 查询出来所有信息
		Criteria cr = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.eq("tid", new BigDecimal(tid)));
		FundMerchantBeanModel model = (FundMerchantBeanModel) cr.uniqueResult();
		model.setStatus(starts);
		session.update(model);

	}

	/**
	 * 审核明细
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateStartBybeans(List<FundMerchantModel> list, Date date) {
		Session session = this.getSession();
		for (FundMerchantModel BeanModel : list) {
			// 查询出来所有信息
			Criteria cr = session.createCriteria(FundMerchantModel.class)
					.add(Restrictions.eq("tid", BeanModel.getTid()));
			FundMerchantModel model = (FundMerchantModel) cr.uniqueResult();
			model.setStatus("00");
			if (model.getStexatime() != null) {
				model.setCvlexatime(date);
			} else {
				model.setStexatime(date);
			}
			session.update(model);
		}
	}

	/**
	 * 审核明细
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void updateStarts(List<String> tids, Date date) {
		Session session = this.getSession();
		for (String tid : tids) {
			// 查询出来所有信息
			Criteria cr = session.createCriteria(FundMerchantModel.class)
					.add(Restrictions.eq("tid", new BigDecimal(tid)));
			FundMerchantModel model = (FundMerchantModel) cr.uniqueResult();
			model.setStatus("00");
			if (model.getStexatime() != null) {
				model.setCvlexatime(date);
			} else {
				model.setStexatime(date);
			}
			session.update(model);
		}
	}

	/**
	 * 订单号查询
	 */
	@Override
	public PagResultBean selectByOrder(FundQueryCondition fundBean) {
		Session session = this.getSession();
		PagResultBean bean = new PagResultBean();
		Criteria criteria = session.createCriteria(FundMerchantModel.class)
				.add(Restrictions.eq("orderId", fundBean.getOrderId()));
		List<FundMerchantModel> list = criteria.list();
		bean.setFundmerchants(list);
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		bean.setRows(rowCount);
		return bean;
	}

	/**
	 * 按照时间查询
	 */
	@Override
	public PagResultBean SelectAllByDate(String beginDate, String endDate, FundQueryCondition fundBean) {
		Session session = this.getSession();
		PagResultBean bean = new PagResultBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(beginDate.replace("-", "/"));
			end = sdf.parse(endDate.replace("-", "/")); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Criteria criteria = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.between("intime", begin, end));
		bean.setList(criteria.list()); 
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		bean.setRows(rowCount);
		return bean;
	}

	@Override
	public PagResultBean selectAllBybeginDate(String beginDate, FundQueryCondition fundBean) {
		Session session = this.getSession();
		PagResultBean bean = new PagResultBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date begin = null;
		try {
			begin = sdf.parse(beginDate.replace("-", "/"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Criteria criteria = session.createCriteria(FundMerchantBeanModel.class)
				.add(Restrictions.between("intime", begin, new Date()));
		bean.setList(criteria.list()); 
		// 获取总行数
		Long rowCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		// 设置起始页
		criteria.setFirstResult(fundBean.getPage());
		// 页大小
		criteria.setMaxResults(fundBean.getPageSize());
		bean.setRows(rowCount);
		return bean;
	}

	@Override
	public PagResultBean selectByEndDate(String endDate, FundQueryCondition fundBean) {
		 String begin = "1000/01/01";
		 PagResultBean bean = SelectAllByDate(begin,endDate,fundBean);
		 return bean;
	}

}
