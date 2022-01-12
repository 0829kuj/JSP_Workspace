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
	<jsp:useBean id="user2" class="beans.User" scope="page"/>

	<!-- set메서드로 입력하지 않고 parameter를 통해 값을 입력 -->	
	<jsp:setProperty property="*" name="user2"/>
	<!-- property에 따로 변수를 생성해주지않아도 url에 속성과 값을 입력해주면 미리 지정해준 것 처럼 생성됨 -->
	
	이메일: <%= user2.getEmail() %>
	비밀번호: <%= user2.getPassword() %>
	이름: <%= user2.getName() %>
	
</body>
</html>