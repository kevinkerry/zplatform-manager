/* 
 * ITxnsLogDAO.java  
 * 
 * version TODO
 *
 * 2015年11月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.iface;

import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月16日 下午4:26:06
 * @since 
 */
public interface ITxnsDAO extends BasePagedQueryDAO<PojoTxnsLog,TxnsLogBean>{
    
   //public List<TxnLogModel> getTxnLogModel(TxnLogBean tlb); 
   
    /**
     * 通过交易序列号得到交易流水
     * @param txnseqno
     * @return
     */
    public PojoTxnsLog getTxnsModelByNo(String txnseqno);

}
