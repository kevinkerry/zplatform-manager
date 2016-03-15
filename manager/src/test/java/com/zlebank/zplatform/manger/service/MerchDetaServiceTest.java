package com.zlebank.zplatform.manger.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.bean.Enterprise;
import com.zlebank.zplatform.manager.bean.MerchDeta;
import com.zlebank.zplatform.manager.service.iface.IMerchDetaService;
import com.zlebank.zplatform.manger.util.RandomArugment;

public class MerchDetaServiceTest {

	public ApplicationContext context;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("/spring/*");
	}

	/*public void test() {
		context = new ClassPathXmlApplicationContext("/spring/*");
		IMerchDetaService merchDetaService = (IMerchDetaService) context
				.getBean("merchDetaService");
		List<?> setlcycleList = merchDetaService.querySetlcycleAll();
		Assert.assertNotNull(setlcycleList);
		Assert.assertTrue(setlcycleList.size() > 0);
	}*/

	
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
	@Test
	@Ignore
	public void testGetBean() {
        IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
        MerchDeta merch = merchDetaService.getBean(17);
        Assert.assertEquals(4, merch.getMember().getSelfId());
    }
	
	
	public void testUpload() {
	    IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
	    File file = new File("d:\\/Desert.jpg");
	    
	    //test if CertType is UNKONW
	    boolean isSucc = merchDetaService.upload(4, file.getName(), file, CertType.UNKONW);
        Assert.assertFalse(isSucc);
	    
	    //test if merhch is not exist
	     isSucc = merchDetaService.upload(8, file.getName(), file, CertType.BUSILICE);
	    Assert.assertFalse(isSucc);
        
        isSucc = merchDetaService.upload(11, file.getName(), file, CertType.BUSILICE);
        Assert.assertTrue(isSucc);
	}
	
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
	 
	public void testSaveChange() {
	    IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
	    long merchDetaApplyId = 11;
	    MerchDeta merchDeta = merchDetaService.getBean(merchDetaApplyId);
	    merchDeta.getMember().setAddress("123231322");
	    merchDeta.setBankNode(merchDeta.getBankNode()+","+merchDeta.getBankCode());
	    List<?> result = merchDetaService.saveChangeMerchDeta(merchDetaApplyId, merchDeta);
	    @SuppressWarnings("unchecked")
        Map<String, Object> res = (Map<String, Object>) result.get(0);
        System.out.println("RET:" + res.get("RET"));
        System.out.println("INFO:" + res.get("INFO"));
	    MerchDeta _merchDeta = merchDetaService.getBean(merchDetaApplyId+1);
	    Assert.assertEquals(merchDeta.getMember().getAddress(), _merchDeta.getMember().getAddress());
	}
	@Test
	public void testLoadMerchMK(){
	    IMerchDetaService merchDetaService = (IMerchDetaService) context
                .getBean("merchDetaService");
	    Map<String,Object> res = merchDetaService.loadMerchMk("200000000000630");
	    if(res.entrySet().isEmpty()){
	        Assert.fail();
	    } 
	}

	public MerchDeta nextNewMerch() {
		MerchDeta merch = new MerchDeta();
		Enterprise enterprise = new Enterprise();
		
		enterprise.setCoopInstiId(25L);
		enterprise.setEnterpriseName(RandomArugment.randomName("商户",15));
		enterprise.setEnterpriseInsti(0L);
		enterprise.setProvince(Long.valueOf("430000"));
		enterprise.setCity(Long.valueOf("430100"));
		enterprise.setStreet(Long.valueOf("430121"));
		enterprise.setAddress("");
		enterprise.setTaxno("1232131");
		enterprise.setLicenceNo("1231313");
		enterprise.setOrgCode("123213213");
		enterprise.setWebsite("http://123123.com");
		enterprise.setCorporation("张三");

		enterprise.setCorpNo("213212");
		enterprise.setContact("13123");
		enterprise.setPhone(RandomArugment.randomNumber(11));
		enterprise.setContTitle("");
		enterprise.setContEmail("");

		enterprise.setCustFrom("");
		enterprise.setCustMgr("");
		enterprise.setCustMgrDept("");
		enterprise.setSignatory("李四");
		
		enterprise.setPostCode("");
		enterprise.setEmail(RandomArugment.randomEmail());
		enterprise.setInUser(2L);
		enterprise.setContAddress("fsdfsdfsdfdsfsd");
        
        enterprise.setMcc("5611");
        enterprise.setMccList("5734");
        enterprise.setIsDelegation(0L);
        enterprise.setSignCertNo("321541415414114");
		
        merch.setSetlType("1");
		merch.setSetlCycle(1L);
        merch.setBankCode("402602000018");
        merch.setBankNode("402602000018");
        merch.setAccNum("621541414111254");
        merch.setAccName("A电商");
        merch.setCharge(Money.valueOf(100.00));
        merch.setDeposit(Money.valueOf(101.01));
        merch.setAgreemtStart(new Timestamp(new Date().getTime()));
        merch.setAgreemtEnd(new Timestamp(new Date().getTime()));
		merch.setPrdtVer("SP000003");
		merch.setFeeVer("99000001");
		merch.setSpiltVer("");
		merch.setRiskVer("11111112");
		merch.setRoutVer("10000001");
		merch.setParent("");
		merch.setNotes("");
		merch.setRemarks("");
		merch.setMember(enterprise);
		return merch;
	}
}
