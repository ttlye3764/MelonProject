package service;

//test
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import vo.M_BoardVO;
import dao.M_BoardDao;
import data.Database;
import data.Session;

public class M_BoardService {

	private static M_BoardService instance;
	private static int num = 1;
	private M_BoardService() {
	}

	public static M_BoardService getInstance() {
		if (instance == null) {
			instance = new M_BoardService();
		}
		return instance;
	}

	M_BoardDao m_boardDao = M_BoardDao.getInstance();
	Database database = Database.getInstance();

	// 작성
	public void insertM_Board() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy년MM월dd일 hh시mm분");
		MusicService musicService = MusicService.getInstance();
		Date time = new Date();

		M_BoardVO board = new M_BoardVO();

		Scanner scan = new Scanner(System.in);
		System.out.println("제목을 입력하세요");
		String name = scan.nextLine();
		System.out.println("내용을 입력해주세요");
		String content = scan.nextLine();
		System.out.println("추천 노래 번호를 입력해주세요");
		int number = Integer.parseInt(scan.nextLine());

		board.setM_b_name(name);
		board.setU_id(Session.LoginUser.getU_id());
		board.setM_b_content(content);
		board.setM_number(number);
		board.setM_b_date(format.format(time));
		board.setM_b_number(num);
		num++;
		boolean check = true;

		if (musicService.searchNumberMusic(number) == null) {

			System.out.println("해당하는 번호의 노래가 없습니다.");
			check = false;
		}

		if (check) {
			System.out.println("게시글이 등록되었습니다.");
			m_boardDao.insertboard(board);
		}
	}

	// 수정
	public void modifyM_Board() {
		M_BoardVO m_boardvo = new M_BoardVO();
		Scanner scan = new Scanner(System.in);
		M_BoardDao m_boardDao = M_BoardDao.getInstance();
		int number;
		// 저장되어있는 게시글을 전체조회
		m_BoardList();

		System.out.println("게시글 번호를 선택해주세요");
		number = Integer.parseInt(scan.nextLine());

		m_boardvo = m_boardDao.selectM_borad(number);

		if (m_boardvo == null) {
			System.out.println("해당하는 번호의 게시글이 없습니다.");
		} else {
			if (m_boardvo.getU_id().equals(Session.LoginUser.getU_id())) {
				System.out.println("변경할 제목을 입력해주세요");
				String name = scan.nextLine();
				System.out.println("변경할 내용을 입력해주세요");
				String content = scan.nextLine();
				System.out.println("변경할 추천 노래번호를 입력해주세요");
				int m_Number = Integer.parseInt(scan.nextLine());

				m_boardvo.setM_b_name(name);
				m_boardvo.setM_b_content(content);
				m_boardvo.setM_number(m_Number);
				m_boardvo.setM_b_number(number);

				m_boardDao.modifyM_board(m_boardvo);
			}
		}
	}

	public void deleteM_Board() {
		M_BoardVO m_boardVO = new M_BoardVO();
		Scanner sc = new Scanner(System.in);

		m_BoardList();
		System.out.println("삭제할 게시글의 번호를 선택해주세요");
		int number = Integer.parseInt(sc.nextLine());

		m_boardVO = m_boardDao.selectM_borad(number);	
		if (m_boardVO.getU_id().equals(Session.LoginUser.getU_id())
				|| Session.LoginUser.getU_id().equals("admin")) {
			m_boardDao.deleteM_Board(number);
		} else {
			System.out.println("게시자가 다릅니다");
		}
	}

	// 조회
	public void m_BoardList() {

		M_BoardDao m_boardDao = M_BoardDao.getInstance();
		MusicService musicService = MusicService.getInstance();
		ArrayList<M_BoardVO> m_boardList = m_boardDao.boardList();

		System.out.println("-----------------------------------");
		System.out.println("번호\t제목\t내용\t글쓴이\t날짜\t추천 노래번호\t제목\t가수");
		System.out.println("-----------------------------------");

		for (int i = 0; i < m_boardList.size(); i++) {
			System.out.println(m_boardList.get(i).getM_b_number()
					+ "\t"
					+ m_boardList.get(i).getM_b_name()
					+ "\t"
					+ m_boardList.get(i).getM_b_content()
					+ "\t"
					+ m_boardList.get(i).getU_id()
					+ "\t"
					+ m_boardList.get(i).getM_b_date()
					+ "\t"
					+ m_boardList.get(i).getM_number()
					+ "\t"
					+ musicService.searchNumberMusic(
							m_boardList.get(i).getM_number()).getM_name()
					+ "\t"
					+ musicService.searchNumberMusic(
							m_boardList.get(i).getM_number()).getM_singer()
					+ "\t");
		}

	}

}
