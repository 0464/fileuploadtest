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
<script>
	$(document).ready(function(){
		$('#addBtn').click(function(){
			let html = '<div><input type="file" name="boardfile" class="boardfile"></div>';
			$('#fileinput').append(html);
		});
		$('#delBtn').click(function(){
			$('#fileinput').children().last().remove();
		});	
		$('#submitBtn').click(function(){
			let ck = true;
			$('.boardfile').each(function(index,item){
				if($(item).val()==''){
					ck=false;
				}
			});
			if(ck==false){
				alert('선택하지 않은 파일이 있습니다.');
			}else{
				$('#fileUpdateForm').submit();
			}
		});
	});
</script>
</head>
<body class="container">
	<h1>modifyBoard</h1>
	<form id= "fileUpdateForm" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/modifyBoard">
		<table class="table table-bordered table-hover">
			<tr>
				<td>board_id :</td>
				<td>
					<input type="text" name="boardId" value="${boardOne.boardId}" readonly="readonly">
				</td>
			<tr>
				<td>board_title :</td>
				<td>
					<input type="text" name="boardTitle" value="${boardOne.boardTitle}">
				</td>
			</tr>
			<tr>
				<td>board_content :</td>
				<td>
					<input type="text" name="boardContent" value="${boardOne.boardContent}">
				</td>	
			</tr>
			<c:forEach var="bf" items="${boardOne.boardfile}">
			<c:if test="${bf.boardfileName != null}">
			<tr>
				<td>기존 파일</td>
				<td>
					<a href="${pageContext.request.contextPath}/upload/${bf.boardfileName}">${bf.boardfileName}</a>
					<button class="btn btn-outline-dark btn-sm" type="button" onclick="location.href='${pageContext.request.contextPath}/removeBoardfile/${bf.boardId}/${bf.boardfileId}'">파일삭제</button>
				</td>
			</tr>
			</c:if>
			</c:forEach>		
			<tr>
				<td>board_file_add</td>
				<td>
					<div id="fileinput">
						<button class="btn btn-outline-dark btn-sm" type="button" id="addBtn">파일추가</button>
						<button class="btn btn-outline-dark btn-sm" type="button" id="delBtn">파일삭제</button>
					</div>
				</td>
			</tr>
		</table>
		<button class="btn btn-outline-dark btn-sm" type="button" id="submitBtn">수정 완료</button>
		<button class="btn btn-outline-dark btn-sm" type="button" id="cencel" onclick="location.href='${pageContext.request.contextPath}/boardList/1'">취소</button>
	</form>
</body>
</html>