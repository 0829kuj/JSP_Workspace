<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
</head>
<body>
<p>
	<!-- parameter로 action에 login값을 가지고 감 -->
	<!-- 여기서 request.getContextPath()는 /DATABASE 를 의미함. 만약 프로젝트폴더의 이름이 바뀌도러도 일일히 수정하지 않아도 되도록 한것. -->
	<!-- 질문. 여기서 get post중 왜 get방식으로 넘어가지? -->
	<a href="<%= request.getContextPath() %>/Controller?action=login">
		로그인
	</a>
</p>
<p>
	<a href="<%= request.getContextPath() %>/Controller?action=createaccount">
		가입하기
	</a>
</p>
</body>
</html>