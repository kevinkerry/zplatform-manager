package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.BnkTxnModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IBnkTxnService;

public class BnkTxnServiceImpl extends BaseServiceImpl<BnkTxnModel, Long> implements IBnkTxnService{

	private DAOContainer daoContainer;

	public DAOContainer getDaoContainer() {
		return daoContainer;
	}

	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}

	@Override
	public IBaseDAO<BnkTxnModel, Long> getDao() {
		return daoContainer.getBnktxnDAO();
	}
	
	public List<Map<String, Object>> saveBnkTxn(BnkTxnModel bnktxn) {
		String[] columns = new String[] { 
				  "v_instiid", "v_payordno","v_txndatetime", "v_busicode", "v_amount ",
				  "v_pan","v_merchno","v_systrcno","v_paytrcno","v_cfee",
				  "v_dfee","v_retcode","v_acqsettledate"
		};
		Object[] paramaters =new Object[] {
				"".equals(bnktxn.getInstiid()) ? null : bnktxn.getInstiid(),
				"".equals(bnktxn.getPayordno()) ? null : bnktxn.getPayordno(),
				"".equals(bnktxn.getTxndatetime()) ? null : bnktxn.getTxndatetime(),
				"".equals(bnktxn.getBusicode()) ? null : bnktxn.getBusicode(),
				"".equals(bnktxn.getAmount()) ? null : bnktxn.getAmount(),
						
				"".equals(bnktxn.getPan()) ? null : bnktxn.getPan() ,
				"".equals(bnktxn.getMerchno()) ? null : bnktxn.getMerchno() ,
				"".equals(bnktxn.getSystrcno()) ? null : bnktxn.getSystrcno() ,
				"".equals(bnktxn.getPaytrcno()) ? null : bnktxn.getPaytrcno() ,
				"".equals(bnktxn.getCfee()) ? null : bnktxn.getCfee() ,
				
				"".equals(bnktxn.getDfee()) ? null : bnktxn.getDfee() ,
				//		"1","1",
				"".equals(bnktxn.getRetcode()) ? null : bnktxn.getRetcode() ,
				"".equals(bnktxn.getAcqsettledate()) ? null : bnktxn.getAcqsettledate() };
		return getDao().executeOracleProcedure(
				"{CALL PCK_T_BNK_TXN.ins_t_bnk_txn_nocur(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}
	// 判断是否重复上传文件
	public  Boolean  upLoad(String uploadFileName){
	    List<?> bnktxnlist= getDao().queryByHQL("from UploadLogModel where filename=? and recode='00' ",
                new Object[]{uploadFileName});
	    
	    if(bnktxnlist.size()>0){
	        return true;
	    }else{
	        return false;
	    }
	    
	}
	
	// 等对账数据保存成功后，更新UPload表的上传数据状态
	public void updateUploadLog(String uploadFileName){
	    getDao().updateByHQL( "update UploadLogModel set recode='00' where filename=? ",new Object[]{uploadFileName});
	}
	
	public void deleteFailedWechatUploadLog(String uploadFileName){
		getDao().updateByHQL( "delete from UploadLogModel where filename=? ",new Object[]{uploadFileName});
	}
	
	
}
