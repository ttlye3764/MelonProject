package service;

import java.util.ArrayList;
import java.util.Scanner;

import vo.UserVO;
import dao.AdminDao;
import dao.UserDao;
import data.Session;


public class AdminService {
	private static AdminService instance;
	
	private AdminService(){}
	
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}

	UserDao userDao = UserDao.getInstance();
	
	//회원 관리 메소드
	
	//회원 목록
	public void userList() {
		ArrayList<UserVO> userList = userDao.selectUserList();
		TicketService ticketService = TicketService.getinstace();
		int userTicket = 0;
		int userTicketAmount= 0 ;
		
		
		System.out.println("-----------------------------------------------");
		System.out.println("번호\t아이디\t이름\t이용권 구매 횟수\t남은 이용권 수량");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			userTicket = ticketService.userBuyTicket(user.getU_id());
			userTicketAmount = ticketService.userTicketAmount(user.getU_id());
			
			System.out.println(i + 1 + "\t" + user.getU_id() + "\t"
					+ user.getU_name() + "\t" + userTicket + "\t" + userTicketAmount);
			
		}
		System.out.println("------------------------------------------------");
	}
	//회원 삭제
	public void userdelete(){
		Scanner s = new Scanner(System.in);
		AdminDao adminDao = AdminDao.getinstance();
		System.out.println("----------------------");
		System.out.println("탈퇴시킬 회원의 아이디를 입력해주세요.");
		System.out.print("아이디 : ");
		System.out.println("----------------------");
		String id = s.nextLine();
		
		UserVO user = new UserVO();

		user.setU_id(id);

		adminDao.AdmindeleteUser(user);
	}
	
	
}
