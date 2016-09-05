package com.zlebank.zplatform.manager.action.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FinRealNameBean;
import com.zlebank.zplatform.manager.dao.object.scan.EnterpriseRealnameMode;
import com.zlebank.zplatform.manager.enums.ReviewEnum;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.utils.AmountUtil;

public class FinRealNameAuditAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	 private static final Log log = LogFactory.getLog(FinRealNameAuditAction.class);
	 
	private ServiceContainer serviceContainer;
	//实体
	private FinRealNameBean bean;
	
	HttpServletRequest request = ServletActionContext.getRequest();

	public String show(){
		return SUCCESS;
	}
	
	/****
	 * 列表查询
	 * @return
	 */
	public String query(){
		Map<String,Object> map =new HashMap<String,Object>();
		try {
			Map<String,Object> param=new HashMap<String,Object>();
			if(bean ==null){
				bean = new FinRealNameBean();
			} 
			if(StringUtils.isNotEmpty(bean.getMemberId())){
				param.put("memberId", bean.getMemberId().trim());
			}
			if(StringUtils.isNotEmpty(bean.getEnterpriseName())){
				param.put("enterpriseName", bean.getEnterpriseName().trim());
			}
			PageVo<EnterpriseRealnameMode> pageVo =serviceContainer.getEnterpriseRealnamService().findByPage(param, getPage(), getRows());
			map.put("total", pageVo.getTotal());
			map.put("rows", pageVo.getList());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			map.put("total", null);
			map.put("rows", null);
		}
		
		json_encode(map);
		return SUCCESS;
	}
	/**
	 * 通过id获取实名认证信息
	 * @return
	 */
	public String getById(){
		if(bean ==null){
			bean = new FinRealNameBean();
		}
		EnterpriseRealnameMode mode = serviceContainer.getEnterpriseRealnamService().get(bean.getTid());
		json_encode(mode);
		return null;
	}
	
	/***
	 * 审核
	 * @return
	 */
	public void audit(){
		if(bean ==null){
			bean = new FinRealNameBean();
		} 
		Map<String, Object> map = new HashMap<String, Object>();
		if(bean.getAuditFlag()==null){
			map.put("messg", "审核标志不能为空！");
            json_encode(map);
            return;
		}
		if(bean.getTid()==null){
			map.put("messg", "tid不能为空！");
            json_encode(map);
            return;
		}
		EnterpriseRealnameMode realNameBean = serviceContainer.getEnterpriseRealnamService().get(bean.getTid());
		realNameBean.setStexaTime(new Date());
		realNameBean.setStexaUser(getCurrentUser().getUserId());
		if(realNameBean==null){
			map.put("messg", "未找到审核数据！");
            json_encode(map);
            return;
		}
		//审核成功
		if(bean.getAuditFlag().equals("true")){
			if(bean.getAmount()==null){
				map.put("messg", "打款金额不能为空！");
	            json_encode(map);
	            return;
			}else{
				//状态待审核--》审核通过
				realNameBean.setStatus(ReviewEnum.SUCCESS.getCode());
				//打款金额
				realNameBean.setTxnamt(AmountUtil.toLongAmount(bean.getAmount()));
				serviceContainer.getEnterpriseRealnamService().updateApplyStatus(realNameBean);
			}
		//审核失败	
		}else if(bean.getAuditFlag().equals("false")){
			if(StringUtil.isEmpty(bean.getOpinion())){
				map.put("messg", "审核意见不能为空！");
	            json_encode(map);
	            return;
			}else{
				//状态待审核--》审核通过
				realNameBean.setStatus(ReviewEnum.FIRSTREFUSED.getCode());
				realNameBean.setStexaOpt(bean.getOpinion());
				serviceContainer.getEnterpriseRealnamService().updateApplyStatus(realNameBean);
			}
		//其他	
		}else{
			map.put("messg", "审核失败！");
            json_encode(map);
            return;
		}
		
	}

	public ServiceContainer getServiceContainer() {
		return serviceContainer;
	}

	public void setServiceContainer(ServiceContainer serviceContainer) {
		this.serviceContainer = serviceContainer;
	}

	public FinRealNameBean getBean() {
		return bean;
	}

	public void setBean(FinRealNameBean bean) {
		this.bean = bean;
	}
	
	
	

	

	
	
	
	
	
}
