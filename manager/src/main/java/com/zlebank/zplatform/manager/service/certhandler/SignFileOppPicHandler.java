package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class SignFileOppPicHandler extends CertPicHandler{
    public SignFileOppPicHandler(){
        certType = CertType.SIGNATORYFILE_OPPOSITE;
    }
    
    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta,String fileName) {
        merchDeta.setSignfileOpp(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getSignfileOpp();
    }
}
