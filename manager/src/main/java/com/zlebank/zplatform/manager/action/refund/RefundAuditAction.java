package com.zlebank.zplatform.manager.action.refund;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.pojo.AccStatusEnum;
import com.zlebank.zplatform.commons.utils.DateUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.trade.bean.ResultBean;
import com.zlebank.zplatform.trade.bean.wap.WapRefundBean;
import com.zlebank.zplatform.trade.dao.ITxnsOrderinfoDAO;
import com.zlebank.zplatform.trade.exception.TradeException;
import com.zlebank.zplatform.trade.model.TxnsLogModel;
import com.zlebank.zplatform.trade.model.TxnsOrderinfoModel;
import com.zlebank.zplatform.trade.model.TxnsRefundModel;
import com.zlebank.zplatform.trade.service.IGateWayService;
import com.zlebank.zplatform.trade.service.ITxnsLogService;
import com.zlebank.zplatform.trade.service.ITxnsRefundService;
import com.zlebank.zplatform.trade.service.RefundService;
import com.zlebank.zplatform.trade.service.TransferDataService;
import com.zlebank.zplatform.trade.service.impl.UpdateInsteadServiceImpl;
import com.zlebank.zplatform.trade.utils.OrderNumber;
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
        	/*TxnsRefundModel refund = iTxnsRefundService.getRefundByOldTxnSeqno(itxnsCode,null);
            if (refund != null) {
                map.put("messg", "该流水号已经申请退款了");
                json_encode(map);
                return null;
            }*/
            
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("messg", "申请退款退款成功");
			}
            /*List<?> li = txnsService.getTxnsLogById(itxnsCode);
            JSONArray jsonArray = JSONArray.fromObject(li);
            JSONObject job = jsonArray.getJSONObject(0);
            TxnsRefundModel ixn = new TxnsRefundModel();
            ixn.setRefundorderno(OrderNumber.getInstance()
                    .generateRefundOrderNo());
            ixn.setMemberid(job.get("ACCMEMBERID").toString());
            ixn.setOldorderno(job.get("ACCORDNO").toString());
            ixn.setOldtxnseqno(job.get("TXNSEQNO").toString());
            ixn.setMerchno(job.get("ACCFIRMERNO").toString());
            ixn.setSubmerchno(job.get("ACCSECMERNO").toString());
            ixn.setAmount(Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setOldamount(Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setStatus("01");
            ixn.setReltxnseqno(itxnsCode);
            iTxnsRefundService.saveRefundOrder(ixn);*/
        }
        System.out.println(pojoTxnsLog.getTxnseqno());
        map.put("messg", "申请退款退款成功");
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
        // variables.put("feever", PojoTxnsLog.getFeever());
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
        System.out.println(txnxRefund.getFlag());
        if (txnxRefund.getFlag().equals("true")) {
        	
            Long refund_amount = txnsRefundModel.getAmount();
            //部分退款时校验t_txns_refund表中的正在审核或者已经退款的交易的金额之和
            Long sumAmt = iTxnsRefundService.getSumAmtByOldTxnseqno(txnsRefundModel.getOldtxnseqno());
            if((sumAmt)>refund_amount){
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
        	//审核账务处理 
        	refuseRefundAccount(txnsRefundModel.getReltxnseqno());
        	map.put("messg", "初审未过");
            json_encode(map);
        }
        

        // 取到原交易信息
        /*List<?> li = txnsService.getTxnsLogById(txnsRefundModel
                .getOldtxnseqno());
        JSONArray jsonArray = JSONArray.fromObject(li);
        JSONObject job = jsonArray.getJSONObject(0);

        // 保存划拨
        if (txnxRefund.getFlag().equals("true")) {
            PojoTranData pojoTranData = new PojoTranData();
            List<PojoTranData> pojoTranDataList = new ArrayList<PojoTranData>();
            // 划拨批次号
            pojoTranData.setTranDataSeqNo(String.valueOf(System
                    .currentTimeMillis()));
            // pojoTranData.setTranBatch(tranBatch);
            pojoTranData.setAccNo(job.get("ACCORDNO").toString());
            // 划拨金额
            pojoTranData.setTranAmt(txnsRefundModel.getAmount());
            // pojoTranData.setBusiDataId("11111111111111");
            // pojoTranDataList.add(pojoTranData);

            // pojoTranData.setTxnseqno();
            pojoTranData.setStatus(InsteadPayDetailStatusEnum.WAIT_TRAN_APPROVE
                    .getCode());
            // PojoTranData tmp = BeanCopyUtil.copyBean(PojoTranData.class,
            // pojoTranData);
            // pojoTranData.setTranAmt(detail.getAmt());
            // / "业务流水号" /
            pojoTranData.setBusiDataId(Long.parseLong(txnsRefundModel
                    .getRefundorderno()));
            pojoTranData.setMemberId(txnsRefundModel.getMerchno());
            // 交易手续费0
            pojoTranData.setTranFee(0L);
            pojoTranData.setBusiType(TransferBusiTypeEnum.INSTEAD.getCode());
            pojoTranData.setBankNo("00001");
            pojoTranData.setBankName(job.get("ACCORDNO").toString());
            pojoTranDataList.add(pojoTranData);

            try {
                transferDataService.saveTransferData(
                        TransferBusiTypeEnum.REFUND, 1L, pojoTranDataList);
                map.put("messg", "退款审核成功");
                json_encode(map);
            } catch (RecordsAlreadyExistsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {

            map.put("messg", "初审未过");
            json_encode(map);
        }*/

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
    
    
    public void refuseRefundAccount(String txnseqno){
    	log.info("交易:"+txnseqno+"退款账务处理开始");
    	TxnsLogModel txnsLog = txnsLogService.getTxnsLogByTxnseqno(txnseqno);
		TxnsOrderinfoModel order = txnsOrderinfoDAO.getOrderByTxnseqno(txnseqno);
		ResultBean resultBean = null;
        /**交易类型**/
        String busiCode = txnsLog.getBusicode();
        /**付款方会员ID**/
        String payMemberId =  txnsLog.getAccmemberid();
        /**收款方会员ID**/
        String payToMemberId = txnsLog.getAccsecmerno();
        /**收款方父级会员ID**/
        String payToParentMemberId="" ;
        
        /**产品id**/
        String productId = "";
        /**交易金额**/
        BigDecimal amount = new BigDecimal(txnsLog.getAmount());
        /**佣金**/
        BigDecimal commission = new BigDecimal(StringUtil.isNotEmpty(txnsLog.getTradcomm()+"")?txnsLog.getTradcomm():0);
        /**手续费**/
        BigDecimal charge = new BigDecimal(StringUtil.isNotEmpty(txnsLog.getTxnfee()+"")?txnsLog.getTxnfee():0L);
        /**金额D**/
        BigDecimal amountD = new BigDecimal(0);
        /**金额E**/
        BigDecimal amountE = new BigDecimal(0);
        
        TradeInfo tradeInfo = new TradeInfo(txnsLog.getTxnseqno(), "", busiCode, payMemberId, payToMemberId, payToParentMemberId, "", productId, amount, commission, charge, amountD, amountE, false);
        tradeInfo.setCoopInstCode(txnsLog.getAccfirmerno());
        
        log.info(JSON.toJSONString(tradeInfo));
        try {
			accEntryService.accEntryProcess(tradeInfo,EntryEvent.AUDIT_REJECT);
			resultBean = new ResultBean("success");
		}  catch (AccBussinessException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
            e.printStackTrace();
        } catch (AbstractBusiAcctException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            resultBean = new ResultBean("T099", e.getMessage());
            e.printStackTrace();
        }
        
        if(resultBean.isResultBool()){
        	
            txnsLog.setApporderstatus(AccStatusEnum.Finish.getCode());
            txnsLog.setApporderinfo("退款账务成功");
            order.setStatus("00");
        }else{
        	
            txnsLog.setApporderstatus(AccStatusEnum.AccountingFail.getCode());
            txnsLog.setApporderinfo(resultBean.getErrMsg());
        }
        txnsOrderinfoDAO.update(order);
        log.info("交易:"+txnseqno+"退款账务处理成功");
    }

}
