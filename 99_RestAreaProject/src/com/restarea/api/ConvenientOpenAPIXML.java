package com.restarea.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.restarea.model.vo.Convenient;

public class ConvenientOpenAPIXML {
	
public static void main(String[] args) {
		
		List<Convenient> list = callConvenientByJSON();
		
		System.out.println("===================================");
		for(Convenient c : list) {
			System.out.println(c.toString());
		}
	}	
	
	// 편의시설 API 
	public static final String key = "7717351437";
	public static final String CONVENIENT_XML_URL = "https://data.ex.co.kr/openapi/restinfo/restConvList";
	public static int pageNum = 11;
	
		public static List<Convenient> callConvenientByJSON(){
		
			
			List<Convenient> list = new ArrayList<>();
			
			while(true) {
				StringBuilder urlBuffer = new StringBuilder(CONVENIENT_XML_URL);
				urlBuffer.append("?" + "key=" + key);
				urlBuffer.append("&" + "type=xml");
				urlBuffer.append("&" + "numOfRows=" + "10");
				urlBuffer.append("&" + "pageNo=" + pageNum);
				
				System.out.println(urlBuffer);
				
				try {
					URL url = new URL(urlBuffer.toString());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/xml");
					int code = conn.getResponseCode(); // 실제호출하는부
					
					System.out.println("Response code: " + code);
					
					if(code < 200 || code > 300) {
						System.out.println("페이지가 잘못되었습니다.");
						return null;
					}
					
					// 3. 페이지 Parsing(해석)
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					Document doc = db.parse(conn.getInputStream()); // xml 부를 파싱하여 객체화
					
					doc.getDocumentElement().normalize();
					
					System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
					System.out.println("======================================================");
					
						
					NodeList nList = doc.getElementsByTagName("list");
					for(int i = 0; i < nList.getLength(); i++) {
						Node node = nList.item(i);
						System.out.println("\nCurrent Element : " + node.getNodeName());
						if(node.getNodeType() == Node.ELEMENT_NODE) {
							try {
								Element eElement = (Element) node;
								
								System.out.println("etime : " + getStrData(eElement, "etime"));
								System.out.println("psCode : " + getStrData(eElement, "psCode"));
								System.out.println("psDesc : " + getStrData(eElement, "psDesc"));
								System.out.println("psName : " + getStrData(eElement, "psName"));
								System.out.println("stdRestCd : " + getStrData(eElement, "stdRestCd"));
								System.out.println("stime : " + getStrData(eElement, "stime"));
								
								String etime = getStrData(eElement, "etime");
								String psCode = getStrData(eElement, "psCode");
								String psDesc = getStrData(eElement, "psDesc");
								String psName = getStrData(eElement, "psName");
								String stdRestCd = getStrData(eElement, "stdRestCd");
								String stime = getStrData(eElement, "stime");
									
								Convenient con = new Convenient(psCode, stdRestCd, psName, psDesc, stime, etime);
								
								list.add(con);
								System.out.println("페이지 : " + pageNum++);
							} catch (Exception e) {
								System.out.println("데이터가 잘못되었습니다.");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}	
			}
			
		
	private static String getStrData(Element eElement, String tagName){
		try {
			return eElement.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Exception e) {
			return "-";
		}
	}
	
}
