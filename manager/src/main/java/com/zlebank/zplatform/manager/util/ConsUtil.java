package com.zlebank.zplatform.manager.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zlebank.zplatform.action.constantModel.ConstantModel;
/**
 * 
 * 获取外部配置文件
 *
 * @author jingxr
 * @version
 * @date 2016年6月13日 上午10:50:09
 * @since
 */

public class ConsUtil {
	private static ConsUtil util;
	public ConstantModel constantModel;
	private ConsUtil(){
		try{
		    constantModel = new ConstantModel();
			String path = "/home/web/trade/";
			File file = new File(path+ "address.properties");
			if(!file.exists()){
			    path = getClass().getResource("/").getPath();
			    file = null;
			}
			Properties prop = new Properties();
			InputStream stream = null;
			stream = new BufferedInputStream(new FileInputStream(file));
			prop.load(stream);
			String merPortalUrl = prop.getProperty("merPortal_Url");
			constantModel.setMerPortalUrl(merPortalUrl);
			String merPortalWlanUrl = prop.getProperty("merPortalWlan_Url");
			constantModel.setMerPortalWlanUrl(merPortalWlanUrl);
			String mailProxyUrl = prop.getProperty("mailProxy_Url");
			constantModel.setMailProxyUrl(mailProxyUrl);
			String querySyncMerRequest = prop.getProperty("querySyncMerRequest");
			constantModel.setQuerySyncMerRequest(querySyncMerRequest);
			String querySyncMerNotify = prop.getProperty("querySyncMerNotify");
			constantModel.setQuerySyncMerNotify(querySyncMerNotify);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public static synchronized ConsUtil getInstance(){
		if(util==null){
			util = new ConsUtil();
		}
		return util;
	}
}
