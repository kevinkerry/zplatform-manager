package com.zlebank.zplatform.manager.dao.iface;

import java.util.Map;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.bean.FinProductBean;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.manager.dao.object.scan.FinProductMode;
import com.zlebank.zplatform.trade.common.page.PageVo;

public interface IFinProductDAO  extends BaseDAO<FinProductMode>{
	/***
	 * 查询列表
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageVo findByPage(FinProductBean bean,
			int pageNo, int pageSize) ;
	
}
