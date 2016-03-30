package com.zlebank.zplatform.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.iface.IChannelFileDao;
import com.zlebank.zplatform.manager.service.iface.IChannelFileService;
import com.zlebank.zplatform.trade.model.ChannelFileMode;
@Service
public class ChannelFileService implements IChannelFileService {

    @Autowired
    private IChannelFileDao iChannelFileDao;

    @Override
    public ChannelFileMode getLikeInstiid(String fileName) {
        String queryString = "from ChannelFileMode c where c.fileName like '%"+ fileName+"%' and c.status=01";
        List<ChannelFileMode> list = iChannelFileDao.queryBySQL(queryString);

        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public List<ChannelFileMode> getAllStatusChannel() {
        String queryString = "from ChannelFileMode c where  c.status=00";
        List<ChannelFileMode> list = iChannelFileDao.queryBySQL(queryString);
        return list;
    }

    @Override
    public Boolean booChanCodeAndFileName(String uploadFileName,String instiid) {
        String queryString = "from ChannelFileMode c where c.fileName like '%"+ uploadFileName+"%' and c.chnlCode like '%"+instiid.substring(0,6)+"%'";
        List<ChannelFileMode> list = iChannelFileDao.queryBySQL(queryString);
        if(list.size()>0){
            return true;
        }
        return false;
    }
    

}
