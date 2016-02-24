package com.zlebank.zplatform.manager.dao;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ITaskDAO;
import com.zlebank.zplatform.manager.dao.object.TaskModel;

public class TaskDAOImpl extends HibernateDAOImpl<TaskModel,Long> implements ITaskDAO{

}
