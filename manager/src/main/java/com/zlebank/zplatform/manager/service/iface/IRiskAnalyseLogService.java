package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.bean.RiskAnalyseLogQueryBean;
import com.zlebank.zplatform.manager.dao.object.RiskAnalyseLogModel;

public interface IRiskAnalyseLogService extends IBaseService<RiskAnalyseLogModel,Long>{

    Map<String, Object> queryRiskRulesControl(int page,
            int rows,
            RiskAnalyseLogQueryBean riskAnalyseLogQueryBean, String roletype);

    public List<?> showRulecodeList(String roletype);

    Map<String, Object> queryAllRiskRulesControl(int page,
            int rows,
            RiskAnalyseLogQueryBean riskAnalyseLogQueryBean,
            String roletype);

    Map<String, Object> queryAllSuccess(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryWithdrawals(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryInsteadPay(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryAllWithdrawals(Map<String, Object> variables);

    Map<String, Object> queryAllInsteadPay(Map<String, Object> variables);

    Map<String, Object> queryAllCrr(Map<String, Object> variables);

}
