<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP page</title>
</head>
<body>
	<h1>JSP 페이지</h1>
	<%-- 여러줄 자바스크립틀릿 --%>
	<% 
	String text = "오늘의 날짜는: ";
	out.println(text);	// out.println으로 출력
	%>
	
	<!-- 자바 한줄 Express태그: 화면에 바로출력 -->
	<%=new java.util.Date() %>
</body>
</html>			