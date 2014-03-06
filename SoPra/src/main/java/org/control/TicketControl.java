package org.control;

import java.io.IOException;

import org.model.Ticket;
import org.view.TicketScreen;

public class TicketControl {
	
	
	
	private TicketControl(){
		
	}
	
	
	private static TicketControl instance = null;
	
	public TicketControl getInstance(){
		if(instance == null)
			instance = new TicketControl();
			
		return instance;
	}
	
	public void createTicket(){
		Ticket newTicket = new Ticket();
		newTicket.setCategory(TicketScreen.getInstance().getCategory());
		newTicket.setRequester(LoginControl.getInstance().getCurrentUser());
		newTicket.setShortInfo(TicketScreen.getInstance().getTicketText());
		
		
		try {
			DatabaseControl.getInstance().save(newTicket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
