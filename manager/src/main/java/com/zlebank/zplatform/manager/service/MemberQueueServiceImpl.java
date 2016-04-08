package com.zlebank.zplatform.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.iface.IMemberQueueDAO;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;
import com.zlebank.zplatform.manager.service.iface.IMemberQueueService;
@Service
public class MemberQueueServiceImpl implements IMemberQueueService{
    @Autowired
    private IMemberQueueDAO iMemberQueueDao;

    @Override
    public List<?> getAllMemberQueueMode() {
        
        return iMemberQueueDao.getAllMemberQueueMode();
    }

    @Override
    public MemberQueueMode getMemQueuebyMemId(String memId) {
        return iMemberQueueDao.getUniqueByHQL("from MemberQueueMode m where m.memberId=? ", new Object[]{memId});
    }

    @Override
    public void save(MemberQueueMode member) {
        // TODO Auto-generated method stub
        iMemberQueueDao.save(member);
        
    }
    
    @Override
    public void update(MemberQueueMode member) {
        // TODO Auto-generated method stub
        iMemberQueueDao.update(member);
        
    }
}
