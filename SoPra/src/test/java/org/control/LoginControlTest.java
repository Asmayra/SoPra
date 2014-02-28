package org.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.model.User;

public class LoginControlTest {
	
	
	@BeforeClass
	public static void createTestUser(){
		User newUser = new User();
		
		newUser.setUsername("LoginTestUser");
		newUser.setSalt(PasswordControl.generateSalt());
		newUser.setPassword(PasswordControl.encodePassword("Kennwort", newUser.getSalt()));
		
		
		try{
			DatabaseController.getInstance().save(newUser);
		}catch(IOException e){
			System.out.println("Username already taken!");
		}
	}

	@Test
	public void loginCheckTest() {
		assertTrue(LoginControl.getInstance().checkLogin("LoginTestUser", "Kennwort"));
		assertFalse(LoginControl.getInstance().checkLogin("LoginTestUser", "kennwort"));
		assertFalse(LoginControl.getInstance().checkLogin("LoginTestUser", ""));
	}
	
	
	
	
	@AfterClass
	public static void deleteTestUser(){
		User newUser = (User) DatabaseController.getInstance().load(User.class, "LoginTestUser");
		DatabaseController.getInstance().delete(newUser);
		
		assertTrue(DatabaseController.getInstance().load(User.class, "LoginTestUser") == null);
		
	}

}
