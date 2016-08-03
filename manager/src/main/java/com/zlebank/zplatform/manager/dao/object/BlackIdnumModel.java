package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_BLACKLIST_IDNUM")
public class BlackIdnumModel implements java.io.Serializable{

    /**标识**/
    private String tid;
    /**持卡人身份证号**/
    private String idnum;
    /**状态**/
    private String status;
    /**备注1**/
    private String notes;
    /**备注2**/
    private String remarks;
    /**风险级别**/
    private String risklevel;
    /**起始日期**/
    private String sdate;
    /**截止日期**/
    private String edate;
    
    public BlackIdnumModel() {
        super();
    }
    public BlackIdnumModel(String tid, String idnum, String status,
            String notes, String remarks, String risklevel, String sdate,
            String edate) {
        super();
        this.tid = tid;
        this.idnum = idnum;
        this.status = status;
        this.notes = notes;
        this.remarks = remarks;
        this.risklevel = risklevel;
        this.sdate = sdate;
        this.edate = edate;
    }
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getIdnum() {
        return idnum;
    }
    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getRisklevel() {
        return risklevel;
    }
    public void setRisklevel(String risklevel) {
        this.risklevel = risklevel;
    }
    public String getSdate() {
        return sdate;
    }
    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
    public String getEdate() {
        return edate;
    }
    public void setEdate(String edate) {
        this.edate = edate;
    }
    
    
    
}
