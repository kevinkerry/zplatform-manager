package com.zlebank.zplatform.manager.dao.iface;

import java.util.Map;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseRealnameApply;
import com.zlebank.zplatform.trade.common.page.PageVo;

public interface IEnterpriseRealNameDAO  extends BaseDAO<EnterpriseRealnameMode>{
	/***
	 * 查询列表
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageVo findByPage(Map<String, Object> map,
			int pageNo, int pageSize) ;
	/***
	 * 修改状态
	 * @param realNameBean
	 */
	public void updateApplyStatus(EnterpriseRealnameMode realNameBean);
	/****
	 * 查询
	 * @param id
	 * @return
	 */
	public EnterpriseRealnameMode get(Long id);
}
