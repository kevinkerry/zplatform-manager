package com.zlebank.zplatform.manager.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.AccAcctDAO;
import com.zlebank.zplatform.manager.dao.object.AccAcctModel;

@Repository("accAcctDAO")
public class AccAcctDAOImpl extends HibernateBaseDAOImpl<AccAcctModel> implements AccAcctDAO {
    public Session getSession(){
        return  super.getSession();
    }

}
