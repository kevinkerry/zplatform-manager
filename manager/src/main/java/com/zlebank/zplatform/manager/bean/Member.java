package com.zlebank.zplatform.manager.bean;

import java.util.Date;

import com.zlebank.zplatform.member.bean.enums.MemberStatusType;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.bean.enums.RealNameLvType;

/**
 * 
 * Member domain model
 *
 * @author yangying
 * @version
 * @date 2016年3月4日 上午10:49:38
 * @since 1.3.0
 */
public class Member {
    /** 主键，标示，申请记录Id **/
    private long selfId;
    /** 在用会员ID **/
    private long memId;
    /** 会员ID **/
    private String memberId;
    /** 合作机构 **/
    private String instiCode;
    /** 会员昵称 **/
    private String memberName;
    /** 登录名 **/
    private String loginName;
    /** 登录密码 **/
    private String pwd;
    /** 支付密码 **/
    private String payPwd;
    /** 实名等级，01-未实名，02-姓名+身份证,03-银行卡校验,04-证件审核 **/
    private RealNameLvType realnameLv;
    /** 手机 **/
    private String phone;
    /** 邮箱 **/
    private String email;
    /** 会员类型，01-个人，02-企业 **/
    private MemberType memberType;
    /** 会员状态，00-正常，02-冻结，99-注销 **/
    private MemberStatusType status;
    /** 注册认证，01-手机认证，02-邮箱认证，03-Both **/
    private String registerIdent;
    /** 锁定时间 **/
    private Date lockTime;
    /** 会员注册时间 **/
    private Date intTme;
    /** 修改时间 **/
    private Date upTime;
    
    public long getSelfId() {
        return selfId;
    }
    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }
    public long getMemId() {
        return memId;
    }
    public void setMemId(long memId) {
        this.memId = memId;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getInstiCode() {
        return instiCode;
    }
    public void setInstiCode(String instiCode) {
        this.instiCode = instiCode;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getPayPwd() {
        return payPwd;
    }
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }
    public RealNameLvType getRealnameLv() {
        return realnameLv;
    }
    public void setRealnameLv(RealNameLvType realnameLv) {
        this.realnameLv = realnameLv;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public MemberType getMemberType() {
        return memberType;
    }
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    public MemberStatusType getStatus() {
        return status;
    }
    public void setStatus(MemberStatusType status) {
        this.status = status;
    }
    public String getRegisterIdent() {
        return registerIdent;
    }
    public void setRegisterIdent(String registerIdent) {
        this.registerIdent = registerIdent;
    }
    public Date getLockTime() {
        return lockTime;
    }
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    public Date getIntTme() {
        return intTme;
    }
    public void setIntTme(Date intTme) {
        this.intTme = intTme;
    }
    public Date getUpTime() {
        return upTime;
    }
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}
