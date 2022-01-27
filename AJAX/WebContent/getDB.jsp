<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
  String strId = request.getParameter("id");	// parameter로 받을땐 모두 문자열임
  if(strId == null || strId.trim().equals("")){	// strId가 null값이거나 공백일 경우
	  out.print("ID를 입력해주세요.");
  }else{
	  int id = Integer.parseInt(strId);	// 문자열인 strId를 int형으로 변환
	  
	  try{
		  String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		  Connection conn = DriverManager.getConnection(url, "root", "1234");
		  PreparedStatement pstmt = conn.prepareStatement("select * from emp where id=?");
		  pstmt.setInt(1, id);
		  
		  ResultSet rs = pstmt.executeQuery();	// sql실행 후 결과를 rs에 저장
		  
		  if(rs.next()){ // 결과가 있는 행은 true
			  out.print(rs.getInt("id") + ". " + rs.getString("name"));	// rs.getInt()의 ()안은 속성으로 값을 가져옴
		  }else{
			  out.print("테이블에 해당 id가 없습니다...");
		  }
		  conn.close();
		  
	  } catch (Exception e){
		  e.printStackTrace();	// 예외발생시 예외문구출력
	  }
  }
  
%>