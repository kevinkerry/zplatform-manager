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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.bean.TransferBatchQuery;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.TransferBatch;
import com.zlebank.zplatform.manager.enums.TransFerDataStatusEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.ITransferBatchService;
import com.zlebank.zplatform.manager.service.iface.ITransferService;

/**
 * 划拨
 *
 * @author yangpeng
 * @version
 * @date 2015年12月8日 下午4:39:49
 * @since
 */
public class TransferAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private String falg;

    @Autowired
    private ITransferService transfer;
    @Autowired
    private ITransferBatchService batchservice;

    private TransferDataQuery transQuery;

    private AuditBean ftb;

    private TransferBatchQuery tbq;
    
    private String batchno;

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public TransferBatchQuery getTbq() {
        return tbq;
    }

    public void setTbq(TransferBatchQuery tbq) {
        this.tbq = tbq;
    }

    public AuditBean getFtb() {
        return ftb;
    }

    public void setFtb(AuditBean ftb) {
        this.ftb = ftb;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public TransferDataQuery getTransQuery() {
        return transQuery;
    }

    public void setTransQuery(TransferDataQuery transQuery) {
        this.transQuery = transQuery;
    }

    public String getTrinsfer() {
        return this.SUCCESS;
    }

    /**
     * 通过条件查询划拨数据
     */
    public void queryTrinsfer() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtil.isNotEmpty(falg)) {
            if (transQuery == null) {
                transQuery = new TransferDataQuery();
            }
            if ("first".equals(falg)) {
                transQuery.setStatus(TransFerDataStatusEnum.FIRSTTRIAL
                        .getCode());
            } else if ("second".equals(falg)) {
                transQuery.setStatus(TransFerDataStatusEnum.SECONDTRIAL
                        .getCode());
            }

        }
        PagedResult<TransferData> pr = transfer.queryPaged(page, pageSize,
                transQuery);
        try {
            List<TransferData> li = pr.getPagedResult();
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 根据条件进行审核
     */
   public void secondAuditByConditions(){
       Long userId = getCurrentUser().getUserId();
       ftb.setStexauser(userId);
       String messg = null;
       if (transQuery == null) {
           transQuery = new TransferDataQuery();
       }
       if (StringUtil.isNotEmpty(falg)) {
           if (transQuery == null) {
               transQuery = new TransferDataQuery();
           }
           if ("first".equals(falg)) {
               transQuery.setStatus(TransFerDataStatusEnum.FIRSTTRIAL
                       .getCode());
           } else if ("second".equals(falg)) {
               transQuery.setStatus(TransFerDataStatusEnum.SECONDTRIAL
                       .getCode());
           }

       }
       try {
           transfer.secondAuditByConditions(ftb, transQuery,falg);
           messg = "操作成功";
       } catch (ManagerWithdrawException e) {
           messg = e.getMessage();
       } catch (AccBussinessException e) {
           messg = e.getMessage();
       } catch (AbstractBusiAcctException e) {
           messg = e.getMessage();
       } catch (NumberFormatException e) {
           messg = e.getMessage();
       }

       json_encode(messg);
       
   } 

    

    /**
     * 划拨初审
     */
    public void firstAudit() {

        Long userId = getCurrentUser().getUserId();
        ftb.setStexauser(userId);
        String messg = "";
        try {
            transfer.firstAudit(ftb,null);
            messg = "操作成功";
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();
        }

        json_encode(messg);

    }

    public String getFirstTrial() {
        return "first";
    }

    public String getSecondTrial() {
        return "second";

    }

    public void secondAudit() {
        Long userId = getCurrentUser().getUserId();
        ftb.setStexauser(userId);
        String messg = null;
        try {
            transfer.secondAudit(ftb,null);
            messg = "操作成功";
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();
        }

        json_encode(messg);
    }
    /**
     * 得到划拨页面
     * 
     * @return
     */
    public String getTransferBatch() {
        return "queryBatch";

    }
    
    
    /**
     * 划拨
     * 
     * @return
     */
    public String transferBatch() {
        return "batch";

    }
    /**
     * 根据条件查询划拨总数据
     */
    public void querytransfer() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(falg)) {
            if (tbq == null) {
                tbq = new TransferBatchQuery();
            }
            tbq.setStatus("01");

        }
        
        PagedResult<TransferBatch> tfr = batchservice.queryPaged(page,
                pageSize, tbq);
        try {
            List<TransferBatch> li = tfr.getPagedResult();
            for (TransferBatch trans : li) {
                trans.setSumMoney(Money.valueOf(
                        new BigDecimal(trans.getSumamount()==null?0:trans.getSumamount())).toYuan());

                trans.setSuccMoney(Money.valueOf(
                        new BigDecimal(trans.getSuccamount()==null?0:trans.getSuccamount())).toYuan());

                trans.setFailMoney(Money.valueOf(
                        new BigDecimal(trans.getFailamount()==null?0:trans.getFailamount())).toYuan());

            }
            Long total = tfr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /***
     * 划拨
     */
        public void transfer(){
            String messg = null;
            try {
                batchservice.TransferBatch(batchno);
                messg="操作成功";
            } catch (ManagerWithdrawException e) {
               messg="操作失败:"+e.getMessage();
            }   
            catch (Exception e){
                messg="操作失败:内部错误";
                
            }
            json_encode(messg);
        }
    
        
        
 
        
        
        
    
    
}
