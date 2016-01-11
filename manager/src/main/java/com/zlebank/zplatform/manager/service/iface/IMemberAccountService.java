/* 
 * IMemberAccountService.java  
 * 
 * version TODO
 *
 * 2015年11月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.acc.exception.AccBussinessException;

/**
 * 会员账户管理
 *
 * @author yangpeng
 * @version
 * @date 2015年11月10日 上午11:14:44
 * @since 
 */
public interface IMemberAccountService {
    
    /**
     * 根据会员Id解冻账户
     * @param memberId
     * @return
     */
    public boolean  unFreezeAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
    /**
     * 根据会员Id冻结账户
     * @param memberId
     * @return
     */
    public boolean freezeAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
    
    /**
     * 根据会员Id止入账户
     * @param memberId
     * @return
     */
    public boolean stopInAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
    
    /**
     * 根据会员Id解止入账户
     * @param memberId
     * @return
     */
    public boolean reopenInAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
    /**
     * 根据会员Id止出账户
     * @param memberId
     * @return
     */
    public boolean stopOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
        /**
         * 根据会员Id解止出账户
         * @param memberId
         * @return
         */
    public boolean reopenOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
        /**
         * 根据会员Id注销账户
         * @param memberId
         * @return
         */
    public boolean logOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException;
    
    

}
