package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="com.zlebank.zplatform.manager.dao.object.RouteConfigModel")
@Table(name="T_ROUTE_CONFIG")
public class RouteConfigModel implements java.io.Serializable{

    /**标识**/
    private int rid;
    /**最小金额**/
    private int minamt;
    /**最大金额**/
    private int maxamt;
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
    private int ordertype;
    /**优先级(第二次分类)**/
    private int orders;
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
    
    public RouteConfigModel(int rid, int minamt, int maxamt, String cashcode,
            String stime, String etime, String bankcode, String busicode,
            String cardtype, String routver, String status, Date intime,
            Long inuser, Date uptime, Long upuser, int ordertype, int orders,
            String isdef, String notes, String remarks, String merchroutver) {
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
    @Id
    @Column(name="RID",unique = true,nullable = false,length=10)
    public int getRid() {
        return rid;
    }
    public void setRid(int rid) {
        this.rid = rid;
    }
    @Column(name="MINAMT",nullable = false,length=12)
    public int getMinamt() {
        return minamt;
    }
    public void setMinamt(int minamt) {
        this.minamt = minamt;
    }
    @Column(name="MAXAMT",nullable = false,length=12)
    public int getMaxamt() {
        return maxamt;
    }
    public void setMaxamt(int maxamt) {
        this.maxamt = maxamt;
    }
    @Column(name="CASHCODE",length=8)
    public String getCashcode() {
        return cashcode;
    }
    public void setCashcode(String cashcode) {
        this.cashcode = cashcode;
    }
    @Column(name="STIME",length=6)
    public String getStime() {
        return stime;
    }
    public void setStime(String stime) {
        this.stime = stime;
    }
    @Column(name="ETIME",length=6)
    public String getEtime() {
        return etime;
    }
    public void setEtime(String etime) {
        this.etime = etime;
    }
    @Column(name="BANKCODE",length=256)
    public String getBankcode() {
        return bankcode;
    }
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }
    @Column(name="BUSICODE",length=256)
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    @Column(name="CARDTYPE",length=16)
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    } 
    @Column(name="ROUTVER",length=8)
    public String getRoutver() {
        return routver;
    }
    public void setRoutver(String routver) {
        this.routver = routver;
    }
    @Column(name="STATUS",length=2)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    @Column(name="INUSER",length=10)
    public Long getInuser() {
        return inuser;
    }
    public void setInuser(Long inuser) {
        this.inuser = inuser;
    }
    @Column(name="UPTIME")
    public Date getUptime() {
        return uptime;
    }
    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }
    @Column(name="UPUSER",length=10)
    public Long getUpuser() {
        return upuser;
    }
    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }
    @Column(name="ORDERTYPE",length=10)
    public int getOrdertype() {
        return ordertype;
    }
    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }
    @Column(name="ORDERS",length=10)
    public int getOrders() {
        return orders;
    }
    public void setOrders(int orders) {
        this.orders = orders;
    }
    @Column(name="ISDEF",length=1)
    public String getIsdef() {
        return isdef;
    }
    public void setIsdef(String isdef) {
        this.isdef = isdef;
    }
    @Column(name="NOTES",length=128)
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name="REMARKS",length=128)
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name="MERCHROUTVER",length=8)
    public String getMerchroutver() {
        return merchroutver;
    }
    public void setMerchroutver(String merchroutver) {
        this.merchroutver = merchroutver;
    }
    
    
}
