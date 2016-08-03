package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;

public class OrgCertPicHandler extends CertPicHandler{
    public OrgCertPicHandler(){
        certType = CertType.ORGCERT;
    }

    @Override
    public PojoEnterpriseDetaApply decorate(PojoEnterpriseDetaApply enterpriseDetaApply, String fileName) { 
        enterpriseDetaApply.setOrgCodeFile(fileName);
        return enterpriseDetaApply;
    }
    
    @Override
    public String getFileName(PojoEnterpriseDetaApply enterpriseDetaApply){
        return enterpriseDetaApply.getOrgCodeFile();
    }
}
