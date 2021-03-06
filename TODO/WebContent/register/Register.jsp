<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <title>유저 등록 페이지</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <!-- nav bar 끝 -->
    <!-- 본문 시작 -->
    <div class="container">
      <div class="row mt-5">
        <div class="col-md-6 mx-auto">
          <h2>유저 등록</h2>
          <div class="alert alert-success center" role="alert">
            <p>${MESSAGE}</p>
          </div>
          <!-- input태그에 작성한 정보를 /register로 post방식으로 전송 -->
          <form action="<%=request.getContextPath()%>/register" method="post">
            <div class="form-group mt-2">
              <label for="firstName">성 : </label>
              <input type="text" class="form-control" name="firstName" required />
            </div>
            <div class="form-group mt-2">
              <label for="lastName">이름 : </label>
              <input type="text" class="form-control" name="lastName" required />
            </div>
            <div class="form-group mt-2">
              <label for="userName">id : </label>
              <input type="text" class="form-control" name="userName" required />
            </div>
            <div class="form-group mt-2">
              <label for="password">password : </label>
              <input type="text" class="form-control" name="password" required />
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-success">가입하기</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />

    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
