<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var ctx = '<%=request.getContextPath()%>';
    function alertObj(obj) {
        if (typeof obj == 'object') {
            var text = "";
            for (var i in obj) {
                if (text.length > 1000) {
                    if (!window.confirm(text))
                        return;
                    text = "";
                }
                text += i + ":" + obj[i] + "\n";
            }
            if (text)
                window.confirm(text);
        } else 
            alert((typeof obj)+","+obj);
    }
</script>
<script type="text/javascript"
	src="${ctx}/javascript/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="${ctx}/javascript/easyui/jquery.easyui.min.js"></script>
<link href="${ctx}/javascript/easyui/themes/default/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/javascript/easyui/themes/icon.css" rel="stylesheet"
	type="text/css" />
