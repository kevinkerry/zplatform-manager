package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.UploadLogModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IUploadLogService;

public class UploadLogServiceImpl extends BaseServiceImpl<UploadLogModel, Long> implements IUploadLogService{

	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public IBaseDAO<UploadLogModel, Long> getDao() {
		return daoContainer.getUploadlogDAO();
	}
	public List<?> saveProcess(String instiid) {

		String[] columns = new String[] {"v_instiid","v_stage","v_speed","v_status"}; 
		Object[] paramaters = new Object[]{instiid,"11","0","00"};
		return getDao().executeOracleProcedure(
				"{CALL  PCK_T_SETT_PROCESS.ins_t_sett_process(?,?,?,?,?)}",columns,
				paramaters, "cursor0");
	}
	public List<?> StartCheckFile(String tid) {
		String[] columns = new String[] {"v_tid"}; 
		Object[] paramaters = new Object[]{tid};
		return getDao().executeOracleProcedure(
				"{CALL  CHECK_BILL.checkbill(?,?)}",columns,
				paramaters, "cursor0");
	}
	public Map<String, Object> findProcessByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_stime", "v_etime", "i_no",
				"i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("startDate") ? variables.get("startDate"): null;
		paramaters[1] = variables.containsKey("endDate") ? variables.get("endDate") : null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return getDao().executePageOracleProcedure("{CALL PCK_T_SETT_PROCESS.sel_t_sett_process(?,?,?,?,?,?)}",columns,
				paramaters, "cursor0","v_total");
	}
	
	
	public Map<String, Object> findMemberByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_memberid", "v_membername", "v_phone",
				"v_email","v_loginame"," v_membertype" ,"v_r","v_p"};
		Object[] paramaters = new Object[8];
		paramaters[0] = variables.containsKey("memberId") ? variables.get("memberId"): null;
		paramaters[1] = variables.containsKey("memberName") ? variables.get("memberName") : null;
		paramaters[2] = null;
		paramaters[3] = null;
		paramaters[4] = null;
		paramaters[5] =  variables.containsKey("membertype") ? variables.get("membertype") : null;
		paramaters[6] = rows;
		paramaters[7] = page;

		return getDao().executePageOracleProcedure("{CALL PCK_T_MEMBER.sel_t_member(?,?,?,?,?,?,?,?,?,?)}",columns,
				paramaters,"cursor1","v_num");
	}
	public Map<String, Object> findMemberACCByPage(Map<String, Object> variables, int page,
			int rows) {

		String[] columns = new String[] { "v_memberid", "v_acct_code", "v_acct_code_name",
				"v_busiacct_name","i_no","i_perno" };
		Object[] paramaters = new Object[6];
		paramaters[0] = variables.containsKey("memberId") ? variables.get("memberId"): null;
		paramaters[1] = variables.containsKey("accNo") ? variables.get("accNo") : null;
		paramaters[2] = variables.containsKey("acct_code_name") ? variables.get("acct_code_name"): null;
		paramaters[3] = variables.containsKey("busiacct_name") ? variables.get("busiacct_name") : null;
		paramaters[4] = page;
		paramaters[5] = rows;


		return getDao().executePageOracleProcedure("{CALL PCK_SEL_ACCT.sel_acct(?,?,?,?,?,?,?,?)}",columns,
				paramaters,"cursor0","v_total");
	}
	
}
