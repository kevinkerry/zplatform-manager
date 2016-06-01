/* 
 * ITxnsWinhdrawDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.manager.dao.iface.ITWithdrawDAO;
import com.zlebank.zplatform.trade.model.TxnsWithdrawModel;

/**
 * 提现dao
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午10:18:45
 * @since 
 */
@Repository("txnswith")
public class ITxnsWithdrawDAOImpl  extends
AbstractPagedQueryDAOImpl<TxnsWithdrawModel, TxnsWithdrawQuery> implements ITWithdrawDAO {

    /**
     *
     * @param e
     * @return
     */
    @Override
    protected Criteria buildCriteria(TxnsWithdrawQuery e) {
     Criteria crite=   this.getSession().createCriteria(TxnsWithdrawModel.class);
    if(e!=null){
        if(e.getId()!=null){
        crite.add(Restrictions.eq("id", e.getId()));
        }
        if(StringUtil.isNotEmpty(e.getMemberid())){
            crite.add(Restrictions.eq("memberid", e.getMemberid()));  
        }
        if(StringUtil.isNotEmpty(e.getWithdrawtype())){
            crite.add(Restrictions.eq("withdrawtype", e.getWithdrawtype()));  
        }
        if(StringUtil.isNotEmpty(e.getGatewayorderno())){
            crite.add(Restrictions.eq("gatewayorderno", e.getGatewayorderno()));  
        }
        if(StringUtil.isNotEmpty(e.getStatus())){
            crite.add(Restrictions.eq("status", e.getStatus()));  
        }
        }
        crite.addOrder(Order.desc("intime"));   
        return crite;
    
    }

    /**
     *
     * @param withdraworderno
     * @return
     */
    @Override
    public TxnsWithdrawModel getTxnsWithdrawByorderNo(String txnseqNo,String status,String withdraworderno) {
        Criteria crite=   this.getSession().createCriteria(TxnsWithdrawModel.class);
        if(StringUtil.isNotEmpty(txnseqNo)){
            crite.add(Restrictions.eq("txnseqNo", txnseqNo));  
        }
        if(StringUtil.isNotEmpty(withdraworderno)){
            crite.add(Restrictions.eq("withdraworderno", withdraworderno)); 
        }
            try {
                crite.add(Restrictions.eq("status",status));
                return (TxnsWithdrawModel) crite.uniqueResult();
            } catch (Exception e) {
             return null;
            }
   
       
      
        
    }
    
    

    
   

}
