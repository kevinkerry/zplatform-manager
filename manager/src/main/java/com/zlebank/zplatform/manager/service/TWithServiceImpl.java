/* 
 * ITxnsWinhdrawServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年11月20日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.BusiAcctQuery;
import com.zlebank.zplatform.acc.bean.QueryAccount;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.AcctStatusType;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.BusinessServiec;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.CardBin;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.dao.CardBinDao;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawBean;
import com.zlebank.zplatform.manager.bean.TxnsWithdrawQuery;
import com.zlebank.zplatform.manager.dao.iface.ITWithdrawDAO;
import com.zlebank.zplatform.manager.dao.object.TxnsWithdrawModel;
import com.zlebank.zplatform.manager.enums.ReviewEnum;
import com.zlebank.zplatform.manager.enums.TransFerDataStatusEnum;
import com.zlebank.zplatform.manager.enums.WithdrawalsBusCodeEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.exception.TradeException;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.manager.service.iface.ITWithService;
import com.zlebank.zplatform.member.bean.enums.BusinessActorType;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.exception.MemberBussinessException;
import com.zlebank.zplatform.member.pojo.PojoMember;
import com.zlebank.zplatform.member.pojo.PojoMerchDeta;
import com.zlebank.zplatform.member.pojo.PojoParaDic;
import com.zlebank.zplatform.member.service.MemberService;
import com.zlebank.zplatform.member.service.MerchService;
import com.zlebank.zplatform.member.service.PersonService;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoTransferData;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年11月20日 上午10:40:45
 * @since
 */

@Service
public class TWithServiceImpl
        extends
            AbstractBasePageService<TxnsWithdrawQuery, TxnsWithdrawBean>
        implements
            ITWithService {
    public final static String DEFAULT_TIME_STAMP_FROMAT = "yyyy-MM-dd HH:mm:ss";
    /** 批次号序列 **/
    private final static String SEQUENCES = "seq_t_txns_withdraw_batchno";
    /** 收款方Id **/
    private final static String PAYTOMEMBERID = "999999999999999";
    private final static String PERSON = "0";
    private final static String MERCH = "1";
    /** 状态 **/
    private final static String PARATYPE = "DRAWSTATUS";
    /** 民生银行code **/
    private final static String TOTALBANKCODE = "0305";
    /** 对公账户 **/
    private final static String PUBLICACC = "01";
    /** 私人账户 **/
    private final static String PRIVATEACC = "00";
    private final static String BUSINESSCODE="30000001";
    private final static String  BUSINESSTYPE="3000";
    /** 民生 **/
    private final static String CMBC = "01";
    /** 其他 **/
    private final static String OTHER = "02";
    @Autowired
    private ParaDicDAO paradic;
    @Autowired
    private MemberService members;
    @Autowired
    private MerchService merch;
    @Autowired
    private PersonService person;
    @Autowired
    private BusinessServiec business;
    @Autowired
    private ITWithdrawDAO tw;
    @Autowired
    private CardBinDao cardbin;
    @Autowired
    private AccEntryService accEntyr;
    @Autowired
    private IRiskService risk;

    @Autowired
    private TransferDataDAO transdata;
    @Autowired
    private TransferBatchDAO transbatch;

    /**
     * 提现申请
     * 
     * @param twb
     * @return
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Map<String, Object> saveTxnsWinhdraw(QueryAccount qa,
            TxnsWithdrawBean twb,
            Long userId) {

        Map<String, Object> messg = new HashMap<String, Object>();

        boolean falg = false;
        if (twb != null) {
            Map<String, Object> map = this.risk(twb);
            if (map != null && (Boolean) map.get("falg") == false) {
                messg.put("messg", map.get("messg"));
                return messg;
            }
            try {

                int i = 1;
                PagedResult<BusiAcctQuery> pr = members.getBusiAccount(qa, i,
                        Integer.MAX_VALUE);
                // 账户余额
                BusiAcctQuery baq = pr.getPagedResult().get(0);
                // 提现金额
                BigDecimal with = new BigDecimal(StringUtil.isEmpty(twb
                        .getAmount()) ? "0" : twb.getAmount())
                        .multiply(new BigDecimal(100));
                // 可用余额
                BigDecimal balance = baq.getBalance().getAmount();
                // 手续费

                /*
                 * 如果提现金额+手续费>账户也 不让提现 如果手续费>账户余额不让提现
                 */
                BigDecimal fee = new BigDecimal(StringUtil.isNotEmpty(twb
                        .getFee()) ? twb.getFee() : "0")
                        .multiply(new BigDecimal(100));
                // -1小于
                if (balance.compareTo(with.add(fee)) == -1
                        || balance.compareTo(fee) == -1

                ) {
                    messg.put("messg", "账户余额不足");
                    return messg;
                }
                // 得到状态
                PojoParaDic status = paradic.getPrimay(PARATYPE);
                if (status == null) {
                    messg.put("messg", "内部错误:字典表配置错误");
                    return messg;
                }

                TxnsWithdrawModel txnsw = BeanCopyUtil.copyBean(
                        TxnsWithdrawModel.class, twb);
                txnsw.setIntime(new Date());
                txnsw.setStatus(status.getParaCode());
                txnsw.setInuser(userId);
                txnsw.setAmount(Money.valueOf(with));
                txnsw.setFee(Money.valueOf(fee));
                txnsw.setId(99L);
                // 调用分录规则
                TradeInfo tradeInfo = new TradeInfo();
                tradeInfo.setAmount(with.add(fee));
                tradeInfo.setBusiCode(WithdrawalsBusCodeEnum.APPLY.getCode());
                tradeInfo.setPayMemberId(twb.getMemberid());
                tradeInfo.setPayToMemberId(PAYTOMEMBERID);
                tradeInfo.setTxnseqno(twb.getTxnseqNo());
                tradeInfo.setPayordno(twb.getWithdraworderno());
                tradeInfo.setCommission(new BigDecimal(0));
                tradeInfo.setCharge(fee);
                // 查看余额
                accEntyr.accEntryProcess(tradeInfo);
                tw.saveA(txnsw);
                falg = true;
                messg.put("messg", "操作成功");
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MemberBussinessException e) {
                messg.put("messg", e.getMessage());
            } catch (AccBussinessException e) {
                messg.put("messg", e.getMessage());
            } catch (AbstractBusiAcctException e) {
                messg.put("messg", e.getMessage());
            } catch (NumberFormatException e) {
                messg.put("messg", e.getMessage());
            }

        }
        messg.put("falg", falg);
        return messg;

    }

    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(TxnsWithdrawQuery example) {
        return tw.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<TxnsWithdrawBean> getItem(int offset,
            int pageSize,
            TxnsWithdrawQuery example) {
        List<TxnsWithdrawBean> li = new ArrayList<TxnsWithdrawBean>();
        List<TxnsWithdrawModel> twm = tw.getListByQuery(offset, pageSize,
                example);
        if (twm == null) {
            return null;
        }
        for (TxnsWithdrawModel tw : twm) {
            TxnsWithdrawBean txnswithban = BeanCopyUtil.copyBean(
                    TxnsWithdrawBean.class, tw);
            txnswithban.setAmount(tw.getAmount() == null ? "" : tw.getAmount()
                    .toYuan());
            txnswithban.setFee(tw.getFee() == null ? "" : tw.getFee().toYuan());
            txnswithban.setIntime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, tw.getIntime()));
            txnswithban.setStexatime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, tw.getStexatime()));
            txnswithban.setCvlexatime(DateUtil.formatDateTime(
                    DEFAULT_TIME_STAMP_FROMAT, tw.getCvlexatime()));
            li.add(txnswithban);
        }

        return li;
    }

    /**
     * 提现预申请
     * 
     * @param qa
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Map<String, Object> getTxnsWinth(QueryAccount qa) {
        Map<String, Object> map = new HashMap<String, Object>();
        TxnsWithdrawBean txnsWinth = new TxnsWithdrawBean();
        String memberId = qa.getMemberId();
        PojoMember pm = members.getMbmberByMemberId(memberId, null);
        if (pm == null) {
            map.put("messg", "会员不存在");
            return map;
        }
        if (pm.getMembertype() == null) {
            map.put("messg", "会员不合法");
            return map;
        }
        // 个人账户+银行卡信息
        if (BusinessActorType.INDIVIDUAL == pm.getMembertype()) {

        }
        // 商户账户+银行卡信息
        else if (BusinessActorType.ENTERPRISE == pm.getMembertype()) {
            // 商户信息
            PojoMerchDeta merchPojo = merch.getMerchBymemberId(memberId);
            if (merchPojo == null) {
                map.put("messg", "会员不合法");
                return map;
            }
            if (merchPojo.getAccnum() == null) {
                map.put("messg", "该账户无卡号,无法提现");
                return map;
            }
            CardBin card = cardbin.getCard(merchPojo.getAccnum());
            if (card == null) {
                map.put("messg", "账户银行卡信息错误,无法提现");
                return map;
            }
            /*
             * PojoMerchDeta pMerchPojo = merch.getMerchBymemberId(merchPojo
             * .getParent());
             */
            // 账户信息
            int i = 1;
            PagedResult<BusiAcctQuery> pr = members.getBusiAccount(qa, i,
                    Integer.MAX_VALUE);
            // 数据不合法
            if (pr.getTotal() > 1 || pr.getTotal() <= 0) {
                map.put("messg", "数据不合法");
                return map;
            }
            try {
                BusiAcctQuery baq = pr.getPagedResult().get(0);
                if (AcctStatusType.NORMAL != baq.getStatus()
                        && AcctStatusType.STOP_IN != baq.getStatus()) {
                    map.put("messg", "账户被锁不能提现");
                    return map;
                }
                if (StringUtil.isEmpty(merchPojo.getParent())) {
                    txnsWinth.setMerchId(String.valueOf(merchPojo.getMemId()));
                } else {
                    PojoMerchDeta pMerchPojo = merch
                            .getMerchBymemberId(merchPojo.getParent());
                    if (pMerchPojo == null) {
                        map.put("messg", "一级商户不存在");
                        return map;
                    }
                    txnsWinth.setBankcode(pMerchPojo.getBanknode() == null
                            ? pMerchPojo.getBankcode()
                            : pMerchPojo.getBanknode());
                    txnsWinth.setMerchId(merchPojo.getParent());
                    txnsWinth
                            .setSubMerchId(String.valueOf(merchPojo.getMemId()));
                }
                txnsWinth.setCardType(card.getType());
                // 银行主行号
                txnsWinth.setTotalBankCode(card.getBankCode());
                TxnsLogModel txnsLog = new TxnsLogModel();
                String withdraworderno = risk.generateWithdrawOrderNo();
                String txnseqNo = risk.generateWithdrawOrderNo();
                txnsWinth.setTxnseqNo(txnseqNo);
                txnsWinth.setWithdraworderno(withdraworderno);
                txnsLog.setTxnseqno(txnseqNo);
                // 交易序列号，扣率版本，业务类型，交易金额，会员号，原交易序列号，卡类型
                txnsLog.setFeever(merchPojo.getFeever() == null
                        ? ""
                        : merchPojo.getFeever());
                txnsLog.setBusicode(WithdrawalsBusCodeEnum.APPLY.getCode());
                txnsLog.setAccfirmerno(merchPojo.getParent() == null
                        ? memberId
                        : merchPojo.getParent());
                txnsLog.setCardtype(txnsWinth.getCardType());
                txnsWinth.setBankname(card.getBankName());
                txnsWinth.setMemberid(memberId);
                txnsWinth.setWithdrawtype(MERCH);
                txnsWinth.setAcctno(merchPojo.getAccnum());
                txnsWinth.setAcctname(merchPojo.getAccname());
                map.put("txns", txnsWinth);
                // 手续费
                map.put("txnsLog", txnsLog);
                map.put("balance", baq.getBalance().toYuan());
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return map;

    }
    /***
     * 得到提现手续费
     * 
     * @param txns
     * @return
     * @throws ManagerWithdrawException
     */
    public Long getTxnFee(TxnsLog txns) throws ManagerWithdrawException {
        if (StringUtil.isEmpty(txns.getAmount())) {
            throw new ManagerWithdrawException("G100005");

        }

        TxnsLogModel tlm = BeanCopyUtil.copyBean(TxnsLogModel.class, txns);
        tlm.setAmount(new BigDecimal(txns.getAmount()).multiply(
                new BigDecimal(100)).longValue());
        return risk.getTxnFee(tlm);

    }

    /**
     * @param merchId
     *            一级商户号
     * @param subMerchId
     *            二级商户号
     * @param memberId
     *            会员id
     * @param busiCode
     *            业务code
     * @param txnAmt
     *            金额
     * @param cardType
     *            卡类型
     * @param cardNo
     *            卡号
     */
    private Map<String, Object> risk(TxnsWithdrawBean twb) {
        boolean isok = true;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            risk.tradeRiskControl(twb.getMerchId(), twb.getSubMerchId(), twb
                    .getMemberid(), WithdrawalsBusCodeEnum.APPLY.getCode(),
                    String.valueOf(twb.getAmount() == null ? "" : twb
                            .getAmount()), twb.getCardType(), twb.getAcctno());
            map.put("falg", isok);
        } catch (TradeException e) {
            isok = false;
            map.put("falg", isok);
            map.put("messg", e.getMessage());
        }
        return map;
    }

    /**
     *
     * @param firstTrial
     * @return
     * @throws ManagerWithdrawException
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void firstTrialWinth(AuditBean firstTrial)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        // 如果提现bean不等于空 做提现操作 等于空返回错误信息
        if (firstTrial == null) {

            throw new ManagerWithdrawException("G100001");
        }
        String with = firstTrial.getOrderNo();
        if (StringUtil.isEmpty(with)) {
            throw new ManagerWithdrawException("G100002");
        }
        String[] withdraworderno = with.split("\\|");
        if (withdraworderno.length <= 0) {
            throw new ManagerWithdrawException("G100002");
        }

        for (int i = 0; i < withdraworderno.length; i++) {
            // 如果这个批次有数据查询不到返回错误信息
            TxnsWithdrawModel txns = tw.getTxnsWithdrawByorderNo(
                   null, ReviewEnum.FIRSTTRIAL.getCode(), withdraworderno[i]);
            if (txns == null) {
                throw new ManagerWithdrawException("G100003");
            }
            txns.setStexauser(firstTrial.getStexauser());
            txns.setStexaopt(firstTrial.getStexaopt());
            txns.setStexatime(new Date());
            // 初审通过
            if (firstTrial.getFalg() == true) {
                txns.setStatus(ReviewEnum.SECONDTRIAL.getCode());
            } else {
                // 初审拒绝
                txns.setStatus(ReviewEnum.FIRSTREFUSED.getCode());
                Fused(txns);
            }

            // txns.setBatchno(batchno);
        }

    }

    /**
     * 审核拒绝
     * 
     * @return
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    private void Fused(TxnsWithdrawModel txns) throws AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        // 调用分录规则
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setAmount(txns.getAmount() == null ? new BigDecimal(0) : txns
                .getAmount().getAmount());
        tradeInfo.setBusiCode(WithdrawalsBusCodeEnum.REFUSE.getCode());
        tradeInfo.setPayMemberId(txns.getMemberid());
        tradeInfo.setPayToMemberId(PAYTOMEMBERID);
        tradeInfo.setTxnseqno(txns.getTxnseqNo());
        tradeInfo.setPayordno(txns.getWithdraworderno());
        tradeInfo.setCommission(new BigDecimal(0));
        tradeInfo.setCharge(txns.getFee().getAmount());
        accEntyr.accEntryProcess(tradeInfo);

    }

    /**
     *
     * @param secondTrial
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void secondTrialWinth(AuditBean secondTrial)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        if (secondTrial == null) {
            throw new ManagerWithdrawException("G100001");
        }
        String with = secondTrial.getOrderNo();
        if (StringUtil.isEmpty(with)) {
            throw new ManagerWithdrawException("G100002");
        }
        String[] withdraworderno = with.split("\\|");
        if (withdraworderno.length <= 0) {
            throw new ManagerWithdrawException("G100002");
        }
        for (int i = 0; i < withdraworderno.length; i++) {
            // 如果这个批次里面有数据为空返回错误信息
            TxnsWithdrawModel txns = tw.getTxnsWithdrawByorderNo(
                 null  , ReviewEnum.SECONDTRIAL.getCode(), withdraworderno[i]);
            if (txns == null) {
                throw new ManagerWithdrawException("G100003");
            }

            txns.setCvlexauser(secondTrial.getStexauser());
            txns.setCvlexaopt(secondTrial.getStexaopt());
            txns.setCvlexatime(new Date());
            // 复审通过
            if (secondTrial.getFalg() == true) {
                // 如果有民生银行的卡 单独处理
                if (TOTALBANKCODE.equals(txns.getTotalBankCode())) {
                    
                    PojoTransferData pojotransDate = saveTransfer(txns);
                    pojotransDate.setTransfertype(CMBC);
                    pojotransDate.setCreatetime(new Date());
                } else {
                    PojoTransferData pojotransDate = saveTransfer(txns);
                    pojotransDate.setTransfertype(OTHER);
                    pojotransDate.setCreatetime(new Date());

                }

            } else {
                // 复审拒绝
                txns.setStatus(ReviewEnum.SECONREFUSED.getCode());
                Fused(txns);
            }

        }
    }
    /**
     * 新增划拨数据
     */
    private PojoTransferData saveTransfer(TxnsWithdrawModel txns) {
        txns.setStatus(ReviewEnum.BATCH.getCode());
        // 通过后需要将数据记录到划拨明细表中 并且将数据统计写入划拨表中
        PojoTransferData pojotransDate = new PojoTransferData();
        // 批次号
        pojotransDate.setBatchno(txns.getBatchno());
        pojotransDate.setMemberid(txns.getMemberid());
        // 订单号
        pojotransDate.setTranid(risk.generateWithdrawOrderNo());
        // 账户类型
        pojotransDate.setAcctype(PUBLICACC);
        // 账号
        pojotransDate.setAccno(txns.getAcctno());
        // 户名
        pojotransDate.setAccname(txns.getAcctname());
        pojotransDate.setTxnfee(txns.getFee()==null?new BigDecimal(0):txns.getFee().getAmount());
        // 支行代码
        pojotransDate.setBanktype(txns.getBankcode());
        pojotransDate.setBankname(txns.getBankname());
        pojotransDate.setTransamt(txns.getAmount().getAmount().longValue());
        pojotransDate.setStatus(TransFerDataStatusEnum.FIRSTTRIAL.getCode());
        pojotransDate.setBusicode(BUSINESSCODE);
        pojotransDate.setBusitype(BUSINESSTYPE);
        pojotransDate.setRelatedorderno(txns.getWithdraworderno());
        pojotransDate.setTxnseqno(txns.getTxnseqNo());
        // transdata.merge(pojotransDate);
        return transdata.merge(pojotransDate);
    }

    /**
     * 划拨拒绝
     * 
     * @return
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    public void trialBatch(Boolean falg, TxnsWithdrawModel txns)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {
        if (falg == false) {
            Fused(txns);
            txns.setStatus(ReviewEnum.BATCHFAILURE.getCode());
        } 

    }

}
