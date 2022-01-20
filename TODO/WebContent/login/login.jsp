<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <title>로그인 페이지</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <!-- nav bar 끝 -->
    <!-- 본문 시작 -->
    <div class="container">
      <h2>로그인</h2>
      <div class="col-md-6">
        <div class="alert alert-success center" role="alert">
          <p><%=session.getAttribute("message") %></p>
        </div>
        <!-- input태그에 작성한 정보를 /register로 post방식으로 전송 -->
        <form action="<%=request.getContextPath()%>/login" method="post">
          <div class="form-group">
            <label for="username">유저 이름 : </label>
            <input type="text" class="form-control" name="username"
            value="<%=session.getAttribute("user") %>" required />
          </div>
          <div class="form-group">
            <label for="password">비밀번호 : </label>
            <input type="text" class="form-control" name="password" required />
          </div>
          <div class="form-group mt-3">
            <button type="submit" class="btn btn-outline-danger">로그인</button>
          </div>
        </form>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />

    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
