package vo;

import java.util.Date;

public class O_BoardVO {
	private String o_b_name;  //공지사항 제목
	private String ad_name;   //관리자 이름
	private String o_b_content; //공지사항 내용
	private int o_b_number;		//공지사항 번호
	private String o_b_date;    //공지사항 작성일자
	public String getO_b_name() {
		return o_b_name;
	}
	public void setO_b_name(String o_b_name) {
		this.o_b_name = o_b_name;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getO_b_content() {
		return o_b_content;
	}
	public void setO_b_content(String o_b_content) {
		this.o_b_content = o_b_content;
	}
	public int getO_b_number() {
		return o_b_number;
	}
	public void setO_b_number(int o_b_number) {
		this.o_b_number = o_b_number;
	}
	public String getO_b_date() {
		return o_b_date;
	}
	public void setO_b_date(String o_b_date) {
		this.o_b_date = o_b_date;
	}
	
		
	
}
