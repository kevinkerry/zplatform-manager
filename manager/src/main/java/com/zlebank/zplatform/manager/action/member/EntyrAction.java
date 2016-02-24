/* 
 * MemberAction.java  
 * 
 * version TODO
 *
 * 2015年10月12日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.AccEntry;
import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.AccEntryBean;
import com.zlebank.zplatform.manager.service.iface.IMemberService;
import com.zlebank.zplatform.member.bean.MemberQuery;
import com.zlebank.zplatform.member.exception.MemberBussinessException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年10月12日 下午7:39:24
 * @since 
 */
public class EntyrAction extends BaseAction{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    public IMemberService ims;
    @Autowired
    public BusinessServiec business;
    
    public List<Business> bus;
    
    public List<Business> getBus() {
        return bus;
    }

    public void setBus(List<Business> bus) {
        this.bus = bus;
    }

    public MemberQuery mq;

    public MemberQuery getMq() {
        return mq;
    }

    public void setMq(MemberQuery mq) {
        this.mq = mq;
    }
    
    
   public void queryTradeDetail(){
          String mesg;
          Map<String , Object> map=new HashMap<String, Object>();
          if(mq==null){
              mq=new MemberQuery();
          }

          if(StringUtil.isNotEmpty(mq.getType()))
          mq.setStatus(AccEntryStatus.fromValue( mq.getType()));
        try {
            PagedResult<AccEntry> pr = ims.queryTradeDetail(this.getPage(),
                    this.getRows(), mq);
            
           List<AccEntryBean> entryBean =new ArrayList<AccEntryBean>();
            List<AccEntry> li = pr.getPagedResult();
            if(!li.isEmpty()){
            for(AccEntry accen:li){
                AccEntryBean  entryb=BeanCopyUtil.copyBean(AccEntryBean.class, accen);
             entryb.setAmount(accen.getAmount().toYuan()); 
             entryb.setCrdr(accen.getCrdr().getCode());
             entryb.setStatus(accen.getStatus().getCode());
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String dateString = formatter.format(accen.getInTime());
             entryb.setInTime(dateString);
             entryBean.add(entryb);
            }
            }
            Long total=pr.getTotal();
            map.put("total", total);
            map.put("rows",entryBean);
            json_encode(map);
          
        } catch (IllegalAccessException e) {
            mesg = e.getMessage();
        }

        catch (MemberBussinessException e) {
            mesg = e.getMessage();
        }
        
    }
   
   public String queryEntry(){
       bus=business.getAllBusiness();
       return this.SUCCESS;
   }
   
}
