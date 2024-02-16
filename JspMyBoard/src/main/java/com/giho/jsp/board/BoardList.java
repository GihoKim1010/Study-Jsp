package com.giho.jsp.board;

import java.util.ArrayList;

import com.giho.jsp.board.db.Dao;
import com.giho.jsp.board.db.Dto;

public class BoardList {
	private Dao dao;
	public String category;
	public ArrayList<Dto> posts;
	public int totalPage;
	public int currentPage;
	public String searchWord; // 검색어 있을경우 대비

	int totalBlock; // 블럭 총 갯수
	int currentBlock; // 현재 블럭 no
	int blockStart; // 블럭 시작 페이지 번호
	int blockEnd; // 블럭 끝 페이지 번호
	int prevPage; // 이전 페이지 번호
	int nextPage; // 다음 페이지 번호
	boolean hasPrev = true; // 이전 페이지 존재 가능 여부 초기화
	boolean hasNext = true; // 다음 페이지 존재 가능 여부 초기화

	public BoardList(Dao dao, String category, String currentPage, String searchWord) {
		super();
		this.dao = dao;
		this.category = category;
		this.currentPage = Integer.parseInt(currentPage);
		this.searchWord = searchWord;
		this.totalPage = countPage();
		getList();

		totalBlock = (int) Math.ceil((double) totalPage / Board.PAGE_LINK_AMOUNT);
		currentBlock = (int) Math.ceil((double) this.currentPage / Board.PAGE_LINK_AMOUNT);
		blockStart = (currentBlock - 1) * Board.PAGE_LINK_AMOUNT + 1;
		blockEnd = currentBlock * Board.PAGE_LINK_AMOUNT;
		if (blockEnd > totalPage) { // blockEnd 가 totalPage를 넘어가는 예외처리
			blockEnd = totalPage;
		}

		if (currentBlock == 1) { // hasPrev, hasNext 값 지정
			hasPrev = false;
		} else {
			hasPrev = true;
		}

		if (currentBlock >= totalBlock) {
			hasNext = false;
		} else {
			hasNext = true;
		}
	}

	// 페이지 수 구하기
	public int countPage() {
		int count = dao.countPosts(category, searchWord);
		// 아래는 삼항 연산자. 0일경우 그대로, 아닐경우 1을 더했음.
		int totalPage = (count % Board.LIST_AMOUNT == 0) ? count / Board.LIST_AMOUNT : count / Board.LIST_AMOUNT + 1;
		return totalPage;
	}

	public void getList() {
		int startIndex = (currentPage - 1) * Board.LIST_AMOUNT;
		posts = dao.list(category, startIndex, searchWord);
	}

	// 글 리스트 객체를 얻어옴
	public ArrayList<Dto> getPosts() {
		return posts;
	}

	// 페이지 리스트를 출력하기 위해 html을 리턴받음
	public String getHtmlPageList() {
		String html = "";

		// 이전 블럭이 있는 경우
		if (hasPrev) {
			if (searchWord == null) {
				html = html + String.format("<a href='/board/list?category=%s&page=%d'>🐿️이전블럭가기🐿️</a>", category,
						prevPage);
			} else {
				html = html + String.format("<a href='/board/list?category=%s&page=%d&word=%s'>🐿️이전블럭가기🐿️</a>",
						category, prevPage, searchWord);
			}
		}

		// 페이징 블럭 생성
		for (int i = blockStart; i <= blockEnd; i++) {
			if (searchWord == null) {
				html = html
						+ String.format("<a href='/board/list?category=%s&page=%d'>%d</a>&nbsp;&nbsp;", category, i, i);
			} else {
				html = html + String.format("<a href='/board/list?category=%s&page=%d&word=%s'>%d</a>&nbsp;&nbsp;",
						category, i, searchWord, i);
			}
		}

		// 다음블럭이 있는 경우
		if (hasNext) {
			if (searchWord == null) {
				html = html + String.format("<a href='/board/list?category=%s&page=%d'>🐿️다음블럭가기🐿️</a>", category,
						nextPage);
			} else {
				html = html + String.format("<a href='/board/list?category=%s&page=%d&word=%s'>🐿️다음블럭가기🐿️</a>",
						category, nextPage, searchWord);
			}

		}
		return html;
	}
}
