package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.BlackIdnumModel;


public interface ICardHolderBlackService extends IBaseService<BlackIdnumModel,Long>{

    Map<String, Object> queryCardHolderBlackList(Map<String, Object> variables, int i, int j);

    String AddOneBlackCardHolder(BlackIdnumModel blackIdnumModel);

    Map<String, Object> queryOneBlackCardHolder(String tid);

    String delteOneCardHolderBlack(String tid);

    String startCardHolderBlack(String tid);

    String updateBlackCardHolder(BlackIdnumModel blackIdnumModel);


    
}
