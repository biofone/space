package cn.chaochao.web;

import cn.chaochao.domain.User;
import cn.chaochao.exception.MsgException;
import cn.chaochao.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String depName=request.getParameter("empName");
        String password=request.getParameter("password");
        //记住用户名
        Cookie cookie=new Cookie("empName", URLEncoder.encode(depName,"utf-8"));
        cookie.setMaxAge(60*60*24*30);
        cookie.setPath(request.getContextPath()+"/index.jsp");
        response.addCookie(cookie);

        //30天自动登录？

        //用户登录状态
        UserService userService=new UserService();
        try{
            User user=userService.loginUser(depName,password);
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
        }catch(MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        response.sendRedirect(request.getContextPath()+"/viewServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
