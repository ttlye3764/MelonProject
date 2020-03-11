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

		// 어떻게 보여줄건지
		for (int i = 0; i < o_boardList.size(); i++) {
			O_BoardVO o_board = o_boardList.get(i);
			System.out.println(o_board.getO_b_name() + o_board.getAd_name()
					+ o_board.getO_b_content() + o_board.getO_b_number()
					+ o_board.getO_b_date());
		}

	}

	// 공지사항 등록
	public void insertO_board() {
		O_BoardVO o_board = new O_BoardVO();
		SimpleDateFormat format = new SimpleDateFormat("YYYY년MM월dd일 hh시mm분");
		Date time = new Date();
		Database database = Database.getInstance();

		Scanner scan = new Scanner(System.in);

		System.out.println("공지사항 제목을 입력해주세요");
		String name = scan.nextLine();
		System.out.println("공지사항 내용을 입력해주세요");
		String content = scan.nextLine();

		o_board.setO_b_name(name);
		o_board.setAd_name(Session.LoginUser.getU_id());
		o_board.setO_b_content(content);
		o_board.setO_b_date(format.format(time));
		o_board.setO_b_number(database.tb_o_board.size() + 1);

		o_boardDao.insertO_borad(o_board);
	}

	// 공지사항 수정
	public void modifyO_board() {
		Scanner scan = new Scanner(System.in);
		int number;
		O_BoardVO board = new O_BoardVO();
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
