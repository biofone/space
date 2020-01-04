package com.easymall.web;

import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;
import com.easymall.utils.JDBCUtils;
import com.easymall.utils.MD5Utils;
import com.easymall.utils.WebUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.乱码处理
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        //2.获取请求参数--用户填写的信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");
        //3.校验
        //非空校验
        if(WebUtils.isNull(username)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","用户名不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        if(WebUtils.isNull(password)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","密码不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        if(WebUtils.isNull(password2)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","确认密码不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        if(WebUtils.isNull(nickname)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","昵称不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        if(WebUtils.isNull(email)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","邮箱不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        if(WebUtils.isNull(valistr)){
            //1.设置错误提示信息，添加在域对象request身上
            request.setAttribute("msg","验证码不能为空");
            //2.请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;//请求转发前后的代码会正常执行，此处发生校验，应该打断后续代码。
        }
        //密码一致性校验
            //1.获取两个密码框中的数据
            //2.判断两次密码是否一致
        if(!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //邮箱格式校验
            //1.邮箱正则  lishuai@tedu.cn.com
            //2.使用用户输入的邮箱和正则做判断
        String reg = "\\w+@\\w+(\\.\\w+)+";
        if(!email.matches(reg)){
            request.setAttribute("msg","邮箱格式不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //验证码校验
        //使用用户输入的验证码和session域中的验证码作比对
        String code = (String) request.getSession().getAttribute("code");
        if(!code.equalsIgnoreCase(valistr)){
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //4.完成注册--JDBC
        /*Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn =  JDBCUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username = ?");
            ps.setString(1,username);
            rs = ps.executeQuery();
            if(rs.next()){
                request.setAttribute("msg","用户名已存在");
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
                return;
            }else{
                ps = conn.prepareStatement("insert into user values(null,?,?,?,?)");
                ps.setString(1,username);
                ps.setString(2,password);
                ps.setString(3,nickname);
                ps.setString(4,email);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
*/
        //当前为web层， 需要访问service层， 创建service层对象
        UserService userService = new UserService();
        //在userService身上存在一个方法，这个方法负责完成注册。
        //registUser需要传递参数较多，所以将参数封装在一个User对象身上
        User user = new User(0,username, MD5Utils.md5(password),nickname,email);
        try{
            userService.registUser(user);
        }catch(MsgException e){
            //一旦捕获异常，则证明用户名不可以，需要作出提示
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }


        //5.注册完成后，跳转到网站首页
        response.getWriter().write(
                "<h1 align='center'>" +
                        "<font color='red'>恭喜，注册成功，3秒之后跳转到首页..</font>" +
                        "</h1>");
        response.setHeader("refresh","3;url=http://www.easymall.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
