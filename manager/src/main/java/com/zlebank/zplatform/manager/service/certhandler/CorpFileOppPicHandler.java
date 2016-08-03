package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;

public class CorpFileOppPicHandler extends CertPicHandler{
    public CorpFileOppPicHandler(){
        certType = CertType.CORPFILE_OPPOSITE;
    }
    
    @Override
    public PojoEnterpriseDetaApply decorate(PojoEnterpriseDetaApply enterpriseDetaApply,String fileName) {
        enterpriseDetaApply.setCorpFileOpp(fileName);
        return enterpriseDetaApply;
    }
    
    @Override
    public String getFileName(PojoEnterpriseDetaApply enterpriseDetaApply){
        return enterpriseDetaApply.getCorpFileOpp();
    }
}
