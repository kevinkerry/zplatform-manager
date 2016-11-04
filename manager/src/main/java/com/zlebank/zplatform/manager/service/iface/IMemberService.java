/* 
 * MemberService.java  
 * 
 * version TODO
 *
 * 2015年10月12日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.exception.MemberBussinessException;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月12日 下午5:53:15
 * @since 
 */
public interface IMemberService{
   /**
    * 通过条件得到交易明细
    * @param page
    * @param pageSize
    * @param mQuery
    * @return
    */
    public  PagedResult<AccEntry>  queryTradeDetail(int page,int pageSize, MemberQuery mQuery)throws MemberBussinessException ;
    
    /**
     * 通过条件得到账户信息
     * @return
     */
    public PagedResult<BusiAcctQuery> queryAccount(int page,int pageSize,  QueryAccount qa);


}
