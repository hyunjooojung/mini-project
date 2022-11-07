package com.restarea.model.vo;

public class User {
	private int userKey;
	private String userId;
	private String userpw;
	private String userNm;
	private String userPh;

	public User() {
		super();
	}

	public User(int userKey, String userId, String userpw, String userNm, String userPh) {
		super();
		this.userKey = userKey;
		this.userId = userId;
		this.userpw = userpw;
		this.userNm = userNm;
		this.userPh = userPh;
	}

	public String toString() {
		return "User [userKey=" + userKey + ", userId=" + userId + ", userpw=" + userpw + ", userNm=" + userNm
				+ ", userPh=" + userPh + "]";
	}

	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserPh() {
		return userPh;
	}

	public void setUserPh(String userPh) {
		this.userPh = userPh;
	}



}
