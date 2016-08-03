package com.zlebank.zplatform.manager.dao;


import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.AbstractPagedQueryDAOImpl;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.CmbcResfileBean;
import com.zlebank.zplatform.manager.dao.iface.ICmbcResfileDAO;
import com.zlebank.zplatform.manager.dao.object.CmbcResfileModel;


@Repository
public class CmbcResfileDAOImpl extends AbstractPagedQueryDAOImpl<CmbcResfileModel, CmbcResfileBean>
        implements
            ICmbcResfileDAO {

    @Override
    protected Criteria buildCriteria(CmbcResfileBean e) {
        Criteria crite= this.getSession().createCriteria(CmbcResfileModel.class);
        if(e!=null){
         if(StringUtil.isNotEmpty(e.getPaydate())){
             String[] paydateString = e.getPaydate().split("-");
             String paydate = "" ;
             for(int i= 0;i<paydateString.length;i++){
                 paydate +=paydateString[i];
             }
                crite.add(Restrictions.eq("paydate", paydate));
            }
        }
        return crite;
    }
 



}
