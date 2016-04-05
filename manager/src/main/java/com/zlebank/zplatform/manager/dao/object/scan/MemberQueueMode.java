package com.zlebank.zplatform.manager.dao.object.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author eason
 *
 */
@Entity
@Table(name = "T_MEMBER_QUEUE")
public class MemberQueueMode implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4256955842014387732L;
    /**
     * 
     */
    private Long id;
    //会员号
    private String memberId;
    //最大发送次数
    private int maxSendTimes;
    private String idCard;
    //当前发送次数
    private int sendTimes;
    //状态 00成功 01 不存在 02超时
    private String status;
    //是否保存到邮件激活表 00是 01否
    private String flag;
    //邮箱
    private String email;
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name = "MAX_SEND_TIMES")
    public int getMaxSendTimes() {
        return maxSendTimes;
    }
    public void setMaxSendTimes(int maxSendTimes) {
        this.maxSendTimes = maxSendTimes;
    }
    @Column(name = "SEND_TIMES")
    public int getSendTimes() {
        return sendTimes;
    }
    public void setSendTimes(int sendTimes) {
        this.sendTimes = sendTimes;
    }
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "IDCARD")
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    
    

}
