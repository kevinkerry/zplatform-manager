package com.zlebank.zplatform.manager.action.risk;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.BlackIdnumModel;
import com.zlebank.zplatform.manager.dao.object.BlacklistMemberModel;
import com.zlebank.zplatform.manager.dao.object.BlacklistPanModel;
import com.zlebank.zplatform.manager.dao.object.LimitCreditSingleModel;
import com.zlebank.zplatform.manager.dao.object.RiskCaseModel;
import com.zlebank.zplatform.manager.dao.object.RiskModel;
import com.zlebank.zplatform.manager.dao.object.WhitePanModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;

public class RiskAction extends BaseAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 523499466692436803L;
	private RiskModel riskModel;
	private RiskCaseModel riskCaseModel;
	private String riskId;
	private String riskver;
	private String merchName;
	private List<String> checkboxList;
	private ServiceContainer serviceContainer;

	public String showRisk() {
		return "success";
	}

	// 对应页面功能模块：风控管理；银行卡黑名单，银行卡白名单,商户黑名单，分卡种单笔限额

	// 风控版本分页查询
	public String queryRisk() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (riskModel == null) {
			riskModel = new RiskModel();
		}
		variables.put("riskver", riskModel.getRiskver());
		variables.put("riskname", riskModel.getRiskname());
		Map<String, Object> groupList = serviceContainer.getRiskService()
				.findRiskByPage(variables, getPage(), getRows());
		json_encode(groupList);
		return null;
	}

	public String saveRisk() {
	    String result = "";
        if (riskModel == null||StringUtil.isEmpty(riskModel.getRiskver().trim())||StringUtil.isEmpty(riskModel.getRiskname().trim())) {
            result = "风控版本代码或者风控名称不能为空";
            json_encode(result);
            return null;
        }
		riskModel.setInuser(getCurrentUser().getUserId());
		result = serviceContainer.getRiskService().AddOneRisk(riskModel);
		json_encode(result);
		return null;
	}

	public String queryOneRisk() {
		Map<String, Object> feeList = serviceContainer.getRiskService()
				.queryOneRisk(riskId);
		json_encode(feeList);
		return null;
	}

	public String queryOneRiskCase() {
		Map<String, Object> feeList = serviceContainer.getRiskService()
				.queryOneRiskCase(riskId);
		json_encode(feeList);
		return null;
	}

	public String UpdateOneRisk() {
	    String result = "";
        if (riskModel == null||StringUtil.isEmpty(riskModel.getRiskver().trim())||StringUtil.isEmpty(riskModel.getRiskname().trim())) {
            result = "风控版本代码或者风控名称不能为空";
            json_encode(result);
            return null;
        }
		riskModel.setInuser(getCurrentUser().getUserId());
		String mark = serviceContainer.getRiskService()
				.UpdateOneRisk(riskModel);
		json_encode(mark);
		return null;
	}

	// 根据一条风控ID，查询下面的实例，给实例配置复选业务
	public String toMakeRiskCase() {
		return "toMakeRiskCase";
	}

	public String queryRiskCase() {
		List<?> riskCaseList = serviceContainer.getRiskService().queryRiskCase(
				riskver);
		try {
			json_encode(riskCaseList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 风控策略(所有的策略)
	public String queryRisklistCheck() {
		List<?> riskcheckList = serviceContainer.getRiskService()
				.query_risk_list();
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 风控策略(一条风控实例包含的风控策略做标记)
	public String queryRisklistCheckActive() {
		List<?> riskcheckList = serviceContainer.getRiskService()
				.query_risk_list_active(riskId);
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改风控实例，生成activeflag64位
	public String updateRiskCase() {
		if (riskCaseModel == null) {
			riskCaseModel = new RiskCaseModel();
		}
		StringBuffer activeflag = new StringBuffer(
				"000000000000000000000000000000000000000000000000000000000000");
		if (checkboxList != null) {
			for (int i = 0; i < checkboxList.size(); i++) {
				activeflag
						.deleteCharAt(Integer.parseInt(checkboxList.get(i)) - 1);
				activeflag.insert(Integer.parseInt(checkboxList.get(i)) - 1,
						"1");
			}
		}
		riskCaseModel.setUpuser(getCurrentUser().getUserId());
		riskCaseModel.setActiveflag(activeflag.toString());
		String mark = serviceContainer.getRiskService().UpdateOneRiskCase(
				riskCaseModel);
		json_encode(mark);
		return null;
	}

	// 风控版本
	public String queryRisklist() {
		List<?> riskcheckList = serviceContainer.getRiskService()
				.query_risk_all();
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 风控实例
	public String queryRisklistCase() {
		List<?> riskcheckList = serviceContainer.getRiskService()
				.query_risk_case_all(riskId);
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -------------------------------------------------------------银行卡黑名单-----------------------------------------------------------------------
	private BlacklistPanModel blackpanModel;

	public String showBlackPan() {
		return "black_pan_manager";
	}

	public String queryBlackPanByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanModel();
		}
		variables.put("pan", blackpanModel.getPan());
		Map<String, Object> groupList = serviceContainer.getRiskService()
				.findBlackPanByPage(variables, getPage(), getRows());
		json_encode(groupList);
		return null;
	}

	// 风险等级
	public String queryRiskLevel() {
		List<?> riskcheckList = serviceContainer.getRiskService()
				.query_risk_level();
		try {
			json_encode(riskcheckList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveBlackPan() {
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanModel();
		}
		String mark = serviceContainer.getRiskService().AddOneBlackPan(
				blackpanModel);
		json_encode(mark);
		return null;
	}

	public String queryOneBlackPan() {
		Map<String, Object> feeList = serviceContainer.getRiskService()
				.queryOneBlackPan(riskId);
		json_encode(feeList);
		return null;
	}

	public String updateBlackPan() {
		if (blackpanModel == null) {
			blackpanModel = new BlacklistPanModel();
		}
		String mark = serviceContainer.getRiskService().updateOneBlackPan(
				blackpanModel);
		json_encode(mark);
		return null;
	}

	public String deleteBlackPan() {
		String mark = serviceContainer.getRiskService().deleteOneBlackPan(
				riskId);
		json_encode(mark);
		return null;
	}

	public String startBlackPan() {
		String mark = serviceContainer.getRiskService()
				.startOneBlackPan(riskId);
		json_encode(mark);
		return null;
	}

	// --------------------------------------------------
	// 银行卡白名单------------------------------------------------------------------------
	private WhitePanModel whitepanModel;

	public String showWhitePan() {
		return "white_pan_manager";
	}

	public String queryWhitePanByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		variables.put("pan", whitepanModel.getPan());
		Map<String, Object> groupList = serviceContainer.getRiskService()
				.findWhitePanByPage(variables, getPage(), getRows());
		json_encode(groupList);
		return null;
	}

	public String saveWhitePan() {
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		String mark = serviceContainer.getRiskService().AddOneWhitePan(
				whitepanModel);
		json_encode(mark);
		return null;
	}

	public String queryOneWhitePan() {
		Map<String, Object> feeList = serviceContainer.getRiskService()
				.queryOneWhitePan(riskId);
		json_encode(feeList);
		return null;
	}

	public String updateWhitePan() {
		if (whitepanModel == null) {
			whitepanModel = new WhitePanModel();
		}
		String mark = serviceContainer.getRiskService().updateOneWhitePan(
				whitepanModel);
		json_encode(mark);
		return null;
	}

	public String deleteWhitePan() {
		String mark = serviceContainer.getRiskService().deleteOneWhitePan(
				riskId);
		json_encode(mark);
		return null;
	}

	public String startWhitePan() {
		String mark = serviceContainer.getRiskService()
				.startOneWhitePan(riskId);
		json_encode(mark);
		return null;
	}

	// --------------------------------------------------------------卡类别日累计限额------------------------------------------------------
	private BlacklistMemberModel blacklistMemberModel;

	public String showBlacklistMember() {
		return "black_mem_manager";
	}

	public String queryBlacklistMemberByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		variables.put("memberId", blacklistMemberModel.getMemberid());
		variables.put("merchName", merchName);
		Map<String, Object> LimitMemList = serviceContainer.getRiskService()
				.findBlacklistMemberByPage(variables, getPage(), getRows());
		json_encode(LimitMemList);
		return null;
	}

	public String saveBlacklistMember() {
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		String mark = serviceContainer.getRiskService().AddOneBlacklistMember(
				blacklistMemberModel);
		json_encode(mark);
		return null;
	}

	public String queryOneBlacklistMember() {
		Map<String, Object> onelimit = serviceContainer.getRiskService()
				.queryOneBlacklistMember(riskId);
		json_encode(onelimit);
		return null;
	}

	public String updateBlacklistMember() {
		if (blacklistMemberModel == null) {
			blacklistMemberModel = new BlacklistMemberModel();
		}
		String mark = serviceContainer.getRiskService().updateBlacklistMember(
				blacklistMemberModel);
		json_encode(mark);
		return null;
	}

	public String deleteBlacklistMember() {
		String mark = serviceContainer.getRiskService()
				.deleteOneBlacklistMember(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	public String startBlacklistMember() {
		String mark = serviceContainer.getRiskService()
				.startOneBlacklistMember(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	// -------------------------------------------------------------------------------------------
	// --------------------------------------------------------------分卡种单笔限额------------------------------------------------------
	private LimitCreditSingleModel limitCreditSingleModel;

	public String showLimitCreditSingle() {
		return "limit_credit_single_manager";
	}

	public String queryLimitSingleByPage() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("userId", getCurrentUser().getUserId());
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		variables.put("caseid", limitCreditSingleModel.getCaseid());
		variables.put("riskver", riskver);
		Map<String, Object> LimitMemList = serviceContainer.getRiskService()
				.findlimitCreditSingleByPage(variables, getPage(), getRows());
		json_encode(LimitMemList);
		return null;
	}

	public String saveLimitCreditSingle() {
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		String mark = serviceContainer.getRiskService()
				.AddOnelimitCreditSingle(limitCreditSingleModel);
		json_encode(mark);
		return null;
	}

	public String queryLimitCreditSingle() {
		Map<String, Object> onelimit = serviceContainer.getRiskService()
				.queryOnelimitCreditSingle(riskId);
		json_encode(onelimit);
		return null;
	}

	public String updateLimitCreditSingle() {
		if (limitCreditSingleModel == null) {
			limitCreditSingleModel = new LimitCreditSingleModel();
		}
		String mark = serviceContainer.getRiskService()
				.updateOnelimitCreditSingle(limitCreditSingleModel);
		json_encode(mark);
		return null;
	}

	public String deleteLimitCreditSingle() {
		String mark = serviceContainer.getRiskService()
				.deleteOnelimitCreditSingle(riskId,
						getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	public String startLimitCreditSingle() {
		String mark = serviceContainer
				.getRiskService()
				.startOnelimitCreditSingle(riskId, getCurrentUser().getUserId());
		json_encode(mark);
		return null;
	}

	
	
	//-----------------------------------------------------------------持卡人黑名单------------------------------------------------
    private BlackIdnumModel blackIdnumModel;
                
    public BlackIdnumModel getBlackIdnumModel() {
        return blackIdnumModel;
    }

    public void setBlackIdnumModel(BlackIdnumModel blackIdnumModel) {
        this.blackIdnumModel = blackIdnumModel;
    }

    public String showCardholderBlackList(){
        return "cardholder_black";
    }
    HttpServletRequest request = ServletActionContext.getRequest();
    public final static String DEFAULT_TIME_STAMP_FROMAT2 = "yyyy-MM-dd";
    /**
     * 根据身份证号查询持卡人黑名单
     * @return
     */
    public String queryCardHolderBlackList(){
        Map<String, Object> variables = new HashMap<String, Object>();
        if(blackIdnumModel == null){
            blackIdnumModel = new BlackIdnumModel();
        }
        variables.put("idnum", blackIdnumModel.getIdnum());
        
        Map<String, Object> result = serviceContainer.getCardHolderBlackService().queryCardHolderBlackList(variables,this.getPage(),this.getRows());
        json_encode(result);
        return null;
    }
    
    /***
     * 保存持卡人黑名单信息
     * @return
     */
    public void saveCardHolderBlack(){       
        if(blackIdnumModel == null){
            blackIdnumModel = new BlackIdnumModel();
        }
        String sdateString = blackIdnumModel.getSdate();
        String edateString = blackIdnumModel.getEdate();
        String[] sdate1 = sdateString.split("-");
        String[] edate1 = edateString.split("-");
        String sdate = "";
        String edate = ""; 
        for(int i=0;i<sdate1.length;i++){
            sdate = sdate+sdate1[i];
            edate = edate+edate1[i];
        }        
        blackIdnumModel.setSdate(sdate);
        blackIdnumModel.setEdate(edate);
        String result = serviceContainer.getCardHolderBlackService().AddOneBlackCardHolder(blackIdnumModel);
        json_encode(result);
    }
    
    /**
     * （根据选中的记录的tid）查询一条持卡人黑名单信息
     * @return
     */
    public String queryOneBlackCardHolder(){        
        String tid = request.getParameter("tid");
        Map<String, Object> blackCardHolder = serviceContainer.getCardHolderBlackService().queryOneBlackCardHolder(tid);
        String sdateString = (String) blackCardHolder.get("SDATE");
        String edateString = (String) blackCardHolder.get("EDATE");
        String sdate = sdateString.substring(0,4)+"-"+sdateString.substring(4,6)+"-"+sdateString.substring(6,8);
        String edate = edateString.substring(0,4)+"-"+edateString.substring(4,6)+"-"+edateString.substring(6,8);      
        blackCardHolder.put("SDATE", sdate);
        blackCardHolder.put("EDATE", edate);
    
        json_encode(blackCardHolder);
        return null;
    }
    
    /***
     * 修改持卡人黑名单
     */
    
    public String updateBlackCardHolder(){
        String tid = request.getParameter("tid");
        blackIdnumModel.setTid(tid);
        if(blackIdnumModel == null){
            blackIdnumModel = new BlackIdnumModel();
        }
        String sdateString = blackIdnumModel.getSdate();
        String edateString = blackIdnumModel.getEdate();
        String[] sdate1 = sdateString.split("-");
        String[] edate1 = edateString.split("-");
        String sdate = "";
        String edate = ""; 
        for(int i=0;i<sdate1.length;i++){
            sdate = sdate+sdate1[i];
            edate = edate+edate1[i];
        }        
        blackIdnumModel.setSdate(sdate);
        blackIdnumModel.setEdate(edate);
        String mark = serviceContainer.getCardHolderBlackService().updateBlackCardHolder(blackIdnumModel);
        json_encode(mark);
        return null;
        
    }
    /**
     * （根据选中记录的tid）注销此持卡人黑名单
     * @return
     */
    public String deleteCardHolderBlack(){
        String tid = request.getParameter("tid");
        String mark = serviceContainer.getCardHolderBlackService().delteOneCardHolderBlack(tid);
        json_encode(mark);
        return null;
    }
    /**
     * （根据选中记录的tid）启用此持卡人黑名单
     * @return
     */
    public String startCardHolderBlack(){
        String tid = request.getParameter("tid");
        String mark = serviceContainer.getCardHolderBlackService().startCardHolderBlack(tid);
        json_encode(mark);
        return null;
        
    }
	public RiskModel getRiskModel() {
		return riskModel;
	}

	public void setRiskModel(RiskModel riskModel) {
		this.riskModel = riskModel;
	}

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public String getRiskId() {
		return riskId;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public String getRiskver() {
		return riskver;
	}

	public void setRiskver(String riskver) {
		this.riskver = riskver;
	}

	public RiskCaseModel getRiskCaseModel() {
		return riskCaseModel;
	}

	public void setRiskCaseModel(RiskCaseModel riskCaseModel) {
		this.riskCaseModel = riskCaseModel;
	}

	public List<String> getCheckboxList() {
		return checkboxList;
	}

	public void setCheckboxList(List<String> checkboxList) {
		this.checkboxList = checkboxList;
	}

	public BlacklistPanModel getBlackpanModel() {
		return blackpanModel;
	}

	public void setBlackpanModel(BlacklistPanModel blackpanModel) {
		this.blackpanModel = blackpanModel;
	}

	public WhitePanModel getWhitepanModel() {
		return whitepanModel;
	}

	public void setWhitepanModel(WhitePanModel whitepanModel) {
		this.whitepanModel = whitepanModel;
	}

	public BlacklistMemberModel getBlacklistMemberModel() {
		return blacklistMemberModel;
	}

	public void setBlacklistMemberModel(
			BlacklistMemberModel blacklistMemberModel) {
		this.blacklistMemberModel = blacklistMemberModel;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public LimitCreditSingleModel getLimitCreditSingleModel() {
		return limitCreditSingleModel;
	}

	public void setLimitCreditSingleModel(
			LimitCreditSingleModel limitCreditSingleModel) {
		this.limitCreditSingleModel = limitCreditSingleModel;
	}

}
