package org.control;

public class TicketControl {
	
	
	
	private TicketControl(){
		
	}
	
	
	private static TicketControl instance = null;
	
	public TicketControl getInstance(){
		if(instance == null)
			instance = new TicketControl();
			
		return instance;
	}
	
	public Boolean createTicket(){
		Ticket newTicket = new Ticket();
		
		
		
		
	}
	
}
