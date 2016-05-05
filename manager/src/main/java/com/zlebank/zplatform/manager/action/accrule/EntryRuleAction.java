/* 
 * EntryRuleAction.java  
 * 
 * version TODO
 *
 * 2015年10月23日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.accrule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.bean.SubjectQuery;
import com.zlebank.zplatform.acc.bean.SubjectRule;
import com.zlebank.zplatform.acc.bean.enums.CRDRType;
import com.zlebank.zplatform.acc.bean.enums.RuleStatusType;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.acc.service.SubjectRuleService;
import com.zlebank.zplatform.acc.service.SubjectService;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.service.iface.ISubjectRuleService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月23日 下午12:01:11
 * @since 
 */
public class EntryRuleAction extends BaseAction{
   
    @Autowired
    private SubjectRuleService srs;
    @Autowired
    public BusinessServiec business;
    @Autowired
    private SubjectService subject;
    @Autowired
    private ISubjectRuleService sub;
    private SubjectAccountRule sar;
    
    private String accCode;
    
    private SubjectRule sr;
    
    private String id;
    
    private String falg;
    
    
  
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public SubjectRule getSr() {
        return sr;
    }

    public void setSr(SubjectRule sr) {
        this.sr = sr;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public SubjectAccountRule getSar() {
        return sar;
    }

    public void setSar(SubjectAccountRule sar) {
        this.sar = sar;
    }

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    public void queryRule(){
      Map<String, Object> m=new HashMap<String, Object>();
        try {
            int page= this.getPage();
            int pageSize=getRows();
              Long count=  srs.getcount(sar);
              List<Map<String, Object>> li= srs.getRuleBySar(sar, page, pageSize);
              if(li==null){
                  li=new ArrayList<Map<String,Object>>();
              }
           m.put("total", count);
           m.put("rows",li);
           
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        json_encode(m);
    }
    
    public String getQueryRule(){
      return   Action.SUCCESS;
        
    }
    /**
     * 得到所有交易类型
     */
    public void getBusiness(){
        List<Business> li= business.getAllBusiness();
        try {
            json_encode(li);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 通过科目code得到科目
     * @return
     */
    public void getSubjectByCode(){
       String str="";
        try {
            SubjectQuery sq=     subject.subjectByCode(accCode);
        
        } catch (AccBussinessException e) {
         str=e.getMessage();
        }
        json_encode(str); 
    }
    /**
     * 新增规则
     */
    public void addRuel(){
      String messg="";
        if(sr==null){
            messg="参数不正确，请重新输入";
        if(StringUtil.isEmpty(sr.getCrdrType())||StringUtil.isEmpty(sr.getActionStatus()))
            messg="参数不正确，请重新输入";
        }else{
            try {
                sr.setCrdr(CRDRType.fromValue(sr.getCrdrType()));
                sr.setStatus(RuleStatusType.fromValue(sr.getActionStatus()));
                sr.setFlag(!sr.getFlag().equals("99")?sr.getFlag():sr.getFlags());
             Long ruleId=   srs.addSubjectRule(sr,getCurrentUser().getUserId());
             if(ruleId!=null){
                 messg="true";
             }else{
                 messg="新增失败";
             }
            } catch (AccBussinessException e) {
              messg=e.getMessage();
            }
        }
        json_encode(messg); 
    }
    
        public void stopOrStarRule(){
       String messg="操作成功";
      //操作之前先检查数据的有效性
       try { 
       if(id!=null){
         SubjectRule subr=  srs.subjectRuleByID(Long.valueOf(id));
          if("3".equals(falg)){
              if(!subr.getStatus().getCode().equals("99")){
            srs.deleteSubjectRule(Long.valueOf(id));
              }else{
                  messg="数据不是最新，请刷新后重试";
              }
          }else if("2".equals(falg)){
              if(subr.getStatus().getCode().equals("00")){
              srs.stopSubjectRule(Long.valueOf(id),getCurrentUser().getUserId());
              }else{
                  messg="数据不是最新，请刷新后重试";
              }
          }else if("4".equals(falg)){
              if(subr.getStatus().getCode().equals("01")){
              srs.enableSubjectRule(Long.valueOf(id),getCurrentUser().getUserId());
              }else{
                  messg="数据不是最新，请刷新后重试";
              }
              }else{
              messg="参数错误";
          }
            
        }else{
            messg="参数错误";
        }
         } catch (AccBussinessException e) {
          messg=e.getMessage();
         } catch (NumberFormatException e) {
            messg=e.getMessage();
         }
       json_encode(messg); 
    }
    public void update(){
        Map<String, Object> m=new HashMap<String, Object>();
     
             int page= this.getPage();
            int pageSize= this.getPage_size();
              Long count=  srs.getcount(sar);
              try {
                List<Map<String, Object>> li= srs.getRuleBySar(sar, page, pageSize);
             if(!li.isEmpty()){
                m.put("li",  li.get(0));}
              } catch (AccBussinessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
              json_encode(m); 
              
              
    }
        
    public void updates(){
        String messg="true";
        try {
            if(sr!=null){
              sr.setCrdr(CRDRType.fromValue(StringUtil.isNotEmpty(sr.getCrdrType())?sr.getCrdrType():null));  
              sr.setStatus(RuleStatusType.fromValue(StringUtil.isNotEmpty(sr.getActionStatus())?sr.getActionStatus():null));
              
            
            srs.updateSubjectRule(sr, getCurrentUser().getUserId());
            }
        } catch (AccBussinessException e) {
            messg="操作失败"+e.getMessage();
        }
        json_encode(messg); 
    }
    
    public  void balance(){
        String messg="";
        try {
         boolean isok=   sub.balance(sar);
         if(isok==true){
             messg="规则平衡";
         }else{
             messg="规则不平衡";
         }
        } catch (AccBussinessException e) {
               messg=e.getMessage();
            e.printStackTrace();
        }
        json_encode(messg); 
    }
        
        
}
