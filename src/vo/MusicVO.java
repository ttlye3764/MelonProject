package vo;

import java.util.Date;

public class MusicVO {
	private String m_name;	//노래제목
	private String m_singer; //가수
	private String m_lyrics; //작사
	private String m_composition; //작곡
	private int m_count; //카운트
	private String m_genre; //장르
	private String m_date; //날짜
	private int m_number; //노래 등록 번호
	
	
	
	
	public String getM_lyrics() {
		return m_lyrics;
	}
	public void setM_lyrics(String m_lyrics) {
		this.m_lyrics = m_lyrics;
	}
	public String getM_composition() {
		return m_composition;
	}
	public void setM_composition(String m_composition) {
		this.m_composition = m_composition;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	
	public String getM_singer() {
		return m_singer;
	}
	public void setM_singer(String m_singer) {
		this.m_singer = m_singer;
	}
	
	public int getM_count() {
		return m_count;
	}
	public void setM_count(int m_count) {
		this.m_count = m_count;
	}
	public String getM_genre() {
		return m_genre;
	}
	public void setM_genre(String m_genre) {
		this.m_genre = m_genre;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public int getM_number() {
		return m_number;
	}
	public void setM_number(int m_number) {
		this.m_number = m_number;
	}
	@Override
	public String toString() {
		return "MusicVO [m_name=" + m_name + ", m_singer=" + m_singer
				+ ", m_lyrics=" + m_lyrics + ", m_composition=" + m_composition
				+ ", m_count=" + m_count + ", m_genre=" + m_genre + ", m_date="
				+ m_date + ", m_number=" + m_number + "]";
	}
	
	
}
