package com.zlebank.zplatform.manager.dao;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IMerchDetaDAO;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

@SuppressWarnings("unchecked")
public class MerchDetaDAOImpl extends HibernateDAOImpl<MerchDetaModel, Long>
        implements
            IMerchDetaDAO {

    public boolean isRepeat(String email, String cellphone, String merchInsti) {
        String queryHQL;
        Query query;
        if (merchInsti == null || merchInsti.equals("")) {
            queryHQL = "select count(merch) from MerchDetaModel merch where merchinsti is null and (email=? or cellphoneno=?)";
            query = getCurrentSession().createQuery(queryHQL);
            query.setParameter(0, email);
            query.setParameter(1, cellphone);
        } else {
            queryHQL = "select count(merch) from MerchDetaModel merch where merchinsti=? and (email=? or cellphoneno=?)";
            query = getCurrentSession().createQuery(queryHQL);
            query.setParameter(0, merchInsti);
            query.setParameter(1, email);
            query.setParameter(2, cellphone);
        }
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
        } else{
            query = getCurrentSession().createSQLQuery(querySQL);
            query.setParameter(0, bankNode);
        }
        String result = (String) query.uniqueResult();
        
        return result;
    }
    
    public boolean hasSame(String memberid,String email,String cellphone){
        String queryHQL;
        Query query;
        
            queryHQL = "select count(merch) from MerchDetaModel merch where memberid!=? and (email=? or cellphoneno=?)";
            query = getCurrentSession().createQuery(queryHQL);
            query.setParameter(0, memberid);
            query.setParameter(1, email);
            query.setParameter(2, cellphone);
        
        Long count = (Long) query.uniqueResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}
