/* 
 * TxnsLogServiceText.java  
 * 
 * version TODO
 *
 * 2015年12月31日 
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

import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月31日 下午2:52:41
 * @since 
 */
public class TxnsLogServiceText {
   public ApplicationContext context;
    
    private ITxnsLoService irs=null;
    @Before
    public void init(){
        context  = new ClassPathXmlApplicationContext("/spring/*");
        irs=context.getBean(ITxnsLoService.class);
    }
    @Test
    public void getTxnsLogByNo(){
        List<?> returnList=    irs.getTxnsLogById("1510219900000050");  
        for (int i = 0; i < returnList.size(); i++) {
         System.out.println(returnList);
            
            
        }
        
       
        
    }
    
    
    
    
}
