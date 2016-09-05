package com.zlebank.zplatform.manager.bean.fund.remit;

import com.zlebank.zplatform.commons.bean.Bean;

public class RemitBean implements Bean{
	
	private String tid;
	private String memberid;
	private String financingid;
	private String productcode;
	private String orderid;
	private String tn;
	private String status;
	private String in_user;
	private String in_time;
	private String stexa_user;
	private String stexa_time;
	private String stexa_opt;
	private String cvlexa_user;
	private String cvlexa_time;
	private String cvlexa_opt;
	private String notes;
	private String remarks;
	
	
	public RemitBean(String tid, String memberid, String financingid, String productcode, String orderid, String tn,
			String status, String in_user, String in_time, String stexa_user, String stexa_time, String stexa_opt,
			String cvlexa_user, String cvlexa_time, String cvlexa_opt, String notes, String remarks) {
		super();
		this.tid = tid;
		this.memberid = memberid;
		this.financingid = financingid;    //融资id
		this.productcode = productcode;    // 产品代码
		this.orderid = orderid;            //订单号
		this.tn = tn;                       //订单
		this.status = status;
		this.in_user = in_user;
		this.in_time = in_time;
		this.stexa_user = stexa_user;
		this.stexa_time = stexa_time;
		this.stexa_opt = stexa_opt;
		this.cvlexa_user = cvlexa_user;
		this.cvlexa_time = cvlexa_time;
		this.cvlexa_opt = cvlexa_opt;
		this.notes = notes;
		this.remarks = remarks;
	}
	public RemitBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getFinancingid() {
		return financingid;
	}
	public void setFinancingid(String financingid) {
		this.financingid = financingid;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIn_user() {
		return in_user;
	}
	public void setIn_user(String in_user) {
		this.in_user = in_user;
	}
	public String getIn_time() {
		return in_time;
	}
	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}
	public String getStexa_user() {
		return stexa_user;
	}
	public void setStexa_user(String stexa_user) {
		this.stexa_user = stexa_user;
	}
	public String getStexa_time() {
		return stexa_time;
	}
	public void setStexa_time(String stexa_time) {
		this.stexa_time = stexa_time;
	}
	public String getStexa_opt() {
		return stexa_opt;
	}
	public void setStexa_opt(String stexa_opt) {
		this.stexa_opt = stexa_opt;
	}
	public String getCvlexa_user() {
		return cvlexa_user;
	}
	public void setCvlexa_user(String cvlexa_user) {
		this.cvlexa_user = cvlexa_user;
	}
	public String getCvlexa_time() {
		return cvlexa_time;
	}
	public void setCvlexa_time(String cvlexa_time) {
		this.cvlexa_time = cvlexa_time;
	}
	public String getCvlexa_opt() {
		return cvlexa_opt;
	}
	public void setCvlexa_opt(String cvlexa_opt) {
		this.cvlexa_opt = cvlexa_opt;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "RemitBean [tid=" + tid + ", memberid=" + memberid + ", financingid=" + financingid + ", productcode="
				+ productcode + ", orderid=" + orderid + ", tn=" + tn + ", status=" + status + ", in_user=" + in_user
				+ ", in_time=" + in_time + ", stexa_user=" + stexa_user + ", stexa_time=" + stexa_time + ", stexa_opt="
				+ stexa_opt + ", cvlexa_user=" + cvlexa_user + ", cvlexa_time=" + cvlexa_time + ", cvlexa_opt="
				+ cvlexa_opt + ", notes=" + notes + ", remarks=" + remarks + "]";
	}
	
	
}
