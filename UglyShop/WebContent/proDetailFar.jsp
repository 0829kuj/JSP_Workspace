<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>상품 디테일 페이지 농민ver</h2>
<a href="<%= request.getContextPath() %>/reviewDetailFar.jsp">리뷰상세페이지</a>
<jsp:include page="/includes/footerFar.jsp" />
<script>
	$('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
	$('#m-proListFar').addClass('active');	// 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>