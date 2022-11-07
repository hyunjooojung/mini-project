package com.restarea.service;

import static com.restarea.common.JDBCTemplate.commit;
import static com.restarea.common.JDBCTemplate.openConnection;
import static com.restarea.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.restarea.dao.InfoDao;
import com.restarea.model.vo.Info;
import com.restarea.model.vo.InfoAndCon;

public class InfoService {
	private InfoDao dao = new InfoDao();
	private Connection conn = null;
	
	public InfoService() {
		conn = openConnection();
	}
	
	public List<String> selectByRoutelist() {
		return dao.selectByRoutelist(conn);
	}
	
	// -- 노선별 검색
	public List<Info> selectByRoute(String routenm) {
		return dao.selectByRoute(conn, routenm);
	}
	
	// -- 노선별 검색 + 상하행검색
	public List<Info> selectRouAndGue(String routenm, String gudclssnm) {
		return dao.selectRouAndGue(conn, routenm, gudclssnm);
	}
	
	// -- 선택 휴게소가 보유한 편의시설 리스트
	public List<String> selectConvenient(String svarnm) {
		return dao.selectConvenient(conn, svarnm);
	}
	
	// -- 휴게소 세부사항 ★편의시설★
	public List<InfoAndCon> selectPsAndCon2(String svarnm, String psname) {
		return dao.selectPsAndCon(conn, svarnm, psname);
	}
	
	// -- 지역별 검색
	public List<String> selectByAvar(String avaraddr) {
		return dao.selectByAvar(conn, avaraddr);
	}
	
	// -- 휴게소 세부사항
	public List<InfoAndCon> selectPsAndCon(String svarnm) {
		return dao.selectPsAndCon(conn, svarnm);
	}
	
	public int insert(Info info) {
		int result = dao.insert(conn, info);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
}
