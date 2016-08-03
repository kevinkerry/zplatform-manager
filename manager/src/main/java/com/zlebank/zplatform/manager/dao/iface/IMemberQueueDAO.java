package com.zlebank.zplatform.manager.dao.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;


public interface IMemberQueueDAO extends IBaseDAO<MemberQueueMode,Long> {
    public List<?> getAllMemberQueueMode();

}
