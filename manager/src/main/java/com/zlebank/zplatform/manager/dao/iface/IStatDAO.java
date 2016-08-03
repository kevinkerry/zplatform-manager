package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.TradeStatModel;

public interface IStatDAO {
    
    public List<TradeStatModel> queryTradeStat(String beginTime,String endTime,String payInstCode,String firLevMemId,String secLevMemId);
    
    public Map<String,Object> queryEntryCount(String beginTime,String endTime,String acctCode,int page,int pageSize);
}
