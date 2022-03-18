<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "dao.UserDAO"%>   
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.css">
<jsp:include page="../includes/headerUser.jsp" />

<h2>리뷰 작성 페이지 고객ver</h2>		
<br><br><br>
<div class="container" style="width: 30rem; ">
	
	<div class="card" style="text-align: center; margin-top: 20px;">
		<div class="card-body">
			<h5 class="card-title">상품이름 [ ${param.prodName} ]</h5>
			<h6 class="card-subtitle mb-2 text-muted">농민ID [ ${param.farmID} ]</h6>
			
		</div>
	</div>
	<br><br>
		
	<div class="row" style="">
	<br><br>
	<form method="post" action="<%=request.getContextPath() %>/reviewController?cmd=save"> <!-- DB저장 -->
		<input type="hidden" name="userID" value="${userID}" />
		<input type="hidden" name="prodID" value ="${param.prodID}" />
		<!-- 적는 칸 -->
		<table class="table table-striped" style="text-align: center; border:1px solid #dddddd; width: 30rem;">
			<thead>
				<tr>
					<th colspan="2" style="background-color: #eeeeee; text-align: center;">리뷰 작성하기</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" class="form-control" placeholder="글 제목" name="title" maxlength="50" required></td>
				</tr>
				<tr>
					<td><textarea class="form-control" placeholder="글 내용" name="content" maxlength="2048" style="height:350px" required></textarea></td>
				</tr>
			</tbody>
		</table>
		
		<div  style="text-align:center; align-content: center; ">
			<button  onclick = "location.href = '<%=request.getContextPath()%>/order?cmd=ordersList' " type="button" class="btn btn-primary mb-3">취소</button> <!-- 리스트로 이동  -->
			<input type="submit" class="btn btn-primary mb-3" value="등록">		
		</div>
		
	</form>
</div>

</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>

</body>
</html>

