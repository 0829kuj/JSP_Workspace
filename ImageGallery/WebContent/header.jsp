<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>${param.title}</title>
</head>
<body>

<div class="headerWrapper">
	<div class="header">
		<!-- {}안의 코드는 현재 프로젝트의 경로를 의미함 -->
		<img src="${pageContext.request.contextPath}/images/logo.png"/>
		<span id="title"></span>
	</div>
</div>	
<div class="content">