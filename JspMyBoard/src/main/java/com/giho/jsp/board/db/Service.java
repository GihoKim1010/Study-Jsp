package com.giho.jsp.board.db;

import com.giho.jsp.board.BoardList;

public class Service {
	Dao dao;
	
	public Service() {
		dao = new Dao();
	}
	
	public void del(String category, String no) {
		dao.del(category, no);
	}
	
	public void write(Dto d) {
		dao.write(d);
	}
	
	public Dto read(String category, String no) {
		return dao.read(category, no);
	}
	
	public void edit(Dto d, String no) {
		dao.edit(d, no);
	}
	
	public BoardList list(String category, String currentPage, String searchWord) {
		if(currentPage==null) {
			currentPage="1";
		}
		BoardList bl = new BoardList(dao, category, currentPage, searchWord);
		return bl;
	}
}
