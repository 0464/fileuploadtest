<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class="container">
	<h1>boardList</h1>
	<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/index'">index</button>
	<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/addBoard'">addBoard</button>
	<table class="table table-bordered table-hover">
		<tr>
			<th>board_id</th>
			<th>board_title</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="b" items="${boardList}">
			<tr>
				<td>${b.boardId}</td>
				<td><a href="${pageContext.request.contextPath}/boardOne/${b.boardId}/1">${b.boardTitle}</a></td>
				<td>
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/modifyBoard/${b.boardId}'">수정</button>
				</td>
				<td>
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/removeBoard/${b.boardId}'">삭제</button>
				</td>
		</c:forEach>
	</table>
	<c:if test="${currentPage>1}">
		<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/boardList/${currentPage-1}'">이전</button>
	</c:if>
	<c:if test="${currentPage<lastPage}">
		<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/boardList/${currentPage+1}'">다음</button>
	</c:if>
</body>
</html>