package com.zlebank.zplatform.manager.service;

import com.zlebank.zplatform.manager.bean.FinProductBean;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.service.iface.IFinProductService;
import com.zlebank.zplatform.trade.common.page.PageVo;


public class FinProductServiceImpl  implements IFinProductService {
	
	private DAOContainer daoContainer;
	
	

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}


	@Override
	public PageVo findByPage(FinProductBean bean, int pageNo, int pageSize) {
		return this.daoContainer.getFinProductDAO().findByPage(bean, pageNo, pageSize);
	}

	
	
	
	
	


	

	

}
