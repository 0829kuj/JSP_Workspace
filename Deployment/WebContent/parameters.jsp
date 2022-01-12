<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	String user = request.getParameter("user");
	String id = request.getParameter("id");
	// jsp에는 이미 out객체가 생성되어있어 바로사용가능
	out.println("유저 파라메터: " + user);
	out.println("<br>아이디 파라메터: " + id);
	%>
	<br>
	<% if (id == null) { %>
	<p>ID parameter 없음</p>
	<% } else { %>
	
	<% for (int i = 0; i < 5; i++){ %>
	<p>이 태그는 <% out.print(i); %>번 반복됩니다.</p>
	<% } %>
	<p>ID parameter <% out.print(id); %></p>
	<% } %>
	
	
	
</body>
</html>