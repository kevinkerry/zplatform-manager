package com.zlebank.zplatform.manger.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.service.iface.IBankTransferService;
import com.zlebank.zplatform.manger.util.RandomArugment;
import com.zlebank.zplatform.trade.bean.enums.SeqNoEnum;
import com.zlebank.zplatform.trade.dao.BankTransferChannelDAO;
import com.zlebank.zplatform.trade.model.PojoBankTransferBatch;
import com.zlebank.zplatform.trade.model.PojoBankTransferChannel;
import com.zlebank.zplatform.trade.model.PojoBankTransferData;
import com.zlebank.zplatform.trade.model.PojoTranBatch;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.service.SeqNoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/*.xml")
public class BankTransferTest {
	@Autowired
	private SeqNoService seqNoServiceImpl;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IBankTransferService bankTransferService;
	private Long bankTranBatchId=52L;
	@Autowired
	private BankTransferChannelDAO bankTransferChannelDAO;
	
	
	public void test(){
		System.out.println(seqNoServiceImpl);
	}
	@Test
	public void testTrail(){
		
		addBankTranBatch();
		
		//auditTranBatch();
	}
	
	public void addBankTranBatch() {
        List<PojoBankTransferData> pojobankTranDatas = new ArrayList<PojoBankTransferData>();
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
            tranData.setAccName("测试账户");
            tranData.setAccNo("6228480018543668976");
            tranData.setAccType(RandomArugment.randomBoolean() ? "00" : "01");
            tranData.setBankNo("103100024015");
            tranData.setBankName("农业银行");
            tranData.setBusiDataId(1L);
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
           
        	
        	
        	
        	PojoBankTransferData banktranData = new PojoBankTransferData();
            String banktranDataseqNo = seqNoServiceImpl
                    .getBatchNo(SeqNoEnum.BANK_TRAN_DATA_NO);
            banktranData.setBankTranDataSeqNo(banktranDataseqNo);
            //tranData.setAccName("accName" + new Random().nextInt(transDatasNum));
            banktranData.setTranData(tranData);
            banktranData.setAccNo("6228480018543668976");
            banktranData.setAccName("测试账户");
            banktranData.setAccType(RandomArugment.randomBoolean() ? "00" : "01");
            banktranData.setAccBankNo("103100024015");
            banktranData.setAccBankName("农业银行");
            //long tranAmt = Long.parseLong(RandomArugment.randomNumber(4));
            //totalAmt += tranAmt;
            banktranData.setTranAmt(tranAmt);
            banktranData.setTransferType("02");
            banktranData.setStatus("01");
            //Date nowDate = new Date();
            banktranData.setApplyTime(nowDate);
            pojobankTranDatas.add(banktranData);
            totalCount++;
        }

        PojoBankTransferBatch banktranBatch = new PojoBankTransferBatch();
        PojoBankTransferChannel channel = bankTransferChannelDAO.getByChannelCode("10000002");
        //channel.setChannelCode("10000001");
        banktranBatch.setChannel(channel);
        banktranBatch.setTotalAmt(totalAmt);
        banktranBatch.setTotalCount(totalCount);
        banktranBatch.setSuccessAmt(0L);
        banktranBatch.setSuccessCount(0L);
        banktranBatch.setFailAmt(0L);
        banktranBatch.setFailCount(0L);
        banktranBatch.setStatus("01");
        banktranBatch.setOpenStatus("1");
        banktranBatch.setApplyTime(new Date());
        banktranBatch.setBankTranBatchNo(seqNoServiceImpl
                .getBatchNo(SeqNoEnum.BANK_TRAN_BATCH_NO));
        banktranBatch.addBankTranDatas(pojobankTranDatas);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //session.persist(banktranBatch);
        //bankTranBatchId = banktranBatch.getTid();
        
        PojoTranBatch tranBatch = new PojoTranBatch();
        tranBatch.setBusiBatchId(1L);
        tranBatch.setBusiType("00");
        tranBatch.setTotalAmt(totalAmt);
        tranBatch.setTotalCount(totalCount);
        tranBatch.setApproveAmt(0L);
        tranBatch.setApproveCount(0L);
        tranBatch.setRefuseAmt(0L);
        tranBatch.setRefuseCount(0L);
        tranBatch.setWaitApproveAmt(totalAmt);
        tranBatch.setWaitApproveCount(totalCount);
        tranBatch.setApplyTime(new Date());
        tranBatch.setStatus("01");
        tranBatch.setTranBatchNo(seqNoServiceImpl
                .getBatchNo(SeqNoEnum.TRAN_BATCH_NO));
        tranBatch.addTranDatas(pojoTranDatas);

        
        
        session.persist(tranBatch);
        session.getTransaction().commit();
        session.clear();
        session.close();
    }
	public void auditTranBatch() {
		boolean flag = bankTransferService.bankTransferBatchTrial(bankTranBatchId+"", true,1L);
		System.out.println(flag);
    }
}
