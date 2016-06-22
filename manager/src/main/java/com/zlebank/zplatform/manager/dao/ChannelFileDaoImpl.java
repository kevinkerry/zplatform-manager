package com.zlebank.zplatform.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IChannelFileDao;
import com.zlebank.zplatform.manager.dao.object.scan.ChannelFileMode;
@Repository
public class ChannelFileDaoImpl extends HibernateBaseDAOImpl<ChannelFileMode>
        implements
            IChannelFileDao {
    public List<ChannelFileMode> queryByHQL(String queryString) {
        @SuppressWarnings("unchecked")
        List<ChannelFileMode> list = getSession().createQuery(queryString)
                .list();
        return list;
    }

    public boolean isInsitFileHandlerExist(String uploadFileName, String instiId) {
       /* String queryString = "select count(1) from T_CHANNEL_FILE t where t.file_name = substr('"+uploadFileName+"',1,length(t.file_name)) and t.channel_code like '%"
                + instiId.substring(0, 6) + "%'";*/
        
        
        String queryString = "select count(id) from ChannelFileMode t where t.fileName = SUBSTRING('"+uploadFileName+"',0,length(t.fileName)) and t.chnlCode like '%"
                + instiId.substring(0, 6) + "%'";
        
//        Long result =  (Long)getSession().createQuery(queryString).iterate().next();
//        System.out.println("result1 = " + result1);
//        if (result != null&&result.longValue()>0) {
//                return true;
//        }
        Boolean result = (Boolean)getSession().createQuery(queryString).iterate().hasNext();
        if(result == true){
            return true ;
        }
        return false;
    }
}
