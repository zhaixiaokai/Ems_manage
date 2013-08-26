<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="include.jsp"%>
<html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	var   r   =   /^\+?[1-9][0-9]*$/;
	$("#sbmBtn").bind("click",function(){
		if($("#entSelect").val()==""){
			$("#entInfo").html("请选择企业！");
			return;
		}
		if(null==$("#pointNum").val()||""==$("#pointNum").val()){
			$("#pointInfo").html("充值点数必填！");
			return;
		}
		if($("#pointNum").val()>100000000){
			$("#pointInfo").html("充值点数不能超过100000000！");
			return;	
		}
		if($("#explain").val().length>100){
			$("#explainInfo").html("100字以内");
			return;
		}
		$("#addPointForm").submit();
	});
	$("#pointNum").bind("blur",function(){
		if(null==$("#pointNum").val()||""==$("#pointNum").val()){
			$("#pointInfo").html("充值点数必填！");
			return;
		}else if(!r.test($("#pointNum").val())){
			$("#pointInfo").html("充值点数必须为正整数！");
			$("#pointNum").val("");
			return;
		}else{
			$("#pointInfo").html("");
		}
	});
	$("#entSelect").change(function(){
		$("#entInfo").html("");
	});
});
</script>
</head>
<body>
<h2>企业充点</h2>
	<form action="/point/addAction" id="addPointForm" method="post">
	<table>
		<tr>
			<td>企业名称：</td>
			<td>
				<select id="entSelect" name="entId">
					<option value="">---请选择---</option>
					<c:forEach items="${entOptions}" var="option" varStatus="i" step="1">
						<option value="${option.value}">${option.text}</option>
					</c:forEach>
				</select>
			</td>
			<td><span style="color: red;">*</span></td>
			<td><span style="color: red;" id="entInfo"></span></td>
		</tr>
		<tr>
			<td>充值点数：</td>
			<td><input type="text" id="pointNum" placeholder="填写正整数" name="point"/></td>
			<td><span style="color: red;">*</span></td>
			<td><span style="color: red;" id="pointInfo"></span></td>
		</tr>
		<tr>
			<td>备注说明：</td>
			<td><textarea id="explain" cols="30" rows="3" name="explain"></textarea></td>
			<td></td>
			<td><span style="color: red;" id="explainInfo"></span></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="button" id="sbmBtn" value="确定">
				<input type="reset" id="rstBtn" value="重置">
				</td>
			<td></td>
			<td></td>
		</tr>
	</table>
	<div class="notice1">
		注：<br>
		<span style="color: red; margin-left: 20px;">*</span>充点范围0~100000000<br>
		<span style="color: red; margin-left: 20px;">*</span>备注信息长度不超过100个字
	</div>
	</form>
</body>
</html>