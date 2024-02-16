<%@ page import="com.giho.jsp.board.db.Dao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giho's Board</title>
</head>
<body>
	<fieldset>
		<legend>일반 게시판</legend>
		<a href="/board/list?category=free"> 자유게시판</a>
		<hr>
		<a href="/board/list?category=notice"> 공지게시판</a>
	</fieldset>
</body>
</html>