<%@ page import="java.util.List" %>
<%@ page import="cn.tedu.domain.Msg" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/24
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>主页面</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script type="javascript" src="js/jquery-1.4.2.js"></script>

</head>

<body bgcolor="#FFFFFF" text="#000000">
<p>&nbsp;</p>
<table width="500" border="0" align="center">
  <tr>
    <td height="34"><font size="4"><b>
      <a href="fabu.jsp">发布信息</a></b></font> &nbsp;
      &nbsp; &nbsp; <a href="${pageContext.request.contextPath}/LoginOutServlet"><b><font size="4"> 退出系统</font></b></a></td>
  </tr><tr>
  <td height="32" valign="bottom"><font size="4"><b>留言信息:</b></font></td>
</tr>

  <tr>
    <td>
      <table width="100%" border="1" bordercolor="#3399CC" cellspacing="1">
        <tr align="center">
          <td width="195">发送人</td>
          <td width="195">接收人</td>
          <td width="230">信息内容</td>
          <td width="200" align="center">发送时间</td>
          <td>&nbsp;</td>
        </tr>
       <%-- <%
          List<Msg> mm = (List<Msg>) request.getAttribute("msgsAll");

        %>--%>
        <c:forEach var="m" items="${msgsAll}" >
          <tr >
          <td align="center">${m.sendUser}</td>
            <td align="center">${m.recUser}</td>
            <td align="left" id="msg">${m.msgs}</td>
            <td align="center">${m.sendTime}</td>
            <td align="center" >${m.id}</td>

            <td><a href="<%=request.getContextPath()%>/DeleteServlet?id=${m.id}" ><img src="trash.gif"></img></a></td>
        </tr>
        </c:forEach>
      </table>
      <p>&nbsp;</p>
    </td>
  </tr>
  <tr>
    <td>
      <p>&nbsp;</p>
    </td>
  </tr>
</table>
</body>
</html>
