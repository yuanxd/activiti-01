$(function() {
	// 初始化功能菜单树
	initFunctionTree();
	// 设置选项卡面板
//	initTables();
	// 初始化系统配置菜单
//	initSystemConfigTree();
});
/**
 * 初始化选项卡
 */
function initTables() {
	// 绑定tabs的右键菜单
	$("#tabs").tabs({
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
		}
	});
	// 实例化menu的onClick事件
	$("#tabsMenu").menu({
		onClick : function(item) {
			closeTab(this, item.name);
		}
	});

	// 几个关闭事件的实现
	function closeTab(menu, type) {
		var curTabTitle = $(menu).data("tabTitle");
		var tabs = $("#tabs");

		if (type === "close") {
			tabs.tabs("close", curTabTitle);
			return;
		}

		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];

		$.each(allTabs, function() {
			var opt = $(this).panel("options");
			if (opt.closable && opt.title != curTabTitle && type === "Other") {
				closeTabsTitle.push(opt.title);
			} else if (opt.closable && type === "All") {
				closeTabsTitle.push(opt.title);
			}
		});

		for ( var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
}
/**
 * 添加选项卡
 * 
 * @param name
 *            选项卡名称
 * @param link
 *            选项卡链接
 */
function addTab(name, link) {
	if ($("#tabs").tabs('exists', name)) {
		$('#tabs').tabs('select', name);
	} else {
		$('#tabs')
				.tabs(
						'add',
						{
							title : name,
							closable : true,
							content : '<iframe width="100%" height="100%" frameborder="0" src='
									+ ctx
									+ link
									+ ' style="width:100%;height:100%;"></iframe>'
						});
	}
}
/**
 * 初始化功能菜单树
 */
function initFunctionTree() {
	/**
	 * 功能菜单树节点单击处理
	 * 
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @param clickFlag
	 */
	function onClick(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("functionTree");
		// 没有链接的节点为目录节点，进行展开处理
		var link = treeNode.link;
		if (!link) {
			zTree.expandNode(treeNode);
			return;
		}
		addTab(treeNode.name, link);
	}
	$.fn.zTree.init($("#functionTree"), {
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
}
/**
 * 初始化系统配置树<br>
 * 目前树图节点写死，以后可以改为从数据库获取
 */
function initSystemConfigTree() {
	/**
	 * 功能菜单树节点单击处理
	 * 
	 * @param event
	 * @param treeId
	 * @param treeNode
	 * @param clickFlag
	 */
	function onClick(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("systemConfigTree");
		// 没有链接的节点为目录节点，进行展开处理
		var link = treeNode.link;
		if (!link) {
			zTree.expandNode(treeNode);
			return;
		}
		addTab(treeNode.name, link);
	}
	var setting = {
		view : {
			selectedMulti : false
		},
		callback : {
			// onAsyncSuccess : zTreeOnAsyncSuccess,
			onClick : onClick
		}
	};
	var systemConfigTreeNodes = [ {
		id : 1,
		pId : 0,
		name : "菜单配置",
		open : true,
		link : "/system/menu"
	} ];

	$.fn.zTree.init($("#systemConfigTree"), setting, systemConfigTreeNodes);
}
/**
 * 查询成功后设置节点选中
 */
// function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
// var treeObj = $.fn.zTree.getZTreeObj("functionTree");
// var nodes = treeObj.getSelectedNodes();
// if (!nodes || nodes.length == 0) {
// nodes = treeObj.getNodes();
// if (nodes && nodes.length > 0) {
// treeObj.selectNode(nodes[0]);
// }
// }
// }
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
		// childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		childNodes[i].isParent = childNodes[i].folder;
	}
	return childNodes;
}