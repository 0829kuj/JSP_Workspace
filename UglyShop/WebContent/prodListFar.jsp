<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>상품 리스트 페이지 농민ver</h2>

<!-- Section-->
<section class="py-5">
  <div class="container px-4 px-lg-5 mt-5">
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
      <c:forEach var="products" items="${products}">
        <div class="col mb-5">
          <div class="card h-100">
            <!-- Product image-->
            <img class="card-img-top" src="${products.prodImg}" width="450" alt="..." />
            <!-- Product details-->
            <div class="card-body p-4">
              <div class="text-center">
                <!-- Product name-->
                <h5 class="fw-bolder"><c:out value="${products.prodName}" /></h5>
                <p>판매자: <c:out value="${products.farmID}" /></p>
                <!-- Product price-->
                <p><c:out value="${products.prodPrice}" />원</p>
              </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
              <div class="text-center"><a class="btn btn-secondary mt-auto" href="<%= request.getContextPath() %>/ProdController?cmd=find&id=${products.prodID}">자세히 보기</a></div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</section>

<style>
  .container {
    background-color: darkseagreen;
  }
  .container2 {
    margin-top: 40px;
  }
  /* .card {
    margin-right: 30px;
    margin-bottom: 30px;
  } */
</style>
<jsp:include page="/includes/footerFar.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
<script>
  $('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
  $('#m-proListFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>
