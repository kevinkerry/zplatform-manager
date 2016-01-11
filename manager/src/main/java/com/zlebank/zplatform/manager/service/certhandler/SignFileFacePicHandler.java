package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class SignFileFacePicHandler extends CertPicHandler{
    public SignFileFacePicHandler(){
        certType = CertType.SIGNATORYFILE_FACE;
    }
    
    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta,String fileName) {
        merchDeta.setSignfile(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getSignfile();
    }
}
