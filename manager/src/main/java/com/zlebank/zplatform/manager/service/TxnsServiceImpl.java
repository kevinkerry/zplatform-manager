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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.dao.iface.ITxnsDAO;
import com.zlebank.zplatform.manager.dao.iface.ITxnsHibDao;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;
import com.zlebank.zplatform.manager.service.iface.IRiskService;
import com.zlebank.zplatform.manager.service.iface.ITxnsLoService;

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

    @Autowired
    private ITxnsDAO itld;

    @Autowired
    private ITxnsHibDao itxnsHibDao;

    @Autowired
    private IRiskService txnskService;

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
        //return null;
       
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
    
    
}
