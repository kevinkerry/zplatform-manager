package com.zlebank.zplatform.manager.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.BusiacctDAO;
import com.zlebank.zplatform.manager.dao.object.BusiacctModel;

@Repository("busiacctDAO")
public class BusiacctDAOImpl extends HibernateBaseDAOImpl<BusiacctModel> implements BusiacctDAO{
    public Session getSession(){
        return  super.getSession();
    }
}
