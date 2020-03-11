package vo;

import java.util.Date;

public class ticketVO {

	private int t_number; // 티켓 수량
	private Date t_buy_date;  // 티켓 산 날짜
	private String u_name; // 사용자 이름
	
	
	public int getT_number() {
		return t_number;
	}
	public void setT_number(int t_number) {
		this.t_number = t_number;
	}
	public Date getT_buy_date() {
		return t_buy_date;
	}
	public void setT_buy_date(Date t_buy_date) {
		this.t_buy_date = t_buy_date;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	
	
	
}
