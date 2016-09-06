package com.zlebank.zplatform.manager.service;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.manager.service.iface.IEnterpriseRealNameService;
import com.zlebank.zplatform.trade.common.page.PageVo;


public class EnterpriseRealNameServiceImpl  implements IEnterpriseRealNameService {
	
	private DAOContainer daoContainer;

	@Override
	public PageVo findByPage(Map<String, Object> map, int pageNo, int pageSize) {
		return this.daoContainer.getEnterpriseRealnameDAO().findByPage(map, pageNo, pageSize);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Throwable.class)
	public void updateApplyStatus(EnterpriseRealnameMode realNameBean) {
	    this.daoContainer.getEnterpriseRealnameDAO().update(realNameBean);
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public EnterpriseRealnameMode get(Long id) {
		return this.daoContainer.getEnterpriseRealnameDAO().get(id);
	}
	
	
	
	


	

	

}
