<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 가져오기</title>
</head>
<body>
<!-- 주석 단축기: ctrl + shift + / -->
	<!-- 정적 include: 미리 컴파일해둠. 어쩌다 한 번 바꾸는 파일 -->
	<%@ include file="copyright.txt"%>
	<p/>
	<!-- 동적 include: 페이지를 요청할때마다 새로고침. 자주 수정하는 파일 -->
	<jsp:include page="update.txt"/>
	
	<!-- 자바변수등을 넣으려면 정적 include를 사용 -->
	<%@ include file="variables.jsp" %>
	<%=name %>
	<br>
	<!-- 실행시(runtime)에 어떤파일을 include 할지 결정할때는 동적 include를 사용 -->
	<!-- 실행시 id가 있을 경우 login.html, 없을경우 fail.html페이지를 보여줌-->
	<% String id = request.getParameter("id"); 
	if(id == null){ %>
	<!-- 정적include로 html파일 로드시 한글이 깨질 수 있음 (동적 include로 확인해볼것) -->
	<jsp:include page="fail.html"/>
	<%} else { %>
	<jsp:include page="login.html"/>
	<%} %>
</body>
</html>