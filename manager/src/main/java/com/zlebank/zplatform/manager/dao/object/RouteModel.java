package com.zlebank.zplatform.manager.dao.object;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ROUTE")
public class RouteModel implements java.io.Serializable{

    /**标识**/
    private String routid;
    /**路由号**/
    private String routver;
    /**路由名称**/
    private String routname;
    /**状态**/
    private int status;
    /**写入时间**/
    private Date intime;
    /**写入人**/
    private Long inuser;
    /**更新时间**/
    private Date uptime;
    /**更新人**/
    private Long upuser;
    /**备注**/
    private String note;
    /**操作**/
    private String remarks;
    
    private String routidStr;
    
    public RouteModel() {
        super();
    }

    public RouteModel(String routid, String routver, String routname,
            int status, Date intime, Long inuser, Date uptime,
            Long upuser, String note, String remarks) {
        super();
        this.routid = routid;
        this.routver = routver;
        this.routname = routname;
        this.status = status;
        this.intime = intime;
        this.inuser = inuser;
        this.uptime = uptime;
        this.upuser = upuser;
        this.note = note;
        this.remarks = remarks;
    }

    @Id
    @Column(name="ROUTID",unique = true, nullable = false,length=10)
    public String getRoutid() {
        return routid;
    }

    public void setRoutid(String routid) {
        this.routid = routid;
    }

    @Column(name="ROUTVER",nullable = false,length=10)
    public String getRoutver() {
        return routver;
    }

    public void setRoutver(String routver) {
        this.routver = routver;
    }

    @Column(name="ROUTNAME",nullable=false,length=64)
    public String getRoutname() {
        return routname;
    }

    public void setRoutname(String routname) {
        this.routname = routname;
    }

    @Column(name="STATUS",nullable=false,length=1)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name="INTIME")
    public Date getIntime() {
        return intime;
    }
    
    public void setIntime(Date intime) {
        this.intime = intime;
    }

    @Column(name="INUSER")
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

    @Column(name="UPUSER")
    public Long getUpuser() {
        return upuser;
    }

    public void setUpuser(Long upuser) {
        this.upuser = upuser;
    }

    @Column(name="NOTES")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name="REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRoutidStr() {
        return routidStr;
    }

    public void setRoutidStr(String routidStr) {
        this.routidStr = routidStr;
    }
    
    
    
    
}
