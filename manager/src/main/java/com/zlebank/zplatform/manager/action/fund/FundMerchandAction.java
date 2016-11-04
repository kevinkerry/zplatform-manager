package com.zlebank.zplatform.manager.action.fund;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FundMerchant;
import com.zlebank.zplatform.manager.bean.FundMerchantBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.dao.object.FundMerchantBeanModel;
import com.zlebank.zplatform.manager.dao.object.FundMerchantModel;
import com.zlebank.zplatform.manager.service.iface.IfundMerchantService;
import com.zlebank.zplatform.trade.service.EnterpriseTradeService;

public class FundMerchandAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";

	@Autowired
	private IfundMerchantService service;
	@Autowired
	private EnterpriseTradeService enterpriseTradeService;

	private String mer_id;
	private String batchno; // 批次号
	private String orderId;
	private String chackBoxData; // 请求的数据
	private String biaoJi;
	private String beginDate;
	private String endDate;

	// 查询条件
	private FundQueryCondition fundBean;

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
		try {
			List<FundMerchantBean> list = null;
			if (batchno != null) {
				list = service.getmerBybatchno(batchno);
				if (list != null) {
					map.put("total", 1);
				}
			} else {
				// 查询所有
				fundBean = new FundQueryCondition(page, pageSize);
				PagResultBean allMerchantBean = service.getAllMerchantBean(fundBean);
				list = new ArrayList<FundMerchantBean>();
				if (list != null) {
					for (FundMerchantBeanModel mr : allMerchantBean.getList()) {
						FundMerchantBean fundMerchant = BeanCopyUtil.copyBean(FundMerchantBean.class, mr);
						list.add(fundMerchant);
					}
					Long rows = allMerchantBean.getRows();
					map.put("total", rows);
				}
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
		fundBean = new FundQueryCondition(page, pageSize);
		try {
			fundBean.setBatchNo(batchno);
			PagResultBean allMerchant = service.getAllMerchant(fundBean);
			List<FundMerchant> list = new ArrayList<FundMerchant>();
			if (list != null) {
				for (FundMerchantModel mr : allMerchant.getFundmerchants()) {
					FundMerchant fundMerchant = BeanCopyUtil.copyBean(FundMerchant.class, mr);
					list.add(fundMerchant);
				}
				map.put("total", allMerchant.getRows());
			} else {
				map.put("total", 0);
			}
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
	 * 根据订单号查询
	 */
	public void QueryByOrder() {
		int page = this.getPage();
		int pageSize = this.getRows();
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderId != null) {
			fundBean = new FundQueryCondition(page, pageSize);
			fundBean.setOrderId(orderId);
			try {
				PagResultBean pag = service.selectByOrder(fundBean);
				List<FundMerchantModel> fundmerchants = pag.getFundmerchants();
				List<FundMerchant> list = new ArrayList<FundMerchant>();
				if (fundmerchants != null) {
					for (FundMerchantModel mr : fundmerchants) {
						FundMerchant fundMerchant = BeanCopyUtil.copyBean(FundMerchant.class, mr);
						list.add(fundMerchant);
					}
					map.put("rows", list);
					map.put("total", pag.getRows());
				} else {
					map.put("total", 0);
					map.put("success", 2);
				}
			} catch (Exception e) {
				map.put("success", 1);
			}
			json_encode(map);
		}
	}

	/**
	 * 根据请求判断要审核的数据是明细“//”还是商户信息“-”
	 */
	public void getPag() {

		// 判断审核通过还是拒绝
		Map<String, Object> map = new HashMap<String, Object>();
		if (chackBoxData.indexOf("//") > -1) {
			// 包含|的话做明细审核tid
			List<String> tt = Arrays.asList(chackBoxData.split("//"));
			List<String> arr = new ArrayList<String>();
			Integer flg = 2 ;
			for (String str : tt) {
				arr.add(str.replace("//", ""));
				flg = audit1(arr);
			}
			map.put("success", flg);
		} else if (chackBoxData.indexOf("-") > -1) {
			// 包含 - 做商户审核，根据tid
			List<String> tt = Arrays.asList(chackBoxData.split("-"));
			List<String> arr = new ArrayList<String>();
			for (String str : tt) {
				arr.add(str.replace("-", ""));
				audit2(arr);
			}
			map.put("success", 1);
		}
		json_encode(map);
	}

	/**
	 * 明细审核通过订单号
	 */
	public Integer audit1(List<String> tids) {
		Date date = new Date();
		Integer falg = 1;
		try {
			service.auditBytid(tids, date, enterpriseTradeService);
		} catch (Exception e) {
			e.printStackTrace();
			falg = 2;
		}
		return falg;
	}

	/**
	 * 商户还款审核通过
	 */
	public void audit2(List<String> tids) {
		// 根据tid进行审核
		Date date = new Date();
		service.getAllDataByTid(tids, date);
	}

	/**
	 * 根据时间查询 前端验证不能为空
	 */
	public void QueryByDate() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 拿到时间调用方法查询所有满足条件的
		int page = this.getPage();
		int pageSize = this.getRows();
		FundQueryCondition fundBean = new FundQueryCondition(page, pageSize);
		if (beginDate == null) {
			fundBean.setEndDate(endDate);
		} else if (endDate == null) {
			fundBean.setBeginDate(beginDate);
		} else {
			fundBean.setBeginDate(beginDate);
			fundBean.setEndDate(endDate);
		}
		PagResultBean result = service.selectByDate(fundBean);
		if (result == null) {
			map.put("success", false);
			map.put("total", 0);
		} else {
			map.put("success", true);
			List<FundMerchantBeanModel> fundmerchants = result.getList();
			List<FundMerchantBean> list = new ArrayList<FundMerchantBean>();
			for (FundMerchantBeanModel mr : fundmerchants) {
				FundMerchantBean fundMerchant = BeanCopyUtil.copyBean(FundMerchantBean.class, mr);
				list.add(fundMerchant);
			}
			map.put("rows", list);
			map.put("total", result.getRows());
		}
		json_encode(map);
	}
}