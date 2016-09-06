package com.zlebank.zplatform.manager.service.iface;

import java.util.Date;
import java.util.List;

import com.zlebank.zplatform.manager.action.fund.PagResultBean;
import com.zlebank.zplatform.manager.bean.FundQueryCondition;
import com.zlebank.zplatform.manager.bean.RaiseTr;

public interface RaiseTrService {
    /**
     * 查询所有的募集款划拨数据
     * @return 
     */
	public PagResultBean selectAllRaise(FundQueryCondition fundBean);
	/**
	 * 审核
	 * @param orderId
	 */
	public void aduitMes(String orderId,Date date);
	
	public List<RaiseTr> selectByOrder(String orderId);
	
} 
