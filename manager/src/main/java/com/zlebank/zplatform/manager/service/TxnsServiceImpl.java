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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.commons.service.impl.AbstractBasePageService;
import com.zlebank.zplatform.commons.utils.BeanCopyUtil;
import com.zlebank.zplatform.manager.bean.TxnsLog;
import com.zlebank.zplatform.manager.bean.TxnsLogBean;
import com.zlebank.zplatform.manager.dao.iface.ITxnsDAO;
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
public class TxnsServiceImpl extends AbstractBasePageService<TxnsLogBean,TxnsLog> implements ITxnsLoService {
    
    @Autowired
    private ITxnsDAO  itld;
 
    @Autowired
    private IRiskService txnskService;

    /**
     *
     * @param example
     * @return
     */
    @Override
    protected long getTotal(TxnsLogBean example) {
      return   itld.count(example);
      
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
        List <TxnsLog> li=new ArrayList<TxnsLog>();
        List<PojoTxnsLog> txns=   itld.getListByQuery(offset, pageSize, example);
    for(PojoTxnsLog txn: txns){
        TxnsLog txnslogb=   BeanCopyUtil.copyBean(TxnsLog.class, txn);
    txnslogb.setAmount(txn.getAmount()==null?"":txn.getAmount().toYuan());
    txnslogb.setTradcomm(txn.getTradcomm()==null?"":txn.getTradcomm().toYuan());
         txnslogb.setTxnfee(txn.getTxnfee()==null?"":txn.getTxnfee().toYuan());
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
       
       return  txnskService.getTxnsLogbyId(txnseqno);
    }

}
