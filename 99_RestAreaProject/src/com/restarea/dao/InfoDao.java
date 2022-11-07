package com.restarea.dao;

import static com.restarea.common.JDBCTemplate.close;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restarea.model.vo.Info;
import com.restarea.model.vo.InfoAndCon;

public class InfoDao {

	public int insert(Connection conn, Info info) {
		try {
			String sql = "INSERT INTO "
					+ "RestingArea(svarCd, svarNm, svarAddr, routeNm, avarAddr, gudClssNm, rprsTelNo) "
					+ "VALUES(?,?,?,?,?,?,?) ";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			int cnt = 1;
			pstmt.setString(cnt++, info.getSvarCd());
			pstmt.setString(cnt++, info.getSvarNm());
			pstmt.setString(cnt++, info.getSvarAddr());
			pstmt.setString(cnt++, info.getRouteNm());
			pstmt.setString(cnt++, info.getAvarAddr());
			pstmt.setString(cnt++, info.getGudClssNm());
			pstmt.setString(cnt++, info.getRprsTelNo());

			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
// 26 -- 노선리스트
	public List<String> selectByRoutelist(Connection conn) {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT DISTINCT routenm "
					+ "FROM restingarea ORDER BY routenm";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next() == true) {
				String routenm = rs.getString("ROUTENM");
				list.add(routenm);
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
	
	
//	27 -- 노선별 검색
	public List<Info> selectByRoute(Connection conn, String routenm) {
		List<Info> list = new ArrayList<Info>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * "
					+ "FROM RESTINGAREA "
					+ "WHERE ROUTENM = ?"
					+ "And SVARNM LIKE '%휴게소'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, routenm);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String SVARCD = rs.getString("SVARCD");
				String SVARNM = rs.getString("SVARNM");
				String SVARADDR = rs.getString("SVARADDR");
				String ROUTENM = rs.getString("ROUTENM");
				String AVARADDR = rs.getString("AVARADDR");
				String GUDCLSSNM = rs.getString("GUDCLSSNM");
				String RPRSTELNO = rs.getString("RPRSTELNO");
				Info info = new Info(SVARCD, SVARNM, SVARADDR, ROUTENM, AVARADDR, GUDCLSSNM, RPRSTELNO);
				list.add(info);
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
	
	
//	30 -- 노선별 검색 + 상하행검색
	public List<Info> selectRouAndGue(Connection conn, String routenm, String gudclssnm) {
		List<Info> list = new ArrayList<Info>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * "
					+ "FROM RESTINGAREA "
					+ "WHERE ROUTENM = ? "
					+ "AND GUDCLSSNM = ? "
					+ "AND SVARNM LIKE '%게소'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, routenm);
			pstmt.setString(2, gudclssnm);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String SVARCD = rs.getString("SVARCD");
				String SVARNM = rs.getString("SVARNM");
				String SVARADDR = rs.getString("SVARADDR");
				String ROUTENM = rs.getString("ROUTENM");
				String AVARADDR = rs.getString("AVARADDR");
				String GUDCLSSNM = rs.getString("GUDCLSSNM");
				String RPRSTELNO = rs.getString("RPRSTELNO");
				Info info = new Info(SVARCD, SVARNM, SVARADDR, ROUTENM, AVARADDR, GUDCLSSNM, RPRSTELNO);
				list.add(info);
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
	
//	33 -- 선택 휴게소가 보유한 편의시설 리스트
	public List<String> selectConvenient(Connection conn, String svarnm) {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT C.PSNAME FROM CONVENIENT C "
					+ "JOIN RESTINGAREA R ON(C.STDRESTCD = R.SVARCD) "
					+ "WHERE R.SVARNM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarnm);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String PSNAME = rs.getString("PSNAME");
				list.add(PSNAME);
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
	
//	38, 52 -- 휴게소 세부사항 ★편의시설★
	public List<InfoAndCon> selectPsAndCon(Connection conn, String svarnm, String psname) {
		List<InfoAndCon> list = new ArrayList<InfoAndCon>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM CONVENIENT C "
					+ "JOIN RESTINGAREA R ON(C.STDRESTCD = R.SVARCD) "
					+ "WHERE R.SVARNM = ? AND C.PSNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarnm);
			pstmt.setString(2, psname);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String PSCODE = rs.getString("PSCODE");
				String STDRESTCD = rs.getString("STDRESTCD");
				String PSNAME = rs.getString("PSNAME");
				String PSDESC = rs.getString("PSDESC");
				String STIME = rs.getString("STIME");
				String ETIME = rs.getString("ETIME");
				String SVARCD = rs.getString("SVARCD");
				String SVARNM = rs.getString("SVARNM");
				String SVARADDR = rs.getString("SVARADDR");
				String ROUTENM = rs.getString("ROUTENM");
				String AVARADDR = rs.getString("AVARADDR");
				String GUDCLSSNM = rs.getString("GUDCLSSNM");
				String RPRSTELNO = rs.getString("RPRSTELNO");
				InfoAndCon infoandcon = new InfoAndCon(PSCODE, STDRESTCD, PSNAME, PSDESC, STIME, ETIME, 
						SVARCD, SVARNM, SVARADDR, ROUTENM, AVARADDR, GUDCLSSNM, RPRSTELNO);
				list.add(infoandcon);
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
	
//	44 -- 지역별 검색
	public List<String> selectByAvar(Connection conn, String avaraddr) {
		List<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT SVARNM "
					+ "FROM RESTINGAREA "
					+ "WHERE AVARADDR = ?"
					+ "and SVARNM LIKE '%게소'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, avaraddr);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String SVARNM = rs.getString("SVARNM");
				list.add(SVARNM);
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
	
//	47 -- 휴게소 세부사항
	public List<InfoAndCon> selectPsAndCon(Connection conn, String svarnm) {
		List<InfoAndCon> list = new ArrayList<InfoAndCon>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM CONVENIENT C "
					+ "JOIN RESTINGAREA R ON(C.STDRESTCD = R.SVARCD) "
					+ "WHERE R.SVARNM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarnm);
			rs = pstmt.executeQuery();
			
			while (rs.next() == true) {
				String PSCODE = rs.getString("PSCODE");
				String STDRESTCD = rs.getString("STDRESTCD");
				String PSNAME = rs.getString("PSNAME");
				String PSDESC = rs.getString("PSDESC");
				String STIME = rs.getString("STIME");
				String ETIME = rs.getString("ETIME");
				String SVARCD = rs.getString("SVARCD");
				String SVARNM = rs.getString("SVARNM");
				String SVARADDR = rs.getString("SVARADDR");
				String ROUTENM = rs.getString("ROUTENM");
				String AVARADDR = rs.getString("AVARADDR");
				String GUDCLSSNM = rs.getString("GUDCLSSNM");
				String RPRSTELNO = rs.getString("RPRSTELNO");
				InfoAndCon infoandcon = new InfoAndCon(PSCODE, STDRESTCD, PSNAME, PSDESC, STIME, ETIME, 
						SVARCD, SVARNM, SVARADDR, ROUTENM, AVARADDR, GUDCLSSNM, RPRSTELNO);
				list.add(infoandcon);
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
	
}