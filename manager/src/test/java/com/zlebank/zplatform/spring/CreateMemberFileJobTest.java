package com.zlebank.zplatform.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.job.CreateMemberFileJob;
import com.zlebank.zplatform.manager.job.SaveMemberQueueJob;

public class CreateMemberFileJobTest {
    
private ApplicationContext context;
    
    @Test
    public void test(){
        context = new ClassPathXmlApplicationContext("/spring/*");
        CreateMemberFileJob createMemberFileJob =(CreateMemberFileJob) context.getBean("createMemberFileJob");
        try {
            createMemberFileJob.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void testSendEmail(){
        context = new ClassPathXmlApplicationContext("/spring/*");
        SaveMemberQueueJob saveMemberQueueJob =(SaveMemberQueueJob) context.getBean("sendEmailJob");
        try {
            saveMemberQueueJob.execute();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
