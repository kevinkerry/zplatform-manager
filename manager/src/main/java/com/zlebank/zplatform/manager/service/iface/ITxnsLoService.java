/* 
 *
 * 
 * version TODO
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月17日 上午9:35:31
 * @since 
 */
public interface ITxnsLoService extends IBasePageService<TxnsLogBean, TxnsLog>{
    /**
     * 明细
     * @param txnseqno
     * @return
     */
   public List<?> getTxnsLogById(String txnseqno); 
   

}
