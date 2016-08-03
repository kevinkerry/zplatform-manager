package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.TaskModel;

public interface ITaskService extends IBaseService<TaskModel,Long>{
	
	public List<?> saveTask(TaskModel task);
	public List<?> updateTask(TaskModel task);
	public String deleteTask(Long nid);
	public List<?> findTaskByPage(Map<String, Object> variables, int page,
			int rows);
	public long findTaskByPageCount(Map<String, Object> variables);
	
	public List<?> queryUserTask(Map<String, Object> variables);
	
	public void completeTask(String taskNo);
	

	
}
