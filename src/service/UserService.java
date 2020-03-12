package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		UserVO user = new UserVO();
		System.out.println("<<< JOIN MENU >>>");
		System.out.println("--------------");
		System.out.println("아이디 : 영어와 숫자 입력이 가능합니다.");
		System.out.println("4자리 이상 12자리 이내로 작성해주세요.");
		System.out.print("아이디를 입력해주세요: ");
		String u_id = scan.nextLine();
		
		String id = "[a-z0-9]{4,12}";
		Pattern p1 = Pattern.compile(id);
		Matcher m1 = p1.matcher(u_id);
		boolean idCheck = m1.matches();
		if(idCheck){
			user.setU_id(u_id);
		}else{
			System.out.println("위의 사항을 만족하지 못하였습니다. 다시 입력해주세요");
			join();
			return;
		}
		System.out.println("--------------");
		System.out.println("비밀번호 : 영어,숫자 입력이 가능합니다.");
		System.out.println("6자리 이상 11자리 이내로 작성해주세요.");
		System.out.print("비밀번호를 입력해주세요 : ");
		
		String u_pw = scan.nextLine();
		String pw = "\\w{6,11}";
		Pattern p2 = Pattern.compile(pw);
		Matcher m2 = p2.matcher(u_pw);
		boolean pwCheck = m2.matches();
		if(pwCheck){
			user.setU_pw(u_pw);
		}else{
			System.out.println("위의 사항을 만족하지 못하였습니다. 다시 입력해주세요");
			join();
			return;
		}
		System.out.println("이름 : 한글만 입력 가능합니다.");
		System.out.println("2자리 이상 4자리 이내로 작성해주세요.");
		System.out.print("이름 : ");
		String u_name = scan.nextLine();
		String name = "[가-힣]{2,4}";
		Pattern p3 = Pattern.compile(name);
		Matcher m3 = p3.matcher(u_name);
		boolean nm_Check = m3.matches();
		
		if(nm_Check){
			user.setU_name(u_name);
		}else{
			System.out.println("위의 사항을 만족하지 못하였습니다. 다시 입력해주세요");
			join();
			return;
		}
		
		System.out.println("닉네임 : 한글,영어,숫자  입력이 가능합니다. 2자리 이상 8자리 이내로 작성해주세요.");
		System.out.print("닉네임 : ");
		String u_n_name = scan.nextLine();
		String n_name = "[가-힣a-z0-9]{2,8}";
		Pattern p4 = Pattern.compile(n_name);
		Matcher m4 = p4.matcher(u_n_name);
		boolean u_nmCheck = m4.matches();
		if(u_nmCheck){
			user.setU_n_name(u_n_name);
		}else{
			System.out.println("위의 사항을 만족하지 못하였습니다. 다시 입력해주세요");
			join();
			return;
		}
		userDao.insertUser(user);
	}

	// 로그인
	public void login() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("<<< LOGIN MENU >>>");
		
		System.out.println("--------------");
		System.out.print("아이디를 입력해주세요: ");
		String id = scan.nextLine();
				
		System.out.println("--------------");
		System.out.print("비밀번호를 입력해주세요 : ");
		String password = scan.nextLine();

		HashMap<String, String> param = new HashMap<>();
		param.put("ID", id);
		param.put("PASSWORD", password);

		UserVO user = userDao.selectUser(param);

		if (user == null) {
			System.out.println("--------------------------");
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");

		} else {
			System.out.println("--------------------------");
			System.out.println("로그인 성공");
			System.out.println(user.getU_name() + "님 환영합니다.");
			Session.LoginUser = user;
		}
	}
	

	// 
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
				String pw = "\\w{6,11}";
				Pattern pw2 = Pattern.compile(pw);
				Matcher m2pw = pw2.matcher(password);
				boolean pwCheck1 = m2pw.matches();
				if(pwCheck1){
					Session.LoginUser.setU_pw(password);
					System.out.println("비밀번호가 변경되었습니다.");
				}else{
					System.out.println("글자 제한 수의 범위 이내로 작성해주세요.");
					PWchange();
				}
				break;
			} else {
				System.out.println("비밀번호가 틀립니다.");
			}
		} while (pwCheck != null);
	}

	// 닉네임 변경 (체크 부분에서 오류)
	public void NMchange() {
		Scanner s = new Scanner(System.in);
		String u_n_name = null;
		UserVO nmCheck = null;

		do {
			System.out.println("변경할 닉네임 이름을 입력해주세요.");
			u_n_name = s.nextLine();
			
			HashMap<String, String> param = new HashMap<>();
			param.put("UNAME", u_n_name);
			nmCheck = userDao.selectUser(param); // 저장된 값과 비교
			
			if (nmCheck != null) { // 닉네임이 중복되었을 때
				System.out.println("중복된 닉네임입니다.");
			} else {
				String n_name = "[가-힣a-z0-9]{2,8}";
				Pattern pnm = Pattern.compile(n_name);
				Matcher mnm = pnm.matcher(u_n_name);
				boolean u_nmCheck = mnm.matches();
				if(u_nmCheck){
					Session.LoginUser.setU_n_name(u_n_name);
					System.out.println("닉네임이 변경되었습니다.");
				}
			}
		} while (nmCheck != null);

	}

	
}

/*
 * System.out.println("닉네임 : 영어와 숫자  입력이 가능합니다. 2자리 이상 8자리 이내로 작성해주세요.");
System.out.print("닉네임 : ");
String u_n_name = scan.nextLine();
String n_name = "\\w{2,8}";
Pattern p4 = Pattern.compile(n_name);
Matcher m4 = p4.matcher(u_n_name);
boolean u_nmCheck = m4.matches();
if(u_nmCheck){
user.setU_n_name(u_n_name);
}else{
System.out.println("글자 제한 수의 범위 이내로 작성해주세요.");
join();
}
 */