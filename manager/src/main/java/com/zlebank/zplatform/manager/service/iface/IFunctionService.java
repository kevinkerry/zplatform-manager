package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.FunctionModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;

public interface IFunctionService extends IBaseService<FunctionModel, Long>{
	public List<?> findAllFuntion(UserModel loginuser);
	public List<?> findLoginFuntion(UserModel loginuser);
	public List<?> findFunction();
	public List<?> existUserAndRoleFunct(Long userId,Long fid);
}
