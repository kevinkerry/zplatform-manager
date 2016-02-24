/* 
 * RevisionQuery.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
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
 * @date 2015年12月30日 下午2:41:33
 * @since 
 */
public class RevisionQuery implements Bean{
    /** 调账订单号 **/
    private String revisionno;
    
    /** 关联订单 **/
    private String txnslogid;
    
    /** 业务代码 **/
    private String busicode;

    public String getRevisionno() {
        return revisionno;
    }

    public void setRevisionno(String revisionno) {
        this.revisionno = revisionno;
    }

    public String getTxnslogid() {
        return txnslogid;
    }

    public void setTxnslogid(String txnslogid) {
        this.txnslogid = txnslogid;
    }

    public String getBusicode() {
        return busicode;
    }

    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    
    

}
