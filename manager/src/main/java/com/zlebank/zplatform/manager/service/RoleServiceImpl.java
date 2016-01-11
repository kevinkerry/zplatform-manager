package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.RoleModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRoleService;

public class RoleServiceImpl extends BaseServiceImpl<RoleModel, Long> implements
		IRoleService {

	private DAOContainer daoContainer;

	@Override
	public IBaseDAO<RoleModel, Long> getDao() {
		return daoContainer.getRoleDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	public Map<String, Object> findRoleByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_role_name", "v_organ_id",
				"v_dept_id","i_no","i_perno" };
		Object[] paramaters = new Object[5];
		paramaters[0] = variables.containsKey("roleName") ? variables
				.get("roleName") : null;
		paramaters[1] = variables.containsKey("organId") ? variables
				.get("organId") : null;
		paramaters[2] = variables.containsKey("deptId") ? variables
				.get("deptId") : null;
		paramaters[3] = page;
		paramaters[4] = rows;
		return getDao().executePageOracleProcedure("{CALL PCK_T_ROLE.sel_t_role(?,?,?,?,?,?,?)}",columns,
				paramaters, "cursor0","v_total");
	}

	public long findRoleByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_role_name", "v_organ_id",
				"v_dept_id"};
		Object[] paramaters = new Object[3];
		paramaters[0] = variables.containsKey("roleName") ? variables
				.get("roleName") : null;
		paramaters[1] = variables.containsKey("organId") ? variables
				.get("organId") : null;
		paramaters[2] = variables.containsKey("deptId") ? variables
				.get("deptId") : null;
		
		Object total = getDao().executeOracleProcedure(
				"{CALL PCK_T_ROLE.sel_t_role_num(?,?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

	public List<?> saveRole(RoleModel role) {

		String[] columns = new String[] { "v_role_name", "v_organ_id",
				"v_dept_id", "v_creator", "v_notes", "v_remarks" };
		Object[] paramaters = new Object[] {
				"".equals(role.getRoleName()) ? null : role.getRoleName(),
				"".equals(role.getOrganId()) ? null : role.getOrganId(),
				"".equals(role.getDeptId()) ? null : role.getDeptId(),
				"".equals(role.getCreator()) ? null : role.getCreator(),
				"".equals(role.getNotes()) ? null : role.getNotes(),
				"".equals(role.getRemarks()) ? null : role.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_ROLE.ins_t_role(?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> updateRole(RoleModel role) {
		String[] columns = new String[] { "v_role_id", "v_role_name",
				"v_organ_id", "v_dept_id", "v_creator", "v_notes", "v_remarks" };
		Object[] paramaters = new Object[] {
				"".equals(role.getRoleId()) ? null : role.getRoleId(),
				"".equals(role.getRoleName()) ? null : role.getRoleName(),
				"".equals(role.getOrganId()) ? null : role.getOrganId(),
				"".equals(role.getDeptId()) ? null : role.getDeptId(),
				"".equals(role.getCreator()) ? null : role.getCreator(),
				"".equals(role.getNotes()) ? null : role.getNotes(),
				"".equals(role.getRemarks()) ? null : role.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_ROLE.upt_t_role(?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> deleteRole(Long roleId) {
		String[] columns = new String[] { "v_role_id"};
		Object[] paramaters = new Object[] {roleId};
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_ROLE.del_t_role(?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> getMerchantFirstTrialRoleByUser(Long userId, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> getMerchantSecondTrialRoleByUser(Long userId, int flag) {
		// TODO Auto-generated method stub
		return null;
	}

}
