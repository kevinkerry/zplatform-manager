package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.acc.pojo.PojoBusiness;
import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.trade.model.ChnlDetaModel;
/**
 * 
 * Class Description
 *
 * @author jingxr
 * @version
 * @date 2016年7月27日 上午10:18:41
 * @since
 */
public interface IChannelDAO extends BaseDAO<ChnlDetaModel>{
    public List<ChnlDetaModel> getAllChannelCodeList();
}
