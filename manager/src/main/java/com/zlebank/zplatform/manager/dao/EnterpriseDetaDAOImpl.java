package com.zlebank.zplatform.manager.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IEnterpriseDetaDAO;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseDeta;

public class EnterpriseDetaDAOImpl extends HibernateDAOImpl<PojoEnterpriseDetaApply, Long> implements IEnterpriseDetaDAO{
    /**
     * 根据memberId得到企业
     * @param memberId
     * @return 企业信息
     */
    @Override
    public PojoEnterpriseDeta getEnterpriseByMemberId(String memberId) {
        Criteria crite = this.getCurrentSession().createCriteria(PojoEnterpriseDeta.class);
        crite.add(Restrictions.eq("memberId", memberId));
        PojoEnterpriseDeta member = (PojoEnterpriseDeta) crite.uniqueResult();
        return member;
    }

}
