/* 
 * MInsteadpayServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月23日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.dao.CardBinDao;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IInsteadPayService;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailBean;
import com.zlebank.zplatform.trade.bean.InsteadPayDetailQuery;
import com.zlebank.zplatform.trade.bean.enums.InsteadEnum;
import com.zlebank.zplatform.trade.dao.InsteadPayDetailDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoInsteadPayDetail;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月23日 上午9:58:52
 * @since
 */
@Service
public class MInsteadpayServiceImpl implements IInsteadPayService {
    @Autowired
    private InsteadPayDetailDAO insteadPayDetailDAO;
    /** 民生银行code **/
    private final static String TOTALBANKCODE = "0305";
    @Autowired
    private CardBinDao cardbin;
    @Autowired
    private IRiskService risk;
    @Autowired
    private TransferDataDAO transdata;
    @Autowired
    private AccEntryService accEntyr;
    /** 民生 **/
    private final static String CMBC = "01";
    /** 其他 **/
    private final static String OTHER = "02";

    private static final String BUSINESSCODE = "70000001";
    private static final String BUSINESSTYPE = "7000";
    
    /** 审核拒绝 **/
    private static final String REFUSE = "70000003";
    /**
     *
     * @param trial
     * @throws AccBussinessException
     * @throws ManagerWithdrawException
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void firstInstead(AuditBean trial) throws AccBussinessException,
            ManagerWithdrawException, AbstractBusiAcctException,
            NumberFormatException {
        if (trial == null || StringUtil.isEmpty(trial.getOrderNo())) {
            throw new ManagerWithdrawException("G100014");
        }
        PojoInsteadPayDetail pojoinstead = insteadPayDetailDAO
                .getDetailByTxnseqno(trial.getOrderNo(), InsteadEnum.FIRSTTRIAL.getCode());
        if (pojoinstead == null) {
            throw new ManagerWithdrawException("G100003");
        }
        // 审核通过
        if (trial.getFalg() == true) {
            //through(pojoinstead);
            pojoinstead.setStexauser(trial.getStexauser());
            pojoinstead.setStexaopt(trial.getStexaopt());
        } else {
            veto(pojoinstead,InsteadEnum.FIRSTREFUSED.getCode());
            pojoinstead.setStexauser(trial.getStexauser());
            pojoinstead.setStexaopt(trial.getStexaopt());
        }
    }

    /***
     * 审核拒绝
     * 
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * 
     * @throws AccBussinessException
     */
    public  void veto(PojoInsteadPayDetail pojoinstead,String status)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {
        pojoinstead.setStatus(status);
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setAmount(pojoinstead.getAmt());
        tradeInfo.setBusiCode(REFUSE);
        tradeInfo.setPayMemberId(pojoinstead.getMerId());
        tradeInfo.setTxnseqno(pojoinstead.getTxnseqno());
        //tradeInfo.setTxnseqno(pojoinstead.getOrderId());
        tradeInfo.setCommission(new BigDecimal(0));
        tradeInfo.setCharge(pojoinstead.getTxnfee());
        accEntyr.accEntryProcess(tradeInfo);

    }

    /**
     * 审核通过
     * 
     * @param pojoinstead
     * @throws ManagerWithdrawException 
     */
    private String through(PojoInsteadPayDetail pojoinstead) throws ManagerWithdrawException {
        /*if (StringUtil.isEmpty(pojoinstead.getAccNo())) {
            throw new ManagerWithdrawException("G100016");
        }
        CardBin card = cardbin.getCard(pojoinstead.getAccNo());
        if (card == null) {
            throw new ManagerWithdrawException("G100016");
        }
        // 将数据转换为划拨数据
        pojoinstead.setStatus(InsteadEnum.BATCH.getCode());
        // 通过后需要将数据记录到划拨明细表中 并且将数据统计写入划拨表中
        PojoTransferData pojotransDate = new PojoTransferData();
        if(TOTALBANKCODE.equals(card.getBankCode())){
            pojotransDate.setTransfertype(CMBC);
        }else{
            pojotransDate.setTransfertype(OTHER);
        }
        // 划拨订单号
        pojotransDate.setTranid(risk.generateWithdrawOrderNo());
        //关联批次号
        pojotransDate.setAssociatedBatch(String.valueOf(pojoinstead.getBatchId()==null?"":pojoinstead.getBatchId()));
        // 账号
        pojotransDate.setAccno(pojoinstead.getAccNo());
        // 户名
        pojotransDate.setAccname(pojoinstead.getAccName());
        // 支行代码
        pojotransDate.setBanktype(pojoinstead.getBankCode());
        //手续费
        pojotransDate.setTxnfee(pojoinstead.getTxnfee());
        pojotransDate.setTxnseqno(pojoinstead.getTxnseqno());
        // 总金额
        pojotransDate.setTransamt(pojoinstead.getAmt().longValue());
        pojotransDate.setMemberid(pojoinstead.getMerId());
        pojotransDate.setStatus(TransFerDataStatusEnum.FIRSTTRIAL.getCode());
        pojotransDate.setBusicode(BUSINESSCODE);
        pojotransDate.setBusitype(BUSINESSTYPE);
        pojotransDate.setRelatedorderno(pojoinstead.getOrderId());
        return transdata.merge(pojotransDate);*/
    	return null;

    }

    /**
     *
     * @param txnserno
     * @param status
     * @return
     * @throws ManagerWithdrawException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public InsteadPayDetailBean getDetailByTxnseqno(String txnserno,String status) throws ManagerWithdrawException {
        PojoInsteadPayDetail pojoinstead = insteadPayDetailDAO
                .getDetailByTxnseqno(txnserno,status);
        if(pojoinstead==null){
            throw new ManagerWithdrawException("G100014");
        }
        
         InsteadPayDetailBean instead=
                 BeanCopyUtil.copyBean(InsteadPayDetailBean.class, pojoinstead);
         instead.setAmt(Money.valueOf(pojoinstead.getAmt()).toYuan()); 
         
         return instead;
         
    }
    
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void batchFirst(AuditBean trial,InsteadPayDetailQuery instead) throws AccBussinessException, AbstractBusiAcctException, NumberFormatException, ManagerWithdrawException{
        List<PojoInsteadPayDetail> li= insteadPayDetailDAO.getListByQuery(0,Integer.MAX_VALUE, instead);
        if(li==null||li.isEmpty()){
            throw new ManagerWithdrawException("G100014");
        }
        
        for (PojoInsteadPayDetail ppd:li){
            // 审核通过
            if (trial.getFalg() == true) {
                through(ppd);
                ppd.setStexauser(trial.getStexauser());
                ppd.setStexaopt(trial.getStexaopt());
            } else {
                veto(ppd,InsteadEnum.FIRSTREFUSED.getCode());
                ppd.setStexauser(trial.getStexauser());
                ppd.setStexaopt(trial.getStexaopt());
            }
        }
   
    
    }

}
