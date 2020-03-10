package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.M_BoardDao;
import data.Database;
import data.Session;
import vo.M_BoardVO;
import vo.UserVO;

public class M_BoardService {
	
	private static M_BoardService instance;
	
	private M_BoardService(){}
	
	public static M_BoardService getInstance(){
		if(instance == null){
			instance = new M_BoardService();
		}
		return instance;
	}
	
	M_BoardDao m_boardDao = M_BoardDao.getInstance();
	Database database = Database.getInstance();
	
	//작성
	public void insertM_Board(){
		
		M_BoardVO board = new M_BoardVO();
		
        Scanner scan = new Scanner(System.in);
        System.out.println("제목을 입력하세요");
        String name = scan.nextLine();
        System.out.println("내용을 적어주세요");
        String content = scan.nextLine();
        System.out.println("게시글 번호를 입력해주세요");
        int number = Integer.parseInt(scan.nextLine());
        System.out.println("날짜를 적어주세요");
        String date = scan.nextLine();
        
        System.out.println("게시글이 등록되었습니다");
        
        
             
		
		board.setM_b_name(name);
		board.setU_id(Session.LoginUser.getU_id());
		board.setM_b_content(content);
		board.setM_b_number(number);
		board.setM_b_date(date);
		
		
		m_boardDao.insertboard(board);
		
       
 
       
	}
	
	//수정
	public void modifyM_Board(){
		M_BoardVO m_boardvo = new M_BoardVO();
		Scanner sc = new Scanner(System.in);
		M_BoardDao m_boardDao = M_BoardDao.getInstance();
		int number;
		//저장되어있는 게시글을 전체조회
		m_BoardList();

		System.out.println("게시글 번호를 선택해주세요");
		number = Integer.parseInt(sc.nextLine());
		
		m_boardvo = m_boardDao.selectM_borad(number);
		
		if(m_boardvo == null){
			System.out.println("해당하는 번호의 게시글이 없습니다.");
		}else{
			if(m_boardvo.getU_id().equals(Session.LoginUser.getU_id())){
				System.out.println("변경할 제목을 입력해주세요");
				String name = sc.nextLine();
				System.out.println("변경할 내용을 입력해주세요");
				String content = sc.nextLine();
				
				
				m_boardvo.setM_b_name(name);
				m_boardvo.setM_b_content(content);
				
				 
				m_boardDao.modifyM_board(m_boardvo);
				
			}
		}
		
		
		
		
	}
	

	/*//삭제
	public void deleteM_Board(){
		M_BoardVO m_boardvo = new M_BoardVO();
		M_BoardDao m_boardDao = M_Boa
		Scanner sc = new Scanner(System.in);
		int number;
		number = Integer.parseInt(sc.nextLine());
		
		M_BoardVO boardvo = SelectM_board(number);
		if(m_boardvo.getM_u_id() == Session.loginUser.getId()){
			System.out.println("게시자가 일치하지 않습니다");
		}else{
			m_boardDao.deleteM_board(number);
		}
	}*/
			
		
	
	//조회
	public void m_BoardList(){
		ArrayList<M_BoardVO> boardList = m_boardDao.M_BoardList();
		
		System.out.println("-----------------------------------");
		System.out.println("번호\t제목\t내용\t이름\t날짜");
		System.out.println("-----------------------------------");
		for(int i = 0 ; i< boardList.size() ; i++){
			System.out.println(i + 1 + "\t" + boardList.get(i).getM_b_name()
					+"\t"+ boardList.get(i).getM_b_content()
					+"\t"+boardList.get(i).getU_id()
					+"\t"+boardList.get(i).getM_number()
					+"\t"+boardList.get(i).getM_b_date());
		}
		
		
	}
	


}
