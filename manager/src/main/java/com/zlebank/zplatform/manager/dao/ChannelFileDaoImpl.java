package com.zlebank.zplatform.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IChannelFileDao;
import com.zlebank.zplatform.trade.model.ChannelFileMode;
@Repository
public class ChannelFileDaoImpl extends HibernateBaseDAOImpl<ChannelFileMode> implements IChannelFileDao{
    public List<ChannelFileMode> queryBySQL(String queryString) {
        List<ChannelFileMode> list = getSession().createQuery(queryString).list();
        return list;
    }

    
  
}
