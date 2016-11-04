package com.zlebank.zplatform.manager.dao.iface;

import org.hibernate.Session;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.AccAcctModel;

public interface AccAcctDAO extends BaseDAO<AccAcctModel>{
    Session getSession();
}
