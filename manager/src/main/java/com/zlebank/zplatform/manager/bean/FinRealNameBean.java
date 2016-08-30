package com.zlebank.zplatform.manager.bean;

public class FinRealNameBean {
	//审核是否成功
	private String auditFlag;
	//审核意见
	private String opinion;
	//打款金额
	private String amount;
	//会员ID
	private String memberId;
	//企业名称
	private String enterpriseName;
	//enterpriseNameAPP主键
	private Long tid;
	
	
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
