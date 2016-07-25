package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

public class CmbcResfileBean implements Bean{

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
    
    
    public CmbcResfileBean() {
        super();
    }
    public CmbcResfileBean(String tid, String banktrandataseqno,
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
    public String getTid() {
        return tid;
    }
    public void setTid(String tid) {
        this.tid = tid;
    }
    public String getBanktrandataseqno() {
        return banktrandataseqno;
    }
    public void setBanktrandataseqno(String banktrandataseqno) {
        this.banktrandataseqno = banktrandataseqno;
    }
    public String getBanktranresno() {
        return banktranresno;
    }
    public void setBanktranresno(String banktranresno) {
        this.banktranresno = banktranresno;
    }
    public String getAccno() {
        return accno;
    }
    public void setAccno(String accno) {
        this.accno = accno;
    }
    public String getAccname() {
        return accname;
    }
    public void setAccname(String accname) {
        this.accname = accname;
    }
    public String getTranamt() {
        return tranamt;
    }
    public void setTranamt(String tranamt) {
        this.tranamt = tranamt;
    }
    public String getRestype() {
        return restype;
    }
    public void setRestype(String restype) {
        this.restype = restype;
    }
    public String getRescode() {
        return rescode;
    }
    public void setRescode(String rescode) {
        this.rescode = rescode;
    }
    public String getResinfo() {
        return resinfo;
    }
    public void setResinfo(String resinfo) {
        this.resinfo = resinfo;
    }
    public String getPaydate() {
        return paydate;
    }
    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }
    public String getPaydatetime() {
        return paydatetime;
    }
    public void setPaydatetime(String paydatetime) {
        this.paydatetime = paydatetime;
    }
    public String getBanktranbatchno() {
        return banktranbatchno;
    }
    public void setBanktranbatchno(String banktranbatchno) {
        this.banktranbatchno = banktranbatchno;
    }
    
    
}
