<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/global.jsp"%>
<%@ include file="../common/meta.jsp"%>
<title>Index</title>
<script type="text/javascript" src="${ctx}/javascript/system/menu.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" split="true" title="菜单功能" style="width: 250px;"
		data-options="tools:'#tt'">
		<ul id="menus" class="ztree" style="width: 260px; overflow: auto;"></ul>
	</div>
	<div region="center" id="content" style="padding: 10px;">
		<form id="detailForm" method="post">
			<input type="hidden" name="pid" id="pid"> 
            <input type="hidden" name="id" id="id">
            <input type="hidden" name="folder" id="folder">
			<table>
				<tr>
					<td><label for="name">名称:</label></td>
					<td><input class="easyui-validatebox" type="text" id="name"
						name="name" data-options="required:true"></td>
				</tr>
				<tr>
					<td><label for="code">代码:</label></td>
					<td><input class="easyui-validatebox" id="code" name="code" data-options="required:true"></td>
				</tr>
				<tr>
					<td><label for="link">链接:</label></td>
					<td><input class="easyui-validatebox" id="link" name="link"></td>
				</tr>
				<tr>
					<td><label for="status">状态:</label></td>
					<td><select class="easyui-combobox" name="status" id="status"
						style="width: 200px;" data-options="required:true,editable:false">
							<option value="1">启用</option>
							<option value="0">禁用</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="func">选择功能:</label></td>
					<td><select class="easyui-combobox" name="func" id="func"
						style="width: 200px;">
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div region="north" style="background:#C9EDCC;padding:5px;width:600px;">
        <a href="javascript:void(0)" id="mb1" class="easyui-menubutton" data-options="plain:false,menu:'#mm1',iconCls:'icon-add'">新增</a>
        <a href="javascript:void(0)" id="saveBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        <a href="javascript:void(0)" id="deleteBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    </div>
    <div id="mm1" style="width:150px;">
        <div id="addFolderBtn" name="addFolderBtn" data-options="iconCls:'icon-add'">同级目录</div>
        <div id="addSubFolderBtn" name="addSubFolderBtn" data-options="iconCls:'icon-add'">下级目录</div>
        <div id="addMenuBtn" name="addMenuBtn" data-options="iconCls:'icon-add'">菜单</div>
        <div id="addFunBtn" name="addFunBtn" data-options="iconCls:'icon-add'">功能</div>
    </div>
	<div id="tt">
		<a href="javascript:void(0)" class="icon-reload"
			onclick="reloadTree()"></a>
	</div>

</body>
</html>