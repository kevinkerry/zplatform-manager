package com.zlebank.zplatform.manager.action.industry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.service.iface.IndustryAccountGroupService;
import com.zlebank.zplatform.member.bean.CoopInsti;
import com.zlebank.zplatform.member.bean.InduGroupMemberBean;
import com.zlebank.zplatform.member.bean.InduGroupMemberQuery;
import com.zlebank.zplatform.member.bean.IndustryGroupBean;
import com.zlebank.zplatform.member.bean.IndustryGroupCreatBean;
import com.zlebank.zplatform.member.bean.IndustryGroupQuery;
import com.zlebank.zplatform.member.exception.ExistedDataException;
import com.zlebank.zplatform.member.exception.NotFoundDataException;
import com.zlebank.zplatform.member.service.CoopInstiService;
import com.zlebank.zplatform.member.service.IndustryGroupMemberService;
import com.zlebank.zplatform.member.service.IndustryGroupService;

public class AccountGroupAction extends BaseAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private IndustryGroupCreatBean industryGroup;
    private IndustryGroupQuery query;
    private IndustryGroupBean groupBean;
    private String memberId;
    private long groupId ;
    private String merchId;
    private InduGroupMemberQuery queryBean;
    private String groupCode;
    private long id;
    @Autowired
    private CoopInstiService coopInstiService;
    @Autowired
    private IndustryGroupMemberService industryGroupMemberService;
    Map<String,String> resultMap = new HashMap<String, String>();
    
    @Autowired
    private IndustryGroupService industryGroupService;
    @Autowired
    private IndustryAccountGroupService industryAccountGroupService;
    
    public String query(){
        return SUCCESS;
    }
    
    /**
     * 根据输入的商户编号查询商户名称和商户号对应的合作机构名称
     */
    public String  queryReverseInfo(){ 
        List<Map<String, Object>> resultList =  industryAccountGroupService.getMerchNameAndInstiName(merchId);
        Map<String, Object> nameMap =null;
        if(resultList.size()!=0 && resultList!=null){
            nameMap = resultList.get(0);
        }
        json_encode(nameMap);
        return null;
        
    }
    /**
     * 查询账户组信息
     */
    public String queryGroupInfo(){
        if(query==null){             
            query = new IndustryGroupQuery();
        }
        PagedResult<IndustryGroupBean>  accountGroupResult= industryGroupService.queryPaged(this.getPage(), this.getPage_size(),query);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", accountGroupResult.getTotal());
        try {
            map.put("rows", accountGroupResult.getPagedResult());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        json_encode(map);
        return null;
    }
    /**
     * 根据主键id查询一条账户组信息
     */
    public String queryOneGroup(){
            IndustryGroupBean industryGroupBean = industryGroupService.queryGroupByCodeOrId(groupId, null);
            String instiCode = industryGroupBean.getInstiCode();
            CoopInsti coopInsti = null;
            if(instiCode!=null && instiCode!=""){
                 coopInsti =  coopInstiService.getInstiByInstiCode(instiCode);
            }
            String instiName = coopInsti.getInstiName();
            if(instiName!=null && instiName!=""){
                industryGroupBean.setInstiName(instiName);
            }
            json_encode(industryGroupBean);
            return null;        
    }
    /**
     * 新增账户组信息
     * @throws NotFoundDataException 
     * @throws ExistedDataException 
     */
    public String save() {
        if(industryGroup==null){
            industryGroup = new IndustryGroupCreatBean();
        }
        String codeString = null;
        String flag = null;
        industryGroup.setInuser(getCurrentUser().getUserId()); 
        try {             
            try {
                if(industryGroup.getDrawable()==null||industryGroup.getDrawable()==""){
                    industryGroup.setDrawable("1");
                }
                codeString =  industryGroupService.addGroup(industryGroup);
            } catch (ExistedDataException e) {
                e.printStackTrace();
            } catch (NotFoundDataException e) {
                e.printStackTrace();
            }         
        } catch (AbstractBusiAcctException e) {
            e.printStackTrace();
        }
        if(StringUtil.isNotEmpty(codeString)){
             flag = "添加成功!";
        }else{
            flag = "添加失败!";
        }
        json_encode(flag);
        return null;
    } 
    /**
     * 修改账户组信息
     */
    @SuppressWarnings("unused")
    public String update(){

        String flag ="";
        IndustryGroupBean groupBean = null;
        if(industryGroup==null){
            flag="保存的数据为空";
        }else{           
            groupBean = BeanCopyUtil.copyBean(IndustryGroupBean.class,industryGroup);
            if(groupCode!=null&&groupCode!=""){
                 groupBean.setGroupCode(groupCode);           
            } 
            if(id!=0){
                groupBean.setId(id);    
           }                  
        }
        try {
            industryGroupService.updateGroup(groupBean);
            flag="修改成功!";
        } catch (NotFoundDataException e) {
            e.printStackTrace();
            flag="修改失败!";
        }
        json_encode(flag);
        return null;
    }

    /**
     * 查询账户列表
     * @return
     */
    @SuppressWarnings({"unused", "rawtypes", "unchecked"})
    public String queryAccountInfoList(){
        if(queryBean == null){
            queryBean = new InduGroupMemberQuery();
        }
        if(groupCode!=null){
            queryBean.setGroupCode(groupCode);
        }
        PagedResult<InduGroupMemberBean>  memberList =  industryGroupMemberService.queryPaged(this.getPage(), this.getPage_size(), queryBean);
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        Map<String, Object> resultMap = null;
        for(int i=0;i< memberList.getTotal();i++){
            Map map = new HashMap<String, Object>();
            try {
                map.put("MEMBERID", memberList.getPagedResult().get(i).getMemberId());
                map.put("ACCTCODE", memberList.getPagedResult().get(i).getAcctCode());
                map.put("ACCTCODENAME", memberList.getPagedResult().get(i).getAcctName());          
                map.put("USAGE", memberList.getPagedResult().get(i).getUsageDesc());
                map.put("TOTALBALANCE", memberList.getPagedResult().get(i).getTotalBalance());
                resultList.add(map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            
        }
        try {
            json_encode(resultList);
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return null;       
    }
    //----------------------------------------------------------------------------------------------------------------------
    

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public IndustryGroupCreatBean getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(IndustryGroupCreatBean industryGroup) {
        this.industryGroup = industryGroup;
    }

    public IndustryGroupQuery getQuery() {
        return query;
    }

    public void setQuery(IndustryGroupQuery query) {
        this.query = query;
    }

    public IndustryGroupBean getGroupBean() {
        return groupBean;
    }

    public void setGroupBean(IndustryGroupBean groupBean) {
        this.groupBean = groupBean;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public IndustryGroupService getIndustryGroupService() {
        return industryGroupService;
    }

    public void setIndustryGroupService(IndustryGroupService industryGroupService) {
        this.industryGroupService = industryGroupService;
    }

    public IndustryAccountGroupService getIndustryAccountGroupService() {
        return industryAccountGroupService;
    }

    public void setIndustryAccountGroupService(IndustryAccountGroupService industryAccountGroupService) {
        this.industryAccountGroupService = industryAccountGroupService;
    }

    public CoopInstiService getCoopInstiService() {
        return coopInstiService;
    }

    public void setCoopInstiService(CoopInstiService coopInstiService) {
        this.coopInstiService = coopInstiService;
    }

    public IndustryGroupMemberService getIndustryGroupMemberService() {
        return industryGroupMemberService;
    }

    public void setIndustryGroupMemberService(IndustryGroupMemberService industryGroupMemberService) {
        this.industryGroupMemberService = industryGroupMemberService;
    }

    public InduGroupMemberQuery getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(InduGroupMemberQuery queryBean) {
        this.queryBean = queryBean;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }


    
    
}
