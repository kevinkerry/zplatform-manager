package com.zlebank.zplatform.manager.bean;

public class MoneyStatisticsQueryBean {

    /**组织机构号**/
    private String instiCode;
    /**账户组编号**/
    private String groupCode;
    /**状态**/
    private String accStatus;
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public String getAccStatus() {
        return accStatus;
    }
    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }
    
   
}
