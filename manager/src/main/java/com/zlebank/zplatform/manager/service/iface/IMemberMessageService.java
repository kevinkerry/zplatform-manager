/* 
 * IMemberMessageService.java  
 * 
 * version TODO
 *
 * 2015年11月11日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.FeeModel;


/**
 * 会员信息service
 *
 * @author yangpeng
 * @version
 * @date 2015年11月11日 下午2:29:37
 * @since 
 */
public interface IMemberMessageService extends  IBaseService<FeeModel, Long>{
    /**
     * 扣率信息存储过程
     * @param memberId
     * @return
     */
    public List<?> getFeeByMid(String memberId);
    
    /**
     * 风控信息存储过程
     * @param memberId
     * @return
     */
    public List<?> getRiskByMid(String memberId);
    
    /**
     * 路由存储过程
     * @param memberId
     * @return
     */
    public List<?> getRouteByMid(String memberId);
}
