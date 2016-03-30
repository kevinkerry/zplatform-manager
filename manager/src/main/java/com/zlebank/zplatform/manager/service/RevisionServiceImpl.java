/* 
 * RevisionServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月30日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.manager.bean.RevisionBean;
import com.zlebank.zplatform.manager.bean.RevisionQuery;
import com.zlebank.zplatform.manager.dao.iface.IRevisionDAO;
import com.zlebank.zplatform.manager.dao.iface.ITxnsDAO;
import com.zlebank.zplatform.manager.dao.iface.IUserDAO;
import com.zlebank.zplatform.manager.dao.object.PojoRevision;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.dao.object.UserModel;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IRevisionService;
import com.zlebank.zplatform.trade.utils.OrderNumber;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月30日 下午4:11:54
 * @since
 */
@Service
public class RevisionServiceImpl
        extends
            AbstractBasePageService<RevisionQuery, RevisionBean>
        implements
            IRevisionService {
    @Autowired
    private IRevisionDAO ird;
    
    @Autowired
    private ITxnsDAO txnsDao;
    @Autowired
    private IUserDAO userdao;
   
    @Autowired
    private AccEntryService accEntyr;
    /**
     *
     * @param rb
     * @throws NumberFormatException 
     * @throws AbstractBusiAcctException 
     * @throws AccBussinessException 
     * @throws ManagerWithdrawException 
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void saveRevision(String txnsLogNo,Long userId) throws AccBussinessException, AbstractBusiAcctException, NumberFormatException, ManagerWithdrawException {
        TradeInfo trad=null;
        PojoRevision revision=new PojoRevision();
       
        //流水号
        String revisionNo = OrderNumber.getInstance().generateWithdrawOrderNo();;
        //交易流水号 
        revision.setRevisionno(revisionNo);
        
        PojoTxnsLog txns= txnsDao.getTxnsModelByNo(txnsLogNo);
        if(txns==null){
            throw new ManagerWithdrawException("G100014");  
        }
        //业务代码
        revision.setBusicode(txns.getBusicode());
        //业务类型
        revision.setBusitype(txns.getBusitype());
        trad=      this.base(txns);
        revision.setTxnslog(txns);
        accEntyr.accEntryProcess(trad);
        
        UserModel user=new UserModel();
        
        user.setUserId(userId);
        //写入人
        revision.setInuser(user);
        
        ird.saveA(revision);
    }
    
    
    
    private TradeInfo base(PojoTxnsLog txns){
        // 调用分录规则
        TradeInfo tradeInfo = new TradeInfo();
        //金额
        tradeInfo.setAmount(txns.getAmount() == null
                ? new BigDecimal(0)
                : txns.getAmount().getAmount());
        tradeInfo.setBusiCode(txns.getBusicode());
        //渠道
        tradeInfo.setChannelId(txns.getPayinst());
        //受理会员号 (收款方)
       tradeInfo.setPayMemberId(txns.getAccmemberid());
       
        //支付流水号
        tradeInfo.setTxnseqno(txns.getTxnseqno());
        //佣金
        tradeInfo.setCommission(txns.getTradcomm()== null
                ? new BigDecimal(0)
                :txns.getTradcomm().getAmount());
        //手续费
       tradeInfo.setCharge(txns.getTxnfee()== null
               ? new BigDecimal(0):txns.getTxnfee().getAmount());
        //受理订单号
        tradeInfo.setPayordno(txns.getAccordno());
        //付款方
        tradeInfo.setPayToMemberId(txns.getAccmemberid());
        
        return tradeInfo;

    }
    
    
   
    
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(RevisionQuery example) {
        return ird.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<RevisionBean> getItem(int offset,
            int pageSize,
            RevisionQuery example) {
        List<PojoRevision> li = ird.getListByQuery(offset, pageSize, example);
        List<RevisionBean> rbli = new ArrayList<RevisionBean>();
        for (PojoRevision rb : li) {
            RevisionBean revisionb = BeanCopyUtil.copyBean(RevisionBean.class,
                    rb);
            revisionb.setInuser(rb.getInuser().getLoginName());
            revisionb.setIntime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, rb.getIntime()));
            revisionb.setTxnslogid(rb.getTxnslog().getTxnseqno());
            revisionb.setStexauser(rb.getStexauser() == null ? "" : rb
                    .getStexauser().getLoginName());
            revisionb.setStexatime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, rb.getStexatime()));
            revisionb.setCvlexauser(rb.getCvlexauser() == null ? "" : rb
                    .getCvlexauser().getLoginName());
            revisionb.setCvlexatime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, rb.getCvlexatime()));
            rbli.add(revisionb);

        }
        return rbli;
    }

}
