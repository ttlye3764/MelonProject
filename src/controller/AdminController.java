package controller;

import java.util.Scanner;

import service.M_BoardService;
import service.MusicService;
import service.O_boardService;
import service.UserService;
import data.Session;

public class AdminController {

	private static AdminController instance;

	private AdminController() {
	}

	public static AdminController getInstance() {
		if (instance == null) {
			instance = new AdminController();
		}
		return instance;
	}

	// 관리자 메뉴
	public void adminLoginMenu() {
		Scanner scan = new Scanner(System.in);
		Controller controller = Controller.getInstance();
		int menu;
		do {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("국내 최다 4000만곡 보유, No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1.회원 관리		2.음원 관리		3.노래 추천 게시판 관리	 4. 공지사항 게시판 관리     5.로그아웃        0.프로그램 종료");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("메뉴에 해당하는 번호를 입력해주세요. >");
			menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1: //
				// 회원 관리 메소드
				admUserMange();
				break;
			case 2:
				// 음원 관리 메소드 호출
				musicMange();
				break;
			case 3:
				// 노래추천 게시판 관리 메소드 호출
				m_board();
				break;

			case 4:
				// 공지사항 게시판 관리 메소드 호출
				o_board();
				break;
			case 5:
				// 로그아웃
				Session.LoginUser = null;
				System.out.println("로그아웃이 완료 되었습니다.");
				controller.start();

			case 0: // 프로그램 종료
				break;
			}
		} while (menu != 0);

	}

	void admUserMange() {
		Scanner scan = new Scanner(System.in);
		UserService userService = UserService.getInstance();
		int menu;
		do {
			System.out.println("1. 회원 목록 조회    2. 회원 삭제  3.이전 메뉴로 가기    4.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1:
				userService.userList();
				// 회원 목록조회 메소드 호출
				break;
			case 2:
				// 회원삭제 메소드 호출
				userService.userDelete();
				break;
			case 3:
				// 관리자 로그인 화면 메소드 호출
				adminLoginMenu();
			case 4:
				System.out.println("프로그램 종료");
			}
		} while (menu != 4);
	}

	// 음원 관리
	void musicMange() {
		MusicService musicService = MusicService.getInstance();
		UserController userController = UserController.getInstance();
		Scanner scan = new Scanner(System.in);
		int menu;
		do {
			System.out.println("1. 음원 검색   2. 음원 보기  3. 음원 등록  4. 음원 삭제  5.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1:
				// 음원검색 메소드 호출
				musicService.searchMusic();
				break;
			case 2:
				userController.chart();
				break;
			case 3:
				// 음원 등록 메소드 호출
				musicService.InsertMusic();
				break;
			case 4:
				// 음원 삭제 메소드 호출
				musicService.deleteMusic();
				break;
			case 5:
				adminLoginMenu();
				break;
			case 0:
				System.out.println("프로그램 종료");
			}
		} while (menu != 0);

	}

	// 공지사항 컨트롤러
	void o_board() {
		Scanner scan = new Scanner(System.in);
		O_boardService o_boardService = O_boardService.getInstance();
		int menu;
		do {
			System.out.println("1. 공지사항 보기  2. 공지사항 등록  3. 공지사항 수정  4. 공지사항 삭제 5.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 공지사항 보기 메소드 호출
				o_boardService.o_boardList();
				break;
			case 2:
				// 공지사항 등록 메소드 호출
				o_boardService.insertO_board();
				break;
			case 3:
				// 공지사항 수정 메소드 호출
				o_boardService.modifyO_board();
				break;
			case 4:
				// 공지사항 삭제
				o_boardService.deleteO_board();
				break;
			case 5:
				// 이전 메뉴로 가기
				adminLoginMenu();
			case 6:
				// 프로그램 종료
				System.out.println("프로그램 종료");
			}

		} while (menu != 5 && menu != 6);
	}
	// 게시판 관리 컨트롤러
	void m_board() {
		Scanner scan = new Scanner(System.in);
		M_BoardService m_boardService = M_BoardService.getInstance();
		UserController usercontroller = UserController.getInstance();
		int menu;
		do {
			System.out.println("1.게시판 보기 2.게시판 삭제  3.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 노래추천 게시판 보기
				m_boardService.m_BoardList();
				break;
			case 2 :
				//노래추천 게시판 삭제
				m_boardService.deleteM_Board();
				break;
			case 3:
				adminLoginMenu();;
				break;
			case 0:
				System.out.println("프로그램 종료");
			}
		} while (menu != 0);
	}
}