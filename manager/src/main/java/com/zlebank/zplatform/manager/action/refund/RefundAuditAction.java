package com.zlebank.zplatform.manager.action.refund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.trade.bean.enums.InsteadPayDetailStatusEnum;
import com.zlebank.zplatform.trade.bean.enums.TransferBusiTypeEnum;
import com.zlebank.zplatform.trade.exception.RecordsAlreadyExistsException;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.model.TxnsRefundModel;
import com.zlebank.zplatform.trade.service.ITxnsRefundService;
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
            System.out.println(iTxnsRefundService.getRefundByRefundorderNo(
                    itxnsCode, getCurrentUser().getUserId().toString()));
            if (iTxnsRefundService.getRefundByOldTxnSeqno(itxnsCode,
                    getCurrentUser().getUserId().toString()) != null) {
                map.put("messg", "该流水号已经申请退款了");
                json_encode(map);
                return null;
            }
            List<?> li = txnsService.getTxnsLogById(itxnsCode);
            JSONArray jsonArray = JSONArray.fromObject(li);
            JSONObject job = jsonArray.getJSONObject(0);
            TxnsRefundModel ixn = new TxnsRefundModel();
            ixn.setRefundorderno(OrderNumber.getInstance()
                    .generateRefundOrderNo());
            ixn.setMemberid(job.get("ACCMEMBERID").toString());
            ixn.setOldorderno(job.get("ACCORDNO").toString());
            ixn.setOldtxnseqno(job.get("TXNSEQNO").toString());
            ixn.setMerchno(getCurrentUser().getUserId().toString());
            ixn.setAmount(Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setOldamount(Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setStatus("01");
            ixn.setReltxnseqno(itxnsCode);
            iTxnsRefundService.saveRefundOrder(ixn);
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
            status = "21";
        } else if (txnxRefund.getFlag().equals("false")) {
            status = "09";
        }
        txnsRefundModel.setStatus(status);
        txnsRefundModel.setStexaopt(txnxRefund.getStexaopt());
        iTxnsRefundService.updateRefund(txnsRefundModel);

        // 取到原交易信息

        List<?> li = txnsService.getTxnsLogById(txnsRefundModel
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
            // /** "业务流水号" **/
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
