<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<HEAD>
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#confirmBtn").bind('click',function(){
			if(""==$("#username").val()||null==$("#username").val()||null==$("#name").val()||""==$("#name").val()){
				alert("请完整填写信息！");
				return;
			}
			$("#loginform").submit();
		});
		$("#username").bind('blur',function(){
			if(null==$("#username").val()||""==$("#username").val()){
				$("#usernameinfo").html("用户名不能为空");
				return;
			}
			emsDwr.getUserNameNumModify($("#username").val(),${user.id},function(num){
				if(num>0){
					$("#usernameinfo").html("用户名重复");
					$("#username").val("");
				}else if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test($("#username").val()))){
					$("#usernameinfo").html("只能为@sohu-inc.com的邮箱");
					$("#username").val("");
				}else if(-1==$("#username").val().indexOf("@sohu-inc.com")){
					$("#usernameinfo").html("只能为@sohu-inc.com的邮箱");
					$("#username").val("");
				}
				else{
					$("#usernameinfo").html("");
				}
			});
		})
		
		$("#name").bind('blur',function(){
			if(null==$("#name").val()||""==$("#name").val()){
				$("#nameinfo").html("姓名不能为空");
				return;
			}else if($("#name").val().length>10){
				$("#nameinfo").html("10个字以内");
				$("#name").val("");
			}
		})
		$("input:radio[value=${user.userType}"+"]").attr("checked",true);
	});
</script>
</HEAD>

<BODY>
<h2>修改用户信息：</h2>
<form action="/user/modifyAction?id=${user.id}" id="loginform" name="loginform"
	method="post">
<table>
	<tr>
		<td>用户名：</td>
		<td><input type="text" name="username" id="username"
			value="${user.userName}" /></td>
		<td>
		<div id="usernameinfo" class="red"></div>
		</td>
	</tr>
	<tr>
		<td>姓名：</td>
		<td><input type="text" name="name" id="name" value="${user.name}" /></td>
		<td>
		<div id="nameinfo" class="red"></div>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="button" value="确定" id="confirmBtn"> 
			<input type="reset" value="重置" id="resetBtn">
		</td>
	</tr>
	<!--
		<tr>
			<td>电子邮件：</td>
			<td><input type="text" name="email" id="email"/></td>		
		</tr>
		
		-->
	<tr style="display: none;">
		<td>用户类型：</td>
		<td><input type="radio" name="usertype" value="0">普通用户
		<input type="radio" name="usertype" value="1">管理员</td>
		<td>
		<div id="usertypeinfo"></div>
		</td>
	</tr>
</table>
</form>

	<div class="notice">
		注：<span style="color: red;">*</span>用户名只能为@sohu-inc.com的帐号
	</div>
	<a href="/user" style="margin-left: 20px;">返回</a>
</BODY>
</HTML>