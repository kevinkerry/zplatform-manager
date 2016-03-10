/* 
 * CheckFileServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.CheckFileBean;
import com.zlebank.zplatform.manager.bean.CheckFileQuery;
import com.zlebank.zplatform.manager.dao.iface.CheckFileDAO;
import com.zlebank.zplatform.manager.dao.object.PojoCheckFile;
import com.zlebank.zplatform.manager.service.iface.CheckFileService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月25日 下午5:11:11
 * @since
 */
@Service
public class CheckFileServiceImpl
        extends
            AbstractBasePageService<CheckFileQuery, CheckFileBean>
        implements
            CheckFileService {
    @Autowired
    private CheckFileDAO check;
    
    
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(CheckFileQuery example) {
       return  check.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<CheckFileBean> getItem(int offset,
            int pageSize,
            CheckFileQuery example) {
        List<CheckFileBean> checkBean=new ArrayList<CheckFileBean>();
        
      List<PojoCheckFile> checkLi=check.getListByQuery(offset, pageSize, example);
      for(PojoCheckFile li:checkLi){
          CheckFileBean checkfileb=    BeanCopyUtil.copyBean(CheckFileBean.class, li);
          checkfileb.setMerchName(li.getMerchno().getAccname());
          checkfileb.setMerchno(li.getMerchno().getMemberId());
          checkfileb.setAmount(li.getAmount().toYuan());
          checkfileb.setBusName(li.getBusicode().getBusiName());
          checkfileb.setBusicode(li.getBusicode().getBusiCode());
          checkBean.add(checkfileb);
      }
      return checkBean;
    }

}
