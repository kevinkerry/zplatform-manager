package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.manager.dao.object.RoleFunctModel;



public interface IRoleFunctService extends IBaseService<RoleFunctModel, Long>{
	public void deleteRoleFunction(Long roleId);
}
