package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.bean.stat.EntryCountRequest;
import com.zlebank.zplatform.manager.bean.stat.TradeStatRequest;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;

public interface IStatService {
    public List<TradeStatModel> queryTradeStat(TradeStatRequest tradeStatRequest);
    public Map<String,Object> entryCount(EntryCountRequest entryCountRequest,int page,int pageSize);
}
