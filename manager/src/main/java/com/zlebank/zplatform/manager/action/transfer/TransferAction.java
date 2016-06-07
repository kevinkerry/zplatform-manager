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
import com.zlebank.zplatform.trade.bean.enums.BankTransferBatchOpenStatusEnum;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.utils.DateUtil;

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
    public String showBank() {
        return "banktransfter";
    }
    /**
     * 
     * @return
     * @since 1.3.0
     */
    public String showTranBatchQuery(){
        return "queryTranBatch";
    }
    /**
     * 查询转账批次数据
     * 
     * @return
     * @since 1.3.0
     */
    public void queryBatch() {
        Map<String, Object> map = transferService.queryBatchTransfer(
                queryTransferBean, getPage(), getPage_size());
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
			if(queryTransferBean.getTid()!=0){
				Map<String, Object> map = transferService.queryDetaTransfer(queryTransferBean,getPage(),getPage_size());
				json_encode(map);
			}
		}
	}
	
	/**


    /**

     * 批量审核
     * 
     * @return
     * @since 1.3.0
     */
	public void batchTrail(){
		String batchNos = "";
		String[] batchId_array = auditBean.getBatchno().split("\\|");
		for(String batchno:batchId_array){
			boolean flag = transferService.transferBatchTrial(Long.parseLong(batchno.trim()), auditBean.getFalg(),getCurrentUser().getUserId());
			if(!flag){
				batchNos+=batchno+",";
			}
		}
		if(StringUtil.isEmpty(batchNos)){
			if(auditBean.getFalg()){
				json_encode("审核成功");
			}else{
				json_encode("操作成功");
			}
			
		}else{
			json_encode("批次号："+batchNos+"转账失败");
		}
	}
	


    /**
     * 划拨单笔审核
     * 
     * @return
     * @since 1.3.0
     */
	public void trailTransferDeta(){
		String batchNos = "";
		String[] batchno_array = auditBean.getOrderNo().split("\\|");
		for(String orderNo:batchno_array){
			boolean flag = transferService.transferDataTrial(Long.valueOf(orderNo), auditBean.getFalg(),getCurrentUser().getUserId());
			if(!flag){
				batchNos+=orderNo+",";
			}
		}
		if(StringUtil.isEmpty(batchNos)){
			if(auditBean.getFalg()){
				json_encode("审核成功");
			}else{
				json_encode("操作成功");
			}
			
		}else{
			json_encode("批次号："+batchNos+"转账失败");
		}
	}
	
    /**
     * 查询转账批次数据
     * 
     * @return
     * @since 1.3.0
     */
    public void queryBankBatch() {
        Map<String, Object> map = bankTransferService.queryBatchBankTransfer(
                queryTransferBean, getPage(), getPage_size());
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
			if(queryTransferBean.getTid()!=0){
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
		//转账审核时间判断 不得超过当日175500
		String endTime = DateUtil.getCurrentDate()+"235500";
		if(Long.valueOf(DateUtil.getCurrentDateTime())>Long.valueOf(endTime)){
			json_encode("代付业务截止时间已到，请明天再进行转账审核");
		}else{
			String batchNos = "";
			String[] batchno_array = auditBean.getBatchno().split("\\|");
			for(String batchno:batchno_array){
				//判断批次状态是否为关闭状态
				Map<String, Object> resultMap = bankTransferService.bankTransferBatchTrial(batchno.trim(), auditBean.getFalg(),getCurrentUser().getUserId());
				if("09".equals(resultMap.get("retcode")+"")){
					batchNos+=resultMap.get("retinfo")+";";
				}
			}
			if(StringUtil.isEmpty(batchNos)){
				if(auditBean.getFalg()){
					json_encode("转账成功");
				}else{
					json_encode("操作成功");
				}
				
			}else{
				json_encode(batchNos);
			}
		}
		
	}
	
	/**
	 * 转账的关闭事件为关闭
	 */
	public void closeBankBatch(){
		String batchNos = "";
		String[] batchno_array = auditBean.getBatchno().split("\\|");
		for(String batchno:batchno_array){
			boolean flag = bankTransferService.colseBankTransferBatch(Long.valueOf(batchno));
			if(!flag){
				batchNos+=batchno+",";
			}
		}
		if(StringUtil.isEmpty(batchNos)){
			json_encode("操作成功");
		}else{
			json_encode("批次号："+batchNos+"转账失败");
		}
	}

    


    /**
     * 根据划拨批次查询转账批次
     * 
     * @return
     * @since 1.3.0
     */
    public String queryBankTranBatchByTranBatch() {
        if (queryTransferBean == null) {
            return null;
        }
        List<BankTranBatch> bankTranBatchs = transferService
                .queryBankTranBatchByTranBatch(queryTransferBean.getTid(),
                        BankTransferBatchOpenStatusEnum.fromValue(bankTranBatch.getOpenStatus()));
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
    public BankTranBatch getBankTranBatch() {
        return bankTranBatch;
    }
    public void setBankTranBatch(BankTranBatch bankTranBatch) {
        this.bankTranBatch = bankTranBatch;
    }
  
}
