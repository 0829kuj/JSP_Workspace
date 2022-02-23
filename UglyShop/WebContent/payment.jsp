<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <title>못난이농산풀 판매사이트</title>
    <style>
      /* .col-md-8 {
        display: flex;
      } */
      /* .pay {
        display: inline-block;
        justify-content: flex-end;
      } */
	  
  /* tbody, td, tfoot, th, thead, tr {
    border-style: none !important;
  } */
	  .bank{
		  width: 80px;
		  height: 40px;
	  }
	  .cardNum{
		  width: 350px;
		  margin: 10px 30px;
	  }
	  .cardPass{
		  width: 100px;
		  margin: 10px 30px;
	  }
    </style>
  </head>
  <body>
    <!-- 로그인 한 경우에 세션에 저장된 유저아이디를 가지고 옴 -->
    <% String userID = null; if(session.getAttribute("userID") != null){ userID = (String)session.getAttribute("userID"); } %>

    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: gray">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/main.jsp">메인</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/proListFar.jsp">농산품</a></li>
          <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/reviewController">리뷰</a></li>
        </ul>
        <form class="d-flex mb-2 mb-auto">
          <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
          <button class="btn btn-secondary me-2" type="submit">Search</button>
        </form>
        <% if(userID == null){ %>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item"><a class="btn btn-primary me-2" href="login/login.jsp" role="button">로그인</a></li>
          <li class="nav-item"><a class="btn btn-success me-2" href="join/join.jsp" role="button">회원가입</a></li>
        </ul>
        <% }else{ %>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 마이 페이지</a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="<%=request.getContextPath()%>/logout.jsp">로그아웃</a></li>
              <li><a class="dropdown-item" href="#">장바구니</a></li>
              <li><a class="dropdown-item" href="#">주문조회</a></li>
              <li><a class="dropdown-item" href="#">고객정보수정</a></li>
            </ul>
          </li>
        </ul>
        <% } %>
      </div>
    </nav>
    <!-- 여기까지가 헤더. 모양만 있는 미완성 헤더이니 나중에 완성된걸로 바꿔주세요. -->
    <div class="row mt-5">
      <div class="col-md-8 mx-auto" style="background-color: bisque">
        <h4>주문하기</h4>
        <hr />
        <table class="table">
          <thead>
            <tr>
              <th scope="col">상품명</th>
              <th scope="col">판매자</th>
              <th scope="col">수량</th>
              <th scope="col">금액</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
            </tr>
            <tr>
              <th scope="row">3</th>
              <td colspan="2">Larry the Bird</td>
              <td>@twitter</td>
            </tr>
          </tbody>
        </table>
        <hr />
		<div class="pay">
			<!-- 은행선택 -->
			<select name="bank" id="bank">
				<option value="1">농협</option>
				<option value="1">우리</option>
				<option value="1">국민</option>
				<option value="1">신한</option>
			</select>
			<input class="cardNum" type="text" placeholder="카드번호를 입력해주세요" />
			<input class="cardPass" type="text" placeholder="카드비밀번호" />
			<!-- a랑 button중 뭐가 나을지 몰라서 다 만듦. 아래 둘 중 선택. -->
			<!-- <button class="btn btn-primary mt-auto">결제하기</button> -->
			<a class="btn btn-primary mt-auto" href="#">결제하기</a>
      	</div>
    </div>
    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
