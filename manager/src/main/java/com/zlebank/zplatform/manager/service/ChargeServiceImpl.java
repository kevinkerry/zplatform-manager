/* 
 * ChargeServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月18日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.manager.bean.ChargeBean;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.dao.iface.IChargeDAO;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.manager.enums.ChargeEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IChargeService;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoParaDic;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.trade.utils.OrderNumber;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月18日 下午2:25:47
 * @since
 */
@Service
public class ChargeServiceImpl
        extends
            AbstractBasePageService<ChargeQuery, ChargeBean>
        implements
            IChargeService {
    /** 状态 **/
    private final static String CHARGESTATUS = "CHARGESTATUS";
    private final static String CHARGENOINSTID="CHARGENOINSTID";
    private final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm:ss";
    private final static String CHARGEBUSICODE="90000001";
    
    
    @Autowired
    private IChargeDAO charge;
    @Autowired
    private MemberService ms; 
    @Autowired
    private ParaDicDAO paradic;
    @Autowired
    private AccEntryService accEntyr;
    
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(ChargeQuery example) {
        return charge.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<ChargeBean> getItem(int offset,
            int pageSize,
            ChargeQuery example) {
        List<ChargeBean> chargeLi = new ArrayList<ChargeBean>();
        List<ChargeModel> li = charge.getListByQuery(offset, pageSize, example);
        for (ChargeModel chargePojo : li) {
            ChargeBean chargeBean = BeanCopyUtil.copyBean(ChargeBean.class,
                    chargePojo);
            chargeBean.setAmount(chargePojo.getAmount().toYuan());
            chargeBean.setMemberid(chargePojo.getMemberid().getMemberId());
            chargeBean.setMemberName(chargePojo.getMemberid().getMemberName());
            chargeBean.setIntime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, chargePojo.getIntime()));
            chargeBean.setStexatime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, chargePojo.getStexatime()));
            chargeBean.setCvlexatime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, chargePojo.getCvlexatime()));
            chargeLi.add(chargeBean);
        }
        return chargeLi;
    }

    /**
     *
     * @param cb
     * @throws ManagerWithdrawException 
     * @throws MemberBussinessException 
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void saveCharge(ChargeBean cb,Long userId) throws ManagerWithdrawException, MemberBussinessException {
           if(cb==null||StringUtil.isEmpty(cb.getMemberid())){
               throw new ManagerWithdrawException("G100008");
           }
           String memId=cb.getMemberid();
           PojoMember member=ms.getMbmberByMemberId(memId, null);
           if(member==null){
               throw new ManagerWithdrawException("G100009"); 
           }
         ChargeModel  cm=   BeanCopyUtil.copyBean(ChargeModel.class,member);
         try {
             cm.setAmount( Money.valueOf(new BigDecimal(cb.getAmount()).multiply(new BigDecimal(100))));
         } catch (Exception e) {
            throw new ManagerWithdrawException("G100010"); 
         }
        
         String chargeNo = OrderNumber.getInstance().generateWithdrawOrderNo();
         cm.setChargeno(chargeNo);
         cm.setIntime(new Date());
         cm.setInuser(userId);
         cm.setMemberid(member);
         if(MemberType.fromValue("01")==member.getMemberType()){
             cm.setChargetype(MemberType.INDIVIDUAL.getCode());
         }else if(MemberType.fromValue("02")==member.getMemberType()){
             cm.setChargetype(MemberType.ENTERPRISE.getCode());
         }
         //渠道
         PojoParaDic changeno = paradic.getPrimay(CHARGENOINSTID);
         // 得到状态
         PojoParaDic status = paradic.getPrimay(CHARGESTATUS);
         if (status == null||changeno==null) {
             throw new ManagerWithdrawException("G100011"); 
         }
         cm.setStatus(status.getParaCode());
      
         cm.setChargenoinstid(changeno.getParaCode());
         charge.saveA(cm);
         
    }

    /**
     *
     * @param ftb
     * @throws ManagerWithdrawException 
     * @throws NumberFormatException 
     * @throws AbstractBusiAcctException 
     * @throws AccBussinessException 
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void firstCharge(AuditBean ftb) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException {
        if(ftb==null||StringUtil.isEmpty(ftb.getOrderNo())){
            throw new ManagerWithdrawException("G100013");  
        }
      ChargeModel charges= charge.getChargeByChargeNo(ftb.getOrderNo(),ChargeEnum.FIRSTTRIAL.getCode());
        if(charges==null){
            throw new ManagerWithdrawException("G100014");   
        }
        charges.setStexatime(new Date());
        charges.setStexaopt(ftb.getStexaopt());
        charges.setStexauser(ftb.getStexauser());
        if(ftb.getFalg()==true){
            charges.setStatus(ChargeEnum.SUCCESS.getCode());
            Fused(charges);
        }else{
           charges.setStatus(ChargeEnum.FIRSTREFUSED.getCode()); 
        }
        
    }
    
    /**
     * 审核通过
     * 
     * @return
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    private void Fused(ChargeModel charge) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        // 调用分录规则
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setAmount(charge.getAmount() == null ? new BigDecimal(0) : charge
                .getAmount().getAmount());
        tradeInfo.setBusiCode(CHARGEBUSICODE);
        tradeInfo.setChannelId(charge.getChargenoinstid());
        tradeInfo.setPayMemberId(charge.getMemberid().getMemberId());
        tradeInfo.setPayToMemberId(charge.getMemberid().getMemberId());
        tradeInfo.setTxnseqno(charge.getChargeno());
        tradeInfo.setCommission(new BigDecimal(0));
        tradeInfo.setCharge(new BigDecimal(0));
        accEntyr.accEntryProcess(tradeInfo,EntryEvent.AUDIT_PASS);
    }
}
