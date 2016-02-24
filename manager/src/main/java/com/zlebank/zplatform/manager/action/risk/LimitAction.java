package com.zlebank.zplatform.manager.action.risk;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.LimitMemCreditDayModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemCreditMonthModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemDayModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemMonthModel;
import com.zlebank.zplatform.manager.dao.object.LimitPerdayModel;
import com.zlebank.zplatform.manager.dao.object.LimitSingleModel;
import com.zlebank.zplatform.manager.dao.object.RiskCaseModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;



public class LimitAction extends BaseAction{
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 523499466692436803L;
    private LimitPerdayModel limitPerdayModel;
    private RiskCaseModel riskCaseModel;
    private String riskId;
    private String tId;
    private String riskver;
    private ServiceContainer serviceContainer;
    public String showPerday(){
    	return "limit_perday";
	}
//对应页面功能模块：银行卡单日限次,卡类别日累计限额，单笔限额
	//--------------------------------------------------------------单卡单日限次------------------------------------------------------
	
		public String  queryLimitPerdayByPage(){
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("userId", getCurrentUser().getUserId());
			if(riskCaseModel==null){
				riskCaseModel=new RiskCaseModel();
			}
			variables.put("busicode", riskCaseModel.getBusicode());
			variables.put("riskver", riskCaseModel.getRiskver());
			Map<String, Object> groupList=serviceContainer.getLimitperdayService().findLimitPerdayByPage(variables, getPage(), getRows());
			json_encode(groupList);
			return null;
		}
		public String  saveLimitPerday(){
			if(limitPerdayModel==null){
				limitPerdayModel=new LimitPerdayModel();
			}
			String mark=serviceContainer.getLimitperdayService().AddOneLimitPerday(limitPerdayModel);
			json_encode(mark);
			return null;
		}
		public String  queryOneLimitPerday(){
			Map<String, Object> feeList=serviceContainer.getLimitperdayService().queryOneLimitPerday(riskId);
			json_encode(feeList);
			return null;		}
		public String  updateLimitPerday(){
			if(limitPerdayModel==null){
				limitPerdayModel=new LimitPerdayModel();
			}
			String mark=serviceContainer.getLimitperdayService().updateOneLimitPerday(limitPerdayModel);
			json_encode(mark);
			return null;
		}  	
		public String deleteLimitPerday(){
			String mark=serviceContainer.getLimitperdayService().deleteOneLimitPerday(riskId);
			json_encode(mark);
			return null;
		}
		public String startLimitPerday(){
			String mark=serviceContainer.getLimitperdayService().startOneLimitPerday(riskId);
			json_encode(mark);
			return null;
		}
		//风险等级
		public String queryRiskLevel(){
			List<?> riskcheckList=serviceContainer.getRiskService().query_risk_level();
			try {
				json_encode(riskcheckList);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//风控版本
		public String queryRiskAll(){
			List<?> List=serviceContainer.getLimitperdayService().query_risk_all();
			try {
				json_encode(List);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
//--------------------------------------------------------------卡类别日累计限额------------------------------------------------------
		private LimitMemCreditDayModel limitMemCreditDayModel;
		public String showLimitMemCreditDay(){
		    	return "success";
		}
		public String  queryLimitMemCreditDayByPage(){
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("userId", getCurrentUser().getUserId());
			if(limitMemCreditDayModel==null){
				limitMemCreditDayModel=new LimitMemCreditDayModel();
			}
			variables.put("memberid", limitMemCreditDayModel.getMemberid());
			variables.put("card_type", limitMemCreditDayModel.getCardType());
			Map<String, Object> LimitMemList=serviceContainer.getLimitperdayService().findLimitMemCreditDayByPage(variables, getPage(), getRows());
			json_encode(LimitMemList);
			return null;
		}
		public String  saveLimitMemCreditDay(){
			if(limitMemCreditDayModel==null){
				limitMemCreditDayModel=new LimitMemCreditDayModel();
			}
			String mark=serviceContainer.getLimitperdayService().AddOneLimitMemCreditDay(limitMemCreditDayModel);
			json_encode(mark);
			return null;
		}
		public String  queryLimitMemCreditDay(){
			Map<String, Object> onelimit=serviceContainer.getLimitperdayService().queryOneLimitMemCreditDay(riskId);
			json_encode(onelimit);
			return null;		
	    }
		public String  updateLimitMemCreditDay(){
			if(limitMemCreditDayModel==null){
				limitMemCreditDayModel=new LimitMemCreditDayModel();
			}
			String mark=serviceContainer.getLimitperdayService().UpdateLimitMemCreditDay(limitMemCreditDayModel);
			json_encode(mark);
			return null;
		} 
		public String deleteLimitMemCreditDay(){
			String mark=serviceContainer.getLimitperdayService().deleteLimitMemCreditDay(riskId);
			json_encode(mark);
			return null;
		}
		public String startLimitMemCreditDay(){
			String mark=serviceContainer.getLimitperdayService().startLimitMemCreditDay(riskId);
			json_encode(mark);
			return null;
		}	
		//--------------------------------------------------------------卡类别月累计限额------------------------------------------------------
		private LimitMemCreditMonthModel limitMemCreditMonthModel;
		public String showLimitMemCreditMonth(){
				    	return "limit_mem_credit_month_manager";
		}
		public String  queryLimitMemCreditMonthByPage(){
					Map<String, Object> variables = new HashMap<String, Object>();
					variables.put("userId", getCurrentUser().getUserId());
					if(limitMemCreditMonthModel==null){
						limitMemCreditMonthModel=new LimitMemCreditMonthModel();
					}
					variables.put("memberid", limitMemCreditMonthModel.getMemberid());
					variables.put("card_type", limitMemCreditMonthModel.getCardType());
					Map<String, Object> LimitMemList=serviceContainer.getLimitperdayService().findLimitMemCreditMonthByPage(variables, getPage(), getRows());
					json_encode(LimitMemList);
					return null;
				}
		public String  saveLimitMemCreditMonth(){
					if(limitMemCreditMonthModel==null){
						limitMemCreditMonthModel=new LimitMemCreditMonthModel();
					}
					String mark=serviceContainer.getLimitperdayService().AddOneLimitMemCreditMonth(limitMemCreditMonthModel);
					json_encode(mark);
					return null;
		}
		public String  queryLimitMemCreditMonth(){
					Map<String, Object> onelimit=serviceContainer.getLimitperdayService().queryOneLimitMemCreditMonth(riskId);
					json_encode(onelimit);
					return null;		
	   }
		public String  updateLimitMemCreditMonth(){
					if(limitMemCreditMonthModel==null){
						limitMemCreditMonthModel=new LimitMemCreditMonthModel();
					}
					String mark=serviceContainer.getLimitperdayService().UpdateLimitMemCreditMonth(limitMemCreditMonthModel);
					json_encode(mark);
					return null;
		} 
		public String deleteLimitMemCreditMonth(){
					String mark=serviceContainer.getLimitperdayService().deleteLimitMemCreditMonth(riskId);
					json_encode(mark);
					return null;
		}
		public String startLimitMemCreditMonth(){
					String mark=serviceContainer.getLimitperdayService().startLimitMemCreditMonth(riskId);
					json_encode(mark);
					return null;
		}
		//--------------------------------------------------------------商户月累计限额------------------------------------------------------
		private LimitMemMonthModel limitMemMonthModel;
		public String showLimitMemMonth(){
				    	return "limit_mem_month_manager";
		}
		public String  queryLimitMemMonthByPage(){
					Map<String, Object> variables = new HashMap<String, Object>();
					variables.put("userId", getCurrentUser().getUserId());
					if(limitMemMonthModel==null){
						limitMemMonthModel=new LimitMemMonthModel();
					}
					variables.put("memberid", limitMemMonthModel.getMemberid());
					Map<String, Object> LimitMemList=serviceContainer.getLimitperdayService().findLimitMemMonthByPage(variables, getPage(), getRows());
					json_encode(LimitMemList);
					return null;
				}
		public String  saveLimitMemMonth(){
					if(limitMemMonthModel==null){
						limitMemMonthModel=new LimitMemMonthModel();
					}
					String mark=serviceContainer.getLimitperdayService().AddOneLimitMemMonth(limitMemMonthModel);
					json_encode(mark);
					return null;
		}
		public String  queryLimitMemMonth(){
					Map<String, Object> onelimit=serviceContainer.getLimitperdayService().queryOneLimitMemMonth(riskId);
					json_encode(onelimit);
					return null;		
	   }
		public String  updateLimitMemMonth(){
					if(limitMemMonthModel==null){
						limitMemMonthModel=new LimitMemMonthModel();
					}
					String mark=serviceContainer.getLimitperdayService().UpdateLimitMemMonth(limitMemMonthModel);
					json_encode(mark);
					return null;
		} 
		public String deleteLimitMemMonth(){
			        
					String mark=serviceContainer.getLimitperdayService().deleteLimitMemMonth(riskId,getCurrentUser().getUserId());
					json_encode(mark);
					return null;
		}
		public String startLimitMemMonth(){
					String mark=serviceContainer.getLimitperdayService().startLimitMemMonth(riskId,getCurrentUser().getUserId());
					json_encode(mark);
					return null;
		}
		//--------------------------------------------------------------商户日累计限额------------------------------------------------------
				private LimitMemDayModel limitMemDayModel;
				public String showLimitMemDay(){
				    	return "limit_mem_day_manager";
				}
				public String  queryLimitMemDayByPage(){
					Map<String, Object> variables = new HashMap<String, Object>();
					variables.put("userId", getCurrentUser().getUserId());
					if(limitMemDayModel==null){
						limitMemDayModel=new LimitMemDayModel();
					}
					variables.put("memberid", limitMemDayModel.getMemberid());
					Map<String, Object> LimitMemList=serviceContainer.getLimitperdayService().findLimitMemDayByPage(variables, getPage(), getRows());
					json_encode(LimitMemList);
					return null;
				}
				public String  saveLimitMemDay(){
					if(limitMemDayModel==null){
						limitMemDayModel=new LimitMemDayModel();
					}
					String mark=serviceContainer.getLimitperdayService().AddOneLimitMemDay(limitMemDayModel);
					json_encode(mark);
					return null;
				}
				public String  queryLimitMemDay(){
					Map<String, Object> onelimit=serviceContainer.getLimitperdayService().queryOneLimitMemDay(riskId);
					json_encode(onelimit);
					return null;		
			    }
				public String  updateLimitMemDay(){
					if(limitMemDayModel==null){
						limitMemDayModel=new LimitMemDayModel();
					}
					String mark=serviceContainer.getLimitperdayService().UpdateLimitMemDay(limitMemDayModel);
					json_encode(mark);
					return null;
				} 
				public String deleteLimitMemDay(){
					String mark=serviceContainer.getLimitperdayService().deleteLimitMemDay(riskId,getCurrentUser().getUserId());
					json_encode(mark);
					return null;
				}
				public String startLimitMemDay(){
					String mark=serviceContainer.getLimitperdayService().startLimitMemDay(riskId,getCurrentUser().getUserId());
					json_encode(mark);
					return null;
				}
//--------------------------------------------------------------单笔限额------------------------------------------------------
				private LimitSingleModel limitSingleModel;
				public String showLimitSingle(){
				    	return "limit_single_manager";
				}
				public String  queryLimitSingleByPage(){
					Map<String, Object> variables = new HashMap<String, Object>();
					variables.put("userId", getCurrentUser().getUserId());
					if(limitSingleModel==null){
						limitSingleModel=new LimitSingleModel();
					}
					variables.put("caseid", limitSingleModel.getCaseid());
					variables.put("riskver", riskver);
					Map<String, Object> LimitMemList=serviceContainer.getLimitperdayService().findLimitSingleByPage(variables, getPage(), getRows());
					json_encode(LimitMemList);
					return null;
				}
				public String  saveLimitSingle(){
					if(limitSingleModel==null){
						limitSingleModel=new LimitSingleModel();
					}
					String mark=serviceContainer.getLimitperdayService().AddOneLimitSingle(limitSingleModel);
					json_encode(mark);
					return null;
				}
				public String  queryLimitSingle(){
					Map<String, Object> onelimit=serviceContainer.getLimitperdayService().queryOneLimitSingle(riskId);
					json_encode(onelimit);
					return null;		
			    }
				public String  updateLimitSingle(){
					if(limitSingleModel==null){
						limitSingleModel=new LimitSingleModel();
					}
					String mark=serviceContainer.getLimitperdayService().updateOneLimitSingle(limitSingleModel);
					json_encode(mark);
					return null;
				} 
				public String deleteLimitSingle(){
					String mark=serviceContainer.getLimitperdayService().deleteOneLimitSingle(riskId);
					json_encode(mark);
					return null;
				}
				public String startLimitSingle(){
					String mark=serviceContainer.getLimitperdayService().startOneLimitSingle(riskId);
					json_encode(mark);
					return null;
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
	public LimitPerdayModel getLimitPerdayModel() {
		return limitPerdayModel;
	}
	public void setLimitPerdayModel(LimitPerdayModel limitPerdayModel) {
		this.limitPerdayModel = limitPerdayModel;
	}
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public RiskCaseModel getRiskCaseModel() {
		return riskCaseModel;
	}
	public void setRiskCaseModel(RiskCaseModel riskCaseModel) {
		this.riskCaseModel = riskCaseModel;
	}
	public LimitMemCreditDayModel getLimitMemCreditDayModel() {
		return limitMemCreditDayModel;
	}
	public void setLimitMemCreditDayModel(
			LimitMemCreditDayModel limitMemCreditDayModel) {
		this.limitMemCreditDayModel = limitMemCreditDayModel;
	}
	public LimitMemCreditMonthModel getLimitMemCreditMonthModel() {
		return limitMemCreditMonthModel;
	}
	public void setLimitMemCreditMonthModel(
			LimitMemCreditMonthModel limitMemCreditMonthModel) {
		this.limitMemCreditMonthModel = limitMemCreditMonthModel;
	}
	public LimitMemMonthModel getLimitMemMonthModel() {
		return limitMemMonthModel;
	}
	public void setLimitMemMonthModel(LimitMemMonthModel limitMemMonthModel) {
		this.limitMemMonthModel = limitMemMonthModel;
	}
	public LimitMemDayModel getLimitMemDayModel() {
		return limitMemDayModel;
	}
	public void setLimitMemDayModel(LimitMemDayModel limitMemDayModel) {
		this.limitMemDayModel = limitMemDayModel;
	}

	public LimitSingleModel getLimitSingleModel() {
		return limitSingleModel;
	}

	public void setLimitSingleModel(LimitSingleModel limitSingleModel) {
		this.limitSingleModel = limitSingleModel;
	}

}
