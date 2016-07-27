package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

public class ChnlDetaBean implements Bean {

    private Long chnlid;
    private String chnlcode;
    private String chnlname;
    private String insticode;
    private String chnltype;
    private String status;
    private String url;
    private String bmk;
    private String mackey;
    private String pinkey;
    private String datakey;
    private String encode;
    private String notes;
    private String remarks;
    private String fronturl;
    private String backurl;
    private String safeurl;
    private String chnlmerchno;
    private String impl;
    private String refundImpl;
    
    
    
    
    public ChnlDetaBean() {
        super();
    }
    
    
    public ChnlDetaBean(Long chnlid, String chnlcode, String chnlname,
            String insticode, String chnltype, String status, String url,
            String bmk, String mackey, String pinkey, String datakey,
            String encode, String notes, String remarks, String fronturl,
            String backurl, String safeurl, String chnlmerchno, String impl,
            String refundImpl) {
        super();
        this.chnlid = chnlid;
        this.chnlcode = chnlcode;
        this.chnlname = chnlname;
        this.insticode = insticode;
        this.chnltype = chnltype;
        this.status = status;
        this.url = url;
        this.bmk = bmk;
        this.mackey = mackey;
        this.pinkey = pinkey;
        this.datakey = datakey;
        this.encode = encode;
        this.notes = notes;
        this.remarks = remarks;
        this.fronturl = fronturl;
        this.backurl = backurl;
        this.safeurl = safeurl;
        this.chnlmerchno = chnlmerchno;
        this.impl = impl;
        this.refundImpl = refundImpl;
    }


    public Long getChnlid() {
        return chnlid;
    }
    public void setChnlid(Long chnlid) {
        this.chnlid = chnlid;
    }
    public String getChnlcode() {
        return chnlcode;
    }
    public void setChnlcode(String chnlcode) {
        this.chnlcode = chnlcode;
    }
    public String getChnlname() {
        return chnlname;
    }
    public void setChnlname(String chnlname) {
        this.chnlname = chnlname;
    }
    public String getInsticode() {
        return insticode;
    }
    public void setInsticode(String insticode) {
        this.insticode = insticode;
    }
    public String getChnltype() {
        return chnltype;
    }
    public void setChnltype(String chnltype) {
        this.chnltype = chnltype;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getBmk() {
        return bmk;
    }
    public void setBmk(String bmk) {
        this.bmk = bmk;
    }
    public String getMackey() {
        return mackey;
    }
    public void setMackey(String mackey) {
        this.mackey = mackey;
    }
    public String getPinkey() {
        return pinkey;
    }
    public void setPinkey(String pinkey) {
        this.pinkey = pinkey;
    }
    public String getDatakey() {
        return datakey;
    }
    public void setDatakey(String datakey) {
        this.datakey = datakey;
    }
    public String getEncode() {
        return encode;
    }
    public void setEncode(String encode) {
        this.encode = encode;
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
    public String getFronturl() {
        return fronturl;
    }
    public void setFronturl(String fronturl) {
        this.fronturl = fronturl;
    }
    public String getBackurl() {
        return backurl;
    }
    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }
    public String getSafeurl() {
        return safeurl;
    }
    public void setSafeurl(String safeurl) {
        this.safeurl = safeurl;
    }
    public String getChnlmerchno() {
        return chnlmerchno;
    }
    public void setChnlmerchno(String chnlmerchno) {
        this.chnlmerchno = chnlmerchno;
    }
    public String getImpl() {
        return impl;
    }
    public void setImpl(String impl) {
        this.impl = impl;
    }
    public String getRefundImpl() {
        return refundImpl;
    }
    public void setRefundImpl(String refundImpl) {
        this.refundImpl = refundImpl;
    }
    
    
}
