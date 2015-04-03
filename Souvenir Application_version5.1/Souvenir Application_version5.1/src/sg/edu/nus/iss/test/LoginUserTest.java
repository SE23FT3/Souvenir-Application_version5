package sg.edu.nus.iss.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.iss.service.LoginUser;
import sg.edu.nus.iss.service.StoreManager;

public class LoginUserTest {
	private StoreManager mg ;
	private LoginUser user1 ;

	@Before
	public void setUp() throws Exception {
		 mg = new StoreManager();
		 user1 = new LoginUser(mg);
	}

	@After
	public void tearDown() throws Exception {
		mg = null;
		user1 = null;
		
	}


	@Test
	public void testValidatelogin() throws IOException {
		assertTrue(user1.validatelogin("abc","1"));
	}

//	@Test
//	public void testChangePassword() {
//		assertTrue(user1.changePassword("abc", "1", "000"));
//		//fail("Not yet implemented");
//	}


	

}
