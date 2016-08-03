package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.TaskModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.ITaskService;

public class TaskServiceImpl extends BaseServiceImpl<TaskModel,Long> implements ITaskService{

	private DAOContainer daoContainer;
	
	@Override
	public IBaseDAO<TaskModel,Long> getDao() {
		// TODO Auto-generated method stub
		return daoContainer.getTaskDAO();
	}

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	public String deleteTask(Long nid) {
		try {
			getDao().executeOracleProcedure(
					"{CALL MODI_T_TASK.del_t_task(?,?)}", new String[]{"v_tid"},
					new Object[]{nid}, "cursor0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "删除失败！";
		}
		return "删除成功！";
	}

	public List<?> findTaskByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_task_no", "v_content","i_no", "i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("task_no") ? variables
				.get("task_no") : null;
		paramaters[1] = variables.containsKey("content") ? variables
				.get("content") : null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_TASK.sel_t_task(?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public long findTaskByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_task_no", "v_content"};
		Object[] paramaters = new Object[2];
		paramaters[0] = variables.containsKey("task_no") ? variables
				.get("task_no") : null;
		paramaters[1] = variables.containsKey("content") ? variables
				.get("content") : null;
		Object total = getDao().executeOracleProcedure(
				"{CALL MODI_T_TASK.sel_t_task_num(?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}

	public List<?> saveTask(TaskModel task) {
		String[] columns = new String[] { 
			  "v_content",
		      "v_url",
		      "v_task_funct",
		      "v_task_type",
		      "v_task_organ",
		      "v_task_no",
		      "v_notes",
		      "v_remarks",
		      "v_task_user"
		};
		Object[] paramaters = new Object[] {
				"".equals(task.getContent()) ? null : task.getContent(),
				"".equals(task.getUrl()) ? null : task.getUrl(),
				"".equals(task.getTaskFunct()) ? null : task.getTaskFunct(),
				"".equals(task.getTaskType()) ? null : task.getTaskType(),
				"".equals(task.getTaskOrgan()) ? null : task.getTaskOrgan(),
				"".equals(task.getTaskNo()) ? null : task.getTaskNo(),
				"".equals(task.getNotes()) ? null : task.getNotes(),
				"".equals(task.getRemarks()) ? null : task.getRemarks(),
				"".equals(task.getTaskUser()) ? null : task.getTaskUser()};
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_TASK.ins_t_task(?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> updateTask(TaskModel task) {
		String[] columns = new String[] { 
				  "v_tid",
				  "v_content",
			      "v_url",
			      "v_task_funct",
			      "v_task_type",
			      "v_task_organ",
			      "v_status",
			      "v_task_no",
			      "v_notes",
			      "v_remarks"
		};
		Object[] paramaters = new Object[] {
				"".equals(task.getTid()) ? null : task.getTid(),
				"".equals(task.getContent()) ? null : task.getContent(),
				"".equals(task.getUrl()) ? null : task.getUrl(),
				"".equals(task.getTaskFunct()) ? null : task.getTaskFunct(),
				"".equals(task.getTaskType()) ? null : task.getTaskType(),
				"".equals(task.getTaskOrgan()) ? null : task.getTaskOrgan(),
				"".equals(task.getStatus()) ? null : task.getStatus(),
				"".equals(task.getTaskNo()) ? null : task.getTaskNo(),
				"".equals(task.getNotes()) ? null : task.getNotes(),
				"".equals(task.getRemarks()) ? null : task.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_TASK.upt_t_task(?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}

	public List<?> queryUserTask(Map<String, Object> variables) {
		String[] columns = new String[] { "v_merch_funct", "v_pos_funct","v_tel_funct", "v_user_id" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("merch_funct") ? variables
				.get("merch_funct") : null;
		paramaters[1] = variables.containsKey("pos_funct") ? variables
				.get("pos_funct") : null;
		paramaters[2] = variables.containsKey("tel_funct") ? variables
				.get("tel_funct") : null;
		paramaters[3] = variables.containsKey("user_id") ? variables
				.get("user_id") : null;
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_TASK.sel_user_task(?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}
	
	public void completeTask(String taskNo){
		String queryString = "from TaskModel where taskNo = ? and status = 1 order by tid desc";
		@SuppressWarnings("unchecked")
        List<TaskModel> taskList = (List<TaskModel>) getDao().queryByHQL(queryString, new Object[]{taskNo});
		if(taskList.size()>0){
			TaskModel merchTask = taskList.get(0);
			merchTask.setStatus(0L);
			getDao().update(merchTask);
		}
		
	}

 

}
