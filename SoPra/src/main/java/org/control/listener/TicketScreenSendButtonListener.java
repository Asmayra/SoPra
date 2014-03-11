package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.LoginControl;
import org.control.TicketControl;
import org.view.TicketScreen;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class TicketScreenSendButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TicketScreen tS = TicketScreen.getInstance();
		
		if(!(TicketControl.getInstance().createTicket(tS.getCategory(),LoginControl.getInstance().getCurrentUser(),tS.getTicketText()))){
			TicketScreen.getInstance().displayErrorLabel();
		}
		TicketScreen.getInstance().dispose();	
	}

}
