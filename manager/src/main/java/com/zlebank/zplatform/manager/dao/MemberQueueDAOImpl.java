package com.zlebank.zplatform.manager.dao;

import java.util.List;

import com.zlebank.zplatform.manager.dao.base.HibernateDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IMemberQueueDAO;
import com.zlebank.zplatform.manager.dao.object.scan.MemberQueueMode;
public class MemberQueueDAOImpl extends HibernateDAOImpl<MemberQueueMode,Long> implements IMemberQueueDAO{

    @Override
    public List<?> getAllMemberQueueMode() {
        String queryString="select * from t_member_queue where SEND_TIMES<3  and FLAG=01  and (STATUS <>00 or STATUS is null)";
        Object[] paramaters=null;
            List<?> list = this.executeBySQL(queryString, paramaters);
        return list;
    }

}
