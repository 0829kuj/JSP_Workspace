<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="user4" class="beans.User" scope="page"/>
<jsp:setProperty property="*" name="user4"/>

<p>아이디: <%=user4.getEmail() %>
<p>비번: <%=user4.getPassword() %>

</body>
</html>