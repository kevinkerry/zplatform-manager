package com.zlebank.zplatform.manager.dao.iface;

import java.util.Map;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.PojoTxnsLog;

public interface ITxnsHibDao  extends BaseDAO<PojoTxnsLog>{
    public Map<String, Object> executePageOracleProcedure(String queryString,String[] columns, Object[] paramaters,String cursorName,String totalName);
}
