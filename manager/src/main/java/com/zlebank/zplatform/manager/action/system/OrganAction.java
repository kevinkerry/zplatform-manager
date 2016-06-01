package com.zlebank.zplatform.manager.action.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.CityModel;
import com.zlebank.zplatform.manager.dao.object.OrganModel;
import com.zlebank.zplatform.manager.dao.object.ProvinceModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class OrganAction extends BaseAction{

	private static final long serialVersionUID = -5212979447414357931L;
	
	private ServiceContainer serviceContainer;
	private List<ProvinceModel> provinceList;
	private List<CityModel> cityList;
	private List<OrganModel> organList;
	private long organId;
	private long pid;
	private OrganModel organ;
	private String organName;
	private String organCode;
	

	public String show(){
		return SUCCESS;
	}
	/**
	 * 获取市信息集合
	 * @return
	 */
	public String queryCity(){
		try {
			cityList = serviceContainer.getCityService().findByProperty("PId", pid);
			json_encode(cityList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取省信息集合
	 * @return
	 */
	public String queryProvince(){
		try {
			provinceList = serviceContainer.getProvinceService().findAll();
			json_encode(provinceList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取得上级机构集合
	 * @return
	 */
	public String getSuper(){
		try {
			/*organList = serviceContainer.getOrganService().findByProperty("superid", 1L);*/
			 List<OrganModel> organList = (List<OrganModel>) serviceContainer.getOrganService().queryByHQL("from OrganModel rm where rm.levelid='1' or rm.levelid='0'", new Object[]{});
			json_encode(organList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除组织机构   
	 * @return
	 */
	public String delete(){
		try {
			List<?> returnList = serviceContainer.getOrganService().deleteOrgan(organId);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 新增组织机构信息
	 * @return
	 */
	public String save(){
		try {
			organ.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getOrganService().saveOrgan(organ);
			json_encode(returnList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新组织机构信息
	 * @return
	 */
	public String update(){
		try {
			organ.setCreator(getCurrentUser().getLoginName());
			List<?> returnList = serviceContainer.getOrganService().updateOrgan(organ);
			json_encode(returnList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 通过ID获取组织机构信息
	 * @return
	 */
	public String getSingleById(){
		Map<String, Object> variable = new HashMap<String, Object>();
		OrganModel organModel = serviceContainer.getOrganService().get(organId);
		cityList = serviceContainer.getCityService().findByProperty("PId",Long.valueOf(organModel.getProvince().toString()));
		variable.put("organModel", organModel);
		variable.put("cityList", cityList);
		json_encode(variable);
		return null;
	}
	
	/**
	 * 查询组织机构信息
	 * @return
	 */
	public String query(){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("organName", organName);
		variables.put("organCode", organCode);
		Map<String, Object> memberList = serviceContainer.getOrganService().findOrganByPage(variables, getPage(), getRows());
		json_encode(memberList);
		return null;
	}


	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}


	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public List<ProvinceModel> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceModel> provinceList) {
		this.provinceList = provinceList;
	}

	public List<CityModel> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityModel> cityList) {
		this.cityList = cityList;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}
	public List<OrganModel> getOrganList() {
		return organList;
	}
	public void setOrganList(List<OrganModel> organList) {
		this.organList = organList;
	}
	public long getOrganId() {
		return organId;
	}
	public void setOrganId(long organId) {
		this.organId = organId;
	}
	public OrganModel getOrgan() {
		return organ;
	}
	public void setOrgan(OrganModel organ) {
		this.organ = organ;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getOrganCode() {
		return organCode;
	}
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

}
