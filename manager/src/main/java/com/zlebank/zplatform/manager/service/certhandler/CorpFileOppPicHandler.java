package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class CorpFileOppPicHandler extends CertPicHandler{
    public CorpFileOppPicHandler(){
        certType = CertType.CORPFILE_OPPOSITE;
    }
    
    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta,String fileName) {
        merchDeta.setCorpfileOpp(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getCorpfileOpp();
    }
}
