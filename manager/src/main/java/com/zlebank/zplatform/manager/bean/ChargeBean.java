/* 
 * ChargeQuery.java  
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
 * @date 2015年12月18日 上午11:43:58
 * @since 
 */
public class ChargeBean implements Bean {

    
    /**ID**/
    private Long id;
    /**充值订单号**/
    private String chargeno;
    /**会员号**/
    private String memberid;
    /**会员姓名**/
    private String memberName;
    /**充值类型1个人2商户**/
    private String chargetype;
    /**金额**/
    private String amount;
    /**状态**/
    private String status;
    /**手工充值渠道**/
    private String chargenoinstid;
    /**写入人**/
    private Long inuser;
    /**写入时间**/
    private String intime;
    /**初审人**/
    private Long stexauser;
    /**初审时间**/
    private String stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复审人**/
    private Long cvlexauser;
    /**复审时间**/
    private String cvlexatime;
    /**复审意见**/
    private String cvlexaopt;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    
    private String usage;
    
    private String chargecode;
    
    
    
    public String getChargecode() {
		return chargecode;
	}
	public void setChargecode(String chargecode) {
		this.chargecode = chargecode;
	}
	public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getChargeno() {
        return chargeno;
    }
    public void setChargeno(String chargeno) {
        this.chargeno = chargeno;
    }
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    public String getChargetype() {
        return chargetype;
    }
    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getChargenoinstid() {
        return chargenoinstid;
    }
    public void setChargenoinstid(String chargenoinstid) {
        this.chargenoinstid = chargenoinstid;
    }
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
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
    public Long getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getIntime() {
        return intime;
    }
    public void setIntime(String intime) {
        this.intime = intime;
    }
    public String getStexatime() {
        return stexatime;
    }
    public void setStexatime(String stexatime) {
        this.stexatime = stexatime;
    }
    public String getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(String cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    
    
    
    
}
