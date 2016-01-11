package com.zlebank.zplatform.manager.bean.stat;

import java.io.Serializable;

public class EntryCountRequest implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7201859151054873267L;

    private String beginDate;
    private String endDate;
    private String acctCode;

    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
}
