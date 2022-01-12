<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- jsp에 자바 bean 객체 생성: id는 객체의 이름, scope는 범위 -->
	<!-- session: 리퀘스트있는동안은 지속됨. 웹 브라우즈별로 변수가 관리되는 경우 사용 -->
	<jsp:useBean id="user1" class="beans.User" scope="request"/>
	<!-- 자바 bean에 값을 입력 (set메서드) -->
	<jsp:setProperty property="email" name="user1" value="son@naver.com"/>
	<jsp:setProperty property="password" name="user1" value="1111"/>
	
	<!-- 페이지를 forward로 넘기기 때문에 url은 그대로 setbeanRequest.jsp로 나타남 -->
	<jsp:forward page="getbeanRequest.jsp"/>
</body>
</html>