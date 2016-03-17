package com.zlebank.zplatform.manager.service.iface;

import java.util.HashMap;
import java.util.List;

import com.zlebank.zplatform.manager.dao.object.ParaDicModel;

public interface IParaDicService extends IBaseService<ParaDicModel, Long>{
	
	public List<?> getAllParaListByParaType(String paraType);
	
	public List<?> getAllParaListByParaType(String paraType,String status);
	
	public String  AddOneParaDic(ParaDicModel  paradic);
	public List<?> queryParaDicList(String parentid,String paraname,String status,int page,int rows);
	public Long queryParaDicCount(String parentid,String paraname,String status) ;
	public String  UpdatePara(ParaDicModel paradic);
	public List<?>  count_report(String year,String month,String type);
    //交易统计
	public List<?>  merchant_report(String year,String month,String type,String merchNo,String merchName,String totalType,int page,int rows);
	public Long  merchant_report_NUM(String year,String month,String type,String merchNo,String merchName,String totalType);
	public HashMap<String, Object>  merchant_report_detail(String type,String merchNo,String merDate);
	public List<?>  merchant_report_all(String year,String month,String type,String merchNo,String merchName,String totalType);
	public List<?>  trade_detail_query(Long userId,String year,String month,String merchNo,String merchOutNo,String posNo,int page,int rows);
	public Long trade_detail_query_num(Long userId,String startDate,String endDate,String merchNo,String merchOutNo,String posNo);
	public String  query_sysdate();
    public HashMap<String, Object>  trade_one_detail(String tradeId);	
    public List<?> trade_detail_all(Long userId,String year,String month,String merchNo,String merchOutNo,String posNo);
    public List<?>  pos_trade_sel(String year,String month,String type,String merchNo,String posNo,int page,int rows);
    public HashMap<String, Object>  pos_trade_one_detail(String type,String posNo,String posDate);
    public Long pos_trade_sel_num(String startDate,String endDate,String type,String merchNo,String posNo);
    public List<?> pos_trade_sel_export(String year,String month,String type,String merchNo,String posNo);
    public List<?>  out_yeji_sel(String year,String month,String type,String totalType,String deminNo,String deminName,int page,int rows);
	public List<?>  out_yeji_sel_export(String year,String month,String type,String totalType,String deminNo,String deminName);
	public Long  out_yeji_sel_num(String startDate,String endDate,String type,String totalType,String deminNo,String deminName);
}
