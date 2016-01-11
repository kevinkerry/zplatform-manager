/* 
 * AccountQuery.java  
 * 
 * version TODO
 *
 * 2015年10月15日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;
import com.zlebank.zplatform.acc.bean.enums.Usage;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月15日 下午4:49:54
 * @since 
 */
public class AccountQuery {
    //可用余额
    private String balance;
    //冻结余额
    private String fronzenBalance;
    //账户总额
    private String totalBalance;
    //业务账户名称
    private String busiAcctName;
    //业务账户号
    private String busiAcctCode;
    //账户状态
    private String status;
    /**科目号**/
    private String acctCode;
   /**会计账户Id**/
    private String acctId;
    //用途
    private Usage usage;
    
    private String memberID;
    
    
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getFronzenBalance() {
        return fronzenBalance;
    }
    public void setFronzenBalance(String fronzenBalance) {
        this.fronzenBalance = fronzenBalance;
    }
    public String getTotalBalance() {
        return totalBalance;
    }
    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }
    public String getBusiAcctName() {
        return busiAcctName;
    }
    public void setBusiAcctName(String busiAcctName) {
        this.busiAcctName = busiAcctName;
    }
    public String getBusiAcctCode() {
        return busiAcctCode;
    }
    public void setBusiAcctCode(String busiAcctCode) {
        this.busiAcctCode = busiAcctCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAcctCode() {
        return acctCode;
    }
    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }
    public String getAcctId() {
        return acctId;
    }
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
    public Usage getUsage() {
        return usage;
    }
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    
    

}
