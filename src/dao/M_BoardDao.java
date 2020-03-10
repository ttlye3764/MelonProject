package dao;

import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import service.M_BoardService;
import vo.M_BoardVO;

public class M_BoardDao {

   private static M_BoardDao instance;
   
   private M_BoardDao(){}
   
   public static M_BoardDao getInstance(){
      if(instance == null){ // instance 가 null이면 객체 생성
         instance = new M_BoardDao(); //userdao 객체를 생성해서 instance에 저장
      }
      return instance;
   }
   
   Database database = Database.getInstance();
   
   public void insertboard(M_BoardVO board){
	   // 이거 수정해야됌 중복 되면 삽입안되게
		  database.tb_m_board.add(board);
   }
   
  

   public ArrayList<M_BoardVO> M_BoardList() {
	
	return database.tb_m_board;
   }
   
  
   
   
   public M_BoardVO selectM_borad(int number) {
		  
		 M_BoardVO m_boardvo = new M_BoardVO();
		
		
		for(int i = 0 ; i<database.tb_m_board.size();i++){
			if(database.tb_m_board.get(i).getM_b_number() == number){
				m_boardvo = database.tb_m_board.get(i);
				
			}else{
				m_boardvo = null;
			}
		}
		return m_boardvo;
	}
   

   public void modifyM_board(M_BoardVO param) {
	   
	   for(int i = 0; i<database.tb_m_board.size();i++) {
			if(param.getM_b_number()==database.tb_m_board.get(i).getM_b_number()) {
				database.tb_m_board.set(database.tb_m_board.get(i).getM_b_number()-1, param);
			}
		}
		
	}
   
}