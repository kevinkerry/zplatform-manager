package com.zlebank.zplatform.manager.action.refund;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.trade.bean.enums.TransferBusiTypeEnum;
import com.zlebank.zplatform.trade.exception.RecordsAlreadyExistsException;
import com.zlebank.zplatform.trade.model.PojoTranData;
import com.zlebank.zplatform.trade.model.TxnsRefundModel;
import com.zlebank.zplatform.trade.service.ITxnsRefundService;
import com.zlebank.zplatform.trade.service.TransferDataService;
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
            if (iTxnsRefundService.getRefundByRefundorderNo(itxnsCode,
                    getCurrentUser().getUserId().toString()) != null) {

                return null;
            }
            List<?> li = txnsService.getTxnsLogById(itxnsCode);
            JSONArray jsonArray = JSONArray.fromObject(li);
            JSONObject job = jsonArray.getJSONObject(0);
            System.out.println(job.get("TXNSEQNO"));
            System.out.println(job.get("OLDORDERNO"));
            System.out.println(job.get("OLDTXNSEQNO"));
            System.out.println(job.get("AMOUNT"));
            System.out.println(job.get("OLDAMOUNT"));
            System.out.println(job.get("RELTXNSEQNO"));
            TxnsRefundModel ixn = new TxnsRefundModel();
            ixn.setRefundorderno(OrderNumber.getInstance().generateRefundOrderNo());
            ixn.setOldorderno(job.get("ACCORDNO").toString());
            ixn.setOldtxnseqno(job.get("TXNSEQNO").toString());
            ixn.setMerchno(getCurrentUser().getUserId().toString());
            ixn.setAmount( Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setOldamount(Long.parseLong(job.get("AMOUNT").toString()));
            ixn.setReltxnseqno(itxnsCode);
            iTxnsRefundService.saveRefundOrder(ixn);
        }
        System.out.println(pojoTxnsLog.getTxnseqno());
        return null;
    }

    // 所有申请退款的列表
    public String queryRefund() {
        Map<String, Object> variables = new HashMap<String, Object>();
        
        variables.put("busitype", "1000");
        variables.put("userId", getCurrentUser().getUserId());
        // variables.put("feever", PojoTxnsLog.getFeever());
        Map<String, Object> groupList = txnsService.findQueryRefundByPage(
                variables, getPage(), getRows());
        json_encode(groupList);

        return null;

    }
    
    
    // 审核退款列表 flag=1 true =0 false
    public void examineRefund() {
        TxnsRefundModel txnsRefundModel = iTxnsRefundService
                .getRefundByRefundor(txnxRefund.getRefundorderno());
        String status = "";
        if (txnxRefund.getFlag().equals("true")) {
            status = "21";

        } else if (txnxRefund.getFlag() == "false") {
            status = "09";
        }
        txnsRefundModel.setStatus(status);
        txnsRefundModel.setStexaopt(txnxRefund.getStexaopt());
        iTxnsRefundService.updateRefund(txnsRefundModel);
        //保存划拨
        if(txnxRefund.getFlag().equals("true")){
            PojoTranData pojoTranData =new PojoTranData();
            PojoTranData[] pojoTranDataList =new  PojoTranData[1];
            
            pojoTranData.setTranDataSeqNo("111111");
            //pojoTranData.setTranBatch(tranBatch);
            pojoTranData.setAccNo("11111111111111111");
            pojoTranData.setTranAmt(11L);
            pojoTranData.setBusiDataId("11111111111111");
            //pojoTranData.set
            pojoTranDataList[0]=pojoTranData;
            
            
            try {
                transferDataService.saveTransferData(TransferBusiTypeEnum.REFUND, "", pojoTranDataList);
            } catch (RecordsAlreadyExistsException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
