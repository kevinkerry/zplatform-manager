package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.OperLogModel;

public interface IOperLogService extends IBaseService<OperLogModel, Long>{
	public List<?> findOperLogByPage(Map<String, Object> variables, int page,int rows);
	public long findOperLogByPageCount(Map<String, Object> variables);
}
