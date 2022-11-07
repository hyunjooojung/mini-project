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
import org.w3c.dom.Element;

import com.restarea.model.vo.Convenient;

public class ConvenientOpenApi {
	// 편의시설 API 
	
	public static void main(String[] args) {
		List<Convenient> list = callConvenientByJSON();
		
		for(Convenient c : list) {
			System.out.println(c.toString());
		}
	}

	public static  final String key = "7717351437";
	public static  final String CONVENIENT_JSON_URL = "https://data.ex.co.kr/openapi/restinfo/restConvList";

	
	public static List<Convenient> callConvenientByJSON() {
		int pageNum = 1;

		List<Convenient> list = new ArrayList<Convenient>();
		
		try {
			loop:
				
			while (true) {
				// 1. URL을 가공하는 코드
				StringBuilder urlBuilder = new StringBuilder(CONVENIENT_JSON_URL);
				urlBuilder.append("?" + "key=" + key);
				urlBuilder.append("&" + "type=json");
				urlBuilder.append("&" + "numOfRows=" + "10");
				urlBuilder.append("&" + "pageNo=" + pageNum);

				// 2. URL을 HTTP객체를 통해 요청하는 코드
				URL url = new URL(urlBuilder.toString());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				// 확인용 200 나옴
				//System.out.println("Response code: " + conn.getResponseCode());
				
				// 실제 호출하는 부
				int code = conn.getResponseCode();
				if (code < 200 || code >= 300) {
					System.out.println("페이지가 잘못되었습니다.");
					return null;
				}
				
				// 3. 페이지 Parsing부 (해석부)
				InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(br);
				JSONObject jsonmain = (JSONObject) obj;
				JSONArray jsonArray = (JSONArray) jsonmain.get("list");
				
				if (jsonArray.isEmpty()) {
					System.out.println("끝");
					return list;
				}
				
				if (jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
//						System.out.println("휴게소 코드 : " + (String) jsonObj.get("stdRestCd"));
//						System.out.println("편의시설 키: " + (String) jsonObj.get("psCode"));
//						System.out.println("편의시설 명칭 : " + (String) jsonObj.get("psName"));
//						System.out.println(("편의시설 설명 : " + (String) jsonObj.get("psDesc")).replace("null", "상세설명 없음"));
//						System.out.println("시작시간 : " + (String) jsonObj.get("stime"));
//						System.out.println("종료시간 : " + (String) jsonObj.get("etime"));
//						System.out.println("==================================================");
						
						String etime = (String) jsonObj.get("etime");
						String psCode = (String) jsonObj.get("psCode");
						String psDesc = getPsDesc(jsonObj, "psDesc");
						String psName = (String) jsonObj.get("psName");
						String stdRestCd = (String) jsonObj.get("stdRestCd");
						String stime = (String) jsonObj.get("stime");
						
						Convenient c = new Convenient(psCode, stdRestCd, psName, psDesc, stime, etime);
						
						list.add(c);
					}
				}
				System.out.println("페이지 : " + pageNum++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private static String getPsDesc(JSONObject jsonObj, String tagName) {
		if(((String)jsonObj.get(tagName)) == null) {
			return "상세설명 없음";
		}
		return (String) jsonObj.get(tagName);
	}
}


