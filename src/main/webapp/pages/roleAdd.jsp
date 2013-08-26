<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<HEAD>
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<script type="text/javascript">
	$(document).ready(function(){
		$("#confirmBtn").bind('click',function(){
			if(""==$("#rolename").val()||null==$("#rolename").val()){
				alert("请完整填写信息！");
				return;
			}
			emsDwr.getRoleNameNum($("#rolename").val(),function(num){
				if(num>0){
					$("#rolenameinfo").html("角色名重复");
					$("#rolename").val("");
					return false;
				}else if($("#rolename").val().length>20){
						$("#rolenameinfo").html("20个字以内");
				}else{
					$("#rolenameinfo").html("");
					$("#addRoleForm").submit();
					return true;
				}
			});
		});
		$("#rolename").bind('blur',function(){
			if(null==$("#rolename").val()||""==$("#rolename").val()){
				$("#rolenameinfo").html("角色名不能为空");
				return;
			}
			emsDwr.getRoleNameNum($("#rolename").val(),function(num){
				if(num>0){
					$("#rolenameinfo").html("角色名重复");
					$("#rolename").val("");
					return false;
				}else if($("#rolename").val().length>20){
						$("#rolenameinfo").html("20个字以内");
				}else{
					$("#rolenameinfo").html("");
					return true;
				}
			});
		})
	});


</script>
</HEAD>

<BODY>
	<h2>添加角色信息：</h2>
	<form action="/role/addAction" id="addRoleForm" name="addRoleForm" method="post">
	<table>
		<tr>
			<td>角色名称：</td>
			<td><input type="text" name="rolename" id="rolename" value=""/></td>	
			<td><div id="rolenameinfo" class="red"></div></td>	
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="button" value="确定" id="confirmBtn">
				<input type="reset" value="重置" id="resetBtn">
			</td>
		</tr>
	</table>
	</form>
	<div class="notice"><span style="color: red;">*</span>角色名称在20个字以内。</div>
	<a href="/role" style="margin-left: 20px;">返回</a>
</BODY>
</HTML>