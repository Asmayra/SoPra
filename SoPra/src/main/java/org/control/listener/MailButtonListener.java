package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.Mailbox;

public class MailButtonListener implements ActionListener  {

	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().showMailbox(Mailbox.getInstance());
		
		
	}

}
