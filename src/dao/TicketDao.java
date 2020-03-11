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
				database.tb_ticket.add(ticket);
				//check = false;
				break;
			}							
		}
		if(check){
			database.tb_ticket.add(ticket);
		}
	}
	
	
	
	public void u_namecompare(ticketVO ticket) {
		for (int i = 0; i<database.tb_ticket.size(); i++){
			if((database.tb_ticket.get(i).getU_name()).equals(ticket.getU_name())){
				
			}
			}
		}
	//03.10
	public ticketVO selectticket(HashMap<String, Object> param) {
		ticketVO rtnTicket = null;
		for (int i = 0; i < database.tb_ticket.size(); i++) {
			ticketVO ticket = database.tb_ticket.get(i);
			boolean flag  = true;
			for(String key : param.keySet()){
				Object value = param.get(key);
				if(key.equals("NAME")){
					if(!ticket.getU_name().equals(value)) flag = false;
				}else if(key.equals("TICKET")){
					if((Integer)ticket.getT_number() != value) flag = false;
				}else if(key.equals("DATE")){
					if(!ticket.getT_buy_date().equals(value)) flag = false;
				}
			}
			 if(flag) rtnTicket = ticket;
		}
	return rtnTicket;
}
	
	public ArrayList<ticketVO> selectticketList() {
		
		return database.tb_ticket;
	}
}
