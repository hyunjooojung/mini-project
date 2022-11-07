package com.restarea.view;

import java.util.List;
import java.util.Scanner;

import com.restarea.controller.BookmarkController;
import com.restarea.controller.ConvenientController;
import com.restarea.controller.FoodController;
import com.restarea.controller.InfoController;
import com.restarea.controller.UserController;
import com.restarea.model.vo.Convenient;
import com.restarea.model.vo.Food;
import com.restarea.model.vo.Info;
import com.restarea.model.vo.User;

public class View {
	// scanner
	private Scanner sc = new Scanner(System.in);

	// controller
	private FoodController fc = new FoodController();
	private ConvenientController cc = new ConvenientController();
	private InfoController ic = new InfoController();
	private UserController uc = new UserController();
	private BookmarkController bc = new BookmarkController();

	private int selnum = 0; // 메인메뉴에서 선택 변수
	private User user = null; // 로그인 유무 
	
	public void Homemenu() {
		while (true) {
			System.out.println("\n환영합니다 휴게소 정보검색창입니다.");
			System.out.println("------------------------");
			System.out.println("1. 로그인 ");
			System.out.println("2. 휴게소 정보 검색 ");
			System.out.println("3. 음식검색 ");
			System.out.println("4. 종료 ");
			if (user != null) {
				System.out.println("=======================");
				System.out.println("5. 즐겨찾는 목록");
				System.out.println("6. 로그아웃");
			}

			System.out.print("메뉴 번호를 입력해주세요 : ");
			selnum = sc.nextInt();

			switch (selnum) {
			case 1:
				if (user == null) {
					loginmenu();
					break;
				}

				if (user != null) {
					System.out.println("\n이미 로그인 중입니다.");
					break;
				}

			case 2:
				restareaInfo();
				break;

			case 3:
				System.out.println();
				FoodMenu();
				break;

			case 4:
				System.out.println("\n시스템을 종료합니다!!");
				System.exit(0);

			case 5:
				if (user == null) {
					System.out.println("\n잘못 입력하셨습니다.");
					break;
				}
				Bookmarklist();
				break;

			case 6:
				if (user == null) {
					System.out.println("\n잘못 입력하셨습니다.");
					break;
				}
				user = uc.logout(user);
				break;

			default:
				System.out.println("\n잘못 입력하셨습니다.");
				break;
			}
		}

	}
	
	// =========================================================================
	// 로그인
	public void loginmenu() {
		System.out.println();
		System.out.print("ID를 입력해주세요 : ");
		String id = sc.next();
		System.out.print("PW를 입력해주세요 : ");
		String pw = sc.next();

		uc.login(id, pw);
		user = uc.getLoginUser(id, pw);
		
	}
	
	// 휴게소 정보검색
	public void restareaInfo() {
		System.out.println("\n1. 노선별 검색");
		System.out.println("2. 지역별 검색");
		System.out.println("3. 편의시설 검색");
		System.out.println("4. 뒤로가기");
		
		System.out.print("메뉴 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		
		switch (num) {
			case 1: 
				//노선별 검색
				routefind();
				break;
			case 2: 
				selectArea();
				break;
			case 3: 
				conFind();
				break;
			case 4:
				Homemenu();
				break;
		
			default:
				System.out.println("\n잘못 입력하셨습니다.");;
		}
	}

	// 노선선택
	public void routefind() {
		int cnt = 1;
		System.out.println("\n=========노선별 검색==========");
		for(String route : ic.selectByRoutelist()) {
			System.out.println(cnt++ +". "+ route);
		}
			
		System.out.print("노선번호를 선택해주세요 : ");
		int num = sc.nextInt();
		
		if(num >= ic.selectByRoutelist().size() + 1) {
			System.out.println("잘못 누르셨습니다. 메인메뉴");
			Homemenu();
		}
		
		
		upDownfind(ic.selectByRoutelist().get(num - 1));
	}

	//상하행 검색
	public void upDownfind(String routeNm) {
		System.out.println("\n1. 상행");
		System.out.println("2. 하행");
		System.out.println("3. 양방향");
		System.out.println("4. 뒤로가기");
		
		System.out.print("메뉴 번호를 입력해주세요 : ");
		int num = sc.nextInt();
		
		switch (num) {
			case 1: 
				//상행 검색
				Find(routeNm, "상행");
				break;
			case 2:
				//하행 검색
				Find(routeNm, "하행");
				break;
			case 3: 
				//전체 검색
				Find(routeNm, "전체");
				break;
			case 4:
				routefind();
				break;
		
			default:
				System.out.println("\n잘못 입력하셨습니다.");;
		}

	}

	// 노선 + 방향 검색
	public void Find(String routeNm, String gudclssnm) {
		List<Info> list = ic.selectRouAndGue(routeNm, gudclssnm);
		int cnt = 1;
		
		if(gudclssnm.equals("상행")) {
			System.out.println("=========" + routeNm  + "상행 검색==========");
			for(Info i : list) {
				System.out.println(cnt++ + "." + i.getSvarNm());
			}
		}
		
		if(gudclssnm.equals("하행")) {
			System.out.println("=========" + routeNm  + "하행 검색==========");
			for(Info i : list) {
				System.out.println(cnt++ + "." + i.getSvarNm());
			}
		}
		
		if(gudclssnm.equals("전체")) {
			 list = ic.selectByRoute(routeNm);
			System.out.println("=========" + routeNm  + "전체 검색==========");
			for(Info i : list) {
				System.out.println(cnt++ + "." + i.getSvarNm());
			}
		}
		
		System.out.println(cnt + "." + "뒤로가기");
		
		
		ResConInfo(routeNm, gudclssnm, list);
		
	}
	// 상세보기 및 즐겨찾기
	public void ResConInfo(String routeNm, String gudclssnm,List<Info> list) {
		System.out.print("휴게소를 선택해주세요 : ");
		int num = sc.nextInt();
		if(num == list.size() + 1) {
			upDownfind(routeNm);
		}
		
		if(num > list.size() + 1) {
			System.out.println("잘못 누르셨습니다. 메인메뉴 돌아갑니다.");
			Homemenu();
		}
		
		// 상세보기 시작!
		String ra = list.get(num - 1).getSvarNm();
		System.out.println("▶ " + ra + "의 세부사항");
		ic.printConDetail(ra);
		
		String detail1 = "----------------------------\n"
						+ "1. 즐겨찾기\n";
		
		String detail2 =  "2. 뒤로 가기\n"
						+ "3. 메인으로 돌아가기\n"
						+ "메뉴 번호를 입력해주세요 : ";
		while(true) {
			if(user != null) {
				System.out.print(detail1);
			}
			System.out.print(detail2);
			int choiceNum = sc.nextInt();
			switch(choiceNum) {
			case 1: 
				if(user == null) {
					System.out.print("잘못된 번호입니다. 다시 입력해주세요.");
					return;
				}
				// 즐겨찾기
				bc.Bookmarkinsert(ic.selectPsAndCon(ra).get(0).getSvarCd(), user.getUserKey());
				break;
			case 2:  
				Find(routeNm, gudclssnm);
				break;
			case 3: 
				Homemenu();
				break;
			default : 
				System.out.print("잘못된 번호입니다. 다시 입력해주세요.");
				return;
			}
		}
		
	}
	//=========================================================================	
	// 지역검색
	public void selectArea() {
		System.out.println("\n----------------------------");
		System.out.println("1. 수도권 지역내 검색");
		System.out.println("2. 충청권 지역내 검색");
		System.out.println("3. 경상권 지역내 검색");
		System.out.println("4. 강원권 지역내 검색");
		System.out.println("5. 전라권 지역내 검색");
		System.out.println("6. 뒤로가기\n");
		System.out.print("메뉴 번호를 입력해주세요 : ");
		
		while(true) {
			List<String> list = null;
			int areaNum = sc.nextInt();
			switch(areaNum) {
				case 1: 
					list = ic.selectByAvar("수도권");
					printInfo(list);
					printCon("수도권");
					break;
				case 2: 
					list = ic.selectByAvar("충청권");
					printInfo(list);
					printCon("충청권");
					break;
				case 3: 
					list = ic.selectByAvar("경상권");
					printInfo(list);
					printCon("경상권");
					break;
				case 4: 
					list = ic.selectByAvar("강원권");
					printInfo(list);
					printCon("강원권");
					break;
				case 5: 
					list = ic.selectByAvar("전라권");
					printInfo(list);
					printCon("전라권");
					break;
				case 6: 
					Homemenu();
					break;
				default : 
					System.out.println("잘못된 번호 입니다. 다시 입력해주세요 : ");
			}
		}
	}
	
	// 세부사항 보기 및 즐겨찾기
	public void printCon(String areaNm) {
		
		System.out.print("메뉴 번호를 입력해주세요 : ");
		int raNum = sc.nextInt() - 1;
		String ra = ic.selectByAvar(areaNm).set(raNum, null);
		System.out.println("▶ " + ra + "의 세부사항");
		ic.printConDetail(ra);
		
		String detail1 = "----------------------------\n"
						+ "1. 즐겨찾기\n";
		
		String detail2 =  "2. 뒤로 가기\n"
						+ "3. 메인으로 돌아가기\n"
						+ "메뉴 번호를 입력해주세요 : ";
		while(true) {
			if(user != null) {
				System.out.print(detail1);
			}
			System.out.print(detail2);
			int choiceNum = sc.nextInt();
			switch(choiceNum) {
			case 1: 
				if(user == null) {
					System.out.print("잘못된 번호입니다. 다시 입력해주세요.");
					return;
				}
				bc.Bookmarkinsert(ic.selectPsAndCon(ra).get(0).getSvarCd(), user.getUserKey());
				break;
			case 2:  
				selectArea();
				break;
			case 3: 
				Homemenu();
				break;
			default : 
				System.out.print("잘못된 번호입니다. 다시 입력해주세요.");
				return;
			}
			
		}
	}
	
	// 리스트 보여주기
	private void printInfo(List<String> list) {
//		System.out.println("----- 조회건 수 총 " + list.size() + "건 -----");
		int num = 1;
		for(String restN : list) {
			System.out.println(num++ + "." + restN.toString());
		}
	}
	
	//==========================================================================
	// 편의시설명 검색
	public void conFind() {
		System.out.println("\n------편의시설 검색------");
		
		System.out.print("검색할 키워드를 입력해주세요 : ");
		String psname = sc.next();
		
		List<String> reslist = cc.getInfoname(psname);
		int cnt = 1;
		
		for(String resName : reslist) {
			System.out.println(cnt++  + ". " + resName);
		}
		System.out.println(cnt + ". " + "뒤로가기");
		
		System.out.print("휴게소 번호를 선택해주세요: ");
		int num = sc.nextInt();
		
		if(num == reslist.size() + 1) {
			conFind();
		}
		
		conResFind(reslist.get(num - 1), psname);
		
	}
	
	// 편의시설 세부사항 보기 및 즐겨찾기
	public void conResFind(String resName, String psname) {
		Convenient c = cc.getInfoCon(resName, psname);
		
		System.out.println(resName + "의 " + psname + " 세부정보 출력");
		System.out.println(c.toStringConvenient2());
		
		System.out.println();
		String detail1 = "----------------------------\n"
				+ "1. 즐겨찾기\n";

		String detail2 =  "2. 뒤로 가기\n"
						+ "3. 메인으로 돌아가기\n"
						+ "메뉴 번호를 입력해주세요 : ";
		while(true) {
			if(user != null) {
				System.out.print(detail1);
			}
			System.out.print(detail2);
			int choiceNum = sc.nextInt();
			switch(choiceNum) {
			case 1: 
				if(user == null) {
					System.out.print("잘못된 번호입니다. 메인메뉴로 돌아갑니다.");
					return;
				}
				bc.Bookmarkinsert(ic.selectPsAndCon(resName).get(0).getSvarCd(), user.getUserKey());
				break;
			case 2:  
				conFind();
				break;
			case 3: 
				Homemenu();
				break;
			default : 
				System.out.print("잘못된 번호입니다. 다시 입력해주세요.");
				return;
			}
		}
	}
	//==========================================================================
	// 즐겨찾기 한 휴게소 리스트
	public void Bookmarklist() {
		int cnt = 1;
		System.out.println("\n========="+ user.getUserNm() +"님 즐겨찾기 리스트===========");
		List<Info>list = bc.BookmarkselectAll(user.getUserKey());
		
		if(list.size() == 0) {
			System.out.println("리스트가 없습니다! 메인메뉴로 돌아갑니다.");
			Homemenu();
		}
		
		
		for(Info i : list) {
			System.out.println(cnt++ + "."+ i.getSvarNm());
		}
		
		
		System.out.print("휴게소 번호를 선택해주세요 : ");
		int sel = sc.nextInt();
		
		if(sel >= list.size() + 1 || sel < 1) {
			System.out.println("잘못입력하셨습니다. 메인메뉴로 돌아갑니다");
			Homemenu();
		}
		
		Info i = list.get(sel - 1);
		
		bookmarkliset2(i);
		
				
	}
	
	//즐겨찾기 한 휴게소별 기능
	public void bookmarkliset2(Info i) {
		System.out.println("\n==================================");
		System.out.println("1." + i.getSvarNm() + "휴게소 음식보기");
		System.out.println("2." + i.getSvarNm() + "휴게소 편의시설보기");
		System.out.println("3." + i.getSvarNm() + "휴게소 정보보기");
		System.out.println("4. 메인메뉴로");
		
		System.out.print("원하는 메뉴를 선택해주세요 : ");
		int sel = sc.nextInt();
		
		switch (sel) {
			case 1:
				//휴게소 음식보기
				bookmarkfood(i);
				break;
			case 2:
				//휴게소 편의시설보기
				bookmarkCon(i);
				break;
			case 3:
				//휴게소 정보보기
				bookmarkInfo(i);
				break;
			case 4:
				//메인메뉴로
				Homemenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
	}
	
	private void bookmarkInfo(Info i) {
		//휴게소 정보보기
		System.out.println("\n=========" + i.getSvarNm() + "휴게소 정보" + "=========");
		System.out.println("1.휴게소이름 : " + i.getSvarNm());
		System.out.println("2.휴게소주소 : " + i.getSvarAddr());
		System.out.println("3.노선명 : " + i.getRouteNm());
		System.out.println("4.상하행 : " + i.getGudClssNm());
		System.out.println("5.전화번호 : " + i.getRprsTelNo());
	}

	private void bookmarkCon(Info i) {
		//휴게소 편의시설보기
		int cnt = 1;
		
		List<String> list = bc.BookmarkInfoConvenient(user, i);
		System.out.println("=========" + i.getSvarNm() + "휴게소 편의시설 목록" + "=========");
		for(String str : list) {
			System.out.println(cnt++ + "." + str);
		}
		
	}

	private void bookmarkfood(Info i) {
		//휴게소 음식보기
		int cnt = 1;
		
		List<String> list = bc.BookmarkInfoFoodname(user, i);
		System.out.println("=========" + i.getSvarNm() + "휴게소 음식 목록" + "=========");
		for(String str : list) {
			System.out.println(cnt++ + "." + str);
		}
		
	}
	// ========================================================================
	// 음식 메인 메뉴
	public void FoodMenu() {

		String foodmenu = "\n1. 휴게소별 음식 검색\n2. 음식명 검색\n3. 뒤로가기\n메뉴 번호를 입력해주세요 : ";
		String input = null;
		
		while (true) {
			System.out.println("========= 음식 메인 메뉴 =========");
			System.out.print(foodmenu);
			int num = sc.nextInt();

			switch (num) {
			case 1:
				RestareaFoodMenu(input);
				break; // 현주
			case 2:
				selectfoodname();
				break; // 경섭
			case 3:
				Homemenu();
				break; // 뒤로가기
			default:
				System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 휴게소별 음식검색 선택시
	public void RestareaFoodMenu(String input) {
		if(input == null) {
			System.out.print("휴게소 이름을 입력해주세요 : ");
			input = sc.next();
		}
		
		String menu = "\n1. 가격별 메뉴 검색\n2. 프리미엄 메뉴 검색\n3. 추천 메뉴 검색\n" + "4. 계절 메뉴 검색\n5. 뒤로가기\n메뉴 번호를 입력해주세요 : ";

		while (true) {
			System.out.println("========= 음식 세부 검색 =========");
			System.out.print(menu);
			int num = sc.nextInt();

			switch (num) {
			case 1:
				FoodpriceMenu(input);
				break;
			case 2:
				selectPremium(input);
				break;
			case 3:
				selectRecommend(input);
				break;
			case 4:
				Season(input);
				break;
			case 5:
				FoodMenu();
				break; // 뒤로가기
			default:
				System.out.print("잘못 입력하셨습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 가격별 메뉴 선택
	public void FoodpriceMenu(String input) {
		String menu = "\n1. 6000원 미만\n2. 6000원 이상 8000원 미만\n3. 8000원 이상 10000원 미만\n"
				+ "4. 10000원 이상\n5. 뒤로가기\n메뉴 번호를 입력해주세요 : ";

		while (true) {
			System.out.println("========= 가격별 메뉴 검색 =========");
			System.out.print(menu);
			int num = sc.nextInt();

			switch (num) {
			case 1:
				selectPrice1(input);
				break;
			case 2:
				selectPrice2(input);
				break;
			case 3:
				selectPrice3(input);
				break;
			case 4:
				selectPrice4(input);
				break;
			case 5:
				RestareaFoodMenu(input);
				break; // 뒤로가기
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 6000원 미만 메뉴 서울만남(부산)휴게소
	public void selectPrice1(String input) {
		System.out.println("\n========= 6000원 미만 메뉴 검색 =========");

		while (true) {

			for (Food f : fc.selectPrice1(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}

			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				FoodpriceMenu(input);
				break;
			case 2:
				FoodMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
		}
	}

	// 6000원 이상 8000원 이하 메뉴
	public void selectPrice2(String input) {
		System.out.println("\n========= 6000원 이상 8000원 미만 메뉴 검색 =========");

		while (true) {
			
			for (Food f : fc.selectPrice2(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}

			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				FoodpriceMenu(input);
				break;
			case 2:
				FoodMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
		}
	}

	// 8000원 이상 10000원 미만 메뉴
	public void selectPrice3(String input) {
		System.out.println("\n========= 8000원 이상 10000원 미만 메뉴 검색 =========");

		while (true) {
			
			for (Food f : fc.selectPrice3(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				FoodpriceMenu(input);
				break;
			case 2:
				FoodMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
		}
	}

	// 10000원 이상 메뉴 이상
	public void selectPrice4(String input) {
		System.out.println("\n========= 10000원 이상 메뉴 검색 =========");
	
		while (true) {
			
			for (Food f : fc.selectPrice4(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				FoodpriceMenu(input);
				break;
			case 2:
				FoodMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
		}
	}

	// 프리미엄 메뉴 검색 서울만남(부산)휴게소 평창(강릉)휴게소
	public void selectPremium(String input) {
		System.out.println("\n========= 프리미엄 메뉴 검색 =========");

		while (true) {
		
			if(fc.selectPremium(input).size() == 0) {
				System.out.println("프리미엄 메뉴가 없습니다.");
			}
			
			for (Food f : fc.selectPremium(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				RestareaFoodMenu(input);
				break;
			case 2:
				FoodMenu();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
		}
	}

	// 추천 메뉴 검색
	public void selectRecommend(String input) {
		System.out.println("\n========= 추천 메뉴 검색 =========");

		while (true) {
			
			if(fc.selectRecommend(input).size() == 0) {
				System.out.println("추천 메뉴가 없습니다.");
			}
			
			for (Food f : fc.selectRecommend(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				RestareaFoodMenu(input);
				return;
			case 2:
				FoodMenu();
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	// 계절메뉴
	public void Season(String input) {
		String season = "\n1. 여름메뉴\n2. 겨울메뉴\n3. 뒤로가기\n메뉴 번호를 입력해주세요 : ";

		while (true) {
			System.out.println("========= 계절 메뉴 =========");
			System.out.print(season);
			int num = sc.nextInt();

			switch (num) {
			case 1:
				selectSeason2(input);
				break; // 여름
			case 2:
				selectSeason1(input);
				break; // 겨울
			case 3:
				RestareaFoodMenu(input);
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 계절메뉴 - 여름
	public void selectSeason2(String input) {
		System.out.println("\n========= 계절 메뉴 여름 =========");
		
		while (true) {
			
			if(fc.selectSeason1(input).size() == 0) {
				System.out.println("여름 메뉴가 없습니다.");
			}
			
			for (Food f : fc.selectSeason2(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				Season(input);
				return;
			case 2:
				FoodMenu();
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}

	// 계절메뉴 - 겨울
	public void selectSeason1(String input) {
		System.out.println("\n========= 계절 메뉴 겨울 =========");

		while (true) {
			
			if(fc.selectSeason1(input).size() == 0) {
				System.out.println("겨울 메뉴가 없습니다.");
			}
				
			for (Food f : fc.selectSeason1(input)) {
				System.out.println("• " + f.getFoodNm() + " 가격: " + f.getFoodCost());
			}
			
			System.out.print("\n1. 뒤로가기\n2. 메인메뉴로 돌아가기\n메뉴번호를 입력해주세요 : ");
			int inputnum = sc.nextInt();

			switch (inputnum) {
			case 1:
				Season(input);
				return;
			case 2:
				FoodMenu();
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}
	}
	// =======================================================================
	// 음식명검색
	public void selectfoodname() {
		System.out.println("\n========= 음식명 검색 =========");
		System.out.print("음식명 키워드를 입력해주세요 : ");

		while (true) {
			String food = sc.next();
			List<String> routelist = fc.findFoodRouteNm(food);

			selectroute(food, routelist);

		}
	}
	
	//음식명으로 검색하면 노선나오고
	public void selectroute(String food, List<String> routelist) {
		int count = 1;

		System.out.println("\n========= 노선 선택 =========");
		for (String r : routelist) {
			System.out.println(count++ + "." + r.toString());
		}
		System.out.println(count + "." + "뒤로가기");

		System.out.print("선택할 노선 번호를 입력해주세요: ");
		int route = sc.nextInt();

		if (route == routelist.size() + 1) {
			selectfoodname();
		}

		List<String> reslist = fc.findFoodRa(food, routelist.get(route - 1));
		// 리스트 인덱스는 0부터 시작하니까

		selectrest(food, routelist, reslist);

	}

	//노선선택하면 휴게소나오고
	public void selectrest(String food, List<String> routelist, List<String> reslist) {
		int count = 1;

		System.out.println("\n========= " + food + " 보유 휴게소 목록 =========");
		for (String r : reslist) {
			System.out.println(count++ + "." + r.toString());
		}
		System.out.println(count + "." + "뒤로가기");
		System.out.print("선택할 휴게소 번호를 입력해주세요: ");
		int res = sc.nextInt();

		if (res == reslist.size() + 1) {
			selectroute(food, routelist);
		}

		List<Food> foodlist = fc.findFood(reslist.get(res - 1), food);

		foodDec(food, reslist.get(res - 1), foodlist);
	}

	//휴게소 선택하면 음식 나오고!
	  public void foodDec(String food, String svarnm, List<Food> foodlist) {

        System.out.println("\n========= " + svarnm + "의 " + food + " 관련 음식 목록입니다. =========");
        for (Food f : foodlist) {
            System.out.println("• " + f.getFoodNm() + " / 가격: " + f.getFoodCost());
        }
        System.out.println("\n" + 0 + "." + "메인메뉴로");
        System.out.println(1 + "." + "종료");
        System.out.print("메뉴 번호를 입력해주세요 : ");
       
        int sel = sc.nextInt();

        switch (sel) {
        case 0:
            Homemenu();
            break;
        case 1:
            System.out.println("종료하겠습니다");
            System.exit(0);
            break;
        default:
            System.out.println("\n잘못 입력하셨습니다. 음식명 검색 메뉴로 돌아갑니다.");
            break;
        }
	 }
 //=============================================================================
}
