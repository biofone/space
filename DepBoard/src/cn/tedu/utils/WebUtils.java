package cn.tedu.utils;

public class WebUtils {
    private WebUtils(){

    }
    public static boolean isNull(String str){
        return str==null||"".equals(str);
    }
}
