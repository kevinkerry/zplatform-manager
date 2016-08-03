/* 
 * QuickCustDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年11月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.QuickCustDAO;
import com.zlebank.zplatform.manager.dao.object.QuickpayCustModel;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月13日 上午10:05:34
 * @since 
 */
@Repository
public class QuickCustDAOImpl extends HibernateBaseDAOImpl<QuickpayCustModel>
        implements
            QuickCustDAO {

    
    /**
     *
     * @param memberId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<QuickpayCustModel> getQuickByMemberID(String memberId) {
     Criteria crite= this.getSession().createCriteria(QuickpayCustModel.class);
     crite.add(Restrictions.eq("relatememberno", memberId));
     crite.add(Restrictions.not(Restrictions.eq("status", "02")));
       return   crite.list();
    
    }

    /**
     *
     * @param id
     */
    @Override
    public QuickpayCustModel getQuickById(Long id) {
        Criteria crite= this.getSession().createCriteria(QuickpayCustModel.class);
        crite.add(Restrictions.eq("id", id));
       return  (QuickpayCustModel)crite.uniqueResult();
    }

}
