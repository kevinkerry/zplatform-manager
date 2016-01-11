/* 
 * QuickCustDAO.java  
 * 
 * version TODO
 *
 * 2015年11月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.QuickpayCustModel;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月13日 上午9:45:06
 * @since 
 */
public interface QuickCustDAO extends BaseDAO<QuickpayCustModel> {
    
    
    public List<QuickpayCustModel> getQuickByMemberID(String memberId);
    
    
    public QuickpayCustModel getQuickById(Long id);
}
