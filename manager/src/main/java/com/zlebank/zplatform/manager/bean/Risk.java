/* 
 * Risk.java  
 * 
 * version TODO
 *
 * 2015年11月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 *风控查询条件
 *
 * @author yangpeng
 * @version
 * @date 2015年11月30日 上午11:26:25
 * @since 
 */
public class Risk implements Bean{
    /**商户号**/
    private String merchId;
    /**子商户号**/
    private String subMerchId;
    /**会员号**/
    private String memberId;
    /**业务code**/
    private String busiCode;
    /**金额**/
    private String txnAmt;
    /**卡类型**/
    private String cardType;
    /**卡号**/
    private String cardNo;
    public String getMerchId() {
        return merchId;
    }
    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }
    public String getSubMerchId() {
        return subMerchId;
    }
    public void setSubMerchId(String subMerchId) {
        this.subMerchId = subMerchId;
    }
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getBusiCode() {
        return busiCode;
    }
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
    public String getTxnAmt() {
        return txnAmt;
    }
    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCardNo() {
        return cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
    
    
    

}
