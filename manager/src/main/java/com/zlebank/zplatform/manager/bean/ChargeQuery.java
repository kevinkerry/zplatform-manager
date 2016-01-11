/* 
 * ChargeBean.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 上午10:46:55
 * @since 
 */
public class ChargeQuery implements Bean{
   /**会员ID**/
    private String memberId;
    /**充值类型**/
    private String chargeType;
    /**充值状态**/
    private String status;
    /**充值订单号**/
    private String chargeno;
    
    
    
    
    public String getChargeno() {
        return chargeno;
    }
    public void setChargeno(String chargeno) {
        this.chargeno = chargeno;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getChargeType() {
        return chargeType;
    }
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
