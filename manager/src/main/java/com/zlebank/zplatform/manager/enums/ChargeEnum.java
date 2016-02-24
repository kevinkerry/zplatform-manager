
package com.zlebank.zplatform.manager.enums;
/**
 * 
 *审核code
 *
 * @author yangpeng
 * @version
 * @date 2015年11月30日 下午2:16:58
 * @since
 */
public enum ChargeEnum {
    /**待初审**/
    FIRSTTRIAL("01"),
    /**初审未过**/
    FIRSTREFUSED("09"),
    /**待复审**/
    SECONDTRIAL("11"),
    /**复审未过**/
    SECONREFUSED("19"),
    /**充值成功**/
    SUCCESS("00"),
    /**自行终止**/
    STOP("39"),
    /**未知代码**/
    UNKNOW("99");
    private String code;
    
    private ChargeEnum(String code){
        this.code = code;
    }
    
    
    public static ChargeEnum fromValue(String value) {
        for(ChargeEnum status:values()){
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
