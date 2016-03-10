package com.zlebank.zplatform.manager.bean;

import java.util.Date;

import com.zlebank.zplatform.member.bean.enums.CardType;

/**
 * 
 * Enterprise domain model
 *
 * @author yangying
 * @version
 * @date 2016年3月4日 上午10:48:04
 * @since 1.3.0
 */
public class Enterprise extends Member {
    /** 会员号 **/
    private String enterpriseMemberId;
    /** 合作机构id **/
    private Long coopInstiId;
    /** 企业名称 **/
    private String enterpriseName;
    /** 所属行业 **/
    private String mcc;
    /** 所属行业子类别 **/
    private String mccList;
    /** 企业所属机构 **/
    private Long enterpriseInsti;
    /** 企业所属省 **/
    private Long province;
    /** 企业所属市 **/
    private Long city;
    /** 企业所属县 **/
    private Long street;
    /** 企业行政地区代码（由商户所属县得到） **/
    private String zoneCode;
    /** 企业邮编 **/
    private String postCode;
    /** 企业地址 **/
    private String address;
    /** 企业邮箱 **/
    private String email;
    /** 企业网址 **/
    private String website;
    /** 企业证件类型 **/
    private CardType cardType;
    /** 税务登记证号 **/
    private String taxno;
    /** 营业执照号 **/
    private String licenceNo;
    /** 组织机构代码证 **/
    private String orgCode;
    /** 法人 **/
    private String corporation;
    /** 法人证件号码 **/
    private String corpNo;
    /** 法人身份证正面路径 **/
    private String corpFile;
    /** 法人身份证背面路径 **/
    private String corpFileOpp;
    /** 税务登记证文件路径 **/
    private String taxFile;
    /** 营业执照文件路径 **/
    private String licenceFile;
    /** 组织机构文件路径 **/
    private String orgCodeFile;
    /** 联系人 **/
    private String contact;
    /** 联系人电话 **/
    private String contPhone;
    /** 联系人职位 **/
    private String contTitle;
    /** 联系人邮箱 **/
    private String contEmail;
    /** 联系人地址 **/
    private String contAddress;
    /** 联系人邮编 **/
    private String contPost;
    /** 客户来源 **/
    private String custFrom;
    /** 客户经理 **/
    private String custMgr;
    /** 客户经理部门 **/
    private String custMgrDept;
    /** 是否授权人办理1-是0-否 **/
    private Long isDelegation;
    /** 委托人 **/
    private String signatory;
    /** 委托人身份证 **/
    private String signCertNo;
    /** 委托人证件照正面路径 **/
    private String signCertFile;
    /** 委托人证件照背面路径 **/
    private String signCertFileOpp;
    /** 状态 **/
    private String enterpriseStatus;
    /** 初次业务时间 **/
    private Date firstTime;
    /** 写入人 **/
    private Long inUser;
    /** 写入时间 **/
    private Date inTime;
    /** 初审人 **/
    private Long stexaUser;
    /** 初审时间 **/
    private Date stexaTime;
    /** 初审意见 **/
    private String stexaOpt;
    /** 复核人 **/
    private Long cvlexaUser;
    /** 复核时间 **/
    private Date cvlexaTime;
    /** 复核意见 **/
    private String cvlexaOpt;
    /** 备注 **/
    private String notes;
    /** 备注 **/
    private String remarks;
    public String getEnterpriseMemberId() {
        return enterpriseMemberId;
    }
    public void setEnterpriseMemberId(String enterpriseMemberId) {
        this.enterpriseMemberId = enterpriseMemberId;
    }
    public Long getCoopInstiId() {
        return coopInstiId;
    }
    public void setCoopInstiId(Long coopInstiId) {
        this.coopInstiId = coopInstiId;
    }
    public String getEnterpriseName() {
        return enterpriseName;
    }
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    public String getMcc() {
        return mcc;
    }
    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
    public String getMccList() {
        return mccList;
    }
    public void setMccList(String mccList) {
        this.mccList = mccList;
    }
    public Long getEnterpriseInsti() {
        return enterpriseInsti;
    }
    public void setEnterpriseInsti(Long enterpriseInsti) {
        this.enterpriseInsti = enterpriseInsti;
    }
    public Long getProvince() {
        return province;
    }
    public void setProvince(Long province) {
        this.province = province;
    }
    public Long getCity() {
        return city;
    }
    public void setCity(Long city) {
        this.city = city;
    }
    public Long getStreet() {
        return street;
    }
    public void setStreet(Long street) {
        this.street = street;
    }
    public String getZoneCode() {
        return zoneCode;
    }
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public CardType getCardType() {
        return cardType;
    }
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
    public String getTaxno() {
        return taxno;
    }
    public void setTaxno(String taxno) {
        this.taxno = taxno;
    }
    public String getLicenceNo() {
        return licenceNo;
    }
    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getCorporation() {
        return corporation;
    }
    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }
    public String getCorpNo() {
        return corpNo;
    }
    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }
    public String getCorpFile() {
        return corpFile;
    }
    public void setCorpFile(String corpFile) {
        this.corpFile = corpFile;
    }
    public String getCorpFileOpp() {
        return corpFileOpp;
    }
    public void setCorpFileOpp(String corpFileOpp) {
        this.corpFileOpp = corpFileOpp;
    }
    public String getTaxFile() {
        return taxFile;
    }
    public void setTaxFile(String taxFile) {
        this.taxFile = taxFile;
    }
    public String getLicenceFile() {
        return licenceFile;
    }
    public void setLicenceFile(String licenceFile) {
        this.licenceFile = licenceFile;
    }
    public String getOrgCodeFile() {
        return orgCodeFile;
    }
    public void setOrgCodeFile(String orgCodeFile) {
        this.orgCodeFile = orgCodeFile;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getContPhone() {
        return contPhone;
    }
    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }
    public String getContTitle() {
        return contTitle;
    }
    public void setContTitle(String contTitle) {
        this.contTitle = contTitle;
    }
    public String getContEmail() {
        return contEmail;
    }
    public void setContEmail(String contEmail) {
        this.contEmail = contEmail;
    }
    public String getContAddress() {
        return contAddress;
    }
    public void setContAddress(String contAddress) {
        this.contAddress = contAddress;
    }
    public String getContPost() {
        return contPost;
    }
    public void setContPost(String contPost) {
        this.contPost = contPost;
    }
    public String getCustFrom() {
        return custFrom;
    }
    public void setCustFrom(String custFrom) {
        this.custFrom = custFrom;
    }
    public String getCustMgr() {
        return custMgr;
    }
    public void setCustMgr(String custMgr) {
        this.custMgr = custMgr;
    }
    public String getCustMgrDept() {
        return custMgrDept;
    }
    public void setCustMgrDept(String custMgrDept) {
        this.custMgrDept = custMgrDept;
    }
    public Long getIsDelegation() {
        return isDelegation;
    }
    public void setIsDelegation(Long isDelegation) {
        this.isDelegation = isDelegation;
    }
    public String getSignatory() {
        return signatory;
    }
    public void setSignatory(String signatory) {
        this.signatory = signatory;
    }
    public String getSignCertNo() {
        return signCertNo;
    }
    public void setSignCertNo(String signCertNo) {
        this.signCertNo = signCertNo;
    }
    public String getSignCertFile() {
        return signCertFile;
    }
    public void setSignCertFile(String signCertFile) {
        this.signCertFile = signCertFile;
    }
    public String getSignCertFileOpp() {
        return signCertFileOpp;
    }
    public void setSignCertFileOpp(String signCertFileOpp) {
        this.signCertFileOpp = signCertFileOpp;
    }
    public String getEnterpriseStatus() {
        return enterpriseStatus;
    }
    public void setEnterpriseStatus(String enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }
    public Date getFirstTime() {
        return firstTime;
    }
    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }
    public Long getInUser() {
        return inUser;
    }
    public void setInUser(Long inUser) {
        this.inUser = inUser;
    }
    public Date getInTime() {
        return inTime;
    }
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    public Long getStexaUser() {
        return stexaUser;
    }
    public void setStexaUser(Long stexaUser) {
        this.stexaUser = stexaUser;
    }
    public Date getStexaTime() {
        return stexaTime;
    }
    public void setStexaTime(Date stexaTime) {
        this.stexaTime = stexaTime;
    }
    public String getStexaOpt() {
        return stexaOpt;
    }
    public void setStexaOpt(String stexaOpt) {
        this.stexaOpt = stexaOpt;
    }
    public Long getCvlexaUser() {
        return cvlexaUser;
    }
    public void setCvlexaUser(Long cvlexaUser) {
        this.cvlexaUser = cvlexaUser;
    }
    public Date getCvlexaTime() {
        return cvlexaTime;
    }
    public void setCvlexaTime(Date cvlexaTime) {
        this.cvlexaTime = cvlexaTime;
    }
    public String getCvlexaOpt() {
        return cvlexaOpt;
    }
    public void setCvlexaOpt(String cvlexaOpt) {
        this.cvlexaOpt = cvlexaOpt;
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
}
