package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import vo.MusicVO;
import vo.UserVO;
import vo.TicketVO;
import controller.UserController;
import dao.TicketDao;
import dao.UserDao;
import data.Session;

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
	TicketDao ticketDao = TicketDao.getInstance();

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
		user.setU_n_name(u_n_name);
		userDao.insertUser(user);
	}

	// 로그인
	public void login() {
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String password = scan.nextLine();

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
	

	// 회원 삭제 03.09 코딩 (코드가 돌아가나, 삭제했을 때 초기 화면으로 안돌아감)
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
	

	// 비밀번호 변경(비밀번호 변경 됨)
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
				break;
			} else {
				System.out.println("비밀번호가 틀립니다.");
			}
		} while (pwCheck != null);
	}

	// 닉네임 변경 (성공적으로 돌아감)
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

	
}



