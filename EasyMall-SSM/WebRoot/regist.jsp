<%--
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
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
    <!--引入jQuery函数库-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
           var formObj = {
                "checkData":function(){
                    var canSub = true;
                   /* //1.获取参数
                    var username = $("input[name='username']").val();
                    //清空操作
                    $("input[name='username']").nextAll("span").text("");
                    //2.非空
                    if(username == ""){
                        //1.找到当前元素后的span
                        $("input[name='username']").nextAll("span").text("用户名不能为空!");
                    }*/
                    canSub = this.checkNull("username","用户名不能为空!") && canSub;
                    canSub = this.checkNull("password","密码不能为空!")&& canSub;
                    canSub = this.checkNull("password2","确认密码不能为空!")&& canSub;
                    canSub = this.checkNull("nickname","昵称不能为空!")&& canSub;
                    canSub = this.checkNull("email","邮箱不能为空!")&& canSub;
                    canSub = this.checkNull("valistr","验证码不能为空!")&& canSub;
                    //3.密码一致性
                    canSub = this.checkPassword()&& canSub;

                    //4.邮箱格式
                    canSub = this.checkEmail()&& canSub;
                    return canSub;
                },
               "checkNull":function(name,msg){
                   //1.获取参数
                   var tag = $.trim($("input[name='"+name+"']").val());
                   //清空操作
                   $("input[name='"+name+"']").nextAll("span").text("");
                   //2.非空
                   if(tag == ""){
                       //1.找到当前元素后的span
                       $("input[name='"+name+"']").nextAll("span").text(msg);
                       return false;
                   }
                   return true;
               },
               "checkPassword":function(){
                   //获取两个密码框中的数据，作比对，如果不同，则作出提示
                   var password = $("input[name='password']").val();
                   var password2 = $("input[name='password2']").val();

                   if(password != password2){
                       $("input[name='password2']").nextAll("span").text("两次密码不一致");
                       return false;
                   }
                   return true;
               },
               "checkEmail":function(){
                    //1.邮箱正则   lishuai@tedu.cn
                   var reg = /\w+@\w+(\.\w+)+/;
                   //2.用户输入的邮箱
                   var email = $("input[name='email']").val();
                   if(email!="" && !reg.test(email)){
                       $("input[name='email']").nextAll("span").text("邮箱格式不正确!");
                       return false;
                   }
                   return true;
               }
            };
            //文档就绪事件
           $(function(){
               //鼠标离开输入框，则发生校验操作
               $("input[name='username']").blur(function(){
                   formObj.checkNull("username","用户名不能为空");
                   //获取输入框中的用户名
                   var username = $("input[name='username']").val();
                   if(username != ""){
                       //ajax用户名是否存在校验
                       $("#username").load("${pageContext.request.contextPath}/AjaxCheckUsername",{"username":username});
                   }
               });
               $("input[name='password']").blur(function(){
                   formObj.checkNull("password","密码不能为空");
               });
               $("input[name='password2']").blur(function(){
                   formObj.checkNull("password2","确认密码不能为空");
                   formObj.checkPassword();
               });
               $("input[name='nickname']").blur(function(){
                   formObj.checkNull("nickname","昵称不能为空");
               });
               $("input[name='email']").blur(function(){
                   formObj.checkNull("email","邮箱不能为空");
                   formObj.checkEmail();
               });
               $("input[name='valistr']").blur(function(){
                   formObj.checkNull("valistr","验证码不能为空");
               });

               //图片单击事件--单击时更换验证码
               $("#img").click(function(){
                   var date = new Date();
                   var time = date.getTime();
                   $(this).attr("src","${pageContext.request.contextPath}/ValidateServlet?time="+time);
               });
           });
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/RegistServlet" method="POST" onsubmit="return formObj.checkData()">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td class="tds" colspan="2" style="color:red;text-align: center">
                <%--后台传入此处的校验内容--%>
<%--                <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
                ${msg}
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username"
               value="${param.username}"/>
                <span id="username"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password"/><span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2"/><span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname"
              value="${param.nickname}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email"
         value="${param.email}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr"/>
                <img id="img" src="${pageContext.request.contextPath}/ValidateServlet" width="" height="" alt="" />
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
