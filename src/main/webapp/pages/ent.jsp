<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript">
	function comit(){

		if(parseInt($("#sy").val())>parseInt($("#ey").val())||(parseInt($("#sy").val())==parseInt($("#ey").val())&&parseInt($("#sm").val())>parseInt($("#em").val()))){
			alert("结束时间不能早于起始时间！");
			return;
		}
		$("#queryForm").submit();
	}

	$(document).ready(function(){
		$("#sy").val(new Date().getFullYear());
		$("#sm").val(new Date().getMonth()+1);
		$("#ey").val(new Date().getFullYear());
		$("#em").val(new Date().getMonth()+1);
		
		$("#sy").val(${sy});
		$("#sm").val(${sm});
		$("#ey").val(${ey});
		$("#em").val(${em});
		$("#entName").val("${entName}");
		if("${entName}"==""){$("#entName").val("-1");}
	});
</script>
</head>
<BODY>
<h2>开户统计</h2>
<form action="/ent/query" id="queryForm" method="post">
<table>
	<tr>
		<td>开户时间段：</td>
		<td><select name="sy" id="sy">
			<option value="2005">2005</option>
			<option value="2006">2006</option>
			<option value="2007">2007</option>
			<option value="2008">2008</option>
			<option value="2009">2009</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
			<option value="2020">2020</option>
			<option value="2021">2021</option>
			<option value="2022">2022</option>
			<option value="2023">2023</option>
			<option value="2024">2024</option>
			<option value="2025">2025</option>
			<option value="2026">2026</option>
			<option value="2027">2027</option>
		</select> 年 <select name="sm" id="sm">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> 月 &nbsp;&nbsp;——&nbsp;&nbsp; <select name="ey" id="ey">
			<option value="2005">2005</option>
			<option value="2006">2006</option>
			<option value="2007">2007</option>
			<option value="2008">2008</option>
			<option value="2009">2009</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
			<option value="2013">2013</option>
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
			<option value="2018">2018</option>
			<option value="2019">2019</option>
			<option value="2020">2020</option>
			<option value="2021">2021</option>
			<option value="2022">2022</option>
			<option value="2023">2023</option>
			<option value="2024">2024</option>
			<option value="2025">2025</option>
			<option value="2026">2026</option>
			<option value="2027">2027</option>
		</select> 年 <select name="em" id="em">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> 月</td>
	</tr>
	<tr>
		<td>企业名称：</td>
		<td>
			<!-- <input type="text" name="entName" id="entName"></input> -->
			<select id="entName" name="entName">
				<option value="-1">---请选择---</option>
				<c:forEach items="${entOptions}" var="option" varStatus="i" step="1">
					<option value="${option.value}">${option.text}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="button" value="查询" onclick="comit()">
		</td>
	</tr>
</table>
</form>

<table border="1" cellspacing="1" cellpadding="0" width="900px">
	<tr class="table_title">
		<th>企业名称</th>
		<th>发送域名</th>
		<th>开户时间</th>
		<th>现有用户数</th>
		<th>管理员帐号</th>
		<th width="150px">操作</th>
	</tr>
	<c:forEach items="${pageBean.data}" var="ent" varStatus="i" step="1">
		<tr>
			<td>${ent.entName}</td>
			<td>${ent.domain}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd"  value="${ent.createTime}" /></td>
			<td>${ent.userNum}</td>
			<td>${ent.adminAccount}</td>
			<td align="center">
				<a href="/ent/modify?eid=${ent.id}">修改</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<jsp:include page="pager.jsp"></jsp:include>
		</td>
	</tr>
</table>
<form action="/ent/query" method="post" id="PageForm">
	<input type="hidden" value="${sy}" name="sy"/>
	<input type="hidden" value="${sm}" name="sm"/>
	<input type="hidden" value="${ey}" name="ey"/>
	<input type="hidden" value="${em}" name="em"/>
	<input type="hidden" value="${entName}" name="entName"/>
	<input type="hidden" value="${cp}" name="cp" id="pageNum"/>
</form>
</BODY>
</HTML>