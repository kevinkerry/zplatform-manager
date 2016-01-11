package com.zlebank.zplatform.manager.service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.UserFunctModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IUserFunctService;

public class UserFunctServiceImpl extends BaseServiceImpl<UserFunctModel, Long> implements IUserFunctService{

	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<UserFunctModel, Long> getDao() {
		return daoContainer.getUserFunctDAO();
	}
	
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	public void deleteOldFunc(Long userId) {
		getDao().updateByHQL("delete from UserFunctModel u where u.userId = ?",new Object[]{userId});
	}
}
