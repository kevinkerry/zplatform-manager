package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.RoleModel;

public interface IRoleService extends IBaseService<RoleModel, Long>{
	public List<?> saveRole(RoleModel role);
	public List<?> updateRole(RoleModel role);
	public List<?> deleteRole(Long roleId);
	public Map<String, Object> findRoleByPage(Map<String, Object> variables, int page,
			int rows);
	public long findRoleByPageCount(Map<String, Object> variables);
	
}
