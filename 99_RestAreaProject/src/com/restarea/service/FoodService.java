package com.restarea.service;

import static com.restarea.common.JDBCTemplate.commit;
import static com.restarea.common.JDBCTemplate.openConnection;
import static com.restarea.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.restarea.dao.FoodDao;
import com.restarea.model.vo.Food;

public class FoodService {
	private FoodDao dao = new FoodDao();
	private Connection conn = null;
	
	public FoodService() {
		conn = openConnection();
	}
	
	public int insert(Food food) {
		int result = dao.insert(conn, food);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public List<Food> selectPrice1(String svarNm) {
		return dao.selectPrice1(conn, svarNm);
	}
	
	public List<Food> selectPrice2(String svarNm) {
		return dao.selectPrice2(conn, svarNm);
	}
	
	public List<Food> selectPrice3(String svarNm) {
		return dao.selectPrice3(conn, svarNm);
	}
	
	public List<Food> selectPrice4(String svarNm) {
		return dao.selectPrice4(conn, svarNm);
	}
	
	public List<Food> selectPremium(String svarNm) {
		return dao.selectPremium(conn, svarNm);
	}
	
	public List<Food> selectRecommend(String svarNm) {
		return dao.selectRecommend(conn, svarNm);
	}
	
	public List<Food> selectSeason1(String svarNm) {
		return dao.selectSeason1(conn, svarNm);
	} 
	
	public List<Food> selectSeason2(String svarNm) {
		return dao.selectSeason2(conn, svarNm);
	} 
	
	public List<Food> selectDetail(String svarNm, String foodNm) {
		return dao.selectDetail(conn, svarNm, foodNm);
	}
	
	public List<String> findFoodRouteNm(String foodNm) {
		return dao.findFoodRouteNm(conn, foodNm);
	}
	
	public List<String> findFoodRa(String foodNm, String routenm) {
		return dao.findFoodRa(conn, routenm, foodNm);
	}
	
	public List<Food> findFood(String svarNm, String foodNm) {
		return dao.findFood(conn, svarNm, foodNm);
	}
}
