package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="T_ACC_BUSIACCT")
@Entity
public class BusiacctModel implements Serializable{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6035969102868178008L;


    private int id;
    private String business_actor_id;
    private String acct_id;
    private String busiacct_code;
    private String usage;
    private String status;
    private String busiacct_name;
    private String note;
    private String remarks;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getBusiness_actor_id() {
        return business_actor_id;
    }
    public void setBusiness_actor_id(String business_actor_id) {
        this.business_actor_id = business_actor_id;
    }
    public String getAcct_id() {
        return acct_id;
    }
    public void setAcct_id(String acct_id) {
        this.acct_id = acct_id;
    }
    public String getBusiacct_code() {
        return busiacct_code;
    }
    public void setBusiacct_code(String busiacct_code) {
        this.busiacct_code = busiacct_code;
    }
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getBusiacct_name() {
        return busiacct_name;
    }
    public void setBusiacct_name(String busiacct_name) {
        this.busiacct_name = busiacct_name;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}
