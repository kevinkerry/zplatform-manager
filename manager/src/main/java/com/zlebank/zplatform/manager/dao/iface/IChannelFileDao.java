package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.commons.dao.BaseDAO;
import com.zlebank.zplatform.manager.dao.object.ChannelFileMode;

public interface IChannelFileDao  extends BaseDAO<ChannelFileMode>{

    public List<ChannelFileMode> queryBySQL(String queryString) ;
}
