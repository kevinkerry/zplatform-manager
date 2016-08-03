package com.zlebank.zplatform.manager.service.iface;

import java.util.List;

import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;


public interface IMemberQueueService {
    public List<?> getAllMemberQueueMode() ;
    public MemberQueueMode getMemQueuebyMemId(String memId);
    public void save(MemberQueueMode member);
    public void update(MemberQueueMode member);
}
