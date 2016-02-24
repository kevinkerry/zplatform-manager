/* 
 * AccFrozenTaskAction.java  
 * 
 * version TODO
 *
 * 2015年10月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.FreezeAmountService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.AccountAmountMan;

/**
 *冻结信息查询
 *
 * @author yangpeng
 * @version
 * @date 2015年10月21日 上午9:32:34
 * @since 
 */
public class AccFrozenTaskAction extends BaseAction{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
    @Autowired
    private FreezeAmountService fas;
    
    private AccountAmountQuery aaq;
    
    private String id;
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountAmountQuery getAaq() {
        return aaq;
    }

    public void setAaq(AccountAmountQuery aaq) {
        this.aaq = aaq;
    }

    public String queryAcc(){
        
        return this.SUCCESS;
    }
    
    public void  freezeAmount(){
    String messg="";
        int page=  this.getPage();
       int pageSize= this.getRows();
       Map<String, Object> map = new HashMap<String, Object>();
       PagedResult<AccountAmount> pr  =   fas.queryPaged(page, pageSize, aaq);
        try {
            List<AccountAmount> li=pr.getPagedResult();
            List<AccountAmountMan> accountam=new ArrayList<AccountAmountMan>();
           if(li!=null){
            for(AccountAmount acca:li){
                AccountAmountMan aam=BeanCopyUtil.copyBean(AccountAmountMan.class, acca);
             aam.setStartTime(aam.getFrozenSTime()==null?null:DateUtil.formatDateTime(DEFAULT_TIME_STAMP_FROMAT,aam.getFrozenSTime()));
             aam.setEndTime(aam.getUnfrozenTime()==null?null:DateUtil.formatDateTime(DEFAULT_TIME_STAMP_FROMAT,aam.getUnfrozenTime()));
                aam.setFrozenBalance(acca.getFrozenBalance()==null?null:acca.getFrozenBalance().toYuan());
               aam.setStatus(acca.getStatus()==null?null:acca.getStatus().getCode());
               accountam.add(aam);
           
            }
           }
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", accountam);
          
        } catch (IllegalAccessException e) {
           messg=e.getMessage();
           map.put("messg", messg);
        }
        json_encode(map);
    }
    /**
     * 解冻资金
     */
    public void unFreezeAmount(){
       String messg="";
       Map<String, Object> map = new HashMap<String, Object>();
       if(StringUtil.isEmpty(id)){
           messg="参数错误Id为空";
       }else{
        //解冻前验证状态 
           AccountAmount aa= fas.getAccountByID(Long.valueOf(id));
           //验证状态
           if(aa.getStatus().getCode().equals("00")){
               messg="该记录不是最新，请刷新页面后重试";
           }else{
        aa.setId(id==null?null:Long.valueOf(id));
        try {
            fas.unFreezeAmount(aa, false);
            messg="操作成功";
        } catch (AccBussinessException e) {
            messg=e.getMessage();
        }
    }}
       map.put("messg", messg);
       json_encode(map);
       
    }
    
}
