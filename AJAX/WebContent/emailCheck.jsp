<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
  String email = request.getParameter("email");	// parameter로 받을땐 모두 문자열임
  if(email.contains("@") && email.contains(".")){	// 이메일에 @와 .이 있는지 검사
  	  // 이메일 형식이 맞으면
	  try{
		  String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		  Connection conn = DriverManager.getConnection(url, "root", "1234");
		  PreparedStatement pstmt = conn.prepareStatement("select * from members where email=?");
		  pstmt.setString(1, email);
		  
		  ResultSet rs = pstmt.executeQuery();	// sql실행 후 결과를 rs에 저장
		  
		  if(rs.next()){ // DB에 쿼리문을 보낸 결과가 있는 경우 true반환
			  out.print("이미 가입된 이메일입니다.");
		  }else{
			  out.print("사용가능한 이메일입니다.");
		  }
		  conn.close();
		  
	  } catch (Exception e){
		  e.printStackTrace();	// 예외발생시 예외문구출력
	  }
  }else{
	out.print("잘못된 이메일 형식입니다!");
  }
%>