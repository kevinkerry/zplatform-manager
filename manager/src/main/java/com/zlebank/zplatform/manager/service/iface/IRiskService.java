package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.BlacklistMemberModel;
import com.zlebank.zplatform.manager.dao.object.BlacklistPanModel;
import com.zlebank.zplatform.manager.dao.object.LimitCreditSingleModel;
import com.zlebank.zplatform.manager.dao.object.RiskCaseModel;
import com.zlebank.zplatform.manager.dao.object.RiskModel;
import com.zlebank.zplatform.manager.dao.object.WhitePanModel;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

public interface IRiskService extends IBaseService<RiskModel, Long>{
	  public Map<String, Object> findRiskByPage(Map<String, Object> variables, int page,int rows);
	  public String AddOneRisk(RiskModel risk) ;
	  public Map<String, Object> queryOneRisk(String riskId);
	  public String UpdateOneRisk(RiskModel risk) ;
	  public List<?> queryRiskCase(String riskver);
	  public List<?> query_risk_list() ;
	  public Map<String, Object> queryOneRiskCase(String v_caseid);
	  public List<?> query_risk_list_active(String riskid);
	  public String UpdateOneRiskCase(RiskCaseModel riskcase);
	  
	  public Map<String, Object> findBlackPanByPage(Map<String, Object> variables, int page,int rows);
	  public List<?> query_risk_level();
	  public String AddOneBlackPan(BlacklistPanModel blackPan);
      public Map<String, Object> queryOneBlackPan(String tId);
 	  public String updateOneBlackPan(BlacklistPanModel blackPan);
 	  public String deleteOneBlackPan(String tid) ;
 	  public String startOneBlackPan(String tid);	
 	  
	 public Map<String, Object> findWhitePanByPage(Map<String, Object> variables, int page,int rows);
     public String AddOneWhitePan(WhitePanModel whitePan);
	 public Map<String, Object> queryOneWhitePan(String tId);
	 public String updateOneWhitePan(WhitePanModel whitePan);
	 public String deleteOneWhitePan(String tid);
	 public String startOneWhitePan(String tid);
	 
	 public List<?> query_risk_all();
	 public List<?> query_risk_case_all(String riskver);
	
	 public Map<String, Object> findBlacklistMemberByPage(Map<String, Object> variables, int page,int rows);
     public String AddOneBlacklistMember(BlacklistMemberModel blm);  
	 public Map<String, Object> queryOneBlacklistMember(String tId);
	 public String updateBlacklistMember(BlacklistMemberModel blm);
	 public String deleteOneBlacklistMember(String tid,Long userid);
	 public String startOneBlacklistMember(String tid,Long userid);
	 
	 public Map<String, Object> findlimitCreditSingleByPage(Map<String, Object> variables, int page,int rows);
	 public String AddOnelimitCreditSingle(LimitCreditSingleModel limitSingle);
	 public String updateOnelimitCreditSingle(LimitCreditSingleModel limitSingle);
	 public Map<String, Object> queryOnelimitCreditSingle(String tId);	
	 public String deleteOnelimitCreditSingle(String tId,Long userid);
	 public String startOnelimitCreditSingle(String tId,Long userid);
	 /**
	  * 提现风控
	  * @param merchId
	  * @param subMerchId
	  * @param memberId
	  * @param busiCode
	  * @param txnAmt
	  * @param cardType
	  * @param cardNo
	  * @throws TradeException
	  */
	 public void tradeRiskControl(String merchId,String subMerchId,String memberId,String busiCode,String txnAmt,String cardType,String cardNo) throws TradeException;
	     
	 /**
	  * 提现手续费
	  * @param txnsLog
	  * @return
	  */
	  public Long getTxnFee(TxnsLogModel txnsLog);
	  
	  /**
	   * 交易明细
	   * @param txnseqno
	   * @return
	   */
	  public List<?>  getTxnsLogbyId(String txnseqno);
	  

}
