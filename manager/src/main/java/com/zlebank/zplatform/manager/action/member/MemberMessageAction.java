/* 
 * MemberMessage.java  
 * 
 * version TODO
 *
 * 2015年11月3日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.QuickpayCustBean;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;
import com.zlebank.zplatform.manager.service.iface.IMemberAccountService;
import com.zlebank.zplatform.manager.service.iface.IMemberMessageService;
import com.zlebank.zplatform.manager.service.iface.IQuickService;
import com.zlebank.zplatform.member.bean.PersonManager;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.member.service.PersonService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月3日 下午3:10:06
 * @since 
 */
public class MemberMessageAction extends BaseAction{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private MerchService merchService;
    @Autowired
    private ServiceContainer serviceContainer;
    @Autowired
    private PersonService personservice;
    @Autowired
    private IMemberMessageService mms;
    @Autowired
    private IQuickService qs;
    @Autowired
    private IMemberAccountService imas;
    /**个人会员Id**/
    private String qid;
    /**商户Id**/
    private String mid;
    /**会员id**/
    private String memberId;
    /**会员状态**/
    private String status;
    
    /**方法标示**/
    private String falg;
    
    
    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
    
    /**
     *商户会员基本信息
     */
    public void memberBaseMessage(){
        Long userId=getCurrentUser().getUserId();
        Map<String ,Object>    merchMap= serviceContainer.getMerchDetaService().queryOneMerchDeta(Long.parseLong(mid),userId);
      json_encode(merchMap);  
      
    }
    /**
     * 会员银行卡信息
     */
    public void memberBankInfo(){
        List<QuickpayCustBean> li=qs.getQuiceByMemberId(memberId);
        try {
            json_encode(li);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    
    
    
    /**
     * 会员扣率信息
     */
    public void memeberFee(){
      //  Map<String, Object>map= new HashMap<String, Object>();
        List<?> li=(List<?>) mms.getFeeByMid(memberId);
      if(li==null)
      li=new ArrayList<String>();
      try {
        json_encode(li);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
         
    }
    /**
     * 个人会员信息查询
     */
    public void personMessage(){
        PersonManager person=personservice.getPersonBeanByMemberId(memberId);
        json_encode(person); 
    }
        /**
         * 风控查询
         */
      public void queryRick(){
          List<?> li=mms.getRiskByMid(memberId);
          if(li==null)
              li=new ArrayList<String>();
          try {
            json_encode(li);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
      /**
       * 路由查询
       */
      public void queryRoute(){
          List<?> li=mms.getRouteByMid(memberId);
          if(li==null)
              li=new ArrayList<String>();
          try {
            json_encode(li);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
      /**
       * 银行卡解绑
       */
      public void unlockBank(){
         String messg="";
        boolean isok=qs.unlockQuice(qid, status);
          if(isok==false)
              messg="修改失败";
          messg="修改成功";
          json_encode(messg);
      }
      
      // 1  冻结  2 止入 3 止出 4 解止出 5 解冻 6 解止入
      public void memberOperation(){
          String messg="";
          if("1".equals(falg)){
              messg=freezeAcct();
          } else if("2".equals(falg)){
              messg= stopInAcct() ;
          }else if("3".equals(falg)){
              messg= stopOutAcct();
          }else if("4".equals(falg)){
              messg= reopenOutAcct();
          }else if("5".equals(falg)){
              messg= unFreezeAcct();
          }else if("6".equals(falg)){
              messg= reopenInAcct();
          }
          json_encode(messg);
      }
      
      /**
       * 根据会员Id止出账户
       */
      private String stopOutAcct() {
          String messg="";
          try {
           boolean isok=   imas.stopOutAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
         
      }
      /**
       * 解除止出
       */
      private String reopenOutAcct() {
          String messg="";
          try {
           boolean isok=   imas.reopenOutAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
      /**
       * 注销账户
       */
      private String logOutAcct() {
          String messg="";
          try {
           boolean isok=   imas.logOutAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
      /**
       * 止入账户
       */
      private String stopInAcct() {
          String messg="";
          try {
           boolean isok=   imas.stopInAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
      /**
       * 解除止入
       */
      private String reopenInAcct() {
          String messg="";
          try {
           boolean isok=   imas.reopenInAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
      /**
       * 解冻账户
       */
      private String unFreezeAcct() {
          String messg="";
          try {
           boolean isok=   imas.unFreezeAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
      /**
       * 冻结账户
       */
      private String freezeAcct() {
          String messg="";
          try {
           boolean isok=   imas.freezeAcctByMid(memberId);
           if(isok==true){
               messg="操作成功";
               }else{
               messg="操作失败";
               }
          } catch (AccBussinessException e) {
      
              messg=e.getMessage();
          } catch (IllegalAccessException e) {
              messg=e.getMessage();
          }
          return messg;
      }
    }
