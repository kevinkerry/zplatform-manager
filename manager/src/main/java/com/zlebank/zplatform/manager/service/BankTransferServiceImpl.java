/* 
 * TransferServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月8日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.enums.TransferTrialEnum;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IBankTransferService;
import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.BankTransferBatchDAO;
import com.zlebank.zplatform.trade.dao.BankTransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoBankTransferBatch;
import com.zlebank.zplatform.trade.model.PojoBankTransferData;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2015年12月8日 下午4:17:10
 * @since
 */
@Service("bankTransferService")
public class BankTransferServiceImpl extends BaseServiceImpl<PojoBankTransferData, Long> implements IBankTransferService{
	@Autowired
	private BankTransferBatchDAO bankTransferBatchDAO;
	@Autowired
	private BankTransferDataDAO bankTransferDataDAO;
    @Autowired
    private ITransferService transferService;

	@Override
	public IBaseDAO<PojoBankTransferData, Long> getDao() {
		
		return null;
	}

	/**
	 * 转账批次审核
	 * @param batchNo
	 * @param flag
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public boolean bankTransferBatchTrial(String batchNo,boolean flag){
		try {
			TransferTrialEnum transferTrialEnum = null;
			if(flag){
				transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
			}else {
				transferTrialEnum = TransferTrialEnum.REFUSED;
			}
	    	
			PojoBankTransferBatch transferBatch = bankTransferBatchDAO.getByBankTranBatchNo(Long.valueOf(batchNo));
	    	if("00".equals(transferTrialEnum.getCode())){
	    		//更新全部转账数据状态，等待转账
	    		bankTransferDataDAO.updateWaitBankTransferStatus(batchNo, "02");
	    	}else{
	    		//更新全部转账数据状态，拒绝转账
	    		bankTransferDataDAO.updateWaitBankTransferStatus(batchNo, "04");
	    		//处理划拨流程中的数据
	    		//获取全部为审核的转账数据
				List<PojoBankTransferData> bankTransferDataList = bankTransferDataDAO.findTransDataByBatchNo(batchNo);
	    		for(PojoBankTransferData bankTransferData : bankTransferDataList){
	    			transferService.updateTransferDataToFinish(Long.valueOf(bankTransferData.getTranData().getTid()),"09");
	    		}
	    	}
	    	transferBatch.setStatus("02");
	    	transferBatch.setTranStatus("01");
	    	//更新批次状态
	    	bankTransferBatchDAO.update(transferBatch);
	    	//开始划拨
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Override
	public Map<String, Object> queryBatchBankTransfer(
			QueryTransferBean queryTransferBean, int page, int pageSize) {
		// TODO Auto-generated method stub
		return bankTransferBatchDAO.queryBankTransferByPage(queryTransferBean, page, pageSize);
	}


	@Override
	public Map<String, Object> queryDataBankTransfer(
			QueryTransferBean queryTransferBean, int page, int pageSize) {
		// TODO Auto-generated method stub
		return bankTransferDataDAO.queryBankTransferDataByPage(queryTransferBean, page, pageSize);
	}


	
}
