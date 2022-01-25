<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
<style>
#message {
	position: relative;
	top: 100px;
	width: 300px;
	border: 1px solid gray;
	padding: 20px;
	background-color: #CCFFCC;
	text-align: center;
	font-weight: bold;
}
</style>
</head>
<body>
<div id="message">
	<p> 로그인 되었습니다. </p>
	<p> <%= request.getAttribute("email") %></p>
</div>
</body>
</html>