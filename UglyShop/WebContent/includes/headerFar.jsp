<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" />
    <title>못난이농산풀 판매사이트</title>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a id="m-home" class="nav-link" href="<%= request.getContextPath() %>/homeFar.jsp"><span class="navbar-brand">타이틀</span></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div class="navbar-nav">
            <a id="m-proListFar" class="nav-link" href="<%= request.getContextPath() %>/proListFar.jsp">농산물</a>
            <a id="m-reviewFar" class="nav-link" href="<%= request.getContextPath() %>/reviewFar.jsp">리뷰</a>
          </div>
        </div>
      </nav>
    </header>
