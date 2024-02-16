<%@page import="com.giho.jsp.board.db.Dto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.giho.jsp.board.BoardList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	BoardList bl = (BoardList) request.getAttribute("bl");
	ArrayList<Dto> posts = bl.getPosts();
	%>

	<fieldset>
		<legend><%=bl.category%>
			게시판
		</legend>
		글번호, 제목, 작성자
		<hr>
		<%
		for (int i = 0; i < posts.size(); i = i + 1) {
		%>

		<%=posts.get(i).no%>
		<a
			href="/board/read?category=<%=bl.category%>&no=<%=posts.get(i).no%>"><%=posts.get(i).title%></a>
		<%=posts.get(i).id%>
		<hr>
		<%
		}
		%>
	</fieldset>
	<fieldset>
		<legend>페이지 블럭</legend>
		<%=bl.getHtmlPageList()%>
	</fieldset>
	<fieldset>
		<legend>링크</legend>
		<a href="/write.jsp?category=<%=bl.category%>">쓰기</a> <a
			href="/board/list?category=<%=bl.category%>&">list로</a> <a href="/">홈으로</a>
	</fieldset>
	<fieldset>
		<legend>검색</legend>
		<form action="/board/list">
			<input type="hidden" name="category" value="<%=bl.category%>">
			<input name="word"> <input type="submit" value="검색">
		</form>
	</fieldset>
</body>
</html>