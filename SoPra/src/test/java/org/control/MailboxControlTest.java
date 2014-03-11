package org.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.model.User;

/**
 * JUnitTest für die Klasse MailboxControl
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
@Ignore
public class MailboxControlTest {
	
	
	/**
	 * Testet die Nachrichten senden Funktion
	 */
	@Test
	public void sendMessageTest() {
				
		User mailTestUser = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser");
		
		assertTrue( mailTestUser != null );
		
		User mailTestUser2 = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser2");
		
		assertTrue( mailTestUser2 != null );
		
		String[] testArr = {"mailTestUser2"};
		
		MailboxControl.getInstance().sendMessage(mailTestUser, testArr, "TestTestTest", "TestInhalt");
		
		mailTestUser2 = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser2");
		
		assertTrue( mailTestUser2 != null );
		
		assertTrue( !mailTestUser2.getMessages().isEmpty() );
		
	}
	
	
	/**
	 * Erstellt 2 mailTestUser die in dem JUnitTest verwendet werden
	 */
	@BeforeClass
	public static void beforeClass()
	{
		User mailTestUser = new User();
		mailTestUser.setUsername("mailTestUser");
		
		
		User mailTestUser2 = new User();
		mailTestUser2.setUsername("mailTestUser2");
		
		try {
			DatabaseControl.getInstance().save(mailTestUser);
			DatabaseControl.getInstance().save(mailTestUser2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Löscht die 2 mailTestUser aus der Datenbank.
	 */

	@AfterClass
	public static void afterClass()
	{
		User mailTestUser = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser");
		
		if( mailTestUser != null ){
			try {
				DatabaseControl.getInstance().delete(mailTestUser);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		mailTestUser = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser");
		
		assertTrue( mailTestUser == null );
		
		User mailTestUser2 = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser2");
		
		if( mailTestUser2 != null ){
			try {
				DatabaseControl.getInstance().delete(mailTestUser2);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		mailTestUser2 = (User) DatabaseControl.getInstance().load(User.class, "mailTestUser2");
		
		assertTrue( mailTestUser2 == null );
		
		
	}

}
