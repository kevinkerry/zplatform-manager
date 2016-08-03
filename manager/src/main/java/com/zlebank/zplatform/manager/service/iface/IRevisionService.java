/* 
 * IRevisionService.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.service.IBasePageService;
import com.zlebank.zplatform.manager.bean.RevisionBean;
import com.zlebank.zplatform.manager.bean.RevisionQuery;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月30日 下午4:07:34
 * @since 
 */
public interface IRevisionService  extends IBasePageService<RevisionQuery, RevisionBean>{
    
    /**
     * 手工调账
     * @param rb
     */
  public void saveRevision(String txnsLogNo,Long userId) 
          throws AccBussinessException, AbstractBusiAcctException, ManagerWithdrawException ;
    

   }
