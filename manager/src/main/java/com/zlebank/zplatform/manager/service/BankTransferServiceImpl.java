
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

import java.text.ParseException;
import java.util.HashMap;
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
import com.zlebank.zplatform.trade.adapter.insteadpay.IInsteadPayTrade;
import com.zlebank.zplatform.trade.bean.ResultBean;
import com.zlebank.zplatform.trade.bean.UpdateData;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.BankTransferBatchDAO;
import com.zlebank.zplatform.trade.dao.BankTransferDataDAO;
import com.zlebank.zplatform.trade.factory.TradeAdapterFactory;
import com.zlebank.zplatform.trade.model.PojoBankTransferBatch;
import com.zlebank.zplatform.trade.model.PojoBankTransferData;
import com.zlebank.zplatform.trade.service.ObserverListService;


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
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=Throwable.class)
	public Map<String, Object> bankTransferBatchTrial(String batchNo,boolean flag,Long userId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//判断转账批次状态
		PojoBankTransferBatch transferBatch = bankTransferBatchDAO.getByBankTranBatchNo(Long.valueOf(batchNo));
		if("0".equals(transferBatch.getOpenStatus())){//未关闭的禁止转账
			resultMap.put("retcode", "09");
			resultMap.put("retinfo", "批次号:"+transferBatch.getBankTranBatchNo()+"未关闭不可转账");
			return resultMap;
		}
		try {
			//只有审核通过和待审核的数据可以进行转账操作
			if(transferBatch.getStatus().equals("01")&&transferBatch.getTranStatus().equals("01")){
				TransferTrialEnum transferTrialEnum = null;
				if(flag){
					transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
				}else {
					transferTrialEnum = TransferTrialEnum.REFUSED;
				}
		    	
				transferBatch.setTranUser(userId);
		    	if("00".equals(transferTrialEnum.getCode())){
		    		//更新全部转账数据状态，等待转账
		    		bankTransferDataDAO.updateWaitBankTransferStatus(batchNo, "02");
		    		transferBatch.setStatus("02");//审核完成状态
			    	transferBatch.setTranStatus("01");//等待转账状态
			    	//更新批次状态
			    	bankTransferBatchDAO.updateTransferBatch(transferBatch);
			    	//开始划拨
			    	try {
						IInsteadPayTrade insteadPayTrade = TradeAdapterFactory.getInstance().getInsteadPayTrade(transferBatch.getChannel().getBankChannelCode());
						ResultBean resultBean = insteadPayTrade.batchPay(batchNo);
						if(!resultBean.isResultBool()){
							bankTransferDataDAO.updateBankTransferStatus(batchNo, "01");
							transferBatch.setStatus("01");//审核完成状态
					    	transferBatch.setTranStatus("01");//等待转账状态
					    	//更新批次状态
					    	bankTransferBatchDAO.updateTransferBatch(transferBatch);
					    	resultMap.put("retcode", "09");
							resultMap.put("retinfo", "批次号:"+transferBatch.getBankTranBatchNo()+"转账失败");
							return resultMap;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//强制回滚，防止出现事务异常时，没有回滚
						bankTransferDataDAO.updateBankTransferStatus(batchNo, "01");
						transferBatch.setStatus("01");//审核完成状态
				    	transferBatch.setTranStatus("01");//等待转账状态
				    	//更新批次状态
				    	bankTransferBatchDAO.updateTransferBatch(transferBatch);
				    	resultMap.put("retcode", "09");
						resultMap.put("retinfo", "批次号:"+transferBatch.getBankTranBatchNo()+"转账失败");
						return resultMap;
					}
		    	}else{
		    		//更新全部转账数据状态，拒绝转账
		    		bankTransferDataDAO.updateWaitBankTransferStatus(batchNo, "04");
		    		//处理划拨流程中的数据
		    		//获取全部为审核的转账数据
					List<PojoBankTransferData> bankTransferDataList = bankTransferDataDAO.findTransDataByBatchNo(Long.valueOf(batchNo));
		    		for(PojoBankTransferData bankTransferData : bankTransferDataList){
		    			transferService.updateTransferDataToFinish(Long.valueOf(bankTransferData.getTranData().getTid()),"09");
		    			UpdateData updateData = new UpdateData();
		                updateData.setTxnSeqNo(bankTransferData.getTranData().getTxnseqno());
		                updateData.setResultCode("02");
		                updateData.setResultMessage("审核拒绝");
		                ObserverListService.getInstance().notify(updateData, bankTransferData.getTranData().getBusiType());
		    		}
		    		transferBatch.setStatus("03");
			    	transferBatch.setTranStatus("04");
			    	//更新批次状态
			    	bankTransferBatchDAO.update(transferBatch);
		    	}
		    	
			}else{
				resultMap.put("retcode", "09");
				resultMap.put("retinfo", "批次号:"+transferBatch.getBankTranBatchNo()+"不是待转账批次");
				return resultMap;
			}
			
			
		} catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("retcode", "09");
			resultMap.put("retinfo", "批次号:"+transferBatch.getBankTranBatchNo()+"转账失败");
			/*if(!transferBatch.getStatus().equals("01")&&!transferBatch.getTranStatus().equals("01")){
				bankTransferDataDAO.updateBankTransferStatus(batchNo, "01");
				transferBatch.setStatus("01");//审核完成状态
		    	transferBatch.setTranStatus("");//等待转账状态
		    	//更新批次状态
		    	bankTransferBatchDAO.updateTransferBatch(transferBatch);
			}*/
			return resultMap;
		}
		resultMap.put("retcode", "00");
		resultMap.put("retinfo", "转账成功");
		return resultMap;
	}
	

	@Override
	public Map<String, Object> queryBatchBankTransfer(
			QueryTransferBean queryTransferBean, int page, int pageSize) {
		// TODO Auto-generated method stub
		try {
			return bankTransferBatchDAO.queryBankTransferByPage(queryTransferBean, page, pageSize);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Map<String, Object> queryDataBankTransfer(
			QueryTransferBean queryTransferBean, int page, int pageSize) {
		return bankTransferDataDAO.queryBankTransferDataByPage(queryTransferBean, page, pageSize);
	}

	@Override
	public boolean colseBankTransferBatch(Long tid) {
		return bankTransferBatchDAO.closeBankTransferBatch(tid);
	}


	
}
