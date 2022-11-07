package com.restarea.controller;

import com.restarea.model.vo.User;
import com.restarea.service.UserService;

public class UserController {

	private UserService userService = new UserService();
	
	public void login(String userId, String userpw) {
		int user = userService.login(userId, userpw);
		if(user == 1) {
			System.out.println("로그인 성공");
			 // 로그인 성공
		}
		
		if(user == -1) {
			System.out.println("아이디 불일치");
			// 아이디 불일치
		}
		
		if(user == 0) {
			System.out.println("비밀번호 불일치");
			// 비밀번호 불일치
		}
		
		if(user == -2) {
			System.out.println("데이터 베이스 오류");
			 // 데이터 베이스 오류
		}
	}

	public User logout(User user) {
		user = null;
		return user;
	}
	
	public User getLoginUser(String userId,String userpw) {
		return userService.getInfo(userId, userpw);
	}
	
}
	
		
	

