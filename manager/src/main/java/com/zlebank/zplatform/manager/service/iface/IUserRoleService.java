package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.manager.dao.object.UserRoleModel;

public interface IUserRoleService extends IBaseService<UserRoleModel, Long>{
	public void deleteOldUserRole(Long userId);
}
