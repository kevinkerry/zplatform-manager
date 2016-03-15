/* 
 * ITxnsWinhdrawService.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;




import java.util.Map;

import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawBean;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.manager.dao.object.TxnsWithdrawModel;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;

/**
 * 提现service
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午9:35:29
 * @since 
 */
public interface ITWithService extends IBasePageService<TxnsWithdrawQuery ,TxnsWithdrawBean >{
        
        /**
         * 提现申请
         * @param twb
         * @return
         */
    public Map<String, Object> saveTxnsWinhdraw(QueryAccount qa,TxnsWithdrawBean twb,Long userId);
    
        /**
         * 提现预申请
         * @param qa
         * @return
         */
    public Map<String, Object> getTxnsWinth(QueryAccount qa);

    /**
     *提现复审
     * @param secondTrial
     * @return
     */
    public void secondTrialWinth(AuditBean secondTrial) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException;

    /**
     * 
     * 提现初审
     * @param firstTrial
     * @return
     */
    public void firstTrialWinth(AuditBean firstTrial) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException;
    /**
     * 划拨审核
     * @param falg
     * @param txns
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    public void trialBatch(Boolean falg,TxnsWithdrawModel txns) throws AccBussinessException, AbstractBusiAcctException, NumberFormatException;   

    public Long getTxnFee(TxnsLog txns)throws ManagerWithdrawException;


}
