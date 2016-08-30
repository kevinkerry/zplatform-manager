package com.zlebank.zplatform.manager.dao.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * TStepRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_STEP_RATE", uniqueConstraints = @UniqueConstraint(columnNames = {
        "FEEVER", "BUSICODE"}))
public class StepRateModel implements java.io.Serializable {

    // Fields

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2950010758485850483L;
    private Long tid;
    private String feever;
    private String busicode;
    private String rateType;
    private Long servicefee;
    
    
    
    private BigDecimal feeRate;
    private BigDecimal minFee;
    private BigDecimal maxFee;
    private BigDecimal limit1;
    private BigDecimal feeRate2;
    private BigDecimal minFee2;
    private BigDecimal maxFee2;
    private BigDecimal limit2;
    private BigDecimal feeRate3;
    private BigDecimal minFee3;
    private BigDecimal maxFee3;
    private Long inuser;
    private Date intime;
    private String notes;
    private String remarks;

    private String minFeeStr;
    private String maxFeeStr;
    private String limit1Str;
    private String minFee2Str;
    private String maxFee2Str;
    private String limit2Str;
    private String minFee3Str;
    private String maxFee3Str;
    private String feeRateStr;
    private String feeRate2Str;
    private String feeRate3Str;

    // Constructors

    /** default constructor */
    public StepRateModel() {
    }

    /** minimal constructor */
    public StepRateModel(Long tid, String feever, String busicode,
            String rateType) {
        this.tid = tid;
        this.feever = feever;
        this.busicode = busicode;
        this.rateType = rateType;
    }

    /** full constructor */
    public StepRateModel(Long tid, String feever, String busicode,
            String rateType, Long servicefee, BigDecimal feeRate,
            BigDecimal minFee, BigDecimal maxFee, BigDecimal limit1,
            BigDecimal feeRate2, BigDecimal minFee2, BigDecimal maxFee2,
            BigDecimal limit2, BigDecimal feeRate3, BigDecimal minFee3,
            BigDecimal maxFee3, Long inuser, Date intime, String notes,
            String remarks) {
        this.tid = tid;
        this.feever = feever;
        this.busicode = busicode;
        this.rateType = rateType;
        this.servicefee = servicefee;
        this.feeRate = feeRate;
        this.minFee = minFee;
        this.maxFee = maxFee;
        this.limit1 = limit1;
        this.feeRate2 = feeRate2;
        this.minFee2 = minFee2;
        this.maxFee2 = maxFee2;
        this.limit2 = limit2;
        this.feeRate3 = feeRate3;
        this.minFee3 = minFee3;
        this.maxFee3 = maxFee3;
        this.inuser = inuser;
        this.intime = intime;
        this.notes = notes;
        this.remarks = remarks;
    }

    // Property accessors
    @Id
    @Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Column(name = "FEEVER", nullable = false, length = 8)
    public String getFeever() {
        return this.feever;
    }

    public void setFeever(String feever) {
        this.feever = feever;
    }

    @Column(name = "BUSICODE", nullable = false, length = 4)
    public String getBusicode() {
        return this.busicode;
    }

    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }

    @Column(name = "RATE_TYPE", nullable = false, length = 2)
    public String getRateType() {
        return this.rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    @Column(name = "SERVICEFEE", precision = 12, scale = 0)
    public Long getServicefee() {
        return this.servicefee;
    }

    public void setServicefee(Long servicefee) {
        this.servicefee = servicefee;
    }

    @Column(name = "FEE_RATE", precision = 4, scale = 0)
    public BigDecimal getFeeRate() {
        return this.feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    @Column(name = "MIN_FEE", precision = 12, scale = 0)
    public BigDecimal getMinFee() {
        return this.minFee;
    }

    public void setMinFee(BigDecimal minFee) {
        this.minFee = minFee;
    }

    @Column(name = "MAX_FEE", precision = 12, scale = 0)
    public BigDecimal getMaxFee() {
        return this.maxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }

    @Column(name = "LIMIT1", precision = 12, scale = 0)
    public BigDecimal getLimit1() {
        return this.limit1;
    }

    public void setLimit1(BigDecimal limit1) {
        this.limit1 = limit1;
    }

    @Column(name = "FEE_RATE2", precision = 4, scale = 0)
    public BigDecimal getFeeRate2() {
        return this.feeRate2;
    }

    public void setFeeRate2(BigDecimal feeRate2) {
        this.feeRate2 = feeRate2;
    }

    @Column(name = "MIN_FEE2", precision = 12, scale = 0)
    public BigDecimal getMinFee2() {
        return this.minFee2;
    }

    public void setMinFee2(BigDecimal minFee2) {
        this.minFee2 = minFee2;
    }

    @Column(name = "MAX_FEE2", precision = 12, scale = 0)
    public BigDecimal getMaxFee2() {
        return this.maxFee2;
    }

    public void setMaxFee2(BigDecimal maxFee2) {
        this.maxFee2 = maxFee2;
    }

    @Column(name = "LIMIT2", precision = 12, scale = 0)
    public BigDecimal getLimit2() {
        return this.limit2;
    }

    public void setLimit2(BigDecimal limit2) {
        this.limit2 = limit2;
    }

    @Column(name = "FEE_RATE3", precision = 4, scale = 0)
    public BigDecimal getFeeRate3() {
        return this.feeRate3;
    }

    public void setFeeRate3(BigDecimal feeRate3) {
        this.feeRate3 = feeRate3;
    }

    @Column(name = "MIN_FEE3", precision = 12, scale = 0)
    public BigDecimal getMinFee3() {
        return this.minFee3;
    }

    public void setMinFee3(BigDecimal minFee3) {
        this.minFee3 = minFee3;
    }

    @Column(name = "MAX_FEE3", precision = 12, scale = 0)
    public BigDecimal getMaxFee3() {
        return this.maxFee3;
    }

    public void setMaxFee3(BigDecimal maxFee3) {
        this.maxFee3 = maxFee3;
    }

    @Column(name = "INUSER", precision = 10, scale = 0)
    public Long getInuser() {
        return this.inuser;
    }

    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "INTIME", length = 7)
    public Date getIntime() {
        return this.intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    @Column(name = "NOTES", length = 128)
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "REMARKS", length = 128)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Transient
    public String getMinFeeStr() {
        return minFeeStr;
    }

    public void setMinFeeStr(String minFeeStr) {
        this.minFeeStr = minFeeStr;
    }
    @Transient
    public String getMaxFeeStr() {
        return maxFeeStr;
    }

    public void setMaxFeeStr(String maxFeeStr) {
        this.maxFeeStr = maxFeeStr;
    }
    @Transient
    public String getLimit1Str() {
        return limit1Str;
    }

    public void setLimit1Str(String limit1Str) {
        this.limit1Str = limit1Str;
    }
    @Transient
    public String getMinFee2Str() {
        return minFee2Str;
    }

    public void setMinFee2Str(String minFee2Str) {
        this.minFee2Str = minFee2Str;
    }
    @Transient
    public String getMaxFee2Str() {
        return maxFee2Str;
    }

    public void setMaxFee2Str(String maxFee2Str) {
        this.maxFee2Str = maxFee2Str;
    }
    @Transient
    public String getLimit2Str() {
        return limit2Str;
    }

    public void setLimit2Str(String limit2Str) {
        this.limit2Str = limit2Str;
    }
    @Transient
    public String getMinFee3Str() {
        return minFee3Str;
    }

    public void setMinFee3Str(String minFee3Str) {
        this.minFee3Str = minFee3Str;
    }
    @Transient
    public String getMaxFee3Str() {
        return maxFee3Str;
    }

    public void setMaxFee3Str(String maxFee3Str) {
        this.maxFee3Str = maxFee3Str;
    }

    @Transient
    public String getFeeRateStr() {
        return feeRateStr;
    }

    public void setFeeRateStr(String feeRateStr) {
        this.feeRateStr = feeRateStr;
    }

    @Transient
    public String getFeeRate2Str() {
        return feeRate2Str;
    }

    public void setFeeRate2Str(String feeRate2Str) {
        this.feeRate2Str = feeRate2Str;
    }
    @Transient
    public String getFeeRate3Str() {
        return feeRate3Str;
    }

    public void setFeeRate3Str(String feeRate3Str) {
        this.feeRate3Str = feeRate3Str;
    }

}