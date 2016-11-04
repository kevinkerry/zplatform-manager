/* 
 * AccountQueryDAOImpl.java  
 * 
 * version
 *
 * 2015年9月7日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.dao.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.dao.AccountQueryDAO;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.PojoAccount;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年9月7日 上午11:44:51
 * @since
 */
@Repository
public class AccountQueryDAOImpl extends HibernateBaseDAOImpl<PojoAccount>
        implements
            AccountQueryDAO {

    /**
     *
     * @param accountId
     * @return
     */
    @Override
    public Account getAccountByID(long accountId) {
        // this.getSession().createCriteria(persistentClass)
        return null;
    }

    /**
     *
     * @param memberId
     * @return
     * @throws AccBussinessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAccount(QueryAccount qa,
            Integer firset,
            Integer max) throws AccBussinessException {
        SQLQuery query = this.getSQLquery(qa);
        if (firset == null || max == null) {
            throw new AccBussinessException("E100009");
        }
        query.setFirstResult(firset).setMaxResults(max);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAllAccountByMId(String memberId,
            String busiAcctCode) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select acc.status, acc.acct_type ,acc.BUSINESS_ACTOR_ID  ");
        sb.append(" ,acc.balance,acc.frozen_balance,acc.total_balance  ");
        sb.append(",bus.acct_id,bus.busiacct_code,bus.busiacct_name,bus.usage  ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where   acc.status <>'99'   ");
        if (memberId != null) {
            sb.append(" and bus.business_actor_id=:memeberId ");
        }
        if (StringUtil.isNotEmpty(busiAcctCode)) {
            sb.append(" and bus.BUSIACCT_CODE=:busiAcctCode");
        }
        String sql = sb.toString();
        SQLQuery query = this.getSession().createSQLQuery(sql);

        if (StringUtil.isNotEmpty(busiAcctCode)) {
            query.setParameter("busiAcctCode", busiAcctCode);
        }
        if (StringUtil.isNotEmpty(memberId)) {
            query.setParameter("memeberId", memberId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        /*
         * query.setResultTransformer(new SQLColumnToBean( MemberQuery.class));
         */
        // @SuppressWarnings("unchecked")
        // List<Map<String, String> > li=query.list();

        return query.list();

    }

    private SQLQuery getSQLquery(QueryAccount qa) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select acc.status, acc.acct_type ,acc.ACCT_CODE ,acc.BUSINESS_ACTOR_ID ");
        sb.append(" ,acc.balance,acc.frozen_balance,acc.total_balance  ");
        sb.append(",bus.acct_id,bus.busiacct_code,bus.busiacct_name ,bus.USAGE  ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where  1 = 1 ");
//        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where   acc.status <>'99'   ");
        if (qa != null) {
            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                sb.append(" and bus.business_actor_id=:memeberId ");
            }
            // 账户名
            if (StringUtil.isNotEmpty(qa.getAcctCodeName())) {
                sb.append(" and acc.ACCT_CODE_NAME like :acctCodeName ");
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                sb.append(" and bus.USAGE=:usage ");
            }
            // 科目号
            if (StringUtil.isNotEmpty(qa.getAcctcode())) {
                sb.append(" and acc.ACCT_CODE=:acctCode ");
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                sb.append(" and bus.BUSIACCT_CODE=:busiacctCode ");
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                sb.append(" and acc.STATUS=:accStatus");
            }



            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);

            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                query.setParameter("memeberId", qa.getMemberId());
            }
            // 账户名
            if (StringUtil.isNotEmpty(qa.getAcctCodeName())) {
                query.setParameter("acctCodeName", "%"+qa.getAcctCodeName()+"%");
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                query.setParameter("usage", qa.getUsage());
            }
            // 科目号
            if (StringUtil.isNotEmpty(qa.getAcctcode())) {
                query.setParameter("acctCode", qa.getAcctcode());
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                query.setParameter("busiacctCode", qa.getBusiCode());
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                query.setParameter("accStatus", qa.getAccStatus());
            }



            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

            return query;
        } else {
            String sql = sb.toString();
            SQLQuery query = this.getSession().createSQLQuery(sql);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            return query;
        }

    }

    /**
     *
     * @param qa
     * @return
     */
    @Override
    public Long getcount(QueryAccount qa) {
        Long all = 0L;
         SQLQuery query = null;
        StringBuffer sb = new StringBuffer();
        sb.append(" select count(*) ");
        sb.append("  from t_acc_busiacct bus inner join  t_acc_acct acc  on bus.acct_id=acc.id  where 1=1 ");
        if (qa != null) {
            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                sb.append(" and bus.business_actor_id=:memeberId ");
            }
            // 账户名
            if (StringUtil.isNotEmpty(qa.getAcctCodeName())) {
                sb.append(" and acc.ACCT_CODE_NAME like :acctCodeName");
            }
           
            // 用途
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                sb.append(" and bus.USAGE=:usage");
            }
            //科目号
            if (StringUtil.isNotEmpty(qa.getAcctcode())) {
                sb.append(" and acc.ACCT_CODE=:acctCode");
            }          
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                sb.append(" and bus.BUSIACCT_CODE=:busiacctCode");
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                sb.append(" and acc.STATUS=:accStatus");
            }
             String sql = sb.toString();
            query = this.getSession().createSQLQuery(sql);
            /**会员号、账户名、用途、科目号、业务账户号、状态**/
            // 会员号
            if (StringUtil.isNotEmpty(qa.getMemberId())) {
                query.setParameter("memeberId", qa.getMemberId());
            }
            // 账户名
            if (StringUtil.isNotEmpty(qa.getAcctCodeName())) {
                query.setParameter("acctCodeName", "%"+qa.getAcctCodeName()+"%");
            }
            // 作用
            if (StringUtil.isNotEmpty(qa.getUsage())) {
                query.setParameter("usage", qa.getUsage());
            }
            // 科目号
            if (StringUtil.isNotEmpty(qa.getAcctcode())) {
                query.setParameter("acctCode", qa.getAcctcode());
            }
            // 业务账户号
            if (StringUtil.isNotEmpty(qa.getBusiCode())) {
                query.setParameter("busiacctCode", qa.getBusiCode());
            }
            // 账户状态
            if (StringUtil.isNotEmpty(qa.getAccStatus())) {
                query.setParameter("accStatus", qa.getAccStatus());
            }


            
            

        } else {

            String sql = sb.toString();
            query = this.getSession().createSQLQuery(sql);

        }
        BigDecimal size = (BigDecimal) query.uniqueResult();
        if (size != null) {
            all = size.longValue();

        }
        return all;

    }

}
