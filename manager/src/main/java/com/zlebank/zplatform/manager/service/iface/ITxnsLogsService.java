package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.trade.model.TxnsLogModel;

public interface ITxnsLogsService extends IBaseService<TxnsLogModel,Long> {

    Map<String, Object> findPersonTxnsLogs(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> findPersonTxnsLogsOfAllPage(Map<String, Object> variables);
            

}
