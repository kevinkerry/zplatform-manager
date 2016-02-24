/* 
 * TransferServiceImpl.java  
 * 
 * version TODO
 *
 * 2015年12月8日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.pojo.Money;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.dao.iface.ITWithdrawDAO;
import com.zlebank.zplatform.manager.dao.object.TxnsWithdrawModel;
import com.zlebank.zplatform.manager.enums.InsteadEnum;
import com.zlebank.zplatform.manager.enums.ReviewEnum;
import com.zlebank.zplatform.manager.enums.TransFerDataStatusEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.iface.IInsteadPayService;
import com.zlebank.zplatform.manager.service.iface.ITWithService;
import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.member.util.MemberUtil;
import com.zlebank.zplatform.trade.dao.InsteadPayDetailDAO;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoInsteadPayDetail;
import com.zlebank.zplatform.trade.model.PojoTransferBatch;
import com.zlebank.zplatform.trade.model.PojoTransferData;

/**
 * Class Description
 *
 * @author yangpeng
 * @version
 * @date 2015年12月8日 下午4:17:10
 * @since
 */
@Service
public class TransferServiceImpl
        extends
            AbstractBasePageService<TransferDataQuery, TransferData>
        implements
            ITransferService {

    @Autowired
    private ParaDicDAO primayDao;
    @Autowired
    private TransferDataDAO transferDao;
    @Autowired
    private ITWithdrawDAO withdao;
    @Autowired
    private TransferBatchDAO transbatch;
    @Autowired
    private ITWithService itws;
    @Autowired
    private IInsteadPayService instead;
    @Autowired
    private InsteadPayDetailDAO insteadPayDetailDAO;

    /** 行内 **/
    private final static String CMBC = "01";
    /** 行外 **/
    private final static String OTHER = "02";
    /** 提现 **/
    private final static String WITH = "3000";
    /** 代付 **/
    private final static String INSTEAD = "7000";

    /** 批次号序列 **/
    private final static String SEQUENCES = "seq_t_txns_withdraw_batchno";
    /** 民生渠道 **/
    private final static String CMBCCHNL = "93000001";
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(TransferDataQuery example) {
        return transferDao.count(example);
    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<TransferData> getItem(int offset,
            int pageSize,
            TransferDataQuery example) {
        List<PojoTransferData> transData = transferDao.getListByQuery(offset,
                pageSize, example);
        List<TransferData> li = new ArrayList<TransferData>();
        for (PojoTransferData potrans : transData) {
            TransferData tran = BeanCopyUtil.copyBean(TransferData.class,
                    potrans);

            tran.setFee(Money.valueOf(
                    potrans.getTxnfee() == null ? new BigDecimal(0) : potrans
                            .getTxnfee()).toYuan());
            tran.setMoney(Money.valueOf(
                    new BigDecimal(potrans.getTransamt() == null ? 0L : potrans
                            .getTransamt())).toYuan());
            tran.setCreatetime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getCreatetime()));
            tran.setCvlexatime(DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getCvlexatime()));
            tran.setStexatime((DateUtil.formatDateTime(
                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getStexatime())));
            li.add(tran);
        }
        return li;
    }

    /**
     *
     * @param ftb
     * @throws ManagerWithdrawException
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void firstAudit(AuditBean ftb, List<PojoTransferData> pjfd)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {

        if (ftb == null) {
            throw new ManagerWithdrawException("G100001");
        }
        if (pjfd == null || pjfd.isEmpty()) {

            String with = ftb.getOrderNo();
            if (StringUtil.isEmpty(with)) {
                throw new ManagerWithdrawException("G100004");
            }
            String[] tid = with.split("\\|");
            if (tid.length <= 0) {
                throw new ManagerWithdrawException("G100004");
            }

            for (int i = 0; i < tid.length; i++) {

                String tranid = tid[i];
                PojoTransferData trans = transferDao.getTransferDataByTranId(
                        tranid, TransFerDataStatusEnum.FIRSTTRIAL.getCode());
                if (trans == null) {
                    throw new ManagerWithdrawException("G100003");
                }
                trans.setStexauser(ftb.getStexauser());
                trans.setStexaopt(ftb.getStexaopt());
                trans.setStexatime(new Date());
                // 初审通过
                if (ftb.getFalg() == true) {
                    trans.setStatus(TransFerDataStatusEnum.SECONDTRIAL
                            .getCode());
                } else {
                    // 初审拒绝
                    trans.setStatus(TransFerDataStatusEnum.FIRSTREFUSED
                            .getCode());
                    firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
                }

            }
        } else {
            for (PojoTransferData trans : pjfd) {
                trans.setStexauser(ftb.getStexauser());
                trans.setStexaopt(ftb.getStexaopt());
                trans.setStexatime(new Date());
                // 初审通过
                if (ftb.getFalg() == true) {
                    trans.setStatus(TransFerDataStatusEnum.SECONDTRIAL
                            .getCode());
                } else {
                    // 初审拒绝
                    trans.setStatus(TransFerDataStatusEnum.FIRSTREFUSED
                            .getCode());
                    firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
                }
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private void senond(AuditBean ftb, List<PojoTransferData> pjfd)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        boolean isok = true;
        // 民生金额
        BigDecimal CMBCsumAmount = new BigDecimal(0);
        // 普通金额
        BigDecimal sumAmount = new BigDecimal(0);
        Long CMBCCount = 1L;
        Long count = 1L;
        PojoTransferBatch cmbctransferBatch = null;
        PojoTransferBatch transferBatch = null;

        // 普通批次号
        String batchno = null;
        // 民生批次号
        String CMBCBatchno = null;
        // 得到所有的明细

        for (int i = 0; i < pjfd.size(); i++) {

            PojoTransferData trans = pjfd.get(i);
            if (trans == null) {
                throw new ManagerWithdrawException("G100003");
            }
            trans.setCvlexauser(ftb.getStexauser());
            trans.setCvlexaopt(ftb.getStexaopt());
            trans.setCvlexatime(new Date());

            // 复审通过
            if (ftb.getFalg() == true) {
                trans.setStatus(TransFerDataStatusEnum.INITIAL.getCode());
                // 民生
                if ("01".equals(trans.getTransfertype())) {
                    // 生成批次号
                    if (StringUtil.isEmpty(CMBCBatchno)) {
                        CMBCBatchno = getBatchno();
                    }
                    // 民生银行的做一个批次
                    trans.setBatchno(CMBCBatchno);

                    if (cmbctransferBatch == null) {
                        cmbctransferBatch = new PojoTransferBatch();
                    }
                    if (StringUtil.isEmpty(cmbctransferBatch.getBatchno())) {
                        // 民生批次号
                        cmbctransferBatch.setBatchno(trans.getBatchno());

                        // 状态
                        cmbctransferBatch
                                .setStatus(TransFerDataStatusEnum.INITIAL
                                        .getCode());
                        cmbctransferBatch.setTransfertype(CMBC);
                        cmbctransferBatch.setCreatetime(DateUtil
                                .getCurrentDate());

                    }
                    // 总条数
                    cmbctransferBatch.setSumitem(CMBCCount);
                    // 金额
                    CMBCsumAmount = new BigDecimal(trans.getTransamt())
                            .add(CMBCsumAmount);
                    cmbctransferBatch.setBusicode(trans.getBusicode());
                    cmbctransferBatch.setBusitype(trans.getBusitype());
                    cmbctransferBatch.setSumamount(CMBCsumAmount.longValue());
                    cmbctransferBatch.setBatchno(CMBCBatchno);
                    CMBCCount++;

                } else {
                    if (transferBatch == null) {
                        transferBatch = new PojoTransferBatch();
                    }
                    if (StringUtil.isEmpty(batchno)) {
                        // 生成批次号
                        batchno = getBatchno();
                    }
                    trans.setBatchno(batchno);

                    if (StringUtil.isEmpty(transferBatch.getBatchno())) {
                        transferBatch.setBatchno(trans.getBatchno());
                        transferBatch.setStatus(TransFerDataStatusEnum.INITIAL
                                .getCode());
                    }
                    transferBatch.setCreatetime(DateUtil.getCurrentDate());
                    transferBatch.setSumitem(Long.valueOf(count));
                    sumAmount = new BigDecimal(trans.getTransamt())
                            .add(sumAmount);
                    transferBatch.setTransfertype(OTHER);
                    transferBatch.setBusicode(trans.getBusicode());
                    transferBatch.setBusitype(trans.getBusitype());
                    transferBatch.setSumamount(sumAmount.longValue());
                    transferBatch.setBatchno(batchno);
                    count++;

                }

            } else {
                // 复审拒绝
                trans.setStatus(TransFerDataStatusEnum.SECONREFUSED.getCode());
                firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
                isok = false;
            }

        }

        if (isok == true) {
            if (cmbctransferBatch != null) {
                cmbctransferBatch.setChnlcode(CMBCCHNL);
                transbatch.merge(cmbctransferBatch);
            }
            if (transferBatch != null) {
                transferBatch.setChnlcode(CMBCCHNL);
                transbatch.merge(transferBatch);
            }

        }

    }

    /**
     * 将数据分为普通银行和民生
     * 
     * @param transferdate
     * @return
     * @throws ManagerWithdrawException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private Map<String, List<PojoTransferData>> getCmbc(List<PojoTransferData> transferdate)
            throws ManagerWithdrawException {
        Map<String, List<PojoTransferData>> fer = new HashMap<String, List<PojoTransferData>>();
        List<PojoTransferData> cmbc = null;
        List<PojoTransferData> other = null;
        for (PojoTransferData trans : transferdate) {
            if (trans == null) {
                throw new ManagerWithdrawException("G100003");
            }
            // 民生批次
            if ("01".equals(trans.getTransfertype())) {
                if (cmbc == null) {
                    cmbc = new ArrayList<PojoTransferData>();
                }
                cmbc.add(trans);
            }
            // 外行批次
            else {
                if (other == null) {
                    other = new ArrayList<PojoTransferData>();
                }
                other.add(trans);
            }
        }
        if (cmbc != null) {
            fer.put("cmbc", cmbc);
        }
        if (other != null) {
            fer.put("other", other);

        }
        return fer;

    }

    /**
     * 将划拨数据按不同的code进行分类
     * 
     * @param trans
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private Map<String, List<PojoTransferData>> getTransferByCode(List<PojoTransferData> trans) {
        Map<String, List<PojoTransferData>> rtn = new HashMap<String, List<PojoTransferData>>();
        Set<String> busiSet = new HashSet<String>();
        for (PojoTransferData transfer : trans) {
            if (busiSet.contains(transfer.getBusicode())) {
                List<PojoTransferData> tmp = rtn.get(transfer.getBusicode());
                tmp.add(transfer);
                rtn.put(transfer.getBusicode(), tmp);
            } else {
                List<PojoTransferData> tmp = new ArrayList<PojoTransferData>();
                tmp.add(transfer);
                rtn.put(transfer.getBusicode(), tmp);
            }
            busiSet.add(transfer.getBusicode());
        }

        return rtn;
    }

    /**
     * 将数据按四千比一个阶梯分类
     * 
     * @param rtn
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private Map<Integer, List<PojoTransferData>> getTransferByBank(Map<String, List<PojoTransferData>> rtn) {
        Map<Integer, List<PojoTransferData>> map = new HashMap<Integer, List<PojoTransferData>>(); // 用map存起来新的分组后数据
        Iterator<?> iter = rtn.entrySet().iterator();
        int k = 0;
        List<PojoTransferData> lis = null;
        while (iter.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            List<PojoTransferData> li = rtn.get(key);
            if (li.size() > 4000) {
                for (int i = 0; i < li.size(); i += 4000) {
                    if (i + 9 > li.size()) {
                        lis = li.subList(i, li.size());
                    } else {
                        lis = li.subList(i, i + 3999);
                    }
                    map.put(k, lis);
                    k++;
                }
            } else {
                map.put(k, li);
                k++;

            }
        }
        return map;
    }
    /**
     * 根据流水号得到划拨数据
     * 
     * @param with
     * @return
     * @throws ManagerWithdrawException
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private List<PojoTransferData> gettransByNo(String with)
            throws ManagerWithdrawException {
        List<PojoTransferData> li = new ArrayList<PojoTransferData>();
        if (StringUtil.isEmpty(with)) {
            throw new ManagerWithdrawException("G100004");
        }
        String[] tid = with.split("\\|");
        if (tid.length <= 0) {
            throw new ManagerWithdrawException("G100004");
        }
        for (int i = 0; i < tid.length; i++) {
            String tranid = tid[i];
            PojoTransferData trans = transferDao.getTransferDataByTranId(
                    tranid, TransFerDataStatusEnum.SECONDTRIAL.getCode());
            if (trans == null) {
                throw new ManagerWithdrawException("G100003");
            }
            li.add(trans);
        }
        return li;
    }

    /***
     * 划拨拒绝
     * 
     * @param withdraworderno
     * @throws NumberFormatException
     * @throws AbstractBusiAcctException
     * @throws AccBussinessException
     * @throws ManagerWithdrawException
     */
    private void firstrefunsed(String txnseqNo, String businessType)
            throws AccBussinessException, AbstractBusiAcctException,
            NumberFormatException, ManagerWithdrawException {

        if (WITH.equals(businessType)) {
            TxnsWithdrawModel txns = withdao.getTxnsWithdrawByorderNo(txnseqNo,
                    ReviewEnum.BATCH.getCode(), null);
            if (txns == null) {
                throw new ManagerWithdrawException("G100003");
            }
            // 提现审核拒绝
            itws.trialBatch(false, txns);
        } else if (INSTEAD.equals(businessType)) {
            PojoInsteadPayDetail pojoinstead = insteadPayDetailDAO
                    .getDetailByTxnseqno(txnseqNo, InsteadEnum.BATCH.getCode());
            if (pojoinstead == null) {
                throw new ManagerWithdrawException("G100003");
            }
            instead.veto(pojoinstead, InsteadEnum.BATCHFAILURE.getCode());

        }
    }

    // 生成批次号
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    private String getBatchno() {

        List<Map<String, Object>> li = primayDao.getSeq(SEQUENCES);
        Map<String, Object> map = li.get(0);
        String str = map.get("NEXTVAL") + "";
        String batchno = MemberUtil.getMemberID("", str);

        return batchno;
    }

    /**
     *
     * @param ftb
     * @param pjfd
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void secondAudit(AuditBean ftb, List<PojoTransferData> pjfd)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        Map<Integer, List<PojoTransferData>> transin = null;
        if (pjfd == null) {
            pjfd = gettransByNo(ftb.getOrderNo());

        }
        // 按code分类
        Map<String, List<PojoTransferData>> map = this.getTransferByCode(pjfd);
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            List<PojoTransferData> li = map.get(key);
            // 按民生分类
            Map<String, List<PojoTransferData>> transMap = getCmbc(li);
            // 大于四千的进行分类
            transin = getTransferByBank(transMap);

            if (transin != null) {
                Iterator iters = transin.entrySet().iterator();
                while (iters.hasNext()) {
                    Map.Entry entrys = (Map.Entry) iters.next();
                    Object keys = entrys.getKey();
                    List<PojoTransferData> lis = transin.get(keys);
                    senond(ftb, lis);

                }

            }
        };
    }

    /**
     *
     * @param ftb
     * @param tbq
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void secondAuditByConditions(AuditBean ftb,
            TransferDataQuery tbq,
            String falg) throws ManagerWithdrawException,
            AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {

        // 根据条件查询数据
        List<PojoTransferData> li = transferDao.getListByQuery(0,
                Integer.MAX_VALUE, tbq);
        if (li == null || li.isEmpty()) {
            throw new ManagerWithdrawException("G100006");
        }
        if ("first".equals(falg)) {
            this.firstAudit(ftb, li);
        } else {
            this.secondAudit(ftb, li);
        }
    }

}
