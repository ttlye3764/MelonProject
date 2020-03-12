package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import data.Database;
import data.Session;
import service.MusicService;
import vo.MusicVO;
import vo.R_playListVO;
import vo.TicketVO;
import vo.UserVO;
import vo.PlayListVO;
public class MusicDao {

	private static MusicDao instance;

	private MusicDao() {
	}

	public static MusicDao getInstance() {
		if (instance == null) {
			instance = new MusicDao();
		}
		return instance;
	}

	Database database = Database.getInstance();
	TicketDao ticketDao = TicketDao.getInstance();
	public void insertMusic(MusicVO music) { //노래 추가
		boolean check = true;
		for (int i = 0; i < database.tb_music.size(); i++) {
			if ((database.tb_music.get(i).getM_name()).equals(music.getM_name())
				&& database.tb_music.get(i).getM_singer().equals(music.getM_singer())) {
				
				System.out.println("중복된 노래 입니다.");
				check = false;
				break;
			}else if(database.tb_music.get(i).getM_number() == music.getM_number()){
				System.out.println("중복된 등록 번호입니다.");
				check = false;
				break;
			}
		}
		if (check) {
			database.tb_music.add(music);
			System.out.println("한곡이 추가 되었습니다.");
		}
	}
	
	
	

	public MusicVO searchMusic(MusicVO param) {  //노래검색
		MusicVO rtnMusic = null;			
		for (int i = 0; i < database.tb_music.size(); i++) {
			MusicVO music = database.tb_music.get(i);

			if ((music.getM_name().equals(param.getM_name()) &&  music.getM_singer().equals(param.getM_singer()))) {				
				rtnMusic = music;
				break;
			} 
		}	
		
		return rtnMusic;
	}
	
	public void deleteMusic(MusicVO param){  //노래삭제
		boolean check = false;
		
		for (int i = 0; i < database.tb_music.size(); i++) {
			MusicVO music = database.tb_music.get(i);

			if ((music.getM_name().equals(param.getM_name()) &&  music.getM_singer().equals(param.getM_singer()))) {
				database.tb_music.remove(i);
				check = true;
				break;
			} else {
				check = false;
			}
		}
		if(check){
			System.out.println("한 건의 데이터가 삭제 되었습니다.");			
		}
		else{
			System.out.println("삭제할 노래를 찾지 못했습니다.");
		}
	}
	
	
	
	
	public ArrayList<MusicVO> MusicList() {  //총 노래 목록 리스트 보기

		return database.tb_music;
	}

	public void insertR_playList(MusicVO music) {   //최근 들은 노래 리스트		
	
		UserVO uservo = Session.LoginUser;  //로그인된 user정보를 가져오기 위해 세션을 사용
		
		String u_id = uservo.getU_id();
		int m_number = music.getM_number();		
		R_playListVO r_playlist = new R_playListVO();
		
		r_playlist.setU_id(u_id);
		r_playlist.setM_number(m_number);	
		database.tb_r_playlist.add(r_playlist);		
		System.out.println("최근 들은 노래 리스트에 추가 되었습니다.");
	}

	public ArrayList<R_playListVO> R_PlayList() {   // 최근들은노래 리스트
		return database.tb_r_playlist;
	}
	
	public ArrayList<PlayListVO> PlayList() {		// 사용자 플레이 리스트
		return database.tb_playlist;
	}

	public void insert_playList(MusicVO music) {					// 사용자 플레이 리스트 노래넣기
		boolean check = true;
		UserVO uservo = Session.LoginUser;  //로그인된 user정보를 가져오기 위해 세션을 사용
		
		String u_id = uservo.getU_id();
		int m_number = music.getM_number();		
		PlayListVO playlist = new PlayListVO();
		
		playlist.setU_id(u_id);
		playlist.setM_number(m_number);
		
		
		for (int i = 0; i < database.tb_playlist.size(); i++) {
			if ((database.tb_playlist.get(i).getM_number() == music.getM_number() 
					&& database.tb_playlist.get(i).getU_id() == uservo.getU_id())){				
				System.out.println("이미 추가된 노래입니다.");
				check = false;
				break;
			}
		}
		if (check) {
			database.tb_playlist.add(playlist);	
			System.out.println("사용자 노래 리스트에 추가 되었습니다.");
		}
	}

	public void CountPlus(MusicVO param) {
		
		for (int i = 0; i < database.tb_music.size(); i++) {
			MusicVO music = database.tb_music.get(i);

			if ((music.getM_name().equals(param.getM_name()) &&  music.getM_singer().equals(param.getM_singer()))) {				
				music.setM_count(music.getM_count() + 1);
				break;
			} 
		}
	}

	public void listenPlayList(UserVO uservo, ArrayList<MusicVO> list) {
		ArrayList<TicketVO> ticketList = ticketDao.ticketList();	
		MusicVO musicvo = new MusicVO();
		int listsize = list.size();
		int count = 0;
		boolean check = true;
		
		for (int i = 0; i < list.size(); i++) {
			musicvo = list.get(i);
			this.CountPlus(musicvo);
		}
		
		A: for (int i = 0; i < ticketList.size(); i++) {
			if(ticketList.get(i).getU_Id().equals(uservo.getU_id())){
				if(ticketList.get(i).getTicket_M_Amount()<1){
					continue;
				}
				else{
					for (int j = 0; j < listsize; j++) {
						if(ticketList.get(i).getTicket_M_Amount()<1){
							listsize = listsize + count;
							check = true;
							continue A; 
						}
						else{
							ticketList.get(i).setTicket_M_Amount(ticketList.get(i).getTicket_M_Amount()-1);					
							count--;
							check = false;
						}
					}
				break;
				}				
			}				
		}
		if(check){
			System.out.println("이용권이 부족합니다.");
		}
		else{
			System.out.println("듣기 완료");
		}
	}

	public void deletePlayListMusic(UserVO uservo) {
		Scanner scan = new Scanner(System.in);
		boolean check = true;
		System.out.println("삭제할 노래 이름을 입력해주세요.");
		String name = scan.nextLine();
		System.out.println("노래의 가수 이름을 입력해주세요.");
		String singer = scan.nextLine();
		MusicVO music = new MusicVO();		
		music.setM_name(name);
		music.setM_singer(singer);
		
		music = this.searchMusic(music);
		if(music == null){
			System.out.println("삭제할 노래가 없습니다.");
		}
		else{
		
			for (int i = 0; i < database.tb_playlist.size(); i++) {
				if(music.getM_number() == database.tb_playlist.get(i).getM_number() && 
						uservo.getU_id().equals(database.tb_playlist.get(i).getU_id())){
					database.tb_playlist.remove(i);
					check = false;
					break;
				}			
			}
		if(check){
			System.out.println("삭제할 노래가 없습니다.");				
		}
		else{
			System.out.println("해당 노래를 삭제하였습니다.");
		}
		}
	}
}
