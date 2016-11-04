package com.zlebank.zplatform.manager.dao.iface;

import org.hibernate.Session;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.BusiacctModel;

public interface BusiacctDAO extends BaseDAO<BusiacctModel>{

    Session getSession();
}
