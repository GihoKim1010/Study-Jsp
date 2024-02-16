<%@ page import="java.util.*" %>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 중에서 core 태그 사용 가능. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% //String str1 = "고양이"; %>
<% String str1 = null; %>

<%
	if(str1!=null){
		out.println(str1);
	} else {
		out.println("야옹이");
	}
%>

<c:out value="<%=str1 %>" default="야옹이" /><br>
<!-- core태그를 이용하여 축약. str1을 출력하되, null일경우 default로 야옹이 출력-->
	
</body>
</html>