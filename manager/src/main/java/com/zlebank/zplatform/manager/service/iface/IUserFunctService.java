package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.manager.dao.object.UserFunctModel;

public interface IUserFunctService extends IBaseService<UserFunctModel, Long>{
	public void deleteOldFunc(Long userId);
}
