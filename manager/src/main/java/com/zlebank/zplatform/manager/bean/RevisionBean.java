/* 
 * RevisionBean.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
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
 * @date 2015年12月30日 下午2:41:17
 * @since
 */
public class RevisionBean implements Bean {
    /** ID **/
    private Long id;
    /** 调账订单号 **/
    private String revisionno;
    /** 关联订单 **/
    private String txnslogid;
    /** 业务代码 **/
    private String busicode;
    /** 业务类型 **/
    private String busitype;
    /** 状态 **/
    private String status;
    /** 写入人 **/
    private String inuser;
    /** 写入时间 **/
    private String intime;
    /** 初审人 **/
    private String stexauser;
    /** 初审时间 **/
    private String stexatime;
    /** 初审意见 **/
    private String stexaopt;
    /** 复审人 **/
    private String cvlexauser;
    /** 复审时间 **/
    private String cvlexatime;
    /** 复审意见 **/
    private String cvlexaopt;
    /** 科目ID **/
    private String accCode;
    /** 备注 **/
    private String notes;
    /** 备注 **/
    private String remarks;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRevisionno() {
        return revisionno;
    }
    public void setRevisionno(String revisionno) {
        this.revisionno = revisionno;
    }
    public String getTxnslogid() {
        return txnslogid;
    }
    public void setTxnslogid(String txnslogid) {
        this.txnslogid = txnslogid;
    }
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    public String getBusitype() {
        return busitype;
    }
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getInuser() {
        return inuser;
    }
    public void setInuser(String inuser) {
        this.inuser = inuser;
    }
    public String getIntime() {
        return intime;
    }
    public void setIntime(String intime) {
        this.intime = intime;
    }
    public String getStexauser() {
        return stexauser;
    }
    public void setStexauser(String stexauser) {
        this.stexauser = stexauser;
    }
    public String getStexatime() {
        return stexatime;
    }
    public void setStexatime(String stexatime) {
        this.stexatime = stexatime;
    }
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    public String getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(String cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    public String getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(String cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
    }
    public String getAccCode() {
        return accCode;
    }
    public void setAccCode(String accCode) {
        this.accCode = accCode;
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

}
