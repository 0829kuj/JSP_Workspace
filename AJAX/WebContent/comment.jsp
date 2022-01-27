<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%
  String comment = request.getParameter("comment");
  String email = request.getParameter("email");	// parameter로 받을땐 모두 문자열임
  
  if(comment.trim().equals("") || email.trim().equals("")){	// 입력받은 댓글과 이메일이 공백인지 검사
	  out.print("<p>댓글와 이메일을 적어주세요.</p>");
  
  } else{
  	  // 이메일 형식이 맞으면
	  try{
		  String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		  Connection conn = DriverManager.getConnection(url, "root", "1234");
		  PreparedStatement pstmt = conn.prepareStatement("insert into comment (comment, email) values (?, ?)"); // 입력용
		  pstmt.setString(1, comment);
		  pstmt.setString(2, email);
		  int i = pstmt.executeUpdate();	// insert문으로 반환받은 결과(행의 갯수)가 1이면 정상

		  pstmt = conn.prepareStatement("SELECT * FROM comment ORDER BY id DESC");	// 전체검색용
		  ResultSet rs = pstmt.executeQuery();	// 전체검색 sql문을 실행 후 결과를 rs에 저장
		  
		  out.print("<hr/>");
		  out.print("<h2>Comments: </h2>");
		  while(rs.next()){ // DB에 쿼리문을 보낸 결과가 있는 경우 true반환. if문은 하나만 출력하게 되므로 while문을 사용해 있는 댓글을 모두 출력
			  out.print("<div class='box'>");
			  out.print("<p>" + rs.getString("comment") + "</p>");
			  out.print("<p><strong>글쓴이: " + rs.getString("email") + "</strong></p>");
			  out.print("</div>");
		  }
		  conn.close();
		  
	  } catch (Exception e){
		  e.printStackTrace();	// 예외발생시 예외문구출력
	  }
  }
%>