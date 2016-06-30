package com.zlebank.zplatform.manager.action.refund;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.trade.bean.UpdateData;
import com.zlebank.zplatform.trade.bean.enums.InsteadPayDetailStatusEnum;
import com.zlebank.zplatform.trade.bean.enums.TransferBusiTypeEnum;
import com.zlebank.zplatform.trade.dao.InsteadPayBatchDAO;
import com.zlebank.zplatform.trade.dao.InsteadPayDetailDAO;
import com.zlebank.zplatform.trade.model.PojoInsteadPayDetail;
import com.zlebank.zplatform.trade.model.TxnsRefundModel;
import com.zlebank.zplatform.trade.service.ITxnsRefundService;
import com.zlebank.zplatform.trade.service.impl.UpdateInsteadServiceImpl;

public class RefundUpdate
        /*implements
            UpdateSubject,
            ApplicationListener<ContextRefreshedEvent>*/ {
    private static final Log log = LogFactory
            .getLog(UpdateInsteadServiceImpl.class);

    @Autowired
    private InsteadPayBatchDAO insteadPayBatchDAO;

    @Autowired
    private InsteadPayDetailDAO insteadPayDetailDAO;

    @Autowired
    private AccEntryService accEntryService;
    @Autowired
    private ITxnsRefundService iTxnsRefundService;

    /*@Override*/
    public void update(UpdateData data) {
        /*List<UpdateSubject> observerList = ObserverListService.getInstance()
                .getObserverList();
         */
        PojoInsteadPayDetail detail = insteadPayDetailDAO
                .getDetailByTxnseqno(data.getTxnSeqNo());
        if (detail == null) {
            log.error("没有找到需要记账的流水");
            return;
        }
        // 更新退款状态29 退款失败 00成功
        TxnsRefundModel txnsRefundModel = iTxnsRefundService
                .getRefundByRefundor(data.getTxnSeqNo());
        if ("00".equals(data.getResultCode())) {
            detail.setStatus(InsteadPayDetailStatusEnum.TRAN_FINISH.getCode());
            txnsRefundModel.setStatus("00");

        } else {
            detail.setStatus(InsteadPayDetailStatusEnum.TRAN_FAILED.getCode());
            txnsRefundModel.setStatus("29");

        }
        iTxnsRefundService.updateRefund(txnsRefundModel);

        detail.setRespCode(data.getResultCode());
        detail.setRespMsg(data.getResultMessage());
        insteadPayDetailDAO.merge(detail);

        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setPayMemberId(detail.getMerId());
        tradeInfo.setAmount(new BigDecimal(detail.getAmt()));
        tradeInfo.setCharge(new BigDecimal(detail.getTxnfee()));
        tradeInfo.setTxnseqno(detail.getTxnseqno());
        tradeInfo.setBusiCode("70000001");
        tradeInfo.setChannelId(data.getChannelCode());
        try {
            if ("00".equals(data.getResultCode())) {
                accEntryService.accEntryProcess(tradeInfo,
                        EntryEvent.AUDIT_PASS);
            } else {
                accEntryService.accEntryProcess(tradeInfo,
                        EntryEvent.AUDIT_REJECT);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 更新退款状态
    }

    /*@Override*/
    public String getBusiCode() {
        return TransferBusiTypeEnum.REFUND.getCode();
    }

    /**
     * 注册观察者
     * 
     * @param event
     */
    /*@Override*/
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /*ObserverListService.getInstance().add(this);*/
    }
}
