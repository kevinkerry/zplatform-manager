package com.zlebank.zplatform.manager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ICityDAO;
import com.zlebank.zplatform.manager.dao.object.CityModel;

@SuppressWarnings("unchecked")
public class CityDAOImpl extends HibernateDAOImpl<CityModel, Long> implements ICityDAO{
	public List<CityModel> findNotMuniByPid(long pid){
		Criteria criteria = getCurrentSession().createCriteria(CityModel.class);
		criteria.add(Restrictions.eq("PId", pid));
		Long[] municipalityIds = new Long[]{110000L,120000L,310000L,500000L};
		criteria.add(Restrictions.not(Restrictions.in("CId", municipalityIds)));
		return criteria.list();
	}
}
