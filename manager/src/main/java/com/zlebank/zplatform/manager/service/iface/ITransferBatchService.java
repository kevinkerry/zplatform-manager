/* 
 * ITransferBatchService.java  
 * 
 * version TODO
 *
 * 2015年12月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.commons.bean.TransferBatchQuery;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.TransferBatch;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月10日 上午11:26:03
 * @since 
 */
public interface ITransferBatchService extends IBasePageService<TransferBatchQuery, TransferBatch>{

    /**
     * 按批次号划拨
     * @param batchNo
     * @return
     */
    public void TransferBatch(String batchNo) throws ManagerWithdrawException;
    
}
