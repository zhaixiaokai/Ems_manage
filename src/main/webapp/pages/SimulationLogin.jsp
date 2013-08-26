<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript">

	$(document).ready(function(){

		$("#loginBtn").bind("click",function(){
			$("#loginForm").submit();
		});

		if(${login}){
			var aa=window.open("${redirectURL}");
		}
	});

</script>
</head>
<BODY>
<h2>模拟登录</h2>

<form action="/simulogin/login" id="loginForm">
<table>
	<tr>
		<td>用户名：</td>
		<td><input type="text" id="username" name="username"></input></td>
		<td><input type="button" id="loginBtn" value="确认"></td>
	</tr>
	<tr><td colspan="3"><span><a target="_blank" href="${redirectURL}">${message}</a></span></td></tr>
</table>
</form>

</BODY>
</HTML>