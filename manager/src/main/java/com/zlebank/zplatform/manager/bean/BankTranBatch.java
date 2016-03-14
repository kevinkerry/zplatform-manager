package com.zlebank.zplatform.manager.bean;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.bean.Bean;
import com.zlebank.zplatform.manager.bean.enmu.BankTranBatchOpenStatus;

public class BankTranBatch implements Bean{
    /** "表标识" **/
    private Long tid;
    /** "银行转账批次序列号" **/
    private String bankTranBatchNo; 
    /** "关联划拨批次渠道" **/
    private List<TranBatch> tranBatchs;
    /** "总笔数" **/
    private Long totalCount;
    /** "总金额" **/
    private Long totalAmt;
    /** "成功笔数" **/
    private Long successCount;
    /** "成功金额" **/
    private Long successAmt;
    /** "失败笔数" **/
    private Long failCount;
    /** "失败金额" **/
    private Long failAmt;
    /** """状态（01：未审核02：审核通过03：审核拒绝）""" **/
    private String status;
    /** "开放状态（0:开放1：关闭）" **/
    private BankTranBatchOpenStatus openStatus;
    /** """转账状态(01:等待转账02：部分转账成功03：全部转账成功 04：全部失败 **/
    private String tranStatus;
    /** "申请时间 **/
    private Date applyTime;
    /** "默认关闭时间" **/
    private Date defaultCloseTime;
    /** "最后关闭时间（每日）" **/
    private Date latestCloseTime;
    /** "关闭时间" **/
    private Date closeTime;
    /** """触发关闭动作（00：笔数到达上限01：到达每日最后关闭时间02：到达关闭间隔 03：手工关闭）""" **/
    private String closeEvent;
    /** "转账审核人" **/
    private Long tranUser;
    /** 备注 **/
    private String remark;
    /** 关联银行转账流水 **/
    private List<BankTranData> bankTranDatas;
    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public String getBankTranBatchNo() {
        return bankTranBatchNo;
    }
    public void setBankTranBatchNo(String bankTranBatchNo) {
        this.bankTranBatchNo = bankTranBatchNo;
    }
    
    public Long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
    public Long getTotalAmt() {
        return totalAmt;
    }
    public void setTotalAmt(Long totalAmt) {
        this.totalAmt = totalAmt;
    }
    public Long getSuccessCount() {
        return successCount;
    }
    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }
    public Long getSuccessAmt() {
        return successAmt;
    }
    public void setSuccessAmt(Long successAmt) {
        this.successAmt = successAmt;
    }
    public Long getFailCount() {
        return failCount;
    }
    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }
    public Long getFailAmt() {
        return failAmt;
    }
    public void setFailAmt(Long failAmt) {
        this.failAmt = failAmt;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public BankTranBatchOpenStatus getOpenStatus() {
        return openStatus;
    }
    public void setOpenStatus(BankTranBatchOpenStatus openStatus) {
        this.openStatus = openStatus;
    }
    public String getTranStatus() {
        return tranStatus;
    }
    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public Date getDefaultCloseTime() {
        return defaultCloseTime;
    }
    public void setDefaultCloseTime(Date defaultCloseTime) {
        this.defaultCloseTime = defaultCloseTime;
    }
    public Date getLatestCloseTime() {
        return latestCloseTime;
    }
    public void setLatestCloseTime(Date latestCloseTime) {
        this.latestCloseTime = latestCloseTime;
    }
    public Date getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
    public String getCloseEvent() {
        return closeEvent;
    }
    public void setCloseEvent(String closeEvent) {
        this.closeEvent = closeEvent;
    }
    public Long getTranUser() {
        return tranUser;
    }
    public void setTranUser(Long tranUser) {
        this.tranUser = tranUser;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public List<BankTranData> getBankTranDatas() {
        return bankTranDatas;
    }
    public void setBankTranDatas(List<BankTranData> bankTranDatas) {
        this.bankTranDatas = bankTranDatas;
    }
    public List<TranBatch> getTranBatchs() {
        return tranBatchs;
    }
    public void setTranBatchs(List<TranBatch> tranBatchs) {
        this.tranBatchs = tranBatchs;
    }
}
