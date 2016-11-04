package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.scan.MerchConfigModel;

public interface IMerchConfigService  extends IBaseService<MerchConfigModel, Long> {
	/****
	 * 根据类型查询商户配置信息
	 * @param memberId
	 * @param typeId
	 * @return
	 */
	public List<MerchConfigModel> queryList(String memberId, String typeId);

}
