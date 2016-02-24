/* 
 * QuickServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年11月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.QuickpayCustBean;
import com.zlebank.zplatform.manager.dao.iface.QuickCustDAO;
import com.zlebank.zplatform.manager.dao.object.QuickpayCustModel;
import com.zlebank.zplatform.manager.service.iface.IQuickService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月13日 上午10:19:45
 * @since 
 */
  @Service
public class QuickServiceImpl implements IQuickService {
      @Autowired
      private QuickCustDAO qsd;
    /**
     *
     * @param memberId
     * @return
     */
    @Override
    @Transactional
    public List<QuickpayCustBean> getQuiceByMemberId(String memberId) {
        if(StringUtil.isEmpty(memberId))
            return null;
       List<QuickpayCustModel> li= qsd.getQuickByMemberID(memberId);
       List <QuickpayCustBean> quick=new ArrayList<QuickpayCustBean>();
       for(QuickpayCustModel qpcm:li){
         QuickpayCustBean quickBean=  BeanCopyUtil.copyBean(QuickpayCustBean.class, qpcm);
         quick.add(quickBean);
       }
       return quick;
    }
    /**
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean unlockQuice(String id,String type) {
     if (StringUtil.isEmpty(id)||StringUtil.isEmpty(type))
         return false;
     QuickpayCustModel qpcm=    qsd.getQuickById(Long.valueOf(id));
     if(qpcm==null||!type.equals(qpcm.getStatus()))   
     return false;
    qpcm.setStatus("02");
    return true;
    
    }
    
}
