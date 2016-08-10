package com.zlebank.zplatform.manager.dao.object;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Class Description
 *
 * @author jxr
 * @version
 * @date 2016年7月12日 下午4:13:16
 * @since
 */
@Entity
@Table(name="T_RISK_ANALYSE_LOG")
public class RiskAnalyseLogModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**标识**/
    private String tid;
    /**规则代码**/
    private String rolecode;
    /**参数**/
    private int parameter;
    /**规则描述**/
    private String rolename;
    /**商户号\卡号**/
    private String memberid;
    /**目标日期**/
    private String date;
    /**写入时间**/
    private String intime;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    
    public RiskAnalyseLogModel() {
        super();
    }

    public RiskAnalyseLogModel(String tid, String rolecode, int parameter,
            String rolename, String memberid, String date, String intime,
            String notes, String remarks) {
        super();
        this.tid = tid;
        this.rolecode = rolecode;
        this.parameter = parameter;
        this.rolename = rolename;
        this.memberid = memberid;
        this.date = date;
        this.intime = intime;
        this.notes = notes;
        this.remarks = remarks;
    }

    @Id
    @Column(name="TID",length=10)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Column(name="ROLECODE",length=16)
    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    @Column(name="PARAMETER",length=10)
    public int getParameter() {
        return parameter;
    }

    public void setParameter(int parameter) {
        this.parameter = parameter;
    }

    @Column(name="ROLENAME",length=64)
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Column(name="MEMBERID",length=32)
    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    @Column(name="DATES",length=19)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name="INTIME")
    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    @Column(name="NOTES",length=256)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name="REMARKS",length=256)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
    
    
}
