package com.zlebank.zplatform.manager.service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.ProvinceModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IProvinceService;

public class ProvinceServiceImpl extends BaseServiceImpl<ProvinceModel, Long> implements IProvinceService{
	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public IBaseDAO<ProvinceModel, Long> getDao() {
		
		return daoContainer.getProvinceDAO();
	}
	

}
