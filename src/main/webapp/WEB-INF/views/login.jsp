<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ctx = request.getContextPath();
%>
<html>
<head>
<%@ include file="common/global.jsp"%>
<%@ include file="common/meta.jsp"%>
<title>登录</title>
</head>
<body>
	<form action="${ctx}/login/doLogin" method="post">
		<table>
			<tr>
				<td>UserName</td>
				<td><input type="text" id="name" name="name" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" id="password" name="password" /></td>
			</tr>
			<tr>
				<td />
				<td><input type="submit" value="login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>