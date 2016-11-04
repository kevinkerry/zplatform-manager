package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.BusiacctModel;
import com.zlebank.zplatform.trade.service.IBaseService;


public interface IBusiacctService extends IBaseService<BusiacctModel, String>{

    public List<?> queryAllUsage();

    public Map<String, Object> queryAccAccountDetail(String id);

}
