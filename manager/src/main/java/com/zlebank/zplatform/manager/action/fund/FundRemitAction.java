package com.zlebank.zplatform.manager.action.fund;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.thoughtworks.xstream.mapper.Mapper.Null;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.bean.Message;
import com.zlebank.zplatform.manager.bean.RaiseTr;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;
import com.zlebank.zplatform.manager.service.iface.RaiseTrService;
import com.zlebank.zplatform.trade.service.EnterpriseTradeService;

import net.sf.json.JSONObject;

public class FundRemitAction extends BaseAction {

	private String flag;

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
	@Autowired
	private RaiseTrService service;
	@Autowired
	private EnterpriseTradeService enterpriseTradeService;
	
	private String orderId;

	private Long tid;
	// 查询条件
	FundQueryCondition fundBean;

	
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	public String show() {

		return "success";
	}

	/**
	 * 查询出所有的募集款划转信息
	 * 
	 * @return
	 */
	public void query() {
		int page = this.getPage();
		int pageSize = this.getRows();
		Long total = (long) 0;
		fundBean = new FundQueryCondition(page, pageSize);
		List<RaiseTr> li = new ArrayList<RaiseTr>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderId == null) {
			PagResultBean bean = service.selectAllRaise(fundBean);
			for (RaiseTrModel mr : bean.getRaiseTrModels()) {
				RaiseTr fundMerchant = BeanCopyUtil.copyBean(RaiseTr.class, mr);
				li.add(fundMerchant);
			}
			total = bean.getRows();
			map.put("total", total);
		} else {
			li = search();
			if (li != null) {
				map.put("total", 1);
			}
		}
		map.put("rows", li);
		json_encode(map);
	}

	/**
	 * 根据单号查询募集款订单信息
	 */
	public List<RaiseTr> search() {
		int page = this.getPage();
		int pageSize = this.getRows();
		List<RaiseTr> li = service.selectByOrder(orderId);
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("rows", li);
		// json_encode(map);
		return li;
	}

	/**
	 * 审核
	 */
	public Map<String, Object> aduit() {
		// Message mes = new Message();
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		try {
			service.aduitMes(tid, date,enterpriseTradeService);
			// mes.setMess(true);
			map.put("success", 1);
		} catch (Exception e) {
			// mes.setMess(false);
			map.put("success", 2);
		}
		json_encode(map);
		return null;
	}
}
