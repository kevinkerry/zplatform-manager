/* 
 * CheckFilePojo.java  
 * 
 * version TODO
 *
 * 2015年12月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月25日 下午4:50:48
 * @since 
 */
public class CheckFileQuery implements Bean{
    /**交易序列号**/
    private String txnseqno;
    
    /**支付订单号（本方请求的订单号）**/
    private String payordno;
    
    /**商户编号**/
    private String merchno;
    
    /**支付交易流水号**/
    private String paytrcno;

    public String getTxnseqno() {
        return txnseqno;
    }

    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }

    public String getPayordno() {
        return payordno;
    }

    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }

    public String getMerchno() {
        return merchno;
    }

    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }

    public String getPaytrcno() {
        return paytrcno;
    }

    public void setPaytrcno(String paytrcno) {
        this.paytrcno = paytrcno;
    }
    
    
    
  
    

}
