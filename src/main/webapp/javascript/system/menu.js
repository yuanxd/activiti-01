$(function() {
	init();
});
/**
 * 画面初始化处理
 */
function init() {
	/** 初始化树图 */
	$.fn.zTree.init($("#menus"), {
		view : {
			selectedMulti : false
		},
		callback : {
			// onAsyncSuccess : zTreeOnAsyncSuccess,
			onClick : onClick
		},
		async : {
			enable : true,
			url : ctx + "/system/menu/getNodes",
			autoParam : [ "id" ],
			dataFilter : filter
		}
	});
	/** 初始化按钮事件 */
	// 新增同级目录
	$("#addFolderBtn").click(doAddFolderHandler);
	// 新增下级目录
	$("#addSubFolderBtn").click(doAddSubHandler);
	// 新增菜单
	$("#addMenuBtn").click(doAddMenuHandler);
	// 新增菜单功能
	$("#addFunBtn").click(doAddFunHandler);
	// 保存
	$("#saveBtn").click(doSaveHandler);
	// 删除
	$("#deleteBtn").click(doDeleteHandler);
}
/**
 * 新增同级目录处理
 */
function doAddFolderHandler() {
	// 当前选中节点的父节点必须为目录获取空
	var node = getSelectTreeNode();
	if (node) {
		var pNode = node.getParentNode();
		if (pNode && !pNode.folder) {
			$.messager.alert("提示", "必须在目录下新增！");
			return;
		}
	}
	setFormStatus('folder');
	$('#name').val("");
	$('#code').val("");
	$('#id').val("");
	$('#pid').val("");
	$('#folder').val(true);
	var node = getSelectTreeNode();
	if (node) {
		var pNode = node.getParentNode();
		if (pNode) {
			$('#pid').val(pNode.id);
		}
	}
}
/**
 * 新增下级目录处理
 */
function doAddSubHandler() {
	/** 当前节点必须为目录 */
	var node = getSelectTreeNode();
	if (node && !node.folder) {
		$.messager.alert("提示", "必须在目录下新增！");
		return;
	}
	setFormStatus('folder');
	$('#name').val("");
	$('#code').val("");
	$('#id').val("");
	$('#pid').val("");
	$('#folder').val(true);
	var node = getSelectTreeNode();
	if (node) {
		$('#pid').val(node.id);
	}
}
/**
 * 新增菜单处理
 */
function doAddMenuHandler() {
	var node = getSelectTreeNode();
	if (!node || !node.folder) {
		$.messager.alert("提示", "必须在目录下新增！");
		return;
	}
	setFormStatus('menu');
	$.getJSON(ctx + "/system/menu/pages", function(json) {
		$('#func').combobox({
			data : json,
			valueField : 'code',
			textField : 'name',
			editable : false,
			onSelect : function(rec) {
				$('#name').val(rec.name);
				$('#code').val(rec.code);
				$('#link').val(rec.link);
			}
		});
	});
	$('#name').val("");
	$('#code').val("");
	$('#id').val("");
	$('#pid').val("");
	$('#folder').val(false);
	var node = getSelectTreeNode();
	if (node) {
		$('#pid').val(node.id);
	}
}
/**
 * 新增功能处理
 */
function doAddFunHandler() {
	var node = getSelectTreeNode();
	if (!node || node.folder) {
		$.messager.alert("提示", "必须在菜单下新增！");
		return;
	}
	$('#detailForm').form('clear');
	setFormStatus('func');
	$.getJSON(ctx + "/system/menu/functions/" + node.code, function(json) {
		$('#func').combobox({
			data : json,
			valueField : 'code',
			textField : 'name',
			editable : false,
			onSelect : function(rec) {
				$('#name').val(rec.name);
				$('#code').val(rec.code);
			}
		});
	});
	$('#folder').val(false);
	var node = getSelectTreeNode();
	if (node) {
		$('#pid').val(node.id);
	}
}

/**
 * 删除处理
 */
function doDeleteHandler() {
	$.ajax({
		type : "POST",
		url : ctx + "/system/menu/deletePage",
		dataType : 'json',
		data : $('#detailForm').serialize(),
		success : function(data) {
			$.fn.zTree.getZTreeObj("menus").reAsyncChildNodes(null, "refresh");
		}
	});
}
/**
 * 保存处理
 */
function doSaveHandler() {
	if ($('#detailForm').form('validate')) {
		$.ajax({
			type : "POST",
			url : ctx + "/system/menu/savePage",
			dataType : 'json',
			data : $('#detailForm').serialize(),
			success : function(data) {
				reloadTree();
			}
		});
	}

}
/**
 * 刷新树图
 */
function reloadTree() {
	var treeObj = $.fn.zTree.getZTreeObj("menus");
	var nodes = treeObj.getSelectedNodes();
	var node = null;
	if (nodes && nodes.length > 0) {
		node = nodes[0];
		if ($('#pid').val() != node.id)
			node = node.getParentNode();
	}
	$.fn.zTree.getZTreeObj("menus").reAsyncChildNodes(node, "refresh");
}
/**
 * 树图过滤
 * 
 * @param treeId
 * @param parentNode
 * @param childNodes
 * @returns
 */
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for ( var i = 0, l = childNodes.length; i < l; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		// childNodes[i].isParent = childNodes[i].folder;
		if (parentNode && parentNode.folder == false) {
			childNodes[i].isParent = false;
		} else {
			childNodes[i].isParent = true;
		}
	}
	return childNodes;
}
/**
 * 树图单击处理
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function onClick(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("menus");
	zTree.expandNode(treeNode);
	loadData(treeNode);
}
/**
 * 查询成功后设置节点选中
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("menus");
	var nodes = treeObj.getSelectedNodes();
	if (!nodes || nodes.length == 0) {
		nodes = treeObj.getNodes();
		if (nodes && nodes.length > 0) {
			treeObj.selectNode(nodes[0]);
			loadData(nodes[0]);
		} else {
			setFormStatus();
		}
	}
}
/**
 * 设置表单状态<br>
 * status: null 只读<br>
 * status:folder目录表单<br>
 * status:menu 菜单表单<br>
 * status:func 功能表单<br>
 */
function setFormStatus(status) {
	$('#detailForm input').attr('readonly', 'readonly');
	$('#detailForm select').combobox('disable');
	if ('folder' == status) {
		$('#name').attr('readonly', false);
		$('#code').attr('readonly', false);
		$('#status').combobox('enable');
	} else if ('menu' == status) {
		$('#name').attr('readonly', false);
		$('#detailForm select').combobox('enable');
	} else if ('func' == status) {
		$('#name').attr('readonly', false);
		$('#detailForm select').combobox('enable');
	} else {

	}
}
/**
 * 获取树图选中的节点
 * 
 * @returns
 */
function getSelectTreeNodes() {
	var treeObj = $.fn.zTree.getZTreeObj("menus");
	return treeObj.getSelectedNodes();
}
/**
 * 获取选中的节点
 * 
 * @returns
 */
function getSelectTreeNode() {
	var nodes = getSelectTreeNodes();
	if (!nodes || nodes.length == 0) {
		return null;
	}
	return nodes[0];
}
/**
 * 加载选中节点的详细信息
 */
function loadData(treeNode) {
	$('#detailForm').form('clear');
	if (treeNode.getParentNode()) {
		$('#pid').val(treeNode.getParentNode());
	}
	if (treeNode.folder) {
		setFormStatus('folder');
	}
	$('#detailForm').form('load', ctx + '/system/menu/getNode/' + treeNode.id);

}