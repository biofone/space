package cn.chaochao.web;

import cn.chaochao.domain.Msg;
import cn.chaochao.domain.User;
import cn.chaochao.service.UserService;
import cn.chaochao.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/FabuServlet")
public class FabuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取请求参数
        User user= (User) request.getSession().getAttribute("user");
        String sendUser=null;
        String msgs=null;
        String recUser=null;
        if(user == null){
            response.sendRedirect("/login.jsp");
        }else{
            sendUser=user.getName();
            msgs=request.getParameter("Msgs");
            recUser=request.getParameter("recName");
        }
        Date date=new Date();
        String sendTime=date.toLocaleString();
        //非空判断
        if(WebUtils.isNull(msgs)){
            request.setAttribute("msg","信息内容不能为空");
            request.getRequestDispatcher("/fabu.jsp").forward(request,response);
        }
        if(WebUtils.isNull(recUser)){
            request.setAttribute("msg","接收人不能为空");
            request.getRequestDispatcher("/fabu.jsp").forward(request,response);
        }

        //发布信息
        UserService userService=new UserService();
        Msg msg=new Msg(0,sendUser,msgs,recUser,sendTime);
        userService.send(msg);

        response.sendRedirect(request.getContextPath()+"/viewServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
