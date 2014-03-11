package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.model.Ticket;
import org.view.MainScreen;
import org.view.screens.Center.AdminHomeScreen;
import org.view.screens.Center.HomeScreen;
/**
 * Löscht ein Bestimmtes Ticket
 * @author Philipp
 *
 */
public class DeleteTicket implements ActionListener{
	Ticket selected;
	
	public DeleteTicket(Ticket selected){
		this.selected = selected;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selected.setInWorkBy(null);
		selected.setCategory(null);
		selected.setRequester(null);
		selected.setShortInfo(null);
		DatabaseControl.getInstance().delete(selected);
		MainScreen.getInstance().updateCenter(new AdminHomeScreen());
	}

}