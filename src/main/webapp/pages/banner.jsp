<%@ page language="java" pageEncoding="UTF-8"%>

<HTML>
<script type="text/javascript">
	function logout(){
	}
</script>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<BODY>
	<h1>EMS管理后台系统</h1>
	<div style="float: right;">
		欢迎你：&nbsp;${USER.userName}
		|<a href="/logout" target="_top" onclick="logout()">退出登录</a>
	</div>
	
</BODY>
</HTML>