package com.zlebank.zplatform.manager.bean.enmu;

public enum BankTranBatchOpenStatus {
    
    OPEN("1"),CLOSE("0"),UNKONW("");
    private String code;
    private BankTranBatchOpenStatus(String code){
        this.code = code;
    }
    
    public String getCode(){
        return code;
    }
    
    public static BankTranBatchOpenStatus formValue(String code){
        for(BankTranBatchOpenStatus openStatus:BankTranBatchOpenStatus.values()){
            if(code.equals(openStatus.getCode())){
                return openStatus;
            }
        }
        return UNKONW;
    }
}
