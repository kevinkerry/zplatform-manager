package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IUserService;

public class UserServiceImpl extends BaseServiceImpl<UserModel, Long> implements
		IUserService {

	private DAOContainer daoContainer;

	@Override
	public IBaseDAO<UserModel, Long> getDao() {
		return daoContainer.getUserDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	public Map<String, Object> findUserByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_user_code", "v_user_name",
				"v_role_id", "v_organ_id", "v_dept_id", "i_no", "i_perno" };
		Object[] paramaters = new Object[7];
		paramaters[0] = variables.containsKey("userCode") ? variables
				.get("userCode") : null;
		paramaters[1] = variables.containsKey("userName") ? variables
				.get("userName") : null;
		paramaters[2] = variables.containsKey("roleId") ? variables
				.get("roleId") : null;
		paramaters[3] = variables.containsKey("organId") ? variables
				.get("organId") : null;
		paramaters[4] = variables.containsKey("deptId") ? variables
				.get("deptId") : null;
		paramaters[5] = page;
		paramaters[6] = rows;
		return getDao().executePageOracleProcedure("{CALL PCK_T_USER.sel_t_user(?,?,?,?,?,?,?,?,?)}",columns,
				paramaters, "cursor0","v_total");
	}

	public long findUserByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_user_code", "v_user_name",
				"v_role_id", "v_organ_id", "v_dept_id" };
		Object[] paramaters = new Object[5];
		paramaters[0] = variables.containsKey("userCode") ? variables
				.get("userCode") : null;
		paramaters[1] = variables.containsKey("userName") ? variables
				.get("userName") : null;
		paramaters[2] = variables.containsKey("roleId") ? variables
				.get("roleId") : null;
		paramaters[3] = variables.containsKey("organId") ? variables
				.get("organId") : null;
		paramaters[4] = variables.containsKey("deptId") ? variables
				.get("deptId") : null;
		Object total = getDao().executeOracleProcedure(
				"{CALL PCK_T_USER.sel_t_user_num(?,?,?,?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

	public List<?> saveUser(UserModel user) {
		String[] columns = new String[] { "v_user_code", "v_user_name",
				"v_login_name", "v_pwd", "v_pwd_valid", "v_creator",
				"v_organ_id", "v_dept_id", "v_isadmin", "v_notes",
				"v_remarks"};
		Object[] paramaters = new Object[] {
				"".equals(user.getUserCode()) ? null : user.getUserCode(),
				"".equals(user.getUserName()) ? null : user.getUserName(),
				"".equals(user.getLoginName()) ? null : user.getLoginName(),
				"".equals(user.getPwd()) ? null : user.getPwd(),
				"".equals(user.getPwdValid()) ? null : user.getPwdValid(),
				"".equals(user.getCreator()) ? null : user.getCreator(),
				"".equals(user.getOrganId()) ? null : user.getOrganId(),
				"".equals(user.getDeptId()) ? null : user.getDeptId(),
				"".equals(user.getIsadmin()) ? null : user.getIsadmin(),
				"".equals(user.getNotes()) ? null : user.getNotes(),
				"".equals(user.getRemarks()) ? null : user.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_USER.ins_t_user(?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> updateUser(UserModel user) {
		String[] columns = new String[] { "v_user_id","v_user_code", "v_user_name",
				"v_login_name", "v_pwd", "v_pwd_valid", "v_creator",
			    "v_organ_id", "v_dept_id", "v_isadmin","v_status", "v_notes",
				"v_remarks", };
		Object[] paramaters = new Object[] {
				"".equals(user.getUserId()) ? null : user.getUserId(),
				"".equals(user.getUserCode()) ? null : user.getUserCode(),
				"".equals(user.getUserName()) ? null : user.getUserName(),
				"".equals(user.getLoginName()) ? null : user.getLoginName(),
				"".equals(user.getPwd()) ? null : user.getPwd(),
				"".equals(user.getPwdValid()) ? null : user.getPwdValid(),
				"".equals(user.getCreator()) ? null : user.getCreator(),
				"".equals(user.getOrganId()) ? null : user.getOrganId(),
				"".equals(user.getDeptId()) ? null : user.getDeptId(),
				"".equals(user.getIsadmin()) ? null : user.getIsadmin(),
				"".equals(user.getStatus()) ? null : user.getStatus(),
				"".equals(user.getNotes()) ? null : user.getNotes(),
				"".equals(user.getRemarks()) ? null : user.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_USER.upt_t_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

}
