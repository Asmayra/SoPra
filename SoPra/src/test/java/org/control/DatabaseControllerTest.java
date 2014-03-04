package org.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.model.User;

public class DatabaseControllerTest {
	
	
	private static User dbTestUser, dbTestUser2;
	
	
	
	@Test
	public void loadTest()
	{
		dbTestUser = null;
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		assertTrue(dbTestUser != null);
	}
	
	@Test
	public void updateTest()
	{
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		dbTestUser.setFirstname("Wurst");
		DatabaseController.getInstance().update(dbTestUser);
		
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		
		assertTrue(dbTestUser.getFirstname().equals("Wurst"));
	}
	
	@Test
	public void updateNonExistingTest()
	{
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		dbTestUser.setUsername("dbTestUser2");
		DatabaseController.getInstance().update(dbTestUser);
		
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser2");
		dbTestUser2 = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		
		assertTrue(dbTestUser != null);
		assertTrue(dbTestUser2 != null);
	}
	
	@Test
	public void updatePrimaryTest()
	{
		User dbTestUser3 = new User();
		dbTestUser3.setUsername("dbTestUser3");
		
		DatabaseController.getInstance().updatePrimary(User.class, "dbTestUser", dbTestUser3);
		
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		
		dbTestUser2 = (User) DatabaseController.getInstance().load(User.class, "dbTestUser3");
		
		assertTrue(dbTestUser == null);
		assertTrue(dbTestUser2 != null);
	}
	
	@Test
	public void searchTest()
	{
		assertTrue(!DatabaseController.getInstance().queryForKeyword(User.class, "firstname", "ans").isEmpty());
	}	
		
	@Before
	public void before()
	{
		dbTestUser = new User();
		dbTestUser.setUsername("dbTestUser");
		dbTestUser.setFirstname("Hans");
		try {
			DatabaseController.getInstance().save(dbTestUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertTrue(false);
		}
	}
	
	@After
	public void after()
	{
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser");
		if(dbTestUser != null)
			DatabaseController.getInstance().delete(dbTestUser);
		
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser2");
		if(dbTestUser != null)
			DatabaseController.getInstance().delete(dbTestUser);
		
		dbTestUser = (User) DatabaseController.getInstance().load(User.class, "dbTestUser3");
		if(dbTestUser != null)
			DatabaseController.getInstance().delete(dbTestUser);
		
		assertTrue(((User) DatabaseController.getInstance().load(User.class, "dbTestUser") == null));
		assertTrue(((User) DatabaseController.getInstance().load(User.class, "dbTestUser2") == null));
		assertTrue(((User) DatabaseController.getInstance().load(User.class, "dbTestUser3") == null));
	}

}
