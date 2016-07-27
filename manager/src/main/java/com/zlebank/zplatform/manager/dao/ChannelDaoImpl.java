package com.zlebank.zplatform.manager.dao;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.zlebank.zplatform.acc.bean.enums.BusinessEntryStatus;
import com.zlebank.zplatform.commons.dao.impl.HibernateBaseDAOImpl;
import com.zlebank.zplatform.manager.dao.iface.IChannelDAO;
import com.zlebank.zplatform.trade.model.ChnlDetaModel;

/**
 * 
 * Class Description
 *
 * @author jingxr
 * @version
 * @date 2016年7月27日 上午10:18:19
 * @since
 */

public class ChannelDaoImpl extends HibernateBaseDAOImpl<ChnlDetaModel> implements IChannelDAO {

    public final static String status ="00";
    
    @SuppressWarnings("unchecked")
    public List<ChnlDetaModel> getAllChannelCodeList() {
        Criteria crite=  this.getSession().createCriteria(ChnlDetaModel.class);
        crite.add(Restrictions.eq("status", status));
        return crite.list();
    }



}
