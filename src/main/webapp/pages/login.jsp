<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include.jsp"%>
<HTML>
<head>
<script type="text/javascript" src="/js/sso-utils.js"></script>
<%
	session.setMaxInactiveInterval(3600);
%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#username").val("<%=request.getUserPrincipal().getName()%>");
		$("#loginForm").submit();
	});
</script>
</head>
<BODY>
	<form action="/login" method="post" id="loginForm">
		<input type="hidden" name="username" id="username">
	</form>
</BODY>
</HTML>