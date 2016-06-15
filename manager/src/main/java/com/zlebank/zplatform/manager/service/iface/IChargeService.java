/* 
 * IChargeService.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.ChargeBean;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.trade.exception.TradeException;

/**
 * 手工调账    
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 上午11:33:49
 * @since 
 */
public interface IChargeService extends IBasePageService<ChargeQuery,ChargeBean>{
    
   public void saveCharge(ChargeBean cb,Long userId)  throws ManagerWithdrawException, MemberBussinessException;   
   
   public void firstCharge(AuditBean ftb) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException,TradeException, IllegalEntryRequestException;
}
