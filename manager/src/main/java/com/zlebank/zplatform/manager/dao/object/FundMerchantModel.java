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
@Table(name = "T_MERCH_REIMBURSE_DETA")
public class FundMerchantModel implements java.io.Serializable{
	
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3172549297908876859L;
	private BigDecimal tid;
	private String batchno;
	private String merId;
	private String investors;
	private String orderId;
	private BigDecimal txnamt;
	private BigDecimal interset;
	private String porductCode;
	private String status;
	private Long inuser;
	private Date intime;
	private Long stexauser;
	private Date stexatime;
	private String stexaopt;
	private Long cvlexauser;
	private Date cvlexatime;
	private String cvlexaopt;
	private String remarks;
	private String txnseqno;
	private String retinfo;
	private String retCode;
	
	public FundMerchantModel() {
	}

	public FundMerchantModel(BigDecimal tid, String batchno, String orderId,
			Date intime) {
		this.tid = tid;
		this.batchno = batchno;
		this.orderId = orderId;
		this.intime = intime;
	}

	public FundMerchantModel(BigDecimal tid, String batchno, String merId,
			String investors, String orderId, BigDecimal txnamt,
			BigDecimal interset, String porductCode, String status,
			Long inuser, Date intime, Long stexauser, Date stexatime,
			String stexaopt, Long cvlexauser, Date cvlexatime,
			String cvlexaopt, String remarks) {
		this.tid = tid;
		this.batchno = batchno;
		this.merId = merId;
		this.investors = investors;
		this.orderId = orderId;
		this.txnamt = txnamt;
		this.interset = interset;
		this.porductCode = porductCode;
		this.status = status;
		this.inuser = inuser;
		this.intime = intime;
		this.stexauser = stexauser;
		this.stexatime = stexatime;
		this.stexaopt = stexaopt;
		this.cvlexauser = cvlexauser;
		this.cvlexatime = cvlexatime;
		this.cvlexaopt = cvlexaopt;
		this.remarks = remarks;
	}

	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 12, scale = 0)
	public BigDecimal getTid() {
		return this.tid;
	}

	public void setTid(BigDecimal tid) {
		this.tid = tid;
	}

	@Column(name = "BATCHNO", nullable = false, length = 32)
	public String getBatchno() {
		return this.batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	@Column(name = "MER_ID", length = 15)
	public String getMerId() {
		return this.merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	@Column(name = "INVESTORS", length = 15)
	public String getInvestors() {
		return this.investors;
	}

	public void setInvestors(String investors) {
		this.investors = investors;
	}

	@Column(name = "ORDER_ID", nullable = false, length = 32)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "TXNAMT", scale = 0)
	public BigDecimal getTxnamt() {
		return this.txnamt;
	}

	public void setTxnamt(BigDecimal txnamt) {
		this.txnamt = txnamt;
	}

	@Column(name = "INTERSET", scale = 0)
	public BigDecimal getInterset() {
		return this.interset;
	}

	public void setInterset(BigDecimal interset) {
		this.interset = interset;
	}

	@Column(name = "PORDUCT_CODE", length = 6)
	public String getPorductCode() {
		return this.porductCode;
	}

	public void setPorductCode(String porductCode) {
		this.porductCode = porductCode;
	}

	@Column(name = "STATUS", length = 2)
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
	@Column(name = "INTIME", nullable = false, length = 7)
	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Column(name = "STEXAUSER", precision = 10, scale = 0)
	public Long getStexauser() {
		return this.stexauser;
	}

	public void setStexauser(Long stexauser) {
		this.stexauser = stexauser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STEXATIME", length = 7)
	public Date getStexatime() {
		return this.stexatime;
	}

	public void setStexatime(Date stexatime) {
		this.stexatime = stexatime;
	}

	@Column(name = "STEXAOPT", length = 256)
	public String getStexaopt() {
		return this.stexaopt;
	}

	public void setStexaopt(String stexaopt) {
		this.stexaopt = stexaopt;
	}

	@Column(name = "CVLEXAUSER", precision = 10, scale = 0)
	public Long getCvlexauser() {
		return this.cvlexauser;
	}

	public void setCvlexauser(Long cvlexauser) {
		this.cvlexauser = cvlexauser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CVLEXATIME", length = 7)
	public Date getCvlexatime() {
		return this.cvlexatime;
	}

	public void setCvlexatime(Date cvlexatime) {
		this.cvlexatime = cvlexatime;
	}

	@Column(name = "CVLEXAOPT", length = 256)
	public String getCvlexaopt() {
		return this.cvlexaopt;
	}

	public void setCvlexaopt(String cvlexaopt) {
		this.cvlexaopt = cvlexaopt;
	}

	@Column(name = "REMARKS", length = 256)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the txnseqno
	 */
	@Column(name = "TXNSEQNO")
	public String getTxnseqno() {
		return txnseqno;
	}

	/**
	 * @param txnseqno the txnseqno to set
	 */
	public void setTxnseqno(String txnseqno) {
		this.txnseqno = txnseqno;
	}

	/**
	 * @return the retinfo
	 */
	@Column(name = "RETINFO")
	public String getRetinfo() {
		return retinfo;
	}

	/**
	 * @param retinfo the retinfo to set
	 */
	public void setRetinfo(String retinfo) {
		this.retinfo = retinfo;
	}

	/**
	 * @return the retCode
	 */
	@Column(name = "RETCODE")
	public String getRetCode() {
		return retCode;
	}

	/**
	 * @param retCode the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	
	
}
