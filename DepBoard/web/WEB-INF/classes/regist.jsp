<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/24
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录界面</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <script type="text/javascript" src="js/jquery-1.4.2.js"></script>
  <script type="text/javascript">
        var formObj={
            "checkData":function(){
                var canSub=true;
                canSub=checkNull("empName","用户名不能为空")&&canSub;
                canSub=checkNull("password","密码不能为空")&&canSub;
                return canSub;
            },
            "checkNull":function(name,msg){
                var tag=$("input[name='"+name+"']").val();
                $("input[name='"+name+"']").nextAll("span").text("");
                if(tag==""){
                    $("input[name='"+name+"']").nextAll("span").text(msg);
                    return false;
                }
                return true;
            },

        };
        //文档就绪事件
        $(function(){
            $("input[name='empName']").blur(function(){
                formObj.checkNull("empName","用户名不能为空");
              //获取用户名
              var name=$("input[name='empName']").val();
              if(name!=""){
                $("#empName1").load("${pageContext.request.contextPath}/AjaxNameServlet",{"empName":name})
              }
            });
            $("input[name='password']").blur(function(){
                formObj.checkNull("password","密码不能为空")
            });

        });


  </script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<p>&nbsp;</p>
<form action="${pageContext.request.contextPath}/RegistServlet" method="post" onsubmit="return formObj.checkData()">
  <table width="258" border="1" align="center" cellspacing="1" bordercolor="#3399CC">
    <tr align="center">
      <td colspan="2" height="59"><font size="6"><b><font color="#330099" size="5">留言板用户注册</font></b></font></td>
    </tr>
      <tr>
          <td width="96" border="0" align="center" colspan="2" style="color: red;text-align: center">${msg}</td>
      </tr>
    <tr>
      <td width="96" align="right">姓名:</td>
      <td width="154">
        <input type="text" name="empName" value="" />
          <span id="empName1"></span>
      </td>
    </tr>
    <tr>
      <td width="96" align="right">口令:</td>
      <td width="154">
        <input type="password" name="password" />
          <span id="password"></span>
      </td>
    </tr>
    <tr align="center">
      <td colspan="2">
        <input type="submit" name="oper" value=" 注  册 ">
        <input type="reset" name="reset" value=" 复  位 ">
      </td>
    </tr>
  </table>
</form>
</body>
</html>

