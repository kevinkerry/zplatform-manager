package com.zlebank.zplatform.manager.service.iface;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;

public interface IFTPEnterpriseService extends IBaseService<PojoEnterpriseDetaApply, Long>{

    String downloadEnterpriseFromFtp(long enterpriseApplyId,
            String realpath,
            CertType format,
            boolean fouce);

}
