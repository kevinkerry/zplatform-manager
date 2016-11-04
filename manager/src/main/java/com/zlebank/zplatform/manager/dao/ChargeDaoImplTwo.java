package com.zlebank.zplatform.manager.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ChargeDAO;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;

@Repository
public class ChargeDaoImplTwo extends HibernateBaseDAOImpl<ChargeModel> implements ChargeDAO {

    public Session getSession(){
        return  super.getSession();
    }
}
