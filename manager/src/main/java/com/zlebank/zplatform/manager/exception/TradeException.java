/* 
 * BaseException.java  
 * 
 * version TODO
 *
 * 2015年9月6日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.exception;

import com.zlebank.zplatform.commons.exception.AbstractDescribeException;

/**
 * Class Description
 *
 * @author guojia
 * @version
 * @date 2015年9月6日 下午2:43:58
 * @since 
 */
public class TradeException extends AbstractDescribeException{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3716682104238215841L;
    private String code;
    public TradeException(String code,Object ...param) {
        this.code=code;
        this.setParams(param);
    }
    public TradeException(String code) {
        this.code=code;
    }
    /**
     *
     * @return
     */
    @Override
    public String getCode() {
        return code;
    }

}
