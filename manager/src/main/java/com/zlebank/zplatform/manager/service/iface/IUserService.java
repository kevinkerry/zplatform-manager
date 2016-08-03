package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.UserModel;


public interface IUserService extends IBaseService<UserModel, Long>{
	public List<?> saveUser(UserModel user);
	public List<?> updateUser(UserModel user);
	public Map<String, Object> findUserByPage(Map<String, Object> variables, int page,
			int rows);
	public long findUserByPageCount(Map<String, Object> variables);
}
