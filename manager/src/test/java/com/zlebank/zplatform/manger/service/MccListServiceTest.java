package com.zlebank.zplatform.manger.service;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.dao.object.MccListModel;
import com.zlebank.zplatform.manager.service.iface.IMccListService;

public class MccListServiceTest {
	
	public ApplicationContext context;
	
	@Test
	public void test() {
	   Calendar.getInstance();
		context = new ClassPathXmlApplicationContext("/spring/*");
		IMccListService mccListService = (IMccListService) context    
				.getBean("mccListService");
		List<MccListModel> mccList = mccListService.findAll();
		Assert.assertNotNull(mccList);
		Assert.assertTrue(mccList.size() > 0);
	}	
}