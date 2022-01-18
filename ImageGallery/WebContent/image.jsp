<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="header.jsp">	
	<c:param name="title" value="One Image Page"></c:param>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/webshop" />

<!-- 여기서 param.image는 사진을 눌렀을 때 id가 넘어온 값임. 즉 ?에 id가 들어감 -->
<!-- 질문. 근데 이러면 results에는 id에 해당하는 하나의 값만 들어가는거 아닌가?? => 맞음. 하나의 값만 변수 results에 저장됨 -->
<sql:query var="results" dataSource="${ds}" sql="select * from images where id=?">
	<sql:param>${param.image}</sql:param>
</sql:query>
<!-- id로 값을 찾기때문에 한 개만 선택됨 -->

<!-- results는 위에서 클릭한 이미지의 id에 해당하는 하나의 값만 가졌지만 results가 원래 배열이기때문에 배열의 가장 첫번째 값인 하나만 변수 image에 저장됨 -->
<c:set var="image" scope="page" value="${results.rows[0]}"></c:set>
<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>

<!-- 사진이름을 출력하되 첫글자를 대문자로 -->
<!-- fn:toUpperCase(), fn:toLowerCase(): 각각 대문자, 소문자로 만들어주는 함수 -->
<!-- fn:substring() : 문자열을 잘라 하나씩 index를 매겨주는 함수 -->
<!-- fn:substring(image.stem, 0, 1): index 0번 글자부터 시작해 1번째(가장 첫번째 즐가)만 선택(image.stem은 이미지의 이름을 의미) -->
<!-- fn:substring(image.stem, 1, -1): index 1번 글자부터 시작해 -1번째(가장 마지막 글자)까지 선택(image.stem은 이미지의 이름을 의미) -->

<div class="container">
  <div class="heading">
    <h1>
    	<c:out value="${fn:toUpperCase(fn:substring(image.stem, 0, 1))}${fn:toLowerCase(fn:substring(image.stem, 1, -1))}" />
	</h1>
	<!-- DB에 저장된 선택한 id의 값 중 average_ranking필드의 값을 가져온다 -->
    <div class="rating">Rated: ${image.average_ranking} (점수)</div>
  </div>
  <div class="flex-box">
    <div class="picture">
      <div class="imageby">Image by <a href="#">${image.attribution_name}</a></div>
      <img src="${pageContext.request.contextPath}/pics/${picName}">
    </div>
    <div class="rating-radio">
    	<!-- 가지고 있는 값(action-rate, image.id, rating-1~5중 하나)을 /gallery로 post방식으로 보냄 -->
    	<form action='<c:url value="/gallery"/>' method="post">
    	<input type="hidden" name="action" value="rate"/>
    	<input type="hidden" name="image" value="${image.id}"/>
	    
	      <h3>점수를 선택하기</h3>
	      <div><input type="radio" name="rating" value="5" />5 - 최고! </div>
	      <div><input type="radio" name="rating" value="4" />4 - 좋은작품! </div>
	      <div><input type="radio" name="rating" value="3" />3 - 괜찮음 </div>
	      <div><input type="radio" name="rating" value="2" />2 - 그럭저럭 </div>
	      <div><input type="radio" name="rating" value="1" />1 - 지뢰작 </div>
	      <p><input type="submit" name="submit" value="OK"></p>
	    </form>
    </div>
  </div>
</div>

<c:import url="footer.jsp"></c:import>