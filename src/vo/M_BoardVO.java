package vo;

import java.util.Date;

public class M_BoardVO {

	private String m_b_name;    //게시글 제목
	
	private String u_id; 	    //게시자 이름
	
	private String m_b_content; // 게시글 내용
	
	private int m_b_number=1;     //게시글 번호
	
	private String m_b_date;    //게시글 작성 일자
	
 	private int m_number;       //추천 노래 번호 
 	
 	
	public String getM_b_name() {
		return m_b_name;
	}
	public void setM_b_name(String m_b_name) {
		this.m_b_name = m_b_name;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getM_b_content() {
		return m_b_content;
	}
	public void setM_b_content(String m_b_content) {
		this.m_b_content = m_b_content;
	}
	public int getM_b_number() {
		return m_b_number;
	}
	public void setM_b_number(int m_b_number) {
		this.m_b_number = m_b_number;
	}
	public String getM_b_date() {
		return m_b_date;
	}
	public void setM_b_date(String m_b_date) {
		this.m_b_date = m_b_date;
	}
	public int getM_number() {
		return m_number;
	}
	public void setM_number(int m_number) {
		this.m_number = m_number;
	}
	
 	
 	
 	
}
