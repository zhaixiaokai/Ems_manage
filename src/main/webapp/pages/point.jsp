<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryBtn").bind("click",function(){
		$("#queryForm").submit();
	});
	$("#entSelect").val(${entId});
	$("#opUserId").val("${opUserId}");
	$("#resetBtn").bind("click",function(){
		$("#entSelect").val("-1");
		$("#opUserId").val("");
	});
});
</script>
</head>
<BODY>
<h2>充点查询</h2>
<form action="/point/query" id="queryForm" method="post">
<table>
	<tr>
		<td>企业名称：</td>
		<td>
			<select id="entSelect" name="entId">
				<option value="-1">---请选择---</option>
				<c:forEach items="${entOptions}" var="option" varStatus="i" step="1">
					<option value="${option.value}">${option.text}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>操作人：</td>
		<td><input type="text" name="opUserId" id="opUserId" value="${opUserId}"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="button" id="queryBtn" value="查询">
			<input type="button" id="resetBtn" value="重置">
		</td>
	</tr>
</table>
</form>
<br>
<table border="1" cellspacing="1" cellpadding="0" width="900px">
	<tr class="table_title">
		<th>企业名称</th>
		<th>操作人</th>
		<th>时间</th>
		<th>充值点数</th>
		<th>备注</th>
	</tr>
	<c:forEach items="${pageBean.data}" var="record" varStatus="i" step="1">
		<tr>
			<td>${record.ent_name}</td>
			<td>${record.user_id}</td>
			<td>${record.time}</td>
			<td>${record.points}</td>
			<td>${record.exp}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5">
			<jsp:include page="pager.jsp"></jsp:include>
		</td>
		
	</tr>
</table>
<form action="/point/query" method="post" id="PageForm">
	<input type="hidden" value="${entId}" name="entId"/>
	<input type="hidden" value="${opUserId}" name="opUserId"/>
	<input type="hidden" value="${pageBean.currentPage}" name="cp" id="pageNum"/>
</form>
</BODY>
</HTML>