package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.TicketControl;

public class TicketScreenSendButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TicketControl.getInstance().createTicket();
	}

}
