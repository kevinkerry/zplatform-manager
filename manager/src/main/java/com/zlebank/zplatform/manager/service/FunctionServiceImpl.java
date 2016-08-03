package com.zlebank.zplatform.manager.service;

import java.util.List;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.FunctionModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IFunctionService;

public class FunctionServiceImpl extends BaseServiceImpl<FunctionModel, Long> implements IFunctionService{

	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<FunctionModel, Long> getDao() {
		
		return daoContainer.getFunctionDAO();
	}
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	public List<?> findAllFuntion(UserModel loginuser){
		String queryString = "from FunctionModel fun where fun.status=00 order by fun.functOrder asc";
		return getDao().queryByHQL(queryString,null);
	}
	
	public List<?> findFunction(){
		String queryString = "select funct_id, funct_name, funct_order, parent_id, url,icon,leafnode from t_function fun where fun.status=00 order by fun.parent_id, fun.funct_order, fun.funct_id";
		return getDao().queryBySQL(queryString, null);
	}
	
	public List<?> existUserAndRoleFunct(Long userId,Long fid){
		String queryString = "select" +
			"t.user_id,trf.funct_id rolefunct,tuf.funct_id userfunct" +
		"from" +
			"t_user t" +
			"left join t_role_funct trf on t.role_id = trf.role_id"+
			"left join t_user_funct tuf on t.user_id = tuf.user_id"+
		"where" +
			"t.user_id=? and (trf.funct_id=? or tuf.funct_id=?)";
		Object[] paramaters = new Object[]{userId,fid,fid};
		return getDao().queryBySQL(queryString, paramaters);
	}
	
	public List<?> findLoginFuntion(UserModel loginuser) {
		
		String[] columns = new String[] { "v_user"};
		Object[] paramaters = new Object[] {
				"".equals(loginuser.getUserId()) ? null : loginuser.getUserId()};
		return getDao().executeOracleProcedure(
				"{CALL pro_funct_by_user(?,?)}", columns,
				paramaters, "cursor0");
	}
}
