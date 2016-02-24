package com.zlebank.zplatform.manager.action.stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.stat.EntryCountRequest;
import com.zlebank.zplatform.manager.bean.stat.TradeStatRequest;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;
import com.zlebank.zplatform.manager.service.iface.IStatService;

public class StatAction extends BaseAction {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6766439996672692042L;

    private TradeStatRequest tradeStatRequest;
    
    private EntryCountRequest entryCountRequest;
    @Autowired
    private IStatService statService;

    public String showTradeStat() {
        return "tradeStat";
    }
    
    public String showentryCount() {
        return "entryCount";
    }

    public String tradeStat() {
        if (tradeStatRequest == null) {
            tradeStatRequest = new TradeStatRequest();
        }
        List<TradeStatModel> result = statService
                .queryTradeStat(tradeStatRequest);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("total", result.size());
        resultMap.put("rows", result);
        json_encode(resultMap);

        return null;
    }
    
    public String entryCount() {
        if (entryCountRequest == null) {
            entryCountRequest = new EntryCountRequest();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = statService.entryCount(entryCountRequest,getPage(),getRows());
        json_encode(resultMap);

        return null;
    }

    public TradeStatRequest getTradeStatRequest() {
        return tradeStatRequest;
    }
    public void setTradeStatRequest(TradeStatRequest tradeStatRequest) {
        this.tradeStatRequest = tradeStatRequest;
    }

    public EntryCountRequest getEntryCountRequest() {
        return entryCountRequest;
    }

    public void setEntryCountRequest(EntryCountRequest entryCountRequest) {
        this.entryCountRequest = entryCountRequest;
    }
}

