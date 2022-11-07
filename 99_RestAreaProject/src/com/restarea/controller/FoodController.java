package com.restarea.controller;

import java.util.List;

import com.restarea.api.FoodOpenApiManager;
import com.restarea.model.vo.Food;
import com.restarea.service.FoodService;

public class FoodController {
	
	private FoodService foodService = new FoodService();
	
	public void initFood() {
		
		List<Food> list = FoodOpenApiManager.callFoodRAList();
		
		while(true) {
			if(list == null || list.isEmpty()) {
				break;
			}
			for(Food f : list) {
				System.out.println(f);
				foodService.insert(f);
			}
			list = null;
		}
		
	}
	
	public List<Food> selectPrice1(String svarNm){
		return foodService.selectPrice1(svarNm);
	}
	
	public List<Food> selectPrice2(String svarNm){
		return foodService.selectPrice2(svarNm);
	}
	
	public List<Food> selectPrice3(String svarNm){
		return foodService.selectPrice3(svarNm);
	}
	
	public List<Food> selectPrice4(String svarNm){
		return foodService.selectPrice4(svarNm);
	}
	
	public List<Food> selectPremium(String svarNm){
		return foodService.selectPremium(svarNm);
	}
	
	public List<Food> selectRecommend(String svarNm) {
		return foodService.selectRecommend(svarNm);
	}
	
	public List<Food> selectSeason1(String svarNm) {
		return foodService.selectSeason1(svarNm);
	}
	
	public List<Food> selectSeason2(String svarNm) {
		return foodService.selectSeason2(svarNm);
	}
	
	public List<Food> selectDetail(String svarNm, String foodNm) {
		return foodService.selectDetail(svarNm, foodNm);
	}
	
	public List<String> findFoodRouteNm(String foodNm) {
		return foodService.findFoodRouteNm(foodNm);
	}
	
	public List<String> findFoodRa(String foodNm, String routenm) {
		return foodService.findFoodRa(routenm, foodNm);
	}
	
	public List<Food> findFood(String svarNm, String foodNm) {
		return foodService.findFood(svarNm, foodNm);
	}
	
	public static void main(String[] args) {
		FoodController fc = new FoodController();
		//fc.initFood();
	}
}
