package org.control;

import java.io.IOException;

import org.model.Ticket;
import org.model.User;
import org.view.TicketScreen;

public class TicketControl {
	
	
	
	private TicketControl(){
		
	}
	
	
	private static TicketControl instance = null;
	
	public static TicketControl getInstance(){
		if(instance == null)
			instance = new TicketControl();
			
		return instance;
	}
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}
	
	public boolean createTicket(String category, User currentUser, String ticketText){
		Ticket newTicket = new Ticket();
		if (category == null || category == ""){
			return false;
		}
		newTicket.setCategory(category);
		newTicket.setRequester(currentUser);
		newTicket.setShortInfo(ticketText);
		
		
		try {
			DatabaseControl.getInstance().save(newTicket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
}
