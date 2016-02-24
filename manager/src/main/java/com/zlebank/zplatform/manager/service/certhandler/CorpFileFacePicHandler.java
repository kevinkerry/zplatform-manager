package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class CorpFileFacePicHandler extends CertPicHandler {
    
    public CorpFileFacePicHandler(){
        certType = CertType.CORPFILE_FACE;
    }
    
    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta, String fileName) {
        merchDeta.setCorpfile(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getCorpfile();
    }
}
