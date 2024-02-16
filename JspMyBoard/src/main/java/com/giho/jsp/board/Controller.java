package com.giho.jsp.board;

import java.io.IOException;

import com.giho.jsp.board.db.Dto;
import com.giho.jsp.board.db.Service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class Controller extends HttpServlet {
	String category;
	String nextPage;
	Service service;

	@Override
	public void init() throws ServletException {
		service = new Service();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		category = request.getParameter("category");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		if (action != null) {
			switch (action) {
			case "/del":
				nextPage = "/board/list";
				service.del(category, request.getParameter("no"));
				break;
			case "/write":
				nextPage = "/board/list";
				Dto dto = new Dto(category, request.getParameter("title"), request.getParameter("id"),
						request.getParameter("text"));
				service.write(dto);
				break;
			case "/edit_insert":
				nextPage="/edit.jsp";
				request.setAttribute("post", service.read(category, request.getParameter("no")));
				break;
			case "/edit_proc":
				nextPage="/board/list";
				service.edit(
						new Dto(request.getParameter("title"),
								request.getParameter("text")
								)
						,request.getParameter("no")
						);
				break;
			case "/read":
				nextPage="/read.jsp";
				Dto d=service.read(category, request.getParameter("no"));
				request.setAttribute("post", d);
				break;
			case "/list":
				nextPage="list.jsp";
				BoardList bl = service.list(category, request.getParameter("page"), request.getParameter("searchWord"));
				request.setAttribute("bl", bl);
				break;
			}
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
		}
	}
}
