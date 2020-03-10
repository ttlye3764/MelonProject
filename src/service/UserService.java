package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import vo.UserVO;
import vo.ticketVO;
import controller.UserController;
import dao.UserDao;
import data.Session;
//1234
public class UserService {

	private static UserService instance;

	private UserService() {
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	UserDao userDao = UserDao.getInstance();

	// 회원가입
	public void join() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 : ");
		String u_id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String u_pw = scan.nextLine();
		System.out.print("이름 : ");
		String u_name = scan.nextLine();
		System.out.print("닉네임 : ");
		String u_n_name = scan.nextLine();

		UserVO user = new UserVO();

		user.setU_id(u_id);
		user.setU_pw(u_pw);
		user.setU_name(u_name);
		user.setU_name(u_n_name);
		userDao.insertUser(user);
	}

	// 로그인
	public void login() {
		Scanner s = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호: ");
		String password = s.nextLine();

		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", password);

		UserVO user = userDao.selectUser(param);

		if (user == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");

		} else {
			System.out.println("로그인 성공");
			System.out.println(user.getU_name() + "님 환영합니다.");
			Session.LoginUser = user;
		}

	}

	// 회원목록
	public void userList() {
		ArrayList<UserVO> userList = userDao.selectUserList();
		System.out.println("-----------------------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < userList.size(); i++) {
			UserVO user = userList.get(i);
			System.out.println(i + 1 + "\t" + user.getU_id() + "\t"
					+ user.getU_name());
		}
		System.out.println("------------------------------------------------");
	}

	// 회원 정보
	public void userInfo() {
		Scanner s = new Scanner(System.in);
		UserController userController = UserController.getInstance();
		int menu;
		UserVO user = Session.LoginUser;
		do {
			System.out.println("--------------내 정보-----------------");
			System.out.println(" 내 아이디 : " + user.getU_id()); // 세션에 있는 아이디를 가지고
																// 온다.
			System.out.println(" 내 닉네임 : " + user.getU_name()); // 세션에 있는 닉네임을
																// 가져온다.
			System.out.println(" 1. 이용권 구매 여부 ");
			System.out.println(" 2. 비밀번호 변경");
			System.out.println(" 3. 닉네임 변경");
			System.out.println(" 4. 회원 탈퇴");
			System.out.println(" 5. 이전 메뉴");
			System.out.println("-------------------------------------");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {
			case 1:
				TicketPurchase();
				break;
			case 2:
				PWchange();
				break;
			case 3:
				NMchange();
				break;
			case 4:
				userDelete();
				break;
			case 5:
				userController.userLoginMenu();
				break;
			case 0:
				break;
			}
		} while (menu != 0);
	}

	// 회원 삭제 03.09 코딩
	public void userDelete() {
		Scanner s = new Scanner(System.in);
		System.out.println("회원 탈퇴를 위한 정보를 입력해주세요.");
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호 : ");
		String pw = s.nextLine();

		UserVO user = new UserVO();

		user.setU_id(id);
		user.setU_pw(pw);

		userDao.deleteUser(user);
	}

	// 비밀번호 변경
	public void PWchange() {
		Scanner s = new Scanner(System.in);
		String password = null;
		UserVO pwCheck = null;

		do {
			System.out.println("기존 비밀번호를 입력해주세요.");
			password = s.nextLine();

			HashMap<String, String> param = new HashMap<>();
			param.put("PASSWORD", password);
			pwCheck = userDao.selectUser(param); // 저장된 값과 비교

			if (pwCheck != null) {
				System.out.println("변경할 비밀번호를 입력해주세요.");
				password = s.nextLine();

				Session.LoginUser.setU_pw(password);
				System.out.println("비밀번호가 변경되었습니다.");
				userInfo();
				break;
			} else {
				System.out.println("비밀번호가 틀립니다.");
			}
		} while (pwCheck != null);
	}

	// 닉네임 변경
	public void NMchange() {
		Scanner s = new Scanner(System.in);
		String name = null;
		UserVO nmCheck = null;

		do {
			System.out.println("변경할 닉네임 이름을 입력해주세요.");
			name = s.nextLine();

			HashMap<String, String> param = new HashMap<>();
			param.put("NAME", name);
			nmCheck = userDao.selectUser(param); // 저장된 값과 비교

			if (nmCheck != null) { // 닉네임이 중복되었을 때
				System.out.println("중복된 닉네임입니다.");
			} else {
				Session.LoginUser.setU_n_name(name);
				System.out.println("닉네임이 변경되었습니다.");
			}
		} while (nmCheck != null);

	}

	// 이용권 구매 여부 03.09 코딩
	public void TicketPurchase() {
		Scanner s = new Scanner(System.in);
		UserVO user = Session.LoginUser;
		ticketVO Ticket = new ticketVO();
		Date today = new Date(); // 날짜를 표현하는 클래스,현재 날짜가 저장
		int menu = 0;
		int ticket = 0;
		String name = user.getU_name();
		do {
			System.out.println("--------------이용권 구매 여부-----------------");
			System.out.println("이용권 갯수 : " + user.getU_ticket());
			System.out.println("이용권 구매 날짜 : " + Ticket.getT_buy_date());
			System.out.println("데이터 베이스에 저장된 이용권 갯수 : " + Ticket.getT_number());
			System.out.println("데이터 베이스에 저장된 닉네임 : " + Ticket.getU_name());
			System.out.println("\n" + "1. 이용권 구매 2.이전 메뉴");
			menu = Integer.parseInt(s.nextLine());
			switch (menu) {
			case 1:
				System.out.println("이용권을 구매 했습니다.");
				ticket += 20;
				UserVO user1 = new UserVO();
				user.setU_ticket(ticket);
				Ticket.setT_buy_date(today);
				Ticket.setU_name(name);
				Ticket.setT_number(ticket);
				break;
			case 2:
				userInfo();
				break;
			}
		} while (menu != 0);
	}

}
