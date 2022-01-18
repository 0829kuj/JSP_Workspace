<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!-- JSTL로 import해줌 -->
<!-- header.jsp의 title 값을 여기 value에 넣어서 지정해줌 -->
<c:import url="header.jsp">	
	<c:param name="title" value="Home"></c:param>
</c:import>

<!-- dataSource의 값이 context.xml의 resource 와 같아야 함 -->
<sql:setDataSource var="ds" dataSource="jdbc/webshop" />
<!-- 전체이미지를 id순으로 검색 -->
<sql:query var="results" dataSource="${ds}" sql="select * from images order by id" />

<!-- results에는 쿼리 실행결과이고 이것을 results.rows로 받아 한줄씩 row로 반복 -->

<!-- 경로(contextPath)/pics/사진이름.jpg 를 동적(Dynamic)으로 생성 -->

<!-- 한 행(8개)씩 변수 image에 저장. 갯수를 바꾸고싶으면 tablewidth의 value값 8을 다른 숫자로 바꿈 -->
<!-- 변수 picName(사진이름.확장자)을 만들어 이미지의 경로지정에 활용 -->

<table class="images">
<c:set var="tablewidth" value="8"></c:set>
<c:forEach var="image" items="${results.rows}" varStatus="row">
	
	<c:if test="${row.index % tablewidth == 0}"><tr></c:if>
	<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>
	<td>
		<c:url value="/gallery?action=image&image=${image.id}" />
		<a href="<c:url value="/gallery?action=image&image=${image.id}" />">
			<img src="${pageContext.request.contextPath}/pics/${picName}">
		</a>
	</td>
	
	<c:if test="${row.index+1 % tablewidth == 0}"></tr></c:if>
</c:forEach>
<!-- index번호가 8의 배수이면 tr이 열리고 td가 반복된 후 7의 배수가 되면 td의 반복을 멈추고 tr이 닫힘 => 한 행의 이미지가 8개가 됨 -->
</table>


<!-- 동적 컨텐츠(내용)가 들어감 -->

<c:import url="footer.jsp"></c:import>