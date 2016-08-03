package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.bean.ChnlDetaBean;

public interface IChannelService {
    /**
     * 得到所有交易渠道以及对应的渠道代码
     * @return
     */
    public List<ChnlDetaBean> getAllChannelCodeList(); 
}
