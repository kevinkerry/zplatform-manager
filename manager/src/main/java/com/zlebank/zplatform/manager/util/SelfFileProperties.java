package com.zlebank.zplatform.manager.util;

public class SelfFileProperties {
	
	/**
	 * file path
	 */
	public final static String SELF_FILE_FILE_SAVE_RELATIVE_PATH= "/cust/selffile";
	
	public final static String RECON_FILE_ROOT_DIR = "/merchantreconfile";//商户对账文件FTP服务器根路径
	
	public final static String RECON_FILE_CHANGE_LOG_FILE_NAME="reconfile_change_log.xml";
	/**
	 * file type
	 */
	public final static String FILE_TYPE_TXT="txt";
	public final static String FILE_TYPE_EXCEL="excel";
	public final static String FILE_TYPE_PDF="pdf";
	public final static String FILE_TYPE_XML="xml";
	
	/**
	 * file suffix
	 */
	public final static String FILE_SUFFIX_TXT="txt";
	public final static String FILE_SUFFIX_EXCEL="xls";
	public final static String FILE_SUFFIX_PDF="pdf";
	public final static String FILE_SUFFIX_XML="xml";
	
	/**
	 *cash trac type
	 */
	
	public final static String TRAC_TYPE_MERCH="01";
	public final static String TRAC_TYPE_INSTI="02";
	public final static String TRAC_TYPE_CASH_PREDICTION="03";
	
	/**
	 *  file seqno of a day,current date(format:yyyyMMdd)+sequence(4byte),for example:201212090001
	 *  to 
	 */
	public  volatile static String CEBSH_FILE_TRANS_SEQ="";
	public  volatile static String WZB_FILE_TRANS_SEQ="";
	
	/**
	 * separator
	 */
	public  volatile static String SEPARTOR_SIMPLE_ROW_SEPARATOR="\n";
	public  volatile static String SEPARTOR_COMPLEX_ROW_SEPARATOR="\r\n";
	public  volatile static String SEPARTOR_DEFAULT_UNIT_SEPARATOR="|";
}
