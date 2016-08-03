package com.zlebank.zplatform.manager.action.refund;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.trade.bean.ResultBean;
import com.zlebank.zplatform.trade.bean.wap.WapRefundBean;
import com.zlebank.zplatform.trade.dao.ITxnsOrderinfoDAO;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.TxnsLogModel;
import com.zlebank.zplatform.trade.model.TxnsRefundModel;
import com.zlebank.zplatform.trade.service.IGateWayService;
import com.zlebank.zplatform.trade.service.ITxnsLogService;
import com.zlebank.zplatform.trade.service.ITxnsRefundService;
import com.zlebank.zplatform.trade.service.RefundService;
import com.zlebank.zplatform.trade.service.TransferDataService;
import com.zlebank.zplatform.trade.service.impl.UpdateInsteadServiceImpl;
/**
 * 退款申请
 *
 * @author yinsen.zhou
 * @version
 * @date 2016年3月15日 下午4:53:20
 * @since
 */
public class RefundAuditAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory
            .getLog(UpdateInsteadServiceImpl.class);
    @Autowired
    private ITxnsLoService txnsService;

    private PojoTxnsLog pojoTxnsLog;
    private TxnsRefundModel txnxRefund;

    @Autowired
    private ITxnsRefundService iTxnsRefundService;
    @Autowired
    private TransferDataService transferDataService;
    @Autowired
    private RefundService refundService;
    @Autowired 
    private IGateWayService gateWayService;
    @Autowired
    private ITxnsOrderinfoDAO txnsOrderinfoDAO;
    @Autowired
    private AccEntryService accEntryService;
    @Autowired
    private ITxnsLogService txnsLogService;
    // 退款申请界面
    public String show() {
        return SUCCESS;
    }

    public String showRefund() {

        return "showRefund";

    }
    // 所有交易流水号
    public String queryTxns() {
        Map<String, Object> variables = new HashMap<String, Object>();
        if (pojoTxnsLog != null) {
            variables.put("v_txnseqno", pojoTxnsLog.getTxnseqno());
        }

        variables.put("busitype", "1000");
        variables.put("userId", getCurrentUser().getUserId());

        // variables.put("feever", PojoTxnsLog.getFeever());
        Map<String, Object> groupList = txnsService.findTxnsLogByPage(
                variables, getPage(), getRows());
        json_encode(groupList);
        return null;
    }
    // 申请退款处理
    public String batchAudit() {
        String[] array = pojoTxnsLog.getTxnseqno().split("\\|");
        Map<String, Object> map = new HashMap<String, Object>();
        for (String itxnsCode : array) {
        	TxnsLogModel txnsLog = txnsLogService.getTxnsLogByTxnseqno(itxnsCode);
        	TxnsRefundModel refund = iTxnsRefundService.getRefundByOldTxnSeqno(itxnsCode,null);
            if (refund != null) {
                map.put("messg", "该流水号已经申请退款了");
                json_encode(map);
                return null;
            }
            
            WapRefundBean refundBean = new WapRefundBean();
    		refundBean.setOrderId(DateUtil.getCurrentDateTime());
    		refundBean.setOrigOrderId(txnsLog.getAccordno());
    		refundBean.setTxnAmt(txnsLog.getAmount()+"");
    		refundBean.setTxnType("14");
    		refundBean.setTxnSubType("00");
    		refundBean.setBizType("000202");
    		refundBean.setCoopInstiId(txnsLog.getAccfirmerno());
    		refundBean.setMerId(txnsLog.getAccsecmerno());
    		refundBean.setMemberId(txnsLog.getAccmemberid());
            try {
            	log.info("refund json:"+JSON.toJSONString(refundBean));
				gateWayService.refund(JSON.toJSONString(refundBean));
			} catch (TradeException e) {
				e.printStackTrace();
				map.put("messg", "申请退款失败:"+e.getMessage());
				json_encode(map);
				return null;
			}
        }
        map.put("messg", "申请退款成功");
        json_encode(map);
        return null;
    }

    // 所有申请退款的列表
    public String queryRefund() {
        Map<String, Object> variables = new HashMap<String, Object>();

        if (txnxRefund != null) {
            variables.put("v_refundorderno", txnxRefund.getRefundorderno());
            variables.put("v_memberid", txnxRefund.getMemberid());
        }
        variables.put("userId", getCurrentUser().getUserId());
        Map<String, Object> groupList = txnsService.findQueryRefundByPage(
                variables, getPage(), getRows());
        json_encode(groupList);

        return null;

    }

    // 审核退款列表 flag=1 true =0 false
    public void examineRefund() {
        Map<String, Object> map = new HashMap<String, Object>();
        TxnsRefundModel txnsRefundModel = iTxnsRefundService
                .getRefundByRefundor(txnxRefund.getRefundorderno());
        String status = "";
        if (txnxRefund.getFlag().equals("true")) {
        	
            //Long refund_amount = txnsRefundModel.getAmount();
            //部分退款时校验t_txns_refund表中的正在审核或者已经退款的交易的金额之和
            Long sumAmt = iTxnsRefundService.getSumAmtByOldTxnseqno(txnsRefundModel.getOldtxnseqno());
            if(sumAmt>txnsRefundModel.getOldamount()){
            	map.put("messg", "退款金额之和大于原始交易金额");
                json_encode(map);
                return;
            }
            status = "21";
        } else if (txnxRefund.getFlag().equals("false")) {
            status = "09";
        }
        txnsRefundModel.setStatus(status);
        txnsRefundModel.setStexaopt(txnxRefund.getStexaopt());
        iTxnsRefundService.updateRefund(txnsRefundModel);
        
        if(txnxRefund.getFlag().equals("true")){
        	//退款路由逻辑开始
            ResultBean resultBean = refundService.execute(txnxRefund.getRefundorderno(), txnsRefundModel.getSubmerchno());
            log.info(JSON.toJSONString(resultBean));
            if(resultBean.isResultBool()){
            	map.put("messg", "退款审核成功");
                json_encode(map);
            }else{
            	txnsRefundModel.setStatus("01");
            	iTxnsRefundService.updateRefund(txnsRefundModel);
            	map.put("messg", "退款失败");
                json_encode(map);
            }
        }else{
        	String txnseqno = txnsRefundModel.getReltxnseqno();
        	//审核账务处理 
        	txnsService.refuseRefundAccount(txnseqno);
        	map.put("messg", "初审拒绝");
            json_encode(map);
        }
    }

    public PojoTxnsLog getPojoTxnsLog() {
        return pojoTxnsLog;
    }
    public void setPojoTxnsLog(PojoTxnsLog pojoTxnsLog) {
        this.pojoTxnsLog = pojoTxnsLog;
    }

    public TxnsRefundModel getTxnxRefund() {
        return txnxRefund;
    }

    public void setTxnxRefund(TxnsRefundModel txnxRefund) {
        this.txnxRefund = txnxRefund;
    }
    
    
    

}
