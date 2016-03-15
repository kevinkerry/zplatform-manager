/* 
 * FirstTrialWithdra.java  
 * 
 * version TODO
 *
 * 2015年12月1日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.txnswithdra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawBean;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.manager.enums.ReviewEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.ITWithService;

/**
 * 提现审核
 *
 * @author yangpeng
 * @version
 * @date 2015年12月1日 下午4:53:20
 * @since
 */
public class TrialWithdraAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private ITWithService itws;

    private TxnsWithdrawQuery twq;

    private AuditBean ftb;
    
    public String falg;

    public AuditBean getFtb() {
        return ftb;
    }

    public void setFtb(AuditBean ftb) {
        this.ftb = ftb;
    }

    public TxnsWithdrawQuery getTwq() {
        return twq;
    }

    public void setTwq(TxnsWithdrawQuery twq) {
        this.twq = twq;
    }

    /**
     * 得到审核信息
     */
    public void queryTrialWithdra() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        if (twq == null) {
            twq = new TxnsWithdrawQuery();
        }
        if("first".equals(falg)){
        twq.setStatus(ReviewEnum.FIRSTTRIAL.getCode());
        }else if("second".equals(falg)){
            twq.setStatus(ReviewEnum.SECONDTRIAL.getCode());  
        }
        PagedResult<TxnsWithdrawBean> pr = itws.queryPaged(page, pageSize, twq);
        try {

            List<TxnsWithdrawBean> li = pr.getPagedResult();
            for (TxnsWithdrawBean txnsw : li) {

            }
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String getFirstTrial() {
        return this.SUCCESS;
    }
    /**
     * 提现初审
     */
    public void firstTrial() {
        Long userId = getCurrentUser().getUserId();
        ftb.setStexauser(userId);
        String messg = "";
        try {
            itws.firstTrialWinth(ftb);
            messg = "操作成功";
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();

        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        }
        json_encode(messg);
        }
        
    
    /**
     * 提现复审
     */
    public void senondTrial() {
        Long userId = getCurrentUser().getUserId();
        ftb.setStexauser(userId);
        String messg = "";
        try {
            itws.secondTrialWinth(ftb);
            messg = "操作成功";
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();

        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        }
        json_encode(messg);
        }
        
    public String getSecondTrial() {
        return "second";
    }
    
}
