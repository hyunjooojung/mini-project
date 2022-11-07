package com.restarea.controller;

import java.util.List;

import com.restarea.api.ConvenientOpenApi;
import com.restarea.model.vo.Convenient;
import com.restarea.service.ConvenientService;

public class ConvenientController {

	private ConvenientService convenientService = new ConvenientService();
	
	public void initConvenient() {
		
		List<Convenient> list = ConvenientOpenApi.callConvenientByJSON();
		
		while(true) {
		
			if(list == null || list.isEmpty()) {
				break;
			}
			
			for(Convenient c : list) {
				System.out.println(c);
				convenientService.insert(c);
			}
			list = null;
		}
	}
	//편의시설 검색 경정비를 가지고 있는 휴게소
	public List<String> getInfoname(String psname){
		return convenientService.selectInfo(psname);
	}
	
	// 입력받은 휴게소 + 편의시설 세부정보
	public Convenient getInfoCon(String svarnm,String psname){
		return convenientService.selectInfoConvenient(svarnm,psname);
	}
	
	public static void main(String[] args) {
		ConvenientController cc = new ConvenientController();
		//cc.initConvenient();
	}
}
