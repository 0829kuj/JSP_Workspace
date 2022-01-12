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
	<jsp:useBean id="user" class="beans.User" scope="session"/>
	<!-- 자바 bean에 값을 입력 (set메서드) -->
	<jsp:setProperty property="email" name="user" value="0829kuj@naver.com"/>
	<jsp:setProperty property="password" name="user" value="letmein"/>
	
</body>
</html>