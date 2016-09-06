package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.trade.common.page.PageVo;

public interface IEnterpriseRealNameService   {
	/***
	 * 
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageVo findByPage(Map<String, Object> map,
			int pageNo, int pageSize) ;

	public void updateApplyStatus(EnterpriseRealnameMode realNameBean);
	
	public EnterpriseRealnameMode get(Long id);
}
