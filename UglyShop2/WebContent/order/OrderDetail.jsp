<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/headerUser3.jsp" />
<body>
	<div class="row mt-5">
		<div class="col-md-8 mx-auto">
			<hr />
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">장바구니번호</th>
						<th scope="col">상품명</th>
						<!-- <th scope="col">판매자</th> -->
						<th scope="col">수량</th>
						<th scope="col">금액</th>
						<th scope="col">배송상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${order}">
						<%-- <c:set var="prodPrice" value="${order.cartID}" />
						<c:set var="prodPrice" value="${order.prodName}" />
						<c:set var="prodPrice" value="${order.prodPrice}" />
						<c:set var="orderQuantity" value="${order.orderQuantity}" />
						<c:set var="orderQuantity" value="${order.is_status}" /> --%>
						<tr>
							<td><c:out value="${order.cartID}" /></td>
							<td><c:out value="${order.prodName}" /></td>
							<td><c:out value="${order.orderQuantity}" /></td>
							<td><c:out value="${order.prodPrice}" /></td>
							<td><c:out value="${order.is_status}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div>
		<c:forEach var="order" items="${order}"> 	
	<div style="background-color: coral">
  		<p>상품명: <c:out value="${order.reviewID}" /></p>
 		<p>가격: <c:out value="${order.prodPrice}" /></p>
 		<p>주문수량: <c:out value="${order.orderQuantity}" /></p>
 		<p>배송상황: <c:out value="${order.is_status}" /></p>
 	</div>
 	<br><br>
 	</c:forEach>
	</div>
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>