package com.zlebank.zplatform.manager.dao;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IndustryAccountGroupDAO;
import com.zlebank.zplatform.manager.dao.object.IndustryGroupModel;

@SuppressWarnings("unchecked")
@Repository("industryAccountGroupDAO")
public class IndustryAccountGroupDaoImpl extends HibernateDAOImpl<IndustryGroupModel,String> implements IndustryAccountGroupDAO{

}
