/* 
 * IQuickService.java  
 * 
 * version TODO
 *
 * 2015年11月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.bean.QuickpayCustBean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月13日 上午10:17:47
 * @since 
 */
public interface IQuickService {
    
    public List<QuickpayCustBean> getQuiceByMemberId(String memberId);
    
    public boolean unlockQuice(String id,String type);
    
}
