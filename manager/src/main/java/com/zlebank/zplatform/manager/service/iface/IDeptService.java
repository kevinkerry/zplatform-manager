package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.DeptModel;

public interface IDeptService extends IBaseService<DeptModel, Long>{
	
	public List<?> saveDept(DeptModel dept);
	public List<?> updateDept(DeptModel dept);
	public Map<String, Object> findDeptByPage(Map<String, Object> variables, int page,
			int rows);
	public long findDeptByPageCount(Map<String, Object> variables);
	public List<?> deleteDept(Long deptId);
}
