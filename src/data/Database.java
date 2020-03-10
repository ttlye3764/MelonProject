package data;

import java.util.ArrayList;

import vo.M_BoardVO;
import vo.MusicVO;
import vo.O_BoardVO;
import vo.PlayListVO;
import vo.R_playListVO;
import vo.UserVO;
import vo.ticketVO;

public class Database {

	private static Database instance;

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	
	public ArrayList<UserVO> tb_user = new ArrayList<>();   //유저 테이블
	
	public ArrayList<MusicVO> tb_music = new ArrayList<>(); //음악 테이블
	
	public ArrayList<PlayListVO> tb_playlist = new ArrayList<>(); //재생목록
	
	public ArrayList<R_playListVO> tb_r_playlist = new ArrayList<>();//최근 들은노래 리스트
	
	public ArrayList<M_BoardVO> tb_m_board = new ArrayList<>(); //노래추천게시판
	
	public ArrayList<O_BoardVO> tb_o_board = new ArrayList<>(); //공지사항 게시판
	
	public ArrayList<ticketVO> tb_ticket = new ArrayList<>(); //티켓
	
	
	
	
	
	
	
	
	
	
	
	{
		M_BoardVO m_board = new M_BoardVO();
		m_board.setM_b_name("확인용");
		m_board.setM_b_content("내용");
		m_board.setM_b_date("2020-03-09");
		m_board.setU_id("확인용");
		m_board.setM_b_number(1);
		m_board.setM_number(1); //입력값을 받아서 입력값에 해당하는 노래게시판 번호
		tb_m_board.add(m_board);
		
		O_BoardVO o_board = new O_BoardVO();
		o_board.setO_b_name("이재호");
		o_board.setAd_name("admin");
		o_board.setO_b_content("아아아아ㅏ");
		o_board.setO_b_number(1);
		o_board.setO_b_date("현재날짜");
		tb_o_board.add(o_board);
		
		UserVO user = new UserVO();
		user.setU_id("admin");
		user.setU_pw("1111");
		user.setU_name("관리자");
		tb_user.add(user);
		
		//노래 넣는부분//
		MusicVO music1 = new MusicVO();
		MusicVO music2 = new MusicVO();
		MusicVO music3 = new MusicVO();
		MusicVO music4 = new MusicVO();
		MusicVO music5 = new MusicVO();
		MusicVO music6 = new MusicVO();
		MusicVO music7 = new MusicVO();
		MusicVO music8 = new MusicVO();
		MusicVO music9 = new MusicVO();
		MusicVO music10 = new MusicVO();
		MusicVO music11 = new MusicVO();
		MusicVO music12 = new MusicVO();
		MusicVO music13 = new MusicVO();
		MusicVO music14= new MusicVO();
		MusicVO music15 = new MusicVO();
		MusicVO music16 = new MusicVO();
		MusicVO music17 = new MusicVO();
		MusicVO music18 = new MusicVO();
		MusicVO music19 = new MusicVO();
		MusicVO music20 = new MusicVO();
		
		
		//20개 
		//장르 4개 
		// 5개씩 순위로 4개 순위
		
		
		
		
		
		music1.setM_number(1);
		music1.setM_name("ON");
		music1.setM_singer("방탄소년단");
		music1.setM_lyrics("Pdogg,j-hope");
		music1.setM_composition("Pdogg, j-hope");
		music1.setM_genre("힙합");
		music1.setM_date("2020-02-21");
		music1.setM_count(5);
		tb_music.add(music1);
		//
		music2.setM_number(2);
		music2.setM_name("Blueming");
		music2.setM_singer("아이유");
		music2.setM_lyrics("아이유");
		music2.setM_composition("이종훈,아이유");
		music2.setM_genre("힙합");
		music2.setM_date("2019-11-18");
		music2.setM_count(3);
		tb_music.add(music2);
		//
		music3.setM_number(3);
		music3.setM_name("00:00(Zero O'Clock)");
		music3.setM_singer("방탄소년단");
		music3.setM_lyrics("Pdogg,RM");
		music3.setM_composition("Pdogg, RM");
		music3.setM_genre("힙합");
		music3.setM_date("2020-02-21");
		music3.setM_count(2);
		tb_music.add(music3);
		//
		music4.setM_number(4);
		music4.setM_name("아무노래");
		music4.setM_singer("지코(ZICO)");
		music4.setM_lyrics("지코(ZICO)");
		music4.setM_composition("지코(ZICO)");
		music4.setM_genre("힙합");
		music4.setM_date("2020-01-13");
		music4.setM_count(16);
		tb_music.add(music4);
		//		
		music5.setM_number(5);
		music5.setM_name("늦은 밤 너의 집 앞 골목길에서");
		music5.setM_singer("노을");
		music5.setM_lyrics("미후왕");
		music5.setM_composition("빅가이로빈");
		music5.setM_genre("발라드");
		music5.setM_date("2019-11-07");
		music5.setM_count(5);
		tb_music.add(music5);
		//	
		music6.setM_number(6);
		music6.setM_name("Stay With Me");
		music6.setM_singer("찬열, 펀치");
		music6.setM_lyrics("지훈");
		music6.setM_composition("로코베리,이승주");
		music6.setM_genre("발라드");
		music6.setM_date("2016-12-03");
		music6.setM_count(14);
		tb_music.add(music6);
		//	
		music7.setM_number(7);
		music7.setM_name("니 소식");
		music7.setM_singer("송하예");
		music7.setM_lyrics("미친기집애");
		music7.setM_composition("미친기집애");
		music7.setM_genre("발라드");
		music7.setM_date("2019-05-11");
		music7.setM_count(19);
		tb_music.add(music7);
		//
		music8.setM_number(8);
		music8.setM_name("오늘도 빛나는 너에게");
		music8.setM_singer("마크툽");
		music8.setM_lyrics("마크툽");
		music8.setM_composition("마크툽");
		music8.setM_genre("발라드");
		music8.setM_date("2019-06-09");
		music8.setM_count(40);
		tb_music.add(music8);
		//
		music9.setM_number(9);
		music9.setM_name("2002");
		music9.setM_singer("Anne-Marie");
		music9.setM_lyrics("Anne-Marie");
		music9.setM_composition("Anne-Marie");
		music9.setM_genre("POP");
		music9.setM_date("2018-08-03");
		music9.setM_count(26);
		tb_music.add(music9);
		//
		music10.setM_number(10);
		music10.setM_name("Bad guy");
		music10.setM_singer("Billie Eilish");
		music10.setM_lyrics("Billie Eilish");
		music10.setM_composition("Billie Eilish");
		music10.setM_genre("POP");
		music10.setM_date("2019-03-29");
		music10.setM_count(27);
		tb_music.add(music10);
		//
		music11.setM_number(11);
		music11.setM_name("Paris in the rain");
		music11.setM_singer("Lauv");
		music11.setM_lyrics("Lauv");
		music11.setM_composition("Lauv");
		music11.setM_genre("POP");
		music11.setM_date("2018-10-08");
		music11.setM_count(14);
		tb_music.add(music11);
		//
		music12.setM_number(12);
		music12.setM_name("Birth day");
		music12.setM_singer("Anne-Marie");
		music12.setM_lyrics("Anne-Marie");
		music12.setM_composition("Anne-Marie");
		music12.setM_genre("POP");
		music12.setM_date("2020-02-07");
		music12.setM_count(9);
		tb_music.add(music12);
		//
		music13.setM_number(13);
		music13.setM_name("FIESTA");
		music13.setM_singer("IZ*ONE");
		music13.setM_lyrics("서지음, 고현정");
		music13.setM_composition("최현준,김승수");
		music13.setM_genre("Dance");
		music13.setM_date("2020-02-17");
		music13.setM_count(15);
		tb_music.add(music13);
		//
		music14.setM_number(14);
		music14.setM_name("psycho");
		music14.setM_singer("red velvet");
		music14.setM_lyrics("kenzie");
		music14.setM_composition("Andrew Scott");
		music14.setM_genre("Dance");
		music14.setM_date("2019-12-23");
		music14.setM_count(1);
		tb_music.add(music14);
		//
		music15.setM_number(15);
		music15.setM_name("분홍신");
		music15.setM_singer("IU");
		music15.setM_lyrics("김이나");
		music15.setM_composition("이민수");
		music15.setM_genre("Dance");
		music15.setM_date("2013-10-08");
		music15.setM_count(3);
		tb_music.add(music15);
		//
		music16.setM_number(16);
		music16.setM_name("MILLIONS");
		music16.setM_singer("WINNER");
		music16.setM_lyrics("강승윤, 송민호");
		music16.setM_composition("강승윤");
		music16.setM_genre("Dance");
		music16.setM_date("2018-12-19");
		music16.setM_count(8);
		tb_music.add(music16);
		//
		music17.setM_number(17);
		music17.setM_name("Square");
		music17.setM_singer("백예린");
		music17.setM_lyrics("백예린");
		music17.setM_composition("백예린");
		music17.setM_genre("R&B");
		music17.setM_date("2019-12-10");
		music17.setM_count(7);
		tb_music.add(music17);
		//
		music18.setM_number(18);
		music18.setM_name("팔레트");
		music18.setM_singer("IU");
		music18.setM_lyrics("IU");
		music18.setM_composition("IU");
		music18.setM_genre("R&B");
		music18.setM_date("2017-04-21");
		music18.setM_count(10);
		tb_music.add(music18);
		//
		music19.setM_number(19);
		music19.setM_name("가끔 이러다");
		music19.setM_singer("펀치");
		music19.setM_lyrics("박강일");
		music19.setM_composition("박강일");
		music19.setM_genre("R&B");
		music19.setM_date("2019-09-18");
		music19.setM_count(20);
		tb_music.add(music19);
		//
		music20.setM_number(20);
		music20.setM_name("instagram");
		music20.setM_singer("DEAN");
		music20.setM_lyrics("DEAN");
		music20.setM_composition("DEAN");
		music20.setM_genre("R&B");
		music20.setM_date("2017-12-26");
		music20.setM_count(14);
		tb_music.add(music20);
		//
	}
	
	
	
	
	public static void main(String[] args) {
		Database a = new Database();	
		for (int i = 0; i < a.tb_music.size(); i++) {
			System.out.println(a.tb_music.get(i));
		}
	}
	
	
	
}
