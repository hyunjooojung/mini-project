package com.restarea.service;

import static com.restarea.common.JDBCTemplate.commit;
import static com.restarea.common.JDBCTemplate.openConnection;
import static com.restarea.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.restarea.dao.BookmarkDao;
import com.restarea.model.vo.Info;
import com.restarea.model.vo.User;

public class BookmarkService {

	private BookmarkDao dao = new BookmarkDao();
	private Connection conn = null;

	public BookmarkService() {
		conn = openConnection();
	}

	public int Bookmarkinsert (String svarcd, int userkey) {
		int result = dao.insert(conn, svarcd, userkey);
		if(result > 0) {
			commit(conn);
			System.out.println("즐겨찾기 추가가 완료되었습니다.");
		} else {
			rollback(conn);
		}
		return result;
	}
	

	// 휴게소 선택하면 음식리스트1 (휴게소이름 + 음식)
	public List<String> BookmarkInfoFood(User user, Info i) {
		return dao.BookmarkInfoFood(conn, user, i);
	}

	// 휴게소 선택하면 편의시설리스트 2 (휴게소이름 + 편의시설)
	public List<String> BookmarkInfoConvenient(User user, Info i) {
		return dao.BookmarkInfoConvenient(conn, user, i);
	}

	// 휴게소 선택하면 휴게소정보 3 (휴게소 정보 전체 출력)
	public List<Info> BookmarkselectAll(int userKey) {
		return dao.BookmarkselectAll(conn, userKey);
	}

}
