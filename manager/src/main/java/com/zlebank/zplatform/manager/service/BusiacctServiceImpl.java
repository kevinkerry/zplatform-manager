package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.dao.iface.BusiacctDAO;
import com.zlebank.zplatform.manager.dao.object.BusiacctModel;
import com.zlebank.zplatform.manager.service.iface.IBusiacctService;
import com.zlebank.zplatform.trade.service.base.BaseServiceImpl;
@Service("busiacctService")
public class BusiacctServiceImpl extends BaseServiceImpl<BusiacctModel, String> implements IBusiacctService {

    @Autowired
    private BusiacctDAO busiacctDAO;
    
    public BusiacctDAO getBusiacctDAO() {
        return busiacctDAO;
    }

    public void setBusiacctDAO(BusiacctDAO busiacctDAO) {
        this.busiacctDAO = busiacctDAO;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public List<?> queryAllUsage() {
        String  queryString ="select distinct (usage) from t_acc_busiacct order by usage asc ";        
        return (List<?>) super.queryBySQL(queryString, new Object[]{});
    }

    @Override
    public Session getSession() {       
        return busiacctDAO.getSession();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryAccAccountDetail(String id) {
        Map<String, Object> map;
//        String queryString = "select t.business_actor_id, t.acct_code,t.acct_code_name,tt.usage,t.total_balance totalBlance,t.balance,t.frozen_balance frozenBalance,t.status from t_acc_acct t ,"
//                + " t_acc_busiacct tt where t.id =tt.acct_id and t.id=? ";
        String queryString = "select t.business_actor_id, t.acct_code,t.acct_code_name,tt.usage,"
                + "t.total_balance/100 totalBalance,t.balance/100 balance,t.frozen_balance/100 frozenBalance,t.status,ti.group_name "
                + " from t_acc_busiacct tt,t_acc_acct t left join t_industry_group ti on ti.member_id = t.business_actor_id "
                + "  where t.id =tt.acct_id and t.id=? ";
        List<Map<String, Object>> listMap =  (List<Map<String, Object>>) super.queryBySQL(queryString, new Object[]{id});
        return listMap.get(0);
      
    }

}
