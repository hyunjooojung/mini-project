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

import com.restarea.model.vo.Info;

public class InfoOpenApiManager {
	public static String key = "0508468213";
	public static String INFO_RA_JSON_URL = "http://data.ex.co.kr/openapi/restinfo/hiwaySvarInfoList";

	public static void main(String[] args) {
		InfoOpenApiManager.callHiwaySvarInfoListByJson();
	}

	public static List<Info> callHiwaySvarInfoListByJson() {
		List<Info> iList = new ArrayList<>(); 
		
		try {
			StringBuilder urlBuilder = new StringBuilder(INFO_RA_JSON_URL);
			urlBuilder.append("?" + "key=" + key);
			urlBuilder.append("&" + "type=json");
			//URL 가공 clear
			URL url = new URL(urlBuilder.toString());
			System.out.println(urlBuilder);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			int code = conn.getResponseCode();
			
			if(code < 200 || code >= 300) {
				System.out.println("페이지가 잘못되었습니다.");
				return null;
			}
			
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			JSONParser jsonParser = new JSONParser();
			JSONObject rootObj = (JSONObject) jsonParser.parse(br);
			JSONArray infoArray = (JSONArray) rootObj.get("list");
			
			for(int i = 0; i < infoArray.size(); i++) {
				JSONObject obj = (JSONObject) infoArray.get(i);
				String svarCd = (String) obj.get("svarCd");
				String svarNm = (String) obj.get("svarNm");
				String svarAddr = (String) obj.get("svarAddr");
				String routeNm = (String) obj.get("routeNm");
				String avarAddr = GetAddString(obj, "svarAddr");
				String gudClssNm = (String) obj.get("gudClssNm");
				String rprsTelNo = (String) obj.get("rprsTelNo");
				
				Info info = new Info(svarCd, svarNm, svarAddr, routeNm, avarAddr, gudClssNm, rprsTelNo);
				iList.add(info);
				
				System.out.println(info.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iList;
	}

	private static String GetAddString(JSONObject rootObj, String svarAddr) {

		String avarAddr = "";
		String addr = (String) rootObj.get(svarAddr);
		String testAddr = addr.split(" ")[0];
		
		switch (testAddr) {
		case "경기도": case "경기": case "서울": case "서울시": case "서울특별시": case "인천": case "인천시": case "인천광역시":
			avarAddr = "수도권";
			break;
		case "대전": case "대전시": case "대전광역시": case "충남": case "충청남도": case "충북": case "충청북도": case "충청도":
			avarAddr = "충청권";
			break;
		case "대구": case "대구시": case "대구광역시": case "울산": case "울산시": case "울산광역시": case "부산": case "부산시": case "부산광역시":
		case "경남": case "경상남도": case "경북": case "경상북도": case "경상도":
			avarAddr = "경상권";
			break;
		case "강원": case "강원도":
			avarAddr = "강원권";
			break;
		case "광주": case "광주시": case "광주광역시": case "전남": case "전라남도": case "전북": case "전라북도": case "전라도":
			avarAddr = "전라권";
			break;
		}
		return avarAddr;
	}
	
	

}




