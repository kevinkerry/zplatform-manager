/* 
 * MemberMessageServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年11月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.FeeModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IMemberMessageService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月11日 下午3:17:24
 * @since 
 */
@Service
public class MemberMessageServiceImpl extends BaseServiceImpl<FeeModel, Long> implements IMemberMessageService{
  
 
    @Autowired
    private DAOContainer daoContainer;
    
    
    @Override
    public IBaseDAO<FeeModel, Long> getDao() {
        return daoContainer.getFeeDAO();
    }

    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }
    /**
     *扣率信息存储过程
     * @param memberId
     * @return
     */
    @Override

    public List<?> getFeeByMid(String memberId) {
       if(StringUtil.isEmpty(memberId))
           return null;
        String[] columns = new String[]{"v_memberid"};
        Object[] paramaters = new Object[1];
        paramaters[0] = memberId;
    return   getDao().executeOracleProcedure("{CALL PCK_SEL_MEMBER_DETA.sel_member_fee(?,?)}", columns,
                paramaters, "cursor0");
    }

    /**
     *风控信息存储过程
     * @param memberId
     * @return
     */
    @Override
    public List<?> getRiskByMid(String memberId) {
        if(StringUtil.isEmpty(memberId))
            return null;
         String[] columns = new String[]{"v_memberid"};
         Object[] paramaters = new Object[1];
         paramaters[0] = memberId;
     return   getDao().executeOracleProcedure("{CALL PCK_SEL_MEMBER_DETA.sel_member_risk(?,?)}", columns,
                 paramaters, "cursor0");
    }

    /**
     *路由信息存储过程
     * @param memberId
     * @return
     */
    @Override
    public List<?> getRouteByMid(String memberId) {
        if(StringUtil.isEmpty(memberId))
            return null;
         String[] columns = new String[]{"v_memberid"};
         Object[] paramaters = new Object[1];
         paramaters[0] = memberId;
     return   getDao().executeOracleProcedure("{CALL PCK_SEL_MEMBER_DETA.sel_member_route(?,?)}", columns,
                 paramaters, "cursor0");
    }

}
