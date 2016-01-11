/* 
 * MemberAccountServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年11月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.FreezeAcctService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.service.iface.IMemberAccountService;
import com.zlebank.zplatform.manager.service.iface.IMemberService;

/**
 *会员账户service
 *
 * @author yangpeng
 * @version
 * @date 2015年11月10日 下午1:41:50
 * @since 
 */
@Service
public class MemberAccountServiceImpl implements IMemberAccountService {
    @Autowired
    private FreezeAcctService fas;
    
    @Autowired
    private IMemberService ims;
    
    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean unFreezeAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
         List<Account> li=   this.getAccountbyMid(memberId);
         if(li.isEmpty())
             return false;
        for(Account acc:li){
            fas.unFreezeAcct(acc);
        }
         return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean freezeAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.freezeAcct(acc);
       }
        return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean stopInAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.stopInAcct(acc);
       }
        return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean reopenInAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.reopenInAcct(acc);
       }
        return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean stopOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.stopOutAcct(acc);
       }
        return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean reopenOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.reopenOutAcct(acc);
       }
        return true;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws IllegalAccessException 
     * @throws AccBussinessException 
     */
    @Override
    public boolean logOutAcctByMid(String memberId) throws IllegalAccessException, AccBussinessException {
        List<Account> li=   this.getAccountbyMid(memberId);
        if(li.isEmpty())
            return false;
       for(Account acc:li){
           fas.logOutAcct(acc);
       }
        return true;
    }
    
    
    private List<Account> getAccountbyMid(String memberId) throws IllegalAccessException{
       List< Account> li=new ArrayList<Account>();
        QueryAccount qa=  new QueryAccount();
        qa.setMemberId(memberId);
        int i=1;
        PagedResult<BusiAcctQuery> pr=ims.queryAccount(i, Integer.MAX_VALUE, qa);
        if(pr!=null&&pr.getPagedResult()!=null&&!pr.getPagedResult().isEmpty()){
            for(BusiAcctQuery baq:pr.getPagedResult()){
               Account  acc=new Account();
             acc.setId(Integer.valueOf(baq.getAcctId()));;
                li.add(acc);
            }
            
        }
            return li;
        
    };
}
