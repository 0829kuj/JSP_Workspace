<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
    <title>농민용 못난이농산풀 판매사이트</title>
  </head>
  <body>
    <header>
      <!--  로그인 한 경우에 세션에 저장된 farmID를 가지고 옴 -->
      <% String farmID = null; if(session.getAttribute("farmID") != null){ farmID = (String)session.getAttribute("farmID"); } %>

      <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: gray">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/homeFar.jsp">Main</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/ProdController">농산품</a></li>
            <li class="nav-item"><a class="nav-link" href="<%= request.getContextPath() %>/reviewController">리뷰</a></li>
          </ul>
          <form class="d-flex mb-2 mb-auto">
            <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
            <button class="btn btn-secondary me-2" type="submit">Search</button>
          </form>
          <% if(farmID == null){ %>
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
                <li><a class="dropdown-item" href="#">주문조회</a></li>
                <li><a class="dropdown-item" href="#">고객정보수정</a></li>
              </ul>
            </li>
          </ul>
          <% } %>
        </div>
      </nav>
    </header>
