package com.zlebank.zplatform.manager.bean;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.zlebank.zplatform.commons.bean.Bean;

public class FundMerchantBean implements Bean{
	
	private BigDecimal tid;
	private String batchNo;
	private String merId;
	private String prodcutcode;
	private String txnTime;
	private Integer totalQty;
	private BigDecimal totalAmt;
	private String status;
	private Long inuser;
	private Date intime;
	private Long upuser;
	private Date uptime;
	private String notes;
	private String type;
	private Integer approveCount;
	private BigDecimal approveAmt;
	private Integer unapproveCount;
	private BigDecimal unapproveAmt;
	private Integer refuseCount;
	private BigDecimal refuseAmt;
	private Date applyTime;
	private Date approveFinishTime;
	private Date finishTime;
	private String filePath;
	private String originalFileName;
	private String tn;
	private String coopinsticode;
	public BigDecimal getTid() {
		return tid;
	}
	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getProdcutcode() {
		return prodcutcode;
	}
	public void setProdcutcode(String prodcutcode) {
		this.prodcutcode = prodcutcode;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public Integer getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}
	public BigDecimal getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getInuser() {
		return inuser;
	}
	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}
	public Date getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		this.intime = intime;
	}
	public Long getUpuser() {
		return upuser;
	}
	public void setUpuser(Long upuser) {
		this.upuser = upuser;
	}
	public Date getUptime() {
		return uptime;
	}
	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getApproveCount() {
		return approveCount;
	}
	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}
	public BigDecimal getApproveAmt() {
		return approveAmt;
	}
	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}
	public Integer getUnapproveCount() {
		return unapproveCount;
	}
	public void setUnapproveCount(Integer unapproveCount) {
		this.unapproveCount = unapproveCount;
	}
	public BigDecimal getUnapproveAmt() {
		return unapproveAmt;
	}
	public void setUnapproveAmt(BigDecimal unapproveAmt) {
		this.unapproveAmt = unapproveAmt;
	}
	public Integer getRefuseCount() {
		return refuseCount;
	}
	public void setRefuseCount(Integer refuseCount) {
		this.refuseCount = refuseCount;
	}
	public BigDecimal getRefuseAmt() {
		return refuseAmt;
	}
	public void setRefuseAmt(BigDecimal refuseAmt) {
		this.refuseAmt = refuseAmt;
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
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getCoopinsticode() {
		return coopinsticode;
	}
	public void setCoopinsticode(String coopinsticode) {
		this.coopinsticode = coopinsticode;
	}
	public FundMerchantBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FundMerchantBean(BigDecimal tid, String batchNo, String merId, String prodcutcode, String txnTime,
			Integer totalQty, BigDecimal totalAmt, String status, Long inuser, Date intime, Long upuser, Date uptime,
			String notes, String type, Integer approveCount, BigDecimal approveAmt, Integer unapproveCount,
			BigDecimal unapproveAmt, Integer refuseCount, BigDecimal refuseAmt, Date applyTime, Date approveFinishTime,
			Date finishTime, String filePath, String originalFileName, String tn, String coopinsticode) {
		super();
		this.tid = tid;
		this.batchNo = batchNo;
		this.merId = merId;
		this.prodcutcode = prodcutcode;
		this.txnTime = txnTime;
		this.totalQty = totalQty;
		this.totalAmt = totalAmt;
		this.status = status;
		this.inuser = inuser;
		this.intime = intime;
		this.upuser = upuser;
		this.uptime = uptime;
		this.notes = notes;
		this.type = type;
		this.approveCount = approveCount;
		this.approveAmt = approveAmt;
		this.unapproveCount = unapproveCount;
		this.unapproveAmt = unapproveAmt;
		this.refuseCount = refuseCount;
		this.refuseAmt = refuseAmt;
		this.applyTime = applyTime;
		this.approveFinishTime = approveFinishTime;
		this.finishTime = finishTime;
		this.filePath = filePath;
		this.originalFileName = originalFileName;
		this.tn = tn;
		this.coopinsticode = coopinsticode;
	}
	@Override
	public String toString() {
		return "FundMerchantBean [tid=" + tid + ", batchNo=" + batchNo + ", merId=" + merId + ", prodcutcode="
				+ prodcutcode + ", txnTime=" + txnTime + ", totalQty=" + totalQty + ", totalAmt=" + totalAmt
				+ ", status=" + status + ", inuser=" + inuser + ", intime=" + intime + ", upuser=" + upuser
				+ ", uptime=" + uptime + ", notes=" + notes + ", type=" + type + ", approveCount=" + approveCount
				+ ", approveAmt=" + approveAmt + ", unapproveCount=" + unapproveCount + ", unapproveAmt=" + unapproveAmt
				+ ", refuseCount=" + refuseCount + ", refuseAmt=" + refuseAmt + ", applyTime=" + applyTime
				+ ", approveFinishTime=" + approveFinishTime + ", finishTime=" + finishTime + ", filePath=" + filePath
				+ ", originalFileName=" + originalFileName + ", tn=" + tn + ", coopinsticode=" + coopinsticode + "]";
	}
	
	

}
