package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.DeptModel;
import com.zlebank.zplatform.manager.dao.object.FunctionModel;
import com.zlebank.zplatform.manager.dao.object.OrganModel;
import com.zlebank.zplatform.manager.dao.object.RoleFunctModel;
import com.zlebank.zplatform.manager.dao.object.RoleModel;
import com.zlebank.zplatform.manager.dao.object.UserFunctModel;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.dao.object.UserRoleModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.util.MD5Util;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ServiceContainer serviceContainer;
	private UserModel user;
	private long userId;
	private Long roleId;
	private Long organId;
	private Long deptId;
	private String userFunc;
	private List<OrganModel> organList;
	private List<DeptModel> deptList;
	private List<RoleModel> roleList;
	private List<UserModel> userList;
	private String oldPwd;
	private String newPwd;
	private String conPwd;

	// @Authority(actionName="show", privilege="9")
	public String show() {
		return SUCCESS;
	}

	/**
	 * 显示组织机构列表
	 * 
	 * @return
	 */
	public String showOrgan() {
		try {

			organList = serviceContainer.getOrganService().findByProperty(
					"status", "00");
			json_encode(organList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示职能部门列表
	 * 
	 * @return
	 */
	public String showDept() {
		try {
			Map<String, Object> variable = new HashMap<String, Object>();
			variable.put("status", "00");
			variable.put("organId", organId);
			deptList = serviceContainer.getDeptService().findByProperty(
					variable);
			json_encode(deptList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示用户可选的角色（由所选的职能部门决定）
	 * 
	 * @return
	 */
	public String showRole() {
		try {
			Map<String, Object> variable = new HashMap<String, Object>();
			variable.put("deptId", deptId);
			variable.put("status", "00");
			roleList = serviceContainer.getRoleService().findByProperty(
					variable);
			json_encode(roleList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String query() {
		Map<String, Object> variables = new HashMap<String, Object>();
		if (user != null) {
			variables.put("userName", user.getUserName());
			variables.put("userCode", user.getUserCode());
			variables.put("organId", user.getOrganId());
			variables.put("deptId", user.getDeptId());
			variables.put("roleId", user.getNotes());
		}

		Map<String, Object> userList = serviceContainer.getUserService()
				.findUserByPage(variables, getPage(), getRows());
		json_encode(userList);
		return null;
	}

	/**
	 * 新增用户信息
	 * 
	 * @return
	 */
	public String save() {
		try {
			user.setCreator(getCurrentUser().getLoginName());
			String passwordMark = "w5y1j5z1s1l1z6z0y8z1m1l0c5r5y3z4";
			passwordMark = passwordMark + "123456";
			user.setPwd(MD5Util.MD5(passwordMark));
			List<?> returnList = serviceContainer.getUserService().saveUser(
					user);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String update() {
		try {
			user.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getUserService().updateUser(
					user);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过ID获取用户信息
	 * 
	 * @return
	 */
	public String getSingleById() {
		UserModel user = serviceContainer.getUserService().get(userId);
		json_encode(user);
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String delete() {
		UserModel user = serviceContainer.getUserService().get(userId);
		user.setStatus("01");
		serviceContainer.getUserService().update(user);
		json_encode("操作成功");
		return null;
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	// @Authority(actionName="resetPwd",
	// privilege="0",operContent="重置登录密码",functId="0")
	public String resetPwd() {
		UserModel user = serviceContainer.getUserService().get(userId);
		String passwordMark = "w5y1j5z1s1l1z6z0y8z1m1l0c5r5y3z4";
		passwordMark = passwordMark + "123456";
		user.setPwd(MD5Util.MD5(passwordMark));
		user.setPwdValid(new Timestamp(new Date().getTime()));// 密码重置有效期更新为当前时间，登录后会强制修改密码
		serviceContainer.getUserService().update(user);
		json_encode("操作成功");
		return null;
	}

	// /////////////////////////////////////用户权限管理模块///////////////////////////////////////////

	/**
	 * 加载所有角色
	 * 
	 * @return
	 */
	public String queryRoleAllList() {
		@SuppressWarnings("unchecked")
		List<RoleModel> rolelist = (List<RoleModel>) serviceContainer
				.getRoleService().queryByHQL(
						"from RoleModel rm where rm.status='00'",
						new Object[] {});
		try {
			json_encode(rolelist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载用户已经有的角色
	 * 
	 * @return
	 */
	public String queryRoleListhave() {
		List<RoleModel> rolelist = serviceContainer.getRoleService().findAll();
		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("userId", userId);
		List<UserRoleModel> rolefunctList = serviceContainer
				.getUserRoleService().findByProperty(variable);
		List<RoleModel> haverolelist = new ArrayList<RoleModel>();
		for (RoleModel roleMl : rolelist) {
			for (UserRoleModel rfctml : rolefunctList) {
				if ((roleMl.getRoleId()).equals(rfctml.getRoleId())) {
					haverolelist.add(roleMl);
				}
			}
		}
		try {
			json_encode(haverolelist);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 加载用户已经有的权限
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryFunction() {
		List<RoleFunctModel> roleList = new ArrayList<RoleFunctModel>();
		try {
			List<FunctionModel> list = (List<FunctionModel>) serviceContainer
					.getFunctionService().findAllFuntion(getCurrentUser());
			List<UserRoleModel> allRoleList = serviceContainer
					.getUserRoleService().findByProperty("userId", userId);
			List<Object> roleIdlist = new ArrayList<Object>();
			for (UserRoleModel role : allRoleList) {
				roleIdlist.add(role.getRoleId());
			}
			if (roleIdlist.size() > 0) {
				roleList = (List<RoleFunctModel>) serviceContainer
						.getRoleFunctService()
						.queryByHQL(
								"from RoleFunctModel rfm where rfm.roleId in (:roleIds) ",
								"roleIds", roleIdlist);

			}
			// List<RoleFunctModel> roleList2 =
			// serviceContainer.getRoleFunctService().findByProperty("roleId",
			// roleId);
			Map<String, Object> variable = new HashMap<String, Object>();
			variable.put("userId", userId);
			List<UserFunctModel> userList = serviceContainer
					.getUserFunctService().findByProperty(variable);
			List<FunctionModel> removeList = new ArrayList<FunctionModel>();
			List<FunctionModel> children = null;
			for (FunctionModel function : list) {
				if ("0".equals(function.getParentId())) {// 根节点
					if (children != null) {
						children = null;
					}
					function.setChildren(new ArrayList<FunctionModel>());
					children = function.getChildren();
					function.setState("closed");
				} else {// 子节点
					for (RoleFunctModel roleFunct : roleList) {
						if (roleFunct.getFunctId()
								.equals(function.getFunctId())) {
							function.setChecked("true");
							function.setText("<span style='color:blue'>"
									+ function.getFunctName() + "</span>");
						}
					}
					for (UserFunctModel userFunct : userList) {
						if (userFunct.getFunctId()
								.equals(function.getFunctId())) {
							function.setChecked("true");
						}
					}
					children.add(function);
					removeList.add(function);
				}
				function.setId(function.getFunctId().toString());
				if (isNull(function.getText())) {
					function.setText(function.getFunctName());
				}
			}
			list.removeAll(removeList);// 移除全部的子节点数据

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("result", list);
			result.put("roleFunction", roleList);
			json_encode(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存用户权限
	 * 
	 * @return
	 */
	public String saveAuth() {
		try {
			serviceContainer.getUserFunctService().deleteOldFunc(userId);
			if (isNull(userFunc)) {
				json_encode("保存成功");
				return null;
			}
			String[] funcId = userFunc.split(",");
			List<UserFunctModel> functList = new ArrayList<UserFunctModel>();
			Long num = 1L;
			for (int i = 0; i < funcId.length; i++) {
				UserFunctModel model = new UserFunctModel();
				model.setUserFunctId(num);
				model.setUserId(userId);
				model.setFunctId(Long.valueOf(funcId[i]));
				functList.add(model);
				num++;
			}
			serviceContainer.getUserFunctService().save(functList);
			json_encode("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json_encode("保存失败");
		}
		return null;
	}

	/**
	 * 保存用户角色
	 * 
	 * @return
	 */
	public String SaveUserRole() {
		try {
			serviceContainer.getUserRoleService().deleteOldUserRole(userId);
			if (isNull(userFunc)) {
				json_encode("保存成功");
				return null;
			}
			String[] roleId = userFunc.split(",");
			List<UserRoleModel> userRoleList = new ArrayList<UserRoleModel>();
			Long num = 1L;
			for (int i = 0; i < roleId.length; i++) {
				UserRoleModel model = new UserRoleModel();
				model.setUserRoleId(num);
				model.setUserId(userId);
				model.setRoleId(Long.valueOf(roleId[i]));
				userRoleList.add(model);
				num++;
			}
			serviceContainer.getUserRoleService().save(userRoleList);
			json_encode("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json_encode("保存失败");
		}
		return null;
	}

	public String changePassword() {
		String passwordMark = "w5y1j5z1s1l1z6z0y8z1m1l0c5r5y3z4";
		String old_passwordMark = passwordMark + oldPwd;
		String new_passwordMark = passwordMark + newPwd;
		UserModel user = getCurrentUser();
		String md5Pwd = MD5Util.MD5(old_passwordMark);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (md5Pwd.equalsIgnoreCase(user.getPwd())) {// 检验MD5密码是否正确
			if (newPwd.equalsIgnoreCase(conPwd)) {
				user.setPwd(MD5Util.MD5(new_passwordMark));
				// 密码有效期延长30天
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, +30);//
				Timestamp timestamp = new Timestamp(cal.getTime().getTime());
				user.setPwdValid(timestamp);
				serviceContainer.getUserService().update(user);
				returnMap.put("retcode", "succ");
				returnMap.put("retinfo", "操作成功！");
			} else {
				returnMap.put("retcode", "error");
				returnMap.put("retinfo", "确认密码输入错误！");

			}

		} else {
			returnMap.put("retcode", "error");
			returnMap.put("retinfo", "原始密码错误！");
		}
		json_encode(returnMap);
		return null;
	}

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
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

	public List<RoleModel> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleModel> roleList) {
		this.roleList = roleList;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserFunc() {
		return userFunc;
	}

	public void setUserFunc(String userFunc) {
		this.userFunc = userFunc;
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConPwd() {
		return conPwd;
	}

	public void setConPwd(String conPwd) {
		this.conPwd = conPwd;
	}

}
