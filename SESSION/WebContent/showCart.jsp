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
<!-- 0) Session에서 cart에 값을 넣어줌 -->
<!-- 1) showCart.jsp에서 session서블릿의 Cart의 객체를 만듦 => 여기서 자꾸 오류생김 -->
<!--  => Session서블릿에서 값을 받아오는거라 showCart.jsp를 실행시키면 안됨. Session을 실행해서 출력은 showCart가 하는 구조임 -->

<% 
	// 세션의 setAttribute를 cart이름으로 Cart클래스로 변환해 받아옴..?
	Cart cart = (Cart)session.getAttribute("cart");	// session서블릿에 저장된 cart를 가져옴 
%>

<!-- 가져온 cart에 setTotalItems해둔 값을 출력 -->
<p>장바구니 아이템 갯수: <%= cart.getTotalItems() %></p>
</body>
</html>