package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.OrganModel;

public interface IOrganService extends IBaseService<OrganModel, Long>{
	public List<?> saveOrgan(OrganModel organ);
	public List<?> updateOrgan(OrganModel organ);
	public Map<String, Object> findOrganByPage(Map<String, Object> variables, int page,
			int rows);
	public long findOrganByPageCount(Map<String, Object> variables);
	public List<?> deleteOrgan(Long organId);
}
