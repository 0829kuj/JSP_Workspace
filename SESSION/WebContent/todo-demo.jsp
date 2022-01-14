<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1) HTML form작성 -->
<form action="todo-demo.jsp">
	새 할일: <input type="text" name="theItem"/>
	<input type="submit" value="입력"/>
</form>
<!-- 1.5) 입력한 아이템을 테스트출력 => 완료 -->
<!-- request.getParameter("theItem")  --> 


<!-- 2) To do 리스트에 새 아이템 추가 -->
<%
	// 세션에 todo리스트가 있는지 확인
	List<String> items = (List<String>) session.getAttribute("todoList");

	// todo리스트가 없다면 items = null 이므로 새로 만들기
	if(items == null) {
		items = new ArrayList<String>();
		session.setAttribute("todoList", items);
	}
	// 입력한 할일(앞서입력받은 theItem)을 받아 리스트(todoList)에 추가
	String theItem = request.getParameter("theItem");
	
	// if문을 통해 중복x 공백입력x 처리
	
	// todo리스트가 만들어져있을경우(null이 아닐경우) && 입력받은 theItem가 공백이 아닐 때
	if((theItem != null) && (!theItem.trim().equals(""))){
		// todo리스트에 입력받은 theItem(공백기준으로 자른 값)의 중복된 값이 없을 때 
		if(!items.contains(theItem.trim())) {
			// items에 theItem을 추가
			items.add(theItem);
			// todo리스트를 세션에 저장
			session.setAttribute("todoList", items);
		}
	}
%>
<!-- 3) todo리스트 출력하기 -->
<hr>
<b>할 일 리스트:</b>
<!-- ol태그는 순서를 구분하는 목록태그 -->
<ol>
<%
	for(String s : items){
		out.println("<li>" + s + "</li>");
	}
%>
<!-- 세션에 리스트가 저장되므로 브라우저를 껐다켜도 값이 유지됨, 브라우저별로 세션이 따로 저장됨 -->
</ol>

</body>
</html>