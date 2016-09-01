package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.manager.bean.FinProductBean;
import com.zlebank.zplatform.trade.common.page.PageVo;

public interface IFinProductService {
	 
	/* 
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageVo findByPage(FinProductBean bean,
			int pageNo, int pageSize) ;
}
