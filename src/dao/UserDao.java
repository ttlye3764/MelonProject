package dao;
//1234
import java.util.ArrayList;
import java.util.HashMap;

import data.Database;
import vo.UserVO;

public class UserDao {

	private static UserDao instance;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	Database database = Database.getInstance();

	public void insertUser(UserVO user) {
		boolean check = true;
		for (int i= 0; i < database.tb_user.size(); i++) {
			if((database.tb_user.get(i).getU_id()).equals(user.getU_id())){
				System.out.println("중복된 아이디 입니다.");
				check = false;
				break;
			}							
		}
		if(check){
			database.tb_user.add(user);	
			System.out.println("가입해주셔서 감사합니다.");
		}
	}

	public UserVO selectUser(HashMap<String, String> param) {
			UserVO rtnUser = null;
			for (int i = 0; i < database.tb_user.size(); i++) {
				UserVO user = database .tb_user.get(i);
				boolean flag  = true;
				for(String key : param.keySet()){
					String value = param.get(key);
					if(key.equals("ID")){
						if(!user.getU_id().equals(value)) flag = false;
					}else if(key.equals("PASSWORD")){
						if(!user.getU_pw().equals(value)) flag = false;
					}else if(key.equals("NAME")){
						if(!user.getU_name().equals(value)) flag = false;
					}
				}
				 if(flag) rtnUser = user;
			}
		return rtnUser;
	}

	
	public ArrayList<UserVO> selectUserList() {
		
		return database.tb_user;
	}
	
	
	public  void deleteUser(UserVO param){  //회원 탈퇴 03.09
		boolean check = false;
		
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);

			if (user.getU_id().equals(param.getU_id()) && user.getU_pw().equals(param.getU_pw())) {
				database.tb_user.remove(i);
				check = true;
				break;
			} else {
				check = false;
			}
		}
		if(check){
			System.out.println("회원탈퇴가 성공적으로 되었습니다.");			
		}
		else{
			System.out.println("입력한 정보가 틀립니다.");
		}
	}
}
