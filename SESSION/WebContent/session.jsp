<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>세션의 기본유지시간</p>
	<!-- 클라이언트의 세션id가 유지되는 기본설정시간이 30분 -->
	<p><%= session.getMaxInactiveInterval() %></p>
</body>
</html>