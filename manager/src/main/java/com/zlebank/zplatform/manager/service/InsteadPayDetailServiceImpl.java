package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.service.iface.InsteadPayDetailService;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailBean;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailQuery;
import com.zlebank.zplatform.trade.dao.InsteadPayDetailDAO;
import com.zlebank.zplatform.trade.model.PojoInsteadPayDetail;

@Service
public class InsteadPayDetailServiceImpl extends AbstractBasePageService<InsteadPayDetailQuery, InsteadPayDetailBean> implements InsteadPayDetailService{
    
    @Autowired
    private InsteadPayDetailDAO insteadPayDetailDAO;
    
    @Override
    protected long getTotal(InsteadPayDetailQuery example) {
        return insteadPayDetailDAO.count(example);
    }

    @Override
    protected List<InsteadPayDetailBean> getItem(int offset,
            int pageSize,
            InsteadPayDetailQuery example) {
        List<PojoInsteadPayDetail> insteadDetails = insteadPayDetailDAO
                .getListByQuery(offset, pageSize, example);
        List<InsteadPayDetailBean> li = new ArrayList<InsteadPayDetailBean>();
        for (PojoInsteadPayDetail pojoinstead : insteadDetails) {
            InsteadPayDetailBean insteadBean = BeanCopyUtil.copyBean(
                    InsteadPayDetailBean.class, pojoinstead);
            insteadBean.setAmt(Money.valueOf(new BigDecimal(pojoinstead.getAmt())).toYuan());
            li.add(insteadBean);
        }
        return li;
    }

}
