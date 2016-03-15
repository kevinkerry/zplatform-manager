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
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.commons.bean.AuditBean;
import com.zlebank.zplatform.commons.bean.PagedResult;
import com.zlebank.zplatform.commons.bean.TransferData;
import com.zlebank.zplatform.commons.bean.TransferDataQuery;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.iface.ITWithdrawDAO;
import com.zlebank.zplatform.manager.enums.TransferTrialEnum;
import com.zlebank.zplatform.manager.exception.ManagerWithdrawException;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IInsteadPayService;
import com.zlebank.zplatform.manager.service.iface.ITWithService;
import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.member.dao.ParaDicDAO;
import com.zlebank.zplatform.trade.batch.spliter.BatchSpliter;
import com.zlebank.zplatform.trade.bean.enums.BusinessEnum;
import com.zlebank.zplatform.trade.bean.page.QueryTransferBean;
import com.zlebank.zplatform.trade.dao.InsteadPayDetailDAO;
import com.zlebank.zplatform.trade.dao.TransferBatchDAO;
import com.zlebank.zplatform.trade.dao.TransferDataDAO;
import com.zlebank.zplatform.trade.model.PojoTranBatch;
import com.zlebank.zplatform.trade.model.PojoTranData;

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
           BaseServiceImpl<PojoTranData, Long>
        implements
            ITransferService {

    /**
     *
     * @param page
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    public PagedResult<TransferData> queryPaged(int page,
            int pageSize,
            TransferDataQuery example) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param ftb
     * @param pjfd
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     * @deprecated
     */
    @Override
    public void firstAudit(AuditBean ftb, List<PojoTranData> pjfd)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        // TODO Auto-generated method stub
        
    }

    /**
     *
     * @param ftb
     * @param pjfd
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     * @deprecated
     */
    @Override
    public void secondAudit(AuditBean ftb, List<PojoTranData> pjfd)
            throws ManagerWithdrawException, AccBussinessException,
            AbstractBusiAcctException, NumberFormatException {
        // TODO Auto-generated method stub
        
    }

    /**
     *
     * @param ftb
     * @param tbq
     * @param falg
     * @throws ManagerWithdrawException
     * @throws AccBussinessException
     * @throws AbstractBusiAcctException
     * @throws NumberFormatException
     * @deprecated
     */
    @Override
    public void secondAuditByConditions(AuditBean ftb,
            TransferDataQuery tbq,
            String falg) throws ManagerWithdrawException,
            AccBussinessException, AbstractBusiAcctException,
            NumberFormatException {
        // TODO Auto-generated method stub
        
    }

    /**
     *
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> queryBatchTransfer(QueryTransferBean queryTransferBean,
            int page,
            int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param queryTransferBean
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> queryDetaTransfer(QueryTransferBean queryTransferBean,
            int page,
            int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     * @param batchNo
     * @param flag
     * @return
     */
    @Override
    public boolean transferBatchTrial(String batchNo, boolean flag) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     *
     * @param tranDataSeqNo
     * @param flag
     * @return
     */
    @Override
    public boolean transferDataTrial(String tranDataSeqNo, boolean flag) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public IBaseDAO<PojoTranData, Long> getDao() {
        // TODO Auto-generated method stub
        return null;
    }
//
//    @Autowired
//    private ParaDicDAO primayDao;
//    @Autowired
//    private TransferDataDAO transferDao;
//    @Autowired
//    private ITWithdrawDAO withdao;
//    @Autowired
//    private TransferBatchDAO transbatch;
//    @Autowired
//    private ITWithService itws;
//    @Autowired
//    private IInsteadPayService instead;
//    @Autowired
//    private InsteadPayDetailDAO insteadPayDetailDAO;
//
//    @Autowired
//	private TransferBatchDAO transferBatchDAO;
//	@Autowired
//	private TransferDataDAO transferDataDAO;
//	@Autowired
//    private AccEntryService accEntryService;
//	@Autowired
//	private BatchSpliter batchSpliter;
//	
//    /** 行内 **/
//    private final static String CMBC = "01";
//    /** 行外 **/
//    private final static String OTHER = "02";
//    /** 提现 **/
//    private final static String WITH = "3000";
//    /** 代付 **/
//    private final static String INSTEAD = "7000";
//
//    /** 批次号序列 **/
//    private final static String SEQUENCES = "seq_t_txns_withdraw_batchno";
//    /** 民生渠道 **/
//    private final static String CMBCCHNL = "93000001";
//    
//
//    /**
//     *
//     * @param offset
//     * @param pageSize
//     * @param example
//     * @return
//     *//*
//    @Override
//    protected List<TransferData> getItem(int offset,
//            int pageSize,
//            TransferDataQuery example) {
//        List<PojoTransferData> transData = transferDao.getListByQuery(offset,
//                pageSize, example);
//        List<TransferData> li = new ArrayList<TransferData>();
//        for (PojoTransferData potrans : transData) {
//            TransferData tran = BeanCopyUtil.copyBean(TransferData.class,
//                    potrans);
//
//            tran.setFee(Money.valueOf(
//                    potrans.getTxnfee() == null ? new BigDecimal(0) : potrans
//                            .getTxnfee()).toYuan());
//            tran.setMoney(Money.valueOf(
//                    new BigDecimal(potrans.getTransamt() == null ? 0L : potrans
//                            .getTransamt())).toYuan());
//            tran.setCreatetime(DateUtil.formatDateTime(
//                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getCreatetime()));
//            tran.setCvlexatime(DateUtil.formatDateTime(
//                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getCvlexatime()));
//            tran.setStexatime((DateUtil.formatDateTime(
//                    DateUtil.DEFAULT_TIME_STAMP_FROMAT, potrans.getStexatime())));
//            li.add(tran);
//        }
//        return li;
//    }
//
//    *//**
//     *
//     * @param ftb
//     * @throws ManagerWithdrawException
//     * @throws NumberFormatException
//     * @throws AbstractBusiAcctException
//     * @throws AccBussinessException
//     *//*
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    public void firstAudit(AuditBean ftb, List<PojoTransferData> pjfd)
//            throws ManagerWithdrawException, AccBussinessException,
//            AbstractBusiAcctException, NumberFormatException {
//
//        if (ftb == null) {
//            throw new ManagerWithdrawException("G100001");
//        }
//        if (pjfd == null || pjfd.isEmpty()) {
//
//            String with = ftb.getOrderNo();
//            if (StringUtil.isEmpty(with)) {
//                throw new ManagerWithdrawException("G100004");
//            }
//            String[] tid = with.split("\\|");
//            if (tid.length <= 0) {
//                throw new ManagerWithdrawException("G100004");
//            }
//
//            for (int i = 0; i < tid.length; i++) {
//
//                String tranid = tid[i];
//                PojoTransferData trans = transferDao.getTransferDataByTranId(
//                        tranid, TransFerDataStatusEnum.FIRSTTRIAL.getCode());
//                if (trans == null) {
//                    throw new ManagerWithdrawException("G100003");
//                }
//                trans.setStexauser(ftb.getStexauser());
//                trans.setStexaopt(ftb.getStexaopt());
//                trans.setStexatime(new Date());
//                // 初审通过
//                if (ftb.getFalg() == true) {
//                    trans.setStatus(TransFerDataStatusEnum.SECONDTRIAL
//                            .getCode());
//                } else {
//                    // 初审拒绝
//                    trans.setStatus(TransFerDataStatusEnum.FIRSTREFUSED
//                            .getCode());
//                    firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
//                }
//
//            }
//        } else {
//            for (PojoTransferData trans : pjfd) {
//                trans.setStexauser(ftb.getStexauser());
//                trans.setStexaopt(ftb.getStexaopt());
//                trans.setStexatime(new Date());
//                // 初审通过
//                if (ftb.getFalg() == true) {
//                    trans.setStatus(TransFerDataStatusEnum.SECONDTRIAL
//                            .getCode());
//                } else {
//                    // 初审拒绝
//                    trans.setStatus(TransFerDataStatusEnum.FIRSTREFUSED
//                            .getCode());
//                    firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
//                }
//            }
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private void senond(AuditBean ftb, List<PojoTransferData> pjfd)
//            throws ManagerWithdrawException, AccBussinessException,
//            AbstractBusiAcctException, NumberFormatException {
//        boolean isok = true;
//        // 民生金额
//        BigDecimal CMBCsumAmount = new BigDecimal(0);
//        // 普通金额
//        BigDecimal sumAmount = new BigDecimal(0);
//        Long CMBCCount = 1L;
//        Long count = 1L;
//        PojoTransferBatch cmbctransferBatch = null;
//        PojoTransferBatch transferBatch = null;
//
//        // 普通批次号
//        String batchno = null;
//        // 民生批次号
//        String CMBCBatchno = null;
//        // 得到所有的明细
//
//        for (int i = 0; i < pjfd.size(); i++) {
//
//            PojoTransferData trans = pjfd.get(i);
//            if (trans == null) {
//                throw new ManagerWithdrawException("G100003");
//            }
//            trans.setCvlexauser(ftb.getStexauser());
//            trans.setCvlexaopt(ftb.getStexaopt());
//            trans.setCvlexatime(new Date());
//
//            // 复审通过
//            if (ftb.getFalg() == true) {
//                trans.setStatus(TransFerDataStatusEnum.INITIAL.getCode());
//                // 民生
//                if ("01".equals(trans.getTransfertype())) {
//                    // 生成批次号
//                    if (StringUtil.isEmpty(CMBCBatchno)) {
//                        CMBCBatchno = getBatchno();
//                    }
//                    // 民生银行的做一个批次
//                    trans.setBatchno(CMBCBatchno);
//
//                    if (cmbctransferBatch == null) {
//                        cmbctransferBatch = new PojoTransferBatch();
//                    }
//                    if (StringUtil.isEmpty(cmbctransferBatch.getBatchno())) {
//                        // 民生批次号
//                        cmbctransferBatch.setBatchno(trans.getBatchno());
//
//                        // 状态
//                        cmbctransferBatch
//                                .setStatus(TransFerDataStatusEnum.INITIAL
//                                        .getCode());
//                        cmbctransferBatch.setTransfertype(CMBC);
//                        cmbctransferBatch.setCreatetime(DateUtil
//                                .getCurrentDate());
//
//                    }
//                    // 总条数
//                    cmbctransferBatch.setSumitem(CMBCCount);
//                    // 金额
//                    CMBCsumAmount = new BigDecimal(trans.getTransamt())
//                            .add(CMBCsumAmount);
//                    cmbctransferBatch.setBusicode(trans.getBusicode());
//                    cmbctransferBatch.setBusitype(trans.getBusitype());
//                    cmbctransferBatch.setSumamount(CMBCsumAmount.longValue());
//                    cmbctransferBatch.setBatchno(CMBCBatchno);
//                    CMBCCount++;
//
//                } else {
//                    if (transferBatch == null) {
//                        transferBatch = new PojoTransferBatch();
//                    }
//                    if (StringUtil.isEmpty(batchno)) {
//                        // 生成批次号
//                        batchno = getBatchno();
//                    }
//                    trans.setBatchno(batchno);
//
//                    if (StringUtil.isEmpty(transferBatch.getBatchno())) {
//                        transferBatch.setBatchno(trans.getBatchno());
//                        transferBatch.setStatus(TransFerDataStatusEnum.INITIAL
//                                .getCode());
//                    }
//                    transferBatch.setCreatetime(DateUtil.getCurrentDate());
//                    transferBatch.setSumitem(Long.valueOf(count));
//                    sumAmount = new BigDecimal(trans.getTransamt())
//                            .add(sumAmount);
//                    transferBatch.setTransfertype(OTHER);
//                    transferBatch.setBusicode(trans.getBusicode());
//                    transferBatch.setBusitype(trans.getBusitype());
//                    transferBatch.setSumamount(sumAmount.longValue());
//                    transferBatch.setBatchno(batchno);
//                    count++;
//
//                }
//
//            } else {
//                // 复审拒绝
//                trans.setStatus(TransFerDataStatusEnum.SECONREFUSED.getCode());
//                firstrefunsed(trans.getTxnseqno(), trans.getBusitype());
//                isok = false;
//            }
//
//        }
//
//        if (isok == true) {
//            if (cmbctransferBatch != null) {
//                cmbctransferBatch.setChnlcode(CMBCCHNL);
//                transbatch.merge(cmbctransferBatch);
//            }
//            if (transferBatch != null) {
//                transferBatch.setChnlcode(CMBCCHNL);
//                transbatch.merge(transferBatch);
//            }
//
//        }
//
//    }
//
//    *//**
//     * 将数据分为普通银行和民生
//     * 
//     * @param transferdate
//     * @return
//     * @throws ManagerWithdrawException
//     *//*
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private Map<String, List<PojoTransferData>> getCmbc(List<PojoTransferData> transferdate)
//            throws ManagerWithdrawException {
//        Map<String, List<PojoTransferData>> fer = new HashMap<String, List<PojoTransferData>>();
//        List<PojoTransferData> cmbc = null;
//        List<PojoTransferData> other = null;
//        for (PojoTransferData trans : transferdate) {
//            if (trans == null) {
//                throw new ManagerWithdrawException("G100003");
//            }
//            // 民生批次
//            if ("01".equals(trans.getTransfertype())) {
//                if (cmbc == null) {
//                    cmbc = new ArrayList<PojoTransferData>();
//                }
//                cmbc.add(trans);
//            }
//            // 外行批次
//            else {
//                if (other == null) {
//                    other = new ArrayList<PojoTransferData>();
//                }
//                other.add(trans);
//            }
//        }
//        if (cmbc != null) {
//            fer.put("cmbc", cmbc);
//        }
//        if (other != null) {
//            fer.put("other", other);
//
//        }
//        return fer;
//
//    }
//
//    *//**
//     * 将划拨数据按不同的code进行分类
//     * 
//     * @param trans
//     * @return
//     *//*
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private Map<String, List<PojoTransferData>> getTransferByCode(List<PojoTransferData> trans) {
//        Map<String, List<PojoTransferData>> rtn = new HashMap<String, List<PojoTransferData>>();
//        Set<String> busiSet = new HashSet<String>();
//        for (PojoTransferData transfer : trans) {
//            if (busiSet.contains(transfer.getBusicode())) {
//                List<PojoTransferData> tmp = rtn.get(transfer.getBusicode());
//                tmp.add(transfer);
//                rtn.put(transfer.getBusicode(), tmp);
//            } else {
//                List<PojoTransferData> tmp = new ArrayList<PojoTransferData>();
//                tmp.add(transfer);
//                rtn.put(transfer.getBusicode(), tmp);
//            }
//            busiSet.add(transfer.getBusicode());
//        }
//
//        return rtn;
//    }
//
//    *//**
//     * 将数据按四千比一个阶梯分类
//     * 
//     * @param rtn
//     * @return
//     *//*
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private Map<Integer, List<PojoTransferData>> getTransferByBank(Map<String, List<PojoTransferData>> rtn) {
//        Map<Integer, List<PojoTransferData>> map = new HashMap<Integer, List<PojoTransferData>>(); // 用map存起来新的分组后数据
//        Iterator<?> iter = rtn.entrySet().iterator();
//        int k = 0;
//        List<PojoTransferData> lis = null;
//        while (iter.hasNext()) {
//            @SuppressWarnings("rawtypes")
//            Map.Entry entry = (Map.Entry) iter.next();
//            String key = (String) entry.getKey();
//            List<PojoTransferData> li = rtn.get(key);
//            if (li.size() > 4000) {
//                for (int i = 0; i < li.size(); i += 4000) {
//                    if (i + 9 > li.size()) {
//                        lis = li.subList(i, li.size());
//                    } else {
//                        lis = li.subList(i, i + 3999);
//                    }
//                    map.put(k, lis);
//                    k++;
//                }
//            } else {
//                map.put(k, li);
//                k++;
//
//            }
//        }
//        return map;
//    }
//    *//**
//     * 根据流水号得到划拨数据
//     * 
//     * @param with
//     * @return
//     * @throws ManagerWithdrawException
//     *//*
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private List<PojoTransferData> gettransByNo(String with)
//            throws ManagerWithdrawException {
//        List<PojoTransferData> li = new ArrayList<PojoTransferData>();
//        if (StringUtil.isEmpty(with)) {
//            throw new ManagerWithdrawException("G100004");
//        }
//        String[] tid = with.split("\\|");
//        if (tid.length <= 0) {
//            throw new ManagerWithdrawException("G100004");
//        }
//        for (int i = 0; i < tid.length; i++) {
//            String tranid = tid[i];
//            PojoTransferData trans = transferDao.getTransferDataByTranId(
//                    tranid, TransFerDataStatusEnum.SECONDTRIAL.getCode());
//            if (trans == null) {
//                throw new ManagerWithdrawException("G100003");
//            }
//            li.add(trans);
//        }
//        return li;
//    }
//
//    *//***
//     * 划拨拒绝
//     * 
//     * @param withdraworderno
//     * @throws NumberFormatException
//     * @throws AbstractBusiAcctException
//     * @throws AccBussinessException
//     * @throws ManagerWithdrawException
//     *//*
//    private void firstrefunsed(String txnseqNo, String businessType)
//            throws AccBussinessException, AbstractBusiAcctException,
//            NumberFormatException, ManagerWithdrawException {
//
//        if (WITH.equals(businessType)) {
//            TxnsWithdrawModel txns = withdao.getTxnsWithdrawByorderNo(txnseqNo,
//                    ReviewEnum.BATCH.getCode(), null);
//            if (txns == null) {
//                throw new ManagerWithdrawException("G100003");
//            }
//            // 提现审核拒绝
//            itws.trialBatch(false, txns);
//        } else if (INSTEAD.equals(businessType)) {
//            PojoInsteadPayDetail pojoinstead = insteadPayDetailDAO
//                    .getDetailByTxnseqno(txnseqNo, InsteadEnum.BATCH.getCode());
//            if (pojoinstead == null) {
//                throw new ManagerWithdrawException("G100003");
//            }
//            instead.veto(pojoinstead, InsteadEnum.BATCHFAILURE.getCode());
//
//        }
//    }
//
//    // 生成批次号
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    private String getBatchno() {
//
//        List<Map<String, Object>> li = primayDao.getSeq(SEQUENCES);
//        Map<String, Object> map = li.get(0);
//        String str = map.get("NEXTVAL") + "";
//        String batchno = MemberUtil.getMemberID("", str);
//
//        return batchno;
//    }
//
//    *//**
//     *
//     * @param ftb
//     * @param pjfd
//     * @throws ManagerWithdrawException
//     * @throws AccBussinessException
//     * @throws AbstractBusiAcctException
//     * @throws NumberFormatException
//     *//*
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    public void secondAudit(AuditBean ftb, List<PojoTransferData> pjfd)
//            throws ManagerWithdrawException, AccBussinessException,
//            AbstractBusiAcctException, NumberFormatException {
//        Map<Integer, List<PojoTransferData>> transin = null;
//        if (pjfd == null) {
//            pjfd = gettransByNo(ftb.getOrderNo());
//
//        }
//        // 按code分类
//        Map<String, List<PojoTransferData>> map = this.getTransferByCode(pjfd);
//        Iterator iter = map.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            String key = (String) entry.getKey();
//            List<PojoTransferData> li = map.get(key);
//            // 按民生分类
//            Map<String, List<PojoTransferData>> transMap = getCmbc(li);
//            // 大于四千的进行分类
//            transin = getTransferByBank(transMap);
//
//            if (transin != null) {
//                Iterator iters = transin.entrySet().iterator();
//                while (iters.hasNext()) {
//                    Map.Entry entrys = (Map.Entry) iters.next();
//                    Object keys = entrys.getKey();
//                    List<PojoTransferData> lis = transin.get(keys);
//                    senond(ftb, lis);
//
//                }
//
//            }
//        };
//    }
//
//    *//**
//     *
//     * @param ftb
//     * @param tbq
//     * @throws ManagerWithdrawException
//     * @throws AccBussinessException
//     * @throws AbstractBusiAcctException
//     * @throws NumberFormatException
//     *//*
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
//    public void secondAuditByConditions(AuditBean ftb,
//            TransferDataQuery tbq,
//            String falg) throws ManagerWithdrawException,
//            AccBussinessException, AbstractBusiAcctException,
//            NumberFormatException {
//
//        // 根据条件查询数据
//        List<PojoTransferData> li = transferDao.getListByQuery(0,
//                Integer.MAX_VALUE, tbq);
//        if (li == null || li.isEmpty()) {
//            throw new ManagerWithdrawException("G100006");
//        }
//        if ("first".equals(falg)) {
//            this.firstAudit(ftb, li);
//        } else {
//            this.secondAudit(ftb, li);
//        }
//    }
//*/
//	
//
//	@Override
//	public PagedResult<TransferData> queryPaged(int page, int pageSize,
//			TransferDataQuery example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	public void firstAudit(AuditBean ftb, List<PojoTranData> pjfd)
//			throws ManagerWithdrawException, AccBussinessException,
//			AbstractBusiAcctException, NumberFormatException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void secondAudit(AuditBean ftb, List<PojoTranData> pjfd)
//			throws ManagerWithdrawException, AccBussinessException,
//			AbstractBusiAcctException, NumberFormatException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	@Override
//	public void secondAuditByConditions(AuditBean ftb, TransferDataQuery tbq,
//			String falg) throws ManagerWithdrawException,
//			AccBussinessException, AbstractBusiAcctException,
//			NumberFormatException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	
//
//	@Override
//	public Map<String, Object> queryBatchTransfer(
//			QueryTransferBean queryTransferBean, int page, int pageSize) {
//		return transferBatchDAO.queryTransferBatchByPage(queryTransferBean, page, pageSize);
//	}
//
//
//	@Override
//	public IBaseDAO<PojoTranData, Long> getDao() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
//	public boolean transferBatchTrial(String batchNo,boolean flag){
//		try {
//			TransferTrialEnum transferTrialEnum = null;
//			if(flag){
//				transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
//			}else {
//				transferTrialEnum = TransferTrialEnum.REFUSED;
//			}
//			List<PojoTranData> transferDataList = transferBatchDAO.queryBatchTransfer(batchNo, transferTrialEnum.getCode());
//	    	//统计审核通过和不同过的数据，笔数和金额
//			long approveCount = 0L;
//			long approveAmount = 0L;
//			long unApproveCount = 0L;
//			long unApproveAmount = 0L;
//			PojoTranBatch transferBatch = transferBatchDAO.getByBatchNo(batchNo);
//	    	if("00".equals(transferTrialEnum.getCode())){
//	    		PojoTranData[] pojoTransferDatas = new PojoTranData[transferDataList.size()];
//	    		transferDataList.toArray(pojoTransferDatas);
//	    		//调用分批算法
//	    		batchSpliter.split(pojoTransferDatas);
//	    		//更划拨新批次信息
//	    		for(PojoTranData transferData : transferDataList){
//	    			if("00".equals(transferData.getStatus())){
//	    				approveCount++;
//	    				approveAmount+=transferData.getTranAmt().longValue();
//	    			}else{
//	    				unApproveCount++;
//	    				unApproveAmount+=transferData.getTranAmt().longValue();
//	    			}
//	    		}
//	    		transferBatch.setApproveAmt(new BigDecimal(transferBatch.getApproveAmt().longValue()+approveAmount));
//	    		transferBatch.setApproveCount(approveCount+transferBatch.getApproveCount());
//	    		transferBatch.setUnapproveAmt(new BigDecimal(transferBatch.getUnapproveAmt().longValue()+unApproveAmount));
//	    		transferBatch.setUnapproveCount(unApproveCount+transferBatch.getUnapproveCount());
//	    		transferBatch.setApproveFinishTime(new Date());
//	    		if(transferBatch.getUnapproveCount()>0){
//	    			transferBatch.setStatus("02");
//	    		}else{
//	    			transferBatch.setStatus("03");
//	    		}
//	    		
//	    		transferDataDAO.updateBatchTransferSingle(transferBatch);
//	    	}else{
//	    		
//	    		for(PojoTranData transferData : transferDataList){
//	    			unApproveCount++;
//					unApproveAmount+=transferData.getTranAmt().longValue();
//					transferData.setStatus(transferTrialEnum.getCode());
//					transferDataDAO.update(transferData);
//					//开始退款
//					BusinessEnum businessEnum = null;
//		    		//00：代付01：提现02：退款
//		    		if("00".equals(transferBatch.getBusitype())){
//		    			businessEnum = BusinessEnum.INSTEADPAY_REFUND;
//		    		}else if("01".equals(transferBatch.getBusitype())){
//		    			businessEnum = BusinessEnum.WITHDRAWALS_REFUND;
//		    		}else if("02".equals(transferBatch.getBusitype())){
//		    			businessEnum = BusinessEnum.REFUND_REFUND;
//		    		}
//		    		businessRefund(transferData,businessEnum);
//	    		}
//	    		transferBatch.setUnapproveAmt(new BigDecimal(transferBatch.getUnapproveAmt().longValue()+unApproveAmount));
//	    		transferBatch.setUnapproveCount(unApproveCount+transferBatch.getUnapproveCount());
//	    		if(transferBatch.getUnapproveCount()>0){
//	    			transferBatch.setStatus("02");
//	    		}else{
//	    			transferBatch.setStatus("03");
//	    		}
//	    		transferDataDAO.updateBatchTransferSingle(transferBatch);
//	    	}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//	
//	
//	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
//    public void businessRefund(PojoTranData transferData,BusinessEnum businessEnum) throws AccBussinessException, AbstractBusiAcctException,NumberFormatException {
//        TradeInfo tradeInfo = new TradeInfo();
//        tradeInfo.setAmount(transferData.getTranAmt());
//        tradeInfo.setBusiCode(businessEnum.getBusiCode());
//        tradeInfo.setPayMemberId(transferData.getMemberId());
//        tradeInfo.setTxnseqno(transferData.getTxnseqno());
//        //tradeInfo.setTxnseqno(pojoinstead.getOrderId());
//        tradeInfo.setCommission(new BigDecimal(0));
//        tradeInfo.setCharge(transferData.getTranFee());
//        accEntryService.accEntryProcess(tradeInfo);
//    }
//	
//	/**
//	 * 单笔审核
//	 *
//	 * @param tranDataSeqNo
//	 * @param flag
//	 * @return
//	 */
//	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
//	public boolean transferDataTrial(String tranDataSeqNo,boolean flag){
//		try {
//			TransferTrialEnum transferTrialEnum = null;
//			if(flag){
//				transferTrialEnum = TransferTrialEnum.SUCCESSFUL;
//			}else {
//				transferTrialEnum = TransferTrialEnum.REFUSED;
//			}
//			PojoTranData transferData = transferDataDAO.queryTransferData(tranDataSeqNo);
//	    	//统计审核通过和不同过的数据，笔数和金额
//			long approveCount = 0L;
//			long approveAmount = 0L;
//			long unApproveCount = 0L;
//			long unApproveAmount = 0L;
//			//判断划拨明细数据状态
//	    	PojoTranBatch transferBatch = transferBatchDAO.getByBatchNo(transferData.getTranBatchId());
//	    	if("00".equals(transferTrialEnum.getCode())){//审核通过的执行分批算法
//	    		PojoTranData[] pojoTransferDatas = new PojoTranData[]{transferData};
//	    		//调用分批算法
//	    		batchSpliter.split(pojoTransferDatas);
//				if("00".equals(transferData.getStatus())){
//					approveCount++;
//					approveAmount+=transferData.getTranAmt().longValue();
//				}else{
//					unApproveCount++;
//					unApproveAmount+=transferData.getTranAmt().longValue();
//				}
//				//更新审核结果笔数和金额
//	    		transferBatch.setApproveAmt(new BigDecimal(transferBatch.getApproveAmt().longValue()+approveAmount));
//	    		transferBatch.setApproveCount(approveCount+transferBatch.getApproveCount());
//	    		transferBatch.setUnapproveAmt(new BigDecimal(transferBatch.getUnapproveAmt().longValue()+unApproveAmount));
//	    		transferBatch.setUnapproveCount(unApproveCount+transferBatch.getUnapproveCount());
//	    		
//	    	}else{
//				unApproveCount++;
//				unApproveAmount+=transferData.getTranAmt().longValue();
//				transferData.setStatus(transferTrialEnum.getCode());
//				this.update(transferData);
//	    		transferBatch.setUnapproveAmt(new BigDecimal(transferBatch.getUnapproveAmt().longValue()+unApproveAmount));
//	    		transferBatch.setUnapproveCount(unApproveCount+transferBatch.getUnapproveCount());
//	    		BusinessEnum businessEnum = null;
//	    		//00：代付01：提现02：退款
//	    		if("00".equals(transferBatch.getBusitype())){
//	    			businessEnum = BusinessEnum.INSTEADPAY_REFUND;
//	    		}else if("01".equals(transferBatch.getBusitype())){
//	    			businessEnum = BusinessEnum.WITHDRAWALS_REFUND;
//	    		}else if("02".equals(transferBatch.getBusitype())){
//	    			businessEnum = BusinessEnum.REFUND_REFUND;
//	    		}
//	    		//业务退汇
//	    		businessRefund(transferData,businessEnum);
//	    	}
//	    	transferDataDAO.updateBatchTransferSingle(transferBatch);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
//	public Map<String, Object> queryDetaTransfer(
//			QueryTransferBean queryTransferBean, int page, int pageSize) {
//		// TODO Auto-generated method stub
//		return transferDataDAO.queryTranfersDetaByPage(queryTransferBean, page, pageSize);
//	}
}
