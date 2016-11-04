package com.zlebank.zplatform.manager.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.scan.MerchConfigModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IMerchConfigService;
@Service
public class MerchConfigServiceImpl extends BaseServiceImpl<MerchConfigModel, Long> implements IMerchConfigService {
	 private final static Log log = LogFactory
	            .getLog(MerchDetaServiceImpl.class);
	
	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<MerchConfigModel, Long> getDao() {
		return daoContainer.getMerchConfigDao();
	}
	@Override
	public List<MerchConfigModel> queryList(String memberId, String typeId) {
		String hql=" from MerchConfigModel where memberId=? and typeId=? and status=? ";
		return (List<MerchConfigModel>) getDao().queryByHQL(hql, new Object[]{memberId, typeId, "00"});
	}
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	
	
	
	


	
	

}
