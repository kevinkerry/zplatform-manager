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

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.trade.model.PojoTransferData;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月8日 下午3:57:33
 * @since 
 */
public interface ITransferService extends IBasePageService<TransferDataQuery,TransferData> {
    /**
     * 划拨初审
     * @param ftb
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    public void firstAudit(AuditBean ftb,List<PojoTransferData> pjfd) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException ;
    /**
     * 划拨复审
     * @param ftb
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    public void secondAudit(AuditBean ftb, List<PojoTransferData> pjfd) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException;
    /**
     * 按条件复审
     * @param ftb
     * @param tbq
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    public void  secondAuditByConditions(AuditBean ftb,TransferDataQuery tbq,String falg)throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException;;
    
    
}
