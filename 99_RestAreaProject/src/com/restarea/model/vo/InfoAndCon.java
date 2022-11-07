package com.restarea.model.vo;

public class InfoAndCon {
	private String psCode; // 편의시설 코드
	private String stdRestCd; // 휴게소 코드
	private String psName; // 편의시설 명칭
	private String psDesc; // 편의시설 설명
	private String stime; // 시작 시간
	private String etime; // 종료 시간
	private String svarCd;		// 휴게소 코드
	private String svarNm;		// 휴게소 이름
	private String svarAddr;	// 휴게소 주소
	private String routeNm;		// 노선명
	private String avarAddr;	// 지역명
	private String gudClssNm;	// 상하행구분명
	private String rprsTelNo;	// 대표전화번호
	
	public InfoAndCon() {
		super();
	}

	public InfoAndCon(String psCode, String stdRestCd, String psName, String psDesc, String stime, String etime,
			String svarCd, String svarNm, String svarAddr, String routeNm, String avarAddr, String gudClssNm,
			String rprsTelNo) {
		super();
		this.psCode = psCode;
		this.stdRestCd = stdRestCd;
		this.psName = psName;
		this.psDesc = psDesc;
		this.stime = stime;
		this.etime = etime;
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
		return "InfoAndCon [psCode=" + psCode + ", stdRestCd=" + stdRestCd + ", psName=" + psName + ", psDesc=" + psDesc
				+ ", stime=" + stime + ", etime=" + etime + ", svarCd=" + svarCd + ", svarNm=" + svarNm + ", svarAddr="
				+ svarAddr + ", routeNm=" + routeNm + ", avarAddr=" + avarAddr + ", gudClssNm=" + gudClssNm
				+ ", rprsTelNo=" + rprsTelNo + "]";
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getStdRestCd() {
		return stdRestCd;
	}

	public void setStdRestCd(String stdRestCd) {
		this.stdRestCd = stdRestCd;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getPsDesc() {
		return psDesc;
	}

	public void setPsDesc(String psDesc) {
		this.psDesc = psDesc;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
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
