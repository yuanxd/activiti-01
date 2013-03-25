<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/global.jsp"%>
<%@ include file="../common/meta.jsp"%>
<title>Index</title>
<script src="${ctx}/javascript/home.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${ctx}/javascript/jcrop/jquery.Jcrop.js"></script>
<link rel="stylesheet" href="${ctx}/javascript/jcrop/jquery.Jcrop.css"
	type="text/css" />
<script type="text/javascript" src="${ctx}/javascript/system/menu.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" split="true" title="菜单功能" style="width: 250px;">
		<ul id="functions" class="ztree" style="width: 260px; overflow: auto;"></ul>
	</div>
	<div region="center" id="content">
		<form id="detailForm" method="post" >
		    <input type="hidden" name="pid" id="pid"> 
			<div>
				<label for="name">名称:</label> <input class="easyui-validatebox"
					type="text" id="name" name="name" data-options="required:true"></input>
			</div>
			<div>
				<label for="code">代码:
				<input class="easyui-combobox" id="code" name="code" id="code">
			</div>
		</form>
	</div>
	<div region="north" style="height: 35px">
		<div class="toolbar" style="margin: 3px 0;">
			<a id="addBtn" name="addBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增下级</a> 
			<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a> 
		</div>
	</div>
</body>
</html>