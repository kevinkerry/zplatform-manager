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

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.enums.TransferTrialEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.trade.bean.enums.BusinessEnum;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.model.PojoTranData;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月8日 下午3:57:33
 * @since 
 */
public interface ITransferService extends IBasePageService<TransferDataQuery,TransferData> {
   
    /**************************以下为1.3版本新增内容***********************************/
    
    
    /**
     * 
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    public Map<String, Object> queryBatchTransfer(QueryTransferBean queryTransferBean,int page,int pageSize);
    
    /**
     * 
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    public Map<String, Object> queryDetaTransfer(QueryTransferBean queryTransferBean,int page,int pageSize);
    
    /**
     * 
     * @param batchNo
     * @param transferTrialEnum
     * @return
     */
    public boolean transferBatchTrial(String batchNo,boolean flag);
    
    /**
     * 
     * @param tid
     * @param transferTrialEnum
     * @return
     */
    public boolean transferDataTrial(Long tid,boolean flag);
    
    /**
     * 针对各个业务（代付/提现/退款）的业务退款方法，交易失败或审核拒绝时
     * @param transferData
     * @param businessEnum
     */
    public void businessRefund(List<PojoTranData> transferDataList) ;
    
    /**
     * 更新划拨明细状态，主要用于转账结果的更新
     * @param tid
     * @param status
     */
    public void updateTransferDataToFinish(Long tid,String status);

}
