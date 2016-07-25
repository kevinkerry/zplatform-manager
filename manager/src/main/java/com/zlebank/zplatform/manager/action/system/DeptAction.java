package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.inject.Container;
import com.sun.jdi.LongValue;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.DeptModel;
import com.zlebank.zplatform.manager.dao.object.OrganModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class DeptAction extends BaseAction{

	private static final long serialVersionUID = 4826003708526109570L;
	private ServiceContainer serviceContainer;
	private List<OrganModel> organList;
	private String deptName;
	private String deptCode;
	private Long deptId;
	private DeptModel dept;
	HttpServletRequest request = ServletActionContext.getRequest();

	public String show(){
		return SUCCESS;
	}
	
	/**
	 * 部门查询
	 * @return
	 */
	public String query(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("deptName", deptName);
		variables.put("deptCode", deptCode);
		Map<String, Object> deptList = serviceContainer.getDeptService().findDeptByPage(variables, getPage(), getRows());
		json_encode(deptList);
		return null;
	}
	
	/**
	 * 显示部门列表
	 * @return
	 */
	public String showOrgan(){
		try {
			organList = serviceContainer.getOrganService().findByProperty("status", "00");
			json_encode(organList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存部门信息
	 * @return
	 */
	public String save(){
		try {
			dept.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getDeptService().saveDept(dept);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新部门信息
	 * @return
	 */
	public String update(){
		try {
			dept.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getDeptService().updateDept(dept);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除部门信息
	 * @return
	 */
	public String delete(){
		
		try {
			List<?> returnList = serviceContainer.getDeptService().deleteDept(deptId);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过ID获取部门信息
	 * @return
	 */
	public String getSingleById(){
		DeptModel dept = serviceContainer.getDeptService().get(deptId);
		json_encode(dept);
		return null;
	}
	/**
	 * 注销时更新备注
	 * @return
	 */
	public Map<String, String> updateNotes(){
	    Map<String, String> resultMap = new HashMap<String, String>();
	    Map<String,Object> variables = new HashMap<String, Object>();
	    String deptCode= request.getParameter("deptCode");
	    String notes = request.getParameter("notes");	    
        List<?> resultList = serviceContainer.getDeptService().updateDeptNotes(deptCode,notes);
        if(resultList!=null && resultList.size()!=0){
            resultMap.put("success", "true");
        }else{
            resultMap.put("success", "false");
        }  
        return resultMap;
//	    String deptcode = (String) rowList.get(0).get("DEPT_CODE");
//	    String deptname =(String) rowList.get(0).get("DEPT_NAME");
//	    String b =  (String) rowList.get(0).get("ORGAN_ID");	    
//	    long organId = b.longValue() ;
//	    String creator = (String) rowList.get(0).get("CREATOR");
//	    java.sql.Timestamp crateTime = (java.sql.Timestamp) rowList.get(0).get("CRATE_TIME");
//	    String status = (String) rowList.get(0).get("STATUS");
//	    String notes = (String) rowList.get(0).get("NOTES");
//	    String remarks = (String) rowList.get(0).get("REMARKS");
//	    dept.setDeptId(deptid);
//	    dept.setDeptCode(deptcode);
//	    dept.setDeptName(deptname);
//	    dept.setOrganId(organId);
//	    dept.setCreator(creator);
//	    dept.setCrateTime(crateTime);
//	    dept.setStatus(status);
//	    dept.setNotes(notes);
//	    dept.setRemarks(remarks);

	    
	}
	

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public List<OrganModel> getOrganList() {
		return organList;
	}

	public void setOrganList(List<OrganModel> organList) {
		this.organList = organList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public DeptModel getDept() {
		return dept;
	}

	public void setDept(DeptModel dept) {
		this.dept = dept;
	}
	
	
	
}
