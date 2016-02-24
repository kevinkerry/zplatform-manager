package com.zlebank.zplatform.manager.dao;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IUserDAO;
import com.zlebank.zplatform.manager.dao.object.UserModel;


public class UserDAOImpl extends HibernateDAOImpl<UserModel,Long> implements IUserDAO{

}
