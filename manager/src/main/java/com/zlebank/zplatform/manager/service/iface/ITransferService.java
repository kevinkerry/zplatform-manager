/* 
 * ITransferService.java  
 * 
 * version TODO
 *
 * 2015年12月8日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.BankTranBatch;
import com.zlebank.zplatform.trade.bean.enums.BankTransferBatchOpenStatusEnum;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.model.PojoTranData;

/**
 * A service interface represent transfer
 * 
 * @author yangpeng
 * @author guojia
 * @author yangying
 * @version 1.3.0
 * @date 2015年12月8日 下午3:57:33
 * @since 1.1.0
 */
public interface ITransferService
        extends
            IBasePageService<TransferDataQuery, TransferData> {

    /**
     * Query a list of transfer batch by paged
     * 
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     * @since 1.3.0
     */
    public Map<String, Object> queryBatchTransfer(QueryTransferBean queryTransferBean,
            int page,
            int pageSize);

    /**
     * Query a list of transfer data by paged
     * 
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @since 1.3.0
     * @return
     */
    public Map<String, Object> queryDetaTransfer(QueryTransferBean queryTransferBean,
            int page,
            int pageSize);

    /**
     * Transfer batch trial
     * 
     * @param batchId
     *            the batch id will be approved
     * @param flag
     *            true represent pass,else represent refuse
     * @since 1.3.0
     * @return false if there is exception
     */
    public boolean transferBatchTrial(long batchId, boolean flag);

    /**
     * Transfer data trial
     * 
     * @param tid
     *            the transfer data will be approved
     * @param flag
     *            true represent pass,else represent refuse
     * @since 1.3.0
     * @return false if there is exception
     */
    public boolean transferDataTrial(Long tid, boolean flag);

    /**
     * 针对各个业务（代付/提现/退款）的业务退款方法，交易失败或审核拒绝时
     * 
     * @param transferData
     * @param businessEnum
     * @since 1.3.0
     */
    public void businessRefund(List<PojoTranData> transferDataList);

    /**
     * 更新划拨明细状态，主要用于转账结果的更新
     * 
     * @param tid
     * @param status
     * @since 1.3.0
     */
    public void updateTransferDataToFinish(Long tid, String status);
    /**
     * Query a list of bank transfer batch by transfer batch
     * @param tranBatchId
     * @param openStatus
     * @return
     * @since 1.3.0
     */
    public List<BankTranBatch> queryBankTranBatchByTranBatch(long tranBatchId,BankTransferBatchOpenStatusEnum openStatus);
}
