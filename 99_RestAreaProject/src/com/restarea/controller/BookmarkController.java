package com.restarea.controller;

import java.util.List;

import com.restarea.model.vo.Info;
import com.restarea.model.vo.User;
import com.restarea.service.BookmarkService;

public class BookmarkController {

	private BookmarkService bookmarkService = new BookmarkService();
	
	// 즐겨찾기
	public int Bookmarkinsert(String svarcd, int userkey) {
		return bookmarkService.Bookmarkinsert(svarcd, userkey); 
	}

	
	// 휴게소 선택하면 음식리스트1 (휴게소이름 + 음식)
	public List<String> BookmarkInfoFoodname(User user, Info i){
		return bookmarkService.BookmarkInfoFood(user, i);
	}
	
	// 휴게소 선택하면 편의시설리스트 2 (휴게소이름 + 편의시설)
	public List<String> BookmarkInfoConvenient(User user, Info i){
		return bookmarkService.BookmarkInfoConvenient(user, i);
	}
	
	// 휴게소 선택하면 휴게소정보 3 (휴게소 정보 전체 출력)
	public List<Info> BookmarkselectAll(int userKey){
		return bookmarkService.BookmarkselectAll(userKey);
	}
	
}
