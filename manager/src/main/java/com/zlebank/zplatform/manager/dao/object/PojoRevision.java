/* 
 * PojoRevision.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.zlebank.zplatform.acc.pojo.PojoAccount;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月30日 下午2:19:45
 * @since 
 */
@Table(name="T_TXNS_REVISION")
@Entity
public class PojoRevision {
    /**ID**/
    private Long id;
    /**调账订单号**/
    private String revisionno;
    /**关联订单**/
    private PojoTxnsLog txnslog;
    /**业务代码**/
    private String busicode;
    /**业务类型**/
    private String busitype;
    /**状态**/
    private String status;
    /**写入人**/
    private UserModel inuser;
    /**写入时间**/
    private Date intime;
    /**初审人**/
    private UserModel stexauser;
    /**初审时间**/
    private Date stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复审人**/
    private UserModel cvlexauser;
    /**复审时间**/
    private Date cvlexatime;
    /**复审意见**/
    private String cvlexaopt;
    /**科目ID**/
    private PojoAccount acctid;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    
    @Id
    @GeneratedValue(generator = "id_gen")
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "T_TXNS_REVISION_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "REVISIONNO")
    public String getRevisionno() {
        return revisionno;
    }
    public void setRevisionno(String revisionno) {
        this.revisionno = revisionno;
    }
    @JoinColumn(name = "TXNSLOGID",nullable=true)
    @OneToOne(optional=false,fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
    public PojoTxnsLog getTxnslog() {
        return txnslog;
    }

    
    @Column(name = "BUSICODE")
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    @Column(name = "BUSITYPE")
    public String getBusitype() {
        return busitype;
    }
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @JoinColumn(name = "INUSER")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    public UserModel getInuser() {
        return inuser;
    }
    public void setInuser(UserModel inuser) {
        this.inuser = inuser;
    }
    @Generated (GenerationTime. INSERT)
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @JoinColumn(name = "STEXAUSER")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    public UserModel getStexauser() {
        return stexauser;
    }
    public void setStexauser(UserModel stexauser) {
        this.stexauser = stexauser;
    }
    @Generated (GenerationTime.INSERT)
    @Column(name = "STEXATIME")
    public Date getStexatime() {
        return stexatime;
    }
    public void setStexatime(Date stexatime) {
        this.stexatime = stexatime;
    }
    @Column(name = "STEXAOPT")
    public String getStexaopt() {
        return stexaopt;
    }
    public void setStexaopt(String stexaopt) {
        this.stexaopt = stexaopt;
    }
    @JoinColumn(name = "CVLEXAUSER")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    public UserModel getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(UserModel cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
    @Generated (GenerationTime. INSERT)
    @Column(name = "CVLEXATIME")
    public Date getCvlexatime() {
        return cvlexatime;
    }
    public void setCvlexatime(Date cvlexatime) {
        this.cvlexatime = cvlexatime;
    }
    @Column(name = "CVLEXAOPT")
    public String getCvlexaopt() {
        return cvlexaopt;
    }
    public void setCvlexaopt(String cvlexaopt) {
        this.cvlexaopt = cvlexaopt;
    }
    @JoinColumn(name = "ACCTID")
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
  
    public PojoAccount getAcctid() {
        return acctid;
    }
    public void setAcctid(PojoAccount acctid) {
        this.acctid = acctid;
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
    public void setTxnslog(PojoTxnsLog txnslog) {
        this.txnslog = txnslog;
    }

    
    

}
