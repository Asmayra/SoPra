package org.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.model.Ticket;
import org.model.User;
import org.view.MainScreen;
import org.view.TicketScreen;
import org.view.screens.Center.TicketScreenAdmin;

/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
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
	/**
	 * Erstellt ein TIcket mit folgendne Attributen
	 * @param category Ticketkategorie
	 * @param currentUser Benutzer,der das Ticket erstellt hat
	 * @param ticketText  Kurznachricht, um das ticket näher zu beschrieben
	 * @return true,falls das ticket erfolgreich erstellt wurde
	 */
	public boolean createTicket(String category, User currentUser, String ticketText){
		Ticket newTicket = new Ticket();
		if (category == null || category == "" || currentUser == null){
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

	/**
	 * Zeigt alle Tickets an,die einer Kategorie zugeordnet sind
	 * @param thema die kathegorie
	 */
	public void showTickets(String thema) {
		ArrayList<Ticket> tickets = (ArrayList<Ticket>) DatabaseControl.getInstance().getTableContent("Ticket");
		Iterator<Ticket> it = tickets.iterator();
		LinkedList<Ticket> ticketsToShow = new LinkedList<Ticket>();
		while(it.hasNext()){
			Ticket current=it.next();
			if(current.getCategory().equals(thema)){
				ticketsToShow.add(current);
			}
		}
		MainScreen.getInstance().updateCenter(new TicketScreenAdmin(ticketsToShow));
	}

	/**
	 * Lässt ein bestimtmes ticket auftachen
	 * @param ticket 
	 */
	public void showSingleTicket(Ticket ticket) {
		LinkedList<Ticket> ticketsToShow = new LinkedList<Ticket>();
		ticketsToShow.add(ticket);
		MainScreen.getInstance().updateCenter(new TicketScreenAdmin(ticketsToShow));
	}

	/**
	 * Zeigt alle übergebenen Tickets
	 * @param ticketsToShow Liste der übergebenen tickets
	 */
	public void showTicketList(LinkedList<Ticket> ticketsToShow) {
		MainScreen.getInstance().updateCenter(new TicketScreenAdmin(ticketsToShow));
	}
	
	
	
	
}
