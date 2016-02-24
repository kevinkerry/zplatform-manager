/* 
 * RevisionDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.RevisionQuery;
import com.zlebank.zplatform.manager.dao.iface.IRevisionDAO;
import com.zlebank.zplatform.manager.dao.object.PojoRevision;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月30日 下午2:48:54
 * @since
 */
@Repository
public class RevisionDAOImpl
        extends
            AbstractPagedQueryDAOImpl<PojoRevision, RevisionQuery>
        implements
            IRevisionDAO {

    /**
     *
     * @param e
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    protected Criteria buildCriteria(RevisionQuery e) {
        Criteria crite = this.getSession().createCriteria(PojoRevision.class);
        if (e != null) {
            if (StringUtil.isNotEmpty(e.getBusicode())) {
                crite.add(Restrictions.eq("busicode", e.getBusicode()));
            }
            if (StringUtil.isNotEmpty(e.getRevisionno())) {
                crite.add(Restrictions.eq("revisionno", e.getRevisionno()));
            }
            if (StringUtil.isNotEmpty(e.getTxnslogid())) {
                crite.createAlias("txnslog", "txnslog",
                        CriteriaSpecification.INNER_JOIN);
                crite.add(Restrictions.eq("txnslog.txnseqno", e.getTxnslogid()));
            }
        }

        crite.addOrder(Order.asc("intime"));
        return crite;

    }

}
