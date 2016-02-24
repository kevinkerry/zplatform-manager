package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.TaskModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class TaskAction extends BaseAction{
	private static final long serialVersionUID = 5211064713639481602L;
	private ServiceContainer serviceContainer;
	private TaskModel task;
	private Long tid;
	public String show(){
		return SUCCESS;
	}

	
	public String query(){
		Map<String, Object> variables = new HashMap<String, Object>();
		if(!isNull(task)){
			variables.put("task_no", task.getTaskNo());
			variables.put("content", task.getContent());
		}
		List<?> taskList = serviceContainer.getTaskService().findTaskByPage(variables, getPage(), getRows());
		long count = serviceContainer.getTaskService().findTaskByPageCount(variables);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows",taskList);
		json_encode(resultMap);
		return null;
	}
	
	public String get(){
		task = serviceContainer.getTaskService().get(tid);
		json_encode(task);
		return null;
	}
	
	public String update(){
		try {
			List<?> returnList = serviceContainer.getTaskService().updateTask(task);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String delete(){
		json_encode(serviceContainer.getTaskService().deleteTask(tid));
		return null;
	}
	
	
	public String queryFunction() throws IOException{
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		@SuppressWarnings("unchecked")
        List<Map<String, Object>> funlist = (List<Map<String, Object>>) session.get("Authority");
		String merch_funct = "0";
		String pos_funct = "0";
		String tel_funct = "0";
		for(int i=0;i<funlist.size();i++){
			Map<String, Object> function = funlist.get(i);
			if("23".equals(function.get("FUNCT_ID").toString())){//商户管理
				merch_funct = "23";
			}
			if("4".equals(function.get("FUNCT_ID").toString())){//POS终端管理
				pos_funct = "4";
			}
			if("44".equals(function.get("FUNCT_ID").toString())){//电话POS管理
				tel_funct = "44";
			}
		}
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("merch_funct", merch_funct);
		variables.put("pos_funct", pos_funct);
		variables.put("tel_funct", tel_funct);
		variables.put("user_id", getCurrentUser().getUserId());
		List<?> resultList = serviceContainer.getTaskService().queryUserTask(variables);
		json_encode(resultList);
		return null;
	}
	
	
	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}


	public TaskModel getTask() {
		return task;
	}


	public void setTask(TaskModel task) {
		this.task = task;
	}


	public Long getTid() {
		return tid;
	}


	public void setTid(Long tid) {
		this.tid = tid;
	}
}
