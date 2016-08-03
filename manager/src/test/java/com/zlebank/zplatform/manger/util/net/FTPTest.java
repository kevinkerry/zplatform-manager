package com.zlebank.zplatform.manger.util.net;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.manager.util.net.FTPClientFactory;

public class FTPTest {
	
	@Test
	public void test(){
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/*");
		
		FTPClientFactory ftpClientFactory = context.getBean(FTPClientFactory.class);
		
		AbstractFTPClient ftp = ftpClientFactory.getFtpClient();
		try {
			ftp.upload("/member/enterprise/11101012414142/", "business.jpg", new File("d:/Lighthouse.jpg"));
			ftp.download("/member/enterprise/11101012414142/", "business.jpg", "d:/", "Lighthouse_new.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("d:/");
		File[] files = file.listFiles();
		for(File checkfile :files){
			System.out.println(checkfile.getName());
			if(checkfile.getName().equals("Lighthouse_new.jpg")){
				Assert.assertTrue(true);
				return;
			}
		}
		Assert.assertTrue(false);
	} 
}
