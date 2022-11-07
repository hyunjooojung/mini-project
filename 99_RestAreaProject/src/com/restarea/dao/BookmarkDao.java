package com.restarea.dao;

import static com.restarea.common.JDBCTemplate.close;
import static com.restarea.common.JDBCTemplate.openConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import com.restarea.common.JDBCTemplate;
import com.restarea.model.vo.Bookmark;
import com.restarea.model.vo.Convenient;
import com.restarea.model.vo.Info;
import com.restarea.model.vo.User;

public class BookmarkDao {

	// 유저가 선택한 휴게소 찜하기! 확인완료!
	public int insert(Connection conn, String svarcd, int userkey) {
		try {
			
			BookmarkDao dao = new BookmarkDao();
			int result = dao.PreventionOfDuplication(conn, userkey, svarcd);
			
			if(result == 1) {
				String sql = "INSERT INTO bookmark_res " 
						+"VALUES(?,?,SYSDATE)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				int cnt = 1;
				pstmt.setString(cnt++, svarcd);
				pstmt.setInt(cnt++, userkey);
				
				result = pstmt.executeUpdate();
				pstmt.close();
				
				return result;
				
			} else if(result == 0){ //중복
				System.out.println("이미 즐겨찾기 되었습니다!");
				result = 0;
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1; // DB 오류
	}
	
	// 유저가 중복 휴게소 즐찾 방지 확인완료
	public int PreventionOfDuplication(Connection conn, int userkey, String svarcd) {
		
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			String sql = "SELECT *" 
					+" FROM bookmark_res" 
					+" WHERE userkey = ?"
					+" and svarcd = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userkey);
			pstmt.setString(2, svarcd);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				return 0;
			}
			else if(rs.next() == false) {
				return 1;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return -1;
	}
	
	
	
	// 휴게소 선택하면 음식리스트1 (휴게소이름 + 음식) 확인완료!
	public List<String> BookmarkInfoFood(Connection conn, User user, Info i) {

		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			String sql = "SELECT f.foodnm " 
						+ "FROM food f " 
						+ "JOIN bookmark_res b ON(b.svarcd = f.svarcd) "
						+ "JOIN restingarea r ON(r.svarcd = f.svarcd) " 
						+ "WHERE b.userkey = ? and r.svarnm = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUserKey());
			pstmt.setString(2, i.getSvarNm());
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				String foodNm = rs.getString("foodNm");
				list.add(foodNm);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return null;
	}

	// 휴게소 선택하면 편의시설리스트 2 (휴게소이름 + 편의시설) 확인완료!
	public List<String> BookmarkInfoConvenient(Connection conn, User user, Info i) {

		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			String sql = "SELECT c.psname " 
						+ "FROM convenient c "
						+ "JOIN bookmark_res b ON(b.svarcd = c.stdrestcd) "
						+ "JOIN restingarea r ON(r.svarcd = c.stdrestcd) " 
						+ "WHERE b.userkey =  ?  and r.svarnm = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUserKey());
			pstmt.setString(2, i.getSvarNm());
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				String psName = rs.getString("psName");
				list.add(psName);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return null;
	}

	// 즐겨찾기 휴게소정보 완료!
	public List<Info> BookmarkselectAll(Connection conn, int userKey) {

		List<Info> list = new ArrayList<Info>();
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			String sql = "SELECT r.* " 
						+ "FROM restingarea r " 
						+ "JOIN bookmark_res b ON(b.svarcd = r.svarcd) "
						+ "WHERE b.userkey = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userKey);
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				String svarCd = rs.getString("svarCd");
				String svarnm = rs.getString("svarnm");
				String svarAddr = rs.getString("svarAddr");
				String routeNm = rs.getString("routeNm");
				String avarAddr = rs.getString("avarAddr");
				String gudClssNm = rs.getString("gudClssNm");
				String rprsTelNo = rs.getString("rprsTelNo");

				Info info = new Info(svarCd, svarnm, svarAddr, routeNm, avarAddr, gudClssNm, rprsTelNo);
				list.add(info);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}




}
