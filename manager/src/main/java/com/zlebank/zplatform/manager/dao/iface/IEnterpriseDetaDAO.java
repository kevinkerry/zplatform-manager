package com.zlebank.zplatform.manager.dao.iface;

import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;
import com.zlebank.zplatform.member.pojo.PojoEnterpriseDeta;

public interface IEnterpriseDetaDAO extends IBaseDAO<PojoEnterpriseDetaApply, Long>{
    public  PojoEnterpriseDeta  getEnterpriseByMemberId(String memberId); 

}
