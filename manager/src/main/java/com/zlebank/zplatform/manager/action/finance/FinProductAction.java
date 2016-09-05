package com.zlebank.zplatform.manager.action.finance;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FinProductBean;
import com.zlebank.zplatform.manager.bean.FinRealNameBean;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.manager.dao.object.scan.FinProductMode;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.trade.common.page.PageVo;

public class FinProductAction extends BaseAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(FinProductAction.class);
 
	private ServiceContainer serviceContainer;
	//实体
	private FinProductBean bean;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	public String show(){
		return SUCCESS;
	}
	
	
	/****
	 * 列表查询
	 * @return
	 */
	public String query(){
		Map<String,Object> map =new HashMap<String,Object>();
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			if(bean ==null){
				bean = new FinProductBean();
			} 
			PageVo<FinProductBean> pageVo =serviceContainer.getFinProductService().findByPage(bean, getPage(), getRows());
			map.put("total", pageVo.getTotal());
			map.put("rows", pageVo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			map.put("total", null);
			map.put("rows", null);
		}
		
		json_encode(map);
		return SUCCESS;
	}


	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}


	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}


	public FinProductBean getBean() {
		return bean;
	}


	public void setBean(FinProductBean bean) {
		this.bean = bean;
	}
	
	
	
}
