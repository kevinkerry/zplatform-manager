package com.zlebank.zplatform.manager.util.net;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.zlebank.zplatform.commons.utils.net.ftp.AbstractFTPClient;
import com.zlebank.zplatform.commons.utils.net.ftp.ApacheFTPClient;
import com.zlebank.zplatform.manager.dao.iface.IFTPDAO;
import com.zlebank.zplatform.manager.dao.object.FTPModel;
/**
 * 
 * {@link AbstractFTPClient} client Factory. <br>It create instance of {@link AbstractFTPClient}.
 * It is not a singleton in design pattern,but it should be a bean of spring and 
 * it's a "singleton" which assured by spring.
 *
 * @author yangying
 * @version
 * @date 2015年12月18日 上午9:38:58
 * @since
 */
public class FTPClientFactory extends WebApplicationObjectSupport {
	protected ApplicationContext applicationContext;
	private final String serverName = "FTP_SERVER_PRODUCT";
	private final String module = "MANAGER";

	private void fetchApplicationContext() {
		if (applicationContext == null) {
			applicationContext = getApplicationContext();
		}
		if (applicationContext == null) {
			applicationContext = getWebApplicationContext();
		}
		if (applicationContext == null) {
			// TODO
			throw new RuntimeException();
		}
	} 

	/**
	 * get AbstractFTPClient which configure by properties file . The configure
	 * file is the properties file in java and suffix with .properties. The filePath is
	 * relative path,the root is classPath.
	 * 
	 * @param filePath
	 * @return
	 */
	public AbstractFTPClient getFtpClient(String filePath) {
		// TODO
		return null;
	}

	/**
	 * get AbstractFTPClient which configure by database
	 * 
	 * @return
	 */
	public AbstractFTPClient getFtpClient() {
		if (applicationContext == null) {
			fetchApplicationContext();
		}
		IFTPDAO FTPDAO;
		FTPDAO = (IFTPDAO) applicationContext.getBean("FTPDAO");

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("servername", serverName);
		variable.put("module", module);
		FTPModel ftpConfig = FTPDAO.findByProperty(variable).get(0);
		int port = Integer.parseInt(ftpConfig.getPort());
		AbstractFTPClient ftpClient = new ApacheFTPClient(ftpConfig.getUsers(),
				ftpConfig.getPwd(), ftpConfig.getIp(), port);
		return ftpClient;
	}
}
