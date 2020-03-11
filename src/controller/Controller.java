package controller;

import java.util.Scanner;

import data.Session;
import service.AdminService;
import service.M_BoardService;
import service.MusicService;
import service.O_boardService;
import service.UserService;
import vo.UserVO;

public class Controller {
	private static Controller instance;

	Controller() {
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연 발표자 1명, ppt 및 시연 1명
		 * 
		 * Controller : 메뉴 선택 Service : 메뉴 기능 수행 Dao : 데이터베이스 접속 VO : 데이터를 담는
		 * 클래스
		 * 
		 * 회원가입 로그인 회원목록 -C
		 * 
		 * 정보입력 정보입력 정보출력 -S
		 * 
		 * DB저장 DB조회 DB조회 -D
		 * 
		 * 데이터베이스
		 */
		new Controller().start();

	}

	// 시작 화면
	public void start() { // 메인 화면
		UserService userService = UserService.getInstance();
		AdminController adminController = AdminController.getInstance();
		UserController userController = UserController.getInstance();
		MusicService musicService = MusicService.getInstance();
		Scanner s = new Scanner(System.in);
		int menu; // 메인 기능
        
        
                  
System.out.println("                                                                                               ");           
System.out.println("                                                              ,eee                             ");                               
System.out.println("                                                              eeeee                            ");                              
System.out.println("                                             W#,             ee  eeK                           ");                             
System.out.println("                                            Geee        9eeeGe   eee                           ");                             
System.out.println("             Keeee      ueeeu               Weee     eeeeeeeeeee  eee                          ");                            
System.out.println("              yeeee      eeeG                eee   Keee      #eee    ,                         ");                           
System.out.println("               eeeeE    Deeee       eeeeee   eeeu  eee     uuKXeee  eeee  ,5u                  ");                    
System.out.println("              GeeeeeD   eeeeeE   ,eeeeX5eee  eeey eee  ,,,uKyXXGeeK eee yeeeeez                ");                  
System.out.println("              eee eeey eee eee,  eeeeeEXeee# eeeX eeeK KWWW5D#zeee  eeeeee5eeee                ");                  
System.out.println("             yeee ,eeeeeeu #eee  eee, DeeeeK eee9 Keeey5XDz##9Eeee  eeeez  eee9                ");                  
System.out.println("             eeeX  ueeeey   eeee yeee#       Eeee  XeeeeE#9Eeeeee   Geey   eee,                ");                  
System.out.println("            Keee     eeW     eeeX  #eeeee    eeee    Geeeeeeeeey          Geez                 ");                   
System.out.println("            eeeE                                        y##X,                                  ");                                    
System.out.println("              W                                                                                ");                 
System.out.println("                                                                                               ");        
System.out.println("                                                                                               ");        


		do {
	
			System.out
					.println("====================================================================================");
			System.out
					.println("국내 최다 4000만곡 보유, No.1 뮤직플랫폼 멜론! 실시간 차트부터 나를 아는 똑똑한 음악추천까지!");
			System.out
					.println("====================================================================================");
			System.out.println("1.로그인" + "\t" + "2.회원가입" + "\t" + "3.노래 검색"
					+ "\t" + " 4.차트보기" + "\t" + "5.노래추천 게시판" + "\t" + "6. 공지사항"
					+ " 0.종료");
			System.out
					.println("====================================================================================");
			System.out.print("메뉴에 해당하는 번호를 입력해주세요. >");
			menu = Integer.parseInt(s.nextLine());

			switch (menu) {
			case 1: // 로그인
				userService.login();
				if (Session.LoginUser != null)
					if (Session.LoginUser.getU_id().equals("admin")) {
						adminController.adminLoginMenu();// 관리자 로그인 화면
					} else {
						userController.userLoginMenu();
					}
				break;
			case 2: // 회원가입
				userService.join();
				break;
			case 3:
				// 노래 검색
				musicService.searchMusic();
				break;
			case 4:
				// 차트 보기
				userController.chart();
				break;

			case 5: // 노래추천 게시판
				m_board();
				break;

			case 6:
				// 공지사항 메소드
				o_board();
				break;

			case 0: // 프로그램 종료
				System.out.println("프로그램 종료");
				break;
			}

		} while (menu != 0);
	}

	// 비회원 공지사항
	void o_board() {
		Scanner scan = new Scanner(System.in);
		O_boardService o_boardService = O_boardService.getInstance();
		
		AdminController adminController = AdminController.getInstance();
		UserController userController = UserController.getInstance();
		int menu;
		do {
			System.out.println("1. 공지사항 보기  2.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 공지사항 보기 메소드 호출
				o_boardService.o_boardList();
				break;
			case 2:
				if(Session.LoginUser == null){
					start();	
				}else if(Session.LoginUser.getU_id().equals("admin")){
					adminController.adminLoginMenu();
				}else{
					userController.userLoginMenu();
				}
				
				break;
			}
		} while (menu != 0);
	}

	void m_board() {
		Scanner scan = new Scanner(System.in);
		M_BoardService m_boardService = M_BoardService.getInstance();
		UserController usercontroller = UserController.getInstance();
		int menu;
		do {
			System.out.println("1. 노래추천 게시판 보기  2.이전 메뉴로 가기   0.프로그램 종료");
			menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				// 노래추천 게시판 보기
				m_boardService.m_BoardList();
				break;
			case 2:
				// 이전메뉴로 가기
				start();
				break;
			case 0:
				System.out.println("프로그램 종료");
			}
		} while (menu != 0);
	}

	/*
	 * // 노래 리스트 기능 private void start1() { MusicService musicService =
	 * MusicService.getInstance(); Scanner s = new Scanner(System.in);
	 * 
	 * int menu; do { System.out.println("------------메뉴---------------");
	 * System.out.println("0. 로그인"); // 없애고 System.out.println("1. 노래 리스트 보기");
	 * // 회원,비회원, 관리자 System.out.println("2. 노래 검색"); // 회원, 비회원, 관리자
	 * System.out.println("3. 노래 추가"); // 관리자 System.out.println("4. 노래 삭제"); //
	 * 관리자 System.out.println("5. 최근 들은 플레이리스트 보기"); // 회원
	 * System.out.println("6. 플레이리스트 보기"); //회원
	 * System.out.println("9. 프로그램 종료");
	 * System.out.println("------------------------------");
	 * System.out.print("메뉴에 해당하는 번호 입력 >");
	 * 
	 * menu = Integer.parseInt(s.nextLine());
	 * 
	 * switch (menu) {
	 * 
	 * case 0:
	 * 
	 * break; case 1: musicService.MusicList(); // 저장된 음악 리스트 보기ㅣ break; case 2:
	 * musicService.searchMusic(); // 노래검색 break; case 3:
	 * musicService.InsertMusic(); // 노래추가 break; case 4:
	 * musicService.deleteMusic(); // 노래삭제 break; case 5:
	 * musicService.Show_R_musicList(); // 최근 들은 노래 리스트 확인 break; case 6:
	 * musicService.Show_MusicList(); // 플레이리스트 확인 break; } } while (menu != 9);
	 * 
	 * }
	 */
}

// 컨트롤러 클래스에 로그인 / 비로그인 / 관리자 모드 화면 출력 메소드 만들어서 main문에는 메소드 호출하는 형식으로 하자
