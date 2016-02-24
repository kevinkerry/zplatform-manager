/* 
 * QuickpayCustBean.java  
 * 
 * version TODO
 *
 * 2015年11月13日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 *绑定银行卡bean
 *
 * @author yangpeng
 * @version
 * @date 2015年11月13日 上午9:54:11
 * @since 
 */
public class QuickpayCustBean implements Bean {
    private Long id;
    private String bindcardid;
    private String institution;
    private String customerno;
    private String cardno;
    private String cardtype;
    private String accname;
    private String phone;
    private String idtype;
    private String idnum;
    private String cvv2;
    private String validtime;
    private String status;
    private String relatememberno;
    private String notes;
    private String remarks;
    private String bankcode;
    private String bankname;
    private String miniCardNo;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBindcardid() {
        return bindcardid;
    }
    public void setBindcardid(String bindcardid) {
        this.bindcardid = bindcardid;
    }
    public String getInstitution() {
        return institution;
    }
    public void setInstitution(String institution) {
        this.institution = institution;
    }
    public String getCustomerno() {
        return customerno;
    }
    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }
    public String getCardno() {
        return cardno;
    }
    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    public String getAccname() {
        return accname;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIdtype() {
        return idtype;
    }
    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }
    public String getIdnum() {
        return idnum;
    }
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public String getCvv2() {
        return cvv2;
    }
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public String getValidtime() {
        return validtime;
    }
    public void setValidtime(String validtime) {
        this.validtime = validtime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRelatememberno() {
        return relatememberno;
    }
    public void setRelatememberno(String relatememberno) {
        this.relatememberno = relatememberno;
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
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    public String getBankname() {
        return bankname;
    }
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
    public String getMiniCardNo() {
        return miniCardNo;
    }
    public void setMiniCardNo(String miniCardNo) {
        this.miniCardNo = miniCardNo;
    }
    
    
    
    
}
