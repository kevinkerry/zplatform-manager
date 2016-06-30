package com.zlebank.zplatform.manager.dao;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.ITxnsLogsDAO;
import com.zlebank.zplatform.trade.model.TxnsLogModel;

public class TxnsLogsDAOImpl extends HibernateDAOImpl<TxnsLogModel, Long> implements ITxnsLogsDAO{

}
