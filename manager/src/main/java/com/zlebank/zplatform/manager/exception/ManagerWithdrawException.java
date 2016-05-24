/* 
 * ManagerWithdrawException.java  
 * 
 * version TODO
 *
 * 2015年12月3日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.exception;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月3日 下午2:32:54
 * @since 
 */
public class ManagerWithdrawException extends AbstractManagerException{
    
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    private String code;
    
    
    public ManagerWithdrawException(String code,Object ...param) {
        this.code=code;
        this.setParams(param);
    }
    
    public ManagerWithdrawException(String code) {
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
