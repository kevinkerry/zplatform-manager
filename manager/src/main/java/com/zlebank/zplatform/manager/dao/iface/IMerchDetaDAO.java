package com.zlebank.zplatform.manager.dao.iface;

import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;


public interface IMerchDetaDAO extends IBaseDAO<MerchDetaModel, Long>{ 
	/**
	 * check if email or cellphoneno is repeat in merchInsti
	 * @param email
	 * @param cellphone
	 * @param merchInsti
	 * @return true if repeat
	 */
	public boolean isRepeat(String email,String cellphone,String merchInsti);
	
	/**
     * check if there is other member has the same email or cellphoneno 
     * @param email
     * @param cellphone
     * @param merchInsti
     * @return true if has
     */
    public boolean hasSame(String memberId,String email,String cellphone);
	
	/**
	 * 
	 * @param bankNode
	 * @param bankCode
	 * @return
	 */
	public String queryBankName(String bankNode,String bankCode);
}
