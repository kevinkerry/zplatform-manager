package com.zlebank.zplatform.manager.dao.object;

import java.sql.Date;

import javax.persistence.Column;

public class IndustryGroupMemberModel {
           
    private Long id;
    private String uniqueTag;
    private String memberId;
    private String groupCode;
    private Long groupId;
    private Date intime;
    
    @Column(name="ID")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="UNIQUE_TAG")
    public String getUniqueTag() {
        return uniqueTag;
    }
    public void setUniqueTag(String uniqueTag) {
        this.uniqueTag = uniqueTag;
    }
    @Column(name="MEMBER_ID")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Column(name="GROUP_CODE")
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    @Column(name="GROUP_ID")
    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    @Column(name="INTIME")
    public Date getIntime() {
        return intime;
    }
    public void setIntime(Date intime) {
        this.intime = intime;
    }
    
    
}
