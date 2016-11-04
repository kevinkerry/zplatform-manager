package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.iface.IndustryAccountGroupDAO;
import com.zlebank.zplatform.manager.dao.object.IndustryGroupModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IndustryAccountGroupService;





@Service("industryAccountGroupService")
public class IndustryAccountGroupServiceImpl extends BaseServiceImpl<IndustryGroupModel, String> implements IndustryAccountGroupService{

    @Autowired
    private IndustryAccountGroupDAO industryAccountGroupDAO;
    
    @Override
    public List<Map<String, Object>> getMerchNameAndInstiName(String merchId) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list =  (List<Map<String, Object>>) getIndustryAccountGroupDAO().executeBySQL("select b.insti_name as INSTINAME ,b.insti_code as INSTICODE from t_enterprise_deta a join t_coop_insti b on a.coop_insti_id=b.id where a.member_id=?", 
                new Object[]{merchId});        
        return    list;
    }

    /**
     * 根据会员号获取科目号、账户名、用途、账户总金额信息
     *
     * @param memberid
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getmemberInfo(String memberid) {        
        List<Map<String, Object>> list =  (List<Map<String, Object>>) getIndustryAccountGroupDAO().executeBySQL("select a.business_actor_id memberid,a.acct_code acctcode,a.acct_code_name acctcodename,a.total_balance totalbalance,b.usage usage  from t_acc_acct a left join t_acc_busiacct b on a.acct_code=b.busiacct_code where a.business_actor_id=? ", 
                new Object[]{memberid});        
        return    list;
    }
    
    /**
     * 获取所有账户组的代码和名称
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAllGroupCodeAndName() {
        List<Map<String, Object>> list =  (List<Map<String, Object>>) getIndustryAccountGroupDAO().executeBySQL("select t.group_name as groupname,t.group_code as groupcode  from t_industry_group t   ", 
                new Object[]{});        
        return    list;
    }
    @Override
    public IBaseDAO<IndustryGroupModel, String> getDao() {
     
        return null;
    }

    public IndustryAccountGroupDAO getIndustryAccountGroupDAO() {
        return industryAccountGroupDAO;
    }

    public void setIndustryAccountGroupDAO(IndustryAccountGroupDAO industryAccountGroupDAO) {
        this.industryAccountGroupDAO = industryAccountGroupDAO;
    }




    
}
