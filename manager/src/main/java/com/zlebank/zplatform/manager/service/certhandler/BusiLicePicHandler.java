package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;

public class BusiLicePicHandler extends CertPicHandler{
    
    public BusiLicePicHandler(){
        certType = CertType.BUSILICE;
    }
    
    @Override
    public PojoEnterpriseDetaApply decorate(PojoEnterpriseDetaApply enterpriseDetaApply,String fileName) {
        enterpriseDetaApply.setLicenceFile(fileName);
        return enterpriseDetaApply;
    }
    
    @Override
    public String getFileName(PojoEnterpriseDetaApply enterpriseDetaApply){
        return enterpriseDetaApply.getLicenceFile();
    }
}
