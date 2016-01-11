package com.zlebank.zplatform.manager.dao.object;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年12月23日 上午9:57:09
 * @since
 */
@Entity
public class TradeStatModel implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 580889719287298653L;

    @Id
    private long rowNum;
    private String chnlName;
    private String busiName;
    private String succAmount;
    private String failAmount;
    private String succTxnFee;
    private String failTxnFee;
    private String succTradComm;
    private String failTradComm;
    private String succCount;
    private String failCount;
    private String totalCount;
    
    
    public long getRowNum() {
        return rowNum;
    }
    public void setRowNum(long rowNum) {
        this.rowNum = rowNum;
    }
    public String getChnlName() {
        return chnlName;
    }
    public void setChnlName(String chnlName) {
        this.chnlName = chnlName;
    }
    public String getBusiName() {
        return busiName;
    }
    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }
    public String getSuccAmount() {
        return succAmount;
    }
    public void setSuccAmount(String succAmount) {
        this.succAmount = succAmount;
    }
    public String getFailAmount() {
        return failAmount;
    }
    public void setFailAmount(String failAmount) {
        this.failAmount = failAmount;
    }
    public String getSuccTxnFee() {
        return succTxnFee;
    }
    public void setSuccTxnFee(String succTxnFee) {
        this.succTxnFee = succTxnFee;
    }
    public String getFailTxnFee() {
        return failTxnFee;
    }
    public void setFailTxnFee(String failTxnFee) {
        this.failTxnFee = failTxnFee;
    }
    public String getSuccTradComm() {
        return succTradComm;
    }
    public void setSuccTradComm(String succTradComm) {
        this.succTradComm = succTradComm;
    }
    public String getFailTradComm() {
        return failTradComm;
    }
    public void setFailTradComm(String failTradComm) {
        this.failTradComm = failTradComm;
    }
    public String getSuccCount() {
        return succCount;
    }
    public void setSuccCount(String succCount) {
        this.succCount = succCount;
    }
    public String getFailCount() {
        return failCount;
    }
    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }
    public String getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
