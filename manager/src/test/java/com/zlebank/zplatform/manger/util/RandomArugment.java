package com.zlebank.zplatform.manger.util;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class RandomArugment {
    /**
     * Generate a random number.It length is specific by parameter length
     * 
     * @param length
     * @return
     */
    public static String randomNumber(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    /**
     * Generate a random acc name which start by "accName"
     * 
     * @return
     */
    public static String randomAccName() {
        Random random = new Random();
        int length = random.nextInt(10);
        String name = RandomStringUtils.random(length);

        return "accName" + name;
    }
   /**
    * Generate a random name which start by prefix
    * @param prefix
    * @param length
    * @return
    */
    public static String randomName(String prefix,int length) {
        Random random = new Random();
        length = random.nextInt(length);
        String name = RandomStringUtils.random(length);

        return prefix + name;
    }
    /**
     * Generate a random boolean value
     * @return
     */
    public static boolean randomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
    /**
     * 
     * @return
     */
    public static String randomEmail(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int preLength = random.nextInt(10);
        String pre = RandomStringUtils.randomAlphabetic(preLength);
        int suffixOperateLength = random.nextInt(5);
        String suffixOperate = RandomStringUtils.randomAlphabetic(suffixOperateLength);
        
        sb.append(pre);
        sb.append("@");
        sb.append(suffixOperate);
        sb.append(".");
        sb.append("com");
        return sb.toString();
    }
}
