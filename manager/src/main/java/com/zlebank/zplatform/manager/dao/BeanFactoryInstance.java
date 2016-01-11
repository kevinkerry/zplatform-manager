package com.zlebank.zplatform.manager.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactoryInstance {
	private static BeanFactory beanFactory;
	
	synchronized public static BeanFactory getInstance(){
		if(beanFactory == null){
			beanFactory = new ClassPathXmlApplicationContext("applicationContextForTask.xml");
		}
		return beanFactory;
	}
	
	private BeanFactoryInstance(){
	}
	
}
