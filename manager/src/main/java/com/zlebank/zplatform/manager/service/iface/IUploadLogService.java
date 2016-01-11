package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.UploadLogModel;

public interface IUploadLogService extends IBaseService<UploadLogModel, Long>{
	public List<?> StartCheckFile(String instiid) ;
	public Map<String, Object> findProcessByPage(Map<String, Object> variables, int page,
			int rows) ;
	public List<?> saveProcess(String instiid);
	public Map<String, Object> findMemberByPage(Map<String, Object> variables, int page,
			int rows) ;
	public Map<String, Object> findMemberACCByPage(Map<String, Object> variables, int page,
			int rows) ;
}
