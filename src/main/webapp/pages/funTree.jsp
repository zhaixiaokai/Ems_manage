<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<HEAD>
	<link rel="stylesheet" href="/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/js/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript">
		var setting = {
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		$(document).ready(function(){
			var zNodes =
				<%= request.getAttribute("funTreeJson")%>;
				if(zNodes.length==0) alert("您尚未获得任何权限，请联系管理员！");
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</script>
</HEAD>

<BODY>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
</BODY>
</HTML>