package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.OperLogModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IOperLogService;

public class OperLogServiceImpl extends BaseServiceImpl<OperLogModel, Long> implements IOperLogService{

	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<OperLogModel, Long> getDao() {
		// TODO Auto-generated method stub
		return daoContainer.getOperLogDAO();
	}
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	
	
	public List<?> findOperLogByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[]{
				 "v_user_name",
			      "v_ip",
			      "v_stime",
			      "v_etime",
			      "i_no",
			      "i_perno"
		};
		Object[] paramaters = new Object[6];
		paramaters[0] = variables.containsKey("user_name")?variables.get("user_name"):null;
		paramaters[1] = variables.containsKey("ip")?variables.get("ip"):null;
		paramaters[2] = variables.containsKey("stime")?variables.get("stime"):null;
		paramaters[3] = variables.containsKey("etime")?variables.get("etime"):null;
		paramaters[4] = page;
		paramaters[5] = rows;
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_OPER_LOG.sel_t_oper_log(?,?,?,?,?,?,?)}",columns,
				paramaters, "cursor0");
	}
	public long findOperLogByPageCount(Map<String, Object> variables) {
		String[] columns = new String[]{
				  "v_user_name",
			      "v_ip",
			      "v_stime",
			      "v_etime"
		};
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("user_name")?variables.get("user_name"):null;
		paramaters[1] = variables.containsKey("ip")?variables.get("ip"):null;
		paramaters[2] = variables.containsKey("stime")?variables.get("stime"):null;
		paramaters[3] = variables.containsKey("etime")?variables.get("etime"):null;
		Object total = getDao().executeOracleProcedure(
				"{CALL MODI_T_OPER_LOG.sel_t_oper_log_num(?,?,?,?,?)}",columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

}
