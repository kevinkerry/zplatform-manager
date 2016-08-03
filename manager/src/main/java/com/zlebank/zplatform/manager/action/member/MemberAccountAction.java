/* 
 * Member.java  
 * 
 * version TODO
 *
 * 2015年10月14日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.action.member;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.acc.bean.Account;
import com.zlebank.zplatform.acc.bean.AccountAmount;
import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.FreezeAcctService;
import com.zlebank.zplatform.acc.service.FreezeAmountService;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.AccountAmountMan;
import com.zlebank.zplatform.manager.bean.AccountQuery;
import com.zlebank.zplatform.manager.service.iface.IMemberService;

/**
 * 会员账户管理
 *
 * @author yangpeng
 * @version
 * @date 2015年10月14日 下午1:55:00
 * @since
 */

public class MemberAccountAction extends BaseAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm";
    @Autowired
    private IMemberService ims;
    @Autowired
    private FreezeAcctService fas;
    @Autowired
    private FreezeAmountService freeas;
    private QueryAccount qa;

    private Account para;

    private Integer type;

    private AccountAmountMan account;

    public AccountAmountMan getAccount() {
        return account;
    }
    public void setAccount(AccountAmountMan account) {
        this.account = account;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Account getPara() {
        return para;
    }
    public void setPara(Account para) {
        this.para = para;
    }
    public QueryAccount getQa() {
        return qa;
    }
    public void setQa(QueryAccount qa) {
        this.qa = qa;
    }

    public String getMember() {
        return SUCCESS;
    }

    /**
     * 冻结部分金额
     */
    public void freezeAmount() {
        String messg = "";
        Date nowDate = new Date();
        Date frozeStartDate;
        try {
            frozeStartDate = DateUtil.convertToDate(account.getStartTime(),
                    DEFAULT_TIME_STAMP_FROMAT);

            if (nowDate.getTime() > frozeStartDate.getTime()) {
                messg = "开始日期必须大于或者等于当前时间";
            } else {
                AccountAmount amount = BeanCopyUtil.copyBean(
                        AccountAmount.class, account);
                double money = Double.valueOf(account.getFrozenBalance());
                money = money * 100;
                
                Long frozeStartDateTimeValue = frozeStartDate.getTime();
                Date frozeEndDate = DateUtil.convertToDate(account.getEndTime(),
                        DEFAULT_TIME_STAMP_FROMAT);
                Long frozeStartEndTimeValue = frozeEndDate.getTime();
                amount.setFrozenSTime(frozeStartDate);
                amount.setUnfrozenTime(frozeEndDate);
                amount.setFrozenTime((frozeStartEndTimeValue - frozeStartDateTimeValue) / 60 / 1000);
                amount.setInuser(getCurrentUser().getUserId());
                amount.setUpuser(getCurrentUser().getUserId());
                amount.setFrozenBalance(Money.valueOf(money));
                
                freeas.freezeAmount(amount);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }

        catch (ParseException e1) {
            messg = "系统内部错误:时间转换出错";
        }

        json_encode(messg);
    }
    
    /**
     * 查询所有的账户信息
     */
    public void queryMember() {
        int page = this.getPage();
        int pageSize = this.getRows();
        Map<String, Object> map = new HashMap<String, Object>();
        PagedResult<BusiAcctQuery> pr = ims.queryAccount(page, pageSize, qa);
        try {
            List<BusiAcctQuery> li = pr.getPagedResult();
            List<AccountQuery> accQ = new ArrayList<AccountQuery>();
            for (BusiAcctQuery busaq : li) {
                AccountQuery account = BeanCopyUtil.copyBean(
                        AccountQuery.class, busaq);
                account.setBalance(busaq.getBalance().toYuan());
                account.setFronzenBalance(busaq.getFronzenBalance().toYuan());
                account.setTotalBalance(busaq.getTotalBalance().toYuan());
                account.setStatus(busaq.getStatus().getCode());
                account.setUsage(busaq.getUsage());
                accQ.add(account);
            }

            Long total = pr.getTotal();
            map.put("total", total);
            map.put("rows", accQ);
      
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        json_encode(map);
    }
    
    
    
    /**
     * 账户操作
     */
    public void operation() {
        String messg = "";
        if (type == 1) {
            messg = this.unFreezeAcct();
        } else if (type == 2) {
            messg = this.freezeAcct();
        } else if (type == 3) {
            messg = this.stopInAcct();
        } else if (type == 4) {
            messg = this.reopenInAcct();
        } else if (type == 5) {
            messg = this.stopOutAcct();
        } else if (type == 6) {
            messg = this.reopenOutAcct();
        } else if (type == 7) {
            messg = this.logOutAcct();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("messg", messg);
        json_encode(map);

    }

    /**
     * 账户止出
     */
    private String stopOutAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.stopOutAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();

        }
        return messg;
    }
    /**
     * 解除止出
     */
    private String reopenOutAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.reopenOutAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }
        return messg;
    }
    /**
     * 注销账户
     */
    private String logOutAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.logOutAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
           
        }
        return messg;
    }
    /**
     * 止入账户
     */
    private String stopInAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.stopInAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }
        return messg;
    }
    /**
     * 解除止入
     */
    private String reopenInAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.reopenInAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }
        return messg;
    }
    /**
     * 解冻账户
     */
    private String unFreezeAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.unFreezeAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }
        return messg;
    }
    /**
     * 冻结账户
     */
    private String freezeAcct() {
        String messg = "";
        try {
            if (!fas.getStatus(para)) {
                messg = "数据以改变请刷新页面";
            } else {
                fas.freezeAcct(para);
                messg = "操作成功";
            }
        } catch (AccBussinessException e) {
            messg = e.getMessage();
        }
        return messg;
    }
}