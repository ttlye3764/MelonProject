package service;

import java.nio.channels.SeekableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import vo.MusicVO;
import vo.PlayListVO;
import vo.R_playListVO;
import vo.UserVO;
import dao.MusicDao;
import dao.UserDao;
import data.Session;

public class MusicService {
	
	private static MusicService instance;
	private MusicService(){}
	
	public static MusicService getInstance(){
		if(instance == null){
			instance = new MusicService();
		}
		return instance;
	}
	
	MusicDao musicDao = MusicDao.getInstance();
	
	
	public void InsertMusic(){						//노래 추가
		Scanner s = new Scanner(System.in);
		MusicVO music = new MusicVO();
		
		System.out.print("노래 등록 번호 : ");
		int number= Integer.parseInt(s.nextLine()); 
		System.out.print("노래 제목 : ");
		String name = s.nextLine();
		System.out.print("가수 : ");
		String singer= s.nextLine();
		System.out.print("작사 : ");
		String lyrics = s.nextLine();
		System.out.print("작곡 : ");
		String composition= s.nextLine();
		System.out.print("장르 : ");
		String genre = s.nextLine();
		System.out.print("등록 날짜 : ");
		String date= s.nextLine();
		System.out.print("들은 횟수 : ");
		int count = Integer.parseInt(s.nextLine());		
		
		music.setM_composition(composition);
		music.setM_count(count);
		music.setM_date(date);
		music.setM_genre(genre);
		music.setM_lyrics(lyrics);
		music.setM_name(name);
		music.setM_number(number);
		music.setM_singer(singer);
		
		musicDao.insertMusic(music); 			
	}
	
	
	public void searchMusic(){					//음악 찾기 및 플레이 리스트에 추가하는부분
		Scanner s = new Scanner(System.in);
		System.out.println("찾을 노래 정보를 입력하세요.");
		System.out.print("노래제목 : ");
		String name = s.nextLine();
		System.out.print("가수 : ");
		String singer= s.nextLine();
		
		MusicVO music = new MusicVO();
			
		music.setM_name(name);
		music.setM_singer(singer);
		
		music = musicDao.searchMusic(music);
		if(music == null){
			System.out.println("찾으시는 노래가 없습니다.");
		}else{
			System.out.println("찾은 노래");
			System.out.println("등록번호\t노래제목\t가수\t작사\t작곡\t장르\t음원날짜\t들은횟수\t");
			System.out.println(music.getM_number() + "\t" + music.getM_name() + "\t" + music.getM_singer() + "\t" + music.getM_lyrics() + "\t" +
					music.getM_composition() + "\t" + music.getM_genre() + "\t" + music.getM_date() + "\t" + music.getM_count());
			System.out.println("1. 듣기               2. 리스트에 담기");
			int check = Integer.parseInt(s.nextLine());
			
			switch(check){
			case 1:
				musicDao.insertR_playList(music);				//최근들은노래리스트에 음악 insert 부분
				musicDao.CountPlus(music);
				break;
			case 2:
				musicDao.insert_playList(music);					//사용자 플레이리스트에 음악 insert 부분
				break;
			}
			
			
		}
	}
	
	public void deleteMusic(){				//노래 삭제
		Scanner s = new Scanner(System.in);
		System.out.println("삭제할 노래 정보를 입력하세요.");
		System.out.print("노래제목 : ");
		String name = s.nextLine();
		System.out.print("가수 : ");
		String singer= s.nextLine();
		
		MusicVO music = new MusicVO();
		
		music.setM_name(name);
		music.setM_singer(singer);
		
		musicDao.deleteMusic(music);		
	}
	
	public void MusicList(){		//전체 노래 리스트 보기
		ArrayList<MusicVO> MusicList = musicDao.MusicList();
		
		System.out.println("등록번호\t노래제목\t가수\t작사\t작곡\t장르\t음원날짜\t들은횟수\t");
		
		for (int i = 0; i < MusicList.size(); i++) {
			MusicVO music = MusicList.get(i);
			System.out.println(music.getM_number() + "\t" + music.getM_name() + "\t" + music.getM_singer() + "\t" + music.getM_lyrics() + "\t" +
			music.getM_composition() + "\t" + music.getM_genre() + "\t" + music.getM_date() + "\t" + music.getM_count());
		}		
	}

	public void Show_R_musicList() {		//최근들은 노래 리스트 보기
		
		ArrayList<MusicVO> musicList = musicDao.MusicList();
		ArrayList<R_playListVO> r_playlist = musicDao.R_PlayList();  //m_number  노래등록번호
		ArrayList<MusicVO> r_list = new ArrayList<>(); 
		UserVO uservo = Session.LoginUser;
		System.out.println("최근 들은 노래 리스트");
		
		for (int i = 0; i < r_playlist.size(); i++) {
			if(r_playlist.get(i).getU_id().equals(uservo.getU_id())){
				for (int j = 0; j < musicList.size(); j++) {
					if(musicList.get(j).getM_number() == r_playlist.get(i).getM_number()){
						System.out.println(musicList.get(j).getM_number() + "\t" + musicList.get(j).getM_name() + "\t" + musicList.get(j).getM_singer());
					}
				}
			}			
		}
	}
	
	
public void Show_MusicList() {					//사용자 플레이 리스트 보기
		
		ArrayList<MusicVO> musicList = musicDao.MusicList();
		ArrayList<PlayListVO> playlist = musicDao.PlayList();  //m_number  노래등록번호
		ArrayList<MusicVO> list = new ArrayList<>(); 
		UserVO uservo = Session.LoginUser;
		System.out.println("플레이 리스트");
		
		for (int i = 0; i < playlist.size(); i++) {
			if(playlist.get(i).getU_id().equals(uservo.getU_id())){
				for (int j = 0; j < musicList.size(); j++) {
					if(musicList.get(j).getM_number() == playlist.get(i).getM_number()){
						System.out.println(musicList.get(j).getM_number() + "\t" + musicList.get(j).getM_name() + "\t" + musicList.get(j).getM_singer());
					}
				}
			}			
		}
	}
	
public void Genre_MusicChart(){		//장르별 차트 보기
	ArrayList<MusicVO> MusicList = musicDao.MusicList();		
		
	
	MusicVO musicvo = new MusicVO();
	ArrayList<MusicVO> bal = new ArrayList<>();
	ArrayList<MusicVO> dan = new ArrayList<>();
	ArrayList<MusicVO> rb = new ArrayList<>();
	ArrayList<MusicVO> pop = new ArrayList<>();
	ArrayList<MusicVO> hip = new ArrayList<>();
	
	for (int i = 0; i < MusicList.size(); i++) {
		switch(MusicList.get(i).getM_genre()){
		case "발라드":
			bal.add(MusicList.get(i));
			break;
			
		case "Dance":
			dan.add(MusicList.get(i));
			break;
			
		case "R&B":
			rb.add(MusicList.get(i));
			break;
			
		case "POP":
			pop.add(MusicList.get(i));
			break;
			
		case "힙합":
			hip.add(MusicList.get(i));
			break;
			
		}
	}
	MusicVO[] balChart = new MusicVO[bal.size()];
	MusicVO[] danChart = new MusicVO[dan.size()];
	MusicVO[] rbChart = new MusicVO[rb.size()];
	MusicVO[] popChart = new MusicVO[pop.size()];
	MusicVO[] hipChart = new MusicVO[hip.size()];
	
	for (int i = 0; i < bal.size(); i++) {
		balChart[i] = bal.get(i);
	}
	for (int i = 0; i < dan.size(); i++) {
		danChart[i] = dan.get(i);
	}
	for (int i = 0; i < rb.size(); i++) {
		rbChart[i] = rb.get(i);
	}
	for (int i = 0; i < pop.size(); i++) {
		popChart[i] = pop.get(i);
	}
	for (int i = 0; i < hip.size(); i++) {
		hipChart[i] = hip.get(i);
	}
	
	for (int i = 0; i < balChart.length - 1; i++) { 
		for (int j = i + 1; j < balChart.length; j++) {
			if (balChart[i].getM_count() < balChart[j].getM_count()) {
				musicvo = balChart[i];
				balChart[i] = balChart[j];
				balChart[j] = musicvo;
			}
		}
	}
	for (int i = 0; i < danChart.length - 1; i++) { 
		for (int j = i + 1; j < danChart.length; j++) {
			if (danChart[i].getM_count() < danChart[j].getM_count()) {
				musicvo = danChart[i];
				danChart[i] = danChart[j];
				danChart[j] = musicvo;
			}
		}
	}
	for (int i = 0; i < rbChart.length - 1; i++) { 
		for (int j = i + 1; j < rbChart.length; j++) {
			if (rbChart[i].getM_count() < rbChart[j].getM_count()) {
				musicvo = rbChart[i];
				rbChart[i] = rbChart[j];
				rbChart[j] = musicvo;
			}
		}
	}
	for (int i = 0; i < popChart.length - 1; i++) { 
		for (int j = i + 1; j < popChart.length; j++) {
			if (popChart[i].getM_count() < popChart[j].getM_count()) {
				musicvo = popChart[i];
				popChart[i] = popChart[j];
				popChart[j] = musicvo;
			}
		}
	}
	for (int i = 0; i < hipChart.length - 1; i++) { 
		for (int j = i + 1; j < hipChart.length; j++) {
			if (hipChart[i].getM_count() < hipChart[j].getM_count()) {
				musicvo = hipChart[i];
				hipChart[i] = hipChart[j];
				hipChart[j] = musicvo;
			}
		}
	}
	
	System.out.println("-----------------------발라드 순위 차트-----------------------");
	System.out.println("순위 \t 노래 제목  \t 가수");
	
	for (int i = 0; i < bal.size(); i++) {
		MusicVO music = balChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer());
	}		
	System.out.println("-----------------------댄스 순위 차트-----------------------");
	System.out.println("순위 \t 노래 제목  \t 가수");
	
	for (int i = 0; i < dan.size(); i++) {
		MusicVO music = danChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer());
	}		
	System.out.println("-----------------------R&B 순위 차트-----------------------");
	System.out.println("순위 \t 노래 제목  \t 가수");
	
	for (int i = 0; i < rb.size(); i++) {
		MusicVO music = rbChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer());
	}		
	System.out.println("-----------------------POP 순위 차트-----------------------");
	System.out.println("순위 \t 노래 제목  \t 가수");
	
	for (int i = 0; i < pop.size(); i++) {
		MusicVO music = popChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer());
	}		
	System.out.println("-----------------------힙합 순위 차트-----------------------");
	System.out.println("순위 \t 노래 제목  \t 가수");
	
	for (int i = 0; i < hip.size(); i++) {
		MusicVO music = hipChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer());
	}		
	
	
	
}
public void MusicChart(){		//인기 차트 보기
	ArrayList<MusicVO> MusicList = musicDao.MusicList();
	
	MusicVO[] musicChart = new MusicVO[MusicList.size()];		
	MusicVO musicvo = new MusicVO();
	
	
	for (int i = 0; i < MusicList.size(); i++) {
		musicChart[i] = MusicList.get(i);
	}
	
	for (int i = 0; i < musicChart.length - 1; i++) { 
		for (int j = i + 1; j < musicChart.length; j++) {
			if (musicChart[i].getM_count() < musicChart[j].getM_count()) {
				musicvo = musicChart[i];
				musicChart[i] = musicChart[j];
				musicChart[j] = musicvo;
			}
		}
	}
	
	System.out.println("-----------------------인기  차트-----------------------");
	System.out.println("등록번호\t노래제목\t가수\t작사\t작곡\t장르\t음원날짜\t들은횟수\t");
	
	for (int i = 0; i < musicChart.length; i++) {
		MusicVO music = musicChart[i];
		System.out.println((i+1) + "위" + "\t" + music.getM_name() + "\t" + music.getM_singer() + "\t" + music.getM_lyrics() + "\t" +
		music.getM_composition() + "\t" + music.getM_genre() + "\t" + music.getM_date() + "\t" + music.getM_count());
	}		
}



public void R_MusicChart(){		//최신곡 차트 보기
	ArrayList<MusicVO> MusicList = musicDao.MusicList();
	
	MusicVO[] musicChart = new MusicVO[MusicList.size()];		
	MusicVO musicvo = new MusicVO();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	for (int i = 0; i < MusicList.size(); i++) {
		musicChart[i] = MusicList.get(i);
	}
	Date day1 = null;
	Date day2 = null;
	
	for (int i = 0; i < musicChart.length - 1; i++) { 
		for (int j = i + 1; j < musicChart.length; j++) {
			try {
				day1 = sdf.parse(musicChart[i].getM_date());
				day2 = sdf.parse(musicChart[j].getM_date());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			int compare = day1.compareTo(day2);				
			
			if (compare < 0) {
				musicvo = musicChart[i];
				musicChart[i] = musicChart[j];
				musicChart[j] = musicvo;
			}
		}
	}
	
	System.out.println("-----------------------최신곡  차트-----------------------");
	System.out.println("등록번호\t노래제목\t가수\t작사\t작곡\t장르\t음원날짜\t들은횟수\t");
	
	for (int i = 0; i < musicChart.length; i++) {
		MusicVO music = musicChart[i];
		System.out.println((i+1) + "\t" + music.getM_name() + "\t" + music.getM_singer() + "\t" + music.getM_lyrics() + "\t" +
		music.getM_composition() + "\t" + music.getM_genre() + "\t" + music.getM_date() + "\t" + music.getM_count());
	}		
}
	
	
}
