package com.zlebank.zplatform.manager.service;

import java.util.ArrayList;
import java.util.List;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.ChnlDetaBean;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.service.iface.IChannelService;
import com.zlebank.zplatform.trade.model.ChnlDetaModel;


public class ChannelServiceImpl implements IChannelService{

    private DAOContainer  daoContainer;
        
    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @Override
    public List<ChnlDetaBean> getAllChannelCodeList() {
        List<ChnlDetaModel> li= daoContainer.getChannelDAO().getAllChannelCodeList();
        List<ChnlDetaBean> car=new ArrayList<ChnlDetaBean>();
        for(ChnlDetaModel chnl:li){
           car.add(BeanCopyUtil.copyBean(ChnlDetaBean.class, chnl));
        }  
        return car;
     }
    
}
