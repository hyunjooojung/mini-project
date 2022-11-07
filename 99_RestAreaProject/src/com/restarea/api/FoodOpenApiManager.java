package com.restarea.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.restarea.model.vo.Food;



public class FoodOpenApiManager {
	public static String key = "3183768980";
	public static String FOOD_RA_JSON_URL = "https://data.ex.co.kr/openapi/restinfo/restBestfoodList";
	
	public static void main(String[] args) {
		List<Food> list = callFoodRAList();
		for(Food f : list) {
			System.out.println(f.toString());
		}
		
	}
	
	public static List<Food> callFoodRAList() {
		List<Food> fList = new ArrayList<>();
		int pageNum = 1;
		
		try {
			loop:
			while(true) {
				StringBuilder urlBuilder = new StringBuilder(FOOD_RA_JSON_URL);
				urlBuilder.append("?" + "key=" + key);
				urlBuilder.append("&" + "type=json");
				urlBuilder.append("&" + "numOfRows=" + "50");
				urlBuilder.append("&" + "pageNo=" + pageNum);
				
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				
				int code = conn.getResponseCode();
				if(code < 200 || code >= 300) {
					System.out.println("페이지가 잘못되었습니다.");
					return null;
				}
				
				InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(br);
				JSONObject jsonmain = (JSONObject) obj;
				JSONArray jsonArr = (JSONArray) jsonmain.get("list");
				
				
				if(jsonArr.isEmpty()) { 
					break loop;
				}
				
				if(jsonArr.size() > 0) {
					
					for(int i = 0; i < jsonArr.size(); i++) {
						try {
							JSONObject foodRa = (JSONObject) jsonArr.get(i); 
							int foodSeq = Integer.valueOf((String)(foodRa.get("seq"))) ;										// 음식 시퀀스
							String svarCd = (String) foodRa.get("stdRestCd");						// 휴게소 코드
							String foodNm = ((String) foodRa.get("foodNm")).replace("   ", " ");	// 음식 이름
							int foodCost = Integer.valueOf((String)foodRa.get("foodCost")) ;						// 음식 가격
							String recommendyn = (String) foodRa.get("recommendyn");				// 추천 메뉴
							String seasonMenu = (String) foodRa.get("seasonMenu");					// 계절 메뉴
							String bestfoodyn = (String) foodRa.get("bestfoodyn");					// 베스트 메뉴
							String premiumyn = (String) foodRa.get("premiumyn");					// 프리미엄 메뉴	
							
							Food food = new Food(foodSeq, svarCd, foodNm, foodCost, recommendyn, seasonMenu, bestfoodyn, premiumyn);
							fList.add(food);
							
						} catch(Exception e) {
//							System.out.println("데이터가 잘못되었습니다!");
							e.printStackTrace();
						}
					}
				}
				pageNum++;	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fList;
	}
	
	
	
}
