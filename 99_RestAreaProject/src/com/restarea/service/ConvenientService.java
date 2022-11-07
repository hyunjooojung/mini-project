package com.restarea.service;

import java.sql.Connection;
import java.util.List;

import static com.restarea.common.JDBCTemplate.commit;
import static com.restarea.common.JDBCTemplate.openConnection;
import static com.restarea.common.JDBCTemplate.rollback;

import com.restarea.dao.ConvenientDAO;
import com.restarea.model.vo.Convenient;


public class ConvenientService {
	private ConvenientDAO dao = new ConvenientDAO();
	private Connection conn = null;
	
	
	public ConvenientService() {
		conn = openConnection();
	}
	
	public int insert(Convenient convenient) {
		int result = dao.insert(conn, convenient);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	
	// 입력받은 편의시설을 가지고 있는 휴게소
	public List<String> selectInfo(String psname) {
		return dao.selectInfo(conn, psname);
	}
	
	
	// 입력받은 휴게소 + 편의시설 세부정보
	public Convenient selectInfoConvenient(String svarnm, String psname) {
		return dao.selectInfoConvenient(conn, svarnm, psname);
	}
}
