<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript">

	function addToRole(){
		if(confirm("确认将该用户添加到角色中？")){
			return true;
		}
		return false;
	}
	function rmFromRole(){
		if(confirm("确认从角色中删除该用户？")){
			return true;
		}
		return false;
	}
	$(document).ready(function(){
		$("#queryBtn").bind('click',function(){

			if($("#condition").val()==""){
				alert("请选择查询条件类型！");
				return ;
			}/*
			if($("#cond").val()==""){
				alert("请填写查询条件！");
				return;
			}*/
			$("#queryForm").submit();
		});
		$("#condition").val("${condType}");
		if("${condType}"==""){$("#condition").val("1");}
		$("#cond").val("${condition}");
		/*
		$("#condition").bind("change",function(){
			var condition	=	$("#condition").val();
			if(0==condition){
				$("#conditionInput").html("&nbsp;");
			}else if(1==condition){
				$("#conditionInput").empty();
				$("#conditionInput").append("<input id='cond' name='cond' type='text' value=''/>");
			}else if(2==condition){
				$("#conditionInput").empty();
				$("#conditionInput").append("<input id='cond' name='cond' type='text' value=''/>");
			}else if(3==condition){
				$("#conditionInput").empty();
				$("#conditionInput").append("<input id='cond' name='cond' type='radio' value='1'/>是");
				$("#conditionInput").append("<input id='cond' name='cond' type='radio' value='0' checked='checked'/>否");
				
			}
		});*/
	});
</script>
</head>
<BODY>
<h2>角色成员管理</h2>
<div style="width: 900px;">
<div align="right">
	<div style="width: 380px;float: right;">
	
	<form action="/roleuser/queryUser" id="queryForm" name="queryForm" method="post">
	<select id="condition" name="condition" style="width: 120px;float: left;">
	
		<!-- <option value="">----请选择----</option> -->
		<option value="1">---按用户名---</option>
		<option value="2">----按姓名----</option>
		<!-- <option value="3">-按是否管理员-</option> -->
	</select>
	<div id="conditionInput" style="width:120px;float: left;margin-left:10px;">
		<input id='cond' name='cond' type='text' value=''/>
	</div>
	<input type="hidden" value="${rid}" name="rid">
	<input type="button" value="查询" id="queryBtn"  style="width: 50px;float: right;"/>
	</form>
	</div>
</div>
<br>
<table border="1" cellspacing="1" cellpadding="0" width="900px">
	<tr class="table_title">
		<th>姓名</th>
		<th>用户名</th>
		<!-- <th>用户类型</th> -->
		<th>邮箱</th>
		<th width="150px">操作</th>
	</tr>
	<c:forEach items="${pageBean.data}" var="user" varStatus="i" step="1">
		<tr>
			<td>${user.name}</td>
			<td>${user.userName}</td>
			<!-- 
			<td><c:choose>
				<c:when test="${user.userType==1}">管理员</c:when>
				<c:when test="${user.userType==0}">普通用户</c:when>
				<c:otherwise>其他类型用户</c:otherwise>
			</c:choose></td>
			 -->
			<td>${user.email}</td>
			<td align="center">
				<c:choose>
					<c:when test="${user.inRoleFlag==0}">
						<a onclick="return addToRole()"  href="/roleuser/add?uid=${user.id}&cp=${pageBean.currentPage}&rid=${rid}&condition=${condType}&cond=${condition}" >添加</a>
					</c:when>
					<c:when test="${user.inRoleFlag>0}">
						<a onclick="return rmFromRole()"  href="/roleuser/rm?uid=${user.id}&cp=${pageBean.currentPage}&rid=${rid}&condition=${condType}&cond=${condition}" >删除</a>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<jsp:include page="pager.jsp"></jsp:include>
		</td>
	</tr>
</table>
</div>
<form action="/roleuser/queryUser" method="post" id="PageForm">
	<input type="hidden" value="${condType}" name="condition"/>
	<input type="hidden" value="${condition}" name="cond"/>
	<input type="hidden" value="${rid}" name="rid"/>
	<input type="hidden" value="${pageBean.currentPage}" name="currentPage" id="pageNum"/>
</form>
</BODY>
</HTML>