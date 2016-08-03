/* 
 * TxnsWinhdrawBean.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * 提现订单查询条件
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午9:40:24
 * @since 
 */
public class TxnsWithdrawQuery implements Bean{
    /**标示**/
    private Long id;
    /**提现流水号,不同于交易流水号**/
    private String withdraworderno;
    /**会员号**/
    private String memberid;
    /**提现类型0:个人1:商户**/
    private String withdrawtype;
    /**提现状态**/
    private String status;
    private String gatewayorderno;
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWithdraworderno() {
        return withdraworderno;
    }
    public void setWithdraworderno(String withdraworderno) {
        this.withdraworderno = withdraworderno;
    }
    public String getMemberid() {
        return memberid;
    }
    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }
    public String getWithdrawtype() {
        return withdrawtype;
    }
    public void setWithdrawtype(String withdrawtype) {
        this.withdrawtype = withdrawtype;
    }
    public String getGatewayorderno() {
        return gatewayorderno;
    }
    public void setGatewayorderno(String gatewayorderno) {
        this.gatewayorderno = gatewayorderno;
    }
}
