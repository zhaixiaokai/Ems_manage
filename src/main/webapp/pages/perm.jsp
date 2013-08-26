<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<HEAD>
	<link rel="stylesheet" href="/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="/js/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/js/zTree/js/jquery.ztree.excheck-3.5.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
		
		$(document).ready(function(){
			var zNodes =	<%=request.getAttribute("funTreeJson")%>
			$.fn.zTree.init($("#permTree"), setting, zNodes);
			var permList	=	<%=request.getAttribute("permList")%>;
			var treeObj = $.fn.zTree.getZTreeObj("permTree");
			for(var i=0;i<permList.length;i++){
				treeObj.checkNode(treeObj.getNodeByParam("id",permList[i]),true);
			}
		});
		function save(){
			var treeObj = $.fn.zTree.getZTreeObj("permTree");
			var nodes	=	treeObj.getCheckedNodes(true);
			var permList=new Array();
			for(var i=0;i<nodes.length;i++){
				permList.push(nodes[i].id);
			}
			$("#permList").val(permList.toString());
			$("#permListForm").submit();
		}
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="permTree" class="ztree"></ul>
	</div>
</div>
<a href="/perm/user">返回</a>
<a href="javascript:void(0);" onclick="save()">保存</a>
<form action="/perm/${type}/save" id="permListForm">
	<input type="hidden" id="permList" value="" name="permList"/>
	<input type="hidden" id="entityId" value="${entityId}" name="entityId"/>
</form>
</BODY>
</HTML>