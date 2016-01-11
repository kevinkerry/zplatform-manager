package com.zlebank.zplatform.manager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zlebank.zplatform.manager.bean.stat.EntryCountRequest;
import com.zlebank.zplatform.manager.bean.stat.TradeStatRequest;
import com.zlebank.zplatform.manager.dao.iface.IStatDAO;
import com.zlebank.zplatform.manager.dao.object.TradeStatModel;
import com.zlebank.zplatform.manager.service.iface.IStatService;

@Service
public class StatServiceImpl implements IStatService {

    @Autowired
    private IStatDAO statDAO;

    private final SimpleDateFormat requestDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @Transactional(propagation=Propagation.REQUIRED)
    public List<TradeStatModel> queryTradeStat(TradeStatRequest tradeStatRequest) {
        
        String beginDate = tradeStatRequest.getBeginDate();
        if(!StringUtils.isEmpty(beginDate)){
            beginDate = validAndParseDate(beginDate,true);
            tradeStatRequest.setBeginDate(beginDate);
        }
        
        String endDate = tradeStatRequest.getEndDate();
        if(!StringUtils.isEmpty(endDate)){
            endDate = validAndParseDate(endDate,false);
            tradeStatRequest.setEndDate(endDate);
        }
        
        return statDAO.queryTradeStat(tradeStatRequest.getBeginDate(),
                tradeStatRequest.getEndDate(), null,
                tradeStatRequest.getFirLevMemId(),
                tradeStatRequest.getSecLevMemId());
    }
    public Map<String,Object> entryCount(EntryCountRequest entryCountRequest,int page,int pageSize){
        String beginDate = entryCountRequest.getBeginDate();
        if(!StringUtils.isEmpty(beginDate)){
            beginDate = validAndParseDate(beginDate,true);
            entryCountRequest.setBeginDate(beginDate);
        }
        
        String endDate = entryCountRequest.getEndDate();
        if(!StringUtils.isEmpty(endDate)){
            endDate = validAndParseDate(endDate,false);
            entryCountRequest.setEndDate(endDate);
        }
        
        return statDAO.queryEntryCount(entryCountRequest.getBeginDate(),
                entryCountRequest.getEndDate(), 
                entryCountRequest.getAcctCode(),page,pageSize);
    }
    
    
    private String validAndParseDate(String str,boolean isBeginDate) {
        String parseStr = null;
        try {
            requestDateFormat.setLenient(false);
            Date date = requestDateFormat.parse(str);
            if(isBeginDate){
                parseStr =  String.format("%1$tY%1$tm%1$td000000", date);
            }else{
                parseStr =  String.format("%1$tY%1$tm%1$td235959", date);
            }
           
        } catch (ParseException e) { 
            return null;
        }
        return parseStr;
    }
}
