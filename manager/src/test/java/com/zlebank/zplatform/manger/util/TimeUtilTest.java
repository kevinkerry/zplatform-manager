package com.zlebank.zplatform.manger.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.zlebank.zplatform.manager.job.TimeUtil;

public class TimeUtilTest {
    
    @Test
    public void test(){
        try {
            long time = TimeUtil.syncCurrentTime();
        
        Date date = new Date(time);
        System.out.println(new SimpleDateFormat("yyyyMMdd HHmm").format(date));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
