/* 
 * TxnsLog.java  
 * 
 * version TODO
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.bean;

import com.zlebank.zplatform.commons.bean.Bean;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月17日 上午9:40:36
 * @since 
 */
public class TxnsLog implements Bean{
    /**交易序列号[证联金融所用：关联各子流水表]**/
    private String txnseqno;
    /**交易日期**/
    private String txndate;
    /**交易时间**/
    private String txntime;
    /**应用类型**/
    private String apptype;
    /**业务类型**/
    private String busitype;
    /**业务代码**/
    private String busicode;
    /**交易金额[合计]**/
    private String amount;
    /**交易佣金**/
    private String tradcomm;
    /**交易手续费**/
    private String txnfee;
    /**分控版本[商户角色]**/
    private String riskver;
    /**分润版本[商户角色]**/
    private String splitver;
    /**扣率版本[商户角色]**/
    private String feever;
    /**产品版本[商户角色]**/
    private String prdtver;
    /**收银台版本[商户角色]**/
    private String checkstandver;
    /**路由版本[客户角色]**/
    private String routver;
    /**转出帐号或卡号**/
    private String pan;
    /**转出帐号类型**/
    private String cardtype;
    /**转出帐号或卡号所属机构**/
    private String cardinstino;
    /**转入帐号或卡号**/
    private String inpan;
    /**转入帐号或卡号类型**/
    private String incardtype;
    /**转入帐号或卡号机构代码**/
    private String incardinstino;
    /**受理订单号**/
    private String accordno;
    /**受理订单所属机构**/
    private String accordinst;
    /**受理二级商户号**/
    private String accsecmerno;
    /**受理一级商户号**/
    private String accfirmerno;
    /**受理清算日期**/
    private String accsettledate;
    /**受理定提交时间**/
    private String accordcommitime;
    /**受理定单完成时间**/
    private String accordfintime;
    /**支付类型（01：快捷，02：网银，03：账户）**/
    private String paytype;
    /**支付定单号**/
    private String payordno;
    /**支付所属机构**/
    private String payinst;
    /**支付一级商户号**/
    private String payfirmerno;
    /**支付二级商户号**/
    private String paysecmerno;
    /**支付定单提交时间**/
    private String payordcomtime;
    /**支付定单完成时间**/
    private String payordfintime;
    /**支付方交易流水号**/
    private String payrettsnseqno;
    /**支付方应答码**/
    private String payretcode;
    /**支付方应答信息**/
    private String payretinfo;
    /**应用定单号**/
    private String appordno;
    /**应用所属机构**/
    private String appinst;
    /**应用定单提交时间**/
    private String appordcommitime;
    /**应用定单完成时间**/
    private String appordfintime;
    /**交易查询流水[证联金融返给客户端流水vs交易序列号]**/
    private String tradeseltxn;
    /**中心应答码**/
    private String retcode;
    /**中心应答信息**/
    private String retinfo;
    /**交易状态标志位**/
    private String tradestatflag;
    /**交易所涉流水表标志位**/
    private String tradetxnflag;
    /**路由层次[当前交易号]**/
    private String txncode;
    /**收银代码**/
    private String cashcode;
    /**涉及流水表标志**/
    private String relate;
    /**中心应答时间**/
    private String retdatetime;
    /**原交易序列号**/
    private String txnseqnoOg;
    /**备注**/
    private String notes;
    /**备注**/
    private String remarks;
    /**受理会员号**/
    private String accmemberid;
    /**应用定单状态**/
    private String apporderstatus;
    /**应用订单应答信息**/
    private String apporderinfo;
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    public String getTxndate() {
        return txndate;
    }
    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }
    public String getTxntime() {
        return txntime;
    }
    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }
    public String getApptype() {
        return apptype;
    }
    public void setApptype(String apptype) {
        this.apptype = apptype;
    }
    public String getBusitype() {
        return busitype;
    }
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getTradcomm() {
        return tradcomm;
    }
    public void setTradcomm(String tradcomm) {
        this.tradcomm = tradcomm;
    }
    public String getTxnfee() {
        return txnfee;
    }
    public void setTxnfee(String txnfee) {
        this.txnfee = txnfee;
    }
    public String getRiskver() {
        return riskver;
    }
    public void setRiskver(String riskver) {
        this.riskver = riskver;
    }
    public String getSplitver() {
        return splitver;
    }
    public void setSplitver(String splitver) {
        this.splitver = splitver;
    }
    public String getFeever() {
        return feever;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    public String getPrdtver() {
        return prdtver;
    }
    public void setPrdtver(String prdtver) {
        this.prdtver = prdtver;
    }
    public String getCheckstandver() {
        return checkstandver;
    }
    public void setCheckstandver(String checkstandver) {
        this.checkstandver = checkstandver;
    }
    public String getRoutver() {
        return routver;
    }
    public void setRoutver(String routver) {
        this.routver = routver;
    }
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    public String getCardinstino() {
        return cardinstino;
    }
    public void setCardinstino(String cardinstino) {
        this.cardinstino = cardinstino;
    }
    public String getInpan() {
        return inpan;
    }
    public void setInpan(String inpan) {
        this.inpan = inpan;
    }
    public String getIncardtype() {
        return incardtype;
    }
    public void setIncardtype(String incardtype) {
        this.incardtype = incardtype;
    }
    public String getIncardinstino() {
        return incardinstino;
    }
    public void setIncardinstino(String incardinstino) {
        this.incardinstino = incardinstino;
    }
    public String getAccordno() {
        return accordno;
    }
    public void setAccordno(String accordno) {
        this.accordno = accordno;
    }
    public String getAccordinst() {
        return accordinst;
    }
    public void setAccordinst(String accordinst) {
        this.accordinst = accordinst;
    }
    public String getAccsecmerno() {
        return accsecmerno;
    }
    public void setAccsecmerno(String accsecmerno) {
        this.accsecmerno = accsecmerno;
    }
    public String getAccfirmerno() {
        return accfirmerno;
    }
    public void setAccfirmerno(String accfirmerno) {
        this.accfirmerno = accfirmerno;
    }
    public String getAccsettledate() {
        return accsettledate;
    }
    public void setAccsettledate(String accsettledate) {
        this.accsettledate = accsettledate;
    }
    public String getAccordcommitime() {
        return accordcommitime;
    }
    public void setAccordcommitime(String accordcommitime) {
        this.accordcommitime = accordcommitime;
    }
    public String getAccordfintime() {
        return accordfintime;
    }
    public void setAccordfintime(String accordfintime) {
        this.accordfintime = accordfintime;
    }
    public String getPaytype() {
        return paytype;
    }
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    public String getPayinst() {
        return payinst;
    }
    public void setPayinst(String payinst) {
        this.payinst = payinst;
    }
    public String getPayfirmerno() {
        return payfirmerno;
    }
    public void setPayfirmerno(String payfirmerno) {
        this.payfirmerno = payfirmerno;
    }
    public String getPaysecmerno() {
        return paysecmerno;
    }
    public void setPaysecmerno(String paysecmerno) {
        this.paysecmerno = paysecmerno;
    }
    public String getPayordcomtime() {
        return payordcomtime;
    }
    public void setPayordcomtime(String payordcomtime) {
        this.payordcomtime = payordcomtime;
    }
    public String getPayordfintime() {
        return payordfintime;
    }
    public void setPayordfintime(String payordfintime) {
        this.payordfintime = payordfintime;
    }
    public String getPayrettsnseqno() {
        return payrettsnseqno;
    }
    public void setPayrettsnseqno(String payrettsnseqno) {
        this.payrettsnseqno = payrettsnseqno;
    }
    public String getPayretcode() {
        return payretcode;
    }
    public void setPayretcode(String payretcode) {
        this.payretcode = payretcode;
    }
    public String getPayretinfo() {
        return payretinfo;
    }
    public void setPayretinfo(String payretinfo) {
        this.payretinfo = payretinfo;
    }
    public String getAppordno() {
        return appordno;
    }
    public void setAppordno(String appordno) {
        this.appordno = appordno;
    }
    public String getAppinst() {
        return appinst;
    }
    public void setAppinst(String appinst) {
        this.appinst = appinst;
    }
    public String getAppordcommitime() {
        return appordcommitime;
    }
    public void setAppordcommitime(String appordcommitime) {
        this.appordcommitime = appordcommitime;
    }
    public String getAppordfintime() {
        return appordfintime;
    }
    public void setAppordfintime(String appordfintime) {
        this.appordfintime = appordfintime;
    }
    public String getTradeseltxn() {
        return tradeseltxn;
    }
    public void setTradeseltxn(String tradeseltxn) {
        this.tradeseltxn = tradeseltxn;
    }
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    public String getRetinfo() {
        return retinfo;
    }
    public void setRetinfo(String retinfo) {
        this.retinfo = retinfo;
    }
    public String getTradestatflag() {
        return tradestatflag;
    }
    public void setTradestatflag(String tradestatflag) {
        this.tradestatflag = tradestatflag;
    }
    public String getTradetxnflag() {
        return tradetxnflag;
    }
    public void setTradetxnflag(String tradetxnflag) {
        this.tradetxnflag = tradetxnflag;
    }
    public String getTxncode() {
        return txncode;
    }
    public void setTxncode(String txncode) {
        this.txncode = txncode;
    }
    public String getCashcode() {
        return cashcode;
    }
    public void setCashcode(String cashcode) {
        this.cashcode = cashcode;
    }
    public String getRelate() {
        return relate;
    }
    public void setRelate(String relate) {
        this.relate = relate;
    }
    public String getRetdatetime() {
        return retdatetime;
    }
    public void setRetdatetime(String retdatetime) {
        this.retdatetime = retdatetime;
    }
    public String getTxnseqnoOg() {
        return txnseqnoOg;
    }
    public void setTxnseqnoOg(String txnseqnoOg) {
        this.txnseqnoOg = txnseqnoOg;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getAccmemberid() {
        return accmemberid;
    }
    public void setAccmemberid(String accmemberid) {
        this.accmemberid = accmemberid;
    }
    public String getApporderstatus() {
        return apporderstatus;
    }
    public void setApporderstatus(String apporderstatus) {
        this.apporderstatus = apporderstatus;
    }
    public String getApporderinfo() {
        return apporderinfo;
    }
    public void setApporderinfo(String apporderinfo) {
        this.apporderinfo = apporderinfo;
    }
    
}
