/* 
 * TxnLogModel.java  
 * 
 * version TODO
 *
 * 2015年11月16日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zlebank.zplatform.acc.pojo.Money;


/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月16日 下午4:16:26
 * @since 
 */
@Entity
@Table(name = "T_TXNS_LOG")

public class PojoTxnsLog implements java.io.Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 9014309744836697423L;
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
    private Money amount;
    /**交易佣金**/
    private Money tradcomm;
    /**交易手续费**/
    private Money txnfee;
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
  
    @Id
    @Column(name = "TXNSEQNO")
    public String getTxnseqno() {
        return txnseqno;
    }
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    @Column(name = "TXNDATE")
    public String getTxndate() {
        return txndate;
    }
    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }
    @Column(name = "TXNTIME")
    public String getTxntime() {
        return txntime;
    }
    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }
    @Column(name = "APPTYPE")
    public String getApptype() {
        return apptype;
    }
    public void setApptype(String apptype) {
        this.apptype = apptype;
    }
    @Column(name = "BUSITYPE")
    public String getBusitype() {
        return busitype;
    }
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }
    @Column(name = "BUSICODE")
    public String getBusicode() {
        return busicode;
    }
    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }

    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="AMOUNT"))})
    @Column(name ="AMOUNT")
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="TRADCOMM"))})
    @Column(name = "TRADCOMM")
    public Money getTradcomm() {
        return tradcomm;
    }
    public void setTradcomm(Money tradcomm) {
        this.tradcomm = tradcomm;
    } 

    @AttributeOverrides({@AttributeOverride(name="amount",column=@Column(name="TXNFEE"))})
    @Column(name = "TXNFEE")
    public Money getTxnfee() {
        return txnfee;
    }
    public void setTxnfee(Money txnfee) {
        this.txnfee = txnfee;
    }
    @Column(name = "RISKVER")
    public String getRiskver() {
        return riskver;
    }
    public void setRiskver(String riskver) {
        this.riskver = riskver;
    }
    @Column(name = "SPLITVER")
    public String getSplitver() {
        return splitver;
    }
    public void setSplitver(String splitver) {
        this.splitver = splitver;
    }
    @Column(name = "FEEVER")
    public String getFeever() {
        return feever;
    }
    public void setFeever(String feever) {
        this.feever = feever;
    }
    @Column(name = "PRDTVER")
    public String getPrdtver() {
        return prdtver;
    }
    public void setPrdtver(String prdtver) {
        this.prdtver = prdtver;
    }
    @Column(name = "CHECKSTANDVER")
    public String getCheckstandver() {
        return checkstandver;
    }
    public void setCheckstandver(String checkstandver) {
        this.checkstandver = checkstandver;
    }
    @Column(name = "ROUTVER")
    public String getRoutver() {
        return routver;
    }
    public void setRoutver(String routver) {
        this.routver = routver;
    }
    @Column(name = "PAN")
    public String getPan() {
        return pan;
    }
    public void setPan(String pan) {
        this.pan = pan;
    }
    @Column(name = "CARDTYPE")
    public String getCardtype() {
        return cardtype;
    }
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
    @Column(name = "CARDINSTINO")
    public String getCardinstino() {
        return cardinstino;
    }
    public void setCardinstino(String cardinstino) {
        this.cardinstino = cardinstino;
    }
    @Column(name = "INPAN")
    public String getInpan() {
        return inpan;
    }
    public void setInpan(String inpan) {
        this.inpan = inpan;
    }
    @Column(name = "INCARDTYPE")
    public String getIncardtype() {
        return incardtype;
    }
    public void setIncardtype(String incardtype) {
        this.incardtype = incardtype;
    }
    @Column(name = "INCARDINSTINO")
    public String getIncardinstino() {
        return incardinstino;
    }
    public void setIncardinstino(String incardinstino) {
        this.incardinstino = incardinstino;
    }
    @Column(name = "ACCORDNO")
    public String getAccordno() {
        return accordno;
    }
    public void setAccordno(String accordno) {
        this.accordno = accordno;
    }
    @Column(name = "ACCORDINST")
    public String getAccordinst() {
        return accordinst;
    }
    public void setAccordinst(String accordinst) {
        this.accordinst = accordinst;
    }
    @Column(name = "ACCSECMERNO")
    public String getAccsecmerno() {
        return accsecmerno;
    }
    public void setAccsecmerno(String accsecmerno) {
        this.accsecmerno = accsecmerno;
    }
    @Column(name = "ACCFIRMERNO")
    public String getAccfirmerno() {
        return accfirmerno;
    }
    public void setAccfirmerno(String accfirmerno) {
        this.accfirmerno = accfirmerno;
    }
    @Column(name = "ACCSETTLEDATE")
    public String getAccsettledate() {
        return accsettledate;
    }
    public void setAccsettledate(String accsettledate) {
        this.accsettledate = accsettledate;
    }
    @Column(name = "ACCORDCOMMITIME")
    public String getAccordcommitime() {
        return accordcommitime;
    }
    public void setAccordcommitime(String accordcommitime) {
        this.accordcommitime = accordcommitime;
    }
    @Column(name = "ACCORDFINTIME")
    public String getAccordfintime() {
        return accordfintime;
    }
    public void setAccordfintime(String accordfintime) {
        this.accordfintime = accordfintime;
    }
    @Column(name = "PAYTYPE")
    public String getPaytype() {
        return paytype;
    }
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    @Column(name = "PAYORDNO")
    public String getPayordno() {
        return payordno;
    }
    public void setPayordno(String payordno) {
        this.payordno = payordno;
    }
    @Column(name = "PAYINST")
    public String getPayinst() {
        return payinst;
    }
    public void setPayinst(String payinst) {
        this.payinst = payinst;
    }
    @Column(name = "PAYFIRMERNO")
    public String getPayfirmerno() {
        return payfirmerno;
    }
    public void setPayfirmerno(String payfirmerno) {
        this.payfirmerno = payfirmerno;
    }
    @Column(name = "PAYSECMERNO")
    public String getPaysecmerno() {
        return paysecmerno;
    }
    public void setPaysecmerno(String paysecmerno) {
        this.paysecmerno = paysecmerno;
    }
    @Column(name = "PAYORDCOMTIME")
    public String getPayordcomtime() {
        return payordcomtime;
    }
    public void setPayordcomtime(String payordcomtime) {
        this.payordcomtime = payordcomtime;
    }
    @Column(name = "PAYORDFINTIME")
    public String getPayordfintime() {
        return payordfintime;
    }
    public void setPayordfintime(String payordfintime) {
        this.payordfintime = payordfintime;
    }
    @Column(name = "PAYRETTSNSEQNO")
    public String getPayrettsnseqno() {
        return payrettsnseqno;
    }
    public void setPayrettsnseqno(String payrettsnseqno) {
        this.payrettsnseqno = payrettsnseqno;
    }
    @Column(name = "PAYRETCODE")
    public String getPayretcode() {
        return payretcode;
    }
    public void setPayretcode(String payretcode) {
        this.payretcode = payretcode;
    }
    @Column(name = "PAYRETINFO")
    public String getPayretinfo() {
        return payretinfo;
    }
    public void setPayretinfo(String payretinfo) {
        this.payretinfo = payretinfo;
    }
    @Column(name = "APPORDNO")
    public String getAppordno() {
        return appordno;
    }
    public void setAppordno(String appordno) {
        this.appordno = appordno;
    }
    @Column(name = "APPINST")
    public String getAppinst() {
        return appinst;
    }
    public void setAppinst(String appinst) {
        this.appinst = appinst;
    }
    @Column(name = "APPORDCOMMITIME")
    public String getAppordcommitime() {
        return appordcommitime;
    }
    public void setAppordcommitime(String appordcommitime) {
        this.appordcommitime = appordcommitime;
    }
    @Column(name = "APPORDFINTIME")
    public String getAppordfintime() {
        return appordfintime;
    }
    public void setAppordfintime(String appordfintime) {
        this.appordfintime = appordfintime;
    }
    @Column(name = "TRADESELTXN")
    public String getTradeseltxn() {
        return tradeseltxn;
    }
    public void setTradeseltxn(String tradeseltxn) {
        this.tradeseltxn = tradeseltxn;
    }
    @Column(name = "RETCODE")
    public String getRetcode() {
        return retcode;
    }
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    @Column(name = "RETINFO")
    public String getRetinfo() {
        return retinfo;
    }
    public void setRetinfo(String retinfo) {
        this.retinfo = retinfo;
    }
    @Column(name = "TRADESTATFLAG")
    public String getTradestatflag() {
        return tradestatflag;
    }
    public void setTradestatflag(String tradestatflag) {
        this.tradestatflag = tradestatflag;
    }
    @Column(name = "TRADETXNFLAG")
    public String getTradetxnflag() {
        return tradetxnflag;
    }
    public void setTradetxnflag(String tradetxnflag) {
        this.tradetxnflag = tradetxnflag;
    }
    @Column(name = "TXNCODE")
    public String getTxncode() {
        return txncode;
    }
    public void setTxncode(String txncode) {
        this.txncode = txncode;
    }
    @Column(name = "CASHCODE")
    public String getCashcode() {
        return cashcode;
    }
    public void setCashcode(String cashcode) {
        this.cashcode = cashcode;
    }
    @Column(name = "RELATE")
    public String getRelate() {
        return relate;
    }
    public void setRelate(String relate) {
        this.relate = relate;
    }
    @Column(name = "RETDATETIME")
    public String getRetdatetime() {
        return retdatetime;
    }
    public void setRetdatetime(String retdatetime) {
        this.retdatetime = retdatetime;
    }
    @Column(name = "TXNSEQNO_OG")
    public String getTxnseqnoOg() {
        return txnseqnoOg;
    }
    public void setTxnseqnoOg(String txnseqnoOg) {
        this.txnseqnoOg = txnseqnoOg;
    }
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name = "ACCMEMBERID")
    public String getAccmemberid() {
        return accmemberid;
    }
    public void setAccmemberid(String accmemberid) {
        this.accmemberid = accmemberid;
    }
    @Column(name = "APPORDERSTATUS")
    public String getApporderstatus() {
        return apporderstatus;
    }
    public void setApporderstatus(String apporderstatus) {
        this.apporderstatus = apporderstatus;
    }
    @Column(name = "APPORDERINFO")
    public String getApporderinfo() {
        return apporderinfo;
    }
    public void setApporderinfo(String apporderinfo) {
        this.apporderinfo = apporderinfo;
    }

}
