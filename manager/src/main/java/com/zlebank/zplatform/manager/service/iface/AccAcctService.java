package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.bean.FrozenAccBean;
import com.zlebank.zplatform.manager.dao.object.AccAcctModel;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.service.IBaseService;

public interface AccAcctService extends IBaseService<AccAcctModel, String>{

    public List<?> getAccAcctInfo(String acccodeString);

    
    /***
	 * 冻结记录查询
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageVo<FrozenAccBean> queryByPage(FrozenAccBean query,
			int pageNo, int pageSize) ;


    public List<?> getAccBusiacctInfo(String acccodeString);

}
