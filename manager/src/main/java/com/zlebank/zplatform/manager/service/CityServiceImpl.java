package com.zlebank.zplatform.manager.service;

import java.util.List;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.CityModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.ICityService;

public class CityServiceImpl extends BaseServiceImpl<CityModel, Long> implements ICityService{

	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public IBaseDAO<CityModel, Long> getDao() {
		return daoContainer.getCityDAO();
	}
	
	@Override
	public List<CityModel> findNotMuniByPid(long pid){
		return daoContainer.getCityDAO().findNotMuniByPid(pid);
	}
}
