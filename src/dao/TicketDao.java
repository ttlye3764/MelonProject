package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.UserVO;
import vo.ticketVO;

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
	
	public void insertTicket(ticketVO ticket) {
		boolean check = true;
		for (int i= 0; i < database.tb_ticket.size(); i++) {
			if((database.tb_ticket.get(i).getU_name()).equals(ticket.getU_name())){
				System.out.println("");
				check = false;
				break;
			}							
		}
		if(check){
			database.tb_ticket.add(ticket);	
			System.out.println("");
		}

	}
	
	public ticketVO selectticket(HashMap<String, Object> param) {
		ticketVO rtnTicket = null;
		for (int i = 0; i < database.tb_ticket.size(); i++) {
			ticketVO ticket = database.tb_ticket.get(i);
			boolean flag  = true;
			for(String key : param.keySet()){
				Object value = param.get(key);
				if(key.equals("ID")){
					if(!ticket.getU_name().equals(value)) flag = false;
				}else if(key.equals("PASSWORD")){
					if((Integer)ticket.getT_number() != value) flag = false;
				}else if(key.equals("NAME")){
					if(!ticket.getT_buy_date().equals(value)) flag = false;
				}
			}
			 if(flag) rtnTicket = ticket;
		}
	return rtnTicket;
}
	public ArrayList<ticketVO> selectticket() {
		
		return database.tb_ticket;
	}
}
