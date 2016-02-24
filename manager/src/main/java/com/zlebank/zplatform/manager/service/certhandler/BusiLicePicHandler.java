package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class BusiLicePicHandler extends CertPicHandler{
    
    public BusiLicePicHandler(){
        certType = CertType.BUSILICE;
    }
    
    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta,String fileName) {
        merchDeta.setLicencefile(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getLicencefile();
    }
}
