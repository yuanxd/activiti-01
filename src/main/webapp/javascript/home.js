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
	var systemConfigTreeNodes = [ {
		id : 1,
		pId : 0,
		name : "菜单配置",
		open : true,
		attributes : {
			url : ctx + "/system/menu"
		}
	} ];

	function onClick(event, treeId, treeNode, clickFlag) {
		var zTree = $.fn.zTree.getZTreeObj("functionTree");
		zTree.expandNode(treeNode);
		if (!treeNode.link)
			return;
		if ($("#tabs").tabs('exists', treeNode.name)) {
			$('#tabs').tabs('select', treeNode.name);
		} else {
			$('#tabs')
					.tabs(
							'add',
							{
								title : treeNode.name,
								closable : true,
								content : '<iframe width="100%" height="100%" frameborder="0" src='
										+ ctx
										+ treeNode.link
										+ ' style="width:100%;height:100%;"></iframe>'
							});
		}

	}
	$.fn.zTree.init($("#functionTree"), setting);

	$.fn.zTree.init($("#systemConfigTree"), setting, systemConfigTreeNodes);
	// 在右边center区域打开菜单，新增tab
	function open(text, url, useIframe) {
		if ($("#tabs").tabs('exists', text)) {
			$('#tabs').tabs('select', text);
		} else {
			$('#tabs')
					.tabs(
							'add',
							{
								title : text,
								closable : true,
								content : '<iframe width="100%" height="100%" frameborder="0" src='
										+ url
										+ ' style="width:100%;height:100%;"></iframe>'
							});
		}
	}

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
});
/**
 * 查询成功后设置节点选中
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	var treeObj = $.fn.zTree.getZTreeObj("functionTree");
	var nodes = treeObj.getSelectedNodes();
	if (!nodes || nodes.length == 0) {
		nodes = treeObj.getNodes();
		if (nodes && nodes.length > 0) {
			treeObj.selectNode(nodes[0]);
		} else {
			$('#detailForm').form('clear');
		}
	}
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
		childNodes[i].isParent = childNodes[i].folder;
	}
	return childNodes;
}