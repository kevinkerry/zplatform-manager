/* 
 * SubjectRuleService.java  
 * 
 * version TODO
 *
 * 2015年10月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.acc.bean.SubjectAccountRule;
import com.zlebank.zplatform.acc.exception.AccBussinessException;

/**
 * 分路规则service
 *
 * @author yangpeng
 * @version
 * @date 2015年10月28日 下午4:03:51
 * @since 
 */
public interface ISubjectRuleService {
    /**
     * 试算平衡
     * @param li
     * @return
     */
    public  boolean balance(SubjectAccountRule asr) throws AccBussinessException;
    
}
