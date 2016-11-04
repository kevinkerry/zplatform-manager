package com.zlebank.zplatform.manager.service.container;

import com.zlebank.zplatform.manager.service.iface.IBnkTxnService;
import com.zlebank.zplatform.manager.service.iface.ICardHolderBlackService;
import com.zlebank.zplatform.manager.service.iface.IChannelService;
import com.zlebank.zplatform.manager.service.iface.ICityService;
import com.zlebank.zplatform.manager.service.iface.IDeptService;
import com.zlebank.zplatform.manager.service.iface.IEnterpriseRealNameService;
import com.zlebank.zplatform.manager.service.iface.IFTPEnterpriseService;
import com.zlebank.zplatform.manager.service.iface.IFeeService;
import com.zlebank.zplatform.manager.service.iface.IFinProductService;
import com.zlebank.zplatform.manager.service.iface.IFunctionService;
import com.zlebank.zplatform.manager.service.iface.ILimitPerdayService;
import com.zlebank.zplatform.manager.service.iface.IMccListService;
import com.zlebank.zplatform.manager.service.iface.IMerchConfigService;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manager.service.iface.INoticeService;
import com.zlebank.zplatform.manager.service.iface.IOperLogService;
import com.zlebank.zplatform.manager.service.iface.IOrganService;
import com.zlebank.zplatform.manager.service.iface.IParaDicService;
import com.zlebank.zplatform.manager.service.iface.IProductService;
import com.zlebank.zplatform.manager.service.iface.IProvinceService;
import com.zlebank.zplatform.manager.service.iface.IRiskAnalyseLogService;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.manager.service.iface.IRoleFunctService;
import com.zlebank.zplatform.manager.service.iface.IRoleService;
import com.zlebank.zplatform.manager.service.iface.IRouteService;
import com.zlebank.zplatform.manager.service.iface.ITaskService;
import com.zlebank.zplatform.manager.service.iface.ITxnsLogsService;
import com.zlebank.zplatform.manager.service.iface.IUploadLogService;
import com.zlebank.zplatform.manager.service.iface.IUserFunctService;
import com.zlebank.zplatform.manager.service.iface.IUserRoleService;
import com.zlebank.zplatform.manager.service.iface.IUserService;
import com.zlebank.zplatform.manager.service.iface.IfundMerchantService;
import com.zlebank.zplatform.manager.service.iface.RaiseTrService;


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
	private ITxnsLogsService txnsLogsService;
	private ICardHolderBlackService cardHolderBlackService;
	private IRiskAnalyseLogService riskAnalyseLogService;
    private IChannelService channelService;
    private IRouteService routeService;
    private IEnterpriseRealNameService enterpriseRealnamService;
    private IMerchConfigService merchConfigService;
    private IfundMerchantService fundMerchantService;
    private RaiseTrService raiseTrService;
    
    
	public RaiseTrService getRaiseTrService() {
		return raiseTrService;
	}

	public void setRaiseTrService(RaiseTrService raiseTrService) {
		this.raiseTrService = raiseTrService;
	}

    private IFinProductService finProductService;
    private IFTPEnterpriseService ftpEnterpriseService;


	public IfundMerchantService getFundMerchantService() {
		return fundMerchantService;
	}


	public void setFundMerchantService(IfundMerchantService fundMerchantService) {
		this.fundMerchantService = fundMerchantService;
	}
    public IFTPEnterpriseService getFtpEnterpriseService() {
        return ftpEnterpriseService;
    }

    public void setFtpEnterpriseService(IFTPEnterpriseService ftpEnterpriseService) {
        this.ftpEnterpriseService = ftpEnterpriseService;
    }

    public IFinProductService getFinProductService() {
		return finProductService;
	}

	public void setFinProductService(IFinProductService finProductService) {
		this.finProductService = finProductService;
	}

	public IEnterpriseRealNameService getEnterpriseRealnamService() {
		return enterpriseRealnamService;
	}

	public void setEnterpriseRealnamService(
			IEnterpriseRealNameService enterpriseRealnamService) {
		this.enterpriseRealnamService = enterpriseRealnamService;
	}

	public IChannelService getChannelService() {
        return channelService;
    }

    public void setChannelService(IChannelService channelService) {
        this.channelService = channelService;
    }

    public IRiskAnalyseLogService getRiskAnalyseLogService() {
        return riskAnalyseLogService;
    }

    public void setRiskAnalyseLogService(IRiskAnalyseLogService riskAnalyseLogService) {
        this.riskAnalyseLogService = riskAnalyseLogService;
    }

    public ICardHolderBlackService getCardHolderBlackService() {
        return cardHolderBlackService;
    }

    public void setCardHolderBlackService(ICardHolderBlackService cardHolderBlackService) {
        this.cardHolderBlackService = cardHolderBlackService;
    }

    public ITxnsLogsService getTxnsLogsService() {
        return txnsLogsService;
    }

    public void setTxnsLogsService(ITxnsLogsService txnsLogsService) {
        this.txnsLogsService = txnsLogsService;
    }

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

    public IRouteService getRouteService() {
        return routeService;
    }

    public void setRouteService(IRouteService routeService) {
        this.routeService = routeService;
    }

    public IMerchConfigService getMerchConfigService() {
        return merchConfigService;
    }

    public void setMerchConfigService(IMerchConfigService merchConfigService) {
        this.merchConfigService = merchConfigService;
    }
    
    
}
