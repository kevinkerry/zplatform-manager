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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.exception.IllegalEntryRequestException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.pojo.BusiTypeEnum;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.AuditBean;
import com.zlebank.zplatform.manager.bean.ChargeBean;
import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.dao.iface.IChargeDAO;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.manager.enums.ChargeEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IChargeService;
import com.zlebank.zplatform.member.bean.enums.MemberType;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.pojo.PojoParaDic;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.trade.bean.enums.BusinessEnum;
import com.zlebank.zplatform.trade.bean.gateway.SplitAcctBean;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.TxnsLogModel;
import com.zlebank.zplatform.trade.model.TxnsOrderinfoModel;
import com.zlebank.zplatform.trade.service.IGateWayService;
import com.zlebank.zplatform.trade.service.ITxnsLogService;
import com.zlebank.zplatform.trade.utils.OrderNumber;
import com.zlebank.zplatform.trade.utils.UUIDUtil;

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
    private final static String COOPINSTICODE="300000000000004";
    
    
    @Autowired
    private IChargeDAO charge;
    @Autowired
    private MemberService ms; 
    @Autowired
    private ParaDicDAO paradic;
    @Autowired
    private AccEntryService accEntyr;
    @Autowired
    private ITxnsLogService txnsLogService;
    @Autowired
    private MerchService merchService;
    @Autowired
    private IGateWayService gateWayService;
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
     * @throws TradeException 
     * @throws IllegalEntryRequestException 
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void firstCharge(AuditBean ftb) throws ManagerWithdrawException, AccBussinessException, AbstractBusiAcctException, NumberFormatException, TradeException, IllegalEntryRequestException {
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
     * @throws TradeException 
     * @throws IllegalEntryRequestException 
     */
    private void Fused(ChargeModel charge) throws AccBussinessException,AbstractBusiAcctException, NumberFormatException, TradeException ,IllegalEntryRequestException{
        
        //记录交易流水
        TxnsLogModel txnsLog = new TxnsLogModel();

        txnsLog.setTxnseqno(OrderNumber.getInstance().generateTxnseqno(BusiTypeEnum.charge.getCode()));
        //记录交易流水
        String charge_memberId = charge.getMemberid().getMemberId();
        if(MemberType.INDIVIDUAL==charge.getMemberid().getMemberType()){//为个人会员时
            txnsLog.setRiskver(getDefaultVerInfo(COOPINSTICODE,BusinessEnum.CHARGE_OFFLINE.getBusiCode(),13));
            txnsLog.setSplitver(getDefaultVerInfo(COOPINSTICODE,BusinessEnum.CHARGE_OFFLINE.getBusiCode(),12));
            txnsLog.setFeever(getDefaultVerInfo(COOPINSTICODE,BusinessEnum.CHARGE_OFFLINE.getBusiCode(),11));
            txnsLog.setPrdtver(getDefaultVerInfo(COOPINSTICODE,BusinessEnum.CHARGE_OFFLINE.getBusiCode(),10));
            txnsLog.setRoutver(getDefaultVerInfo(COOPINSTICODE,BusinessEnum.CHARGE_OFFLINE.getBusiCode(),20));
            txnsLog.setAccsettledate(DateUtil.getSettleDate(1));
        }else{
            PojoMerchDeta member = merchService.getMerchBymemberId(charge_memberId);
            txnsLog.setRiskver(member.getRiskVer());
            txnsLog.setSplitver(member.getSpiltVer());
            txnsLog.setFeever(member.getFeeVer());
            txnsLog.setPrdtver(member.getPrdtVer());
           // txnsLog.setCheckstandver(member.getCashver());
            txnsLog.setRoutver(member.getRoutVer());
            txnsLog.setAccordinst(member.getParent());
            txnsLog.setAccsettledate(DateUtil.getSettleDate(Integer.valueOf(member.getSetlCycle().toString())));
        }
        txnsLog.setTxndate(DateUtil.getCurrentDate());
        txnsLog.setTxntime(DateUtil.getCurrentTime());
        txnsLog.setBusicode(BusinessEnum.CHARGE_OFFLINE.getBusiCode());
        txnsLog.setBusitype("9000");
        txnsLog.setTxnseqno(OrderNumber.getInstance().generateTxnseqno(txnsLog.getBusicode()));
        txnsLog.setAmount(charge.getAmount().getAmount().longValue());
        txnsLog.setAccordno(charge.getChargeno());
        txnsLog.setAccfirmerno(COOPINSTICODE);
        if(MemberType.INDIVIDUAL==charge.getMemberid().getMemberType()){
             txnsLog.setAccsecmerno("");
        }else{
            txnsLog.setAccsecmerno(charge.getMemberid().getMemberId());
        }
        txnsLog.setAcccoopinstino(COOPINSTICODE);
        txnsLog.setAccordinst(COOPINSTICODE);
        txnsLog.setAccordcommitime(DateUtil.getCurrentDateTime());
        txnsLog.setAccordfintime(DateUtil.getCurrentDateTime());
        txnsLog.setPaytype("06");//手工充值
        txnsLog.setPayordno("");//手工充值无支付订单号
        txnsLog.setPayfirmerno(charge.getMemberid().getMemberId());
        txnsLog.setPayinst("99999999");//内部支付渠道代码
        txnsLog.setPayordcomtime(DateUtil.getCurrentDateTime());
        txnsLog.setPayordfintime(DateUtil.getCurrentDateTime());
        txnsLog.setPayretcode("0000");
        txnsLog.setPayretinfo("交易成功");
        txnsLog.setRetcode("0000");
        txnsLog.setRetinfo("交易成功");
        txnsLog.setAppinst("000000000000");
        txnsLog.setAppordcommitime(DateUtil.getCurrentDateTime());
        txnsLog.setAppordfintime(DateUtil.getCurrentDateTime());
        txnsLog.setTradeseltxn(UUIDUtil.uuid().replaceAll("-", ""));
        txnsLog.setRetdatetime(DateUtil.getCurrentDateTime());
        txnsLog.setApporderstatus("00");
        txnsLog.setApporderinfo("手工充值账务处理成功");
        txnsLog.setAccbusicode(CHARGEBUSICODE);
        txnsLog.setTradetxnflag("10000000");
        txnsLog.setTradestatflag("00000001");//交易状态
        txnsLog.setRelate("10000000");
        txnsLog.setTxnfee(0L);
        txnsLog.setAccmemberid(charge.getMemberid().getMemberId());
        txnsLogService.save(txnsLog);
        
        TxnsOrderinfoModel orderinfo = gateWayService.getOrderinfoByOrderNoAndMemberId(txnsLog.getAccordno(), txnsLog.getAccsecmerno());
        if(orderinfo!=null){
        	orderinfo.setStatus("00");
        	orderinfo.setRelatetradetxn(txnsLog.getTxnseqno());
        	gateWayService.update(orderinfo);
        }
        // 调用分录规则
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setAmount(charge.getAmount() == null ? new BigDecimal(0) : charge
                .getAmount().getAmount());
        tradeInfo.setBusiCode(CHARGEBUSICODE);
        tradeInfo.setChannelId(charge.getChargenoinstid());
        tradeInfo.setPayMemberId(charge.getMemberid().getMemberId());
        tradeInfo.setPayToMemberId(charge.getMemberid().getMemberId());
        tradeInfo.setTxnseqno(txnsLog.getTxnseqno());
        tradeInfo.setCommission(new BigDecimal(0));
        tradeInfo.setCharge(new BigDecimal(0));
        accEntyr.accEntryProcess(tradeInfo,EntryEvent.AUDIT_PASS);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public String getDefaultVerInfo(String instiCode,String busicode,int verType) throws TradeException{
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) txnsLogService.queryBySQL("select COOP_INSTI_CODE,BUSI_CODE,VER_TYPE,VER_VALUE from T_NONMER_DEFAULT_CONFIG where COOP_INSTI_CODE=? and BUSI_CODE=? and VER_TYPE=?", new Object[]{instiCode,busicode,verType+""});
        if(resultList.size()>0){
            Map<String, Object> valueMap = resultList.get(0);
            return valueMap.get("VER_VALUE").toString();
        }
        throw new TradeException("GW03");
        //return null;
    }
}
