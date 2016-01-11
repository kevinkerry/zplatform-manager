package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.CityModel;

public interface ICityDAO extends IBaseDAO<CityModel, Long>{
	/**
	 * 按照省/直辖市 id查找非直辖市的所有城市
	 * @param pid
	 * @return
	 */
	public List<CityModel> findNotMuniByPid(long pid);
}
