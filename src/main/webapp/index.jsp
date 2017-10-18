<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"> 
<style type="text/css">
	body {
		margin: 0px auto 0px auto;
		width: 50%;
	}
</style>
</head>
<body>
	<!-- 判断用户是否登录 -->
	<c:if test="${sessionScope.loginUser == null }">
		<a href="demo/user/toLoginPage">登录</a>|
		<a href="demo/user/toRegistPage">注册</a>
	</c:if>
	<c:if test="${sessionScope.loginUser != null }">
		欢迎您：${sessionScope.loginUser.userNick }
		<a href="demo/user/showDetail">进入个人中心</a>
		<a href="demo/user/logout">退出</a>
	</c:if>
		
	<br/>
	<form action="demo/user/search" method="post">
		<input type="text" name="keywords"/>
		<input type="submit" value="搜索"/>
	</form>

</body>
</html>