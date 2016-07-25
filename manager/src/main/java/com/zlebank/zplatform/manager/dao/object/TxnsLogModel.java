package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_TXNS_LOG")
public class TxnsLogModel implements java.io.Serializable {
    /**交易序列号[证联金融所用：关联各子流水表]**/
    private String txnseqno;
    /**业务代码**/
    private String busicode;    
    /**转出帐号或卡号**/
    private String pan;
    /**受理订单号**/
    private String accordno;
    /**受理会员号**/
    private String accmemberid;
    
    /**受理二级商户号**/
    private String accsecmerno;
    /**交易时间**/
    private String txntime;
    /**应答流水号**/
    private String payrettsnseqno;
    /**中心应答码**/
    private String retcode;

    
   

    public TxnsLogModel() {
        super();
    }

    public TxnsLogModel(String txnseqno, String busicode, String pan,
            String accordno, String accmemberid, String accsecmerno,
            String txntime, String payrettsnseqno, String retcode) {
        super();
        this.txnseqno = txnseqno;
        this.busicode = busicode;
        this.pan = pan;
        this.accordno = accordno;
        this.accmemberid = accmemberid;
        this.accsecmerno = accsecmerno;
        this.txntime = txntime;
        this.payrettsnseqno = payrettsnseqno;
        this.retcode = retcode;
    }

    @Id
    @Column(name = "TXNSEQNO", unique = true, nullable = false)
    public String getTxnseqno() {
        return txnseqno;
    }
    
    public void setTxnseqno(String txnseqno) {
        this.txnseqno = txnseqno;
    }
    
    @Column(name = "PAN", length = 40)
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
    
    @Column(name = "ACCMEMBERID", length = 15)
    public String getAccmemberid() {
        return accmemberid;
    }

    public void setAccmemberid(String accmemberid) {
        this.accmemberid = accmemberid;
    }
    
    @Column(name = "BUSICODE" ,length = 8)
    public String getBusicode() {
        return busicode;
    }


    public void setBusicode(String busicode) {
        this.busicode = busicode;
    }

    @Column(name = "ACCORDNO" ,length = 40)
    public String getAccordno() {
        return accordno;
    }


    public void setAccordno(String accordno) {
        this.accordno = accordno;
    }

    @Column(name = "ACCSECMERNO",length = 15)
    public String getAccsecmerno() {
        return accsecmerno;
    }


    public void setAccsecmerno(String accsecmerno) {
        this.accsecmerno = accsecmerno;
    }

   @Column(name="TXNTIME",length = 6)
    public String getTxntime() {
        return txntime;
    }


    public void setTxntime(String txntime) {
        this.txntime = txntime;
    }

    @Column(name="PAYRETTSNSEQNO",length = 64)
    public String getPayrettsnseqno() {
        return payrettsnseqno;
    }


    public void setPayrettsnseqno(String payrettsnseqno) {
        this.payrettsnseqno = payrettsnseqno;
    }


    @Column(name= "RETCODE",length = 8)
    public String getRetcode() {
        return retcode;
    }


    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }


 

}
