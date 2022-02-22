<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/includes/header.jsp" />

<h2>review detail 페이지 농민ver</h2>
<br /><br />

<div style="background-color: gray">
  <p>리뷰글번호: <c:out value="${review.reviewID}" /></p>
  <p>제목: <c:out value="${review.reviewTitle}" /></p>
  <p><c:out value="${review.reviewContent}" /></p>
  <p>작성자: <c:out value="${review.userID}" /></p>
  <p>작성일자: <c:out value="${review.reviewDate}" /></p>
  <p>상품번호: <c:out value="${review.prodID}" /></p>
</div>
<hr />
<div style="background-color: bisque">
  <p>덧글번호: <c:out value="${reply.replyID}" /></p>
  <p>판매자: <c:out value="${reply.farmID}" /></p>
  <p><c:out value="${reply.replyContent}" /></p>
  <p>리뷰글번호: <c:out value="${reply.reviewID}" /></p>
</div>
<hr />
<div style="background-color: dodgerblue">
  <p><%= request.getContextPath() %></p>

  <p>현재 접속한 농민의 아이디: <%=session.getAttribute("farmID") %></p>

  <c:if test='${session.getAttribute(farmID) eq "reply.farmID" }'><p>reply.farmID와 세션의 farmID가 동일할때 출력되는 메세지ㄴ낭런이러니ㅏ러런어ㅏㅏ아아아아아아ㅏㅇ13216546874632135798746312123</p></c:if>

  <form name="replyDel" action="<%= request.getContextPath() %>/replyController" method="post">
    <input type="hidden" name="cmd" value="delete" />
    <input type="hidden" name="replyID" value='<c:out value="${reply.replyID}" />' />
    <button type="submit" onclick="if(!confirm('정말로 삭제하시겠습니까?')) return false" class="btn btn-danger btn-action">삭제</button>
  </form>
  <hr />
  <!-- required: submit하기 전 무조건 빈칸을 채우도록하는 속성 -->
  <form name="replyEdit" action="<%= request.getContextPath() %>/replyController" method="get">
    <input type="hidden" name="cmd" value="edit" />
    <h4>덧글달기</h4>
    <textarea name="replyContent" id="commnet" cols="100" rows="10" required></textarea><br />

    <input type="hidden" class="form-control" name="reviewID" value='<c:out value="${review.reviewID}"/>' />
    <input type="hidden" class="form-control" name="farmID" value="<%=session.getAttribute("farmID") %>" />
    <button type="submit" id="replyEdit" class="btn btn-success btn-action">작성완료</button>
  </form>
</div>
<!-- <button type="button" class="btn btn-info btn-sm btn-edit" data-toggle="modal" data-target="#modal-edit">덧글작성</button> -->

<!-- <button type="button" class="btn btn-danger btn-sm btn-delete" data-toggle="modal" data-target="#modal-delete">덧글삭제</button> -->

<br /><br />

<!-- 덧글 삭제확인 모달창 -->
<!-- <div class="modal fade" id="modal-delete" tabindex="-1" aria-labelledby="deleteLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">덧글삭제확인</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>정말로 삭제할까요?</p>
      </div>
      <div class="modal-footer">
        <form id="frm-delete">
          <input type="hidden" name="cmd" value="delete" />
          <input type="hidden" name="replyID" value="<c:out value='${reply.replyID}' />" />
          <button type="submit" id="replyDelete" class="btn btn-danger btn-action">삭제</button>
        </form>
        <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>
-->

<jsp:include page="/includes/footer.jsp" />

<script>
	$('.nav-link').removeClass('active');
	$('#m-review').addClass('active');
</script>
