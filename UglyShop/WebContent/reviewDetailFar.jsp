<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>review detail 페이지 농민ver</h2>
<p>asdfwerdf가나다라마바</p>
<br /><br />
<div style="background-color: gray">
  <p><c:out value="${review.reviewID}" /></p>
  <p><c:out value="${review.reviewTitle}" /></p>
  <p><c:out value="${review.reviewContent}" /></p>
  <p><c:out value="${review.userID}" /></p>
  <p><c:out value="${review.reviewDate}" /></p>
  <p><c:out value="${review.prodID}" /></p>
</div>
<hr />
<div style="background-color: bisque"></div>
<br /><br />

<hr />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
<jsp:include page="/includes/footerFar.jsp" />
