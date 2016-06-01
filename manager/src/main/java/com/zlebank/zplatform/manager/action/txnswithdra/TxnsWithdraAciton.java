/* 
 * TxnsWinhdraAciton.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.txnswithdra;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawBean;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.ITWithService;

/**
 * 提现Action
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午11:43:36
 * @since
 */
public class TxnsWithdraAciton extends BaseAction {

    @Autowired
    private ITWithService itws;

    private TxnsWithdrawBean twb;

    private TxnsWithdrawQuery twq;

    private QueryAccount qa;

    private String usage;

    private TxnsLog txns;

    public TxnsLog getTxns() {
        return txns;
    }

    public void setTxns(TxnsLog txns) {
        this.txns = txns;
    }

    public QueryAccount getQa() {
        return qa;
    }

    public void setQa(QueryAccount qa) {
        this.qa = qa;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public TxnsWithdrawBean getTwb() {
        return twb;
    }

    public TxnsWithdrawQuery getTwq() {
        return twq;
    }

    public void setTwq(TxnsWithdrawQuery twq) {
        this.twq = twq;
    }

    public void setTwb(TxnsWithdrawBean twb) {
        this.twb = twb;
    }

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public String getTxnsWinhdr() {
        return SUCCESS;
    }

    public void queryTxnsWinhdr() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<TxnsWithdrawBean> pr = itws.queryPaged(page, pageSize, twq);
        try {

            List<TxnsWithdrawBean> li = pr.getPagedResult();
            for (TxnsWithdrawBean txnsw : li) {
                txnsw.setAmount(Money.valueOf(new BigDecimal(txnsw.getAmount())).toYuan());
                if (StringUtil.isEmpty(txnsw.getFee())) {
                    txnsw.setFee(Money.ZERO.toYuan());
                } else {
                    txnsw.setFee(Money.valueOf(new BigDecimal(txnsw.getFee())).toYuan());
                }
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

    /**
     * 通过member得到商户或者个人信息
     */
    public void queryMemberByMid() {
        Map<String, Object> map;
        if (qa == null || StringUtil.isEmpty(qa.getMemberId())
                || StringUtil.isEmpty(qa.getUsage())) {
            map = new HashMap<String, Object>();
            map.put("messg", "请输入正确的参数");
        } else {
            map = itws.getTxnsWinth(qa);
        }
        json_encode(map);
    }

    public void saveTxnsWinhdr() {
        Map<String, Object> map = itws.saveTxnsWinhdraw(qa, twb,
                getCurrentUser().getUserId());
        json_encode(map);
    }

    /**
     * 提现手续费
     * 
     * @param txns
     */
    public void getTxnFee() {
        Map<String, Object> map = new HashMap<String, Object>();

        Long txnsfee;
        try {
            txnsfee = itws.getTxnFee(txns);
            String money = Money.valueOf(new BigDecimal(txnsfee)).toYuan();
            map.put("fee", money);
            json_encode(map);
        } catch (ManagerWithdrawException e) {
            String messg = e.getMessage();
            map.put("messg", messg);
            json_encode(map);
        }

    }
}