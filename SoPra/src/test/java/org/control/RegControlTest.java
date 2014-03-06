package org.control;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.model.User;


public class RegControlTest {
	
	
	@BeforeClass
	public static void createTestUser(){
		User newUser = new User();
		
		newUser.setUsername("RegisterTestUser");
		
		
		
		try{
			DatabaseControl.getInstance().save(newUser);
		}catch(IOException e){
			System.out.println("Username already taken!");
		}
	}

	@Test
	public void checkPasswordTest() {
		assertTrue(RegControl.getInstance().checkPasswords("test1234", "test1234"));
		assertFalse(RegControl.getInstance().checkPasswords("test1234", "test12345"));
		assertFalse(RegControl.getInstance().checkPasswords("", ""));
	}
	
	@Test
	public void checkRegistration(){
		assertTrue(RegControl.getInstance().checkRegistration("Testuser", "Testname", "Testnachname", "TestStadt", "TestLand", new Date(), "TestMail"));
		assertFalse(RegControl.getInstance().checkRegistration(null, "Testname", "Testnachname", "TestStadt", "TestLand", new Date(), "TestMail"));
		assertTrue(RegControl.getInstance().checkRegistration("Testuser", "Testname", "Testnachname", null, null, new Date(), "TestMail"));
	}
	
	
	
	
	@Test
	public void userExistTest(){
		assertTrue(RegControl.getInstance().userExists("RegisterTestUser"));
	}
	
	
	
	@AfterClass
	public static void deleteTestuser(){
		User newUser = (User) DatabaseControl.getInstance().load(User.class, "RegisterTestUser");
		
		DatabaseControl.getInstance().delete(newUser);
		
		assertTrue(DatabaseControl.getInstance().load(User.class, "RegisterTestUser") == null);
	}

}
