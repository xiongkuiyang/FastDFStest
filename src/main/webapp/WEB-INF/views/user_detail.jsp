<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<style type="text/css">
	body {
		margin: 0px auto 0px auto;
		width: 50%;
	}
</style>
</head>
<body>
	<table>
		<tr><td colspan="2">用户信息</td></tr>
		<tr>
			<td>昵称</td>
			<td>${sessionScope.loginUser.userNick }</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<c:if test="${sessionScope.loginUser.userGender == 0 }">男</c:if>
				<c:if test="${sessionScope.loginUser.userGender == 1 }">女</c:if>
			</td>
		</tr>
		<tr>
			<td>职业</td>
			<td>${(empty sessionScope.loginUser.userJob)?"未指定":sessionScope.loginUser.userJob }</td>
		</tr>
		<tr>
			<td>家乡</td>
			<td>${(empty sessionScope.loginUser.userHometown)?"未指定":sessionScope.loginUser.userHometown }</td>
		</tr>
		<tr>
			<td>自我介绍</td>
			<td>${(empty sessionScope.loginUser.userDesc)?"未指定":sessionScope.loginUser.userDesc }</td>
		</tr>
		<tr>
			<td>头像</td>
			<td>
				<c:if test="${!empty sessionScope.loginUser.userPicGroup }">
					<img src="http://192.168.72.246/${sessionScope.loginUser.userPicGroup}
					/${sessionScope.loginUser.userPicFilename}">
				</c:if>
				<c:if test="${empty sessionScope.loginUser.userPicGroup }">
					尚未上传头像
				</c:if> 
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="demo/user/toEditUI">更新</a>
			</td>
		</tr>
	</table>
	
	<a href="index.jsp">回首页</a>
</body>
</html>