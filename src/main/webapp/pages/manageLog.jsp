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
	function yearList(o){
		var now	=	new	Date();
		var year	=	now.getUTCFullYear();
		for(i=-20;i<20;i++){
			o.append("<option value=\""+(year+i)+"\">"+(year+i)+"</option>");
		}
	}
	function monthList(o){
		for(var i=1;i<=12;i++){
			o.append("<option value=\""+i+"\">"+i+"</option>");
		}
	}
	function dayList(o,y,m){
		o.empty();
		var	totalDay=0;
		if(m.val()==1||m.val()==3||m.val()==5||m.val()==7||m.val()==8||m.val()==10||m.val()==12){
			totalDay=31;
		}else if(m.val()==4||m.val()==6||m.val()==9||m.val()==11){
			totalDay=30;
		}else{
			if(0 == y.val()%4 && (y.val()%100 != 0 || y.val() % 4 == 0)){
				totalDay=29;
			}else{
				totalDay=28;
			}
		} 
		for(var i=1;i<=totalDay;i++){
			o.append("<option value=\""+i+"\">"+i+"</option>");
		}
	}
	function yChange(o,y,m){
		dayList(o,y,m);
	}
	function mChange(o,y,m){
		dayList(o,y,m);
	}
	function init(){
		$("#sy").val(new Date().getFullYear());
		$("#sm").val(new Date().getMonth()+1);
		$("#ey").val(new Date().getFullYear());
		$("#em").val(new Date().getMonth()+1);
		$("#ed").val(new Date().getUTCDate());
		$("#sy").val(${sy});
		$("#sm").val(${sm});
		$("#sd").val(${sd});
		$("#ey").val(${ey});
		$("#em").val(${em});
		$("#ed").val(${ed});
	}
	$(document).ready(function(){
		
		$("#entName").val("${entName}");
		$("#opUser").val("${opUser}");
		$("#opType").val("${opType}");
		yearList($("#sy"));
		monthList($("#sm"));
		yearList($("#ey"));
		monthList($("#em"));
		//初始化
		dayList($("#sd"),$("#sy"),$("#sm"));
		dayList($("#ed"),$("#ey"),$("#em"));
		//绑定事件
		$("#sy").change(function(){
			dayList($("#sd"),$("#sy"),$("#sm"));
		});
		$("#sm").change(function(){
			dayList($("#sd"),$("#sy"),$("#sm"));
		});
		$("#ey").change(function(){
			dayList($("#ed"),$("#ey"),$("#em"));
		});
		$("#em").change(function(){
			dayList($("#ed"),$("#ey"),$("#em"));
		});


		init();
		if("${entName}"==""){$("#entName").val("-1");}

		$("#resetBtn").bind("click",function(){
			$("#sy").val(new Date().getFullYear());
			$("#sm").val(new Date().getMonth()+1);
			$("#sd").val("1");
			$("#ey").val(new Date().getFullYear());
			$("#em").val(new Date().getMonth()+1);
			$("#ed").val(new Date().getUTCDate());	

			$("#entName").val("-1");
			$("#opUser").val("");
			$("#opType").val("");
		});
	});
</script>
</head>
<BODY>
<h2>管理日志查询</h2>
<form action="/mlog/query" id="queryForm" method="post">
<table>
	<tr>
		<td>日志时间段：</td>
		<td>
			<select name="sy" id="sy">

			</select> 年 
			<select name="sm" id="sm">
			</select> 月 
			<select name="sd" id="sd">
			</select> 日 
			&nbsp;&nbsp;——&nbsp;&nbsp; 
			<select name="ey" id="ey">
			</select> 年 
			<select name="em" id="em">
			</select> 月
			<select name="ed" id="ed">
			</select> 日 
		</td>
	</tr>
	<tr>
		<td>企业名称：</td>
		<td>
			<!-- <input type="text" name="entName" id="entName"> -->
			<select id="entName" name="entName">
				<option value="-1">---请选择---</option>
				<c:forEach items="${entOptions}" var="option" varStatus="i" step="1">
					<option value="${option.value}">${option.text}</option>
				</c:forEach>
			</select>
			
		</td>
	</tr>
	<tr>
		<td>操作用户：</td>
		<td><input type="text" name="opUser" id="opUser"></input></td>
	</tr>
	<tr>
		<td>操作类型：</td>
		<td>
			<select name="opType" id="opType">
				<option value="">--请选择--</option>
				<option>登陆</option>
				<option>添加用户</option>
				<option>删除用户</option>
				<option>设为管理员</option>
				<option>修改密码</option>
				<option>修改用户</option>
				<option>部门转移</option>
				<option>添加部门</option>
				<option>增删部门点数</option>
				<option>修改部门名称</option>
				<option>删除部门</option>
				<option>上传地址列表</option>
				<option>追加地址列表</option>
				<option>删除地址列表</option>
				<option>测试发送</option>
				<option>正式发送</option>
				<option>删除定时邮件</option>
				<option>添加企业</option>
				<option>增加点数</option>
				
			</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="button" value="查询" onclick="comit()">
			<input type="button" value="重置" id="resetBtn">
		</td>
	</tr>
</table>
</form>
<table border="1" cellspacing="1" cellpadding="0" width="900px">
	<tr class="table_title">
		<th>操作用户</th>
		<th>企业名称</th>
		<th>操作类型</th>
		<th>操作对象</th>
		<th>日志时间</th>
		<th>IP地址</th>
		<th>操作结果</th>
	</tr>
	<c:forEach items="${pageBean.data}" var="log" varStatus="i" step="1">
		<tr>
			<td>${log.userName}</td>
			<td>${log.entName}</td>
			<td>${log.operate}</td>
			<td>${log.opObject}</td>
			<td>${log.time}</td>
			<td>${log.ip}</td>
			<td>
				<c:choose>
					<c:when test="${log.result==1}">成功</c:when>
					<c:when test="${log.result==0}">失败</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
			<jsp:include page="pager.jsp"></jsp:include>
		</td>
	</tr>
</table>
<form action="/mlog/query" method="post" id="PageForm">
	<input type="hidden" value="${sy}" name="sy"/>
	<input type="hidden" value="${sm}" name="sm"/>
	<input type="hidden" value="${sd}" name="sd"/>
	<input type="hidden" value="${ey}" name="ey"/>
	<input type="hidden" value="${em}" name="em"/>
	<input type="hidden" value="${ed}" name="ed"/>
	<input type="hidden" value="${opUser}" name="opUser"/>
	<input type="hidden" value="${entName}" name="entName"/>
	<input type="hidden" value="${opType}" name="opType"/>
	<input type="hidden" value="${cp}" name="cp" id="pageNum"/>
</form>
</BODY>
</HTML>