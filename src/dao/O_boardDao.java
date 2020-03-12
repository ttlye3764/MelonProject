package dao;

import java.util.ArrayList;

import data.Database;
import vo.O_BoardVO;

public class O_boardDao {
	
	private static O_boardDao instance;
	
	O_boardDao(){}
	
	public static O_boardDao getInstance() {
		if(instance == null) {
			instance = new O_boardDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	// 공지사항 보기
	public ArrayList<O_BoardVO> o_boardList(){
	
		return database.tb_o_board;
	}
	
	public void insertO_borad(O_BoardVO board){
		
		boolean check = true;
		
		for(int i = 0; i< database.tb_o_board.size(); i++) {
			if(database.tb_o_board.get(i).getO_b_number() == board.getO_b_number())
			{
				System.out.println("중복된 등록번호입니다.");
				check = false;
				break;
			}
			if(database.tb_o_board.get(i).getO_b_name().equals(board.getO_b_name())) {
				System.out.println("중복된 제목입니다.");
				check = false;
				break;
			}
			
			if(check) {
				System.out.println("공지사항이 등록되었습니다.");
				database.tb_o_board.add(board);
				break;
			}
			
		}
	}
	
	public O_BoardVO selectO_board(int number) {
		
		O_BoardVO board = null;
		
		for(int i = 0 ; i< database.tb_o_board.size();i++) {
			if(database.tb_o_board.get(i).getO_b_number() == number) {
				board = database.tb_o_board.get(i);
			}
		}
		
		return board;
		
	}

	public void modifyO_board(O_BoardVO param) {
		for(int i = 0; i<database.tb_o_board.size();i++) {
			if(param.getO_b_number()==database.tb_o_board.get(i).getO_b_number()) {
				database.tb_o_board.set(database.tb_o_board.get(i).getO_b_number()-1,param);
			}
		}
		
	}
	
	public void deleteO_board(int number) {
		for(int i = 0 ; i<database.tb_o_board.size(); i++)
			if(database.tb_o_board.get(i).getO_b_number() == number) {
				database.tb_o_board.remove(i);
				System.out.println("삭제 되었습니다.");
			}
	}
	
	
}
