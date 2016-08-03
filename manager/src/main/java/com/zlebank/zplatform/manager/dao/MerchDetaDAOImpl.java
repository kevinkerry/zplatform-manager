package com.zlebank.zplatform.manager.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IMerchDetaDAO;
import com.zlebank.zplatform.manager.dao.object.PojoMerchDetaApply;

@SuppressWarnings("unchecked")
public class MerchDetaDAOImpl
        extends
            HibernateDAOImpl<PojoMerchDetaApply, Long> implements IMerchDetaDAO {

    public boolean isRepeat(String email, String cellphone, String coopInstiId) {
        String queryHQL;
        Query query;

        queryHQL = "select count(merch) from com.zlebank.zplatform.manager.dao.object.PojoMerchDeta merch where merch.member.instiCode=? and (merch.member.email=? or merch.member.phone=?)";
        query = getCurrentSession().createQuery(queryHQL);
        query.setParameter(0, coopInstiId);
        query.setParameter(1, email);
        query.setParameter(2, cellphone);
        Long count = (Long) query.uniqueResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String queryBankName(String bankNode, String bankCode) {
        String querySQL;
        SQLQuery query;

        if (bankNode == null || bankNode.equals("")) {
            return null;
        }
        querySQL = "select bank_name from t_bank_info where bank_node=?";

        if (bankCode != null && !bankCode.equals("")) {
            querySQL += " and bank_code=?";
            query = getCurrentSession().createSQLQuery(querySQL);
            query.setParameter(0, bankNode);
            query.setParameter(1, bankCode);
        } else {
            query = getCurrentSession().createSQLQuery(querySQL);
            query.setParameter(0, bankNode);
        }
        String result = (String) query.uniqueResult();

        return result;
    }

    public boolean hasSame(String memberId,
            String email,
            String cellPhoneNo,
            long coopInstiId) {
        String queryHQL;
        Query query;

        queryHQL = "select count(merch) from com.zlebank.zplatform.manager.dao.object.PojoMerchDeta merch where memberId!=? and (merch.member.email=? or merch.member.phone=?) and merch.member.instiCode=?";
        query = getCurrentSession().createQuery(queryHQL);
        query.setParameter(0, memberId);
        query.setParameter(1, email);
        query.setParameter(2, cellPhoneNo);
        query.setParameter(3, coopInstiId);
        Long count = (Long) query.uniqueResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
