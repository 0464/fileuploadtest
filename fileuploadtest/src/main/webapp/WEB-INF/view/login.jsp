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
	<h1>login</h1>
	<form method="post" action="${pageContext.request.contextPath}/login">
		<div>user id : <input type="text" name="userId" value="${user.userId}"></div>
		<div>user pw : <input type="password" name="userPw"></div>
		<div><button class="btn btn-outline-dark btn-sm" type="submit">로그인</button></div>
	</form>
</body>
</html>