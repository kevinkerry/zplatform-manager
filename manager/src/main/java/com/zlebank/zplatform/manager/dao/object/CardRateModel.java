package com.zlebank.zplatform.manager.dao.object;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * TCardRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CARD_RATE", uniqueConstraints = @UniqueConstraint(columnNames = {
		"FEEVER", "BUSICODE", "CARDTYPE" }))
public class CardRateModel implements java.io.Serializable {

	// Fields

	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6686745495610585285L;
    private Long tid;
	private String feever;
	private String busicode;
	private String cardtype;
	private BigDecimal feeRate;
	private BigDecimal minFee;
	private BigDecimal maxFee;
	private String rateType;
	private Long inuser;
	private Date intime;
	private String notes;
	private String remarks;
	private String minFeeStr;
	private String maxFeeStr;
	private String feeRateStr;
	// Constructors

	/** default constructor */
	public CardRateModel() {
	}

	/** minimal constructor */
	public CardRateModel(Long tid, String feever, String busicode,
			String cardtype, BigDecimal feeRate) {
		this.tid = tid;
		this.feever = feever;
		this.busicode = busicode;
		this.cardtype = cardtype;
		this.feeRate = feeRate;
	}

	/** full constructor */
	public CardRateModel(Long tid, String feever, String busicode,
			String cardtype, BigDecimal feeRate, BigDecimal minFee, BigDecimal maxFee,
			String rateType, Long inuser, Date intime, String notes,
			String remarks) {
		this.tid = tid;
		this.feever = feever;
		this.busicode = busicode;
		this.cardtype = cardtype;
		this.feeRate = feeRate;
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.rateType = rateType;
		this.inuser = inuser;
		this.intime = intime;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "TID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTid() {
		return this.tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "FEEVER", nullable = false, length = 8)
	public String getFeever() {
		return this.feever;
	}

	public void setFeever(String feever) {
		this.feever = feever;
	}

	@Column(name = "BUSICODE", nullable = false, length = 8)
	public String getBusicode() {
		return this.busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	@Column(name = "CARDTYPE", nullable = false, precision = 1, scale = 0)
	public String getCardtype() {
		return this.cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	@Column(name = "FEE_RATE", nullable = false, precision = 4, scale = 0)
	public BigDecimal getFeeRate() {
		return this.feeRate;
	}

	public void setFeeRate(BigDecimal feeRate) {
		this.feeRate = feeRate;
	}

	@Column(name = "MIN_FEE", precision = 12, scale = 0)
	public BigDecimal getMinFee() {
		return this.minFee;
	}

	public void setMinFee(BigDecimal minFee) {
		this.minFee = minFee;
	}

	@Column(name = "MAX_FEE", precision = 12, scale = 0)
	public BigDecimal getMaxFee() {
		return this.maxFee;
	}

	public void setMaxFee(BigDecimal maxFee) {
		this.maxFee = maxFee;
	}

	@Column(name = "RATE_TYPE", length = 2)
	public String getRateType() {
		return this.rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@Column(name = "INUSER", precision = 10, scale = 0)
	public Long getInuser() {
		return this.inuser;
	}

	public void setInuser(Long inuser) {
		this.inuser = inuser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INTIME", length = 7)
	public Date getIntime() {
		return this.intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	@Column(name = "NOTES", length = 128)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "REMARKS", length = 128)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Transient
    public String getMinFeeStr() {
        return minFeeStr;
    }

    public void setMinFeeStr(String minFeeStr) {
        this.minFeeStr = minFeeStr;
    }
    @Transient
    public String getMaxFeeStr() {
        return maxFeeStr;
    }

    public void setMaxFeeStr(String maxFeeStr) {
        this.maxFeeStr = maxFeeStr;
    }
    @Transient
    public String getFeeRateStr() {
        return feeRateStr;
    }

    public void setFeeRateStr(String feeRateStr) {
        this.feeRateStr = feeRateStr;
    }
}