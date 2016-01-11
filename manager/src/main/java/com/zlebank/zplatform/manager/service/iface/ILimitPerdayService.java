package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.LimitMemCreditDayModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemCreditMonthModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemDayModel;
import com.zlebank.zplatform.manager.dao.object.LimitMemMonthModel;
import com.zlebank.zplatform.manager.dao.object.LimitPerdayModel;
import com.zlebank.zplatform.manager.dao.object.LimitSingleModel;

public interface ILimitPerdayService extends IBaseService<LimitPerdayModel, Long>{
	public List<?> query_risk_all() ;
    public String AddOneLimitPerday(LimitPerdayModel limitPerday);
    public Map<String, Object> queryOneLimitPerday(String tId);
	public Map<String, Object> findLimitPerdayByPage(Map<String, Object> variables, int page,int rows);
    public String updateOneLimitPerday(LimitPerdayModel limitPerday);
    public String deleteOneLimitPerday(String tId);
    public String startOneLimitPerday(String tId);
    
    
    public Map<String, Object> findLimitMemCreditDayByPage(Map<String, Object> variables, int page,int rows);
    public String AddOneLimitMemCreditDay(LimitMemCreditDayModel Mem);
    public Map<String, Object> queryOneLimitMemCreditDay(String tId);
    public String UpdateLimitMemCreditDay(LimitMemCreditDayModel Mem);
    public String deleteLimitMemCreditDay(String tId);
    public String startLimitMemCreditDay(String tId);
    
    public Map<String, Object> findLimitMemCreditMonthByPage(Map<String, Object> variables, int page,int rows);
    public String AddOneLimitMemCreditMonth(LimitMemCreditMonthModel Mem);
    public Map<String, Object> queryOneLimitMemCreditMonth(String tId);	 
    public String UpdateLimitMemCreditMonth(LimitMemCreditMonthModel Mem); 
    public String deleteLimitMemCreditMonth(String tId);
    public String startLimitMemCreditMonth(String tId);
    
    public Map<String, Object> findLimitMemMonthByPage(Map<String, Object> variables, int page,int rows);
    public String AddOneLimitMemMonth(LimitMemMonthModel Mem);
    public Map<String, Object> queryOneLimitMemMonth(String tId); 
    public String UpdateLimitMemMonth(LimitMemMonthModel Mem);  
    public String deleteLimitMemMonth(String tId,Long userid);
    public String startLimitMemMonth(String tId,Long userid);	
    
    public Map<String, Object> findLimitMemDayByPage(Map<String, Object> variables, int page,int rows);
    public String AddOneLimitMemDay(LimitMemDayModel Mem);
    public Map<String, Object> queryOneLimitMemDay(String tId);	 
    public String UpdateLimitMemDay(LimitMemDayModel Mem);
    public String deleteLimitMemDay(String tId,Long userid);
    public String startLimitMemDay(String tId,Long userid);
    
    public Map<String, Object> findLimitSingleByPage(Map<String, Object> variables, int page,int rows);
    public String AddOneLimitSingle(LimitSingleModel limitSingle);
    public String updateOneLimitSingle(LimitSingleModel limitSingle);     
    public Map<String, Object> queryOneLimitSingle(String tId);
    public String deleteOneLimitSingle(String tId);
    public String startOneLimitSingle(String tId);
     
}
