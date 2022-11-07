package com.restarea.dao;

import static com.restarea.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.restarea.common.JDBCTemplate;
import com.restarea.model.vo.Food;
import com.restarea.model.vo.Convenient;
import com.restarea.model.vo.Info;


public class FoodDao {
	
	public int insert(Connection conn, Food food) {
		try {
			String sql = "INSERT INTO food(foodSeq, svarCd, foodNm, foodCost, "
					+ "recommendyn, seasonMenu, bestfoodyn, premiumyn) "
					+ "VALUES(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int cnt = 1;
			pstmt.setInt(cnt++, food.getFoodSeq()); 
			pstmt.setString(cnt++, food.getSvarCd()); 
			pstmt.setString(cnt++, food.getFoodNm()); 
			pstmt.setInt(cnt++, food.getFoodCost()); 
			pstmt.setInt(cnt++, food.getRecommendyn()); 
			pstmt.setString(cnt++, food.getSeasonMenu()); 
			pstmt.setInt(cnt++, food.getBestfoodyn()); 
			pstmt.setInt(cnt++, food.getPremiumyn());
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 1-1. 가격별 검색 - 6000원 미만 
	public List<Food> selectPrice1(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON (f.svarcd = r.svarcd) "
					+ "WHERE f.foodcost < 6000 and r.svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));	
				// selectPrice() 메서드는 값을 초기화하는데, foodnm과 foodCost만 인자 값으로 받고 나머지는 기본값으로 초기화
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 1-2. 가격별 검색 - 6000원 이상 - 8000원 미만 사이 
	public List<Food> selectPrice2(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON (f.svarcd = r.svarcd) "
					+ "WHERE f.foodcost >= 6000 and f.foodcost < 8000 and r.svarNm =  ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));	
				// selectPrice() 메서드는 값을 초기화하는데, foodnm과 foodCost만 인자 값으로 받고 나머지는 기본값으로 초기화
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	// 1-3. 가격별 검색 - 8000원 이상 - 10000원 미만 사이 
	public List<Food> selectPrice3(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON (f.svarcd = r.svarcd) "
					+ "WHERE f.foodcost >= 8000 and f.foodcost < 10000 and r.svarNm =  ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));	
				// selectPrice() 메서드는 값을 초기화하는데, foodnm과 foodCost만 인자 값으로 받고 나머지는 기본값으로 초기화
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	// 1-4. 가격별 검색 - 10000원 이상 
	public List<Food> selectPrice4(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON (f.svarcd = r.svarcd) "
					+ "WHERE f.foodcost >= 10000 and r.svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));	
				// selectPrice() 메서드는 값을 초기화하는데, foodnm과 foodCost만 인자 값으로 받고 나머지는 기본값으로 초기화
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}

	
	// 2. 프리미엄 메뉴 검색
	public List<Food> selectPremium(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f JOIN restingarea r "
					+ "ON (f.svarcd = r.svarcd) WHERE f.premiumyn = 1 "
					+ "and r.svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	// 3. 추천메뉴 검색
	public List<Food> selectRecommend(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm , f.foodcost FROM food f "
					+ "JOIN restingarea r ON (f.svarcd = r.svarcd) "
					+ "WHERE f.recommendyn = 1 "
					+ "and svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 4. 계절 메뉴 검색1 - 겨울
	public List<Food> selectSeason1(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT f.foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON(f.svarcd = r.svarcd) "
					+ "WHERE f.seasonmenu = 'w' "
					+ "and svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 5. 계절 메뉴 검색2 - 여름
	public List<Food> selectSeason2(Connection conn, String svarNm) {
		List<Food> list = new ArrayList<>();
		Food food = new Food();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT foodnm, f.foodcost FROM food f "
					+ "JOIN restingarea r ON(f.svarcd = r.svarcd) "
					+ "WHERE f.seasonmenu = 's' and r.svarNm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 6. 상세 메뉴 보기
	public List<Food> selectDetail(Connection conn, String svarNm, String foodNm) {
		List<Food> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Food food = new Food();
		
		try {
			String sql = "SELECT foodnm, foodcost FROM food f "
					+ "JOIN restingarea r ON(f.svarcd = r.svarcd) "
					+ "WHERE r.svarNm = ? and f.foodnm = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, svarNm);
			pstmt.setString(2, foodNm);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String foodnm = rs.getString("foodnm");
				int foodCost = rs.getInt("foodcost");
				list.add(food.selectPrice(foodnm, foodCost));	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 7. 음식명 검색 - 결과로 노선명이 출력
	public List<String> findFoodRouteNm(Connection conn, String foodNm) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT routenm FROM restingarea r "
					+ "JOIN food f ON(r.svarcd = f.svarcd) "
					+ "WHERE f.foodnm LIKE ? GROUP BY routenm";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + foodNm + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String routenm = rs.getString("routenm");
				list.add(routenm);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	// 8. 음식명 + 노선 검색 = 휴게소 이름
	public List<String> findFoodRa(Connection conn, String foodNm, String routenm) {
		List<String> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT svarNm FROM restingarea r "
					+ "JOIN food f ON(r.svarcd = f.svarcd) "
					+ "WHERE r.routenm = ? and f.foodnm LIKE ? "
					+ "GROUP BY svarNm";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, routenm);
			pstmt.setString(2, "%" + foodNm + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String svarNm = rs.getString("svarNm");
				list.add(svarNm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	
	// 9. 음식 리스트 검색 ★★★ <- x mind에서 음식명 검색으로 들어간 것이니 휴게소에서 검색한 음식만 나오도록 하는 것인지 확인 필요
		//    우선은 선택된 휴게소에서 검색한 음식으로 나오는 모든 메뉴를 출력하도록 함
		public List<Food> findFood(Connection conn, String svarNm, String foodNm) {
			List<Food> list = new ArrayList<>();
			Food food = new Food();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "SELECT f.foodnm, f.foodcost FROM food f "
						+ "JOIN restingarea r ON(r.svarcd = f.svarcd) "
						+ "WHERE r.svarNm = ? and f.foodNm LIKE ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, svarNm);
				pstmt.setString(2, "%" + foodNm + "%");
				rs = pstmt.executeQuery();
				
				while(rs.next() == true) {
					String foodnm = rs.getString("foodnm");
					int foodcost = rs.getInt("foodcost");
					list.add(food.selectPrice(foodnm, foodcost));
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
