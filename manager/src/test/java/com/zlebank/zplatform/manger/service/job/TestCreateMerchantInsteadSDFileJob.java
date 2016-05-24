package com.zlebank.zplatform.manger.service.job;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.job.CreateMerchantInsteadSDFileJob;

public class TestCreateMerchantInsteadSDFileJob {
    
    public ApplicationContext context;
    private CreateMerchantInsteadSDFileJob createMerchantInsteadSDFileJob;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("/spring/*");
        createMerchantInsteadSDFileJob = context.getBean(CreateMerchantInsteadSDFileJob.class);
    }
    
    @Test
    public void testCreateJob(){
        try {
            createMerchantInsteadSDFileJob.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
