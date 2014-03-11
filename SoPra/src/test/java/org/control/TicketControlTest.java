package org.control;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.model.Ticket;
import org.model.User;

/**
 * Test für TicketControl
 * @author Mattias Schoenke, Michal Pfennings
 *
 */
public class TicketControlTest {

	/**
	 * Testet ob createTicket Funktion der TicketControl richtig funktioniert
	 */
	@Test
	public void createTicketTest()
	{
		String testCategory = "Testing";
		String testTicketText = "Testing";
		User testUser = new User();
		testUser.setUsername("TicketTestUser");
		
		try {
			DatabaseControl.getInstance().save(testUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(TicketControl.getInstance().createTicket(testCategory, testUser, testTicketText));
		
		assertFalse(TicketControl.getInstance().createTicket("", testUser, testTicketText));
		
		assertFalse(TicketControl.getInstance().createTicket(null, testUser, testTicketText));
		
		assertFalse(TicketControl.getInstance().createTicket(testCategory, null, testTicketText));
		
		assertTrue(TicketControl.getInstance().createTicket(testCategory, testUser, ""));
		
		for(Object t : DatabaseControl.getInstance().getTableContent("Ticket"))
		{
			if( ( (Ticket)t).getRequester().getUsername().equals("TicketTestUser") )
				DatabaseControl.getInstance().delete(t);
		}
		
		DatabaseControl.getInstance().delete(testUser);
	}

}
