package com.example.utils;

/**
 * @author 蒋蒋
 */
public class StringUtils {

     /**
      * 判断字符串是否为空
      * @param str 字符串
      * @return 返回结果
      * */
     public static boolean isEmpty(String str){
         return str == null || "".equals(str);
    }
}