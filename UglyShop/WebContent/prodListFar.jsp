<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/headerFar.jsp" />
<style>
  .container {
    background-color: darkseagreen;
  }
  .container2 {
    margin-top: 40px;
  }
  .card-img-top {
    width: 150;
    /* object-fit: cover; */
  }
  /* .card2 {
    width: 300px;
  } */
  /* .ProdImg {
    width: 300;
  } */
  .head{
    display: none;
    }
  .box{
    display: flex;
    justify-content: center;
    align-items: center;
  }
  td{
    display: block;
    text-align: center;
  }
  tbody, td, tfoot, th, thead, tr {
    border-style: none !important;
  }
  tbody{
    padding: 30px 0;
  }
</style>
<h2>상품 리스트 페이지 농민ver</h2>

<div class="container px-4 px-lg-5 mt-5" style="background-color: rgb(255, 244, 244)">
<table class="table">
  <thead class="head">
    <tr>
      <th>상품사진</th>
      <th>상품명</th>
      <th>판매자</th>
      <th>가격</th>
      <th>자세히보기</th>
    </tr>
  </thead>
  <tbody class="box">
    <c:forEach var="products" items="${products}">
      <tr>
        <div style="background-color: khaki;">
        <td>
          <img class="ProdImg" src='<%= request.getContextPath() %>/assets/img/bird.jpg' alt="상품사진">
        </td>
        <td><h5><c:out value="${products.prodName}" /></h5></td>
        <td>판매자: <c:out value="${products.farmID}" /></td>
        <td><c:out value="${products.prodPrice}" />원</td>
        <td><a class="btn btn-secondary mt-auto" href="<%= request.getContextPath() %>/ProdController?cmd=find&id=${products.prodID}">자세히 보기</a></td>
      </div>
      </tr>
    </c:forEach>
  </tbody>
</table>
</div>
<hr>

<!-- Section-->
<section class="py-5">
  <div class="container px-4 px-lg-5 mt-5">
    <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
      <c:forEach var="products" items="${products}">
        <div class="col mb-5">
          <div class="card h-100">
            <!-- Product image-->
            <img class="card-img-top" src="${products.prodImg}" alt="상품사진" />
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
<jsp:include page="/includes/footerFar.jsp" />
<script src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js"></script>
<script src="<%=request.getContextPath()%>/js/prodList.js"></script>
<script>
  $('.nav-link').removeClass('active'); // 모든 메뉴의 액티브클래스를 삭제
  $('#m-proListFar').addClass('active'); // 네브바에서 메뉴중 m-home에 active 클래스를 주는 스크립트
</script>
