package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class TaxRegCertPicHandler extends CertPicHandler{
    public TaxRegCertPicHandler(){
        certType = CertType.TAXREGCERT;
    }

    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta, String fileName) { 
        merchDeta.setTaxfile(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getTaxfile();
    }
}
