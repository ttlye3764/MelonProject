package dao;

import vo.UserVO;
import data.Database;

public class AdminDao {
	private static AdminDao instance;
	
	private AdminDao(){}
	
	public static AdminDao getinstance(){
		if(instance == null){
			instance = new AdminDao();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	public void AdmindeleteUser(UserVO param){ //관리자에서  회원 삭제
		boolean check = false;
		for (int i = 0; i < database.tb_user.size(); i++) {
			UserVO user = database.tb_user.get(i);

			if (user.getU_id().equals(param.getU_id())) {
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
