<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- getset을 직접 작성할 필요 없이 변수명.속성명 으로 값을 가져올 수 있다 -->
	<p><c:out value="${user1.name}"></c:out></p>
	<p><c:out value="${user2.name}"></c:out></p>
	<p><c:out value="${user3.name}"></c:out></p>
	<br><br>
	<!-- map list => key값만 지정해주면 거기맞는 value를 출력해줌-->
	<p><c:out value="${mapList.fruit1}"></c:out></p>
	<p><c:out value="${mapList.fruit2}"></c:out></p>
	<p><c:out value="${mapList['fruit1']}"></c:out></p>
	<p><c:out value="${mapList['fruit2']}"></c:out></p>
	<br><br>
	<!-- array list (자주 쓰임. 이 개념이 제일 중요함.) -->
	<!-- item을 하나씩 저장하는 변수가 var, item이 하나의 유저리스트  -->
	<c:forEach var="item" items="${list}">
		${item.id} : ${item.name} <br>	<!-- 현재 선택된 item의 아이디:이름 을 출력 --> 
	</c:forEach>
	<br><br>
	<!-- 테이블 안에 입력 -->
	<table border="1">
		<tr>
			<th>ID</th><th>이름</th>
		</tr>
		<c:forEach var="item" items="${list}">
		<tr>
			<td>${item.id}</td><td>${item.name}</td>
		</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>