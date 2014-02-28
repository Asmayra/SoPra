package org.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.model.User;


public class RegControlTest {
	
	
	@BeforeClass
	public static void createTestUser(){
		User newUser = new User();
		
		newUser.setUsername("TestUser");
		
		
		
		try{
			DatabaseController.getInstance().save(newUser);
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
		assertTrue(RegControl.getInstance().checkRegistration("Testuser", "Testname", "Testnachname", "TestStadt", "TestLand", "TestDob", "TestMail"));
		assertFalse(RegControl.getInstance().checkRegistration(null, "Testname", "Testnachname", "TestStadt", "TestLand", "TestDob", "TestMail"));
		assertTrue(RegControl.getInstance().checkRegistration("Testuser", "Testname", "Testnachname", null, null, "TestDob", "TestMail"));
	}
	
	
	
	
	@Test
	public void userExistTest(){
		assertTrue(RegControl.getInstance().userExists("TestUser"));
	}
	
	
	
	@AfterClass
	public static void deleteTestuser(){
		//Testuser aus Datenbank löschen
	}

}
