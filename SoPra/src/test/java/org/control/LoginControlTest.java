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
			DatabaseControl.getInstance().save(newUser);
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
		User newUser = (User) DatabaseControl.getInstance().load(User.class, "LoginTestUser");
		DatabaseControl.getInstance().delete(newUser);
		
		assertTrue(DatabaseControl.getInstance().load(User.class, "LoginTestUser") == null);
		
	}

}
