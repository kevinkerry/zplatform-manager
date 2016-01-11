package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.NoticeModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.util.interceptor.Authority;

public class NoticeAction extends BaseAction{

	private static final long serialVersionUID = 2257699958325732376L;
	private ServiceContainer serviceContainer;

	private NoticeModel notice;
	private Long nid;
	
	@Authority(actionName="show", privilege="167")
	public String show(){
		return SUCCESS;
	}
	
	public String query(){
		Map<String, Object> variables = new HashMap<String, Object>();
		if(!isNull(notice)){
			variables.put("title", notice.getTitle());
			variables.put("content", notice.getContent());
		}
		List<?> noticeList = serviceContainer.getNoticeService().findNoticeByPage(variables, getPage(), getRows());
		long count = serviceContainer.getNoticeService().findNoticeByPageCount(variables);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows",noticeList);
		json_encode(resultMap);
		return null;
	}
	
	
	public String save(){
		try {
			notice.setPubUser(getCurrentUser().getUserId());
			notice.setNoticeType(1L);
			notice.setNoticeSort(2L);
			List<?> returnList = serviceContainer.getNoticeService().saveNotice(notice);
			json_encode(returnList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String update(){
		try {
			List<?> returnList = serviceContainer.getNoticeService().updateNotice(notice);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(){
		json_encode(serviceContainer.getNoticeService().getNotice(nid));
		return null;
	}
	
	public String delete(){
		json_encode(serviceContainer.getNoticeService().deleteNotice(nid));
		return null;
	}
	
	public String findPublic(){
		try {
			List<?> resultList = serviceContainer.getNoticeService().findPublicNotice();
			json_encode(resultList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String findPerson(){
		try {
			List<?> resultList = serviceContainer.getNoticeService().findPersonNotice(getCurrentUser().getUserId());
			json_encode(resultList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public NoticeModel getNotice() {
		return notice;
	}

	public void setNotice(NoticeModel notice) {
		this.notice = notice;
	}

	public Long getNid() {
		return nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}
	
}
