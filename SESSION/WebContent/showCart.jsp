<%@page import="demo.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1) showCart.jsp에서 session서블릿의 Cart의 객체를 만듦 => 여기서 자꾸 오류생김 -->
<!-- 2) 만든 cart객체를 이용해 TotalItems()의 값을 get으로 가져옴...인데 왜 안됨?? -->
<% 
	// 세션의 setAttribute를 cart이름으로 Cart클래스로 변환해 받아옴..?
	Cart cart = (Cart)session.getAttribute("cart");	// session서블릿에 저장된 cart를 가져옴 
%>
<!-- 가져온 cart에 setTotalItems해둔 값을 출력 -->
<p>장바구니 아이템 갯수: <%= cart.getTotalItems() %></p>
</body>
</html>