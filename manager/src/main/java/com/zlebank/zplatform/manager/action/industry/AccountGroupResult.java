package com.zlebank.zplatform.manager.action.industry;

import java.io.Serializable;
import java.util.Date;

import com.zlebank.zplatform.commons.bean.Bean;



public class AccountGroupResult implements Serializable,Bean{


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    /**账户组编号**/
    private String groupCode;
    /**账户组名称**/
    private String groupName;
    /**主体客户名称**/
    private String majorName;
    /**合作机构名称**/
    private String instiName;
    /**创建日期**/
    private Date inTime;
    /**资金是否可提**/
    private String drawable;
    /**备注**/
    private String note;
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getMajorName() {
        return majorName;
    }
    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    public String getInstiName() {
        return instiName;
    }
    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }
    public Date getInTime() {
        return inTime;
    }
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
    public String getDrawable() {
        return drawable;
    }
    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    
}
