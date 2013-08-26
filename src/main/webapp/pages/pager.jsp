<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<c:choose>
			<c:when test="${pageBean.totalPage>1&&pageBean.currentPage!=1}">
				<a href="#" onclick="firstPage()" id="firstPage">首页</a>&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				<a href="#" class="disable">首页</a>&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${1!=pageBean.currentPage&&0<pageBean.currentPage}">
				<a href="#" onclick="prePage()" id="nextPage">上一页</a>&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				<a href="#" class="disable">上一页</a>&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.totalPage!=pageBean.currentPage&&pageBean.totalPage>1}">
				<a href="#" onclick="nextPage()" id="nextPage">下一页</a>
			</c:when>
			<c:otherwise>
				<a href="#" class="disable">下一页</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${pageBean.totalPage>1&&pageBean.currentPage!=pageBean.totalPage}">
				&nbsp;&nbsp;<a href="#" onclick="lastPage(${pageBean.totalPage})" id="lastPage">末页</a>
			</c:when>
			<c:otherwise>
				<a href="#" class="disable">&nbsp;&nbsp;末页</a>
			</c:otherwise>
		</c:choose>
		&nbsp;&nbsp;共查找到&nbsp;<span style="color: red">${pageBean.totalRow}</span>&nbsp;条记录
		&nbsp;&nbsp;当前第&nbsp;${pageBean.currentPage}/${pageBean.totalPage}&nbsp;页