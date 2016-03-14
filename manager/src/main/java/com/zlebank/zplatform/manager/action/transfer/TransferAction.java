/* 
 * TransFerAction.java  
 * 
 * version TODO
 *
 * 2015年12月8日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.transfer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.manager.bean.BankTranBatch;
import com.zlebank.zplatform.manager.service.iface.IBankTransferService;
import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;

/**
 * 划拨 action
 *
 * @author guojai
 * @author yangying
 * @version 1.3.0
 * @date 2015年12月8日 下午4:39:49
 * @since 1.1.0
 */
public class TransferAction extends BaseAction {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TransferBatchDAO transferBatchDAO;
	@Autowired
	private TransferDataDAO transferDataDAO;
	@Autowired
	private ITransferService transferService;
	@Autowired
	private IBankTransferService bankTransferService;
	
	private QueryTransferBean queryTransferBean;
	private AuditBean auditBean;
	private BankTranBatch bankTranBatch;
	/**
     * 
     * @return
     * @since 1.3.0
     */
	public String showTrial() {
		return "audit";
	}
	/**
     * 
     * @return
     * @since 1.3.0
     */
	public String showBank(){
		return "banktransfter";
	}
	
	/**
     * 查询转账批次数据
     * 
     * @return
     * @since 1.3.0
     */
	public void queryBatch(){
		Map<String, Object> map = transferService.queryBatchTransfer(queryTransferBean,getPage(),getPage_size());
		json_encode(map);
	}
	
	/**
     * 查询划拨明细数据
     * 
     * @return
     * @since 1.3.0
     */
	public void queryDetail(){
		if(queryTransferBean!=null){
			if(StringUtil.isNotEmpty(queryTransferBean.getBatchNo())){
				Map<String, Object> map = transferService.queryDetaTransfer(queryTransferBean,getPage(),getPage_size());
				json_encode(map);
			}
		}
		
	}
	
	/**
     * 批量审核
     * 
     * @return
     * @since 1.3.0
     */
	public void batchTrail(){
		String[] batchId_array = auditBean.getBatchno().split("\\|");
		for(String batchno:batchId_array){
			transferService.transferBatchTrial(Long.parseLong(batchno.trim()), auditBean.getFalg());
		}
		
	}
	
	/**
     * 划拨单笔审核
     * 
     * @return
     * @since 1.3.0
     */
	public void trailTransferDeta(){
		String[] batchno_array = auditBean.getOrderNo().split("\\|");
		for(String orderNo:batchno_array){
			transferService.transferDataTrial(Long.valueOf(orderNo), auditBean.getFalg());
		}
	}
	
	/**
     * 查询转账批次数据
     * 
     * @return
     * @since 1.3.0
     */
	public void queryBankBatch(){
		Map<String, Object> map = bankTransferService.queryBatchBankTransfer(queryTransferBean,getPage(),getPage_size());
		json_encode(map);
	}
	
	/**
     * 查询转账明细数据
     * 
     * @return
     * @since 1.3.0
     */
	public void queryBankData(){
		if(queryTransferBean!=null){
			if(StringUtil.isNotEmpty(queryTransferBean.getBatchNo())){
				Map<String, Object> map = bankTransferService.queryDataBankTransfer(queryTransferBean,getPage(),getPage_size());
				json_encode(map);
			}
		}
	}
	
	 /**
     * 批量审核转账数据
     * 
     * @return
     * @since 1.3.0
     */
	public void batchBankTrial(){
		String[] batchno_array = auditBean.getBatchno().split("\\|");
		for(String batchno:batchno_array){
			bankTransferService.bankTransferBatchTrial(batchno.trim(), auditBean.getFalg());
		}
	}
	
	/**
    * 根据划拨批次查询转账批次
    * 
    * @return
    * @since 1.3.0
    */
   public String queryBankTranBatchByTranBatch() {
       if(queryTransferBean==null){
           return null;
       }
       List<BankTranBatch> bankTranBatchs= transferService.queryBankTranBatchByTranBatch(queryTransferBean.getTid(),bankTranBatch.getOpenStatus());
        
       try {
           json_encode(bankTranBatchs);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

	public QueryTransferBean getQueryTransferBean() {
		return queryTransferBean;
	}

	public void setQueryTransferBean(QueryTransferBean queryTransferBean) {
		this.queryTransferBean = queryTransferBean;
	}

	public AuditBean getAuditBean() {
		return auditBean;
	}

	public void setAuditBean(AuditBean auditBean) {
		this.auditBean = auditBean;
	}

	
	
}
