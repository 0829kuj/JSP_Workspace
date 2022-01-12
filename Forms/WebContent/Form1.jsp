<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- action에 설정된 주소로 form의 입력창에 입력된 데이터와 함께 요청됨 -->
<form action="/Forms/Controller" method="get">
<!-- method를 post로 지정 시 ?속성=값 부분이 가려진 주소만 url창에 표시된다 -->
	<input type="text" name="user">
	<input type="submit" value="전송">

</form>
</body>
</html>