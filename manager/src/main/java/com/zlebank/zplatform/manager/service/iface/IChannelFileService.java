package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.ChannelFileMode;

public interface IChannelFileService {
    public ChannelFileMode getLikeInstiid(String fileName);


    List<ChannelFileMode> getAllStatusChannel();
    public Boolean booChanCodeAndFileName(String uploadFileName,String instiid);
}
