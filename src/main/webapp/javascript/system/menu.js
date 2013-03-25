$(function() {
	var setting = {
		view : {
			selectedMulti : false
		},
		callback : {
			onAsyncSuccess : zTreeOnAsyncSuccess,
			onClick : onClick
		},
		async : {
			enable : true,
			url : ctx + "/system/menu/getNodes",
			autoParam : [ "id", "name=name" ],
			dataFilter : filter
		}
	};
	$.fn.zTree.init($("#functions"), setting);

	$('#code').combobox({
		valueField : 'code',
		textField : 'name'
	});

	$("#addBtn").click(function() {
		doAddHandler();
	});

	$("#saveBtn").click(function() {
		doSaveHandler();
	});

	$("#deleteBtn").click(function() {
		doDeleteHandler();
	});

});
function doAddHandler() {
	$.getJSON(ctx + "/system/menu/pages", function(json) {
		$('#code').combobox({
			data : json,
			valueField : 'code',
			textField : 'name',
			onSelect : function(rec) {
				$('#name').val(rec.name);
				$('#code').val(rec.code);
			}
		});
	});
	$('#name').val("");
	$('#code').val("");
	$('#id').val("");
	var nodes = getSelectTreeNode();
	if (nodes && nodes.length > 0) {
		$('#pid').val(nodes[0].id);
	}
}

function doDeleteHandler() {
	$.ajax({
		type : "POST",
		url : ctx + "/system/menu/deletePage",
		dataType : 'json',
		data : $('#detailForm').serialize(),
		success : function(data) {

		}
	});
}
function doSaveHandler() {
	$.ajax({
		type : "POST",
		url : ctx + "/system/menu/savePage",
		dataType : 'json',
		data : $('#detailForm').serialize(),
		success : function(data) {

		}
	});
}
/**
 * 刷新树图
 */
function reloadTree() {
	$.fn.zTree.getZTreeObj("functions").reAsyncChildNodes(null, "refresh");
}
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		childNodes[i].isParent = childNodes[i].folder;
	}
	return childNodes;
}
function onClick(event, treeId, treeNode, clickFlag) {
	loadData(treeNode);
}
/**
 * 查询成功后设置节点选中
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("functions");
	var nodes = treeObj.getSelectedNodes();
	if (!nodes || nodes.length == 0) {
		nodes = treeObj.getNodes();
		if (nodes && nodes.length > 0) {
			treeObj.selectNode(nodes[0]);
			loadData(nodes[0]);
		}
	}
}
/**
 * 获取树图选中的节点
 * 
 * @returns
 */
function getSelectTreeNode() {
	var treeObj = $.fn.zTree.getZTreeObj("functions");
	return treeObj.getSelectedNodes();
}
/**
 * 加载选中节点的详细信息
 */
function loadData(treeNode) {
	$('#code').attr("readonly", true);
	if (treeNode.getParentNode()) {
		$('#pid').val(treeNode.getParentNode());
	}
	$('#detailForm').form('load', ctx + '/system/menu/getNode/' + treeNode.id);

}