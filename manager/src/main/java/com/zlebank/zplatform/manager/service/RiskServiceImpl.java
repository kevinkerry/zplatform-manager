package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.BlacklistMemberModel;
import com.zlebank.zplatform.manager.dao.object.BlacklistPanModel;
import com.zlebank.zplatform.manager.dao.object.LimitCreditSingleModel;
import com.zlebank.zplatform.manager.dao.object.RiskCaseModel;
import com.zlebank.zplatform.manager.dao.object.RiskModel;
import com.zlebank.zplatform.manager.dao.object.WhitePanModel;
import com.zlebank.zplatform.manager.enums.RiskLevelEnum;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

public class RiskServiceImpl extends BaseServiceImpl<RiskModel, Long>
        implements
            IRiskService {

    private DAOContainer daoContainer;

    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @Override
    public IBaseDAO<RiskModel, Long> getDao() {
        return daoContainer.getRiskDAO();
    }

    public Map<String, Object> findRiskByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_riskver", "v_riskname", "i_no",
                "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("riskver")
                        ? variables.get("riskver")
                        : null,
                variables.containsKey("riskname")
                        ? variables.get("riskname")
                        : null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_RISK.sel_t_risk(?,?,?,?,?,?)}", columns,
                paramaters, "cursor0", "v_total");

    }
    public String AddOneRisk(RiskModel risk) {
        if (risk == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{risk.getRiskver(),
                risk.getRiskname(), risk.getInuser(), risk.getNotes(),
                risk.getRemarks(), risk.getPrdtver()};
        String[] columns = new String[]{"v_riskver", "v_riskname", "v_inuser",
                "v_notes", "v_remarks", " v_prdtver"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_RISK.ins_t_risk(?,?,?,?,?,?,?)}", columns,
                        paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneRisk(String riskId) {
        String[] columns = new String[]{"v_riskid"};

        Object[] paramaters = new Object[]{riskId};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_RISK.sel_t_risk_date(?,?)}", columns, paramaters,
                "cursor0").get(0);
    }
    public String UpdateOneRisk(RiskModel risk) {
        if (risk == null) {
            return "操作失败！";
        }

        Object[] paramaters = new Object[]{risk.getRiskid(), risk.getRiskver(),
                risk.getRiskname(), risk.getInuser(), risk.getNotes(),
                risk.getRemarks(), risk.getPrdtver()};
        String[] columns = new String[]{"v_riskid", "v_riskver", "v_riskname",
                "v_upuser", "v_notes", "v_remarks", " v_prdtver"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_RISK.upt_t_risk(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public List<?> queryRiskCase(String riskver) {
        String[] columns = new String[]{"v_riskver", "v_busicode",
                "v_businame", "v_status"};
        Object[] paramaters = new Object[4];
        paramaters[0] = riskver;
        paramaters[1] = null;
        paramaters[2] = null;
        paramaters[3] = null;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_T_RISK.sel_t_risk_case(?,?,?,?,?)}", columns,
                paramaters, "cursor0");
    }
    // 风控策略
    public List<?> query_risk_list() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risk_list(?,?)}", columns,
                paramaters, "cursor0");
    }
    // 风控版本
    public List<?> query_risk_all() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risk_all(?,?)}", columns,
                paramaters, "cursor0");
    }
    // 风控实例
    public List<?> query_risk_case_all(String riskver) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = riskver;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risk_case_all(?,?)}", columns,
                paramaters, "cursor0");
    }
    // 风控策略（实例包含的风控做标记）
    public List<?> query_risk_list_active(String caseid) {
        String[] columns = new String[]{"v_caseid"};
        Object[] paramaters = new Object[1];
        paramaters[0] = caseid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_T_RISK.sel_t_risk_active(?,?)}", columns,
                paramaters, "cursor0");
    }
    // 一条风控实例
    public Map<String, Object> queryOneRiskCase(String v_caseid) {

        String[] columns = new String[]{"v_caseid"};
        Object[] paramaters = new Object[]{v_caseid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_RISK.sel_t_risk_case_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }
    public String UpdateOneRiskCase(RiskCaseModel riskcase) {
        if (riskcase == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{riskcase.getCaseid(),
                riskcase.getBusicode(), riskcase.getBusiname(),
                riskcase.getRiskver(), riskcase.getNotes(),
                riskcase.getRemarks(), riskcase.getActiveflag()};
        String[] columns = new String[]{"v_caseid", "v_busicode", "v_businame",
                "v_riskver", "v_notes", "v_remarks", " v_activeflag"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_RISK.upt_t_risk_case(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    // 银行卡黑名单
    public Map<String, Object> findBlackPanByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_pan", "v_merchaname", "i_no",
                "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("pan") ? variables.get("pan") : null,
                variables.containsKey("merchaname") ? variables
                        .get("merchaname") : null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_BLACKLIST_PAN.sel_t_blacklist_pan(?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");

    }
    // 风控策略
    public List<?> query_risk_level() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_risklevel(?,?)}", columns,
                paramaters, "cursor0");
    }

    public String AddOneBlackPan(BlacklistPanModel blackPan) {
        if (blackPan == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{blackPan.getPan(),
                blackPan.getRisklevel(), blackPan.getNotes(),
                blackPan.getRemarks()};
        String[] columns = new String[]{"v_pan", "v_risklevel", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_PAN.ins_t_blacklist_pan(?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneBlackPan(String tId) {
        String[] columns = new String[]{"v_tid"};

        Object[] paramaters = new Object[]{tId};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_BLACKLIST_PAN.sel_t_blacklist_pan_date(?,?)}",
                columns, paramaters, "cursor0").get(0);
    }
    public String updateOneBlackPan(BlacklistPanModel blackPan) {
        if (blackPan == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{blackPan.getTId(),
                blackPan.getPan(), blackPan.getRisklevel(),
                blackPan.getNotes(), blackPan.getRemarks()};
        String[] columns = new String[]{"v_t_id", "v_pan", "v_risklevel",
                "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_PAN.upt_t_blacklist_pan(?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String deleteOneBlackPan(String tid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_PAN.del_t_blacklist_pan(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String startOneBlackPan(String tid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_PAN.start_t_blacklist_pan(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    // 银行卡白名单
    public Map<String, Object> findWhitePanByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_pan", "v_merchaname", "i_no",
                "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("pan") ? variables.get("pan") : null,
                variables.containsKey("merchaname") ? variables
                        .get("merchaname") : null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_WHITELIST_PAN.sel_t_whitelist_pan(?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");

    }

    public String AddOneWhitePan(WhitePanModel whitePan) {
        if (whitePan == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{whitePan.getPan(),
                whitePan.getRisklevel(), whitePan.getNotes(),
                whitePan.getRemarks()};
        String[] columns = new String[]{"v_pan", "v_risklevel", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_WHITELIST_PAN.ins_t_whitelist_pan(?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneWhitePan(String tId) {
        String[] columns = new String[]{"v_tid"};

        Object[] paramaters = new Object[]{tId};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_WHITELIST_PAN.sel_t_whitelist_pan_date(?,?)}",
                columns, paramaters, "cursor0").get(0);
    }
    public String updateOneWhitePan(WhitePanModel whitePan) {
        if (whitePan == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{whitePan.getTId(),
                whitePan.getPan(), whitePan.getRisklevel(),
                whitePan.getNotes(), whitePan.getRemarks()};
        String[] columns = new String[]{"v_t_id", "v_pan", "v_risklevel",
                "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_WHITELIST_PAN.upt_t_whitelist_pan(?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String deleteOneWhitePan(String tid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_WHITELIST_PAN.del_t_whitelist_pan(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String startOneWhitePan(String tid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_WHITELIST_PAN.start_t_whitelist_pan(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    // -------------------------------------------------商户黑名单---------------------------------------------------------------------
    // 商户黑名单
    public Map<String, Object> findBlacklistMemberByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_memberid", "v_merchaname", "i_no",
                "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("memberId")
                        ? variables.get("memberId")
                        : null,
                variables.containsKey("merchName")
                        ? variables.get("merchName")
                        : null, page, rows};
        return getDao()
                .executePageOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.sel_t_blacklist_member(?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0", "v_total");
    }

    public String AddOneBlacklistMember(BlacklistMemberModel blm) {
        if (blm == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{blm.getMemberid(),
                blm.getRisklevel(), blm.getNotes(), blm.getRemarks()};
        String[] columns = new String[]{"v_memberid", "v_risklevel", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.ins_t_blacklist_member(?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneBlacklistMember(String tId) {
        String[] columns = new String[]{"v_in"};

        Object[] paramaters = new Object[]{tId};
        return getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.sel_t_blacklist_member_one(?,?)}",
                        columns, paramaters, "cursor0").get(0);
    }
    public String updateBlacklistMember(BlacklistMemberModel blm) {
        if (blm == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{blm.getTId(), blm.getMemberid(),
                blm.getRisklevel(), blm.getNotes(), blm.getRemarks()};
        String[] columns = new String[]{"v_t_id", "v_memberid", "v_risklevel",
                "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.upt_t_blacklist_member(?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String deleteOneBlacklistMember(String tid, Long userid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, userid};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.del_t_blacklist_member(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String startOneBlacklistMember(String tid, Long userid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid, null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_MEMBER.start_t_blacklist_member(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    // --------------------------------------------------------------卡类别单笔限额------------------------------------------------------
    public Map<String, Object> findlimitCreditSingleByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_riskver", "v_busicode",
                "v_max_amount", "v_min_amount", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("riskver")
                        ? variables.get("riskver")
                        : null,
                variables.containsKey("caseid")
                        ? variables.get("caseid")
                        : null, null, null, page, rows};
        return getDao()
                .executePageOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.sel_t_limit_credit_single(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0", "v_total");

    }
    public String AddOnelimitCreditSingle(LimitCreditSingleModel limitSingle) {
        if (limitSingle == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{limitSingle.getCaseid(),
                limitSingle.getMaxAmount(), limitSingle.getMinAmount(),
                limitSingle.getCardType(), limitSingle.getRisklevel(),
                limitSingle.getNotes(), limitSingle.getRemarks()};
        String[] columns = new String[]{"v_caseid", "v_max_amount",
                "v_min_amount", "v_card_type", "v_risklevel", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.ins_t_limit_credit_single(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String updateOnelimitCreditSingle(LimitCreditSingleModel limitSingle) {
        if (limitSingle == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{limitSingle.getTId(),
                limitSingle.getMaxAmount(), limitSingle.getMinAmount(),
                limitSingle.getCardType(), limitSingle.getRisklevel(),
                limitSingle.getNotes(), limitSingle.getRemarks()};
        String[] columns = new String[]{"v_t_id", "v_max_amount",
                "v_min_amount", "v_card_type", "v_risklevel", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.upt_t_limit_credit_single(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOnelimitCreditSingle(String tId) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{tId};
        return getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.sel_t_limit_credit_single_one(?,?)}",
                        columns, paramaters, "cursor0").get(0);
    }
    public String deleteOnelimitCreditSingle(String tId, Long userid) {
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object[] paramaters = new Object[]{tId, null};
        return (String) getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.del_t_limit_credit_single(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
    }
    public String startOnelimitCreditSingle(String tId, Long userid) {
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object[] paramaters = new Object[]{tId, null};
        return (String) getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_LIMIT_CREDIT_SINGLE.start_t_limit_credit_single(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
    }

    public void tradeRiskControl(String merchId,
            String subMerchId,
            String memberId,
            String busiCode,
            String txnAmt,
            String cardType,
            String cardNo) throws TradeException {
        int riskLevel = 0;
        int riskOrder = 0;
        RiskLevelEnum riskLevelEnum = null;
        String riskInfo = "";
        List<Map<String, Object>> riskList = (List<Map<String, Object>>) super
                .queryBySQL(
                        "SELECT FNC_GETRISK(?,?,?,?,?,?,?) AS RISK FROM DUAL",
                        new Object[]{merchId, subMerchId, memberId, busiCode,
                                txnAmt, cardType, cardNo});
        if (riskList.size() > 0) {
            riskInfo = riskList.get(0).get("RISK") + "";
            String[] riskInfos = riskInfo.split(",");
            riskOrder = Integer.valueOf(riskInfos[0]);
            riskLevel = Integer.valueOf(riskInfos[1]);
            riskLevelEnum = RiskLevelEnum.fromValue(riskLevel);
        } else {
            throw new TradeException("T034");
        }
        if (RiskLevelEnum.PASS.equals(riskLevelEnum)) {// 交易通过

        } else if (RiskLevelEnum.REFUSE.equals(riskLevelEnum)) {// 交易拒绝
            throw new TradeException("T035");
        } else {

        }

    }
    
      /**
      *
      * @param txnsLog
      * @return
      */
     @Override
     public Long getTxnFee(TxnsLogModel txnsLog){
         //交易序列号，扣率版本，业务类型，交易金额，会员号，原交易序列号，卡类型 
         @SuppressWarnings("unchecked")
        List<Map<String, Object>> feeList = (List<Map<String, Object>>) super.queryBySQL("select FNC_GETFEES(?,?,?,?,?,?,?) as fee from dual", 
                 new Object[]{txnsLog.getTxnseqno(),txnsLog.getFeever(),txnsLog.getBusicode(),txnsLog.getAmount(),txnsLog.getAccfirmerno(),txnsLog.getTxnseqnoOg(),txnsLog.getCardtype()});
         if(feeList.size()>0){
             if(StringUtil.isNull(feeList.get(0).get("FEE"))){
                 return 0L;
             }else{
                 return Long.valueOf(feeList.get(0).get("FEE")+"");
             }
             
         }
         return 0L;
        
     }
     
     /**
     *
     * @param txnseqno
     */
    @Override
    public List<?> getTxnsLogbyId(String txnseqno) {
        String[] columns = new String[] { "v_txnseqno" };
        Object[] paramaters = new Object[1];
        paramaters[0] = txnseqno;
        return getDao().executeOracleProcedure(
                "{CALL PCK_SEL_T_TXNS_LOG.sel_txns_log_deta(?,?)}", columns,
                paramaters, "cursor0");
    
    
    }
}
