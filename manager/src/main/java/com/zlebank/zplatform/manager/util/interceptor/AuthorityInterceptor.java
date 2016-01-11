package com.zlebank.zplatform.manager.util.interceptor;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zlebank.zplatform.manager.dao.object.OperLogModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class AuthorityInterceptor implements Interceptor {

	private static final Log log = LogFactory.getLog(AuthorityInterceptor.class);
	private static final long serialVersionUID = 1L;
	private ServiceContainer serviceContainer;
	

	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName=invocation.getProxy().getMethod();        
		Method currentMethod=invocation.getAction().getClass().getMethod(methodName, null);
		HttpServletRequest request = ServletActionContext.getRequest();
		String fid = request.getParameter("tid");
		//当前登陆的用户
		UserModel currentUser = (UserModel) request.getSession().getAttribute("LOGIN_USER");
		
		//2、进行权限控制判断               
		//如果该请求方法是需要进行验证的则需执行以下逻辑
		if(currentMethod.isAnnotationPresent(Authority.class)){
			//获取权限校验的注解           
			Authority authority=currentMethod.getAnnotation(Authority.class);            
			//获取当前请求的注解的actionName               
			String actionName=authority.actionName();            
			//获取当前请求需要的权限               
			String privilege=authority.privilege();
			//获取当前操作内容
			String content=authority.operContent();
			//模块标识
			String functId = authority.functId();
			boolean flag = false;
			if("0".equals(privilege)||"".equals(privilege)){//无需权限判断，进入目标页面
				flag = true;
			}else{
				//可以在此判断当前客户是否拥有对应的权限，如果没有可以跳到指定的无权限提示页面，如果拥有则可以继续往下执行。  
				List<Map<String, Object>> funlist = (List<Map<String, Object>>) request.getSession().getAttribute("Authority");
				
				for(Map<String, Object> function : funlist){
					if(function.containsKey("FUNCT_ID")){
						String funct_id = function.get("FUNCT_ID").toString();
						if(privilege.equals(funct_id)){
							flag = true;
						}
					}
				}
				
				
			}
			if(!flag){
				content = "没有当前模块的操作权限!";
			}
			//操作日志记录
			OperLogModel operLog = new OperLogModel();
			operLog.setUserId(currentUser.getUserId());
			operLog.setUserName(currentUser.getUserName());
			if(functId.equals("")){
				functId = privilege;
			}
			operLog.setFunctId(Long.valueOf(functId));
			operLog.setIp(getIpAddr(request));
			operLog.setHostName(request.getRemoteHost());
			operLog.setDeptId(currentUser.getDeptId());
			operLog.setOpContent(content);
			operLog.setLogId(1L);
			Timestamp opDate = new Timestamp(new Date().getTime());
			operLog.setOpDate(opDate);
			serviceContainer.getOperLogService().save(operLog);
			if(flag){//权限session中有此权限
				return invocation.invoke();
			}else{//无此权限
				return "AuthorityError";
			}
			
		}
		return invocation.invoke();
		
		/*Map map = invocation.getInvocationContext().getParameters();
		
        
		log.info("this is AuthorityInterceptor---path:"+request.getServletPath()+"---tid:"+fid);
		if(fid!=null&&!"".equals(fid)){//只对有fid参数的url进行权限验证
        	
            //检验fid是否被人为修改过
            FunctionModel functionModel = serviceContainer.getFunctionService().get(Long.valueOf(fid));
            if(!functionModel.getUrl().equals(request.getServletPath().substring(1)+"?fid="+fid)){
            	//System.out.println(functionModel.getUrl()+"------"+request.getServletPath());
            	return "AuthorityError";
            }else{//没有被修改过检查用户是否有此功能的操作权限
            	if(!userModel.getUserName().equals("admin")){//当前用户不是管理员时进行权限检测
        			List<?> resultList = serviceContainer.getFunctionService().existUserAndRoleFunct(userModel.getUserId(), Long.valueOf(fid));
        			if(resultList.size()==0){
        				return "AuthorityError";
        			}
            	}
            	
            }
		}
            return invocation.invoke();*/
	}
	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}
	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void init() {
		// TODO Auto-generated method stub
		
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
   	 // 如果是多级代理，那么取第一个ip为客户ip   
   	 if (ip != null && ip.indexOf(",") != -1) {  
   	  ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();  
   	 }  
   	 return ip;  
   	} 
	

}
