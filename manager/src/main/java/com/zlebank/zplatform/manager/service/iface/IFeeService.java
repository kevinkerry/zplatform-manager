package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.BusiRateModel;
import com.zlebank.zplatform.manager.dao.object.CardRateModel;
import com.zlebank.zplatform.manager.dao.object.FeeCaseModel;
import com.zlebank.zplatform.manager.dao.object.FeeModel;
import com.zlebank.zplatform.manager.dao.object.MemberRateModel;
import com.zlebank.zplatform.manager.dao.object.StepRateModel;

public interface IFeeService extends IBaseService<FeeModel, Long>{
	  public Map<String, Object> findFeeByPage(Map<String, Object> variables, int page,int rows) ;
	  public String AddOneFee(FeeModel fee) ;
	  public List<?> queryFeeCaseByFeever(String feever);
	  public Map<String, Object> queryOneFee(String feever);
	  public List<?> querFeeFlagType();
	  public List<?> queryFeeSelfType();
	  public String UpdateFeeCase(FeeCaseModel feecase);	
	  public Map<String, Object> queryFeeOneCase(String caseid);
	  public String UpdateFee(FeeModel fee);	
	  
	  public Map<String, Object> findBusiRateByPage(Map<String, Object> variables, int page,int rows);
	  public String AddOneBusiRate(BusiRateModel busirate);
	  public List<?> queryFeeAll();
	  public Map<String, Object> queryOneBusiRate(String tid) ;
	  public String UpdateBusiRate(BusiRateModel busirate);  
	  
	  public Map<String, Object> findMemberRateByPage(Map<String, Object> variables, int page,int rows);
	  public String AddOneMemberRate(MemberRateModel memberrate);
	  public String UpdateMemberRate(MemberRateModel memberrate); 
	  public Map<String, Object> queryOneMemberRate(String tid);
	  
	  public Map<String, Object> findCardRateByPage(Map<String, Object> variables, int page,int rows);		
 	  public String AddOneCardRate(CardRateModel cardrate);
 	  public String UpdateCardRate(CardRateModel cardrate);
 	  public Map<String, Object> queryOneCardRate(String tid);
 	  
 	  public Map<String, Object> findStpeRateByPage(Map<String, Object> variables, int page,int rows);
	  public String AddOneStpeRate(StepRateModel steprate);
	  public String UpdateStpeRate(StepRateModel steprate) ;
	  public Map<String, Object> queryOneStpeRate(String tid);      
}
