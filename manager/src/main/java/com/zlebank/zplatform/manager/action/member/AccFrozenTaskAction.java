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

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.AccountAmountQuery;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.FreezeAmountService;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.FrozenAccBean;
import com.zlebank.zplatform.manager.service.iface.AccAcctService;
import com.zlebank.zplatform.trade.common.page.PageVo;

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
    @Autowired
    private AccAcctService accAcctService;
    
    
    private AccountAmountQuery aaq;
    
    private FrozenAccBean query;
    
    private String id;
    
    
    
    public FrozenAccBean getQuery() {
		return query;
	}

	public void setQuery(FrozenAccBean query) {
		this.query = query;
	}

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
    	if(query==null){
    		query = new FrozenAccBean();
    	}
         String messg="";
       Map<String, Object> map = new HashMap<String, Object>();
       //accAcctService.f
       PageVo<FrozenAccBean> pagevo = accAcctService.queryByPage(query, this.getPage(), this.getRows());
       map.put("total", pagevo.getTotal());
       map.put("rows", pagevo.getList());
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
