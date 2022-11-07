package com.restarea.model.vo;

public class Info {
	private String svarCd;		// 휴게소 코드
	private String svarNm;		// 휴게소 이름
	private String svarAddr;	// 휴게소 주소
	private String routeNm;		// 노선명
	private String avarAddr;	// 지역명
	private String gudClssNm;	// 상하행구분명
	private String rprsTelNo;	// 대표전화번호
	
	public Info() {
		super();
	}

	public Info(String svarCd, String svarNm, String svarAddr, String routeNm, String avarAddr, String gudClssNm, String rprsTelNo) {
		super();
		this.svarCd = svarCd;
		this.svarNm = svarNm;
		this.svarAddr = svarAddr;
		this.routeNm = routeNm;
		this.avarAddr = avarAddr;
		this.gudClssNm = gudClssNm;
		this.rprsTelNo = rprsTelNo;
	}

	@Override
	public String toString() {
		return "Info [svarCd=" + svarCd + ", svarNm=" + svarNm + ", svarAddr=" + svarAddr + ", routeNm=" + routeNm
				+  ", avarAddr=" + avarAddr + ", gudClssNm=" + gudClssNm + ", rprsTelNo=" + rprsTelNo + "]";
	}
	
	public String toStringForConsole() {
		return "휴게소 코드 : " + svarCd + ", 휴게소 이름 : " + svarNm + ", 휴게소 주소 : " + svarAddr + ", 노선명 : " + routeNm
				+ ", 지역명 : " + avarAddr + ", 상하행구분명 : " + gudClssNm + ", 대표전화번호 : " + rprsTelNo;
	}
	
	public String toStringPrintArea() {
		return "svarNm";
	}

	public String getSvarCd() {
		return svarCd;
	}

	public void setSvarCd(String svarCd) {
		this.svarCd = svarCd;
	}

	public String getSvarNm() {
		return svarNm;
	}

	public void setSvarNm(String svarNm) {
		this.svarNm = svarNm;
	}

	public String getSvarAddr() {
		return svarAddr;
	}

	public void setSvarAddr(String svarAddr) {
		this.svarAddr = svarAddr;
	}

	public String getRouteNm() {
		return routeNm;
	}

	public void setRouteNm(String routeNm) {
		this.routeNm = routeNm;
	}

	public String getAvarAddr() {
		return avarAddr;
	}
	
	public void setAvarAddr(String avarAddr) {
		this.avarAddr = avarAddr;
	}
	
	public String getGudClssNm() {
		return gudClssNm;
	}

	public void setGudClssNm(String gudClssNm) {
		this.gudClssNm = gudClssNm;
	}

	public String getRprsTelNo() {
		return rprsTelNo;
	}

	public void setRprsTelNo(String rprsTelNo) {
		this.rprsTelNo = rprsTelNo;
	}

}
