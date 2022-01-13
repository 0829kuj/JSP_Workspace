<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- form에 입력한 내용을 셀프검사해서 합격하면 Controller로 데이터를 보냄 -->

<!-- 아래 form에 입력한 내용으로 user1객체를 생성 -->
<jsp:useBean id="user1" class="beans.User" scope="session" />
<jsp:setProperty property="*" name="user1"/>
<%
	String action = request.getParameter("action");
	
	// action과 formsubmit이 같으면
	if(action != null && action.equals("formsubmit")){
		if(user1.validate()){
			// 검사를 합격하면 컨트롤러로 포워드
			request.getRequestDispatcher("/Controller").forward(request, response);
		}	
	}
%>

<h2><%=user1.getMessage() %></h2>

<form action="/Forms/selfValform.jsp" method="post" >

<!-- hidden타입은 눈에 보이지 않지만 submit시 같이 전송됨 -->
<input type="hidden" name="action" value="formsubmit">
<input type="text" name="email" placeholder="email" value="<%=user1.getEmail()%>"><br>
<input type="text" name="password" placeholder="password" value="<%=user1.getPassword()%>"><br>
<input type="submit" value="전송">

</form>

</body>
</html>