package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.UserVO;
import vo.TicketVO;

public class TicketDao {
	private static TicketDao instance;
	private TicketDao() {
	}
	
	public static TicketDao getInstance() {
		if (instance == null) {
			instance = new TicketDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	
	public void insertTicket(TicketVO ticket){
		database.tb_ticket.add(ticket);
	}
	
	
	public ArrayList<TicketVO> ticketList(){
		return database.tb_ticket;
	}
}
