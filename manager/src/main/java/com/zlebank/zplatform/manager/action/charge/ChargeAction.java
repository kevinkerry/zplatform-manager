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
import com.sun.tools.internal.ws.processor.model.Message;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.Business;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.ChargeBean;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.bean.ChnlDetaBean;
import com.zlebank.zplatform.manager.bean.FrozenAccBean;
import com.zlebank.zplatform.manager.enums.ChargeEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.ChargeService;
import com.zlebank.zplatform.manager.service.iface.IChannelService;
import com.zlebank.zplatform.manager.service.iface.IChargeService;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.trade.common.page.PageVo;
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
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private IChannelService channelService;
    private ChargeQuery chargeQuery;
    public List<ChnlDetaBean> bus;
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
        bus = channelService.getAllChannelCodeList();
        return Action.SUCCESS;
    }

    public void queryCharge() {
        if (chargeQuery == null) {
            chargeQuery = new ChargeQuery();
        }
        if ("first".equals(falg)) {           
            chargeQuery.setStatus(ChargeEnum.FIRSTTRIAL.getCode());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageVo<ChargeQuery> pagevo = chargeService.queryByPage(chargeQuery, this.getPage(), this.getPage_size());
        map.put("total", pagevo.getTotal());
        map.put("rows", pagevo.getList());
        json_encode(map);
//        Map<String, Object> map = new HashMap<String, Object>();
//        PagedResult<ChargeBean> pr = charge.queryPaged(page, pageSize,
//                ChargeQuery);
//        try {
//            List<ChargeBean> li = pr.getPagedResult();
//            Long total = pr.getTotal();
//            map.put("total", total);
//            map.put("rows", li);
//            json_encode(map);
//        } catch (IllegalAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

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
            messg = e.getMessage();
            e.printStackTrace();
        } catch(RuntimeException e){
            messg = e.getMessage();
        }

        map.put("messg", messg);
        map.put("falg", isok);
        json_encode(map);
    }

//    public String queryChnl(){
//        bus = channelService.getAllChannelCodeList();
//        return null;
//    }
}
