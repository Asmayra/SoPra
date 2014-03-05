package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.model.Ticket;

public class DeleteTicket implements ActionListener{
	Ticket selected;
	
	public DeleteTicket(Ticket selected){
		this.selected = selected;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selected.setInWorkBy(null);
		selected.setKategory(null);
		selected.setRequester(null);
		selected.setShortInfo(null);
		DatabaseControl.getInstance().delete(selected);
	}

}