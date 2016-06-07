package com.zlebank.zplatform.manger.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.dao.iface.IStatDAO;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;

public class TradeStatDAOTest {
    public ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("/spring/*");
    }
    @Test
    public void testTradeStat() {
        IStatDAO statDao = context.getBean(IStatDAO.class);
        List<TradeStatModel> list = statDao.queryTradeStat(null, null, null, null, null);
        Assert.assertTrue(list!=null&&list.size()==5);
    }
    
    public void testEntryCount() {
        IStatDAO statDao = context.getBean(IStatDAO.class);
        Map<String,Object> list = statDao.queryEntryCount("", "", null, 1, 10);
        Assert.assertTrue(list.containsKey("total"));
        System.out.println(list.get("total"));
    }
}
