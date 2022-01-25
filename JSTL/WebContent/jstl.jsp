<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL을 사용하기 위한 링크. 여기서 prefix는 접두사임 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 출력 태그: value에 넣은 값을 출력 -->
<c:out value="JSTL 안녕!"/>

<!-- JSTL로 bean객체 사용 -->
<!-- bean객체 생성 -->
<!-- ""안에 달러기호{값}을 사용하면 ""내부에 변수를 사용할 수 있음  객체명.메서드명 으로 호출-->
<jsp:useBean id="test" class="beans.TestBean" />
<p> getInfo메소드 : <c:out value="${test.info}" />

<!-- parameter 가져오기 param.속성이름 -->
<!-- parameter중 name속성의 값을 출력함(테스트는 url창에 ?name=값 으로 확인) -->
<p> 파라메타 : <c:out value="${param.name}" />
<br>

<!-- IF문 -->
<c:if test="${param.name == 'bob'}">
	헬로우 bob
</c:if>
<c:if test="${param.name != 'bob'}">
	bob 이 아니군요.
</c:if><br>

<!-- SWICH문 JSTL choose, when:케이스, otherwise: 디폴트 -->
<c:choose>
	<c:when test="${param.id == 1}">
		<b>아이디는 1이다.</b>
	</c:when>
	<c:when test="${param.id == 2}">
		<b>아이디는 2이다.</b>
	</c:when>
	<c:otherwise>
		<b>아이디는 1또는 2가 아니다.</b>
	</c:otherwise>
</c:choose>
<br>

<!-- 반복문 -->
<c:forEach var="i" begin="0" end="10" step="2">
	i의 값: <c:out value="${i}" /> <br>
</c:forEach>

</body>
</html>