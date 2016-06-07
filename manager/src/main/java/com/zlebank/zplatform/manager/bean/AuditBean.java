/* 
 * FirstTrialBean.java  
 * 
 * version TODO
 *
 * 2015年12月3日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 *审核bean
 *
 * @author yangpeng
 * @version
 * @date 2015年12月3日 上午9:33:29
 * @since 
 */
public class AuditBean implements Bean{ 
    /**订单号**/
    private String orderNo;
    /**批次号**/
    private String batchno;
    /**审核人**/
    private Long stexauser;
    /**审核标示true通过false拒绝**/
    private Boolean falg;
    /**审核意见**/
    private String stexaopt;
    
  
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getBatchno() {
        return batchno;
    }
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
    public Long getStexauser() {
        return stexauser;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
 
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    public Boolean getFalg() {
        return falg;
    }
    public void setFalg(Boolean falg) {
        this.falg = falg;
    }
    
    
    
}
