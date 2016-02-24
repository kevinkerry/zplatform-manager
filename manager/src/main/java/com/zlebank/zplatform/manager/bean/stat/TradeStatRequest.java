package com.zlebank.zplatform.manager.bean.stat;

import java.io.Serializable;

public class TradeStatRequest implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7201859151054873267L;
    
    private String beginDate;
    private String endDate;
    private String firLevMemId;
    private String secLevMemId;
    
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
    public String getFirLevMemId() {
        return firLevMemId;
    }
    public void setFirLevMemId(String firLevMemId) {
        this.firLevMemId = firLevMemId;
    }
    public String getSecLevMemId() {
        return secLevMemId;
    }
    public void setSecLevMemId(String secLevMemId) {
        this.secLevMemId = secLevMemId;
    }
}
