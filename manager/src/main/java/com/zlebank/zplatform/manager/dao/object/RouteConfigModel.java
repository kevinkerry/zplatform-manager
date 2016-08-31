package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_ROUTE_CONFIG")
public class RouteConfigModel implements java.io.Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6264893200288601245L;
    /**标识**/
    private Long rid;
    /**最小金额**/
    private Long minamt;
    /**最大金额**/
    private Long maxamt;
    /**收银代码**/
    private String cashcode;
    /**开始时间**/
    private String stime;
    /**结束时间**/
    private String etime;
    /**交易卡所属银行（发卡行）(用;分隔)**/
    private String bankcode;
    /**交易类型(用;分隔)**/
    private String busicode;
    /**卡类型1-借记卡 2-信用卡**/
    private String cardtype;
    /**路由-渠道代码**/
    private String routver;
    /**状态 00在用 其他停用**/
    private String status;
    /**写入时间**/
    private Date intime;
    /**写入人**/
    private Long inuser;
    /**更新时间**/
    private Date uptime;
    /**更新人**/
    private Long upuser;
    /**优先类型(第一次分类)**/
    private Long ordertype;
    /**优先级(第二次分类)**/
    private Long orders;
    /**是否默认    0是默认路由 1是非默认路由**/
    private String isdef;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**商户路由版本**/
    private String merchroutver;


    public RouteConfigModel() {
        super();
    }
 
    
    
    public RouteConfigModel(Long rid, Long minamt, Long maxamt,
            String cashcode, String stime, String etime, String bankcode,
            String busicode, String cardtype, String routver, String status,
            Date intime, Long inuser, Date uptime, Long upuser, Long ordertype,
            Long orders, String isdef, String notes, String remarks,
            String merchroutver) {
        super();
        this.rid = rid;
        this.minamt = minamt;
        this.maxamt = maxamt;
        this.cashcode = cashcode;
        this.stime = stime;
        this.etime = etime;
        this.bankcode = bankcode;
        this.busicode = busicode;
        this.cardtype = cardtype;
        this.routver = routver;
        this.status = status;
        this.intime = intime;
        this.inuser = inuser;
        this.uptime = uptime;
        this.upuser = upuser;
        this.ordertype = ordertype;
        this.orders = orders;
        this.isdef = isdef;
        this.notes = notes;
        this.remarks = remarks;
        this.merchroutver = merchroutver;
    }



    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getMinamt() {
        return minamt;
    }

    public void setMinamt(Long minamt) {
        this.minamt = minamt;
    }

    public Long getMaxamt() {
        return maxamt;
    }

    public void setMaxamt(Long maxamt) {
        this.maxamt = maxamt;
    }

    public String getCashcode() {
        return cashcode;
    }

    public void setCashcode(String cashcode) {
        this.cashcode = cashcode;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBusicode() {
        return busicode;
    }

    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getRoutver() {
        return routver;
    }

    public void setRoutver(String routver) {
        this.routver = routver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Long getInuser() {
        return inuser;
    }

    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public Long getUpuser() {
        return upuser;
    }

    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }

    public Long getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Long ordertype) {
        this.ordertype = ordertype;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public String getIsdef() {
        return isdef;
    }

    public void setIsdef(String isdef) {
        this.isdef = isdef;
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

    public String getMerchroutver() {
        return merchroutver;
    }

    public void setMerchroutver(String merchroutver) {
        this.merchroutver = merchroutver;
    }
    
    
    
    
}
