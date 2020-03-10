package controller;

import java.util.Scanner;

import service.M_BoardService;
import service.MusicService;
import service.UserService;
import data.Session;

public class UserController {

	private static UserController instance;

	private UserController() {
	}

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	// 유저 로그인 메뉴
	public void userLoginMenu() {
		UserService userService = UserService.getInstance();
		MusicService musicService = MusicService.getInstance();
		Controller controller = Controller.getInstance();
		Scanner scan = new Scanner(System.in);
		int menu;
		do {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("국내 최다 4000만곡 보유, No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println(
					"1. 노래검색		2. 차트보기		3. 플레이리스트		4.내 정보		5.노래추천 게시판		6. 공지사항	7.로그아웃		0.프로그램 종료");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("메뉴에 해당하는 번호를 입력해주세요. >");
			menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1:
				// 노래 검색 메소드 호출
				musicService.searchMusic();
				break;
			case 2:
				// 차트보기 컨트롤러 메소드 호출
				chart();
				break;
			case 3:
				// 플레이리스트 컨트롤러 메소드 호출
				playList();
				break;
			case 4:
				// 내 정보 메소드 호출
				userService.userInfo();
				break;
			case 5:
				// 노래추천 게시판 메소드 호출
				m_board();
				break;
			case 6:
				// 공지사항 메소드 호출
				controller.o_board();
				break;
			case 7: // 로그아웃
				System.out.println("로그아웃");
				Session.LoginUser = null;
				controller.start();
				break;
			case 0:
				System.out.println("프로그램 종료");
				break;
			}
		} while (menu != 0);
	}

	void chart() {
		MusicService musicService = MusicService.getInstance();
		AdminController adminController = AdminController.getInstance();
		Controller controller = Controller.getInstance();
		int menu;
		Scanner scan = new Scanner(System.in);
		do {

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("국내 최다 4000만곡 보유, No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1.최신곡 차트		2.인기곡 차트		3.장르별 차트		4.이전 메뉴로 가기		0.프로그램 종료 ");
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("메뉴에 해당하는 번호를 입력해주세요. >");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 최신곡 차트 메소드 호출
				musicService.R_MusicChart();
				break;
			case 2:
				// 인기곡 차트 메소드 호출
				musicService.MusicChart();
				break;
			case 3:
				// 장르별 차트 메소드 호출
				musicService.Genre_MusicChart();
				break;
			case 4:
				if(Session.LoginUser.getU_id().equals("admin")){
					adminController.musicMange();
				}else if(Session.LoginUser.getU_id()== null){
					controller.start();
				}else{
					userLoginMenu();
				}
				break;
			case 0:
				System.out.println("프로그램 종료");
			}
		} while (menu != 0);
	}

	void playList() {
		int menu;
		MusicService musicService = MusicService.getInstance();
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("1. 최근 노래 리스트	2. 플레이리스트 		3. 이전 메뉴");
			menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1:
				// 최근 노래 리스트 메소드 호출
				musicService.Show_R_musicList();
				break;
			case 2:
				// 플레이리스트 메소드 호출
				musicService.Show_MusicList();
				break;
			case 3:
				// 로그인 유저 컨트롤러 메소드 호출
				userLoginMenu();
				break;
			}

		} while (true);

	}

	void m_board() {
		Scanner scan = new Scanner(System.in);
		M_BoardService m_boardService = M_BoardService.getInstance();
		UserController usercontroller = UserController.getInstance();
		int menu;
		do {
			System.out.println("1.게시판 보기  2.게시판 글 쓰기  3.게시판 수정  4.게시판 삭제  5.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 노래추천 게시판 보기
				m_boardService.m_BoardList();
				break;
			case 2 :
				//노래추천 게시판 글 쓰기
				m_boardService.insertM_Board();
				break;
			case 3 :
				//노래추천 게시판 수정
				m_boardService.modifyM_Board();
				break;
			case 4 :
				//노래추천 게시판 삭제
				m_boardService.deleteM_Board();
				break;
			case 5:
				usercontroller.userLoginMenu();
				break;
			case 0:
				System.out.println("프로그램 종료");
			}
		} while (menu != 0);
	}
}
