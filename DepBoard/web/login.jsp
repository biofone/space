<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/24
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
  <title>登录界面</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
  <script type="text/javascript">
    $(function(){
      $("input[name='empName']").val(decodeURI('${cookie.empName.value}'))
    });

  </script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<p>&nbsp;</p>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
  <table width="258" border="1" align="center" cellspacing="1" bordercolor="#3399CC">
    <tr align="center">
      <td colspan="2" height="59"><font size="6"><b><font color="#330099" size="5">开发部内部留言板</font></b></font></td>
    </tr>
    <tr>
      <td width="96" border="0" align="center" colspan="2" style="color: red;text-align: center">${msg}</td>
    </tr>
    <tr>
      <td width="96" align="right">姓名:</td>
      <td width="154">
        <input type="text" name="empName" value="" size="15">
      </td>
    </tr>
    <tr>
      <td width="96" align="right">口令:</td>
      <td width="154">
        <input type="password" name="password" size="15">
      </td>
    </tr>
    <tr align="center">
      <td colspan="2">
        <input type="submit" name="oper" value=" 登  录 ">
        <input type="reset" name="reset" value=" 复  位 ">
        <a href="regist.jsp">注册</a>
      </td>
    </tr>
  </table>
</form>
</body>
</html>

