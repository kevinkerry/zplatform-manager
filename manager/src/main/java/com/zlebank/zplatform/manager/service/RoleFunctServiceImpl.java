package com.zlebank.zplatform.manager.service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.RoleFunctModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRoleFunctService;

public class RoleFunctServiceImpl extends BaseServiceImpl<RoleFunctModel, Long> implements IRoleFunctService{

	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<RoleFunctModel, Long> getDao() {
		// TODO Auto-generated method stub
		return daoContainer.getRoleFunctDAO();
	}
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	public void deleteRoleFunction(Long roleId) {
		getDao().updateByHQL("delete from RoleFunctModel rf where rf.roleId = ?",new Object[]{roleId});
		
	}
}
