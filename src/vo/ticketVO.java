package vo;

import java.util.Date;

public class TicketVO {

	String u_Id;
	int ticket_M_Amount;
	String ticket_Buy_Date;
	
	public String getTicket_Buy_Date() {
		return ticket_Buy_Date;
	}
	public void setTicket_Buy_Date(String ticket_Buy_Date) {
		this.ticket_Buy_Date = ticket_Buy_Date;
	}
	int ticket_Price;
	
	public int getTicket_M_Amount() {
		return ticket_M_Amount;
	}
	public void setTicket_M_Amount(int ticket_M_Amount) {
		this.ticket_M_Amount = ticket_M_Amount;
	}
	public int getTicket_Price() {
		return ticket_Price;
	}
	public void setTicket_Price(int ticket_Price) {
		this.ticket_Price = ticket_Price;
	}
	public String getU_Id() {
		return u_Id;
	}
	public void setU_Id(String u_Id) {
		this.u_Id = u_Id;
	}
	
	
	
}
