package cn.chaochao.web;


import cn.chaochao.domain.User;
import cn.chaochao.exception.MsgException;
import cn.chaochao.service.UserService;
import cn.chaochao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String empName=request.getParameter("empName");
        String password=request.getParameter("password");
        //非空判断
        if(WebUtils.isNull(empName)){
            request.setAttribute("msg","用户名不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
        }
        if(WebUtils.isNull(password)){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
        }

        UserService userService=new UserService();
        User user=new User(0,empName,password);
        try{
            userService.registUser(user);
        }catch(MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
        }

        //登录完成
        response.getWriter().write("注册成功，3秒后跳转到首页");
        response.setHeader("refresh","1;url=index.jsp");
//        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
