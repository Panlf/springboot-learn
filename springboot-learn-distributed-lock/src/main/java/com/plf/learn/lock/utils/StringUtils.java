package com.plf.learn.lock.utils;

/**
 * @author Panlf
 * @date 2019/5/20
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str
     * @return Null或者空串返回false，否则返回true
     */
    public static boolean isNotEmpty(String str){
        if(str==null || str.trim().length()<=0){
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return Null或者空串返回true，否则返回false
     */
    public static boolean isEmpty(String str){
        return !isNotEmpty(str);
    }
}
