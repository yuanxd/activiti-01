<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="common/global.jsp"%>
<title>Index</title>
<%@ include file="common/meta.jsp"%>
</head>
<body>
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/index/start" method="post" class="form-horizontal">
		<fieldset>
			<legend>
				<small>Test流程发起</small>
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
</body>
</html>