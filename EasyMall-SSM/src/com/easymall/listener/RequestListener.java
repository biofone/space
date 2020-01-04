package com.easymall.listener;

import com.easymall.domain.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;

@WebListener()
public class RequestListener implements ServletRequestListener {
    public Logger logger = Logger.getLogger(RequestListener.class);
    //监听请求
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //用户访问的url路径
        String url = request.getRequestURL().toString();
        //用户访问客户机的ip地址
        String ip = request.getRemoteAddr();
        //用户名访问时的名称
        String username = "游客";
        if(request.getSession(false)!=null
                &&request.getSession(false).getAttribute("user")!= null){
            User user = (User) request.getSession(false).getAttribute("user");
            username = user.getUsername();
        }
        logger.debug("用户【"+username+"】ip【"+ip+"】+访问url【"+url+"】结束");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //用户访问的url路径
        String url = request.getRequestURL().toString();
        //用户访问客户机的ip地址
        String ip = request.getRemoteAddr();
        //用户名访问时的名称
        String username = "游客";
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!= null){
            User user = (User) session.getAttribute("user");
            username = user.getUsername();
        }
        logger.debug("用户【"+username+"】ip【"+ip+"】+访问url【"+url+"】开始");

    }
}
