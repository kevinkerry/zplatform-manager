package com.zlebank.zplatform.manager.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IMccListDAO;
import com.zlebank.zplatform.manager.dao.object.MccListModel;

public class MccListDAOImpl extends HibernateDAOImpl<MccListModel,String> implements IMccListDAO{
	
	@SuppressWarnings("unchecked")
	public List<MccListModel> findAll(){
		Criteria criteria = getCurrentSession().createCriteria(MccListModel.class);
		return criteria.list();
	}
}
