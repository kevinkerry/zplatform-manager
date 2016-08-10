package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.CmbcResfileBean;
import com.zlebank.zplatform.manager.dao.iface.ICmbcResfileDAO;
import com.zlebank.zplatform.manager.dao.object.CmbcResfileModel;
import com.zlebank.zplatform.manager.service.iface.ICmbcResfileService;
@Service
public class CmbcResfileServiceImpl extends
   AbstractBasePageService<CmbcResfileBean, CmbcResfileBean> implements ICmbcResfileService {

    @Autowired
    private ICmbcResfileDAO check;
    
    @Override
    protected long getTotal(CmbcResfileBean example) {
        
        return check.count(example);
    }

    @Override
    protected List<CmbcResfileBean> getItem(int offset,
            int pageSize,
            CmbcResfileBean example) {
        List<CmbcResfileBean> cmbcBean=new ArrayList<CmbcResfileBean>();      
        List<CmbcResfileModel> checkLi=check.getListByQuery(offset, pageSize, example);
        for(CmbcResfileModel li:checkLi){
            CmbcResfileBean cmbcResfileBean=    BeanCopyUtil.copyBean(CmbcResfileBean.class, li);
            cmbcResfileBean.setTid(li.getTid());
            cmbcResfileBean.setAccname(li.getAccname());
            cmbcResfileBean.setAccno(li.getAccname());
            cmbcResfileBean.setBanktranbatchno(li.getBanktranbatchno());
            cmbcResfileBean.setBanktrandataseqno(li.getBanktrandataseqno());
            cmbcResfileBean.setBanktranresno(li.getBanktranresno());
            cmbcResfileBean.setPaydate(li.getPaydate());
            cmbcResfileBean.setPaydatetime(li.getPaydatetime());
            cmbcResfileBean.setRescode(li.getRescode());
            cmbcResfileBean.setResinfo(li.getResinfo());
            cmbcResfileBean.setRestype(li.getRestype());
            cmbcResfileBean.setTranamt(li.getTranamt());
       
            cmbcBean.add(cmbcResfileBean);
         }
        return cmbcBean;
    }

}
