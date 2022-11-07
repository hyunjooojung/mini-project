package com.restarea.controller;

import java.util.ArrayList;
import java.util.List;

import com.restarea.api.InfoOpenApiManager;
import com.restarea.model.vo.Info;
import com.restarea.model.vo.InfoAndCon;
import com.restarea.service.InfoService;
import com.restarea.service.UserService;

public class InfoController {
	private InfoService infoService = new InfoService();
	private List<InfoAndCon> iac = new ArrayList<InfoAndCon>();

	public void initInfo() {
		
		List<Info> list = InfoOpenApiManager.callHiwaySvarInfoListByJson();
		
		while(true) {
			
			if(list == null || list.isEmpty()) {
				break;
			}
			
			for(Info i : list) {
				System.out.println(i);
				infoService.insert(i);
			}
			list = null;
		}
	}
	
	public List<String> selectByRoutelist() {
		return infoService.selectByRoutelist();
	}
	
	// -- 노선별 검색
	public List<Info> selectByRoute(String routenm) {
		return infoService.selectByRoute(routenm);
	}
	
	// -- 노선별 검색 + 상하행검색
	public List<Info> selectRouAndGue(String routenm, String gudclssnm) {
		return infoService.selectRouAndGue(routenm, gudclssnm);
	}
	
	// -- 선택 휴게소가 보유한 편의시설 리스트
	public List<String> selectConvenient(String svarnm) {
		return infoService.selectConvenient(svarnm);
	}
	
	// -- 휴게소 세부사항 ★편의시설★
	public List<InfoAndCon> selectPsAndCon(String svarnm, String psname) {
		return infoService.selectPsAndCon2(svarnm, psname);
	}
	
	// -- 지역별 검색
	public List<String> selectByAvar(String avaraddr) {
		return infoService.selectByAvar(avaraddr);
	}
	
	// -- 휴게소 세부사항
	public List<InfoAndCon> selectPsAndCon(String svarnm) {
		List<InfoAndCon> con = infoService.selectPsAndCon(svarnm);
		con.get(0).getPsName();
		iac = con;
		return con;
	}
	
	// -- 휴게소 세부사항 출력
	public void printConDetail(String svarnm) {
		iac = selectPsAndCon(svarnm);
		for(int i = 0; i<iac.size(); i++) {
			System.out.println("  ▷ " + iac.get(i).getPsName() + " : " +iac.get(i).getPsDesc());
			System.out.println("    시작 시간 : " + iac.get(i).getStime() + " / 종료 시간 : " + iac.get(i).getEtime());
		}	
	}

	public static void main(String[] args) {
		InfoController ic = new InfoController();
		//ic.initInfo();
	}
}
