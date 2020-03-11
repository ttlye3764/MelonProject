package service;

import java.util.Scanner;

import controller.Controller;
import data.Session;

public class AdminService {
	private static AdminService instance;
	
	private AdminService(){}
	
	public static AdminService getInstance(){
		if(instance == null){
			instance = new AdminService();
		}
		return instance;
	}


	
	//회원 관리 메소드
	
	
	
	
	
	
	
	//노래 추천 게시판 관리
	void m_boradMange() {
		
		
	}
	
	//공지사항 게시판 관리
	void o_boradMange() {
		
		
	}
	
}
