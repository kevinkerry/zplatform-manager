package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IMccListService;

public class MccAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899468027712171840L;
	
	private ServiceContainer serviceContainer;
	
	public String queryMccList(){
		IMccListService mccListService = serviceContainer.getMccListService();
		try {
			json_encode(mccListService.findAll());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}
}
