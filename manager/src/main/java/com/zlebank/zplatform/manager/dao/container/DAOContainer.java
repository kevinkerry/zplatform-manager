package com.zlebank.zplatform.manager.dao.container;

import com.zlebank.zplatform.manager.dao.EnterpriseRealNameDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IBnkTxnDAO;
import com.zlebank.zplatform.manager.dao.iface.ICardHolderBlackDAO;
import com.zlebank.zplatform.manager.dao.iface.IChannelDAO;
import com.zlebank.zplatform.manager.dao.iface.ICityDAO;
import com.zlebank.zplatform.manager.dao.iface.ICountyDAO;
import com.zlebank.zplatform.manager.dao.iface.IDeptDAO;
import com.zlebank.zplatform.manager.dao.iface.IEnterpriseDetaDAO;
import com.zlebank.zplatform.manager.dao.iface.IEnterpriseRealNameDAO;
import com.zlebank.zplatform.manager.dao.iface.IFTPDAO;
import com.zlebank.zplatform.manager.dao.iface.IFeeDAO;
import com.zlebank.zplatform.manager.dao.iface.IFunctionDAO;
import com.zlebank.zplatform.manager.dao.iface.ILimitPerDayDAO;
import com.zlebank.zplatform.manager.dao.iface.IMccListDAO;
import com.zlebank.zplatform.manager.dao.iface.IMemberQueueDAO;
import com.zlebank.zplatform.manager.dao.iface.IMerchDetaDAO;
import com.zlebank.zplatform.manager.dao.iface.INoticeDAO;
import com.zlebank.zplatform.manager.dao.iface.IOperLogDAO;
import com.zlebank.zplatform.manager.dao.iface.IOrganDAO;
import com.zlebank.zplatform.manager.dao.iface.IParaDicDAO;
import com.zlebank.zplatform.manager.dao.iface.IProductDAO;
import com.zlebank.zplatform.manager.dao.iface.IProvinceDAO;
import com.zlebank.zplatform.manager.dao.iface.IRiskAnalyseLogDAO;
import com.zlebank.zplatform.manager.dao.iface.IRiskDAO;
import com.zlebank.zplatform.manager.dao.iface.IRoleDAO;
import com.zlebank.zplatform.manager.dao.iface.IRoleFunctDAO;
import com.zlebank.zplatform.manager.dao.iface.IRouteDAO;
import com.zlebank.zplatform.manager.dao.iface.ITaskDAO;
import com.zlebank.zplatform.manager.dao.iface.ITxnsLogsDAO;
import com.zlebank.zplatform.manager.dao.iface.IUploadLogDAO;
import com.zlebank.zplatform.manager.dao.iface.IUserDAO;
import com.zlebank.zplatform.manager.dao.iface.IUserFunctDAO;
import com.zlebank.zplatform.manager.dao.iface.IUserRoleDAO;

public class DAOContainer {

	/**
	 * global
	 */
	private IProvinceDAO provinceDAO;
	private ICityDAO cityDAO;
	private IOperLogDAO operLogDAO;
	private IParaDicDAO paradicDAO;
	private ICountyDAO countyDAO;
	/**
	 * system manage
	 */
	private IUserDAO userDAO;
	private IOrganDAO organDAO;
	private IDeptDAO deptDAO;
	private IRoleDAO roleDAO;
	private IFunctionDAO functionDAO;
	private IUserFunctDAO userFunctDAO;
	private IRoleFunctDAO roleFunctDAO;
	private INoticeDAO noticeDAO;
	private ITaskDAO taskDAO;
	private IFTPDAO FTPDAO;
	private IUserRoleDAO userRoleDAO;
	private IMerchDetaDAO merchDetaDAO;
	private IUploadLogDAO uploadlogDAO;
	private IBnkTxnDAO bnktxnDAO;
	private IProductDAO productDAO;
	private IFeeDAO feeDAO;
	private IRiskDAO riskDAO;
	private ILimitPerDayDAO limitperdayDAO;
	private IMccListDAO mccListDAO;
	private IEnterpriseDetaDAO enterpriseDetaDAO;
	private IMemberQueueDAO memberQueueDAO;	
    private ITxnsLogsDAO txnsLogsDAO;
    private ICardHolderBlackDAO cardHolderBlackDAO;
    private IRiskAnalyseLogDAO riskAnalyseLogDAO;
    private IChannelDAO channelDAO;
    private IRouteDAO routeDAO;
    private IEnterpriseRealNameDAO enterpriseRealnameDAO;
	
    
    
    public IEnterpriseRealNameDAO getEnterpriseRealnameDAO() {
		return enterpriseRealnameDAO;
	}

	public void setEnterpriseRealnameDAO(IEnterpriseRealNameDAO enterpriseRealnameDAO) {
		this.enterpriseRealnameDAO = enterpriseRealnameDAO;
	}

	public IRouteDAO getRouteDAO() {
        return routeDAO;
    }

    public void setRouteDAO(IRouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    public IChannelDAO getChannelDAO() {
        return channelDAO;
    }

    public void setChannelDAO(IChannelDAO channelDAO) {
        this.channelDAO = channelDAO;
    }

    public IRiskAnalyseLogDAO getRiskAnalyseLogDAO() {
        return riskAnalyseLogDAO;
    }

    public void setRiskAnalyseLogDAO(IRiskAnalyseLogDAO riskAnalyseLogDAO) {
        this.riskAnalyseLogDAO = riskAnalyseLogDAO;
    }

    public ICardHolderBlackDAO getCardHolderBlackDAO() {
        return cardHolderBlackDAO;
    }

    public void setCardHolderBlackDAO(ICardHolderBlackDAO cardHolderBlackDAO) {
        this.cardHolderBlackDAO = cardHolderBlackDAO;
    }

    public ITxnsLogsDAO getTxnsLogsDAO() {
        return txnsLogsDAO;
    }

    public void setTxnsLogsDAO(ITxnsLogsDAO txnsLogsDAO) {
        this.txnsLogsDAO = txnsLogsDAO;
    }

    public IMemberQueueDAO getMemberQueueDAO() {
        return memberQueueDAO;
    }

    public void setMemberQueueDAO(IMemberQueueDAO memberQueueDAO) {
        this.memberQueueDAO = memberQueueDAO;
    }

    public IProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(IProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public ICityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(ICityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public IOperLogDAO getOperLogDAO() {
		return operLogDAO;
	}

	public void setOperLogDAO(IOperLogDAO operLogDAO) {
		this.operLogDAO = operLogDAO;
	}

	public IParaDicDAO getParadicDAO() {
		return paradicDAO;
	}

	public void setParadicDAO(IParaDicDAO paradicDAO) {
		this.paradicDAO = paradicDAO;
	}

	public ICountyDAO getCountyDAO() {
		return countyDAO;
	}

	public void setCountyDAO(ICountyDAO countyDAO) {
		this.countyDAO = countyDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IOrganDAO getOrganDAO() {
		return organDAO;
	}

	public void setOrganDAO(IOrganDAO organDAO) {
		this.organDAO = organDAO;
	}

	public IDeptDAO getDeptDAO() {
		return deptDAO;
	}

	public void setDeptDAO(IDeptDAO deptDAO) {
		this.deptDAO = deptDAO;
	}

	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public IFunctionDAO getFunctionDAO() {
		return functionDAO;
	}

	public void setFunctionDAO(IFunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	public IUserFunctDAO getUserFunctDAO() {
		return userFunctDAO;
	}

	public void setUserFunctDAO(IUserFunctDAO userFunctDAO) {
		this.userFunctDAO = userFunctDAO;
	}

	public IRoleFunctDAO getRoleFunctDAO() {
		return roleFunctDAO;
	}

	public void setRoleFunctDAO(IRoleFunctDAO roleFunctDAO) {
		this.roleFunctDAO = roleFunctDAO;
	}
	public INoticeDAO getNoticeDAO() {
		return noticeDAO;
	}
	public void setNoticeDAO(INoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	public ITaskDAO getTaskDAO() {
		return taskDAO;
	}
	public void setTaskDAO(ITaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}
	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	public IMerchDetaDAO getMerchDetaDAO() {
		return merchDetaDAO;
	}
	public void setMerchDetaDAO(IMerchDetaDAO merchDetaDAO) {
		this.merchDetaDAO = merchDetaDAO;
	}
	public IFTPDAO getFTPDAO() {
		return FTPDAO;
	}
	public void setFTPDAO(IFTPDAO fTPDAO) {
		FTPDAO = fTPDAO;
	}
	public IUploadLogDAO getUploadlogDAO() {
		return uploadlogDAO;
	}
	public void setUploadlogDAO(IUploadLogDAO uploadlogDAO) {
		this.uploadlogDAO = uploadlogDAO;
	}
	public IBnkTxnDAO getBnktxnDAO() {
		return bnktxnDAO;
	}
    public void setBnktxnDAO(IBnkTxnDAO bnktxnDAO) {
		this.bnktxnDAO = bnktxnDAO;
	}
	public IProductDAO getProductDAO() {
		return productDAO;
	}
	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	public IFeeDAO getFeeDAO() {
		return feeDAO;
	}
	public void setFeeDAO(IFeeDAO feeDAO) {
		this.feeDAO = feeDAO;
	}
	public IRiskDAO getRiskDAO() {
		return riskDAO;
	}
	public void setRiskDAO(IRiskDAO riskDAO) {
		this.riskDAO = riskDAO;
	}
	public ILimitPerDayDAO getLimitperdayDAO() {
		return limitperdayDAO;
	}
	public void setLimitperdayDAO(ILimitPerDayDAO limitperdayDAO) {
		this.limitperdayDAO = limitperdayDAO;
	}
	public IMccListDAO getMccListDAO() {
		return mccListDAO;
	}
	public void setMccListDAO(IMccListDAO mccListDAO) {
		this.mccListDAO = mccListDAO;
	}

    public IEnterpriseDetaDAO getEnterpriseDetaDAO() {
        return enterpriseDetaDAO;
    }

    public void setEnterpriseDetaDAO(IEnterpriseDetaDAO enterpriseDetaDAO) {
        this.enterpriseDetaDAO = enterpriseDetaDAO;
    }


}
