package com.zlebank.zplatform.manager.dao;


import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ITxnsLogsDAO;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

@SuppressWarnings("unchecked")
public class TxnsLogsDAOImpl extends HibernateDAOImpl<TxnsLogModel, Long> implements ITxnsLogsDAO{

}
