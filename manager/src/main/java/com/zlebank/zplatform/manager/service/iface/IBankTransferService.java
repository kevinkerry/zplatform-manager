package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.model.PojoBankTransferData;

public interface IBankTransferService extends IBaseService<PojoBankTransferData, Long>{
	/**
     * 查询转账批次数据
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    public Map<String, Object> queryBatchBankTransfer(QueryTransferBean queryTransferBean,int page,int pageSize);
    
    /**
     * 查询转账明细数据
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    public Map<String, Object> queryDataBankTransfer(QueryTransferBean queryTransferBean,int page,int pageSize);
    
    /**
     * 转账批次审核
     * @param batchNo
     * @param transferTrialEnum
     * @return
     */
    public Map<String, Object> bankTransferBatchTrial(String batchNo,boolean flag,Long userId);
    
    /**
     * 手工关闭转账批次
     * @param tid
     * @return
     */
    public boolean colseBankTransferBatch(Long tid);

}
