<%--
  Created by IntelliJ IDEA.
  User: tedu
  Date: 2019/9/10
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
    <div id="line1">
        <%--如果没有登录状态，则显示如下内容--%>
        <div id="content">
        <%--如果有登录状态，则显示用户名，及一个登出按钮--%>
        <%--<%
            //如果有session对象，并且对象中包含username域属性，则证明是登录状态
            if(request.getSession(false) != null
                    && request.getSession().getAttribute("username") != null){
        %>--%>
            <c:if test="${sessionScope.user.username != null}">
                <a href="#">欢迎，${sessionScope.user.username} 回来</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/LogOutServlet">登出</a>
            </c:if>
           <%-- <%
            }else{
                %>--%>
            <c:if test="${sessionScope.user.username == null}">
                <a href="${pageContext.request.contextPath}/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/regist.jsp">注册</a>
            </c:if>


           <%-- <%
            }
        %>--%>


        </div>
    </div>
    <div id="line2">
        <img id="logo" src="${pageContext.request.contextPath}/img/head/logo.jpg"/>
        <input type="text" name=""/>
        <input type="button" value="搜 索"/>
        <span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="#">我的购物车</a>
		</span>
        <img id="erwm" src="${pageContext.request.contextPath}/img/head/qr.jpg"/>
    </div>
    <div id="line3">
        <div id="content">
            <ul>
                <li><a href="#">首页</a></li>
                <li><a href="#">全部商品</a></li>
                <li><a href="#">手机数码</a></li>
                <li><a href="#">电脑平板</a></li>
                <li><a href="#">家用电器</a></li>
                <li><a href="#">汽车用品</a></li>
                <li><a href="#">食品饮料</a></li>
                <li><a href="#">图书杂志</a></li>
                <li><a href="#">服装服饰</a></li>
                <li><a href="#">理财产品</a></li>
            </ul>
        </div>
    </div>
</div>