package com.easymall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "EncodingFilter",value="/*")
public class EncodingFilter implements Filter {
    boolean use_encode = false;
    String encode = null;
    public void destroy() {
    }

    //装饰类

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.请求乱码处理--装饰者模式
        //get请求--获取MyHttpServletRequest对象
        HttpServletRequest request = use_encode?
        new MyHttpServletRequest((HttpServletRequest) req,encode): (HttpServletRequest) req;


//        req.setCharacterEncoding("utf-8");// post请求
        //2.响应乱码处理
        resp.setContentType("text/html;charset="+encode);//
        //放行时注意：放行乱码处理后的request对象。
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        use_encode = Boolean.parseBoolean(config.getServletContext().getInitParameter("use_encode"));
        encode = config.getServletContext().getInitParameter("encode");
    }

}
