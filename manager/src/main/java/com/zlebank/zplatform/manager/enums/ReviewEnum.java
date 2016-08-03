
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
public enum ReviewEnum {
    /**待初审**/
    FIRSTTRIAL("01"),
    /**初审未过**/
    FIRSTREFUSED("09"),
    /**待复审**/
    SECONDTRIAL("11"),
    /**复审未过**/
    SECONREFUSED("19"),
    /**待划拨**/
    BATCH("21"),
    /**批处理失败**/
    BATCHFAILURE("29"),
    /**提现成功**/
    SUCCESS("00"),
    /**自行终止**/
    STOP("39"),
    /**未知代码**/
    UNKNOW("99");
    private String code;
    
    private ReviewEnum(String code){
        this.code = code;
    }
    
    
    public static ReviewEnum fromValue(String value) {
        for(ReviewEnum status:values()){
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
