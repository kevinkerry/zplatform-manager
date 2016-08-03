package com.zlebank.zplatform.manager.action.merch;

public enum CertType {
    /**税务登记证*/
    TAXREGCERT("taxRegCert"),
    /**营业执照*/
    BUSILICE("busilice"),
    /**组织机构代码证*/
    ORGCERT("orgCert"),
    /**法人身份证照正面*/
    CORPFILE_FACE ("corpfileFace"),
    /**法人身份证照反面*/
    CORPFILE_OPPOSITE("corpfileOpp"),
    /**委托人身份证照反面*/
    SIGNATORYFILE_FACE("signfileFace"),
    /**委托人身份证照反面*/
    SIGNATORYFILE_OPPOSITE("signfileOpp"),
    /**未知*/
    UNKONW("");
    private String code;
    private CertType(String code){
        this.code = code;
    }
    
    public static CertType format(String code){
        for(CertType certType:CertType.values()){
            if(certType.getCode().equals(code)){
                return certType;
            }
        }
        return UNKONW;
    }
    public String getCode(){
        return code;
    }
}   