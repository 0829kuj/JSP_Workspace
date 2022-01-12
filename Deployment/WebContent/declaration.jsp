<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
/* 메서드 만들기 */
private String getInfo(){
	return "디클레이션 태그 메서드 리턴";
}
%>
<!-- 메서드 호출 -->
<%= getInfo() %>
</body>
</html>