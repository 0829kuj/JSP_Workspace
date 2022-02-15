<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/includes/headerFar.jsp" />
<h2>review detail 페이지 농민ver</h2>
<p>asdfwerdf가나다라마바</p>
<form id="findreply" action="<%= request.getContextPath() %>/reviewDetailFar.jsp">
  <label for="findID">replyID입력: </label>
  <input type="text" id="findID" name="findID" />
  <input type="submit" value="찾기" />
</form>
<form id="savareply" action="<%= request.getContextPath() %>/reviewDetailFar.jsp">
  <label for=""></label>
  <input type="text" id="" name="" />
  <input type="submit" value="저장하기" />
</form>
<form id="deletereply" action="<%= request.getContextPath() %>/reviewDetailFar.jsp">
  <label for="deleteID">replyID입력: </label>
  <input type="text" id="deleteID" name="deleteID" />
  <input type="submit" value="삭제하기" />
</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
<jsp:include page="/includes/footerFar.jsp" />
