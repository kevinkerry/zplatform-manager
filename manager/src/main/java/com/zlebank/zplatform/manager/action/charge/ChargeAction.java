/* 
 * ChargeAction.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.charge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.ChargeBean;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.enums.ChargeEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IChargeService;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.trade.exception.TradeException;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 下午3:40:23
 * @since
 */
public class ChargeAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private IChargeService charge;

    private ChargeQuery chargeQuery;

    private ChargeBean cb;

    private String falg;

    private AuditBean trial;

    public AuditBean getTrial() {
        return trial;
    }

    public void setTrial(AuditBean trial) {
        this.trial = trial;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public ChargeBean getCb() {
        return cb;
    }

    public void setCb(ChargeBean cb) {
        this.cb = cb;
    }

    public ChargeQuery getChargeQuery() {
        return chargeQuery;
    }

    public void setChargeQuery(ChargeQuery chargeQuery) {
        this.chargeQuery = chargeQuery;
    }

    public String getCharge() {

        return Action.SUCCESS;
    }

    public void queryCharge() {
        if ("first".equals(falg)) {
            if (chargeQuery == null) {
                chargeQuery = new ChargeQuery();
            }
            chargeQuery.setStatus(ChargeEnum.FIRSTTRIAL.getCode());

        }
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<ChargeBean> pr = charge.queryPaged(page, pageSize,
                chargeQuery);
        try {
            List<ChargeBean> li = pr.getPagedResult();
            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", li);
            json_encode(map);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void saveCharge() {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean isok = false;
        Long userId = getCurrentUser().getUserId();
        String messg = "";
        try {
            charge.saveCharge(cb, userId);
            isok = true;
            messg = "操作成功";
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (MemberBussinessException e) {
            messg = e.getMessage();
        }
        map.put("messg", messg);
        map.put("falg", isok);
        json_encode(map);

    }

    public String getFirstCharge() {
        return "first";
    }
    /**
     * 充值初审
     */
    public void firstCharge() {
        Map<String, Object> map = new HashMap<String, Object>();
        String messg = "";
        boolean isok = false;
        Long userId = getCurrentUser().getUserId();

        trial.setStexauser(userId);

        try {
            charge.firstCharge(trial);
            isok = true;
            messg = "操作成功";
        } catch (IllegalEntryRequestException e) {
            messg = e.getMessage();;
        } catch (ManagerWithdrawException e) {
            messg = e.getMessage();
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        } catch (AbstractBusiAcctException e) {
            messg = e.getMessage();
        } catch (NumberFormatException e) {
            messg = e.getMessage();
        } catch (TradeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        map.put("messg", messg);
        map.put("falg", isok);
        json_encode(map);
    }

}
