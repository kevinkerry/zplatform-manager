package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
