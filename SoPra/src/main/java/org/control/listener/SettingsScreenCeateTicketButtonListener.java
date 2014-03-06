package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.TicketScreen;

public class SettingsScreenCeateTicketButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TicketScreen.getInstance().resetGui();
		TicketScreen.getInstance().setVisible(true);

	}

}
