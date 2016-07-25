package com.zlebank.zplatform.manager.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.ITxnsLogsService;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

@Service("txnsLogsServiceImpl")
public class TxnsLogsServiceImpl extends BaseServiceImpl<TxnsLogModel, Long>
         implements ITxnsLogsService {
    @Autowired
    private DAOContainer daoContainer;
    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @Override
    public IBaseDAO<TxnsLogModel, Long> getDao() {
        return daoContainer.getTxnsLogsDAO();
    }

    @Override
    public Map<String, Object> findPersonTxnsLogs(Map<String, Object> variables,
            int page,
            int rows) {
        
        String[] columns = new String[]{"v_txnseqno", "v_busicode", "v_pan",
        "v_accordno","v_accsecmerno","v_stime","v_etime","v_payrettsnseqno","v_retcode","v_accmemberid",
        "v_user","i_no"," i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("txnseqno")
                        ? variables.get("txnseqno")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                variables.containsKey("pan")
                        ? variables.get("pan")
                        : null,
                variables.containsKey("accordno")
                        ? variables.get("accordno")
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
                variables.containsKey("payrettsnseqno")
                        ? variables.get("payrettsnseqno")
                        : null,
                variables.containsKey("retcode")
                        ? variables.get("retcode")
                        : null,
                variables.containsKey("accmemberid")
                        ? variables.get("accmemberid")
                        : null,
                variables.containsKey("user")
                        ? variables.get("user")
                        : null,
                        page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYPERSON.sel_txns_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> findPersonTxnsLogsOfAllPage(Map<String, Object> variables) {
        String[] columns = new String[]{"v_txnseqno", "v_busicode", "v_pan",
        "v_accordno","v_accsecmerno","v_stime","v_etime","v_payrettsnseqno","v_retcode","v_accmemberid",
        "v_user"};

        Object[] paramaters = new Object[]{
                variables.containsKey("txnseqno")
                        ? variables.get("txnseqno")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                variables.containsKey("pan")
                        ? variables.get("pan")
                        : null,
                variables.containsKey("accordno")
                        ? variables.get("accordno")
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
                variables.containsKey("payrettsnseqno")
                        ? variables.get("payrettsnseqno")
                        : null,
                variables.containsKey("retcode")
                        ? variables.get("retcode")
                        : null,
                variables.containsKey("accmemberid")
                        ? variables.get("accmemberid")
                        : null,
                variables.containsKey("user")
                        ? variables.get("user")
                        : null};
                        
        return getDao().executePageOracleProcedure(
                "{CALL PCK_SEL_TXNS_BYPERSON.sel_txns_log_all(?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");
    }

    /**
     * 查询所有交易成功的交易流水
     *
     * @param variables
     * @param page
     * @param rows
     * @return
     */
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



