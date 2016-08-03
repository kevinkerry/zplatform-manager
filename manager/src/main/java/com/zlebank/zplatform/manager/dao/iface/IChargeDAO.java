/* 
 * IChargeDAO.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.iface;

import com.zlebank.zplatform.commons.dao.BasePagedQueryDAO;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 上午10:44:49
 * @since 
 */
public interface IChargeDAO extends BasePagedQueryDAO<ChargeModel,ChargeQuery>{

    
    public ChargeModel getChargeByChargeNo(String chargeNo,String status)throws ManagerWithdrawException;
    
    
}
