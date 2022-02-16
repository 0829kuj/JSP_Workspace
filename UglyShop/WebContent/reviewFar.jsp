<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>review 리스트 페이지 농민ver</h2>
<div class="row mt-5">
  <div class="col-md-8 mx-auto">
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자id</th>
          <th>작성일자</th>
          <th>상품번호</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="review" items="${reviews}">
          <tr>
            <td><c:out value="${review.reviewID}" /></td>
            <td>
              <a href="<%= request.getContextPath() %>/reviewController?cmd=view&id=<c:out value='${review.reviewID}'/>"><c:out value="${review.reviewTitle}" /></a>
            </td>
            <td><c:out value="${review.userID}" /></td>
            <td><c:out value="${review.reviewDate}" /></td>
            <td><c:out value="${review.prodID}" /></td>
          </tr>
        </c:forEach>
        <tr></tr>
      </tbody>
    </table>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
<jsp:include page="/includes/footerFar.jsp" />
