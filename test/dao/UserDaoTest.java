package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import data.Database;
import vo.UserVO;

public class UserDaoTest {

	
	//테스트 메소드 설정 방법
	//메소드에 @Test 어노테이션을 붙인다. ==> 테스트 메소드로 인식
	//@Test 어노테이션이 붙은 테스트 메소드는 접근제어자 : public 리턴타입 : void
	
	//jUnit 프레임워크의 실행순서
	//@Before --> @Test --> @After
	UserDao userDao;
	
	@Before
	public void setup(){
		userDao = UserDao.getInstance();
	}
	
	
	
	//테스트 메소드 이름 : 운영메소드 명 + Test
	//insertUser 메소드 테스트
	
	@Test
	public void insertUserTest() {
		//insertUser 메소드를 실행하기 위해 필요한 것
		//1. userDao의 인스턴스 필요
		//2. insertUser 메소드의 인자 ==> UserVo
		
		//given : 주어진 상황
		UserVO userVo = new UserVO();
		//사용자로부터 입력받았다고 가정한 값을 userVO에 넣어준다.
		userVo.setU_id("brown");
		userVo.setU_pw("brown_pass");
		userVo.setU_name("브라운");
		
		
		//when : 행동(메소드 실행)
		userDao.insertUser(userVo);
		
		//then : 그 결과
		//한 건의 사용자를 추가했으므로 tb_user데이터는 2건이 된다.
		assertEquals(2, Database.getInstance().tb_user.size());		
	}
	
	
	
	//selectUserList(사용자 전체 리스트 테스트)
	/**
	 * 
	 */
	@Test
	public void selectUserListTest(){
		//given (userDao 인스턴스)
		
		//when
		ArrayList<UserVO> userList = userDao.selectUserList();
		
		//then
		assertNotNull(userList);
		assertTrue(userList.size()>=1);
		
	}
	
	
	
	
	
	//selectUserTest
	@Test
	public void selectUserSuccessTest(){
		//given (userDao 인스턴스, HashMap)
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		//Database가 초기화 될때 admin이라는 사용자가 tb_user 리스트에 들어가있다.
		paramMap.put("ID","1");
		
		//when
		UserVO userVo = userDao.selectUser(paramMap);
		
		//then
		UserVO expectedUserVo = new UserVO();
		expectedUserVo.setU_id("1");
		expectedUserVo.setU_name("관리자");
		expectedUserVo.setU_pw("1");
		
		assertNotNull(userVo);
		assertEquals("1", userVo.getU_pw());
		assertEquals("관리자", userVo.getU_name());
		assertEquals(expectedUserVo, userVo);
		
		//객체의 값을 비교하기 위해서는 equals 메소드를 override 해야한다.
		//객체 동일, 동치
		//UserVO userVO = new UserVO();
		//UserVO userVO2 = UserVO;
		//userVO 와 userVO2는 동일
		
		//UserVO userVO = new UserVO();
		//userVO.setId("admin");
		//userVO.setPassword("admin");
		//userVO.setName("관리자");

		//UserVO userVO2 = new UserVO();
		//userVO.setId("admin");
		//userVO.setPassword("admin");
		//userVO.setName("관리자");
		
		//userVO 와 userVO2는 같은 값을 갖는다.
		//if(userVO == userVO2) ->동일에 대한 비교
		//if(userVO.equals(userVO2)) ->동치에 대한 비교
	}
	
	//selectUser의 실패 케이스
	@Test
	public void selectUserFailTest(){
		//given : userDao 인스턴스, HashMap
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("ID","NOT_EXISTS_ID");
		
		//when
		UserVO userVo = userDao.selectUser(paramMap);
		
		//then
		assertNull(userVo);
	}
	
	@Test
	public void selectUserNotExistsKeyTest(){
		//given : userDao 인스턴스, HashMap
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("NONE","NOT_EXISTS_KEY");
		
		//when
		UserVO userVo = userDao.selectUser(paramMap);
		
		//then
		assertNull(userVo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
