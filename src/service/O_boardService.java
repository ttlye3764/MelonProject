package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import vo.O_BoardVO;
import dao.O_boardDao;
import data.Database;
import data.Session;

public class O_boardService {

	private static O_boardService instance;
	private static int num = 1;
	O_boardService() {
	}

	public static O_boardService getInstance() {
		if (instance == null) {
			instance = new O_boardService();
		}
		return instance;
	}

	O_boardDao o_boardDao = O_boardDao.getInstance();

	// 공지사항 리스트 보기
	public void o_boardList() {
		O_boardDao o_boardDao = O_boardDao.getInstance();
		ArrayList<O_BoardVO> o_boardList = o_boardDao.o_boardList();
		System.out.println("---------------------------");
		System.out.println("번호 |  제목  | 내용  |  등록자   |  등록일");
		for (int i = 0; i < o_boardList.size(); i++) {
		
			System.out.println(
					o_boardList.get(i).getO_b_number()
					+ "\t"
					+ 	o_boardList.get(i).getO_b_name()
					+ "\t"
					+ 	o_boardList.get(i).getO_b_content()
					+ "\t"
					+ 	o_boardList.get(i).getAd_name()
					+ "\t"
					+ 	o_boardList.get(i).getO_b_date()
					+ "\t");
		}

	}

	// 공지사항 등록
	public void insertO_board() {
		O_BoardVO o_board = new O_BoardVO();
		SimpleDateFormat format = new SimpleDateFormat("YYYY년MM월dd일 hh시mm분");
		Date time = new Date();
		

		Scanner scan = new Scanner(System.in);

		System.out.println("공지사항 제목을 입력해주세요");
		String name = scan.nextLine();
		
		System.out.println("공지사항 내용을 입력해주세요");
		String content = scan.nextLine();

		o_board.setO_b_name(name);
		o_board.setO_b_content(content);
		o_board.setAd_name(Session.LoginUser.getU_id());
		o_board.setO_b_date(format.format(time));
		o_board.setO_b_number(num);
		num++;
		o_boardDao.insertO_borad(o_board);
		System.out.println("공지사항이 등록되었습니다.");
	}

	// 공지사항 수정
	public void modifyO_board() {
		Scanner scan = new Scanner(System.in);
		int number;
		O_BoardVO board = new O_BoardVO();
		o_boardList();
		System.out.println("수정할 게시판의 번호를 입력해주세요");
		number = Integer.parseInt(scan.nextLine());

		board = o_boardDao.selectO_board(number);
		if (board == null) {
			System.out.println("해당하는 번호의 공지사항이 없습니다.");
		} else {
			System.out.println("변경할 공지사항 제목을 입력해주세요");
			String name = scan.nextLine();
			System.out.println("변경할 공지사항 내용을 입력해해주세요");
			String content = scan.nextLine();

			board.setO_b_name(name);
			board.setO_b_content(content);
			board.setO_b_number(number);

			o_boardDao.modifyO_board(board);
		}

	}

	public void deleteO_board() {
		Scanner scan = new Scanner(System.in);
		int number;
		o_boardList();
		System.out.println("삭제할 게시판의 번호를 입력해주세요");
		number = Integer.parseInt(scan.nextLine());

		O_BoardVO board = o_boardDao.selectO_board(number);
		if (board == null) {
			System.out.println("해당하는 번호의 공지사항이 없습니다.");
		} else {
			o_boardDao.deleteO_board(number);
		}
	}

}
