<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript"><!--
	function deleteRole(id){
		if(confirm("确认要删除该角色？")){
			return true;
		}
		return false;
	}
	function modifyRole(id){
		return true;
	}
	$(document).ready(function(){

		$("#queryBtn").bind('click',function(){
			/*
			if(null==$("#conditionInput").val()||""==$("#conditionInput").val()){
				alert("输入查询条件！");
				return;
			}*/
			$("#queryForm").submit();
		});
	});
--></script>
</head>
<BODY>
<h2>角色管理</h2>
<div style="width: 900px;">
<a href="/role/add" target="rightFrame">添加角色</a>
<div align="right">
	<div style="width: 380px;float: right;">
	
	<form action="/role/query" id="queryForm" name="queryForm" method="post">
	<div style="float:left;width:120px;">角色名称：</div>
	<input type="text" id="conditionInput" value="${condition}" name="condition" style="width:120px;float: left;margin-left:10px;"/>
	<input type="button" value="查询" id="queryBtn"  style="width: 50px;float: right;"/>
	</form>
	</div>
</div>
<table border="1" cellspacing="1" cellpadding="0" width="900px">
	<tr class="table_title">
		<!-- <th>角色ID</th> -->
		<th>角色名称</th>
		<th width="150px">操作</th>
	</tr>
	<c:forEach items="${pageBean.data}" var="role" varStatus="i" step="1">
		<tr>
			<!-- <td>${role.id}</td> -->
			<td>${role.roleName}</td>
			<td align="center">
				<a onclick="return deleteRole()"  href="/role/delAction?opid=${role.id}" >删除</a>|
				<a onclick="modifyRole()"  href="/role/modify?opid=${role.id}">修改</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="2">
			<jsp:include page="pager.jsp"></jsp:include>
		</td>
	</tr>
</table>
</div>

<form action="/role/query" method="post" id="PageForm">
	<input type="hidden" value="${condition}" name="condition"/>
	<input type="hidden" value="${pageBean.currentPage}" name="currentPage" id="pageNum"/>
</form>
</BODY>
</HTML>