package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CMBC_RESFILE_LOG")
public class CmbcResfileModel implements java.io.Serializable{

    private String tid;
    private String banktrandataseqno;
    private String banktranresno;
    private String accno;
    private String accname;
    private String tranamt;
    private String restype;
    private String rescode;
    private String resinfo;
    private String paydate;
    private String paydatetime;
    private String banktranbatchno;
    
    /**
     * 构造方法
     */
    public CmbcResfileModel() {
        super();
    }
    
    public CmbcResfileModel(String tid, String banktrandataseqno,
            String banktranresno, String accno, String accname, String tranamt,
            String restype, String rescode, String resinfo, String paydate,
            String paydatetime, String banktranbatchno) {
        super();
        this.tid = tid;
        this.banktrandataseqno = banktrandataseqno;
        this.banktranresno = banktranresno;
        this.accno = accno;
        this.accname = accname;
        this.tranamt = tranamt;
        this.restype = restype;
        this.rescode = rescode;
        this.resinfo = resinfo;
        this.paydate = paydate;
        this.paydatetime = paydatetime;
        this.banktranbatchno = banktranbatchno;
    }
    @Id
    @Column(name="TID",length=12)
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    @Column(name="BANKTRANDATASEQNO",length=32)
    public String getBanktrandataseqno() {
        return banktrandataseqno;
    }
    public void setBanktrandataseqno(String banktrandataseqno) {
        this.banktrandataseqno = banktrandataseqno;
    }
    @Column(name="BANKTRANRESNO",length=32)
    public String getBanktranresno() {
        return banktranresno;
    }
    public void setBanktranresno(String banktranresno) {
        this.banktranresno = banktranresno;
    }
    @Column(name="ACCNO",length=32)
    public String getAccno() {
        return accno;
    }
    public void setAccno(String accno) {
        this.accno = accno;
    }
    @Column(name="ACCNAME",length=128)
    public String getAccname() {
        return accname;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    @Column(name="TRANAMT",length=12)
    public String getTranamt() {
        return tranamt;
    }
    public void setTranamt(String tranamt) {
        this.tranamt = tranamt;
    }
    @Column(name="RESTYPE",length=2)
    public String getRestype() {
        return restype;
    }
    public void setRestype(String restype) {
        this.restype = restype;
    }
    @Column(name="RESCODE",length=8)
    public String getRescode() {
        return rescode;
    }
    public void setRescode(String rescode) {
        this.rescode = rescode;
    }
    @Column(name="RESINFO",length=64)
    public String getResinfo() {
        return resinfo;
    }
    public void setResinfo(String resinfo) {
        this.resinfo = resinfo;
    }
    @Column(name="PAYDATE",length=8)
    public String getPaydate() {
        return paydate;
    }
    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }
    @Column(name="PAYDATETIME",length=14)
    public String getPaydatetime() {
        return paydatetime;
    }
    public void setPaydatetime(String paydatetime) {
        this.paydatetime = paydatetime;
    }
    @Column(name="BANKTRANBATCHNO",length=32)
    public String getBanktranbatchno() {
        return banktranbatchno;
    }
    public void setBanktranbatchno(String banktranbatchno) {
        this.banktranbatchno = banktranbatchno;
    }
    
}
