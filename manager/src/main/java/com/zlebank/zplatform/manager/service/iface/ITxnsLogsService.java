package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.trade.model.TxnsLogModel;

public interface ITxnsLogsService extends IBaseService<TxnsLogModel,Long> {

    Map<String, Object> findPersonTxnsLogs(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> findPersonTxnsLogsOfAllPage(Map<String, Object> variables);

    Map<String, Object> queryAllSuccess(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryWithdrawals(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryInsteadPay(Map<String, Object> variables,
            int page,
            int rows);

    Map<String, Object> queryAllWithdrawals(Map<String, Object> variables);

    Map<String, Object> queryAllInsteadPay(Map<String, Object> variables);

    Map<String, Object> queryAllCrr(Map<String, Object> variables);


}
