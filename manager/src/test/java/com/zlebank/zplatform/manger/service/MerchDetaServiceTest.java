package com.zlebank.zplatform.manger.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;

public class MerchDetaServiceTest {

	public ApplicationContext context;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("/spring/*");
	}

	public void test() {
		context = new ClassPathXmlApplicationContext("/spring/*");
		IMerchDetaService merchDetaService = (IMerchDetaService) context
				.getBean("merchDetaService");
		List<?> setlcycleList = merchDetaService.querySetlcycleAll();
		Assert.assertNotNull(setlcycleList);
		Assert.assertTrue(setlcycleList.size() > 0);
	}

	
	public void testSaveMerchDeta() {
		IMerchDetaService merchDetaService = (IMerchDetaService) context
				.getBean("merchDetaService");
		List<?> result = merchDetaService.saveMerchDeta(nextNewMerch());
		@SuppressWarnings("unchecked")
		Map<String, Object> res = (Map<String, Object>) result.get(0);
		System.out.println("RET:" + res.get("RET"));
		System.out.println("INFO:" + res.get("INFO"));
		if (!res.get("RET").equals("succ")) {
			Assert.fail();
		}
	}
	
	
	public void testUpload() {
	    IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
	    File file = new File("d:\\/Desert.jpg");
	    
	    //test if CertType is UNKONW
	    boolean isSucc = merchDetaService.upload(372, file.getName(), file, CertType.UNKONW);
        Assert.assertFalse(isSucc);
	    
	    //test if merhc is not exist
	     isSucc = merchDetaService.upload(2214145, file.getName(), file, CertType.BUSILICE);
	    Assert.assertFalse(isSucc);
        
        isSucc = merchDetaService.upload(372, file.getName(), file, CertType.BUSILICE);
        Assert.assertTrue(isSucc);
	}
	@Test
	public void testDownLoad() {
        IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
        
        //test if file exist in ftp
        String path = merchDetaService.downloadFromFtp(366, "d:\\download", CertType.BUSILICE,false);
        Assert.assertEquals("d:\\download/366/busilice.jpg", path);
        
        //test if file is exist in local
        path = merchDetaService.downloadFromFtp(366, "d:\\download", CertType.BUSILICE,false);
        Assert.assertEquals("d:\\download/366/busilice.jpg", path);
        
        //test if file is upload
        path = merchDetaService.downloadFromFtp(358, "d:\\download", CertType.TAXREGCERT,false);
        Assert.assertEquals("", path);
        
      //test if file is not exist in ftp or in local(to test this ,delete flie from local and ftp)
        path = merchDetaService.downloadFromFtp(366, "d:\\download", CertType.CORPFILE_FACE,false);
        Assert.assertEquals(null, path);
    }

	public MerchDetaModel nextNewMerch() {
		MerchDetaModel merch = new MerchDetaModel();

		merch.setMerchname(randomMerchName());
		merch.setMerchinsti("");
		merch.setProvince("430000");
		merch.setCity("430100");
		merch.setStreet("430121");
		merch.setAddress("");
		merch.setTaxno("1232131");
		merch.setLicenceno("1231313");
		merch.setOrgcode("123213213");
		merch.setWebsite("http://123123.com");
		merch.setCorporation("张三");

		merch.setCorpno("213212");
		merch.setContact("13123");
		merch.setContphone("");
		merch.setConttitle("");
		merch.setContemail("");

		merch.setCustfrom("");
		merch.setCustmgr("");
		merch.setCustmgrdept("");
		merch.setSignatory("李四");
		merch.setSetlcycle("1");
		merch.setBankcode("402602000018");
		merch.setBanknode("402602000018");
		merch.setAccnum("621541414111254");

		merch.setAccname("A电商");
		merch.setCharge("100");
		merch.setDeposit("101");
		merch.setAgreemtStart(new Timestamp(new Date().getTime()));
		merch.setAgreemtEnd(new Timestamp(new Date().getTime()));
		merch.setPostcode("");
		merch.setEmail(randomEmail());
		merch.setInuser("2");
		merch.setSecretKey("1");
		merch.setPrdtver("SP000003");
		merch.setFeever("99000001");
		merch.setSpiltver("");
		merch.setRiskver("11111112");
		merch.setRoutver("10000001");
		merch.setCashver("00000008");
		merch.setParent("");
		merch.setNotes("");
		merch.setRemarks("");
		merch.setContaddress("fsdfsdfsdfdsfsd");
		merch.setSetltype("1");
		merch.setMcc("5611");
		merch.setMcclist("5734");
		merch.setIsDelegation(1);
		merch.setSignCertNo("321541415414114");
		merch.setCellPhoneNo(randomNumber(11));
		return merch;
	}

	private String randomNumber(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	private String randomEmail(){
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int preLength = random.nextInt(10);
		String pre = RandomStringUtils.randomAlphabetic(preLength);
		int suffixOperateLength = random.nextInt(5);
		String suffixOperate = RandomStringUtils.randomAlphabetic(suffixOperateLength);
		
		sb.append(pre);
		sb.append("@");
		sb.append(suffixOperate);
		sb.append(".");
		sb.append("com");
		return sb.toString();
	}
	
	private String randomMerchName(){
		Random random = new Random();
		int length = random.nextInt(15);
		String name = RandomStringUtils.random(length);
		 
		return "商户"+name;
	} 
}
