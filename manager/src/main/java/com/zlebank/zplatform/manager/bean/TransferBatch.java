/* 
 * TransferBatch.java  
 * 
 * version TODO
 *
 * 2015年12月10日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月10日 上午11:06:58
 * @since 
 */
public class TransferBatch implements Bean{
    private long tid;
    private String batchno;
    private Long sumitem;
    private Long sumamount;
    private Long succitem;
    private Long succamount;
    private Long failitem;
    private Long failamount;
    private String status;
    private String createtime;
    private String transfertime;
    private String chnlcode;
    private Integer retrytimes;
    private String transfertype;
    private String insteadpaybatchno;
    private String merchid; 
    private String requestfilename;
    private String responsefilename;
    private String accstatus;
    
    private String sumMoney;
    
    private String succMoney;
    
    private String failMoney;
    
    
    
    
   
    public String getSumMoney() {
        return sumMoney;
    }
    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }
    public String getSuccMoney() {
        return succMoney;
    }
    public void setSuccMoney(String succMoney) {
        this.succMoney = succMoney;
    }
    public String getFailMoney() {
        return failMoney;
    }
    public void setFailMoney(String failMoney) {
        this.failMoney = failMoney;
    }
    public long getTid() {
        return tid;
    }
    public void setTid(long tid) {
        this.tid = tid;
    }
    public String getBatchno() {
        return batchno;
    }
    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }
    public Long getSumitem() {
        return sumitem;
    }
    public void setSumitem(Long sumitem) {
        this.sumitem = sumitem;
    }
    public Long getSumamount() {
        return sumamount;
    }
    public void setSumamount(Long sumamount) {
        this.sumamount = sumamount;
    }
    public Long getSuccitem() {
        return succitem;
    }
    public void setSuccitem(Long succitem) {
        this.succitem = succitem;
    }
    public Long getSuccamount() {
        return succamount;
    }
    public void setSuccamount(Long succamount) {
        this.succamount = succamount;
    }
    public Long getFailitem() {
        return failitem;
    }
    public void setFailitem(Long failitem) {
        this.failitem = failitem;
    }
    public Long getFailamount() {
        return failamount;
    }
    public void setFailamount(Long failamount) {
        this.failamount = failamount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getTransfertime() {
        return transfertime;
    }
    public void setTransfertime(String transfertime) {
        this.transfertime = transfertime;
    }
    public String getChnlcode() {
        return chnlcode;
    }
    public void setChnlcode(String chnlcode) {
        this.chnlcode = chnlcode;
    }
    public Integer getRetrytimes() {
        return retrytimes;
    }
    public void setRetrytimes(Integer retrytimes) {
        this.retrytimes = retrytimes;
    }
    public String getTransfertype() {
        return transfertype;
    }
    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }
    public String getInsteadpaybatchno() {
        return insteadpaybatchno;
    }
    public void setInsteadpaybatchno(String insteadpaybatchno) {
        this.insteadpaybatchno = insteadpaybatchno;
    }
    public String getMerchid() {
        return merchid;
    }
    public void setMerchid(String merchid) {
        this.merchid = merchid;
    }
    public String getRequestfilename() {
        return requestfilename;
    }
    public void setRequestfilename(String requestfilename) {
        this.requestfilename = requestfilename;
    }
    public String getResponsefilename() {
        return responsefilename;
    }
    public void setResponsefilename(String responsefilename) {
        this.responsefilename = responsefilename;
    }
    public String getAccstatus() {
        return accstatus;
    }
    public void setAccstatus(String accstatus) {
        this.accstatus = accstatus;
    }
    
    
    
    
    
    
    
}
