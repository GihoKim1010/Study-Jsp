package com.giho.jsp.board.db;

public class Dto {
	public String no;
	public String title;
	public String id;
	public String datetime;
	public String hit;
	public String text;
	public String replyCount;
	public String replyOri;
	public String category;

	public Dto(String category, String title, String id, String text) {
		this.category = category;
		this.title = title;
		this.id = id;
		this.text = text;
	}

	public Dto(String category, String no, String title, String id, String datetime, String hit, String text,
			String replyCount, String replyOri) {
		this.category = category;
		this.no = no;
		this.title = title;
		this.id = id;
		this.datetime = datetime;
		this.hit = hit;
		this.text = text;
		this.replyCount = replyCount;
		this.replyOri = replyOri;
	}
	
	public Dto(String no, String category) {
		this.category = category;
		this.no = no;
	}
	
	public Dto(String no, String title, String text) {
		this.title = title;
		this.no = no;
		this.text = text;
	}
}
