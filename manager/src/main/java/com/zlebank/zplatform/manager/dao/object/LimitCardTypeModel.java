package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LimitCardTypeModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LIMIT_CARD_TYPE")
public class LimitCardTypeModel implements java.io.Serializable {

	// Fields

	private Long TId;
	private String caseid;
	private String debitCardFlag;
	private String creditCardFag;
	private String quasiCreditCardFlag;
	private String prePaymentCardFalg;
	private String status;
	private String risklevel;
	private String notes;
	private String remarks;

	// Constructors

	/** default constructor */
	public LimitCardTypeModel() {
	}

	/** minimal constructor */
	public LimitCardTypeModel(Long TId, String caseid, String debitCardFlag,
			String creditCardFag, String quasiCreditCardFlag,
			String prePaymentCardFalg) {
		this.TId = TId;
		this.caseid = caseid;
		this.debitCardFlag = debitCardFlag;
		this.creditCardFag = creditCardFag;
		this.quasiCreditCardFlag = quasiCreditCardFlag;
		this.prePaymentCardFalg = prePaymentCardFalg;
	}

	/** full constructor */
	public LimitCardTypeModel(Long TId, String caseid, String debitCardFlag,
			String creditCardFag, String quasiCreditCardFlag,
			String prePaymentCardFalg, String status, String risklevel,
			String notes, String remarks) {
		this.TId = TId;
		this.caseid = caseid;
		this.debitCardFlag = debitCardFlag;
		this.creditCardFag = creditCardFag;
		this.quasiCreditCardFlag = quasiCreditCardFlag;
		this.prePaymentCardFalg = prePaymentCardFalg;
		this.status = status;
		this.risklevel = risklevel;
		this.notes = notes;
		this.remarks = remarks;
	}

	// Property accessors
	@Id
	@Column(name = "T_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getTId() {
		return this.TId;
	}

	public void setTId(Long TId) {
		this.TId = TId;
	}

	@Column(name = "CASEID", nullable = false, precision = 10, scale = 0)
	public String getCaseid() {
		return this.caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	@Column(name = "DEBIT_CARD_FLAG", nullable = false, precision = 1, scale = 0)
	public String getDebitCardFlag() {
		return this.debitCardFlag;
	}

	public void setDebitCardFlag(String debitCardFlag) {
		this.debitCardFlag = debitCardFlag;
	}

	@Column(name = "CREDIT_CARD_FAG", nullable = false, precision = 1, scale = 0)
	public String getCreditCardFag() {
		return this.creditCardFag;
	}

	public void setCreditCardFag(String creditCardFag) {
		this.creditCardFag = creditCardFag;
	}

	@Column(name = "QUASI_CREDIT_CARD_FLAG", nullable = false, precision = 1, scale = 0)
	public String getQuasiCreditCardFlag() {
		return this.quasiCreditCardFlag;
	}

	public void setQuasiCreditCardFlag(String quasiCreditCardFlag) {
		this.quasiCreditCardFlag = quasiCreditCardFlag;
	}

	@Column(name = "PRE_PAYMENT_CARD_FALG", nullable = false, precision = 1, scale = 0)
	public String getPrePaymentCardFalg() {
		return this.prePaymentCardFalg;
	}

	public void setPrePaymentCardFalg(String prePaymentCardFalg) {
		this.prePaymentCardFalg = prePaymentCardFalg;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RISKLEVEL", precision = 1, scale = 0)
	public String getRisklevel() {
		return this.risklevel;
	}

	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
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

}