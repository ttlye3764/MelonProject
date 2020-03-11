package service;

import java.util.ArrayList;
import java.util.Scanner;

import vo.M_BoardVO;
import vo.UserVO;
import controller.Controller;
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
		System.out.println("-----------------------------------------------");
		System.out.println("번호\t아이디\t이름\t이용권 구매 횟수");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			System.out.println(i + 1 + "\t" + user.getU_id() + "\t"
					+ user.getU_name() + "\t");
			ticketService.userTicketAmount(user.getU_id());
		}
		System.out.println("------------------------------------------------");
	}
	//회원 삭제
	public void userdelete(){
		Scanner s = new Scanner(System.in);
		System.out.println("탈퇴시킬 회원의 아이디를 입력해주세요.");
		System.out.print("아이디 : ");
		String id = s.nextLine();
		
		UserVO user = new UserVO();

		user.setU_id(id);

		userDao.AdmindeleteUser(user);
	}
	

	
	//노래 추천 게시판 관리
	void m_boradMange() {
		
		
	}
	
	//공지사항 게시판 관리
	void o_boradMange() {
		
		
	}
	
}
