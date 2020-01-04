<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE HTML>
<html>
<head>
  <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
  <title>欢迎光临EasyMall</title>
</head>
<body>
<%--拼接头部--%>
<%--<%request.getRequestDispatcher("/_head.jsp").include(request,response);%>--%>
<%--静态包含--%>
<%@include file="_head.jsp"%>
<div id="index">
  <div id="line1">
    <img src="${pageContext.request.contextPath}/img/index/banner_big.jpg"/>
  </div>
  <div id="line2">
    <img id="line2_1" src="${pageContext.request.contextPath}/img/index/adv1.jpg"/>
    <img id="line2_2" src="${pageContext.request.contextPath}/img/index/adv2.jpg"/>
    <img id="line2_3" src="${pageContext.request.contextPath}/img/index/adv_l1.jpg"/>
  </div>
  <div id="line3">
    <img id="line3_1" src="${pageContext.request.contextPath}/img/index/adv3.jpg"/>
    <img id="line3_2" src="${pageContext.request.contextPath}/img/index/adv4.jpg"/>
    <div id="line3_right">
      <img id="line3_3" src="${pageContext.request.contextPath}/img/index/adv_l2.jpg"/>
      <img id="line3_4" src="${pageContext.request.contextPath}/img/index/adv_l3.jpg"/>
    </div>
  </div>
  <div id="line4">
    <img src="${pageContext.request.contextPath}/img/index/217.jpg"/>
  </div>
  <div id="line5">
				<span id="line5_1">
					<img src="${pageContext.request.contextPath}/img/index/icon_g1.png"/>&nbsp;&nbsp;500强企业 品质保证
				</span>
    <span id="line5_2">
					<img src="${pageContext.request.contextPath}/img/index/icon_g2.png"/>&nbsp;&nbsp;7天退货 15天换货
				</span>
    <span id="line5_3">
					<img src="${pageContext.request.contextPath}/img/index/icon_g3.png"/>&nbsp;&nbsp;100元起免运费
				</span>
    <span id="line5_4">
					<img src="${pageContext.request.contextPath}/img/index/icon_g4.png"/>&nbsp;&nbsp;448家维修网点 全国联保
				</span>
  </div>
</div>
<%--拼接尾部--%>
<%--<%request.getRequestDispatcher("/_foot.jsp").include(request,response);%>--%>
<%--静态包含--%>
<%@include file="_foot.jsp"%>
</body>
</html>

