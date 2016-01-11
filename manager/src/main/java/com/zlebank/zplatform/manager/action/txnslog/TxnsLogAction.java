/* 
 * TxnsLogAction.java  
 * 
 * version 
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.txnslog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月17日 上午11:22:39
 * @since
 */
public class TxnsLogAction extends BaseAction {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String SIMPLE_TIME_FROMAT = "HH:mm:ss";
    public final static String SIMPLE_DATE_FROMAT = "yyyy-MM-dd";
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private ITxnsLoService txns;
    @Autowired
    public BusinessServiec business;

    private TxnsLogBean tlb;

    public List<Business> bus;

    public List<Business> getBus() {
        return bus;
    }

    public void setBus(List<Business> bus) {
        this.bus = bus;
    }
    
    

    public TxnsLogBean getTlb() {
        return tlb;
    }

    public void setTlb(TxnsLogBean tlb) {
        this.tlb = tlb;
    }

    /**
     * 请求页面
     * 
     * @return
     */
    public String getTxnsLog() {
        bus = business.getAllBusiness();
        return this.SUCCESS;
    }

    /**
     * 查询交易流水
     */
    public void getTxnsLoByTxnsBean(){
        int page=  this.getPage();
        int pageSize= this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<TxnsLog> pr  =  txns.queryPaged(page, pageSize, tlb);
        try {
            List<TxnsLog> li=pr.getPagedResult();
          
            Long total = pr.getTotal();
            
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        
    }
    
    public void getTxnsLogByTxnseqno(){
        String messg=null;    
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> li=txns.getTxnsLogById(tlb.getTxnseqno());
     
           if(li==null||li.isEmpty()){
                messg="交易数据不存在";
                map.put("messg", messg);
           }else {
             map.put("json",li.get(0));
           }
           json_encode( map);
       
    
    }
    
    
}
