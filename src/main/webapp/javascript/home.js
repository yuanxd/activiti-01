$(function() {
	// 功能菜单，使用iframe创建画面
	var funTreeData = [ {
		text : "测试",
		children : [ {
			text : "Jcrop",
			attributes : {
				url : ctx + "/test/jcrop"
			}
		}, {
			text : "一级菜单2",
			attributes : {
				url : ""
			}
		}, {
			text : "一级菜单3",
			state : "closed",
			children : [ {
				text : "二级菜单1",
				attributes : {
					url : ""
				}
			}, {
				text : "二级菜单2",
				attributes : {
					url : ""
				}
			}, {
				text : "二级菜单3",
				attributes : {
					url : ""
				}
			} ]
		} ]
	}, {
		text : "测试",
	} ];
	// 系统配置菜单数据，不使用iframe创建画面
	var sysconfgTreeData = [ {
		text : "菜单管理",
		attributes : {
			url : ctx + "/system/menu"
		}
	}, {
		text : "用户管理"
	}, {
		text : "角色管理"
	}, {
		text : "组织管理"
	} ];

	// 实例化树形菜单
	$("#functionTree").tree({
		data : funTreeData,
		lines : true,
		onClick : function(node) {
			if (node.attributes) {
				open(node.text, node.attributes.url);
			}
		}
	});
	// 实例化树形菜单
	$("#systemConfigTree").tree({
		data : sysconfgTreeData,
		lines : true,
		onClick : function(node) {
			if (node.attributes) {
				open(node.text, node.attributes.url, node.attributes.iframe);
			}
		}
	});
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