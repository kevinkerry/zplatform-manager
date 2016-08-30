package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseRealnameApply;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.model.CashBankModel;
import com.zlebank.zplatform.trade.service.IBaseService;

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
