/* 
 * RevisionTest.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manger.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.bean.RevisionBean;
import com.zlebank.zplatform.manager.bean.RevisionQuery;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IRevisionService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月30日 下午4:29:27
 * @since 
 */
public class RevisionTest {
   
    public ApplicationContext context;
    
    private IRevisionService irs=null;
    @Before
    public void init(){
        context  = new ClassPathXmlApplicationContext("/spring/*");
        irs=context.getBean(IRevisionService.class);
    }
    
    @Test
    public void queryRevision(){
       RevisionQuery rq=new RevisionQuery();
        rq.setBusicode("30000001");
        rq.setRevisionno("");
        rq.setTxnslogid("");
     
        PagedResult<RevisionBean> pr =    irs.queryPaged(1, Integer.MAX_VALUE, rq); 
        try {
            List<RevisionBean> li =pr.getPagedResult();
            
            for(RevisionBean rb:li){
                System.out.println(rb.getBusicode());
                
            }
            
            
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
      
    }
    
    /**
     * 手工调账
     */
    @Test
    public  void save(){
        try {
            irs. saveRevision("1512309900051416", 2L);
        } catch (AccBussinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ManagerWithdrawException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AbstractBusiAcctException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    

}
