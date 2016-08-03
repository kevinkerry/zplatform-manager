package com.zlebank.zplatform.manger.util;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.zlebank.zplatform.commons.utils.DateUtil;

public class DateUtilTest {
    
    @Test
    public void test(){
        String srcTime = "2016-06-15 12:37";
        try {
            Date date = DateUtil.convertToDate(srcTime, "yyyy-MM-dd HH:mm");
            System.out.println(date.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
