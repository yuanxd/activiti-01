<%@include file="common.jsp"%>
<script type="text/javascript"
	src="${ctx}/javascript/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/ztree/jquery.ztree.core-3.5.js"></script>
<link href="${ctx}/javascript/easyui/themes/default/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/javascript/easyui/themes/icon.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/javascript/easyui/themes/icon.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/javascript/ztree/zTreeStyle.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	$.fn.panel.defaults.onBeforeDestroy = function() {
		var frame = $('iframe', this);
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	};
</script>
