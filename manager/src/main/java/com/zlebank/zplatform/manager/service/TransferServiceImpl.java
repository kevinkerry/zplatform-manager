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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.manager.bean.BankTranBatch;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.enums.TransferTrialEnum;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.trade.batch.spliter.BatchSpliter;
import com.zlebank.zplatform.trade.bean.UpdateData;
import com.zlebank.zplatform.trade.bean.enums.BankTransferBatchOpenStatusEnum;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.BankTransferBatchDAO;
import com.zlebank.zplatform.trade.dao.BankTransferDataDAO;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoBankTransferBatch;
import com.zlebank.zplatform.trade.model.PojoTranBatch;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.service.UpdateSubject;

/**
 * 
 * @author yangpeng
 * @version
 * @date 2015年12月8日 下午4:17:10
 * @since 1.1.0
 */
@Service
public class TransferServiceImpl
        extends
           BaseServiceImpl<PojoTranData, Long>
        implements
            ITransferService {
    @Autowired
    private TransferBatchDAO transferBatchDAO;
    @Autowired
    private TransferDataDAO transferDataDAO;
    @Autowired
    private AccEntryService accEntryService;
    @Autowired
    private BatchSpliter batchSpliter;
    @Autowired
    private BankTransferBatchDAO bankTransferBatchDAO;
    @Autowired
    private BankTransferDataDAO bankTransferDataDAO;
    @Autowired
    private UpdateSubject updateSubject;

    @Override
    public PagedResult<TransferData> queryPaged(int page, int pageSize,
            TransferDataQuery example) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Map<String, Object> queryBatchTransfer(
            QueryTransferBean queryTransferBean, int page, int pageSize) {
        try {
			return transferBatchDAO.queryTransferBatchByPage(queryTransferBean, page, pageSize);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }


    @Override
    public IBaseDAO<PojoTranData, Long> getDao() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
    /**
     * 针对各个业务（代付/提现/退款）的业务退款方法，交易失败或审核拒绝时
     * @param transferData
     * @param businessEnum
     */
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void businessRefund(List<PojoTranData> transferDataList) {
    	for(PojoTranData transferData : transferDataList){
    		UpdateData updateData = new UpdateData();
            updateData.setTxnSeqNo(transferData.getTxnseqno());
            updateData.setResultCode("09");
            updateData.setResultMessage("审核拒绝");
            updateData.setChannelCode("");
            updateSubject.update(updateData);
    	}
    }

	
	@Override
	public boolean transferBatchTrial (long batchId, boolean flag,Long userId) {
		try {
			TransferTrialEnum transferTrialEnum = null;
			if(flag){
				transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
			}else {
				transferTrialEnum = TransferTrialEnum.REFUSED;
			}
			//获取等待审核的划拨数据，其他状态的不做处理
			List<PojoTranData> transferDataList = transferBatchDAO.queryWaitTrialTranData(batchId);
	    	//统计审核通过和不同过的数据，笔数和金额
			long approveCount = 0L;
			long approveAmount = 0L;
			long unApproveCount = 0L;
			long unApproveAmount = 0L;
			PojoTranBatch transferBatch = transferBatchDAO.getByBatchId(batchId);
			switch (transferTrialEnum) {
				case SUCCESSFUL:
					PojoTranData[] pojoTransferDatas = new PojoTranData[transferDataList.size()];
		    		transferDataList.toArray(pojoTransferDatas);
		    		//调用分批算法
		    		batchSpliter.split(pojoTransferDatas);
		    		//更划拨新批次信息
		    		for(PojoTranData transferData : transferDataList){
		    			transferData.setStatus("00");
		    			transferData.setApproveTime(new Date());
		    			transferData.setApproveUser(userId);
		    			if("00".equals(transferData.getStatus())){
		    				approveCount++;
		    				approveAmount+=transferData.getTranAmt().longValue();
		    			}else if("01".equals(transferData.getStatus())){//为待审状态时不做处理
		    				//unApproveCount++;
		    				//unApproveAmount+=transferData.getTranAmt().longValue();
		    			}else{
		    				unApproveCount++;
		    				unApproveAmount+=transferData.getTranAmt().longValue();
		    			}
		    			transferDataDAO.update(transferData);
		    		}
		    		transferBatch.setApproveAmt(transferBatch.getApproveAmt().longValue()+approveAmount);
		    		transferBatch.setApproveCount(approveCount+transferBatch.getApproveCount());
		    		transferBatch.setRefuseAmt(transferBatch.getRefuseAmt()+unApproveAmount);
		    		transferBatch.setRefuseCount(unApproveCount+transferBatch.getRefuseCount());
		    		transferBatch.setApproveFinishTime(new Date());
		    		if(transferBatch.getRefuseCount()>0){
		    			transferBatch.setStatus("02");
		    		}else{
		    			transferBatch.setStatus("03");
		    		}
		    		
		    		transferDataDAO.updateBatchTransferSingle(transferBatch);
					break;
				case REFUSED:
					for(PojoTranData transferData : transferDataList){
		    			unApproveCount++;
						unApproveAmount+=transferData.getTranAmt().longValue();
						transferData.setStatus(transferTrialEnum.getCode());
						transferData.setApproveUser(userId);
						transferDataDAO.update(transferData);
		    		}
		    		//业务退款
		    		businessRefund(transferDataList);
		    		transferBatch.setRefuseAmt(transferBatch.getRefuseAmt()+unApproveAmount);
		    		transferBatch.setRefuseCount(unApproveCount+transferBatch.getRefuseCount());
		    		if(transferBatch.getRefuseCount()>0){
		    			transferBatch.setStatus("02");
		    		}else{
		    			transferBatch.setStatus("03");
		    		}
		    		transferDataDAO.updateBatchTransferSingle(transferBatch);
					break;
				default:
					break;
			}
			
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	 
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public boolean transferDataTrial(Long tid,boolean flag,Long userId){
		try {
			TransferTrialEnum transferTrialEnum = null;
			if(flag){
				transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
			}else {
				transferTrialEnum = TransferTrialEnum.REFUSED;
			}
			PojoTranData transferData = transferDataDAO.queryTransferData(tid);
			if(transferData==null){
				return false;
			}
	    	//统计审核通过和不同过的数据，笔数和金额
			long approveCount = 0L;
			long approveAmount = 0L;
			long unApproveCount = 0L;
			long unApproveAmount = 0L;
			//判断划拨明细数据状态
	    	PojoTranBatch transferBatch = transferData.getTranBatch();
	    	switch (transferTrialEnum) {
				case SUCCESSFUL:
					PojoTranData[] pojoTransferDatas = new PojoTranData[]{transferData};
		    		//调用分批算法
		    		batchSpliter.split(pojoTransferDatas);
					transferData.setStatus("00");
					transferData.setApproveTime(new Date());
					if("00".equals(transferData.getStatus())){
						approveCount++;
						approveAmount+=transferData.getTranAmt().longValue();
					}else if("01".equals(transferData.getStatus())){
						unApproveCount++;
						unApproveAmount+=transferData.getTranAmt().longValue();
					}else{
						unApproveCount++;
						unApproveAmount+=transferData.getTranAmt().longValue();
					}
					transferDataDAO.update(transferData);
					//更新审核结果笔数和金额
		    		transferBatch.setApproveAmt(transferBatch.getApproveAmt()+approveAmount);
		    		transferBatch.setApproveCount(approveCount+transferBatch.getApproveCount());
		    		transferBatch.setRefuseAmt(transferBatch.getRefuseAmt()+unApproveAmount);
		    		transferBatch.setRefuseCount(unApproveCount+transferBatch.getRefuseCount());
					break;
				case REFUSED:
					transferData.setStatus("09");
					transferData.setApproveTime(new Date());
					unApproveCount++;
					unApproveAmount+=transferData.getTranAmt().longValue();
					transferData.setStatus(transferTrialEnum.getCode());
					transferDataDAO.update(transferData);
					//业务退款
					List<PojoTranData> tranDataList =  new ArrayList<PojoTranData>();
					tranDataList.add(transferData);
		    		businessRefund(tranDataList);
		    		
		    		transferBatch.setRefuseAmt(transferBatch.getRefuseAmt()+unApproveAmount);
		    		transferBatch.setRefuseCount(unApproveCount+transferBatch.getRefuseCount());
					break;
				default:
					break;
			}
	    	transferDataDAO.updateBatchTransferSingle(transferBatch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

    
    

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public Map<String, Object> queryDetaTransfer(
            QueryTransferBean queryTransferBean, int page, int pageSize) {
        // TODO Auto-generated method stub
        return transferDataDAO.queryTranfersDetaByPage(queryTransferBean, page, pageSize);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
    public void updateTransferDataToFinish(Long tid, String status) {
        //更新划拨结果状态
        transferDataDAO.updateTransferDataStatus(tid, status);
        //判断划拨批次是否全部完成,判断状态为02的数据的数量
        PojoTranData pojoTranData= transferDataDAO.queryTransferData(tid);
        Long count = transferDataDAO.queryWaritTransferCount(pojoTranData.getTranBatch().getTid());
        if(count==0){//批次转账全部完成
            PojoTranBatch pojoTranBatch = pojoTranData.getTranBatch();
            pojoTranBatch.setStatus("00");
            transferBatchDAO.update(pojoTranBatch);
        }
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<BankTranBatch> queryBankTranBatchByTranBatch(long tranBatchId,BankTransferBatchOpenStatusEnum openStatus) {
        List<PojoBankTransferBatch> bankTransferBatchs = bankTransferBatchDAO.getByTranBatchAndOpenStatus(tranBatchId, openStatus);
        
        List<BankTranBatch> bankTransferBatchCoper = new ArrayList<BankTranBatch>();
        for(PojoBankTransferBatch copySource:bankTransferBatchs){
            BankTranBatch copyTarget = new BankTranBatch();
            BeanUtils.copyProperties(copySource, copyTarget, "tranBatchs","bankTranDatas");
            bankTransferBatchCoper.add(copyTarget);
        }
        return bankTransferBatchCoper;
    }
}
