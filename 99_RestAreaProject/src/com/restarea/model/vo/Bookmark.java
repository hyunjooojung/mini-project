package com.restarea.model.vo;

import java.sql.Date;

public class Bookmark {

	private int userKey;
	private Date date;
	private String svarCd;

	public Bookmark() {
		super();
	}

	public Bookmark(int userKey, Date date, String svarCd) {
		super();
		this.userKey = userKey;
		this.date = date;
		this.svarCd = svarCd;
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSvarCd() {
		return svarCd;
	}

	public void setSvarCd(String svarCd) {
		this.svarCd = svarCd;
	}

	@Override
	public String toString() {
		return "Bookmark [userKey=" + userKey + ", date=" + date + ", svarCd=" + svarCd + "]";
	}

}
