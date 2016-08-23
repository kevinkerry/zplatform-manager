package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.AccumulateRateModel;
import com.zlebank.zplatform.manager.dao.object.BusiRateModel;
import com.zlebank.zplatform.manager.dao.object.CardRateModel;
import com.zlebank.zplatform.manager.dao.object.FeeCaseModel;
import com.zlebank.zplatform.manager.dao.object.FeeModel;
import com.zlebank.zplatform.manager.dao.object.MemberRateModel;
import com.zlebank.zplatform.manager.dao.object.StepRateModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IFeeService;

public class FeeServiceImpl extends BaseServiceImpl<FeeModel, Long>
        implements
            IFeeService {

    private DAOContainer daoContainer;

    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @Override
    public IBaseDAO<FeeModel, Long> getDao() {
        return daoContainer.getFeeDAO();
    }
    public Map<String, Object> findFeeByPage(Map<String, Object> variables,
            int page,
            int rows) {

        String[] columns = new String[]{"v_feever", "v_feename", "i_no",
                "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null,
                variables.containsKey("feename")
                        ? variables.get("feename")
                        : null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_FEE.sel_t_fee(?,?,?,?,?,?)}", columns, paramaters,
                "cursor0", "v_total");

    }
    public String AddOneFee(FeeModel fee) {
        if (fee == null) {
            return "操作失败！";
        }

        Object[] paramaters = new Object[]{fee.getFeever(), fee.getFeename(),
                fee.getPrdtver(), fee.getInuser(), fee.getNotes(),
                fee.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_feename", " v_prdtver",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_FEE.ins_t_fee(?,?,?,?,?,?,?)}", columns,
                        paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String UpdateFee(FeeModel fee) {
        if (fee == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{fee.getFeeid(), fee.getFeever(),
                fee.getFeename(), fee.getUpuser(), fee.getNotes(),
                fee.getRemarks()};
        String[] columns = new String[]{"v_feeid", "v_feever", "v_feename",
                " v_upuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_FEE.upt_t_fee(?,?,?,?,?,?,?)}", columns,
                        paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    public Map<String, Object> queryOneFee(String feever) {

        String[] columns = new String[]{"v_feever"};

        Object[] paramaters = new Object[]{feever};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_FEE.sel_fee_date(?,?)}", columns, paramaters,
                "cursor0").get(0);
    }

    public List<?> queryChnlType() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_chnlname(?,?)}", columns,
                paramaters, "cursor0");
    }
    public List<?> querFeeFlagType() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_feeretflag(?,?)}", columns,
                paramaters, "cursor0");
    }
    public List<?> queryFeeSelfType() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_FOR_SELECT.sel_dic_selffeetype(?,?)}", columns,
                paramaters, "cursor0");
    }
    public List<?> queryFeeCaseByFeever(String feever) {
        String[] columns = new String[]{"v_feever"};
        Object[] paramaters = new Object[1];
        paramaters[0] = feever;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_T_FEE_CASE.sel_fee_case_by_feever(?,?)}", columns,
                paramaters, "cursor0");
    }
    public Map<String, Object> queryFeeOneCase(String caseid) {
        String[] columns = new String[]{"v_caseid"};
        Object[] paramaters = new Object[1];
        paramaters[0] = caseid;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_T_FEE_CASE.sel_fee_case_by_caseid(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }
    public String UpdateFeeCase(FeeCaseModel feecase) {
        if (feecase == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{feecase.getCaseid(),
                feecase.getBusicode(), feecase.getBusiname(),
                feecase.getFeever(), feecase.getSetlflg(),
                feecase.getFeetype(), feecase.getFeeretflag(),
                feecase.getMerchamtflag(), feecase.getMerchfeeflag(),
                feecase.getUpuser(), feecase.getNotes(), feecase.getRemarks()};
        String[] columns = new String[]{"v_caseid", "v_busicode", "v_businame",
                "v_feever", "v_setlflg", "v_feetype", "v_feeretflag",
                "v_merchamtflag", "v_merchfeeflag", "v_upuser", "v_notes",
                "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_FEE_CASE.upt_t_fee_case(?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    // ---------------------------------------------交易类型扣率---------------------------------------------------------------------
    public Map<String, Object> findBusiRateByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_feever", "v_feename", "v_busicode",
                "v_businame", "v_rate_type", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null,
                variables.containsKey("feename")
                        ? variables.get("feename")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                variables.containsKey("businame")
                        ? variables.get("businame")
                        : null,
                variables.containsKey("rate_type")
                        ? variables.get("rate_type")
                        : null,

                page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_BUSI_RATE.sel_t_busi_rate(?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }
    public String AddOneBusiRate(BusiRateModel busirate) {
        if (busirate == null) {
            return "操作失败！";
        }

        Object[] paramaters = new Object[]{
                busirate.getFeever(),
                busirate.getBusicode(),
                busirate.getFeeRate(),
                busirate.getMinFee() == null ? 0.00f : busirate.getMinFee()
                        .doubleValue(),
                busirate.getMaxFee() == null ? 0.00f : busirate.getMaxFee()
                        .doubleValue(), busirate.getRateType(),
                busirate.getInuser(), busirate.getNotes(),
                busirate.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BUSI_RATE.ins_t_busi_rate(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public List<?> queryFeeAll() {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[1];
        paramaters[0] = 1;
        return getDao().executeOracleProcedure(
                "{CALL  PCK_T_FEE.sel_fee_date_all(?,?)}", columns, paramaters,
                "cursor0");
    }
    public Map<String, Object> queryOneBusiRate(String tid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{tid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_BUSI_RATE.sel_t_busi_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }
    public String UpdateBusiRate(BusiRateModel busirate) {
        if (busirate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                busirate.getFeever(),
                busirate.getBusicode(),
                busirate.getFeeRate(),
                busirate.getMinFee() == null ? 0.00f : busirate.getMinFee()
                        .doubleValue(),
                busirate.getMaxFee() == null ? 0.00f : busirate.getMaxFee()
                        .doubleValue(), busirate.getInuser(),
                busirate.getRateType(), busirate.getNotes(),
                busirate.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_inuser",
                "v_rate_type", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BUSI_RATE.upt_t_busi_rate(?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    // ---------------------------------------------商户扣率---------------------------------------------------------------------
    public Map<String, Object> findMemberRateByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_feever", "v_busicode", "v_memberid",
                "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                variables.containsKey("memberid")
                        ? variables.get("memberid")
                        : null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_MERCH_RATE.sel_t_merch_rate(?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }
    public String AddOneMemberRate(MemberRateModel memberrate) {
        if (memberrate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                memberrate.getFeever(),
                memberrate.getBusicode(),
                memberrate.getMemberid(),
                memberrate.getFeeRate(),
                memberrate.getMinFee() == null ? 0.00f : memberrate.getMinFee()
                        .doubleValue(),
                memberrate.getMaxFee() == null ? 0.00f : memberrate.getMaxFee()
                        .doubleValue(), memberrate.getRateType(),
                memberrate.getInuser(), memberrate.getNotes(),
                memberrate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode", "v_memberid",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_MERCH_RATE.ins_t_merch_rate(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String UpdateMemberRate(MemberRateModel memberrate) {
        if (memberrate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                memberrate.getFeever(),
                memberrate.getBusicode(),
                memberrate.getMemberid(),
                memberrate.getFeeRate(),
                memberrate.getMinFee() == null ? 0.00f : memberrate.getMinFee()
                        .doubleValue(),
                memberrate.getMaxFee() == null ? 0.00f : memberrate.getMaxFee()
                        .doubleValue(), memberrate.getRateType(),
                memberrate.getInuser(), memberrate.getNotes(),
                memberrate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode", "v_memberid",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_MERCH_RATE.upt_t_merch_rate(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneMemberRate(String tid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{tid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_MERCH_RATE.sel_t_merch_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }

    // ---------------------------------------------卡类别扣率---------------------------------------------------------------------
    public Map<String, Object> findCardRateByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_feever", "v_busicode", "v_feename",
                "v_cardtype", "v_rate_type", "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null,
                variables.containsKey("busicode")
                        ? variables.get("busicode")
                        : null,
                null,
                variables.containsKey("cardtype")
                        ? variables.get("cardtype")
                        : null, null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_CARD_RATE.sel_t_card_rate(?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }
    public String AddOneCardRate(CardRateModel cardrate) {
        if (cardrate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                cardrate.getFeever(),
                cardrate.getBusicode(),
                cardrate.getCardtype(),
                cardrate.getFeeRate(),
                cardrate.getMinFee() == null ? 0.00f : cardrate.getMinFee()
                        .doubleValue(),
                cardrate.getMaxFee() == null ? 0.00f : cardrate.getMaxFee()
                        .doubleValue(), cardrate.getRateType(),
                cardrate.getInuser(), cardrate.getNotes(),
                cardrate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode", "v_cardtype",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_CARD_RATE.ins_t_card_rate(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String UpdateCardRate(CardRateModel cardrate) {
        if (cardrate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                cardrate.getFeever(),
                cardrate.getBusicode(),
                cardrate.getCardtype(),
                cardrate.getFeeRate(),
                cardrate.getMinFee() == null ? 0.00f : cardrate.getMinFee()
                        .doubleValue(),
                cardrate.getMaxFee() == null ? 0.00f : cardrate.getMaxFee()
                        .doubleValue(), cardrate.getRateType(),
                cardrate.getInuser(), cardrate.getNotes(),
                cardrate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode", "v_cardtype",
                " v_fee_rate", "v_min_fee", "v_max_fee", "v_rate_type",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_CARD_RATE.upt_t_card_rate(?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneCardRate(String tid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{tid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_CARD_RATE.sel_t_card_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }
    // ---------------------------------------------分段计费---------------------------------------------------------------------
    public Map<String, Object> findStpeRateByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_feever", "v_feename", "v_rate_type",
                "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null, null, null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_STEP_RATE.sel_t_step_rate(?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }

    public String AddOneStpeRate(StepRateModel steprate) {
        if (steprate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                steprate.getFeever(),
                steprate.getBusicode(),
                steprate.getRateType(),
                steprate.getServicefee(),
                steprate.getFeeRate(),
                steprate.getMinFee() == null ? 0.00f : steprate.getMinFee()
                        .doubleValue(),
                steprate.getMaxFee() == null ? 0.00f : steprate.getMaxFee()
                        .doubleValue(),
                steprate.getLimit1() == null ? 0.00f : steprate.getLimit1()
                        .doubleValue(),
                steprate.getFeeRate2(),
                steprate.getMinFee2() == null ? 0.00f : steprate.getMinFee2()
                        .doubleValue(),
                steprate.getMaxFee2() == null ? 0.00f : steprate.getMaxFee2()
                        .doubleValue(),
                steprate.getLimit2() == null ? 0.00f : steprate.getLimit2()
                        .doubleValue(),
                steprate.getFeeRate3(),
                steprate.getMinFee3() == null ? 0.00f : steprate.getMinFee3()
                        .doubleValue(),
                steprate.getMaxFee3() == null ? 0.00f : steprate.getMaxFee3()
                        .doubleValue(), steprate.getInuser(),
                steprate.getNotes(), steprate.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                "v_rate_type", " v_servicefee", "v_fee_rate", "v_min_fee",
                "v_max_fee", "v_limit1", "v_fee_rate2", "v_min_fee2",
                "v_max_fee2", "v_limit2", "v_fee_rate3", "v_min_fee3",
                "v_max_fee3", "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_STEP_RATE.ins_t_step_rate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public String UpdateStpeRate(StepRateModel steprate) {

        if (steprate == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                steprate.getFeever(),
                steprate.getBusicode(),
                steprate.getRateType(),
                steprate.getServicefee(),
                steprate.getFeeRate(),
                steprate.getMinFee() == null ? 0.00f : steprate.getMinFee()
                        .doubleValue(),
                steprate.getMaxFee() == null ? 0.00f : steprate.getMaxFee()
                        .doubleValue(),
                steprate.getLimit1() == null ? 0.00f : steprate.getLimit1()
                        .doubleValue(),
                steprate.getFeeRate2(),
                steprate.getMinFee2() == null ? 0.00f : steprate.getMinFee2()
                        .doubleValue(),
                steprate.getMaxFee2() == null ? 0.00f : steprate.getMaxFee2()
                        .doubleValue(),
                steprate.getLimit2() == null ? 0.00f : steprate.getLimit2()
                        .doubleValue(),
                steprate.getFeeRate3(),
                steprate.getMinFee3() == null ? 0.00f : steprate.getMinFee3()
                        .doubleValue(),
                steprate.getMaxFee3() == null ? 0.00f : steprate.getMaxFee3()
                        .doubleValue(), steprate.getInuser(),
                steprate.getNotes(), steprate.getRemarks()};
        String[] columns = new String[]{"v_feever", "v_busicode",
                "v_rate_type", " v_servicefee", "v_fee_rate", "v_min_fee",
                "v_max_fee", "v_limit1", "v_fee_rate2", "v_min_fee2",
                "v_max_fee2", "v_limit2", "v_fee_rate3", "v_min_fee3",
                "v_max_fee3", "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_STEP_RATE.upt_t_step_rate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    public Map<String, Object> queryOneStpeRate(String tid) {
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{tid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_STEP_RATE.sel_t_step_date(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryFeever(Map<String, Object> variables) {       
        List<Map<String, Object>> list =  (List<Map<String, Object>>) getDao().executeBySQL("select GET_BROK (?) as feever from dual ", 
                new Object[]{variables.get("table_name")});
        Map<String, Object> resultMap = list.get(0);
        return    resultMap;      
    }

    @Override
    public String AddOneAccumulateRate(AccumulateRateModel accumulateRateModel) {
        if (accumulateRateModel == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                accumulateRateModel.getFeever(),
                accumulateRateModel.getBusicode(),
                accumulateRateModel.getRatetype(),
                accumulateRateModel.getServicefee(),
                accumulateRateModel.getFeerate(),
                accumulateRateModel.getMinfee()==null? 0.00f :accumulateRateModel.getMinfee().doubleValue(),
                accumulateRateModel.getMaxfee()==null? 0.00f :accumulateRateModel.getMaxfee().doubleValue(),
                        
                accumulateRateModel.getLimit1()==null? 0.00f :accumulateRateModel.getLimit1().doubleValue(),
                accumulateRateModel.getFeerate2(),
                accumulateRateModel.getMinfee2()==null? 0.00f :accumulateRateModel.getMinfee2().doubleValue(),
                accumulateRateModel.getMaxfee2()==null? 0.00f:accumulateRateModel.getMaxfee2().doubleValue(),                        
                accumulateRateModel.getLimit2()==null? 0.00f:accumulateRateModel.getLimit2().doubleValue(),
                accumulateRateModel.getFeerate3(),
                accumulateRateModel.getMinfee3()==null? 0.00f:accumulateRateModel.getMinfee3().doubleValue(),
                accumulateRateModel.getMaxfee3()==null? 0.00f:accumulateRateModel.getMaxfee3().doubleValue(), accumulateRateModel.getInuser(),
                accumulateRateModel.getNotes(), accumulateRateModel.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                "v_rate_type", " v_servicefee", "v_fee_rate", "v_min_fee",
                "v_max_fee", "v_limit1", "v_fee_rate2", "v_min_fee2",
                "v_max_fee2", "v_limit2", "v_fee_rate3", "v_min_fee3",
                "v_max_fee3", "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_ACCUMLATE_RATE.ins_t_accumulate_rate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    @Override
    public Map<String, Object> findAccumulateRateByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_feever", "v_feename", "v_rate_type",
                "i_no", "i_perno"};
        Object[] paramaters = new Object[]{
                variables.containsKey("feever")
                        ? variables.get("feever")
                        : null, null, null, page, rows};
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_ACCUMLATE_RATE.sel_t_accumulate_rate(?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0", "v_total");
    }

    @Override
    public Map<String, Object> queryOneAccumulateRate(String caseid) {
        
        String[] columns = new String[]{"v_in"};
        Object[] paramaters = new Object[]{caseid};
        return getDao().executeOracleProcedure(
                "{CALL PCK_T_ACCUMLATE_RATE.sel_t_accumulate_rate_detail(?,?)}", columns,
                paramaters, "cursor0").get(0);
    }

    @Override
    public String updateAccumulateRate(AccumulateRateModel accumulateRateModel) {
        if (accumulateRateModel == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{
                accumulateRateModel.getFeever(),
                accumulateRateModel.getBusicode(),
                accumulateRateModel.getRatetype(),
//                accumulateRateModel.getServicefee(),
                accumulateRateModel.getServicefee() == null ? null : accumulateRateModel.getServicefee()
                        .doubleValue(),
                accumulateRateModel.getFeerate(),               
                accumulateRateModel.getMinfee() == null ? null : accumulateRateModel.getMinfee()
                        .doubleValue(),
                accumulateRateModel.getMaxfee()== null ? null : accumulateRateModel.getMaxfee()
                        .doubleValue(),
                accumulateRateModel.getLimit1() == null ? null : accumulateRateModel.getLimit1()
                        .doubleValue(),
                accumulateRateModel.getFeerate2(),
                accumulateRateModel.getMinfee2() == null ? null : accumulateRateModel.getMinfee2() 
                        .doubleValue(),
                accumulateRateModel.getMaxfee2() == null ? null : accumulateRateModel.getMaxfee2()
                        .doubleValue(),
                accumulateRateModel.getLimit2() == null ? null : accumulateRateModel.getLimit2()
                        .doubleValue(),
                accumulateRateModel.getFeerate3(),
                accumulateRateModel.getMinfee3()== null ? null : accumulateRateModel.getMinfee3()
                        .doubleValue(),
                accumulateRateModel.getMaxfee3() == null ? null : accumulateRateModel.getMaxfee3()
                        .doubleValue(), accumulateRateModel.getInuser(),
                accumulateRateModel.getNotes(), accumulateRateModel.getRemarks()};

        String[] columns = new String[]{"v_feever", "v_busicode",
                "v_rate_type", " v_servicefee", "v_fee_rate", "v_min_fee",
                "v_max_fee", "v_limit1", "v_fee_rate2", "v_min_fee2",
                "v_max_fee2", "v_limit2", "v_fee_rate3", "v_min_fee3",
                "v_max_fee3", "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_ACCUMLATE_RATE.upt_t_accumulate_rate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }
    
    
}
