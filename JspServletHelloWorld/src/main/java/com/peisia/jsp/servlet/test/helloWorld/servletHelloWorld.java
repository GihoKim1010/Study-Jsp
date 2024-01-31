package com.peisia.jsp.servlet.test.helloWorld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Xx")
public class servletHelloWorld extends HttpServlet {

	//get으로 하는 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 안하면 한글 깨짐

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		PrintWriter out = response.getWriter();
		out.println("<b>아이디는" + id);
		out.println("<b>비번은" + pw);

		System.out.println("id: " + id);
		System.out.println("pw: " + pw);
	}

	//post로 하는 방식
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");        // 안하면 한글 깨짐

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		PrintWriter out = response.getWriter();
		out.println("<b>아이디는" + id);
		out.println("<b>비번은" + pw);
		
		out.print(
				"<html>"+
						"<head>"+
						"</head>"+
						"<body>"+
						"</body>"+
						"</html>"
						);
//		이런식으로 html을 servlet 내에서 바로 작성할 수도 있긴 하다.

		System.out.println("id: " + id);
		System.out.println("pw: " + pw);

		HttpSession session = request.getSession();
		session.setAttribute("id", "고양이");
		session.setMaxInactiveInterval(60);	//60초 동안 아무것도 안하면 만료
	}

}