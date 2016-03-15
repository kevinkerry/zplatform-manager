package com.zlebank.zplatform.manager.dao.iface;

import com.zlebank.zplatform.manager.dao.object.PojoMerchDetaApply;

public interface IMerchDetaDAO extends IBaseDAO<PojoMerchDetaApply, Long>{ 
	/**
	 * check if email or cellphoneno is repeat in coopInsti
	 * @param email
	 * @param cellphone
	 * @param merchInsti
	 * @param coopInstiId
	 * @return true if repeat
	 */
	public boolean isRepeat(String email, String cellphone,String coopInstiId);
	
	/**
     * check if there is other member has the same email or cell phone No in a coopInsti
     * @param email
     * @param cellphone
     * @param merchInsti
     * @param coopInstiId
     * @return true if has
     */
    public boolean hasSame(String memberId,String email,String cellPhoneNo,long coopInstiId);
	
	/**
	 * 
	 * @param bankNode
	 * @param bankCode
	 * @return
	 */
	public String queryBankName(String bankNode,String bankCode);
}
