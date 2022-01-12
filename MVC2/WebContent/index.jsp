<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>홈페이지</p>
<!-- 누른 a태그마다 action값을 다르게 넣어줌 -> 서블릿페이지에서 if조건문으로 action값에 따라 처리 -->
<!-- request.getContextPath() : (서버의 주소는 생략) 요청한 위치의 프로젝트폴더 경로를 구해줌 -->
<%= request.getContextPath() %>
<p><a href="<%=request.getContextPath() %>/Controller?action=login">Login 페이지로</a></p>
<p><a href="<%=request.getContextPath() %>/Controller?action=about">About 페이지로</a></p>
</body>
</html>