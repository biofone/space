<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: tedu
  Date: 2019/9/10
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="css/login.css"/>
    <%--引入jQuery函数库--%>
    <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        //文档就绪事件 -- 等待页面加载完成之后，
        // 再向input[name='username']的value属性设置值
        $(function(){
            //1.读取cookie中名称为remname的value
            //2.向input[name='username']的value属性设置值
            $("input[name='username']").val(decodeURI('${cookie.remname.value}'));
        });
    </script>
    <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
    <table>
<%--        <%--%>
<%--            Cookie remnameC = null;--%>
<%--            //获取名称为remname的cookie--%>
<%--            Cookie[] cs = request.getCookies();--%>
<%--            for (Cookie c:cs){--%>
<%--                if("remname".equals(c.getName())){--%>
<%--                    remnameC = c;--%>
<%--                }--%>
<%--            }--%>
<%--            String username = "";--%>
<%--            if(remnameC != null){--%>
<%--                username = URLDecoder.decode(remnameC.getValue(),"utf-8");--%>
<%--            }--%>

<%--        %>--%>

        <tr>
            <td class="tdx" colspan="2" style="text-align: center;color:red">
<%--                <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                ${msg}
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名：</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td class="tdx">密&nbsp;&nbsp; 码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%--如果username不等于""就显示对勾--%>
<%--                        <%="".equals(username)?"":"checked='checked'"%>--%>
                       ${empty cookie.remname.value?"":"checked='checked'"} 
                />记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <input type="submit" value="登 陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
