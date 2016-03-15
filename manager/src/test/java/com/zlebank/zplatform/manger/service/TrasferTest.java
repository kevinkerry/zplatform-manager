package com.zlebank.zplatform.manger.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zlebank.zplatform.manager.service.iface.ITransferService;
import com.zlebank.zplatform.manger.util.RandomArugment;
import com.zlebank.zplatform.trade.bean.enums.SeqNoEnum;
import com.zlebank.zplatform.trade.model.PojoTranBatch;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.service.SeqNoService;
/**
 * 
 * Transfer test
 *
 * @author yangying
 * @version
 * @date 2016年3月13日 上午11:24:33
 * @since
 */
public class TrasferTest {
    private SeqNoService seqNoServiceImpl;
    private SessionFactory sessionFactory;
    private long tranBatchId;
    private ApplicationContext context;
    private ITransferService transferServiceImpl;
    
    private static final Log log = LogFactory.getLog(TrasferTest.class);
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("/spring/*");
        seqNoServiceImpl = (SeqNoService) context.getBean("seqNoServiceImpl");
        sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        transferServiceImpl = (ITransferService) context
                .getBean("transferServiceImpl");
    }
    @Test
    public void test() {
        addTranBatch();
        auditTranBatch();
    }

    public void testQueryTranBatch() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(PojoTranBatch.class);

            @SuppressWarnings("unchecked")
            List<PojoTranBatch> transBatchs = (List<PojoTranBatch>) criteria
                    .list();
            Assert.assertEquals(9, transBatchs.size());
            for (PojoTranBatch transBatch : transBatchs) {
                if (transBatch.getTid() == 258) {
                    List<PojoTranData> tranDatas = transBatch.getTranDatas();
                    Assert.assertEquals(10, tranDatas.size());
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            Assert.fail();
        } finally {

            session.close();
        }

    }

    public void addTranBatch() {
        List<PojoTranData> pojoTranDatas = new ArrayList<PojoTranData>();
        int transDatasNum = 10;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "YYYYMMDDHHmmss");
        long totalAmt = 0L;
        long totalCount = 0L;
        for (int i = transDatasNum; i > 0; i--) {
            PojoTranData tranData = new PojoTranData();
            String tranDataseqNo = seqNoServiceImpl
                    .getBatchNo(SeqNoEnum.TRAN_DATA_NO);
            tranData.setTranDataSeqNo(tranDataseqNo);
            tranData.setAccName("accName" + new Random().nextInt(transDatasNum));
            tranData.setAccNo(RandomArugment.randomNumber(22));
            tranData.setAccName(RandomArugment.randomAccName());
            tranData.setAccType(RandomArugment.randomBoolean() ? "0" : "1");
            tranData.setBankNo("313653020010");
            tranData.setBusiDataId(11111111L);
            tranData.setBusiType("00");
            tranData.setMemberId("200000000000593");
            long tranAmt = Long.parseLong(RandomArugment.randomNumber(4));
            totalAmt += tranAmt;
            tranData.setTranAmt(tranAmt);
            tranData.setStatus("01");
            Date nowDate = new Date();
            tranData.setTranFee(new BigDecimal(tranAmt * 0.003).longValue());
            String txnSeqNo = simpleDateFormat.format(nowDate);
            txnSeqNo = txnSeqNo
                    + String.format("%1$02d",
                            Long.parseLong(RandomArugment.randomNumber(2)));
            tranData.setTxnseqno(txnSeqNo);
            tranData.setApplyTime(nowDate);
            pojoTranDatas.add(tranData);
            totalCount++;
        }

        PojoTranBatch tranBatch = new PojoTranBatch();
        tranBatch.setBusiBatchId(222L);
        tranBatch.setBusiType("00");
        tranBatch.setTotalAmt(totalAmt);
        tranBatch.setTotalCount(totalCount);
        tranBatch.setStatus("01");
        tranBatch.setTranBatchNo(seqNoServiceImpl
                .getBatchNo(SeqNoEnum.TRAN_BATCH_NO));
        tranBatch.setApproveCount(0L);
        tranBatch.setApproveAmt(0L);
        tranBatch.setRefuseCount(0L);
        tranBatch.setRefuseAmt(0L);
        tranBatch.setWaitApproveAmt(totalAmt);
        tranBatch.setWaitApproveCount(totalCount);
        tranBatch.addTranDatas(pojoTranDatas);
        tranBatch.setApplyTime(new Date());
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(tranBatch);
        tranBatchId = tranBatch.getTid();
        session.getTransaction().commit();
        session.close();
    }

    public void auditTranBatch() {
        if (tranBatchId == 0) {
            Assert.fail("transfer batch not exist fail");
        }

        Session session = sessionFactory.openSession();
        List<PojoTranData> tranDatas = null;
        try {
            session.beginTransaction();
            PojoTranBatch pojoTranBatch = (PojoTranBatch) session.get(
                    PojoTranBatch.class, tranBatchId);
            tranDatas = pojoTranBatch.getTranDatas();
            session.getTransaction().commit();
          

            if (tranDatas == null || tranDatas.isEmpty()) {
                Assert.fail("transfer data not exist fail");
            }
            for (PojoTranData tranData : tranDatas) {
                boolean isApproved = RandomArugment.randomBoolean();
                boolean isSuccess = transferServiceImpl.transferDataTrial(tranData.getTid(),
                        isApproved);
                if(!isSuccess){
                    log.info("fail tranData"+tranData.getTid());
                }else{
                    log.info("success tranData"+tranData.getTid());
                }
            }
            if(session.getTransaction().isActive()){
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }finally{
            session.close();
        }
    }
}
