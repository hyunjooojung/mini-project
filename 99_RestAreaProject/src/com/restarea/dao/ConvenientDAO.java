package com.restarea.dao;

import static com.restarea.common.JDBCTemplate.close;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restarea.common.JDBCTemplate;
import com.restarea.model.vo.Convenient;

public class ConvenientDAO {
	
	// 휴게소 정보 추가
	public int insert(Connection conn, Convenient convenient) {
		try {
			String sql = "INSERT INTO "
					+ "convenient(psCode, stdRestCd, psName, psDesc, stime, etime) "
					+ "VALUES(?,?,?,?,?,?) ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int cnt = 1;
			pstmt.setString(cnt++, convenient.getPsCode());
			pstmt.setString(cnt++, convenient.getStdRestCd());
			pstmt.setString(cnt++, convenient.getPsName());
			pstmt.setString(cnt++, convenient.getPsDesc());
			pstmt.setString(cnt++, convenient.getStime());
			pstmt.setString(cnt++, convenient.getEtime());
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	// 편의시설명 검색시 휴게소이름 리스트 확인완료
	public List<String> selectInfo(Connection conn, String psname) {
			
			List<String> list = new ArrayList<String>();
			PreparedStatement pstmt = null;
			 
			ResultSet rs = null;

			try {
				String sql = "SELECT r.svarnm " 
						+ "FROM  restingarea r " 
						+ "JOIN convenient c ON(c.stdrestcd = r.svarcd) "
						+ "WHERE psname LIKE ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + psname + "%");
				rs = pstmt.executeQuery();

				while (rs.next() == true) {
					String svarnm = rs.getString("svarnm");
					list.add(svarnm);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
				close(rs);
			
			}
			return null;
		}
	
	// 편의시설 세부정보 확인완료
	public Convenient selectInfoConvenient (Connection conn, String svarnm, String psname) {
		
		PreparedStatement pstmt = null;
		Convenient c = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT c.*" +
						 " FROM convenient c" +
						 " JOIN restingarea r ON(r.svarcd = c.stdrestcd)" +
						 " WHERE r.svarnm = ?" +
						 " and psname LIKE ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarnm);
			pstmt.setString(2, "%" + psname + "%");
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				String psCode = rs.getString("psCode");
				String stdRestCd = rs.getString("stdRestCd");
				String psName = rs.getString("psName");
				String psDesc = rs.getString("psDesc");
				String stime  = rs.getString("stime");  
				String etime  = rs.getString("etime");
				
				c = new Convenient(psCode, stdRestCd, psName, psDesc, stime, etime);
				
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
		
		}
		return null;
	}
	
	
}
