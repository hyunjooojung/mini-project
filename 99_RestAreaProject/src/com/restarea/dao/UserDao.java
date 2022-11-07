package com.restarea.dao;

import static com.restarea.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.restarea.common.JDBCTemplate;
import com.restarea.model.vo.User;

public class UserDao {

	// 로그인을 시도하는 함수 확인완료
	public int login(Connection conn, String userId, String userpw) {
		String sql = "SELECT userpw FROM res_user WHERE userid = ?";
		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userpw))
					return 1;// 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1;// 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public User selectOne(Connection conn, String userId, String userpw) {
		User user = null;
		try {
			String sql = "SELECT * FROM res_user WHERE userid = ? and userpw = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, userId);
			pstmt.setString(2, userpw);

			ResultSet rs = pstmt.executeQuery(); 
			if(rs.next()) {
				int userkey = rs.getInt(1);
				String id = 	rs.getString(2);
				String pw = rs.getString(3);
				String userNm 	= rs.getString(4);
				String userPh 	= rs.getString(5);

				user = new User(userkey, userId, userpw, userNm, userPh);
			}
			close(rs);
			close(pstmt);
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static void main(String[] args) {
		Connection conn = JDBCTemplate.openConnection();
		int user = new UserDao().login(conn, "test1","test1");
		System.out.println(user);

	}

}
