package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;
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
 * 
 * Class Description
 *
 * @author jingxr
 * @version
 * @date 2016年8月19日 上午9:18:57
 * @since
 */
@Entity
@Table(name="T_ACCUMULATE_RATE",uniqueConstraints=@UniqueConstraint(columnNames = {
        "FEEVER", "BUSICODE"}))
public class AccumulateRateModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4070931087501675269L;
    /**标识**/
    private Long tid; 
    /**扣率版本**/    
    private String feever;
    /**业务代码**/
    private String busicode;
    /**扣率类型**/
    private String ratetype;
    /**备注**/
    private String notes;
    /**操作**/
    private String remarks;
    /**写入人**/
    private Long inuser;
    /**写入时间**/
    private Date intime;
    /**0-日 1-月 2-年**/
    private int accmode;
    /**固定费用**/
    private BigDecimal servicefee; 
    /**扣率（万分比）**/
    private BigDecimal feerate;
    /**最低收费额**/
    private BigDecimal minfee;
    /**最高收费额**/
    private BigDecimal maxfee;
    /**阶梯1**/
    private BigDecimal limit1;
    /****/
    private BigDecimal feerate2;
    /****/
    private BigDecimal minfee2;
    /****/
    private BigDecimal maxfee2;
    /**阶梯2**/
    private BigDecimal limit2;
    /****/
    private BigDecimal feerate3;
    /****/
    private BigDecimal minfee3;
    /****/
    private BigDecimal  maxfee3;
    
    private String servicefeeStr;
    private String feerateStr;
    private String minfeeStr;
    private String maxfeeStr;
    private String limit1Str;
    private String feerate2Str;
    private String minfee2Str;
    private String maxfee2Str;
    private String limit2Str;
    private String feerate3Str;
    private String minfee3Str;
    private String  maxfee3Str;
    
    
    
    public AccumulateRateModel() {
        super();
    }



    public AccumulateRateModel(String feever, String busicode, String ratetype,
            String notes, String remarks, Long inuser, Date intime, Long tid,
            BigDecimal servicefee, BigDecimal feerate, BigDecimal minfee,
            BigDecimal maxfee, BigDecimal limit1, BigDecimal feerate2,
            BigDecimal minfee2, BigDecimal maxfee2, BigDecimal limit2,
            BigDecimal feerate3, BigDecimal minfee3, BigDecimal maxfee3,int accmode) {
        super();
        this.feever = feever;
        this.busicode = busicode;
        this.ratetype = ratetype;
        this.notes = notes;
        this.remarks = remarks;
        this.inuser = inuser;
        this.intime = intime;
        this.tid = tid;
        this.servicefee = servicefee;
        this.feerate = feerate;
        this.minfee = minfee;
        this.maxfee = maxfee;
        this.limit1 = limit1;
        this.feerate2 = feerate2;
        this.minfee2 = minfee2;
        this.maxfee2 = maxfee2;
        this.limit2 = limit2;
        this.feerate3 = feerate3;
        this.minfee3 = minfee3;
        this.maxfee3 = maxfee3;
        this.accmode = accmode;
    }

    @Id
    @Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
    public Long getTid() {
        return tid;
    }



    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Column(name = "FEEVER", nullable = false, length = 8)
    public String getFeever() {
        return feever;
    }



    public void setFeever(String feever) {
        this.feever = feever;
    }


    @Column(name = "BUSICODE", nullable = false, length = 4)
    public String getBusicode() {
        return busicode;
    }



    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }


    @Column(name = "RATE_TYPE", nullable = false, length = 2)
    public String getRatetype() {
        return ratetype;
    }



    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }


    @Column(name = "NOTES", length = 128)
    public String getNotes() {
        return notes;
    }



    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Column(name = "REMARKS", length = 128)
    public String getRemarks() {
        return remarks;
    }



    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Column(name = "INUSER", precision = 10, scale = 0)
    public Long getInuser() {
        return inuser;
    }



    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "INTIME", length = 7)
    public Date getIntime() {
        return intime;
    }



    public void setIntime(Date intime) {
        this.intime = intime;
    }





    @Column(name = "SERVICEFEE", precision = 12, scale = 0)
    public BigDecimal getServicefee() {
        return servicefee;
    }



    public void setServicefee(BigDecimal servicefee) {
        this.servicefee = servicefee;
    }


    @Column(name = "FEE_RATE", precision = 4, scale = 0)
    public BigDecimal getFeerate() {
        return feerate;
    }



    public void setFeerate(BigDecimal feerate) {
        this.feerate = feerate;
    }


    @Column(name = "MIN_FEE", precision = 12, scale = 0)
    public BigDecimal getMinfee() {
        return minfee;
    }



    public void setMinfee(BigDecimal minfee) {
        this.minfee = minfee;
    }


    @Column(name = "MAX_FEE", precision = 12, scale = 0)
    public BigDecimal getMaxfee() {
        return maxfee;
    }



    public void setMaxfee(BigDecimal maxfee) {
        this.maxfee = maxfee;
    }


    @Column(name = "LIMIT1", precision = 12, scale = 0)
    public BigDecimal getLimit1() {
        return limit1;
    }



    public void setLimit1(BigDecimal limit1) {
        this.limit1 = limit1;
    }


    @Column(name = "FEE_RATE2", precision = 4, scale = 0)
    public BigDecimal getFeerate2() {
        return feerate2;
    }



    public void setFeerate2(BigDecimal feerate2) {
        this.feerate2 = feerate2;
    }


    @Column(name = "MIN_FEE2", precision = 12, scale = 0)
    public BigDecimal getMinfee2() {
        return minfee2;
    }



    public void setMinfee2(BigDecimal minfee2) {
        this.minfee2 = minfee2;
    }


    @Column(name = "MAX_FEE2", precision = 12, scale = 0)
    public BigDecimal getMaxfee2() {
        return maxfee2;
    }



    public void setMaxfee2(BigDecimal maxfee2) {
        this.maxfee2 = maxfee2;
    }


    @Column(name = "LIMIT2", precision = 12, scale = 0)
    public BigDecimal getLimit2() {
        return limit2;
    }



    public void setLimit2(BigDecimal limit2) {
        this.limit2 = limit2;
    }


    @Column(name = "FEE_RATE3", precision = 4, scale = 0)
    public BigDecimal getFeerate3() {
        return feerate3;
    }



    public void setFeerate3(BigDecimal feerate3) {
        this.feerate3 = feerate3;
    }


    @Column(name = "MIN_FEE3", precision = 12, scale = 0)
    public BigDecimal getMinfee3() {
        return minfee3;
    }



    public void setMinfee3(BigDecimal minfee3) {
        this.minfee3 = minfee3;
    }


    @Column(name = "MAX_FEE3", precision = 12, scale = 0)
    public BigDecimal getMaxfee3() {
        return maxfee3;
    }



    public void setMaxfee3(BigDecimal maxfee3) {
        this.maxfee3 = maxfee3;
    }


    @Transient
    public String getFeerateStr() {
        return feerateStr;
    }



    public void setFeerateStr(String feerateStr) {
        this.feerateStr = feerateStr;
    }


    @Transient
    public String getMinfeeStr() {
        return minfeeStr;
    }



    public void setMinfeeStr(String minfeeStr) {
        this.minfeeStr = minfeeStr;
    }


    @Transient
    public String getMaxfeeStr() {
        return maxfeeStr;
    }



    public void setMaxfeeStr(String maxfeeStr) {
        this.maxfeeStr = maxfeeStr;
    }


    @Transient
    public String getLimit1Str() {
        return limit1Str;
    }



    public void setLimit1Str(String limit1Str) {
        this.limit1Str = limit1Str;
    }


    @Transient
    public String getFeerate2Str() {
        return feerate2Str;
    }



    public void setFeerate2Str(String feerate2Str) {
        this.feerate2Str = feerate2Str;
    }


    @Transient
    public String getMinfee2Str() {
        return minfee2Str;
    }



    public void setMinfee2Str(String minfee2Str) {
        this.minfee2Str = minfee2Str;
    }


    @Transient
    public String getMaxfee2Str() {
        return maxfee2Str;
    }



    public void setMaxfee2Str(String maxfee2Str) {
        this.maxfee2Str = maxfee2Str;
    }


    @Transient
    public String getLimit2Str() {
        return limit2Str;
    }



    public void setLimit2Str(String limit2Str) {
        this.limit2Str = limit2Str;
    }


    @Transient
    public String getFeerate3Str() {
        return feerate3Str;
    }



    public void setFeerate3Str(String feerate3Str) {
        this.feerate3Str = feerate3Str;
    }


    @Transient
    public String getMinfee3Str() {
        return minfee3Str;
    }



    public void setMinfee3Str(String minfee3Str) {
        this.minfee3Str = minfee3Str;
    }


    @Transient
    public String getMaxfee3Str() {
        return maxfee3Str;
    }



    public void setMaxfee3Str(String maxfee3Str) {
        this.maxfee3Str = maxfee3Str;
    }


    @Transient
    public String getServicefeeStr() {
        return servicefeeStr;
    }



    public void setServicefeeStr(String servicefeeStr) {
        this.servicefeeStr = servicefeeStr;
    }



    @Column(name="ACCMODE",length=1)
    public int getAccmode() {
        return accmode;
    }

    public void setAccmode(int accmode) {
        this.accmode = accmode;
    }





 
    
    
    
    
  
     
    

}
