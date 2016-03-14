package com.zlebank.zplatform.manager.bean;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.bean.Bean;

public class TranBatch implements Bean{
    /**标示**/
    private Long tid;
    /**划拨批次号**/
     private String tranBatchNo;
    /**总笔数**/
    private Long totalCount;
    /**总金额**/
    private Long totalAmt;
    /**审核通过笔数**/
    private Long approveCount;
    /**审核通过金额**/
    private Long approveAmt;
    /**审核拒绝笔数**/
    private Long refuseCount;
    /**审核拒绝金额**/
    private Long refuseAmt;
    /**"""状态（01：未审核02：部分审核通过03：全部审核通过**/
    private String status;
    /**"申请时间"**/
    private Date applyTime;
    /**"全部通过完成时间"**/
    private Date approveFinishTime;
    /**"业务类型（00：代付01：提现02：退款）"**/
    private String busiType;
    /**"业务批次ID"**/
    private String busiBatchId;
    /**"银行转账完成时间"**/
    private Date finishTime; 
    /**待审核笔数**/
    private Long waitApproveCount;
    /**待审核金额**/
    private Long waitApproveAmt;
    /**银行转账批次号**/
    private List<BankTranBatch> bankTransferBatchs;
    /**银行转账流水**/
    private List<TranData> tranDatas;
    public Long getTid() {
        return tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public String getTranBatchNo() {
        return tranBatchNo;
    }
    public void setTranBatchNo(String tranBatchNo) {
        this.tranBatchNo = tranBatchNo;
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
    public Long getApproveCount() {
        return approveCount;
    }
    public void setApproveCount(Long approveCount) {
        this.approveCount = approveCount;
    }
    public Long getApproveAmt() {
        return approveAmt;
    }
    public void setApproveAmt(Long approveAmt) {
        this.approveAmt = approveAmt;
    }
    public Long getRefuseCount() {
        return refuseCount;
    }
    public void setRefuseCount(Long refuseCount) {
        this.refuseCount = refuseCount;
    }
    public Long getRefuseAmt() {
        return refuseAmt;
    }
    public void setRefuseAmt(Long refuseAmt) {
        this.refuseAmt = refuseAmt;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public Date getApproveFinishTime() {
        return approveFinishTime;
    }
    public void setApproveFinishTime(Date approveFinishTime) {
        this.approveFinishTime = approveFinishTime;
    }
    public String getBusiType() {
        return busiType;
    }
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }
    public String getBusiBatchId() {
        return busiBatchId;
    }
    public void setBusiBatchId(String busiBatchId) {
        this.busiBatchId = busiBatchId;
    }
    public Date getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public Long getWaitApproveCount() {
        return waitApproveCount;
    }
    public void setWaitApproveCount(Long waitApproveCount) {
        this.waitApproveCount = waitApproveCount;
    }
    public Long getWaitApproveAmt() {
        return waitApproveAmt;
    }
    public void setWaitApproveAmt(Long waitApproveAmt) {
        this.waitApproveAmt = waitApproveAmt;
    }
    public List<BankTranBatch> getBankTransferBatchs() {
        return bankTransferBatchs;
    }
    public void setBankTransferBatchs(List<BankTranBatch> bankTransferBatchs) {
        this.bankTransferBatchs = bankTransferBatchs;
    }
    public List<TranData> getTranDatas() {
        return tranDatas;
    }
    public void setTranDatas(List<TranData> tranDatas) {
        this.tranDatas = tranDatas;
    }
}
