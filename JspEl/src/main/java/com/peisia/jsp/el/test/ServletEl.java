package com.peisia.jsp.el.test;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletEl")
public class ServletEl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cat cat = new Cat("키티",7);
		
		
		request.setAttribute("kitty", cat);
		
		request.setAttribute("x", "문자엑스");
		
		RequestDispatcher rd = request.getRequestDispatcher("/cat.jsp");
		rd.forward(request, response);
	}
	
}