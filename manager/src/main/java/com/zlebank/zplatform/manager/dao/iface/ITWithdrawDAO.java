/* 
 * ITxnsWinhdrawDAO.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.iface;
import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.trade.model.TxnsWithdrawModel;

/**
 * 提现DAO
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午10:11:25
 * @since 
 */
public interface ITWithdrawDAO extends BasePagedQueryDAO<TxnsWithdrawModel, TxnsWithdrawQuery> {
    
    /**
     * 根据提现订单号状态得到订单信息
     * @param withdraworderno
     * @return
     */
    public TxnsWithdrawModel getTxnsWithdrawByorderNo(String txnseqNo,String status,String withdraworderno);

    /**
     * @param txns
     * @return 
     */
    public TxnsWithdrawModel update(TxnsWithdrawModel txns);

    
}
