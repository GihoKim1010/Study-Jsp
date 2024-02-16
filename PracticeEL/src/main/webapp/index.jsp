<%@ page import="com.giho.PracticeEL.Cat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CatInfo</title>
</head>
<body>
	<fieldset>
		<legend>내 고양이 정보</legend>
		<hr>
		고양이 이름은 : ${myCat.name}
		<hr>
		고양이 나이는 : ${myCat.age}
		<hr>
	</fieldset>
</body>
</html>