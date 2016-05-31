/* 
 *
 * 
 * version TODO
 *
 * 2015年11月17日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.manager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.exception.AbstractBusiAcctException;
import com.zlebank.zplatform.acc.exception.AccBussinessException;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.dao.pojo.AccStatusEnum;
import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.dao.iface.ITxnsDAO;
import com.zlebank.zplatform.manager.dao.iface.ITxnsHibDao;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;
import com.zlebank.zplatform.trade.bean.ResultBean;
import com.zlebank.zplatform.trade.dao.ITxnsOrderinfoDAO;
import com.zlebank.zplatform.trade.model.TxnsLogModel;
import com.zlebank.zplatform.trade.model.TxnsOrderinfoModel;
import com.zlebank.zplatform.trade.service.IGateWayService;
import com.zlebank.zplatform.trade.service.ITxnsLogService;

/**
 * 交易流水查询
 *
 * @author yangpeng
 * @version
 * @date 2015年11月17日 上午9:49:58
 * @since
 */
@Service
public class TxnsServiceImpl
        extends
            AbstractBasePageService<TxnsLogBean, TxnsLog>
        implements
            ITxnsLoService {
	private static final Log log = LogFactory.getLog(TxnsServiceImpl.class);
    @Autowired
    private ITxnsDAO itld;

    @Autowired
    private ITxnsHibDao itxnsHibDao;

    @Autowired
    private IRiskService txnskService;
    @Autowired 
    private IGateWayService gateWayService;
    @Autowired
    private ITxnsOrderinfoDAO txnsOrderinfoDAO;
    @Autowired
    private AccEntryService accEntryService;
    @Autowired
    private ITxnsLogService txnsLogService;
    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(TxnsLogBean example) {
        return itld.count(example);

    }

    /**
     *
     * @param offset
     * @param pageSize
     * @param example
     * @return
     */
    @Override
    protected List<TxnsLog> getItem(int offset,
            int pageSize,
            TxnsLogBean example) {
        List<TxnsLog> li = new ArrayList<TxnsLog>();
        List<PojoTxnsLog> txns = itld.getListByQuery(offset, pageSize, example);
        for (PojoTxnsLog txn : txns) {
            TxnsLog txnslogb = BeanCopyUtil.copyBean(TxnsLog.class, txn);
            txnslogb.setAmount(txn.getAmount() == null ? "" : txn.getAmount()
                    .toYuan());
            txnslogb.setTradcomm(txn.getTradcomm() == null ? "" : txn
                    .getTradcomm().toYuan());
            txnslogb.setTxnfee(txn.getTxnfee() == null ? "" : txn.getTxnfee()
                    .toYuan());
            li.add(txnslogb);
        }
        return li;
    }

    /**
     *
     * @param txnseqno
     * @return
     */
    @Override
    public List<?> getTxnsLogById(String txnseqno) {

        return txnskService.getTxnsLogbyId(txnseqno);
    }

    public Map<String, Object> findTxnsLogByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_txnseqno", "v_busicode","v_busitype", "v_pan",
                "v_accordno", "v_accsecmerno", "v_accfirmerno",
                "v_accsettledate", "v_stime", "v_etime", "v_payrettsnseqno",
                "v_retcode", "v_user", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("v_txnseqno") ? variables
                        .get("v_txnseqno") : null,
                variables.containsKey("v_busicode") ? variables
                                .get("v_busicode") : null,
                variables.containsKey("busitype")
                        ? variables.get("busitype")
                        : null,
                variables.containsKey("v_pan") ? variables.get("v_pan") : null,
                variables.containsKey("v_accordno") ? variables
                        .get("v_accordno") : null,
                variables.containsKey("v_accsecmerno") ? variables
                        .get("v_accsecmerno") : null,
                variables.containsKey("v_accfirmerno") ? variables
                        .get("v_accfirmerno") : null,
                variables.containsKey("v_accsettledate") ? variables
                        .get("v_accsettledate") : null,
                variables.containsKey("v_stime")
                        ? variables.get("v_stime")
                        : null,
                variables.containsKey("v_etime")
                        ? variables.get("v_etime")
                        : null,
                variables.containsKey("v_payrettsnseqno") ? variables
                        .get("v_payrettsnseqno") : null,
                variables.containsKey("v_retcode")
                        ? variables.get("v_retcode")
                        : null,
                variables.containsKey("userId")
                        ? variables.get("userId")
                        : null, page, rows};
        return itxnsHibDao.executePageOracleProcedure(
               "{CALL PCK_SEL_T_TXNS_LOG.sel_txns_log(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
               paramaters, "cursor0","v_total");
    }
    
    public Map<String, Object> findQueryRefundByPage(Map<String, Object> variables,
            int page,
            int rows) {

        String[] columns = new String[]{"v_oldtxnseqno", "v_merchno","v_submerchno", "v_memberid",
                "v_refundorderno", "i_no", "i_perno"};

        Object[] paramaters = new Object[]{
                variables.containsKey("v_oldtxnseqno") ? variables
                        .get("v_oldtxnseqno") : null,
                variables.containsKey("v_merchno") ? variables
                                .get("v_merchno") : null,
                variables.containsKey("v_submerchno")
                        ? variables.get("v_submerchno")
                        : null,
                variables.containsKey("v_memberid") ? variables.get("v_memberid") : null,
                variables.containsKey("v_refundorderno") ? variables
                        .get("v_refundorderno") : null,
                page, rows};
       
        return itxnsHibDao.executePageOracleProcedure(
               "{CALL PCK_T_TXNS_REFUND.sel_t_txns_refund(?,?,?,?,?,?,?,?,?)}", columns,
               paramaters, "cursor0","v_total");

    }
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
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
            order.setStatus("03");
        }else{
        	
            txnsLog.setApporderstatus(AccStatusEnum.AccountingFail.getCode());
            txnsLog.setApporderinfo(resultBean.getErrMsg());
            order.setStatus("03");
        }
        gateWayService.update(order);
        txnsOrderinfoDAO.update(order);
        log.info("交易:"+txnseqno+"退款账务处理成功");
    }
    
}
