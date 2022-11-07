package com.restarea.model.vo;

public class Convenient {
	
	private String psCode; // 편의시설 코드
	private String stdRestCd; // 휴게소 코드
	private String psName; // 편의시설 명칭
	private String psDesc; // 편의시설 설명
	private String stime; // 시작 시간
	private String etime; // 종료 시간
	
	public Convenient() {
		super();		
	}

	public Convenient(String psCode, String stdRestCd, String psName, String psDesc, String stime, String etime) {
		super();
		this.psCode = psCode;
		this.stdRestCd = stdRestCd;
		this.psName = psName;
		this.psDesc = psDesc;
		this.stime = stime;
		this.etime = etime;
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

	@Override
	public String toString() {
		return "Convenient [psCode=" + psCode + ", stdRestCd=" + stdRestCd + ", psName=" + psName + ", psDesc=" + psDesc
				+ ", stime=" + stime + ", etime=" + etime + "]";
	}
	
	public String toStringConvenient() {
			return psName + " : " + psDesc + " / " + "시작시간 : " +  stime + " / " + "종료시간 : " + etime + "/n";
	}
	
	public String toStringConvenient2() {
		return 	psName + " 상세정보입니다.\n " 
				+ "상세설명 : " + psDesc + " \n " 
				+ "시작시간 : " +  stime + " \n " 
				+ "종료시간 : " + etime + "\n";
	}
	
	
}
