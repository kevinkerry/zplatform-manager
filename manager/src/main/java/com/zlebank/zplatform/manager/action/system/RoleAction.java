package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.DeptModel;
import com.zlebank.zplatform.manager.dao.object.FunctionModel;
import com.zlebank.zplatform.manager.dao.object.OrganModel;
import com.zlebank.zplatform.manager.dao.object.RoleFunctModel;
import com.zlebank.zplatform.manager.dao.object.RoleModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.util.interceptor.Authority;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ServiceContainer serviceContainer;
	private RoleModel role;
	private Long roleId;
	private Long organId;
	private String userFunc;
	private List<OrganModel> organList;
	private List<DeptModel> deptList;
	private List<RoleModel> roleList;
	

	public String show(){
		return SUCCESS;
	}
	
	/**
	 * 显示组织机构列表
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
	 * 显示职能部门列表
	 * @return
	 */
	public String showDept(){
		try {
			Map<String, Object> variable = new HashMap<String, Object>();
			variable.put("status", "00");
			variable.put("organId", organId);
			deptList = serviceContainer.getDeptService().findByProperty(variable);
			json_encode(deptList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询角色信息
	 * @return
	 */
	public String query(){
		Map<String, Object> variables = new HashMap<String, Object>();
		if(role!=null){
			variables.put("roleName", role.getRoleName());
			variables.put("organId", role.getOrganId());
			variables.put("deptId", role.getDeptId());
		}
		Map<String, Object> roleList = serviceContainer.getRoleService().findRoleByPage(variables, getPage(), getRows());
		json_encode(roleList);
		return null;
	}
	
	/**
	 * 新增角色信息
	 * @return
	 */
	public String save(){
		try {
			role.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getRoleService().saveRole(role);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新角色信息
	 * @return
	 */
	public String update(){
		try {
			role.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getRoleService().updateRole(role);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除角色信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public String delete(){
		try {
			List<?> returnList = serviceContainer.getRoleService().deleteRole(role.getRoleId());
			Map<String, Object> map =new HashMap<String, Object>();
			map = (Map<String, Object>) returnList.get(0);
			if(map.get("RET").equals("succ")){
			    role.setCreator(getCurrentUser().getUserName());
			    role.setStatus("01");
			    List<?> list = serviceContainer.getRoleService().updateRole(role);
			}			
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过Id获取角色信息
	 * @return
	 */
	public String getSingleById(){
		RoleModel role = serviceContainer.getRoleService().get(roleId);
		json_encode(role);
		return null;
	}
	
	/**
	 * 显示角色权限页面
	 * @return
	 */
	@Authority(actionName="showReceive", privilege="14")
	public String showAuth(){
		Map<String, Object> variable = new HashMap<String, Object>();
		UserModel user = getCurrentUser();
		if(user.getIsadmin().equals("1")||user.getOrganId()==0){//管理员和总公司用户取得全部组织机构信息,职能部门信息
			organList = serviceContainer.getOrganService().findByProperty("status", "00");
			roleList = serviceContainer.getRoleService().findByProperty("status", "00");
			deptList = serviceContainer.getDeptService().findByProperty("status", "00");
		}else{//其他用户只能取得其所在机构的信息
			variable.put("status", "1");
			variable.put("organId", user.getOrganId());
			organList = serviceContainer.getOrganService().findByProperty(variable);
			variable = new HashMap<String, Object>();
			variable.put("status", "00");
			variable.put("organId", user.getOrganId());
			roleList = serviceContainer.getRoleService().findByProperty(variable);
			variable = new HashMap<String, Object>();
			variable.put("status", "1");
			variable.put("organId", user.getOrganId());
			deptList = serviceContainer.getDeptService().findByProperty(variable);
		}
		return "role_authority";
	}
	
	/**
	 * 点击某一角色后 查出对应角色权限
	 * @return
	 * @throws Exception
	 */
    public String queryFunction() throws Exception {
    	try {
			@SuppressWarnings("unchecked")
            List<FunctionModel> list = (List<FunctionModel>) serviceContainer.getFunctionService().findAllFuntion(getCurrentUser());
			List<RoleFunctModel> roleList =  serviceContainer.getRoleFunctService().findByProperty("roleId", roleId);
			List<FunctionModel> removeList = new ArrayList<FunctionModel>(); 
			List<FunctionModel> children = null;
			for(FunctionModel function : list){
				if("0".equals(function.getParentId())){//根节点
					if(children != null){children=null;}
					function.setChildren(new ArrayList<FunctionModel>());
					children = function.getChildren();
					function.setState("closed");
				}else{//子节点
					for(RoleFunctModel roleFunct : roleList){
						if(roleFunct.getFunctId().equals(function.getFunctId()) ){
							function.setChecked("true");
							function.setText(function.getFunctName());//没有蓝色  设置成可选
						}
					}
					children.add(function);//function.getChildren().add(function)
					removeList.add(function);
				}
				function.setId(function.getFunctId().toString());
				if(isNull(function.getText())){
					function.setText(function.getFunctName());
				}
			}
			list.removeAll(removeList);//移除全部的子节点数据
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result",list);
			result.put("roleFunction",roleList);
			json_encode(result);
		} catch (Exception e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    /**
     * 修改角色权限（先删除，再重新保存）
     * @return
     */
    public String saveFunction(){
    	if(roleId!=null&&!roleId.equals("")){
    		try {
    			serviceContainer.getRoleFunctService().deleteRoleFunction(roleId);
    			String[] funcId = userFunc.split(",");
    			List<RoleFunctModel> functList = new ArrayList<RoleFunctModel>();
    			Long num = 1L;
    			for(int i = 0;i<funcId.length;i++){
    				RoleFunctModel model = new RoleFunctModel();
    				model.setRoleFunctId(num);
    				model.setRoleId(roleId);
    				model.setFunctId(Long.valueOf(funcId[i]));
    				functList.add(model);
    				num++;
    			}
    			serviceContainer.getRoleFunctService().save(functList);
    			json_encode("保存成功");
    		} catch (Exception e) {
    			e.printStackTrace();
    			json_encode("保存失败");
    		}
    	}
		
		return null;
	}
	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
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

	public List<DeptModel> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DeptModel> deptList) {
		this.deptList = deptList;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<RoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}

	public String getUserFunc() {
		return userFunc;
	}

	public void setUserFunc(String userFunc) {
		this.userFunc = userFunc;
	}

}
