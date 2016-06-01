package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.BusiRateModel;
import com.zlebank.zplatform.manager.dao.object.CardRateModel;
import com.zlebank.zplatform.manager.dao.object.FeeCaseModel;
import com.zlebank.zplatform.manager.dao.object.FeeModel;
import com.zlebank.zplatform.manager.dao.object.MemberRateModel;
import com.zlebank.zplatform.manager.dao.object.StepRateModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class FeeAction extends BaseAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 523499466692436803L;
    private FeeModel feeModel;
    private String feever;
    private String caseid;
    private FeeCaseModel feecaseModel;
    private ServiceContainer serviceContainer;
    public String show() {
        return "success";
    }
    public String showAddFee() {
        return "showAddFee";
    }
    public String toMakeFee() {
        return "showAddFee";
    }
    public String showBusiRate() {
        return "showbusiRate";
    }

    // 扣率版本分页查询
    public String queryFee() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (feeModel == null) {
            feeModel = new FeeModel();
        }
        variables.put("feever", feeModel.getFeever());
        variables.put("feename", feeModel.getFeename());
        Map<String, Object> groupList = serviceContainer.getFeeService()
                .findFeeByPage(variables, getPage(), getRows());
        json_encode(groupList);
        return null;
    }
    public String saveFee() {
        String mark = "";
        if (feeModel == null||StringUtil.isEmpty(feeModel.getFeename().trim())||StringUtil.isEmpty(feeModel.getFeever().trim())) {
            mark = "风控版本代码或者风控名称不能为空";
            json_encode(mark);
            return null;
        }
        
        feeModel.setInuser(getCurrentUser().getUserId());
        mark = serviceContainer.getFeeService().AddOneFee(feeModel);
        json_encode(mark);
        return null;
    }
    // 扣率版本分页查询
    public String queryFeeCase() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (feeModel == null) {
            feeModel = new FeeModel();
        }
        variables.put("feever", feeModel.getFeever());
        variables.put("feename", feeModel.getFeename());
        Map<String, Object> groupList = serviceContainer.getFeeService()
                .findFeeByPage(variables, getPage(), getRows());
        json_encode(groupList);
        return null;
    }
    public String queryProductAll() {
        List<?> resultList = serviceContainer.getProductService().findAll();
        try {
            json_encode(resultList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 扣率版本下的业务实例
    public String queryFeeCaseByFeever() {
        List<?> resultList = serviceContainer.getFeeService()
                .queryFeeCaseByFeever(feever);
        try {
            json_encode(resultList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 查询一条扣率信息
    public String queryOneFee() {
        Map<String, Object> feeList = serviceContainer.getFeeService()
                .queryOneFee(feever);
        json_encode(feeList);
        return null;
    }
    // 手续费返还方式
    public String querFeeFlagType() {
        List<?> resultList = serviceContainer.getFeeService().querFeeFlagType();
        try {
            json_encode(resultList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 交易计费方式
    public String queryFeeSelfType() {
        List<?> resultList = serviceContainer.getFeeService()
                .queryFeeSelfType();
        try {
            json_encode(resultList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 修改扣率实例
    public String updateFeeCase() {
        if (feecaseModel == null) {
            feecaseModel = new FeeCaseModel();
        }
        feecaseModel.setUpuser(getCurrentUser().getUserId());
        String mark = serviceContainer.getFeeService().UpdateFeeCase(
                feecaseModel);
        json_encode(mark);
        return null;
    }
    // 修改扣率版本
    public String updateFee() {
        String mark = "";
        if (feeModel == null||StringUtil.isEmpty(feeModel.getFeename().trim())||StringUtil.isEmpty(feeModel.getFeever().trim())) {
            mark = "风控版本代码或者风控名称不能为空";
            json_encode(mark);
            return null;
        }
        feeModel.setInuser(getCurrentUser().getUserId());
        mark = serviceContainer.getFeeService().UpdateFee(feeModel);
        json_encode(mark);
        return null;
    }
    // 查询一条扣率版本实例信息
    public String queryOneFeeCase() {
        Map<String, Object> feecase = serviceContainer.getFeeService()
                .queryFeeOneCase(caseid);
        json_encode(feecase);
        return null;
    }

    // ----------------------------------------------
    // 交易类型扣率-------------------------------------------------------------------------------------

    // 扣率版本分页查询
    private BusiRateModel busiRateModel;
    public String queryTxnRate() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (busiRateModel == null) {
            busiRateModel = new BusiRateModel();
        }
        variables.put("feever", busiRateModel.getFeever());
        variables.put("busicode", busiRateModel.getBusicode());
        Map<String, Object> busiList = serviceContainer.getFeeService()
                .findBusiRateByPage(variables, getPage(), getRows());
        json_encode(busiList);
        return null;
    }
    public String saveTxnRate() {
        if (busiRateModel == null) {
            busiRateModel = new BusiRateModel();
        }
        
        if(!StringUtils.isEmpty(busiRateModel.getMinFeeStr())){
            busiRateModel.setMinFee(new BigDecimal(busiRateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(busiRateModel.getMaxFeeStr())){
            busiRateModel.setMaxFee(new BigDecimal(busiRateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(busiRateModel.getFeeRateStr())){
            busiRateModel.setFeeRate(new BigDecimal(busiRateModel.getFeeRateStr()));
        }
        
        busiRateModel.setInuser(getCurrentUser().getUserId().toString());
        String mark = serviceContainer.getFeeService().AddOneBusiRate(
                busiRateModel);
        json_encode(mark);
        return null;
    }
    // 查询所有扣率版本
    public String queryFeeAll() {
        List<?> resultList = serviceContainer.getFeeService().queryFeeAll();
        try {
            json_encode(resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // 查询一条扣率版本实例信息
    public String queryOneBusiRate() {
        Map<String, Object> feecase = serviceContainer.getFeeService()
                .queryOneBusiRate(caseid);
        json_encode(feecase);
        return null;
    }
    // 修改扣率实例
    public String updateBusiRate() {
        if (busiRateModel == null) {
            busiRateModel = new BusiRateModel();
        }
        if(!StringUtils.isEmpty(busiRateModel.getMinFeeStr())){
            busiRateModel.setMinFee(new BigDecimal(busiRateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(busiRateModel.getMaxFeeStr())){
            busiRateModel.setMaxFee(new BigDecimal(busiRateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(busiRateModel.getFeeRateStr())){
            busiRateModel.setFeeRate(new BigDecimal(busiRateModel.getFeeRateStr()));
        }
        String mark = serviceContainer.getFeeService().UpdateBusiRate(
                busiRateModel);
        json_encode(mark);
        return null;
    }
    // ----------------------------------------------
    // 商户扣率-------------------------------------------------------------------------------------
    public String showMemberRate() {
        return "showMemberRate";
    }
    // 商户扣率版本分页查询
    private MemberRateModel memberrateModel;
    public String queryMemberRate() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (memberrateModel == null) {
            memberrateModel = new MemberRateModel();
        } 

        variables.put("feever", memberrateModel.getFeever());
        variables.put("busicode", memberrateModel.getBusicode());
        variables.put("memberid", memberrateModel.getMemberid());
        Map<String, Object> busiList = serviceContainer.getFeeService()
                .findMemberRateByPage(variables, getPage(), getRows());
        json_encode(busiList);
        return null;
    }
    // 保存商户扣率
    public String saveMemberRate() {
        if (memberrateModel == null) {
            memberrateModel = new MemberRateModel();
        } 
        if(!StringUtils.isEmpty(memberrateModel.getMinFeeStr())){
            memberrateModel.setMinFee(new BigDecimal(memberrateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(memberrateModel.getMaxFeeStr())){
            memberrateModel.setMaxFee(new BigDecimal(memberrateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(memberrateModel.getFeeRateStr())){
            memberrateModel.setFeeRate(new BigDecimal(memberrateModel.getFeeRateStr()));
        }
        
        memberrateModel.setInuser(getCurrentUser().getUserId());
        String mark = serviceContainer.getFeeService().AddOneMemberRate(
                memberrateModel);
        json_encode(mark);
        return null;
    }
    // 修改商户扣率实例
    public String updateMemberRate() {
        if (memberrateModel == null) {
            memberrateModel = new MemberRateModel();
        } 
        if(!StringUtils.isEmpty(memberrateModel.getMinFeeStr())){
            memberrateModel.setMinFee(new BigDecimal(memberrateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(memberrateModel.getMaxFeeStr())){
            memberrateModel.setMaxFee(new BigDecimal(memberrateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(memberrateModel.getFeeRateStr())){
            memberrateModel.setFeeRate(new BigDecimal(memberrateModel.getFeeRateStr()));
        }
        String mark = serviceContainer.getFeeService().UpdateMemberRate(
                memberrateModel);
        json_encode(mark);
        return null;
    }
    // 查询一条商户扣率版本实例信息
    public String queryOneMemberRate() {
        Map<String, Object> feecase = serviceContainer.getFeeService()
                .queryOneMemberRate(caseid);
        json_encode(feecase);
        return null;
    }
    // ----------------------------------------------
    // 卡类别扣率-------------------------------------------------------------------------------------
    public String showCardRate() {
        return "showCardRate";
    }
    // 卡扣率分页查询
    private CardRateModel cardrateModel;
    public String queryCardRate() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (cardrateModel == null) {
            cardrateModel = new CardRateModel();
        }
        variables.put("feever", cardrateModel.getFeever());
        variables.put("busicode", cardrateModel.getBusicode());
        variables.put("cardtype", cardrateModel.getCardtype());
        Map<String, Object> busiList = serviceContainer.getFeeService()
                .findCardRateByPage(variables, getPage(), getRows());
        json_encode(busiList);
        return null;
    }
    public String saveCardRate() {
        if (cardrateModel == null) {
            cardrateModel = new CardRateModel();
        }
        if(!StringUtils.isEmpty(cardrateModel.getMinFeeStr())){
            cardrateModel.setMinFee(new BigDecimal(cardrateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(cardrateModel.getMaxFeeStr())){
            cardrateModel.setMaxFee(new BigDecimal(cardrateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(cardrateModel.getFeeRateStr())){
            cardrateModel.setFeeRate(new BigDecimal(cardrateModel.getFeeRateStr()));
        }
        cardrateModel.setInuser(getCurrentUser().getUserId());
        String mark = serviceContainer.getFeeService().AddOneCardRate(
                cardrateModel);
        json_encode(mark);
        return null;
    }
    // 修改卡扣率实例
    public String updateCardRate() {
        if (cardrateModel == null) {
            cardrateModel = new CardRateModel();
        }
        if(!StringUtils.isEmpty(cardrateModel.getMinFeeStr())){
            cardrateModel.setMinFee(new BigDecimal(cardrateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(cardrateModel.getMaxFeeStr())){
            cardrateModel.setMaxFee(new BigDecimal(cardrateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(cardrateModel.getFeeRateStr())){
            cardrateModel.setFeeRate(new BigDecimal(cardrateModel.getFeeRateStr()));
        }
        
        String mark = serviceContainer.getFeeService().UpdateCardRate(
                cardrateModel);
        json_encode(mark);
        return null;
    }
    // 查询一条卡扣率版本实例信息
    public String queryOneCardRate() {
        Map<String, Object> feecase = serviceContainer.getFeeService()
                .queryOneCardRate(caseid);
        json_encode(feecase);
        return null;
    }
    // ----------------------------------------------
    // 分段类别扣率-------------------------------------------------------------------------------------
    public String showStepRate() {
        return "showStepRate";
    }
    // 分段扣率分页查询
    private StepRateModel steprateModel;
    public String queryStepRate() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (steprateModel == null) {
            steprateModel = new StepRateModel();
        }
        variables.put("feever", steprateModel.getFeever());
        Map<String, Object> busiList = serviceContainer.getFeeService()
                .findStpeRateByPage(variables, getPage(), getRows());
        json_encode(busiList);
        return null;
    }
    public String saveStepRate() {
        if (steprateModel == null) {
            steprateModel = new StepRateModel();
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFeeStr())){
            steprateModel.setMinFee(new BigDecimal(steprateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFeeStr())){
            steprateModel.setMaxFee(new BigDecimal(steprateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getLimit1Str())){
            steprateModel.setLimit1(new BigDecimal(steprateModel.getLimit1Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFee2Str())){
            steprateModel.setMinFee2(new BigDecimal(steprateModel.getMinFee2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFee2Str())){
            steprateModel.setMaxFee2(new BigDecimal(steprateModel.getMaxFee2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getLimit2Str())){
            steprateModel.setLimit2(new BigDecimal(steprateModel.getLimit2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFee3Str())){
            steprateModel.setMinFee3(new BigDecimal(steprateModel.getMinFee3Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFee3Str())){
            steprateModel.setMaxFee3(new BigDecimal(steprateModel.getMaxFee3Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getFeeRateStr())){
            steprateModel.setFeeRate(new BigDecimal(steprateModel.getFeeRateStr()));
        }
        if(!StringUtils.isEmpty(steprateModel.getFeeRate2Str())){
            steprateModel.setFeeRate2(new BigDecimal(steprateModel.getFeeRate2Str()));
        }
        if(!StringUtils.isEmpty(steprateModel.getFeeRate3Str())){
            steprateModel.setFeeRate3(new BigDecimal(steprateModel.getFeeRate3Str()));
        }
        
        steprateModel.setInuser(getCurrentUser().getUserId());
        String mark = serviceContainer.getFeeService().AddOneStpeRate(
                steprateModel);
        json_encode(mark);
        return null;
    }
    // 修改分段扣率实例
    public String updateStepRate() {
        if (steprateModel == null) {
            steprateModel = new StepRateModel();
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFeeStr())){
            steprateModel.setMinFee(new BigDecimal(steprateModel.getMinFeeStr()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFeeStr())){
            steprateModel.setMaxFee(new BigDecimal(steprateModel.getMaxFeeStr()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getLimit1Str())){
            steprateModel.setLimit1(new BigDecimal(steprateModel.getLimit1Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFee2Str())){
            steprateModel.setMinFee2(new BigDecimal(steprateModel.getMinFee2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFee2Str())){
            steprateModel.setMaxFee2(new BigDecimal(steprateModel.getMaxFee2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getLimit2Str())){
            steprateModel.setLimit2(new BigDecimal(steprateModel.getLimit2Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMinFee3Str())){
            steprateModel.setMinFee3(new BigDecimal(steprateModel.getMinFee3Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getMaxFee3Str())){
            steprateModel.setMaxFee3(new BigDecimal(steprateModel.getMaxFee3Str()));
        }
        
        if(!StringUtils.isEmpty(steprateModel.getFeeRateStr())){
            steprateModel.setFeeRate(new BigDecimal(steprateModel.getFeeRateStr()));
        }
        if(!StringUtils.isEmpty(steprateModel.getFeeRate2Str())){
            steprateModel.setFeeRate2(new BigDecimal(steprateModel.getFeeRate2Str()));
        }
        if(!StringUtils.isEmpty(steprateModel.getFeeRate3Str())){
            steprateModel.setFeeRate3(new BigDecimal(steprateModel.getFeeRate3Str()));
        }
        String mark = serviceContainer.getFeeService().UpdateStpeRate(
                steprateModel);
        json_encode(mark);
        return null;
    }
    // 查询一条分段扣率版本实例信息
    public String queryOneStepRate() {
        Map<String, Object> feecase = serviceContainer.getFeeService()
                .queryOneStpeRate(caseid);
        json_encode(feecase);
        return null;
    }
    public FeeModel getFeeModel() {
        return feeModel;
    }
    public void setFeeModel(FeeModel feeModel) {
        this.feeModel = feeModel;
    }
    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }
    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }
    public String getFeever() {
        return feever;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    public FeeCaseModel getFeecaseModel() {
        return feecaseModel;
    }
    public void setFeecaseModel(FeeCaseModel feecaseModel) {
        this.feecaseModel = feecaseModel;
    }
    public String getCaseid() {
        return caseid;
    }
    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    public BusiRateModel getBusiRateModel() {
        return busiRateModel;
    }
    public void setBusiRateModel(BusiRateModel busiRateModel) {
        this.busiRateModel = busiRateModel;
    }
    public MemberRateModel getMemberrateModel() {
        return memberrateModel;
    }
    public void setMemberrateModel(MemberRateModel memberrateModel) {
        this.memberrateModel = memberrateModel;
    }
    public CardRateModel getCardrateModel() {
        return cardrateModel;
    }
    public void setCardrateModel(CardRateModel cardrateModel) {
        this.cardrateModel = cardrateModel;
    }
    public StepRateModel getSteprateModel() {
        return steprateModel;
    }
    public void setSteprateModel(StepRateModel steprateModel) {
        this.steprateModel = steprateModel;
    }

}
