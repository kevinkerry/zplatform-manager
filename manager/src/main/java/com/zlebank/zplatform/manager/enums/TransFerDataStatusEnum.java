
package com.zlebank.zplatform.manager.enums;
/**
 * 
 *划拨状态
 *
 * @author yangpeng
 * @version
 * @date 2015年11月30日 下午2:16:58
 * @since
 */
public enum TransFerDataStatusEnum {
    /**划拨初始**/
    INITIAL("01"),
    /**划拨中**/
    FORTRANSFER("02"),
    /**成功**/ 
    SUCCESSFUL("00"),
    /**划拨失败**/ 
    FAILURE("03"),
    /**待初审**/
    FIRSTTRIAL("11"),
    /**初审未过**/
    FIRSTREFUSED("19"),
    /**待复审**/
    SECONDTRIAL("21"),
    /**复审未过**/
    SECONREFUSED("29"),
    /**未知代码**/
    UNKNOW("99");
    private String code;
    
    private TransFerDataStatusEnum(String code){
        this.code = code;
    }
    
    
    public static TransFerDataStatusEnum fromValue(String value) {
        for(TransFerDataStatusEnum status:values()){
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
