package com.zlebank.zplatform.manager.dao;





import java.util.List;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IRiskAnalyseLogDAO;
import com.zlebank.zplatform.manager.dao.object.RiskAnalyseLogModel;

@SuppressWarnings("unchecked")
public class RiskAnalyseLogDAOImpl extends  HibernateDAOImpl<RiskAnalyseLogModel, Long> implements IRiskAnalyseLogDAO {

    @Override
    public List<?> showRulecodeList(String roletype) {
//        RiskAnalyseConfigModel riskAnalyseConfigModel = new RiskAnalyseConfigModel();
        String queryString ="select distinct t.rolecode,t.roledesc from T_RISK_ANALYSE_CONFIG t where t.ROLETYPE = ?";
        List<?> list = this.executeBySQL(queryString, new Object[]{roletype});
        return list;
        



    }

   

   

}
