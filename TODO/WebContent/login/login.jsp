<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />

    <title>로그인 페이지</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <!-- nav bar 끝 -->
    <!-- 본문 시작 -->
    <!-- 부트스트랩 순서: 컨테이너 > 로우 > 컬럼 순 -->
    <div class="container">
      <div class="row mt-5">
        <!-- col-md-6 mx-auto: 가로를 12등분했을때 6지점부터 시작, mx-auto로 마진을 좌우 균등하게 분배하여 div를 중앙정렬함 -->
        <div class="col-md-6 mx-auto">
          <h2>로그인</h2>
          <div class="alert alert-success center" role="alert">
            <p>${message}</p>
          </div>
          <!-- input태그에 작성한 정보를 /register로 post방식으로 전송 -->
          <form action="<%=request.getContextPath()%>/login" method="post">
            <div class="form-group mt-2">
              <label for="username">유저 이름 : </label>
              <input type="text" class="form-control" name="username" value="${user}" required />
            </div>
            <div class="form-group mt-2">
              <label for="password">비밀번호 : </label>
              <input type="text" class="form-control" name="password" required />
            </div>
            <div class="form-group mt-3">
              <button type="submit" class="btn btn-outline-danger">로그인</button>
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
