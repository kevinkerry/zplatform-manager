package com.zlebank.zplatform.manager.service.container;

import com.zlebank.zplatform.manager.service.iface.IBnkTxnService;
import com.zlebank.zplatform.manager.service.iface.ICityService;
import com.zlebank.zplatform.manager.service.iface.IDeptService;
import com.zlebank.zplatform.manager.service.iface.IFeeService;
import com.zlebank.zplatform.manager.service.iface.IFunctionService;
import com.zlebank.zplatform.manager.service.iface.ILimitPerdayService;
import com.zlebank.zplatform.manager.service.iface.IMccListService;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manager.service.iface.INoticeService;
import com.zlebank.zplatform.manager.service.iface.IOperLogService;
import com.zlebank.zplatform.manager.service.iface.IOrganService;
import com.zlebank.zplatform.manager.service.iface.IParaDicService;
import com.zlebank.zplatform.manager.service.iface.IProductService;
import com.zlebank.zplatform.manager.service.iface.IProvinceService;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.manager.service.iface.IRoleFunctService;
import com.zlebank.zplatform.manager.service.iface.IRoleService;
import com.zlebank.zplatform.manager.service.iface.ITaskService;
import com.zlebank.zplatform.manager.service.iface.IUploadLogService;
import com.zlebank.zplatform.manager.service.iface.IUserFunctService;
import com.zlebank.zplatform.manager.service.iface.IUserRoleService;
import com.zlebank.zplatform.manager.service.iface.IUserService;

public class ServiceContainer {

	private IOperLogService operLogService;
	private IUserService userService;
	private IFunctionService functionService;
	private ICityService cityService;
	private IProvinceService provinceService;
	private IOrganService organService;
	private IDeptService deptService;
	private IRoleService roleService;
	private IUserFunctService userFunctService;
	private IRoleFunctService roleFunctService;
	private ITaskService taskService;
	private IUserRoleService userRoleService;
	private INoticeService noticeService;
	private IMerchDetaService merchDetaService;
	private IUploadLogService uploadlogService;
	private IBnkTxnService bnktxnService;
	private IProductService productService;
	private IFeeService feeService;
	private IRiskService riskService;
	private ILimitPerdayService limitperdayService;
	private IMccListService mccListService;
	private IParaDicService paraDicService;
	
	public IOperLogService getOperLogService() {
		return operLogService;
	}

	public void setOperLogService(IOperLogService operLogService) {
		this.operLogService = operLogService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IFunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(IFunctionService functionService) {
		this.functionService = functionService;
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	public IProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(IProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	public IOrganService getOrganService() {
		return organService;
	}

	public void setOrganService(IOrganService organService) {
		this.organService = organService;
	}

	public IDeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IUserFunctService getUserFunctService() {
		return userFunctService;
	}

	public void setUserFunctService(IUserFunctService userFunctService) {
		this.userFunctService = userFunctService;
	}

	public IRoleFunctService getRoleFunctService() {
		return roleFunctService;
	}

	public void setRoleFunctService(IRoleFunctService roleFunctService) {
		this.roleFunctService = roleFunctService;
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public IMerchDetaService getMerchDetaService() {
		return merchDetaService;
	}

	public void setMerchDetaService(IMerchDetaService merchDetaService) {
		this.merchDetaService = merchDetaService;
	}

	public IUploadLogService getUploadlogService() {
		return uploadlogService;
	}

	public void setUploadlogService(IUploadLogService uploadlogService) {
		this.uploadlogService = uploadlogService;
	}

	public IBnkTxnService getBnktxnService() {
		return bnktxnService;
	}

	public void setBnktxnService(IBnkTxnService bnktxnService) {
		this.bnktxnService = bnktxnService;
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	public IFeeService getFeeService() {
		return feeService;
	}

	public void setFeeService(IFeeService feeService) {
		this.feeService = feeService;
	}

	public IRiskService getRiskService() {
		return riskService;
	}

	public void setRiskService(IRiskService riskService) {
		this.riskService = riskService;
	}

	public ILimitPerdayService getLimitperdayService() {
		return limitperdayService;
	}

	public void setLimitperdayService(ILimitPerdayService limitperdayService) {
		this.limitperdayService = limitperdayService;
	}
	
	public IMccListService getMccListService() {
        return mccListService;
    }

    public void setMccListService(IMccListService mccListService) {
        this.mccListService = mccListService;
    }

    public IParaDicService getParaDicService() {
        return paraDicService;
    }

    public void setParaDicService(IParaDicService paraDicService) {
        this.paraDicService = paraDicService;
    }
    
}
