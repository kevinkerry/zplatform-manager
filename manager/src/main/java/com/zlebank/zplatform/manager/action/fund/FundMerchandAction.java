package com.zlebank.zplatform.manager.action.fund;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FundAuditBean;
import com.zlebank.zplatform.manager.bean.FundMerchant;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.TranBatch;
import com.zlebank.zplatform.manager.service.iface.IInsteadPayService;
import com.zlebank.zplatform.manager.service.iface.IfundMerchantService;
import com.zlebank.zplatform.trade.bean.InsteadPayBatchBean;
import com.zlebank.zplatform.trade.bean.InsteadPayBatchQuery;
import com.zlebank.zplatform.trade.service.InsteadBatchService;
import com.zlebank.zplatform.trade.service.InsteadPayService;

public class FundMerchandAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";

	@Autowired
	private IfundMerchantService service;
	@Autowired
	private InsteadBatchService insteadBatchService;
	@Autowired
	private InsteadPayService insteadPayService;
	@Autowired
	private IInsteadPayService iInstea;
	private String mer_id;
	private String batchno; // 批次号
	private String orderId;
	private String chackBoxData; // 请求的数据
	private String biaoJi;
	private String status;

	/** 批次查询条件 **/
	InsteadPayBatchQuery insteadPayBatchQuery;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setMer_id(String mer_id) {
		this.mer_id = mer_id;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public void setChackBoxData(String chackBoxData) {
		this.chackBoxData = chackBoxData;
	}

	public void setBiaoJi(String biaoJi) {
		biaoJi = this.biaoJi;
	}

	public IfundMerchantService getService() {
		return service;
	}

	public void setService(IfundMerchantService service) {
		this.service = service;
	}

	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	public String show() {
		return "success";
	}

	/**
	 * 所有商户查询
	 */
	public void getAllmers() {
		int page = this.getPage();
		int pageSize = this.getRows();
		Map<String, Object> map = new HashMap<String, Object>();
		PagedResult<FundMerchantBean> result = service.queryPaged(page, pageSize, insteadPayBatchQuery);
		try {
			List<FundMerchantBean> list = null;
			if (result != null) {
				Long total = result.getTotal();
				map.put("total", total);
			}
			if (batchno != null) {
				list = service.getmerBybatchno(batchno);
			} else if (status != null) {
				list = service.getmerByStatus(status);
			} else {
				list = service.getAllMerchantBean();
			}

			map.put("rows", list);
			json_encode(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 根据批次商户还款明细查询
	 * 
	 * @return
	 */
	public void getMessage() {
		int page = this.getPage();
		int pageSize = this.getRows();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<FundMerchant> list = service.getAllMerchant(batchno);
			// map.put("total",total);
			map.put("rows", list);
			json_encode(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 按照商户id查询
	 */
	public void getMerById() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			FundMerchant list = service.getMerchant(mer_id);
			map.put("rows", list);
			json_encode(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据请求判断要审核的数据是明细“|”还是商户信息“-”
	 */
	public Map<String, Object> getPag() {

		// 判断审核通过还是拒绝
		Map<String, Object> map = new HashMap<String, Object>();
		if (chackBoxData.indexOf("//") > -1) {
			// 包含|的话做明细审核订单号
			List<String> tt = Arrays.asList(chackBoxData.split("//"));
			List<String> arr = new ArrayList<String>();
			for (String str : tt) {

				arr.add(str.replace("//", ""));
				audit1(arr);
			}
			map.put("success", 1);
		} else if (chackBoxData.indexOf("-") > -1) {
			// 包含 - 做商户审核，根据批次号
			List<String> tt = Arrays.asList(chackBoxData.split("-"));
			List<String> arr = new ArrayList<String>();
			for (String str : tt) {
				// if(biaoJi.indexOf("T")>-1){
				arr.add(str.replace("-", ""));
				audit2(arr);
				// }else {
				// 审核拒绝
				// auditNo2(arr);
				// }
			}
			map.put("success", 1);
		}
		json_encode(map);
		return null;
	}

	/**
	 * 明细审核通过订单号
	 */
	public void audit1(List<String> arr) {
		// 根据订单号修改状态
		Date data = new Date();
		service.auditByOrder(arr,data);
	}

	/**
	 * 商户还款审核通过model
	 */
	public void audit2(List<String> arr) {
		// 根据批次号进行审核
		Date data = new Date();
		service.auditByPc(arr,data);
	}

	/**
	 * 审核拒绝
	 */
	public void auditNo1(List<String> arr) {
		service.auditNoByOrder(arr);
	}

	/**
	 * 商户还款审核拒绝model
	 */
	public void auditNo2(List<String> arr) {
		// 根据批次号进行审核
		service.auditNoByPc(arr);
	}

}
