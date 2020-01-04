package com.easymall.filter;

import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

//30天自动登录的过滤器
@WebFilter(filterName = "LoginFilter",value = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //0.判断是否存在登录状态，未登录状态再完成登录。
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        //从session中读取user域属性，如果读取到
        //则表示有登录状态,反之没有登录状态
        if(session.getAttribute("user") == null){
            //1.拦截请求，从请求中取出cookie信息，完成登录
            Cookie[] cookies = request.getCookies();
            Cookie autologinC = null;//扩大cookie的范围
            if(cookies != null){//初次访问页面cookie可能为null
                for(Cookie c:cookies){//cookie为null不可以增强for循环，会空指针异常
                    if("autologin".equals(c.getName())){
                        autologinC = c;
                    }
                }
            }
            if(autologinC != null){//在自动登录cookie存在清空下，再完成登录功能
                String value = autologinC.getValue();//获取cookie中的值
                String[] arr = value.split("#");//切割值
                String username = URLDecoder.decode(arr[0],"utf-8");//用户名
                String password = arr[1];//密码
                //在UserService类中提供了登录功能的实现，所以创建UserService对象
                //调用loginUser方法实现登录
                UserService  userService = new UserService();
                try{
                    User user = userService.loginUser(username,password);
                    //保存登录状态
                    session.setAttribute("user",user);
                }catch (MsgException e){
                    //如果登录失败，抛出异常，也不需要作出任何操作
                }

            }

        }

        //放行包含Session的request
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
