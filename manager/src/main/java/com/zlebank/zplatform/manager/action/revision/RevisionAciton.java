/* 
 * RevisionAciton.java  
 * 
 * version TODO
 *
 * 2015年12月31日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.RevisionBean;
import com.zlebank.zplatform.manager.bean.RevisionQuery;
import com.zlebank.zplatform.manager.service.iface.IRevisionService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月31日 上午11:00:21
 * @since 
 */
public class RevisionAciton extends BaseAction{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private  IRevisionService revision;
    
    private RevisionQuery  rq;
    
    private String txnsLogNo;
    
    
   public String getTxnsLogNo() {
        return txnsLogNo;
    }





    public void setTxnsLogNo(String txnsLogNo) {
        this.txnsLogNo = txnsLogNo;
    }





    public RevisionQuery getRq() {
        return rq;
    }





    public void setRq(RevisionQuery rq) {
        this.rq = rq;
    }





    public String getRevision(){
       return this.SUCCESS;
    }
    
    
    
    public void queryRevision(){
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<RevisionBean> pr = revision.queryPaged(page, pageSize,rq);
        try {
            List<RevisionBean> li = pr.getPagedResult();
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    public void saveRevision(){
        String messg="";
        boolean isok=false;
        Map< String, Object> map=new HashMap<String, Object>();
        Long userId = getCurrentUser().getUserId();
        /*try {
            revision.saveRevision(txnsLogNo, userId);
            isok=true;
           
            messg="操作成功";
        } catch (AccBussinessException e) {
          messg=e.getMessage();
        } catch (ManagerWithdrawException e) {
            messg=e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg=e.getMessage();
        }*/
        isok=false;
        messg="账户账务改造后暂时不支持此功能";
        map.put("falg", isok);
        map.put("messg", messg);
        json_encode(map);
        
    }
    
    
    
    
    
    
    

}
