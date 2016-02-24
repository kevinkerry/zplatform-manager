package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.CityModel;

public interface ICityService extends IBaseService<CityModel, Long>{
	public List<CityModel> findNotMuniByPid(long pid);
}
