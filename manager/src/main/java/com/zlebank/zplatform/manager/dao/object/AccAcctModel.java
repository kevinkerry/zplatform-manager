/* 
 * PojoAbstractSubject.java  
 * 
 * version TODO
 *
 * 2015年8月24日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.zlebank.zplatform.acc.bean.enums.AcctElementType;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.bean.enums.AcctType;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.commons.dao.pojo.Pojo;

/**
 * Class Description
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月24日 下午2:19:48
 * @since
 */
@Entity
@Table(name = "T_ACC_ACCT")
public  class AccAcctModel implements Serializable {

  

    private AcctType acctType;

    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.AcctSqlType")
    @Column(name = "ACCT_TYPE", insertable = false, updatable = false)
    public AcctType getAcctType() {
        return acctType;
    }

    public void setAcctType(AcctType acctType) {
        this.acctType = acctType;
    }

    private String acctCode;

    private String acctCodeName;

    private CRDRType crdr;

    private AcctElementType acctElement;

    private String businessActorId;
    




    private AcctStatusType status;

    private Long ver;

    private String dac;

    private Date inTime;

    private Long inUser;

    private Date upTime;

    private Long upUser;

    private String notes;

    private String remarks;

    @Column(name = "ACCT_CODE")
    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    @Column(name = "ACCT_CODE_NAME")
    public String getAcctCodeName() {
        return acctCodeName;
    }

    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }

    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.CRDRSqlType")
    @Column(name = "CRDR")
    public CRDRType getCrdr() {
        return crdr;
    }

    public void setCrdr(CRDRType crdr) {
        this.crdr = crdr;
    }

    @Type(type = "com.zlebank.zplatform.acc.pojo.usertype.AcctElementSqlType")
    @Column(name = "ACCT_ELEMENT")
    public AcctElementType getAcctElement() {
        return acctElement;
    }

    public void setAcctElement(AcctElementType acctElement) {
        this.acctElement = acctElement;
    }

    @Column(name = "BUSINESS_ACTOR_ID")
    public String getBusinessActorId() {
        return businessActorId;
    }

    public void setBusinessActorId(String businessActorId) {
        this.businessActorId = businessActorId;
    }
   


    public void setStatus(AcctStatusType status) {
        this.status = status;
    }

    @Column(name = "VER")
    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

    @Column(name = "DAC")
    public String getDac() {
        return dac;
    }

    public void setDac(String dac) {
        this.dac = dac;
    }

    @Column(name = "INTIME")
    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    @Column(name = "INUSER")
    public Long getInUser() {
        return inUser;
    }

    public void setInUser(Long inUser) {
        this.inUser = inUser;
    }

    @Column(name = "UPTIME")
    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    @Column(name = "UPUSER")
    public Long getUpUser() {
        return upUser;
    }

    public void setUpUser(Long upUser) {
        this.upUser = upUser;
    }

    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
