package com.zlebank.zplatform.manager.action.fund;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.Message;
import com.zlebank.zplatform.manager.bean.RaiseTr;
import com.zlebank.zplatform.manager.service.iface.RaiseTrService;

import net.sf.json.JSONObject;

public class FundRemitAction extends BaseAction{
	
	private String flag;
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
	
	@Autowired
	private RaiseTrService service;
	
	private String orderId;
	
	
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	/**
	 * 页面跳转
	 * @return
	 */
	public String show(){
		
		return "success";
	}
	
	
	/**
	 * 查询出所有的募集款划转信息
	 * @return
	 */
	public void query(){
		int page = this.getPage();
		int pageSize = this.getRows();
		List<RaiseTr> li =null;
		if(orderId==null){
		 li = service.selectAllRaise();
		}else{
			li = search();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", li);
		json_encode(map);
	}
	/**
	 * 查询制定单号的募集款订单信息
	 */
	public List<RaiseTr> search(){
		int page = this.getPage();
		int pageSize = this.getRows();
		List<RaiseTr> li = service.selectByOrder(orderId);
	//	Map<String, Object> map = new HashMap<String, Object>();
	//	map.put("rows", li);
	//	json_encode(map);
		return li;
	}
	
	
	/**
	 * 审核
	 */
	public Map<String, Object> aduit(){
		//Message mes = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		try {
			service.aduitMes(orderId,date);
//			mes.setMess(true);
			map.put("success", 1);
		} catch (Exception e) {
//			mes.setMess(false);
			map.put("success", 2);
		}
		json_encode(map);
		return null;
	}
}
