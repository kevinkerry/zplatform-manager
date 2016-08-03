package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.PojoEnterpriseDetaApply;

public class TaxRegCertPicHandler extends CertPicHandler{
    public TaxRegCertPicHandler(){
        certType = CertType.TAXREGCERT;
    }

    @Override
    public PojoEnterpriseDetaApply decorate(PojoEnterpriseDetaApply enterpriseDetaApply, String fileName) { 
        enterpriseDetaApply.setTaxFile(fileName);
        return enterpriseDetaApply;
    }
    
    @Override
    public String getFileName(PojoEnterpriseDetaApply enterpriseDetaApply){
        return enterpriseDetaApply.getTaxFile();
    }
}
