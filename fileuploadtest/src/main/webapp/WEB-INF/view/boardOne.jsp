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
	<h1>boardOne</h1>
		<table class="table table-bordered table-hover">
			<tr>
				<td>board_id</td>
				<td>
					<input type="text" name="boardId" value="${board.boardId}" readonly="readonly">
				</td>
			<tr>
				<td>board_title</td>
				<td>
					<input type="text" name="boardTitle" value="${board.boardTitle}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>board_content</td>
				<td>
					<input type="text" name="boardContent" value="${board.boardContent}" readonly="readonly">
				</td>	
			</tr>
		</table>
		<h3>첨부파일</h3>
		<table class="table table-bordered table-hover">
			<c:forEach var="bf" items="${boardfile.boardfile}">
			<c:if test="${bf.boardfileName != null}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/upload/${bf.boardfileName}">${bf.boardfileName}</a>
				</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
		<h3>댓글목록</h3>
		<table class="table table-bordered table-hover">
			<c:forEach var="c" items="${board.commentList}">
			<c:if test="${c.commentContent != null}">
			<tr>
				<td>${c.commentContent}</td>
				<td>
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/deleteComment/${c.commentId}/${c.boardId}'">삭제</button>
				</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
		<!-- 댓글 목록 페이징 -->
		<c:if test="${currentPage>1}">
		<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/boardOne/${board.boardId}/${currentPage-1}'">이전</button>
		</c:if>
		<c:if test="${currentPage<lastPage}">
		<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/boardOne/${board.boardId}/${currentPage+1}'">다음</button>
		</c:if>
		<!-- 댓글 작성 -->
		<h3>댓글작성</h3>
		<form method="post" action="${pageContext.request.contextPath}/addComment">
			<input type="hidden" name="boardId" value="${board.boardId}">
			<textarea name="commentContent" rows="3" cols="50"></textarea>
			<button class="btn btn-outline-dark btn-sm" type="submit">작성</button>
		</form>
		<button class="btn btn-outline-dark btn-sm" type="button" id="cencel" onclick="location.href='${pageContext.request.contextPath}/boardList/1'">목록</button>
</body>
</html>