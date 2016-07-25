package com.zlebank.zplatform.manager.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.bean.RiskAnalyseLogQueryBean;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.RiskAnalyseLogModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRiskAnalyseLogService;


public class RiskAnalyseLogServiceImpl extends BaseServiceImpl<RiskAnalyseLogModel, Long> implements IRiskAnalyseLogService {

    
    @Autowired
    private DAOContainer daoContainer;
        
    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }


    @Override
    public IBaseDAO<RiskAnalyseLogModel, Long> getDao() {        
        return daoContainer.getRiskAnalyseLogDAO();
    }
   

    @Override
    public Map<String, Object> queryRiskRulesControl(int page,
            int rows,
            RiskAnalyseLogQueryBean riskAnalyseLogQueryBean,String roletype) {
        Map<String , Object> map = new HashMap<String,Object>();
      if(roletype == null ||roletype .equals("1")||roletype.equals("3")){
          String[] columns = new String[] {"v_rolecode", "v_value", "v_sdate","v_edate","v_roletype","v_user","i_no","i_perno" };
          Object[] paramaters = new Object[8];
          paramaters[0] = riskAnalyseLogQueryBean.getRolecode()==null? null: riskAnalyseLogQueryBean.getRolecode();
          paramaters[1] = riskAnalyseLogQueryBean.getMemberid()==null? null: riskAnalyseLogQueryBean.getMemberid();
          paramaters[2] = riskAnalyseLogQueryBean.getStartdate()==null? null:riskAnalyseLogQueryBean.getStartdate();
          paramaters[3] = riskAnalyseLogQueryBean.getEnddate()==null? null:riskAnalyseLogQueryBean.getEnddate();
          paramaters[4] =riskAnalyseLogQueryBean.getRoletype()==null?null:riskAnalyseLogQueryBean.getRoletype();
          paramaters[5] = riskAnalyseLogQueryBean.getUser()==null? null:riskAnalyseLogQueryBean.getUser();
          paramaters[6] = page;                      
          paramaters[7] = rows;              
          map =  getDao().executePageOracleProcedure("{CALL PCK_SEL_T_RISK_ANALYSE_LOG.sel_t_risk_analyse_log(?,?,?,?,?,?,?,?,?,?)}",columns,
                  paramaters, "cursor0","v_total");
      } else{
          String[] columns = new String[] {"v_rolecode", "v_value", "v_sdate","v_edate","v_roletype","v_user","i_no","i_perno" };
          Object[] paramaters = new Object[8];
          paramaters[0] = riskAnalyseLogQueryBean.getRolecode()==null? null: riskAnalyseLogQueryBean.getRolecode();
          paramaters[1] = riskAnalyseLogQueryBean.getMemberid()==null? null: riskAnalyseLogQueryBean.getMemberid();
          paramaters[2] = riskAnalyseLogQueryBean.getStartdate()==null? null:riskAnalyseLogQueryBean.getStartdate();
          paramaters[3] = riskAnalyseLogQueryBean.getEnddate()==null? null:riskAnalyseLogQueryBean.getEnddate();
          paramaters[4] =riskAnalyseLogQueryBean.getRoletype()==null?null:riskAnalyseLogQueryBean.getRoletype();
          paramaters[5] = riskAnalyseLogQueryBean.getUser()==null? null:riskAnalyseLogQueryBean.getUser();
          paramaters[6] = page;                      
          paramaters[7] = rows;              
          map =  getDao().executePageOracleProcedure("{CALL PCK_SEL_T_RISK_ANALYSE_LOG.sel_t_risk_analyse_log2(?,?,?,?,?,?,?,?,?,?)}",columns,
                  paramaters, "cursor0","v_total");
      } 
      return map;
    }

    @Override
    public List<?> showRulecodeList(String roletype) {
      
        return daoContainer.getRiskAnalyseLogDAO().showRulecodeList(roletype);
    }

    @Override
    public Map<String, Object> queryAllRiskRulesControl(int page,
            int rows,
            RiskAnalyseLogQueryBean riskAnalyseLogQueryBean,
            String roletype) {
        
        Map<String , Object> map = new HashMap<String,Object>();
      if(roletype == null ||roletype .equals("1")||roletype.equals("3")){
          String[] columns = new String[] {"v_rolecode", "v_value", "v_sdate","v_edate","v_roletype","v_user" };
          Object[] paramaters = new Object[6];
          paramaters[0] = riskAnalyseLogQueryBean.getRolecode()==null? null: riskAnalyseLogQueryBean.getRolecode();
          paramaters[1] = riskAnalyseLogQueryBean.getMemberid()==null? null: riskAnalyseLogQueryBean.getMemberid();
          paramaters[2] = riskAnalyseLogQueryBean.getStartdate()==null? null:riskAnalyseLogQueryBean.getStartdate();
          paramaters[3] = riskAnalyseLogQueryBean.getEnddate()==null? null:riskAnalyseLogQueryBean.getEnddate();
          paramaters[4] =riskAnalyseLogQueryBean.getRoletype()==null?null:riskAnalyseLogQueryBean.getRoletype();
          paramaters[5] = riskAnalyseLogQueryBean.getUser()==null? null:riskAnalyseLogQueryBean.getUser();            
          map =  getDao().executePageOracleProcedure("{CALL PCK_SEL_T_RISK_ANALYSE_LOG.sel_t_risk_analyse_log_all(?,?,?,?,?,?,?,?)}",columns,
                  paramaters, "cursor0","v_total");
      } else{
          String[] columns = new String[] {"v_rolecode", "v_value", "v_sdate","v_edate","v_roletype","v_user","i_no","i_perno" };
          Object[] paramaters = new Object[8];
          paramaters[0] = riskAnalyseLogQueryBean.getRolecode()==null? null: riskAnalyseLogQueryBean.getRolecode();
          paramaters[1] = riskAnalyseLogQueryBean.getMemberid()==null? null: riskAnalyseLogQueryBean.getMemberid();
          paramaters[2] = riskAnalyseLogQueryBean.getStartdate()==null? null:riskAnalyseLogQueryBean.getStartdate();
          paramaters[3] = riskAnalyseLogQueryBean.getEnddate()==null? null:riskAnalyseLogQueryBean.getEnddate();
          paramaters[4] =riskAnalyseLogQueryBean.getRoletype()==null?null:riskAnalyseLogQueryBean.getRoletype();
          paramaters[5] = riskAnalyseLogQueryBean.getUser()==null? null:riskAnalyseLogQueryBean.getUser();          
          map =  getDao().executePageOracleProcedure("{CALL PCK_SEL_T_RISK_ANALYSE_LOG.sel_t_risk_analyse_log2_all(?,?,?,?,?,?,?,?)}",columns,
                  paramaters, "cursor0","v_total");
      } 
      return map;
    }

    @Override
    public Map<String, Object> queryAllSuccess(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_busicode", "v_accsecmerno", "v_stime",
                "v_etime","v_user","i_no","i_perno"};
                Object[] paramaters = new Object[]{
                        variables.containsKey("busicode")
                                ? variables.get("busicode")
                                : null,
                        variables.containsKey("accsecmerno")
                                ? variables.get("accsecmerno")
                                : null,
                        variables.containsKey("accordcommitimes")
                                ? variables.get("accordcommitimes")
                                : null,
                        variables.containsKey("accordcommitimen")
                                ? variables.get("accordcommitimen")
                                : null,
                        variables.containsKey("user")
                                ? variables.get("user")
                                : null, page,rows};
                                
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_txns(?,?,?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryWithdrawals(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_accsecmerno", "v_stime", "v_etime",
                "v_user","i_no","i_perno"};
                Object[] paramaters = new Object[]{
                        variables.containsKey("accsecmerno")
                                ? variables.get("accsecmerno")
                                : null,
                        variables.containsKey("accordcommitimes")
                                ? variables.get("accordcommitimes")
                                : null,
                        variables.containsKey("accordcommitimen")
                                ? variables.get("accordcommitimen")
                                : null,
                        variables.containsKey("user")
                                ? variables.get("user")
                                : null,page,rows}; 
                                
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_withdraw(?,?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryInsteadPay(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_accsecmerno", "v_stime", "v_etime",
                "v_user","i_no","i_perno"};
                Object[] paramaters = new Object[]{
                        variables.containsKey("accsecmerno")
                                ? variables.get("accsecmerno")
                                : null,
                        variables.containsKey("accordcommitimes")
                                ? variables.get("accordcommitimes")
                                : null,
                        variables.containsKey("accordcommitimen")
                                ? variables.get("accordcommitimen")
                                : null,
                        variables.containsKey("user")
                                ? variables.get("user")
                                : null,page,rows};                                
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_instead_pay(?,?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryAllWithdrawals(Map<String, Object> variables) {
        String[] columns = new String[]{"v_accsecmerno", "v_stime","v_etime","v_user"};

        Object[] paramaters = new Object[]{                
            variables.containsKey("accsecmerno")
                    ? variables.get("accsecmerno")
                    : null,
            variables.containsKey("stime")
                    ? variables.get("stime")
                    : null,
            variables.containsKey("etime")
                    ? variables.get("etime")
                    : null,
            variables.containsKey("user")
                    ? variables.get("user")
                    : null
       };                               
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_withdraw_all(?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryAllInsteadPay(Map<String, Object> variables) {
        String[] columns = new String[]{"v_accsecmerno", "v_stime","v_etime","v_user"};

        Object[] paramaters = new Object[]{                
            variables.containsKey("accsecmerno")
                    ? variables.get("accsecmerno")
                    : null,
            variables.containsKey("stime")
                    ? variables.get("stime")
                    : null,
            variables.containsKey("etime")
                    ? variables.get("etime")
                    : null,
            variables.containsKey("user")
                    ? variables.get("user")
                    : null
       };                               
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_instead_pay_all(?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryAllCrr(Map<String, Object> variables) {
        String[] columns = new String[]{"v_busicode","v_accsecmerno", "v_stime","v_etime","v_user"};

        Object[] paramaters = new Object[]{  
            variables.containsKey("busicode")
                ? variables.get("busicode")
                : null,
            variables.containsKey("accsecmerno")
                    ? variables.get("accsecmerno")
                    : null,
            variables.containsKey("stime")
                    ? variables.get("stime")
                    : null,
            variables.containsKey("etime")
                    ? variables.get("etime")
                    : null,
            variables.containsKey("user")
                    ? variables.get("user")
                    : null
       };                               
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYBUSICODE.sel_txns_all(?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }



}
