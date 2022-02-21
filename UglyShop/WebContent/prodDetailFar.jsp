<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>상품 디테일 페이지 농민ver</h2>
<div style="background-color: bisque">
  <p>상품번호: <c:out value="${product.prodID}" /></p>
  <p>판매자ID: <c:out value="${product.farmID}" /></p>
  <p>상품명: <c:out value="${product.prodName}" /></p>
  <p>가격: <c:out value="${product.prodPrice}" /></p>
  <p><img src="<c:out value='${product.prodImg}' />" alt="상품사진" /></p>
  <p>남은수량: <c:out value="${product.prodID}" /></p>
  <p>상품설명: <c:out value="${product.prodInfo}" /></p>
</div>
<a href="<%= request.getContextPath() %>/reviewController?cmd=find&id=<c:out value="${product.prodID}" />">리뷰상세페이지</a>
<jsp:include page="/includes/footerFar.jsp" />
<script>
  $('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
  $('#m-proListFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>
