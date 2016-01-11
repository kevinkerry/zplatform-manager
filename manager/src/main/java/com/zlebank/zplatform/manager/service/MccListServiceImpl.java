package com.zlebank.zplatform.manager.service;

import java.util.List;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.MccListModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IMccListService;

public class MccListServiceImpl extends BaseServiceImpl<MccListModel, String>
		implements IMccListService {
	
	private DAOContainer daoContainer;

	@Override
	public IBaseDAO<MccListModel, String> getDao() {
		return daoContainer.getMccListDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	
	public List<MccListModel> findAll(){
		return daoContainer.getMccListDAO().findAll();
	}
}
