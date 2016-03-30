package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.trade.model.ChannelFileMode;

public interface IChannelFileService {
    public ChannelFileMode getLikeInstiid(String fileName);


    List<ChannelFileMode> getAllStatusChannel();
    public Boolean booChanCodeAndFileName(String uploadFileName,String instiid);
}
