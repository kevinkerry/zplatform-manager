package com.zlebank.zplatform.manager.enums;

public enum TransferTrialEnum {
	/**划拨初始**/
    INITIAL("01"),
    /*审核拒绝*/
    REFUSE("02"),
    /**划拨中**/
    REFUSED("09"),
    /**成功**/ 
    SUCCESSFUL("00"),
    /**未知代码**/
    UNKNOW("99");
    private String code;
    
    private TransferTrialEnum(String code){
        this.code = code;
    }
    
    
    public static TransferTrialEnum fromValue(String value) {
        for(TransferTrialEnum status:values()){
            if(status.code.equals(value)){
                return status;
            }
        }
        return UNKNOW;
    }
    
    public String getCode(){
        return code;
    }
}
