package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.view.MainScreen;
import org.view.screens.Center.Mailbox;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class MailButtonListener implements ActionListener  {

	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().showMailbox(Mailbox.getInstance());
		MailboxControl.getInstance().updateTable();
		
	}

}
