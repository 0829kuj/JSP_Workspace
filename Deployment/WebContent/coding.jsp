<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(int i = 1; i <6; i++) {%>
	<p>이 태그는 <%= i %>번 반복됩니다.</p>
	<%} %>
	
	<%
	String id = request.getParameter("id");
	if (id == null) {
	%>
	<p>ID parameter: 없음</p>
	<%} else { %>
	<p>ID parameter: <%= id %></p>
	<%} %>
</body>
</html>