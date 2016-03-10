/* 
 * TransferBatchServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.bean.TransferBatchQuery;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.manager.bean.TransferBatch;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.ITransferBatchService;
import com.zlebank.zplatform.trade.cmbc.service.ICMBCTransferService;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月10日 上午11:29:47
 * @since
 */
@Service
public class TransferBatchServiceImpl
        extends
            AbstractBasePageService<TransferBatchQuery, TransferBatch>implements ITransferBatchService {
    
    @Autowired
    private TransferBatchDAO batch;
    @Autowired
    private ICMBCTransferService icmbc;
    
    
    
    
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(TransferBatchQuery example) {
        return 0;//batch.count(example);
    }





    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<TransferBatch> getItem(int offset,
            int pageSize,
            TransferBatchQuery example) {
      /*List<PojoTransferBatch> transfer=batch.getListByQuery(offset, pageSize, example);
      List<TransferBatch> li=new ArrayList<TransferBatch>();
      for(PojoTransferBatch bat:transfer){
       li.add(BeanCopyUtil.copyBean(TransferBatch.class, bat));
      }
    return li;*/
    	return null;
    }

    /**
     *
     * @param batchNo
     * @return
     * @throws ManagerWithdrawException 
     */
    @Override
    public void TransferBatch(String batchNo) throws ManagerWithdrawException {
        String[] batch = batchNo.split("\\|");
        if (batch.length <= 0) {
            throw new ManagerWithdrawException("G100004");
        }
       try {
       for (int i = 0; i < batch.length; i++) {
           //icmbc.batchTransfer(batch[i]);
       }   
       } catch (Exception e) {
           e.printStackTrace();
           throw new ManagerWithdrawException("G100007");
       }     
        
        
    }

   

}
