package com.restarea.model.vo;

public class Food {
	private int foodSeq;
	private String svarCd;        // 휴게소 코드  
	private String foodNm;        // 음식 이름   
	private int foodCost;      // 음식 가격   
	private int recommendyn;   // 추천 메뉴   
	private String seasonMenu;    // 계절 메뉴   
	private int bestfoodyn;    // 베스트 메뉴  
	private int premiumyn;     // 프리미엄 메뉴 
	
	
	public Food() {
		super();
	}
	
	public Food(int foodSeq, String svarCd, String foodNm, int foodCost, String recommendyn, String seasonMenu, String bestfoodyn,
			String premiumyn) {
		super();
		this.foodSeq = foodSeq;
		this.svarCd = svarCd;
		this.foodNm = foodNm;
		this.foodCost = foodCost;
		this.recommendyn = changeRecommedFood(recommendyn);
		this.seasonMenu = seasonMenu;
		this.bestfoodyn = changeBestfoodyn(bestfoodyn);
		this.premiumyn = changePremiumyn(premiumyn);
	}
	
	public Food(int foodSeq, String svarCd, String foodNm, int foodCost, int recommendyn, String seasonMenu, int bestfoodyn,
			int premiumyn) {
		this.foodSeq = foodSeq;
		this.svarCd = svarCd;
		this.foodNm = foodNm;
		this.foodCost = foodCost;
		this.recommendyn = recommendyn;
		this.seasonMenu = seasonMenu;
		this.bestfoodyn = bestfoodyn;
		this.premiumyn = premiumyn;
	}
	
	
	@Override
	public String toString() {
		return "Food [foodSeq=" + foodSeq + ", svarCd=" + svarCd + ", foodNm=" + foodNm + ", foodCost=" + foodCost
				+ ", recommendyn=" + recommendyn + ", seasonMenu=" + seasonMenu + ", bestfoodyn=" + bestfoodyn
				+ ", premiumyn=" + premiumyn + "]";
	}
	
	public int getFoodSeq() {
		return foodSeq;
	}

	public void setFoodSeq(int foodSeq) {
		this.foodSeq = foodSeq;
	}

	public String getSvarCd() {
		return svarCd;
	}

	public void setSvarCd(String svarCd) {
		this.svarCd = svarCd;
	}

	public String getFoodNm() {
		return foodNm;
	}

	public void setFoodNm(String foodNm) {
		this.foodNm = foodNm;
	}

	public int getFoodCost() {
		return foodCost;
	}

	public void setFoodCost(int foodCost) {
		this.foodCost = foodCost;
	}

	public int getRecommendyn() {
		return recommendyn;
	}

	public void setRecommendyn(int recommendyn) {
		this.recommendyn = recommendyn;
	}

	public String getSeasonMenu() {
		return seasonMenu;
	}

	public void setSeasonMenu(String seasonMenu) {
		this.seasonMenu = seasonMenu;
	}

	public int getBestfoodyn() {
		return bestfoodyn;
	}

	public void setBestfoodyn(int bestfoodyn) {
		this.bestfoodyn = bestfoodyn;
	}

	public int getPremiumyn() {
		return premiumyn;
	}

	public void setPremiumyn(int premiumyn) {
		this.premiumyn = premiumyn;
	}

	private int changeRecommedFood(String recommendyn) {
		switch (recommendyn) {
			case "N":
				return 0;
			case "Y":
				return 1;
		}
		return -1;
	}
	
	private int changeBestfoodyn(String bestfoodyn) {
		switch(bestfoodyn) {
			case "N":
				return 0;
			case "Y":
				return 1;
		}
		return -1;
	}
	
	private int changePremiumyn(String premiumyn) {
		switch(premiumyn) {
			case "N":
				return 0;
			case "Y":
				return 1;
		}
		return -1;
	}
	
	public Food selectPrice(String foodNm, int foodCost) {
		Food food = new Food();
		food.foodNm = foodNm;
		food.foodCost = foodCost;
		food.foodSeq= 0;
		food.svarCd = "";
		food.recommendyn = 0;
		food.seasonMenu = "";
		food.bestfoodyn = 0;
		food.premiumyn = 0;
		return food;
	}
	
}
