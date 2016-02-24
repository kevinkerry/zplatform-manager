/* 
 * InsteadPayServiceTest.java  
 * 
 * version TODO
 *
 * 2015年12月22日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.spring;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.bean.CheckFileBean;
import com.zlebank.zplatform.manager.bean.CheckFileQuery;
import com.zlebank.zplatform.manager.service.iface.CheckFileService;
/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月22日 下午4:36:28
 * @since 
 */
public class InsteadPayServiceTest {
    private ApplicationContext context;
    @Test
    public void test() throws IllegalAccessException{
        context = new ClassPathXmlApplicationContext("/spring/*");
     
        CheckFileService cherc = context.getBean(CheckFileService.class);
        CheckFileQuery cfq=new CheckFileQuery();
      cfq.setMerchno("200000000000194");
      
     List<CheckFileBean> cf=  cherc.queryPaged(1, 10, cfq).getPagedResult();
     for(CheckFileBean pojo:cf){
            System.out.println(pojo.getMerchno());
            
            
        }
    }
}
