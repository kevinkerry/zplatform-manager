/* 
 * CheckFileDAOImpl.java  
 * 
 * version TODO
 *
 * 2015年12月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.CheckFileQuery;
import com.zlebank.zplatform.manager.dao.iface.CheckFileDAO;
import com.zlebank.zplatform.manager.dao.object.PojoCheckFile;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月25日 下午5:08:26
 * @since 
 */
@Repository
public class CheckFileDAOImpl extends
AbstractPagedQueryDAOImpl<PojoCheckFile, CheckFileQuery> implements CheckFileDAO{

    /**
     *
     * @param e
     * @return
     */
    @Override
    protected Criteria buildCriteria(CheckFileQuery e) {
      Criteria crite= this.getSession().createCriteria(PojoCheckFile.class);
    if(e!=null){
     if(StringUtil.isNotEmpty(e.getMerchno())){
            crite.createAlias("merchno", "member",
                    CriteriaSpecification.INNER_JOIN);
            crite.add(Restrictions.eq("member.dateMemberid", e.getMerchno()));
        }
        
        if(StringUtil.isNotEmpty(e.getPayordno())){
            crite.add(Restrictions.eq("payordno", e.getPayordno())) ;

        }
        
        if(StringUtil.isNotEmpty(e.getPaytrcno())){
            crite.add(Restrictions.eq("paytrcno", e.getPaytrcno()));
            
        }
        
     if(StringUtil.isNotEmpty(e.getTxnseqno())){
            crite.createAlias("txnseqno", "txnseqno",
                    CriteriaSpecification.INNER_JOIN);
            crite.add(Restrictions.eq("txnseqno.txnseqno", e.getMerchno()));
            
        }
    }
      return crite;
      
       
       
    }

}
