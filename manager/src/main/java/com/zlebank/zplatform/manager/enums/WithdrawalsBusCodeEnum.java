
package com.zlebank.zplatform.manager.enums;
/**
 * 
 *提现code
 *
 * @author yangpeng
 * @version
 * @date 2015年11月30日 下午2:16:58
 * @since
 */
public enum WithdrawalsBusCodeEnum {
    /**提现申请**/
    APPLY("30000001"),
    /**提现通过**/
    THROUGH("30000002"),
    /**提现拒绝**/
    REFUSE("30000003"),
    UNKNOW("99");
    private String code;
    
    private WithdrawalsBusCodeEnum(String code){
        this.code = code;
    }
    
    
    public static WithdrawalsBusCodeEnum fromValue(String value) {
        for(WithdrawalsBusCodeEnum status:values()){
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
