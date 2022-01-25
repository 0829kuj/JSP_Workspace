<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<title>Todo Form</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark"
			style="background-color: cornflowerblue">
			<div class="container-fluid">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/">TODO
					APP</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/todos?action=list">Todo
								리스트</a></li>
					</ul>
					<ul class="navbar-nav mb-2">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/todos/logout">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- nav bar 끝 -->
	<!-- 본문 시작 -->
	<div class="container">
		<div class="row mt-5">
			<div class="col-md-6 mx-auto">
				<div class="card card-body">
					<!-- 여기서부터 동적 페이지 -->
					<!-- 경우1. todo==null 이면 새로 입력, 경우2. todo!=null 이면 업데이트 -->
					<c:if test="${todo != null}">
						<form action="<%=request.getContextPath()%>/todos">
							<input type="hidden" name="action" value='update'>
					</c:if>
					<!-- 페이지를 처음 열었을 땐 todo에 값이 없으므로  -->
					<c:if test="${todo == null}">
						<form action="<%=request.getContextPath()%>/todos">
							<input type="hidden" name="action" value='post'>
					</c:if>
					<h2>
						<c:if test="${todo != null}">할일 수정</c:if>
						<c:if test="${todo == null}">할일 추가</c:if>
					</h2>
					<!-- 할일을 수정한 후 저장버튼을 눌렀을 때 넘어가는 id는 여기의 if문아래 input에서 가지고 있으며, 버튼이 눌러졌을 때 hidden인 채로 넘어간다 -->
					<c:if test="${todo != null}">
						<input type="hidden" class="form-control" name="id"
							value='<c:out value="${todo.id}"/>'>
					</c:if>

					<div class="form-group mb-2">
						<label>제목</label> <input type="text" class="form-control"
							name="title" value='<c:out value="${todo.title}"/>' required>
					</div>
					<div class="form-group mb-2">
						<label>할일 설명</label> <input type="text" class="form-control"
							name="description" value='<c:out value="${todo.description}"/>'>
					</div>
					<div class="form-group mb-2">
						<label>현재 상태</label> <select class="form-control" name="isDone">
							<option value="false">진행중</option>
							<option value="true">완료</option>
						</select>
					</div>
					<div class="form-group mb-4">
						<label>목표 일자</label> <input type="date" class="form-control"
							name="targetDate" value='<c:out value="${todo.targetDate}"/>'>
					</div>
					<div class="form-group d-grid">
						<button type="submit" class="btn btn-outline-info">저장</button>
					</div>
					</form>
				</div>
			</div>
			<!--.col-->
		</div>
	</div>
	<!-- 본문 끝 -->
	<jsp:include page="../common/footer.jsp" />

	<script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
</body>
</html>