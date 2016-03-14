/* 
 * IInsteadPayService.java  
 * 
 * version TODO
 *
 * 2015年12月23日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailBean;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailQuery;
import com.zlebank.zplatform.trade.model.PojoInsteadPayDetail;

/**
 * 代付service
 *
 * @author yangpeng
 * @version
 * @date 2015年12月23日 上午9:53:39
 * @since
 */
public interface IInsteadPayService {

    /**
     * 单笔审核
     * 
     * @param trial
     */
    public void firstInstead(AuditBean trial) throws AccBussinessException,
            ManagerWithdrawException, AbstractBusiAcctException,
            NumberFormatException;

    public InsteadPayDetailBean getDetailByTxnseqno(String txnserno,String status)
            throws ManagerWithdrawException;
    /** 审核拒绝 **/
    public void veto(PojoInsteadPayDetail pojoinstead,String status)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException;
    public void batchFirst(AuditBean trial,InsteadPayDetailQuery instead) 
            throws AccBussinessException, AbstractBusiAcctException, 
            NumberFormatException, ManagerWithdrawException;
}
