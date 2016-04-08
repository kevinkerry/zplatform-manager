package com.zlebank.zplatform.manager.dao.object.scan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    //过期时间 单位秒
    private String expirationTime;
    //激活状态00激活成功01激活失败
    private String acriveStatus;
    @GenericGenerator(name = "id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "T_C_PRIMAY_KEY"),
            @Parameter(name = "value_column_name", value = "NEXT_ID"),
            @Parameter(name = "segment_column_name", value = "KEY_NAME"),
            @Parameter(name = "segment_value", value = "T_TXNS_REFUSE_ID"),
            @Parameter(name = "increment_size", value = "1"),
            @Parameter(name = "optimizer", value = "pooled-lo") })
    @Id
    @GeneratedValue(generator = "id_gen")
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
    @Column(name = "EXPIRATION_TIME")
    public String getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
    @Column(name = "ACTIVE_STATUS")
    public String getAcriveStatus() {
        return acriveStatus;
    }
    public void setAcriveStatus(String acriveStatus) {
        this.acriveStatus = acriveStatus;
    }
    
    
    

}
