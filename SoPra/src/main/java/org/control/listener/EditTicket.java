package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.MailboxControl;
import org.model.Ticket;
import org.model.User;
import org.view.MainScreen;
/**
 * Action Listener for the Edit-Ticket-Button
 * @author Philipp
 *
 */
public class EditTicket implements ActionListener {
	
	private Ticket selected;
	private User admin;
	
	public EditTicket(Ticket selected,User admin){
		this.selected= selected;
		this.admin = admin;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		selected.setInWorkBy(admin);
		String[] recipients =  new String[1];
		recipients[0] =selected.getRequester().getUsername();
		MailboxControl.getInstance().sendMessage(admin,recipients, selected.getKategory(), "Ihre Anfrage wird bearbeitet");
		try{
			DatabaseControl.getInstance().save(selected);
		}catch(Exception e){
			
		}
	}

}
