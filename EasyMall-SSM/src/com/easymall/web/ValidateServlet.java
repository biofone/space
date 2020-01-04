package com.easymall.web;

import com.easymall.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
生成验证码
 */
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.控制浏览器不使用缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("pragma","no-cache");
        //2.生成验证码图片。
        VerifyCode vc = new VerifyCode();
        vc.drawImage(response.getOutputStream());
        //3.获取验证码图片字符
        String code = vc.getCode();
        //4.将生成的验证码加入session域中
        request.getSession().setAttribute("code",code);
        System.out.println(code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
