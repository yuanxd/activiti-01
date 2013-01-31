<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var ctx = '<%=request.getContextPath()%>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	apply:
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/test/start" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>测试</small>
			</legend>
			<table border="1">
				<tr>
					<td>名称：</td>
					<td><input type="text" id="name" name="name" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<button type="submmit">申请</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</form:form>
	list:
	<form:form id="listForm" modelAttribute="user"
		action="${ctx}/test/todo" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>测试</small>
			</legend>
			<table border="1">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<button type="submmit">查询</button>
					</td>
				</tr>
			</table>
		</fieldset>
	</form:form>
</body>
</html>