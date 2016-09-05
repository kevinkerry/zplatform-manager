package com.zlebank.zplatform.manager.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IfundMerchantDao;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;
@SuppressWarnings("unchecked")
public class FundMerchantDaoImpl  extends HibernateBaseDAOImpl<FundMerchantModel>
								implements IfundMerchantDao{

	/**
	 * 查询商户还款详情信息
	 */
	@Override
	public List<FundMerchantModel> getMerchants(String bATCH_NO) {
		Session session = this.getSession();
		List<FundMerchantModel> list =null;
			Criteria criteria = session.createCriteria(FundMerchantModel.class).add(Restrictions.eq("batchno", bATCH_NO));
			list = criteria.list();
	    	 return list;
	}

	@Override
	public IfundMerchantDao getFundMerchantDao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FundMerchantModel getMerchantById(String id) {
		 Criteria criteria= this.getSession().createCriteria(FundMerchantModel.class);
		 FundMerchantModel model =  (FundMerchantModel) criteria.add(Restrictions.eqOrIsNull("mer_id", id));
		return model;
	}
	
	/**
	 * 查询所有商户信息
	 */
	@Override
	public List<FundMerchantBeanModel> getMerchantsBean() {
		Session session = this.getSession();
		List<FundMerchantBeanModel> list =null;
			Criteria criteria = session.createCriteria(FundMerchantBeanModel.class);
			list = criteria.list();
	    	 return list;
	}

	/**
	 * 按条件查询，按照批次号查询
	 */
	@Override
	public List<FundMerchantBeanModel> getMerchantsBybatchno(String batchno) {
		Session session = this.getSession();
		List<FundMerchantBeanModel> list =null;
			Criteria criteria = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("batchNo", batchno));
			list = criteria.list();
	    	 return list;
	}
	/**
	 * 审核明细
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateStartByOrder(List<String> arr,Date date) {
		Session session = this.getSession();
		try {
		for (String str : arr) {
			//查询出来所有信息
			Criteria cr = session.createCriteria(FundMerchantModel.class).add(Restrictions.eq("orderId", str));
			FundMerchantModel model = (FundMerchantModel)cr.uniqueResult();;
			BigDecimal tid = model.getTid();
			model.setStatus("00");
			if(model.getStexatime()!=null){
				model.setCvlexatime(date);
			}else{
				model.setStexatime(date);
			}
			session.update(model);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 批次号审核商户
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void updateStartByPc(List<String> arr ,Date date) {
		Session session = this.getSession();
		
		for (String str : arr) {
			//查询出来所有信息
			Criteria cr = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("batchNo", str));
			//List li = cr.list();
			FundMerchantBeanModel model = (FundMerchantBeanModel) cr.uniqueResult();
			//Integer tid = model.getTid();
			//session.clear();
			/*FundMerchantBeanModel m2 = new FundMerchantBeanModel(model.getBatch_no(),model.getMer_id(),
					model.getProdcutcode(),model.getTxn_time(),model.getTotal_qty(),model.getTotal_amt(),
					"00",model.getInuser(),model.getIntime(),model.getUpuser(),model.getUptime(),
					model.getNotes(),model.getType(),model.getApprove_count(),model.getApprove_amt(),
					model.getUnapprove_count(),model.getUnapprove_amt(),model.getRefuse_count(),model.getRefuse_amt(),
					model.getApply_time(),model.getApprove_finish_time(),model.getFinish_time(),model.getFile_path(),
					model.getOriginal_file_name(),model.getTn(),model.getCoopinsticode());*/
			model.setStatus("00");
			//m2.setTid(tid);
			//session.update(m2);
			session.update(model);
		}
		
		
	}
//	拒绝
	@Override
	public void updateStartNoByOrder(List<String> arr) {
		Session session = this.getSession();
		session.beginTransaction();
		try {
		for (String str : arr) {
			
		}
		session.getTransaction().commit();
		} catch (Exception e) {
			//事务回滚
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateStartNoByPc(List<String> arr) {
		Session session = this.getSession();
		session.beginTransaction();
		try {
		for (String str : arr) {
			//查询出来所有信息
			Criteria cr = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("batchNo", str));
			List li = cr.list();
			FundMerchantBeanModel model = (FundMerchantBeanModel) li.get(0);
			session.clear();
			
			
		}
		session.getTransaction().commit();
		} catch (Exception e) {
			//事务回滚
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<FundMerchantBeanModel> getMerchantsByStatus(String status) {
		Session session = this.getSession();
		List<FundMerchantBeanModel> list =null;
			Criteria criteria = session.createCriteria(FundMerchantBeanModel.class).add(Restrictions.eq("status", status));
			list = criteria.list();
	    	 return list;
	}	
	
}
