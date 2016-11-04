package com.zlebank.zplatform.manager.action.industry;

/**
 * 
 * Class Description
 *
 * @author jingxiaorui
 * @version
 * @date 2016年9月28日 下午4:35:52
 * @since
 */
public class IndustrAccountGroupQuery {

    /**账户组编号**/
    private String groupCode;
    /**主体客户名称**/
    private String memberName;
    /**创建日期（开始）**/
    private String intime1;
    /**创建日期（结束）**/
    private String intime2;
    /**账户组名称**/
    private String groupName;
    /**合作机构名称**/
    private String instiName;
    
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getIntime1() {
        return intime1;
    }
    public void setIntime1(String intime1) {
        this.intime1 = intime1;
    }
    public String getIntime2() {
        return intime2;
    }
    public void setIntime2(String intime2) {
        this.intime2 = intime2;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getInstiName() {
        return instiName;
    }
    public void setInstiName(String instiName) {
        this.instiName = instiName;
    }
    
}
