package com.giho.jsp.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.giho.jsp.board.Board;

public class Dao {
	public Connection con = null;
	public Statement st = null;

	public void connect() {
		try { // 고정문
			Class.forName(Db.DB_JDBC_DRIVER_PACKAGE_PATH);
			con = DriverManager.getConnection(Db.DB_URL, Db.DB_ID, Db.DB_PW);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(String sql) {
		try {
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try { // 고정문
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 글 삭제하기
	public void del(String category, String no) {
		connect();
		String sql = String.format("delete from MY_BOARD;", Board.MY_BOARD, no, category);
		System.out.println("sql : " + sql); // 콘솔로 확인할 용도
		update(sql);
		close();
	}

	// 글 쓰기
	public void write(Dto d) {
		connect();
		String sql = String.format(
				"insert into MY_BOARD (b_category, b_title, b_id, b_text) values ('%s', '%s', '%s', '%s')",
				Board.MY_BOARD, d.category, d.title, d.id, d.text);
		System.out.println("sql : " + sql); // 콘솔로 확인할 용도
		update(sql);
		close();
	}

	// 글 읽기
	public Dto read(String no, String category) {
		connect();
		Dto post = null;
		try {
			String sql = String.format("select * from MY_BOARD where b_no=%s and b_category like '%s'", Board.MY_BOARD,
					no, category);
			System.out.println("sql : " + sql); // 콘솔로 확인할 용도
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(category, rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"),
					rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
					rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return post;
	}

	public void edit(Dto d, String no) { // 글 수정하기
		connect();
		String sql = String.format("update %s set b_title='%s', b_text='%s' where b_no=%s", Board.MY_BOARD, d.title,
				d.text, no);
		System.out.println("sql : " + sql); // 콘솔로 확인할 용도
		update(sql);
		close();
	}

	// 글 리스트 보기
	public ArrayList<Dto> list(String category, int startIndex, String searchWord) {
		connect();
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			String sql;
			if (searchWord == null) {	//검색어 없을경우
				sql = String.format("select * from MY_BOARD where b_category like '%s' limit %d,%d",
						Board.MY_BOARD, category, startIndex, Board.LIST_AMOUNT);
			} else {	//검색어 있을경우
				sql = String.format("select * from MY_BOARD where b_title like '%%%s%%' and b_category like '%s' limit %d,%d",
						Board.MY_BOARD, category, startIndex, Board.LIST_AMOUNT);
			}
			System.out.println("sql : " + sql); // 콘솔로 확인할 용도
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				posts.add(new Dto(rs.getString("B_CATEGORY"), rs.getString("B_NO"), rs.getString("B_TITLE"),
						rs.getString("B_ID"), rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return posts;
	}

	// 카테고리 내 글 수 세기
	public int countPosts(String category, String searchWord) {
		int count = 0;
		connect();
		try {
			if (searchWord == null) { // 검색어 없을경우
				String sql = String.format("select count(*) from MY_BOARD where b_category like '%s'", Board.MY_BOARD,
						category);
				System.out.println("sql : " + sql); // 콘솔로 확인할 용도
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				count = rs.getInt("count(*)");
			} else { // 검색어 있는경우
				String sql = String.format(
						"select count(*) from MY_BOARD where b_title like '%%%s%%' and b_category like '%s'",
						Board.MY_BOARD, searchWord, category);
				System.out.println("sql : " + sql); // 콘솔로 확인할 용도
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				count = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return count;
	}

}
