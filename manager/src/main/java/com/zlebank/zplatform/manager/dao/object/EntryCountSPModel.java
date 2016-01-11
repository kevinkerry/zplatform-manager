package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntryCountSPModel {
    @Id
    private long rowNum;
    private String acctCode;
    private String acctCodeName;
    private String cNum;
    private String cAmount;
    private String dNum;
    private String dAmount;
    private String frozenNum;
    private String frozenAmount;
    private String unFrozenNum;
    private String unFrozenAmount;
    private String befBalance;
    private String aftBalance;
    private String crdr;
    
    public long getRowNum() {
        return rowNum;
    }
    public void setRowNum(long rowNum) {
        this.rowNum = rowNum;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getAcctCodeName() {
        return acctCodeName;
    }
    public void setAcctCodeName(String acctCodeName) {
        this.acctCodeName = acctCodeName;
    }
    public String getcAmount() {
        return cAmount;
    }
    public void setcAmount(String cAmount) {
        this.cAmount = cAmount;
    }
    public String getdAmount() {
        return dAmount;
    }
    public void setdAmount(String dAmount) {
        this.dAmount = dAmount;
    }
    public String getFrozenNum() {
        return frozenNum;
    }
    public void setFrozenNum(String frozenNum) {
        this.frozenNum = frozenNum;
    }
    public String getFrozenAmount() {
        return frozenAmount;
    }
    public void setFrozenAmount(String frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
    public String getUnFrozenNum() {
        return unFrozenNum;
    }
    public void setUnFrozenNum(String unFrozenNum) {
        this.unFrozenNum = unFrozenNum;
    }
    public String getUnFrozenAmount() {
        return unFrozenAmount;
    }
    public void setUnFrozenAmount(String unFrozenAmount) {
        this.unFrozenAmount = unFrozenAmount;
    }
    public String getBefBalance() {
        return befBalance;
    }
    public void setBefBalance(String befBalance) {
        this.befBalance = befBalance;
    }
    public String getAftBalance() {
        return aftBalance;
    }
    public void setAftBalance(String aftBalance) {
        this.aftBalance = aftBalance;
    }
    public String getcNum() {
        return cNum;
    }
    public void setcNum(String cNum) {
        this.cNum = cNum;
    }
    public String getdNum() {
        return dNum;
    }
    public void setdNum(String dNum) {
        this.dNum = dNum;
    }
    public String getCrdr() {
        return crdr;
    }
    public void setCrdr(String crdr) {
        this.crdr = crdr;
    }
}
