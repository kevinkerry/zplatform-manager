package com.zlebank.zplatform.manger.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.service.iface.InsteadPayDetailService;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailBean;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailQuery;

public class InsteadPayServiceTest {
    public ApplicationContext context;
    private InsteadPayDetailService insteadPayDetailService;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("/spring/*");
        insteadPayDetailService = (InsteadPayDetailService)context.getBean(InsteadPayDetailService.class);
    }
    
    @Test
    public void testQueryInsteadPayDetail(){
        String orderId = "123454610001";
        InsteadPayDetailQuery insteadPayDetailQuery = new InsteadPayDetailQuery();
        insteadPayDetailQuery.setOrderId(orderId);
        PagedResult<InsteadPayDetailBean>  reslut = insteadPayDetailService.queryPaged(1, 10, insteadPayDetailQuery);
        Assert.assertEquals(1, reslut.getTotal());
        try {
            List<InsteadPayDetailBean> li = reslut.getPagedResult();
            Assert.assertEquals(orderId, li.get(0).getOrderId());
             
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
