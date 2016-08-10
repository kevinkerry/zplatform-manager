package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

public class RiskAnalyseLogQueryBean implements Bean {

    /**规则编号**/
    private String rolecode;
    /**会员号商户号银行卡号**/
    private String memberid;
    /**规则类型**/
    private String roletype;
    /**开始时间**/
    private String startdate;
    /**结束时间**/
    private String enddate;
    /**写入人**/
    private String user;
    
    public String getRolecode() {
        return rolecode;
    }
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }    
    public String getRoletype() {
        return roletype;
    }
    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }
    public String getStartdate() {
        return startdate;
    }
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    public String getEnddate() {
        return enddate;
    }
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    
}
