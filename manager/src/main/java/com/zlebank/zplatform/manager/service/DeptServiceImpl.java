package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.DeptModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IDeptService;

public class DeptServiceImpl extends BaseServiceImpl<DeptModel, Long> implements
		IDeptService {

	private DAOContainer daoContainer;

	@Override
	public IBaseDAO<DeptModel, Long> getDao() {
		return daoContainer.getDeptDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	public Map<String, Object> findDeptByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_dept_code", "v_dept_name", "i_no",
				"i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("deptCode") ? variables
				.get("deptCode") : null;
		paramaters[1] = variables.containsKey("deptName") ? variables
				.get("deptName") : null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return getDao().executePageOracleProcedure("{CALL PCK_T_DEPT.sel_t_dept(?,?,?,?,?,?)}",columns,
				paramaters, "cursor0","v_total");
	}

	public long findDeptByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_dept_code", "v_dept_name" };
		Object[] paramaters = new Object[2];
		paramaters[0] = variables.containsKey("deptCode") ? variables
				.get("deptCode") : null;
		paramaters[1] = variables.containsKey("deptName") ? variables
				.get("deptName") : null;
		Object total = getDao().executeOracleProcedure(
				"{CALL PCK_T_DEPT.sel_t_dept_num(?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

	public List<?> saveDept(DeptModel dept) {

		String[] columns = new String[] { "v_dept_code", "v_dept_name",
				"v_organ_id", "v_creator", "v_notes ", "v_remarks"

		};
		Object[] paramaters = new Object[] {
				"".equals(dept.getDeptCode()) ? null : dept.getDeptCode(),
				"".equals(dept.getDeptName()) ? null : dept.getDeptName(),
				"".equals(dept.getOrganId()) ? null : dept.getOrganId(),
				"".equals(dept.getCreator()) ? null : dept.getCreator(),
				"".equals(dept.getNotes()) ? null : dept.getNotes(),
				"".equals(dept.getRemarks()) ? null : dept.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_DEPT.ins_t_dept(?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> updateDept(DeptModel dept) {
		String[] columns = new String[] { "v_dept_id", "v_dept_code",
				"v_dept_name", "v_organ_id", "v_creator", "v_status",
				"v_notes", "v_remarks" };
		Object[] paramaters = new Object[] {
				dept.getDeptId(),
				"".equals(dept.getDeptCode()) ? null : dept.getDeptCode(),
				"".equals(dept.getDeptName()) ? null : dept.getDeptName(),
				"".equals(dept.getOrganId()) ? null : dept.getOrganId(),
				"".equals(dept.getCreator()) ? null : dept.getCreator(),
				"".equals(dept.getStatus()) ? null : dept.getStatus(),
				"".equals(dept.getNotes()) ? null : dept.getNotes(),
				"".equals(dept.getRemarks()) ? null : dept.getRemarks() };
		
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_DEPT.upt_t_dept(?,?,?,?,?,?,?,?,?)}",columns,
				paramaters, "cursor0");
	}

	public List<?> deleteDept(Long deptId) {
		String[] columns = new String[] { "v_dept_id"};
		Object[] paramaters = new Object[] {deptId};
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_DEPT.del_t_dept(?,?)}", columns,
				paramaters, "cursor0");
	}

    @Override
    public List<?> updateDeptNotes(String deptCode, String notes) {

        return daoContainer.getDeptDAO().updateDeptNotes(deptCode,notes);
        
        
        
    }

}
