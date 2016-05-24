/* 
 * CheckFileAction.java  
 * 
 * version TODO
 *
 * 2015年12月28日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.action.checkfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.CheckFileBean;
import com.zlebank.zplatform.manager.bean.CheckFileQuery;
import com.zlebank.zplatform.manager.service.iface.CheckFileService;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月28日 上午11:30:52
 * @since
 */
public class CheckFileAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private CheckFileService cfs;

    private CheckFileQuery cfq;

    public CheckFileQuery getCfq() {
        return cfq;
    }

    public void setCfq(CheckFileQuery cfq) {
        this.cfq = cfq;
    }

    public String getCheckFile() {
        return SUCCESS;
    }

    public void getCheckFileByQuery() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<CheckFileBean> cfb = cfs.queryPaged(page, pageSize, cfq);

        try {
            List<CheckFileBean> li = cfb.getPagedResult();
            Long count = cfb.getTotal();
            map.put("total", count);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
