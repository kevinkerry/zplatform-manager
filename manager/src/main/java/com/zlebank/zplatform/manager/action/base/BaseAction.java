package com.zlebank.zplatform.manager.action.base;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.util.DateJsonValueProcessor;

public class BaseAction extends ActionSupport{
	private static final Log log = LogFactory.getLog(BaseAction.class);
	
	private static final long serialVersionUID = 1L;
	private int page_index;
	
	private int page_size=10;
	
	private int page=1;
	private int rows=10;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int pageSize) {
		page_size = pageSize;
	}

	public int getPage_index() {
		return page_index;
	}

	public void setPage_index(int pageIndex) {
		page_index = pageIndex;
	}
	public void json_encode(Object resultList){
		try {
			JsonConfig jsonConfig=new JsonConfig();   
			jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
			jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONArray jsonobject =JSONArray.fromObject(resultList,jsonConfig);
			
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			log.debug(jsonobject.get(0).toString());
			ServletActionContext.getResponse().getWriter().write(jsonobject.get(0).toString());
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void json_encode(String value){
		try {
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			log.debug(value);
			ServletActionContext.getResponse().getWriter().write(value);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void json_encode(List<?> resultList) throws IOException{
		
		try {
			JsonConfig jsonConfig=new JsonConfig();   
			jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
			jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONArray jsonobject =JSONArray.fromObject(resultList,jsonConfig);
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			log.debug(jsonobject.toString());
			ServletActionContext.getResponse().getWriter().write(jsonobject.toString());
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public UserModel getCurrentUser(){
		ActionContext actionContext = ActionContext.getContext();
		Map<String,Object> session = actionContext.getSession();
		if(session.get("LOGIN_USER") instanceof UserModel){
			log.debug("loginuser:"+((UserModel) session.get("LOGIN_USER")).getLoginName());
			return (UserModel) session.get("LOGIN_USER");
		}
		return null;
	}
	
	public String string2Date(String time){
		Date date=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date=formatter.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
		
	}
	
	public boolean isNull(Object value){
		if(value==null||value.toString().equals("")){
			return true;
		}else{
			return false;
		}
		
	}
	public String getIpAddr(HttpServletRequest request) {  
	   	 String ip = request.getHeader("x-forwarded-for");  
	   	 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	   	  ip = request.getHeader("Proxy-Client-IP");  
	   	 }  
	   	 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	   	  ip = request.getHeader("WL-Proxy-Client-IP");  
	   	 }  
	   	 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	   	  ip = request.getRemoteAddr();  
	   	 }  
	   	 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	   	  ip = request.getHeader("http_client_ip");  
	   	 }  
	   	 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	   	  ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	   	 }  
	   	 // 濡傛灉鏄绾т唬鐞嗭紝閭ｄ箞鍙栫涓�釜ip涓哄鎴穒p   
	   	 if (ip != null && ip.indexOf(",") != -1) {  
	   	  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();  
	   	 }  
	   	 return ip;  
	   	} 
}
