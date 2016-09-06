package com.zlebank.zplatform.manager.dao.iface;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.dao.object.RaiseTrModel;

public interface IRaiseTrDao extends BaseDAO<RaiseTrModel>{
    
	public PagResultBean getAllRaise(FundQueryCondition fundBean);

	public void aduitMoney(String orderid,Date date);

	public List<RaiseTrModel> getRaiseByOrder(String orderid);
 
	
	
}
