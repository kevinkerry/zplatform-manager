package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.manager.bean.ChargeQuery;
import com.zlebank.zplatform.manager.dao.object.ChargeModel;
import com.zlebank.zplatform.trade.common.page.PageVo;
import com.zlebank.zplatform.trade.service.IBaseService;

public interface ChargeService extends IBaseService<ChargeModel, String>{

    public PageVo<ChargeQuery> queryByPage(ChargeQuery chargeQuery,
            int page,
            int rows);
}
