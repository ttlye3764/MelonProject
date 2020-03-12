package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.MusicVO;
import vo.TicketVO;
import vo.UserVO;
import dao.MusicDao;

import dao.TicketDao;
import data.Database;
import data.Session;

public class TicketService {

	private static TicketService instance;

	TicketService() {
	}

	public static TicketService getinstace() {
		if (instance == null) {
			instance = new TicketService();
		}
		return instance;
	}
	MusicDao musicDao = MusicDao.getInstance();
	TicketDao ticketDao = TicketDao.getInstance();

	Database database = Database.getInstance();

	public void buyTicket() {
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일 hh시mm분");
		Date time = new Date();
		
		TicketVO ticketVO = new TicketVO();
		Database database = Database.getInstance();

		ticketVO.setU_Id(Session.LoginUser.getU_id());
		ticketVO.setTicket_M_Amount(20);
		ticketVO.setTicket_Buy_Date(format.format(time));
		
		ticketDao.insertTicket(ticketVO);
	}

	// 티켓 산사람 전체 내역 조회

	public void ticketList() {

		ArrayList<TicketVO> ticketList = ticketDao.ticketList();
		for (int i = 0; i < database.tb_ticket.size(); i++) {
			System.out.println("구매한 사람 : ");
			ticketList.get(i).getU_Id();

		}

	}

	// 해당 유저가 산 티켓 조회

	public int userBuyTicket(String name) {
		ArrayList<TicketVO> ticketList = ticketDao.ticketList();
		int buyTicket = 0;
		int totalBuyTicket = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).getU_Id().equals(name)) {
				buyTicket++;
			}
		}
		return buyTicket;
	}
   // 유저가 보유중인 곡 들을 수 있는 수량
	public int userTicketAmount(String name) {
		ArrayList<TicketVO> ticketList = ticketDao.ticketList();
		
		int ticketAmount = 0;
		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).getU_Id().equals(name)) {
				ticketAmount += ticketList.get(i).getTicket_M_Amount();
			}
		}
		return ticketAmount;
	}
	
	public String userTicketDate(String name) {
		ArrayList<TicketVO> ticketList = ticketDao.ticketList();
		
		String ticketDate = "";
		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).getU_Id().equals(name)) {
				ticketDate += i+1 + "번째 이용권 결제일 " + ticketList.get(i).getTicket_Buy_Date() + "\n"; 
				
			}
		}
		return ticketDate;
	}

	public void listenMusic(UserVO uservo, MusicVO music) {
		ArrayList<TicketVO> ticketList = ticketDao.ticketList();	
		boolean check = true;
		for (int i = 0; i < ticketList.size(); i++) {
			if(ticketList.get(i).getU_Id().equals(uservo.getU_id())){
				if(ticketList.get(i).getTicket_M_Amount()<1){
					continue;
				}
				else{
				ticketList.get(i).setTicket_M_Amount(ticketList.get(i).getTicket_M_Amount()-1);
				musicDao.insertR_playList(music); // 최근들은노래리스트에 음악 insert 부분
				musicDao.CountPlus(music);
				check = false;
				break;
				}				
			}				
		}
		if(check){
			System.out.println("사용 가능한 이용권이 없습니다.");
		}
	}

}
