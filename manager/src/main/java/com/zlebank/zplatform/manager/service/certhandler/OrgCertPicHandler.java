package com.zlebank.zplatform.manager.service.certhandler;

import com.zlebank.zplatform.manager.action.merch.CertType;
import com.zlebank.zplatform.manager.dao.object.MerchDetaModel;

public class OrgCertPicHandler extends CertPicHandler{
    public OrgCertPicHandler(){
        certType = CertType.ORGCERT;
    }

    @Override
    public MerchDetaModel decorate(MerchDetaModel merchDeta, String fileName) { 
        merchDeta.setOrgcodefile(fileName);
        return merchDeta;
    }
    
    @Override
    public String getFileName(MerchDetaModel merch){
        return merch.getOrgcodefile();
    }
}
