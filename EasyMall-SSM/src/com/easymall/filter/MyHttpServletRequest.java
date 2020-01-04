package com.easymall.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class MyHttpServletRequest extends HttpServletRequestWrapper {
    public String encode = null;
    public MyHttpServletRequest(HttpServletRequest request,String encode) {
        super(request);
        this.encode = encode;
    }
    //重写的方法
    @Override
    public Map<String, String[]> getParameterMap() {
        //返回一个经过乱码处理后的map对象
        //获取原有的map的值，将值依次作出乱码处理，处理后将值放入新map，并返回。
        Map<String, String[]> map = super.getParameterMap();
        Map<String,String[]> rmap = new HashMap<String,String[]>();
        //将值依次作出乱码处理--遍历map
        try {
            for(Map.Entry<String,String[]> entry:map.entrySet()){
                String key = entry.getKey();
                String[] values = entry.getValue();
                //遍历数组，将值依次取出作出乱码处理，处理后的结构放入一个新数组。
                String[] rvalues = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    rvalues[i] = new String(
                            values[i].getBytes("iso8859-1"),
                            encode) ;
                }
                //将乱码处理后的数组放入新map中
                rmap.put(key,rvalues);
            }
            //map的for循环执行完成，map中全部乱码处理完成。
            return rmap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//            return super.getParameterMap();
    }
    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> map = getParameterMap();
        return map.get(name);
//            return super.getParameterValues(name);
    }
    @Override
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        //将数组中的第一元素作为方法返回值使用。
        return values==null?null:values[0];
//            return super.getParameter(name);
    }
}
