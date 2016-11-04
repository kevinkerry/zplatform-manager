/* 
 * MemberServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年10月12日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.BusiAcct;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.service.iface.IMemberService;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.dao.MemberDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.service.MemberService;

/**
 * 账户查询
 *
 * @author yangpeng
 * @version
 * @date 2015年10月12日 下午7:08:03
 * @since 
 */
@Service(value="memberServicess")
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberService ms;
    
    private MemberDAO mermberDAO;
    /**
     *
     * @param mq
     * @return
     * @throws MemberBussinessException 
     */
    @Transactional
    public PagedResult<AccEntry> queryTradeDetail(int page,int pageSize, MemberQuery mQuery) {
      return   ms.getAccEntryByQueryN( page, pageSize,  mQuery);
    
    
    }
    /**
     *
     * @param page
     * @param pageSize
     * @param qa
     * @return
     */
    public PagedResult<BusiAcctQuery> queryAccount(int page,
            int pageSize,
            QueryAccount qa) {
        return ms.getBusiAccount(qa, page, pageSize);
        
    }
    



}
