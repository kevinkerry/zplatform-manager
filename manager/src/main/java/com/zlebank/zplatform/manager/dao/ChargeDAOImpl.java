/* 
 * ChargeDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.dao.iface.IChargeDAO;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;

/**
 * 手工充值dao
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 上午10:52:07
 * @since
 */
@Repository
public class ChargeDAOImpl
        extends
            AbstractPagedQueryDAOImpl<ChargeModel, ChargeQuery>
        implements
            IChargeDAO {

    /**
     *
     * @param e
     * @return
     */
    @SuppressWarnings("deprecation")
    @Override
    protected Criteria buildCriteria(ChargeQuery e) {
        Session session = getSession();
        Criteria crite = session.createCriteria(ChargeModel.class);
        // crite.setFetchMode("memberid", FetchMode.EAGER);
        if (e != null) {
            if (StringUtil.isNotEmpty(e.getChargeType())) {
                crite.add(Restrictions.eq("chargetype", e.getChargeType()));
            }
            if (StringUtil.isNotEmpty(e.getMemberId())) {
                crite.createAlias("memberid", "member",
                        CriteriaSpecification.INNER_JOIN);
                crite.add(Restrictions.eq("member.memberid", e.getMemberId()));
            }
            if (StringUtil.isNotEmpty(e.getStatus())) {
                crite.add(Restrictions.eq("status", e.getStatus()));
            }
            if (StringUtil.isNotEmpty(e.getChargeno())) {
                crite.add(Restrictions.eq("chargeno", e.getChargeno()));
            }
        }
        crite.addOrder(Order.desc("intime"));
        return crite;

    }

    /**
     *
     * @param chargeNo
     * @return
     * @throws ManagerWithdrawException 
     */
    @Override
    public ChargeModel getChargeByChargeNo(String chargeNo,String status) throws ManagerWithdrawException {
     Criteria crite =this.getSession().createCriteria(ChargeModel.class);
     crite.add(Restrictions.eq("chargeno", chargeNo));
     crite.add(Restrictions.eq("status", status));
     try {
      return    (ChargeModel) crite.uniqueResult();
    } catch (Exception e) {
    throw new ManagerWithdrawException("G100012");
    }
     
    }

}
