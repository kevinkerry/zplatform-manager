/* 
 * ChargeModel.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.member.pojo.PojoMember;

/**
 *充值pojo
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 上午10:03:37
 * @since 
 */

@Entity
@Table(name = "T_TXNS_CHARGE")
public class ChargeModel {
    /**ID**/
    private Long id;
    /**充值订单号**/
    private String chargeno;
    /**会员号**/
    private PojoMember memberid;
    /**充值类型01个人02商户**/
    private String chargetype;
    /**金额**/
    private Money amount;
    /**充值码*/
    private String chargecode; 
    /**状态**/
    private String status;
    /**手工充值渠道**/
    private String chargenoinstid;
    /**写入人**/
    private Long inuser;
    /**写入时间**/
    private Date intime;
    /**初审人**/
    private Long stexauser;
    /**初审时间**/
    private Date stexatime;
    /**初审意见**/
    private String stexaopt;
    /**复审人**/
    private Long cvlexauser;
    /**复审时间**/
    private Date cvlexatime;
    /**复审意见**/
    private String cvlexaopt;
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
            @Parameter(name = "segment_value", value = "T_TXNS_CHARGE_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "CHARGENO")
    public String getChargeno() {
        return chargeno;
    }
    public void setChargeno(String chargeno) {
        this.chargeno = chargeno;
    }
    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBERID",nullable=true)
    public PojoMember getMemberid() {
        return memberid;
    }
    public void setMemberid(PojoMember memberid) {
        this.memberid = memberid;
    }
    @Column(name = "CHARGETYPE")
    public String getChargetype() {
        return chargetype;
    }
    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }
    @Column(name = "AMOUNT")
    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="AMOUNT"))})
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "CHARGENOINSTID")
    public String getChargenoinstid() {
        return chargenoinstid;
    }
    public void setChargenoinstid(String chargenoinstid) {
        this.chargenoinstid = chargenoinstid;
    }
    @Column(name = "INUSER")
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name = "INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name = "STEXAUSER")
    public Long getStexauser() {
        return stexauser;
    }
    public void setStexauser(Long stexauser) {
        this.stexauser = stexauser;
    }
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
    @Column(name = "CVLEXAUSER")
    public Long getCvlexauser() {
        return cvlexauser;
    }
    public void setCvlexauser(Long cvlexauser) {
        this.cvlexauser = cvlexauser;
    }
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
	public String getChargecode() {
		return chargecode;
	}
	 @Column(name = "CHARGECODE")
	public void setChargecode(String chargecode) {
		this.chargecode = chargecode;
	}

    
    

}
