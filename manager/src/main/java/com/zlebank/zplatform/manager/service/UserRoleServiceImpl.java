package com.zlebank.zplatform.manager.service;

import java.util.List;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.UserRoleModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IUserRoleService;


public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleModel, Long> implements
		IUserRoleService {

	private DAOContainer daoContainer;

	@Override
	public IBaseDAO<UserRoleModel, Long> getDao() {
		return daoContainer.getUserRoleDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}


	public List<?> getMerchantFirstTrialRoleByUser(Long userId, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> getMerchantSecondTrialRoleByUser(Long userId, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteOldUserRole(Long userId) {
		getDao().updateByHQL("delete from UserRoleModel u where u.userId = ?",new Object[]{userId});
		
	}

}
