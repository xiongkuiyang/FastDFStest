<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<style type="text/css">
	body {
		margin: 0px auto 0px auto;
		width: 50%;
	}
</style>
</head>
<body>
	<h1>注册页面</h1>
	
	<p>${requestScope.message }</p>
	<form action="demo/user/regist" method="post">
		账号:<input type="text" name="userName"><br/>
		密码:<input type="password" name="userPwd"><br/>
		昵称:<input type="text" name="userNick"/><br/>
		<input type="submit" value="注册">
	</form>
</body>
</html>