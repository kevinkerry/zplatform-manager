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
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.enums.AccEntryStatus;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.AccEntryBean;
import com.zlebank.zplatform.manager.service.iface.AccAcctService;
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
public class EntryAction extends BaseAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    public IMemberService ims;
    @Autowired
    public BusinessServiec business;

    public List<Business> bus;

    public QueryAccount queryAccount;
    public BusiAcctQuery busiAcctQuery;
    @Autowired
    public AccAcctService accAcctService;
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

    public void queryTradeDetail() {
        String mesg ="";
        Map<String, Object> map = new HashMap<String, Object>();
        if (mq == null) {
            mq = new MemberQuery();
        }

        if (StringUtil.isNotEmpty(mq.getType()))
            mq.setStatus(AccEntryStatus.fromValue(mq.getType()));
        try {
            PagedResult<AccEntry> pr = ims.queryTradeDetail(this.getPage(),
                    this.getRows(), mq);

            List<AccEntryBean> entryBean = new ArrayList<AccEntryBean>();
            List<AccEntry> li = pr.getPagedResult();
            if (!li.isEmpty()) {
                for (AccEntry accen : li) {
                    AccEntryBean entryb = BeanCopyUtil.copyBean(
                            AccEntryBean.class, accen);
                    entryb.setAmount(accen.getAmount().toYuan());
                    entryb.setAcctCode(accen.getAcctCode());//科目号
                    entryb.setCrdr(accen.getCrdr().getCode());//借or贷
                    entryb.setStatus(accen.getStatus().getCode());//记账状态                    
                    SimpleDateFormat formatter = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(accen.getInTime());
                    entryb.setInTime(dateString);//分录时间
                    
                   String acccodeString = accen.getAcctCode();
                    if(acccodeString !=null || acccodeString !=""){
                        @SuppressWarnings("unchecked")
                        List<Map<String, String>> list = (List<Map<String, String>>) accAcctService.getAccAcctInfo(acccodeString);                       
                        entryb.setAcctCodeName(list.get(0).get("ACCT_CODE_NAME"));
                        entryb.setMemberid(list.get(0).get("BUSINESS_ACTOR_ID"));
                        entryb.setCrdr(list.get(0).get("CRDR"));  
                        @SuppressWarnings("unchecked")
                        List<Map<String, String>> listUsage = (List<Map<String, String>>) accAcctService.getAccBusiacctInfo(acccodeString); 
                        if(listUsage!=null && listUsage.size()!=0){
                            entryb.setUsage(listUsage.get(0).get("USAGE"));
                        }                      
                    }
                    entryBean.add(entryb);
                }
            }
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", entryBean);
            json_encode(map);
        } catch (IllegalAccessException e) {
            mesg = e.getMessage();
            json_encode(mesg);
        } catch (MemberBussinessException e) {
            mesg = e.getMessage();
            json_encode(mesg);
        }
    }

    public String queryEntry() {
        bus = business.getAllBusiness();
        return SUCCESS;
    }
}
