package com.zlebank.zplatform.manager.dao.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_Merch_Reimburse_Batch")
public class FundMerchantBeanModel implements java.io.Serializable {
	

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5039676335193193516L;
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
	public FundMerchantBeanModel() {
	}

	public FundMerchantBeanModel(BigDecimal tid, String batchNo, String merId,
			BigDecimal totalAmt, String status) {
		this.tid = tid;
		this.batchNo = batchNo;
		this.merId = merId;
		this.totalAmt = totalAmt;
		this.status = status;
	}

	public FundMerchantBeanModel(BigDecimal tid, String batchNo, String merId,
			String prodcutcode, String txnTime, Integer totalQty,
			BigDecimal totalAmt, String status, Long inuser, Date intime,
			Long upuser, Date uptime, String notes, String type,
			Integer approveCount, BigDecimal approveAmt,
			Integer unapproveCount, BigDecimal unapproveAmt,
			Integer refuseCount, BigDecimal refuseAmt, Date applyTime,
			Date approveFinishTime, Date finishTime, String filePath,
			String originalFileName) {
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
	}

	
    
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 12, scale = 0)
	public BigDecimal getTid() {
		return this.tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	@Column(name = "BATCH_NO", nullable = false, length = 32)
	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "MER_ID", nullable = false, length = 15)
	public String getMerId() {
		return this.merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(name = "PRODCUTCODE", length = 16)
	public String getProdcutcode() {
		return this.prodcutcode;
	}

	public void setProdcutcode(String prodcutcode) {
		this.prodcutcode = prodcutcode;
	}

	@Column(name = "TXN_TIME", length = 14)
	public String getTxnTime() {
		return this.txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	@Column(name = "TOTAL_QTY", precision = 9, scale = 0)
	public Integer getTotalQty() {
		return this.totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	@Column(name = "TOTAL_AMT", nullable = false, scale = 0)
	public BigDecimal getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Column(name = "STATUS", nullable = false, length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "INUSER", precision = 10, scale = 0)
	public Long getInuser() {
		return this.inuser;
	}

	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTIME", length = 7)
	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Column(name = "UPUSER", precision = 10, scale = 0)
	public Long getUpuser() {
		return this.upuser;
	}

	public void setUpuser(Long upuser) {
		this.upuser = upuser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPTIME", length = 7)
	public Date getUptime() {
		return this.uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	@Column(name = "NOTES")
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "APPROVE_COUNT", precision = 6, scale = 0)
	public Integer getApproveCount() {
		return this.approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}

	@Column(name = "APPROVE_AMT", precision = 12)
	public BigDecimal getApproveAmt() {
		return this.approveAmt;
	}

	public void setApproveAmt(BigDecimal approveAmt) {
		this.approveAmt = approveAmt;
	}

	@Column(name = "UNAPPROVE_COUNT", precision = 6, scale = 0)
	public Integer getUnapproveCount() {
		return this.unapproveCount;
	}

	public void setUnapproveCount(Integer unapproveCount) {
		this.unapproveCount = unapproveCount;
	}

	@Column(name = "UNAPPROVE_AMT", precision = 12)
	public BigDecimal getUnapproveAmt() {
		return this.unapproveAmt;
	}

	public void setUnapproveAmt(BigDecimal unapproveAmt) {
		this.unapproveAmt = unapproveAmt;
	}

	@Column(name = "REFUSE_COUNT", precision = 6, scale = 0)
	public Integer getRefuseCount() {
		return this.refuseCount;
	}

	public void setRefuseCount(Integer refuseCount) {
		this.refuseCount = refuseCount;
	}

	@Column(name = "REFUSE_AMT", precision = 12)
	public BigDecimal getRefuseAmt() {
		return this.refuseAmt;
	}

	public void setRefuseAmt(BigDecimal refuseAmt) {
		this.refuseAmt = refuseAmt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPLY_TIME", length = 7)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPROVE_FINISH_TIME", length = 7)
	public Date getApproveFinishTime() {
		return this.approveFinishTime;
	}

	public void setApproveFinishTime(Date approveFinishTime) {
		this.approveFinishTime = approveFinishTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FINISH_TIME", length = 7)
	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "FILE_PATH", length = 256)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "ORIGINAL_FILE_NAME", length = 256)
	public String getOriginalFileName() {
		return this.originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	/**
	 * @return the tn
	 */
	@Column(name = "TN", length = 256)
	public String getTn() {
		return tn;
	}

	/**
	 * @param tn the tn to set
	 */
	public void setTn(String tn) {
		this.tn = tn;
	}

	/**
	 * @return the coopinsticode
	 */
	@Column(name = "COOPINSTICODE")
	public String getCoopinsticode() {
		return coopinsticode;
	}

	/**
	 * @param coopinsticode the coopinsticode to set
	 */
	public void setCoopinsticode(String coopinsticode) {
		this.coopinsticode = coopinsticode;
	}
	
	
}
