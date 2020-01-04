package com.easymall.utils;
//web工具类
public class WebUtils {
    //1.私有化构造方法
    private WebUtils(){

    }
    //2.创建静态方法
    //非空判断
    public static boolean isNull(String name){
        return name == null || "".equals(name);
    }
}
