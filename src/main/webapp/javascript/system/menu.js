$(function() {
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
			otherParam : {
				"otherParam" : "zTreeAsyncTest"
			},
			dataFilter : filter
		}
	};
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
	$.fn.zTree.init($("#functions"), setting);

	/**
	 * 加载选中节点的详细信息
	 */
	function loadData(treeNode) {
		$('#code').attr("readonly", true);
		$('#pid').val(treeNode.id);
		$('#detailForm').form('load',
				ctx + '/system/menu/getNode/' + treeNode.id);

	}
	$('#code').combobox({
		valueField : 'code',
		textField : 'name'
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
	$("#addBtn").click(function() {
		doAddHandler();
	});

	$("#saveBtn").click(function() {
		doSaveHandler();
	});

});
